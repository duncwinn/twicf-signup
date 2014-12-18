package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



//We are using @Controller.  @RestController implies @ResponseBody is placed on every method to return a response vs a
//string(view name) that gets mapped to HTML
@Controller
final class UnSubscriptionController {

    private final MailSender mailSender;

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    UnSubscriptionController(JavaMailSender mailSender, SubscriptionRepository subscriptionRepository) {
        this.mailSender = mailSender;
        this.subscriptionRepository = subscriptionRepository;
    }

    /* Get the form; specify request mapping (e.g. GET/POST) else the request method gets mapped to all the http verbs
    This method hands back the view name. Spring allows you to configure the view to be wherever you want but
    boot expects you by default to place your views under /Template */
    @RequestMapping(method = RequestMethod.GET, value = "/unsubscribe")
    String displayForm() {
        return "unSubscribeForm";
    }

    /* Step 2: Post data back: This is the post handler that gets called when you click the subscribe method on the form.
	This method hands back the view name.
	"/" the / is redundant
	@RequestParam is redundant but good practice - Spring will match via variable name if it's the same in the view */
    @RequestMapping(method = RequestMethod.POST, value = "/unsubscribe")
    String createSubscription(@RequestParam String emailAddress) {

        Subscription subscription = subscriptionRepository.findByEmailAddress(emailAddress);

        if(subscription!=null){
            subscriptionRepository.delete(subscription);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailAddress);
            message.setSubject("Goodbye from twicf");
            message.setText("Goodbye from thisweekincf.com, sorry to see you go!");
            message.setFrom("thisweekincf");

            try { this.mailSender.send(message); }catch(Exception e){
                return "redirect:/nomailunsubscribed";
            }

        }else {
            return "redirect:/nosubscriber";
        }


        return "redirect:/unsubscribed";
    }

    //Use this idiom (redirect from POST->GET) to stop resubmitting (POSTing) of the form when refreshing the browser.
    //When refreshing the GET will be refreshed and not the post.

    @RequestMapping(method = RequestMethod.GET, value = "/unsubscribed")
    String confirmation() {
        return "unSubscribedConfirmation";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nosubscriber")
    String reconfirmation() {
        return "noSuchSubscriber";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nomailunsubscribed")
    String noMailConfirmation() {
        return "unSubscribedWithNoEmailConfirmation";
    }

}
