package arya.phonebook.dao.h2.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.UserContact;

public class UserContactDao extends EntityDao<UserContact> {
	private ContactDao contactDao;
	private EmailDetailDao emailDetailDao;

	public UserContactDao() throws ClassNotFoundException, SQLException {
		contactDao = new ContactDao();
		emailDetailDao = new EmailDetailDao();
		super.getStatement().execute(new GenerateCommand<>(UserContact.class).createTable());
	}

	@Override
	public UserContact insert(UserContact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UserContact.class).insertTable());
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
				e.printStackTrace();
			}
		});

	return entity;

	}

	@Override
	public UserContact delete(UserContact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(UserContact.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public UserContact update(UserContact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UserContact.class).updateTable());
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
				e.printStackTrace();
			}
		});

	return entity;
	}

	@Override
	public UserContact select(UserContact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UserContact.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			UserContact userContactReult = new UserContact();
			userContactReult.setBirthday(null); // FIXME
			//FIXME Contact List add
			userContactReult.setDescription(resultSet.getString("DESCRIPTION"));
			//FIXME EmailDetail List add
			userContactReult.setFristname(resultSet.getString("FRISTNAME"));
			userContactReult.setId(resultSet.getInt("ID"));
			userContactReult.setLastname(resultSet.getString("LASTNAME"));
		}
		return entity;
	}
	
	@Override
	public List<UserContact> selects() throws ClassNotFoundException, SQLException {
		List<UserContact> resUserContacts = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(UserContact.class).selectAll());
		while (resultSet.next()) {
			UserContact userContactReult = new UserContact();
			userContactReult.setBirthday(null); // FIXME
			//FIXME Contact List add
			userContactReult.setDescription(resultSet.getString("DESCRIPTION"));
			//FIXME EmailDetail List add
			userContactReult.setFristname(resultSet.getString("FRISTNAME"));
			userContactReult.setId(resultSet.getInt("ID"));
			userContactReult.setLastname(resultSet.getString("LASTNAME"));
			resUserContacts.add(userContactReult);
		}
		return resUserContacts;
	}

	@Override
	public List<UserContact> insert(List<UserContact> entitys) throws ClassNotFoundException, SQLException {
		List<UserContact> resUserContacts = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUserContacts.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUserContacts;
	}

	@Override
	public List<UserContact> delete(List<UserContact> entitys) throws ClassNotFoundException, SQLException {
		List<UserContact> resUserContacts = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUserContacts.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUserContacts;
	}

	@Override
	public List<UserContact> update(List<UserContact> entitys) throws ClassNotFoundException, SQLException {
		List<UserContact> resUserContacts = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUserContacts.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUserContacts;
	}

	@Override
	public UserContact select(int id) throws ClassNotFoundException, SQLException {
		UserContact resUserContact = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UserContact.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resUserContact = new UserContact();
			resUserContact.setBirthday(null); // FIXME
			//FIXME Contact List add
			resUserContact.setDescription(resultSet.getString("DESCRIPTION"));
			//FIXME EmailDetail List add
			resUserContact.setFristname(resultSet.getString("FRISTNAME"));
			resUserContact.setId(resultSet.getInt("ID"));
			resUserContact.setLastname(resultSet.getString("LASTNAME"));
		}
		return resUserContact;
	}



}
