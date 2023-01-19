package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.ContactDao;

public class ContactService extends EntityService {
	private ContactDao contactDao;
	
	public ContactService() throws ClassNotFoundException, SQLException {
		contactDao = new ContactDao();
	}

}
