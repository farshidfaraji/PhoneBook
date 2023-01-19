package arya.phonebook.model;

import java.util.Calendar;
import java.util.List;

import arya.phonebook.model.abstracts.Entity;

public class UserContact extends Entity {
	private String fristname;
	private String lastname;
	private Calendar birthday;
	private List<String> email;
	private String description;
	private List<Contact> contacts;

	public UserContact() {
		super();
	}

	public UserContact(String fristname, String lastname, Calendar birthday, List<String> email, String description,
			List<Contact> contacts) {
		super();
		this.fristname = fristname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.email = email;
		this.description = description;
		this.contacts = contacts;
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

	public List<String> getList() {
		return email;
	}

	public void setList(List<String> list) {
		this.email = list;
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
