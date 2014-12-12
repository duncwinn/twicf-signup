package io.pivotal.twicf.signup;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class SubscriptionTest {

    @Test
    public void test() {
        Subscription subscription = new Subscription("test-email");

        assertEquals("test-email", subscription.getEmailAddress());
    }


}
