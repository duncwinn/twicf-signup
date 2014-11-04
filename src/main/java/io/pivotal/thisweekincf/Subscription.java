package io.pivotal.thisweekincf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Subscription {

	@Id
	@GeneratedValue
	private int id;

	private String emailAddress;

	public Subscription(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	// jpa requires this
	Subscription() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

}
