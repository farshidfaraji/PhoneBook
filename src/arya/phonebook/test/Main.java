package arya.phonebook.test;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.model.User;
import arya.phonebook.model.UserContact;
import arya.phonebook.view.MainForm;

public class Main {

	public static void main(String[] args) {
		System.out.println(new GenerateCommand<>(UserContact.class).createTable());
	}

}
