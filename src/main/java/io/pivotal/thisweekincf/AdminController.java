package io.pivotal.thisweekincf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by duncwinn on 12/11/2014.
 */

@Controller
final class AdminController {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    AdminController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    String displayForm() {
        return "adminForm";
    }


//    @RequestMapping(method = RequestMethod.POST, value = "/admin")
//    ModelAndView createSubscription(String emailAddress) {
//
//        // Save the submitted emailAddress in the DB via the repository
//        List<Subscription> subscriptionList;
//        subscriptionList = subscriptionRepository.findAll();
//
//
//
//        // Return the ModelAndView form... where to go next
//        return "subscribedConfirmation";
//    }


}
