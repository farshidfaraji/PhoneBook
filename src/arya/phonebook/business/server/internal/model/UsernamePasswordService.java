package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.UsernamePasswordDao;
import arya.phonebook.model.UsernamePassword;

public class UsernamePasswordService extends EntityService<UsernamePassword, UsernamePasswordDao> {
	
	public UsernamePasswordService() throws ClassNotFoundException, SQLException {
		dao = new UsernamePasswordDao();
	}
}
