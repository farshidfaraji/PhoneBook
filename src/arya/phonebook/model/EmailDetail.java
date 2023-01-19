package arya.phonebook.model;

import arya.phonebook.model.abstracts.Entity;

public class EmailDetail extends Entity {

	private String email;
	private String description;

	public EmailDetail() {
		super();
	}

	public EmailDetail(String email, String description) {
		super();
		this.email = email;
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
