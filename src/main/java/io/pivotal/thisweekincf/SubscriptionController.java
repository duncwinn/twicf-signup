package io.pivotal.thisweekincf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author DCEW
 */
@Controller
public class SubscriptionController {

	// Get the DB repo to save to
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@RequestMapping("/subscribe")
	public ModelAndView subscriptionForm() {
		return new ModelAndView("subscribeForm");
	}

	// This is the post handler that gets called when you click the subscribe
	// method on the form
	@RequestMapping(value = "/doSubscribe", method = RequestMethod.POST)
	public ModelAndView createSubscription(String emailAddress) {

		// Save the submitted emailAddress in the DB
		subscriptionRepository.save(new Subscription(emailAddress));

		// Return the ModelAndView form... where to go next
		return new ModelAndView("subscribedConfirmation");
	}

}
