package arya.phonebook.model;

import arya.phonebook.model.abstracts.Entity;

public class Phonebook extends Entity {
	private RelLoginPhonebook relLoginPhonebook;
	private Group group;
	private UserContact userContact;

	public Phonebook() {
		super();
	}

	public Phonebook(RelLoginPhonebook relLoginPhonebook, Group group, UserContact userContact) {
		super();
		this.relLoginPhonebook = relLoginPhonebook;
		this.group = group;
		this.userContact = userContact;
	}

	public RelLoginPhonebook getRelLoginPhonebook() {
		return relLoginPhonebook;
	}

	public void setRelLoginPhonebook(RelLoginPhonebook relLoginPhonebook) {
		this.relLoginPhonebook = relLoginPhonebook;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public UserContact getUserContact() {
		return userContact;
	}

	public void setUserContact(UserContact userContact) {
		this.userContact = userContact;
	}

}
