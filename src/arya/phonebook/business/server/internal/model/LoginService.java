package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.LoginDao;
import arya.phonebook.model.Login;

public class LoginService extends EntityService<Login, LoginDao> {
	
	public LoginService() throws ClassNotFoundException, SQLException {
		dao = new LoginDao();
	}

}
