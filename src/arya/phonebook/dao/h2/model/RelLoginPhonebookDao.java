package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.RelLoginPhonebook;

public class RelLoginPhonebookDao extends EntityDao<RelLoginPhonebook>{
	private LoginDao loginDao;
	
	public RelLoginPhonebookDao() throws ClassNotFoundException, SQLException {
		getStatement().execute(ICommands.CREATE_TABLE_RELLOGINPHONEBOOK);
	}

	@Override
	public RelLoginPhonebook insert(RelLoginPhonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_RELLOGINPHONEBOOK);
		preparedStatement.setInt(1, loginDao.insert(entity.getLogin()).getId());
		preparedStatement.executeUpdate();
		return null;
	}

	@Override
	public RelLoginPhonebook delete(RelLoginPhonebook entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelLoginPhonebook update(RelLoginPhonebook entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelLoginPhonebook select(RelLoginPhonebook entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RelLoginPhonebook> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
