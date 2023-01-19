package arya.phonebook.dao.h2.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.UserContact;

public class UserContactDao extends EntityDao<UserContact>{
	private ContactDao contactDao;
	private EmailDetailDao emailDetailDao;
	
	public UserContactDao() throws ClassNotFoundException, SQLException {
		getStatement().execute(ICommands.CREATE_TABLE_USERCONTACT);
	}

	@Override
	public UserContact insert(UserContact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_USERCONTACT);
		preparedStatement.setString(1, entity.getFristname());
		preparedStatement.setString(2, entity.getLastname());
		preparedStatement.setDate(3, (Date)entity.getBirthday().getTime());
		preparedStatement.setString(4, entity.getDescription());
		//preparedStatement.setInt(5, contactDao.insert(entity.));
		//preparedStatement.setInt(6, emailDetailDao.insert(entity.));
		preparedStatement.executeUpdate();
		return null;
	}

	@Override
	public UserContact delete(UserContact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserContact update(UserContact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserContact select(UserContact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserContact> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
