package arya.phonebook.model;

import arya.phonebook.model.abstracts.Entity;

public class Contact extends Entity {
	private String type;
	private String phone;
	private String address;
	private String description;

	public Contact() {
		super();
	}

	public Contact(String type, String phone, String address, String description) {
		super();
		this.type = type;
		this.phone = phone;
		this.address = address;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
