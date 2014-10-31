package io.pivotal.thisweekincf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	// we can write methods here and Spring will create implementations

}
