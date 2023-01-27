package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.RelLoginPhonebook;

public class RelLoginPhonebookDao extends EntityDao<RelLoginPhonebook> {
	private LoginDao loginDao;
	private PhonebookDao phonebookDao;

	public RelLoginPhonebookDao() throws ClassNotFoundException, SQLException {
		loginDao = new LoginDao();
		phonebookDao = new PhonebookDao();
		getStatement().execute(ICommands.CREATE_TABLE_RELLOGINPHONEBOOK);
	}

	@Override
	public RelLoginPhonebook insert(RelLoginPhonebook entity) throws ClassNotFoundException, SQLException {
		int idLogin = entity.getLogin().getId();

		if (idLogin == 0) {
			idLogin = loginDao.insert(entity.getLogin()).getId();
		}

		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_RELLOGINPHONEBOOK);
		preparedStatement.setInt(1, idLogin);
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		
		entity.getPhonebooks().forEach( phonebook -> {
			try {
				phonebook.setRelLoginPhonebook(entity);
				phonebookDao.insert(phonebook);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return entity;
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
