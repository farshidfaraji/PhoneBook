package arya.phonebook.model;

import java.util.Calendar;
import java.util.List;

import arya.phonebook.model.abstracts.Entity;

public class UserContact extends Entity {
	private String fristname;
	private String lastname;
	private Calendar birthday;
	private List<EmailDetail> emailDetails;
	private String description;
	private List<Contact> contacts;

	public UserContact() {
		super();
	}

	public UserContact(String fristname, String lastname, Calendar birthday, List<EmailDetail> emailDetail,
			String description, List<Contact> contact) {
		super();
		this.fristname = fristname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.emailDetails = emailDetail;
		this.description = description;
		this.contacts = contact;
	}

	public String getFristname() {
		return fristname;
	}

	public void setFristname(String fristname) {
		this.fristname = fristname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public List<EmailDetail> getEmailDetails() {
		return emailDetails;
	}

	public void setEmailDetails(List<EmailDetail> emailDetails) {
		this.emailDetails = emailDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
