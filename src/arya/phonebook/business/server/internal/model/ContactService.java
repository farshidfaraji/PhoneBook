package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.ContactDao;
import arya.phonebook.model.Contact;

public class ContactService extends EntityService<Contact, ContactDao> {
	
	public ContactService() throws ClassNotFoundException, SQLException {
		dao = new ContactDao();
	}

}
