package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Service;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by duncwinn on 16/12/2014.
 */

@Controller
final class AdminController {

    //Define vars that you want spring to provide; with final for immutability
    private final Optional<Cloud> cloud;  //Use Optional as we only get this in the cloud
    private final Environment environment;
    private final SubscriptionRepository subscriptionRepository;


    @Autowired
    AdminController(Optional<Cloud> cloud, Environment environment, SubscriptionRepository subscriptionRepository) {
        this.cloud = cloud;
        this.environment = environment;
        this.subscriptionRepository = subscriptionRepository;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    String displayForm(Model model) {
        model.addAttribute("profiles", this.environment.getActiveProfiles());
        model.addAttribute("services", getServiceNames());

        System.out.println("The cloud contains... " + this.cloud.toString());

        return "adminForm";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/admin")
    ModelAndView createSubscription() {
        ModelAndView modelAndView = new ModelAndView("adminResponse");
        modelAndView.addObject("subscriptions", subscriptionRepository.findAll());
        modelAndView.addObject("profiles", this.environment.getActiveProfiles());
        modelAndView.addObject("services", getServiceNames());

        return modelAndView;
    }


    //Just calling cloud.getServiceInfos() give too much gumf so this method extracts service name
    private List<String> getServiceNames() {

        return this.cloud
                .map(cloud -> cloud.getServiceInfos().stream().map(ServiceInfo::getId).collect(Collectors.toList()))
                .orElse(Arrays.asList("Local Environment", "No Cloud Present"));
    }


}
