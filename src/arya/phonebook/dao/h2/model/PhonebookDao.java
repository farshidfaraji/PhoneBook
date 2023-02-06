package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Phonebook;
import arya.phonebook.model.RelLoginPhonebook;
import arya.phonebook.model.UserContact;

public class PhonebookDao extends EntityDao<Phonebook> {
	private RelLoginPhonebookDao relLoginPhonebookDao;
	private UserContactDao userContactDao;
	private GroupDao groupDao;

	public PhonebookDao() throws ClassNotFoundException, SQLException {
		relLoginPhonebookDao = new RelLoginPhonebookDao();
		userContactDao = new UserContactDao();
		groupDao = new GroupDao();
		super.getStatement().execute(new GenerateCommand<>(Phonebook.class).createTable());
	}

	@Override
	public Phonebook insert(Phonebook entity) throws ClassNotFoundException, SQLException {

		int idRelLoginPhonebook = entity.getRelLoginPhonebook().getId();
		int idUserContact = entity.getUserContact().getId();
		int idGroup = entity.getGroup().getId();

		if (idRelLoginPhonebook == 0) {
			idRelLoginPhonebook = relLoginPhonebookDao.insert(entity.getRelLoginPhonebook()).getId();
		}

		if (idUserContact == 0) {
			idUserContact = userContactDao.insert(entity.getUserContact()).getId();
		}

		if (idGroup == 0) {
			idGroup = groupDao.insert(entity.getGroup()).getId();
		}

		PreparedStatement preparedStatement = getPreparedStatement(
				new GenerateCommand<>(Phonebook.class).insertTable());
		preparedStatement.setInt(1, idRelLoginPhonebook);
		preparedStatement.setInt(2, idUserContact);
		preparedStatement.setInt(3, idGroup);
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}

		return entity;
	}

	@Override
	public Phonebook delete(Phonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Phonebook.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Phonebook update(Phonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Phonebook.class).updateTable());
		if (entity.getRelLoginPhonebook().getId() == 0) {
			preparedStatement.setInt(1, relLoginPhonebookDao.insert(entity.getRelLoginPhonebook()).getId());
		} else {
			preparedStatement.setInt(1, entity.getRelLoginPhonebook().getId());
		}
		if (entity.getUserContact().getId() == 0) {
			preparedStatement.setInt(2, userContactDao.insert(entity.getUserContact()).getId());
		} else {
			preparedStatement.setInt(2, entity.getUserContact().getId());
		}
		if (entity.getGroup().getId() == 0 ) {
			preparedStatement.setInt(3, groupDao.insert(entity.getGroup()).getId());
		}else {
			preparedStatement.setInt(3, entity.getGroup().getId());
		}
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Phonebook select(Phonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Phonebook.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Phonebook phonebookReult = new Phonebook();
			phonebookReult.setId(resultSet.getInt("ID"));
			RelLoginPhonebook relLoginPhonebook = new RelLoginPhonebook();
			relLoginPhonebook.setId(resultSet.getInt("ID_RELLOGINPHONEBOOK"));
			phonebookReult.setRelLoginPhonebook(new RelLoginPhonebookDao().select(relLoginPhonebook));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			phonebookReult.setUserContact(new UserContactDao().select(userContact));
		}
		return entity;
	}

	@Override
	public List<Phonebook> selects() throws ClassNotFoundException, SQLException {
		List<Phonebook> resPhonebooks = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(Phonebook.class).selectAll());
		while (resultSet.next()) {
			Phonebook phonebookReult = new Phonebook();
			phonebookReult.setId(resultSet.getInt("ID"));
			RelLoginPhonebook relLoginPhonebook = new RelLoginPhonebook();
			relLoginPhonebook.setId(resultSet.getInt("ID_RELLOGINPHONEBOOK"));
			phonebookReult.setRelLoginPhonebook(new RelLoginPhonebookDao().select(relLoginPhonebook));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			phonebookReult.setUserContact(new UserContactDao().select(userContact));
			resPhonebooks.add(phonebookReult);
		}
		return resPhonebooks;
	}
	
	@Override
	public List<Phonebook> insert(List<Phonebook> entitys) throws ClassNotFoundException, SQLException {
		List<Phonebook> resPhonebooks = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resPhonebooks.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resPhonebooks;
	}
	@Override
	public List<Phonebook> delete(List<Phonebook> entitys) throws ClassNotFoundException, SQLException {
		List<Phonebook> resPhonebooks = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resPhonebooks.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resPhonebooks;
	}

	@Override
	public List<Phonebook> update(List<Phonebook> entitys) throws ClassNotFoundException, SQLException {
		List<Phonebook> resPhonebooks = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resPhonebooks.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resPhonebooks;
	}

	@Override
	public Phonebook select(int id) throws ClassNotFoundException, SQLException {
		Phonebook resPhonebook = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Phonebook.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resPhonebook = new Phonebook();
			resPhonebook.setId(resultSet.getInt("ID"));
			RelLoginPhonebook relLoginPhonebook = new RelLoginPhonebook();
			relLoginPhonebook.setId(resultSet.getInt("ID_RELLOGINPHONEBOOK"));
			resPhonebook.setRelLoginPhonebook(new RelLoginPhonebookDao().select(relLoginPhonebook));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			resPhonebook.setUserContact(new UserContactDao().select(userContact));
		}
		return resPhonebook;
	}

	
}
