package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.GroupDao;

public class GroupService extends EntityService{
	private GroupDao groupDao;
	public GroupService() throws ClassNotFoundException, SQLException {
		groupDao = new GroupDao();
	}

}
