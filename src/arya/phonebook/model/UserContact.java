package arya.phonebook.model;

import java.util.Calendar;

import arya.phonebook.model.abstracts.Entity;

public class UserContact extends Entity {
	private String fristname;
	private String lastname;
	private Calendar birthday;
	private EmailDetail emailDetail;
	private String description;
	private Contact contact;

	public UserContact() {
		super();
	}

	public UserContact(String fristname, String lastname, Calendar birthday, EmailDetail emailDetail,
			String description, Contact contact) {
		super();
		this.fristname = fristname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.emailDetail = emailDetail;
		this.description = description;
		this.contact = contact;
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

	public EmailDetail getEmailDetail() {
		return emailDetail;
	}

	public void setEmailDetail(EmailDetail emailDetail) {
		this.emailDetail = emailDetail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
