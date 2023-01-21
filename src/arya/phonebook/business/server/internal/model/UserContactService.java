package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.UserContactDao;
import arya.phonebook.model.UserContact;

public class UserContactService extends EntityService<UserContact, UserContactDao> {

	public UserContactService() throws ClassNotFoundException, SQLException {
		dao = new UserContactDao();
	}

}
