package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.GroupDao;
import arya.phonebook.model.Group;

public class GroupService extends EntityService<Group, GroupDao> {

	public GroupService() throws ClassNotFoundException, SQLException {
		dao = new GroupDao();
	}

}
