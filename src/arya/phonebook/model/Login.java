package arya.phonebook.model;

import arya.phonebook.model.abstracts.Entity;

public class Login extends Entity {
	private User user;
	private UsernamePassword usernamePassword;

	public Login() {
		super();
	}

	public Login(User user, UsernamePassword usernamePassword) {
		super();
		this.user = user;
		this.usernamePassword = usernamePassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UsernamePassword getUsernamePassword() {
		return usernamePassword;
	}

	public void setUsernamePassword(UsernamePassword usernamePassword) {
		this.usernamePassword = usernamePassword;
	}

}
