package io.pivotal.twicf.signup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Subscription {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private volatile int id;

	private volatile String emailAddress;
    private volatile String password;
    private volatile String role = "";


	public Subscription(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	Subscription() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

}
