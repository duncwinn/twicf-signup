package io.pivotal.thisweekincf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Subscription {

	@Id
	@GeneratedValue
	private volatile int id;

	private volatile String emailAddress;

	public Subscription(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	Subscription() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

}
