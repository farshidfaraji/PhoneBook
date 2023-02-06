package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Contact;
import arya.phonebook.model.UserContact;

public class ContactDao extends EntityDao<Contact> {

	private UserContactDao userContactDao;

	public ContactDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(new GenerateCommand<>(Contact.class).createTable());
	}

	@Override
	public Contact insert(Contact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Contact.class).insertTable());
		preparedStatement.setString(1, entity.getType());
		preparedStatement.setString(2, entity.getPhone());
		preparedStatement.setString(3, entity.getAddress());
		preparedStatement.setString(4, entity.getDescription());
		if (entity.getUserContact().getId() == 0) {
			preparedStatement.setInt(5, userContactDao.insert(entity.getUserContact()).getId());
		} else {
			preparedStatement.setInt(5, entity.getUserContact().getId());
		}
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}

		return entity;
	}

	@Override
	public Contact delete(Contact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Contact.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Contact update(Contact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Contact.class).updateTable());
		preparedStatement.setString(1, entity.getType());
		preparedStatement.setString(2, entity.getPhone());
		preparedStatement.setString(3, entity.getAddress());
		preparedStatement.setString(4, entity.getDescription());
		if (entity.getUserContact().getId() == 0) {
			preparedStatement.setInt(5, userContactDao.insert(entity.getUserContact()).getId());
		} else {
			preparedStatement.setInt(5, entity.getUserContact().getId());
		}
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Contact select(Contact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Contact.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Contact contactReult = new Contact();
			contactReult.setAddress(resultSet.getString("ADDRESS"));
			contactReult.setDescription(resultSet.getString("DESCRIPTION"));
			contactReult.setId(resultSet.getInt("ID"));
			contactReult.setPhone(resultSet.getString("PHONE"));
			contactReult.setType(resultSet.getString("TYPE"));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			contactReult.setUserContact(new UserContactDao().select(userContact));
		}
		return entity;
	}

	@Override
	public List<Contact> selects() throws ClassNotFoundException, SQLException {
		List<Contact> resContacts = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(Contact.class).selectAll());
		while (resultSet.next()) {
			Contact contactReult = new Contact();
			contactReult.setAddress(resultSet.getString("ADDRESS"));
			contactReult.setDescription(resultSet.getString("DESCRIPTION"));
			contactReult.setId(resultSet.getInt("ID"));
			contactReult.setPhone(resultSet.getString("PHONE"));
			contactReult.setType(resultSet.getString("TYPE"));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			contactReult.setUserContact(new UserContactDao().select(userContact));
			resContacts.add(contactReult);
		}
		return resContacts;
	}

	@Override
	public List<Contact> insert(List<Contact> entitys) throws ClassNotFoundException, SQLException {
		List<Contact> resContacts = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resContacts.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resContacts;
	}

	@Override
	public List<Contact> delete(List<Contact> entitys) throws ClassNotFoundException, SQLException {
		List<Contact> resContacts = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resContacts.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resContacts;
	}

	@Override
	public List<Contact> update(List<Contact> entitys) throws ClassNotFoundException, SQLException {
		List<Contact> rescContacts = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				rescContacts.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return rescContacts;
	}

	@Override
	public Contact select(int id) throws ClassNotFoundException, SQLException {
		Contact resContact = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Contact.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resContact = new Contact();
			resContact.setAddress(resultSet.getString("ADDRESS"));
			resContact.setDescription(resultSet.getString("DESCRIPTION"));
			resContact.setId(resultSet.getInt("ID"));
			resContact.setPhone(resultSet.getString("PHONE"));
			resContact.setType(resultSet.getString("TYPE"));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			resContact.setUserContact(new UserContactDao().select(userContact));
		}
		return resContact;
	}
}
