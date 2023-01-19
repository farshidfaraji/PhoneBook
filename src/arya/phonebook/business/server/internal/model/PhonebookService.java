package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.PhonebookDao;

public class PhonebookService extends EntityService{
	private PhonebookDao phonebookDao;
	public PhonebookService() throws ClassNotFoundException, SQLException {
		phonebookDao = new PhonebookDao();
	}
}
