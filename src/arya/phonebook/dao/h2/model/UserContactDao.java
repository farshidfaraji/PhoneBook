package arya.phonebook.dao.h2.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.UserContact;

public class UserContactDao extends EntityDao<UserContact> {
	private ContactDao contactDao;
	private EmailDetailDao emailDetailDao;

	public UserContactDao() throws ClassNotFoundException, SQLException {
		contactDao = new ContactDao();
		emailDetailDao = new EmailDetailDao();
		getStatement().execute(ICommands.CREATE_TABLE_USERCONTACT);
	}

	@Override
	public UserContact insert(UserContact entity) throws ClassNotFoundException, SQLException {

		int idContact = entity.getContact().getId();
		int idEmailDetail = entity.getEmailDetail().getId();

		if (idContact == 0) {
			idContact = contactDao.insert(entity.getContact()).getId();
		}

		if (idEmailDetail == 0) {
			idEmailDetail = emailDetailDao.insert(entity.getEmailDetail()).getId();
		}

		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_USERCONTACT);
		preparedStatement.setString(1, entity.getFristname());
		preparedStatement.setString(2, entity.getLastname());
		preparedStatement.setDate(3, (Date) entity.getBirthday().getTime());
		preparedStatement.setString(4, entity.getDescription());
		preparedStatement.setInt(5, idContact);
		preparedStatement.setInt(6, idEmailDetail);
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}

		return entity;
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
