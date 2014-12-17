package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by duncwinn on 16/12/2014.
 */

@Controller
final class AdminController {

    //Define the vars that you want boot to provide
    //Make them final for immutability
    private final Environment environment;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired(required = false)
    private Cloud cloud;

    @Autowired
    AdminController(Environment environment, SubscriptionRepository subscriptionRepository) {
        this.environment = environment;
        this.subscriptionRepository = subscriptionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    String displayForm(Model model) {
        model.addAttribute("profiles", this.environment.getActiveProfiles());

        if (cloud != null) {
            System.out.println("Cloud services:" + cloud.getServiceInfos());
            model.addAttribute("services", cloud.getServiceInfos());
        }

        return "adminForm";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/admin")
    ModelAndView createSubscription() {
        ModelAndView modelAndView = new ModelAndView("adminResponse");
        modelAndView.addObject("subscriptions", subscriptionRepository.findAll());
        return modelAndView;
    }

}
