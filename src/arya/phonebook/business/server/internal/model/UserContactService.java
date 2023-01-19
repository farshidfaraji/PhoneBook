package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.UserContactDao;

public class UserContactService extends EntityService{
	private UserContactDao userContactDao;

	public UserContactService() throws ClassNotFoundException, SQLException {
		userContactDao = new UserContactDao();
	}

}
