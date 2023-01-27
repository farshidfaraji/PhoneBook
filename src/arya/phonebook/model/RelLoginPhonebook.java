package arya.phonebook.model;

import java.util.List;

import arya.phonebook.model.abstracts.Entity;

public class RelLoginPhonebook extends Entity {
	private Login login;
	private List<Phonebook> phonebooks;

	public RelLoginPhonebook() {
		super();
	}

	public RelLoginPhonebook(Login login, List<Phonebook> phonebooks) {
		super();
		this.login = login;
		this.phonebooks = phonebooks;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Phonebook> getPhonebooks() {
		return phonebooks;
	}

	public void setPhonebooks(List<Phonebook> phonebooks) {
		this.phonebooks = phonebooks;
	}

}
