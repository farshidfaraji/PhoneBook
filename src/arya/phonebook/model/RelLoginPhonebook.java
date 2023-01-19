package arya.phonebook.model;

import java.util.List;

import arya.phonebook.model.abstracts.Entity;

public class RelLoginPhonebook extends Entity {
	private Login login;
	private List<Phonebook> phonebook;

	public RelLoginPhonebook() {
		super();
	}

	public RelLoginPhonebook(Login login, List<Phonebook> phonebook) {
		super();
		this.login = login;
		this.phonebook = phonebook;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Phonebook> getPhonebook() {
		return phonebook;
	}

	public void setPhonebook(List<Phonebook> phonebook) {
		this.phonebook = phonebook;
	}

}
