package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Login;
import arya.phonebook.model.RelLoginPhonebook;

public class RelLoginPhonebookDao extends EntityDao<RelLoginPhonebook> {
	private LoginDao loginDao;
	private PhonebookDao phonebookDao;

	public RelLoginPhonebookDao() throws ClassNotFoundException, SQLException {
		loginDao = new LoginDao();
		phonebookDao = new PhonebookDao();
		super.getStatement().execute(new GenerateCommand<>(RelLoginPhonebook.class).createTable());
	}

	@Override
	public RelLoginPhonebook insert(RelLoginPhonebook entity) throws ClassNotFoundException, SQLException {
		int idLogin = entity.getLogin().getId();

		if (idLogin == 0) {
			idLogin = loginDao.insert(entity.getLogin()).getId();
		}

		PreparedStatement preparedStatement = getPreparedStatement(new GenerateCommand<>(RelLoginPhonebook.class).insertTable());
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
				e.printStackTrace();
			}
		});

		return entity;
	}

	@Override
	public RelLoginPhonebook delete(RelLoginPhonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(RelLoginPhonebook.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public RelLoginPhonebook update(RelLoginPhonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(RelLoginPhonebook.class).updateTable());
		if (entity.getLogin().getId() == 0) {
			preparedStatement.setInt(1, loginDao.insert(entity.getLogin()).getId());
		} else {
			preparedStatement.setInt(1, entity.getLogin().getId());
		}
		// FIXME Phonebook list add
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public RelLoginPhonebook select(RelLoginPhonebook entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(RelLoginPhonebook.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			RelLoginPhonebook relLoginPhonebookReult = new RelLoginPhonebook();
			relLoginPhonebookReult.setId(resultSet.getInt("ID"));
			Login login = new Login();
			login.setId(resultSet.getInt("ID_LOGIN"));
			relLoginPhonebookReult.setLogin(new LoginDao().select(login));
			// FIXME Phonebook list add
		}
		return entity;
	}

	
	@Override
	public List<RelLoginPhonebook> selects() throws ClassNotFoundException, SQLException {
		List<RelLoginPhonebook> resRelLoginPhonebooks = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(RelLoginPhonebook.class).selectAll());
		while (resultSet.next()) {
			RelLoginPhonebook relLoginPhonebookReult = new RelLoginPhonebook();
			relLoginPhonebookReult.setId(resultSet.getInt("ID"));
			Login login = new Login();
			login.setId(resultSet.getInt("ID_LOGIN"));
			relLoginPhonebookReult.setLogin(new LoginDao().select(login));
			// FIXME Phonebook list add
			resRelLoginPhonebooks.add(relLoginPhonebookReult);
		}
		return resRelLoginPhonebooks;
	}

	@Override
	public List<RelLoginPhonebook> insert(List<RelLoginPhonebook> entitys) throws ClassNotFoundException, SQLException {
		List<RelLoginPhonebook> resRelLoginPhonebooks = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resRelLoginPhonebooks.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resRelLoginPhonebooks;
	}

	@Override
	public List<RelLoginPhonebook> delete(List<RelLoginPhonebook> entitys) throws ClassNotFoundException, SQLException {
		List<RelLoginPhonebook> resRelLoginPhonebooks = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resRelLoginPhonebooks.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resRelLoginPhonebooks;
	}

	@Override
	public List<RelLoginPhonebook> update(List<RelLoginPhonebook> entitys) throws ClassNotFoundException, SQLException {
		List<RelLoginPhonebook> resRelLoginPhonebooks = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resRelLoginPhonebooks.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resRelLoginPhonebooks;
	}

	@Override
	public RelLoginPhonebook select(int id) throws ClassNotFoundException, SQLException {
		RelLoginPhonebook resRelLoginPhonebook = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(RelLoginPhonebook.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resRelLoginPhonebook = new RelLoginPhonebook();
			resRelLoginPhonebook.setId(resultSet.getInt("ID"));
			Login login = new Login();
			login.setId(resultSet.getInt("ID_LOGIN"));
			resRelLoginPhonebook.setLogin(new LoginDao().select(login));
			// FIXME Phonebook list add
		}
		return resRelLoginPhonebook;
	}

}
