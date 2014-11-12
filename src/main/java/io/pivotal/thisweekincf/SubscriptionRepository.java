package io.pivotal.thisweekincf;

import org.springframework.data.jpa.repository.JpaRepository;

interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Subscription findByEmailAddress(String emailAddress);

}
