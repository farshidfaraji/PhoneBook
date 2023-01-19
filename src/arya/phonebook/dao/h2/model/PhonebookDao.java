package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Phonebook;

public class PhonebookDao extends EntityDao<Phonebook>{
	private RelLoginPhonebookDao relLoginPhonebookDao;
	private UserContactDao userContactDao;
	private GroupDao groupDao;
	
	public PhonebookDao() throws ClassNotFoundException, SQLException {
		getStatement().execute(ICommands.CREATE_TABLE_PHONEBOOK);
	}

	@Override
	public Phonebook insert(Phonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_PHONEBOOK);
		preparedStatement.setInt(1, relLoginPhonebookDao.insert(entity.getRelLoginPhonebook()).getId());
		preparedStatement.setInt(2, userContactDao.insert(entity.getUserContact()).getId());
		preparedStatement.setInt(3, groupDao.insert(entity.getGroup()).getId());
		preparedStatement.executeUpdate();
		
		return null;
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
