package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(method = RequestMethod.POST, value = "/admin")
    ModelAndView createSubscription() {
        ModelAndView modelAndView = new ModelAndView("adminResponse");
        modelAndView.addObject("subscriptions",subscriptionRepository.findAll());
        return modelAndView;
    }

}
