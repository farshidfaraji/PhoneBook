package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.PhonebookDao;
import arya.phonebook.model.Phonebook;

public class PhonebookService extends EntityService<Phonebook, PhonebookDao> {
	
	public PhonebookService() throws ClassNotFoundException, SQLException {
		dao = new PhonebookDao();
	}
}
