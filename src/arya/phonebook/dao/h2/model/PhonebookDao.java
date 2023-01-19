package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Phonebook;

public class PhonebookDao extends EntityDao<Phonebook> {
	private RelLoginPhonebookDao relLoginPhonebookDao;
	private UserContactDao userContactDao;
	private GroupDao groupDao;

	public PhonebookDao() throws ClassNotFoundException, SQLException {
		relLoginPhonebookDao = new RelLoginPhonebookDao();
		userContactDao = new UserContactDao();
		groupDao = new GroupDao();
		getStatement().execute(ICommands.CREATE_TABLE_PHONEBOOK);
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

		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_PHONEBOOK);
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
	public Phonebook delete(Phonebook entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Phonebook update(Phonebook entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Phonebook select(Phonebook entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Phonebook> select() {
		// TODO Auto-generated method stub
		return null;
	}
}
