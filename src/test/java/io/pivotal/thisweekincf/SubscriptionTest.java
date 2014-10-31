package io.pivotal.thisweekincf;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.pivotal.test.WebTestBase;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

// browser test extending WebTestBase

public class SubscriptionTest extends WebTestBase {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Test
	public void userCanCreateASubscription() throws Exception {
		// prepare
		subscriptionRepository.deleteAll();

		// act... the thing you are testing
		goTo("/subscribe");
		fill("#emailAddress").with("test@example.org");
		click("#subscribe");

		// assert
		assertThat(find("#message").getText(), is(equalTo("Thanks for subscribing!")));

		List<Subscription> subscriptions = subscriptionRepository.findAll();
		assertThat(subscriptions, hasSize(1));
		assertThat(subscriptions.get(0).getEmailAddress(), is(equalTo("test@example.org")));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void twoUsersCanCreateASubscriptionEach() throws Exception {
		// prepare
		subscriptionRepository.deleteAll();

		// act... the thing you are testing

		// first user
		goTo("/subscribe");
		fill("#emailAddress").with("tweedledum@example.org");
		click("#subscribe");

		// second user
		goTo("/subscribe");
		fill("#emailAddress").with("tweedledee@example.org");
		click("#subscribe");

		// assert
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		assertThat(subscriptions, containsInAnyOrder(hasProperty("emailAddress", equalTo("tweedledum@example.org")),
													 hasProperty("emailAddress", equalTo("tweedledee@example.org"))));

	}
}
