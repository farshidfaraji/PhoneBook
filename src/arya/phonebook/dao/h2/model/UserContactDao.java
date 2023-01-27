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
		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_USERCONTACT);
		preparedStatement.setString(1, entity.getFristname());
		preparedStatement.setString(2, entity.getLastname());
		if (entity.getBirthday() != null) {
			preparedStatement.setDate(3, new Date(entity.getBirthday().getTimeInMillis()));
			// preparedStatement.setTimestamp(3, new Timestamp(entity.getBirthday().getTimeInMillis()));
		}else {
			preparedStatement.setDate(3, null);
		}
		preparedStatement.setString(4, entity.getDescription());
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		
		entity.getContacts().forEach(contact -> {
				try {
					if(contact.getId() == 0) {
						contact.setUserContact(entity);
						contactDao.insert(contact);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		
		entity.getEmailDetails().forEach(emailDetail -> {
			try {
				if (emailDetail.getId() == 0) {
					emailDetail.setUserContact(entity);
					emailDetailDao.insert(emailDetail);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

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
