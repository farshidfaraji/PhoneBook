package arya.phonebook.model;

import java.util.Calendar;

import arya.phonebook.model.abstracts.Entity;
import arya.phonebook.model.enums.EnumGender;

public class User extends Entity {
	private String fristname;
	private String lastname;
	private byte[] photo;
	private String email;
	private String nationalID;
	private String address;
	private Calendar birthday;
	private String phone;
	private EnumGender gender;

	public User() {
		super();
	}

	public User(String fristname, String lastname, byte[] photo, String email, String nationalID, String address,
			Calendar birthday, String phone, EnumGender gender) {
		super();
		this.fristname = fristname;
		this.lastname = lastname;
		this.photo = photo;
		this.email = email;
		this.nationalID = nationalID;
		this.address = address;
		this.birthday = birthday;
		this.phone = phone;
		this.gender = gender;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public EnumGender getGender() {
		return gender;
	}

	public void setGender(EnumGender gender) {
		this.gender = gender;
	}

}
