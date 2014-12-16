package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/*
We are using @Controller.
@RestController implies @ResponseBody is placed on every method to return a response vs a
string(view name) that gets mapped to HTML
 */

@Controller
final class SubscriptionController {

    private final MailSender mailSender;

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    SubscriptionController(JavaMailSender mailSender, SubscriptionRepository subscriptionRepository) {
        this.mailSender = mailSender;
        this.subscriptionRepository = subscriptionRepository;
    }

    /*
    STEP 1: Get the form
    Specify the request mapping (e.g. GET/POST) else the request method gets mapped to all the http verbs

    This method hands back the view name. Spring allows you to configure the view to be wherever you want but
    boot expects you by default to place your views under /Template
     */
    @RequestMapping(method = RequestMethod.GET, value = "")
    String displayForm() {
        return "subscribeForm";
    }

    /*
    Step 2: Post data back
    Step 3: Display Confirmation
	This is the post handler that gets called when you click the subscribe method on the form.
	This method hands back the view name.
	"/" the / is redundant
	@RequestParam is redundant but good practice - Spring will match via variable name if it's the same in the view
	*/
    @RequestMapping(method = RequestMethod.POST, value = "")
    String createSubscription(@RequestParam String emailAddress) {

        //Send a comfirmation email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject("Signed Up to twicf");
        message.setText("Welcome to thisweekincf.com");

        try {
            this.mailSender.send(message);
        }catch(Exception e){
            return "redirect:/nomailconfirmation";
        }

        //Save the submitted emailAddress in the DB via the repository
        try {
            subscriptionRepository.save(new Subscription(emailAddress));
        }catch(DataIntegrityViolationException e) {
        //DataIntegrityViolationException are thrown if email already exists in DB because of
        //DDL specifies CONSTRAINT unique_email_address UNIQUE (email_address)
            return "redirect:/reconfirmation";
        }

        // Return the view via redirect... where to go next
        return "redirect:/confirmation";
    }

    //Use this idiom to stop resubmitting of the form when refreshing the browser.
    //When refreshing the GET will be refreshed and not the post.
    @RequestMapping(method = RequestMethod.GET, value = "/confirmation")
    String confirmation() {
        return "subscribedConfirmation";
    }

    //Use this idiom to stop resubmitting of the form when refreshing the browser.
    //When refreshing the GET will be refreshed and not the post.
    @RequestMapping(method = RequestMethod.GET, value = "/reconfirmation")
    String reconfirmation() {
        return "subscribedReConfirmation";
    }

    //Use this idiom to stop resubmitting of the form when refreshing the browser.
    //When refreshing the GET will be refreshed and not the post.
    @RequestMapping(method = RequestMethod.GET, value = "/nomailconfirmation")
    String noMailConfirmation() {
        return "subscribedWithNoEmailConfirmation";
    }

}
