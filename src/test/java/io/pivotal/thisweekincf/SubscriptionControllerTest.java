package io.pivotal.thisweekincf;

import static org.mockito.Mockito.mock;

public class SubscriptionControllerTest {

    private final SubscriptionRepository subscriptionRepository = mock(SubscriptionRepository.class);

    private final SubscriptionController controller = new SubscriptionController(subscriptionRepository);
}
