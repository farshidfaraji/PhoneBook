package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.UserDao;
import arya.phonebook.model.User;

public class UserService extends EntityService<User, UserDao> {

	public UserService() throws ClassNotFoundException, SQLException {
		dao = new UserDao();
	}

}
