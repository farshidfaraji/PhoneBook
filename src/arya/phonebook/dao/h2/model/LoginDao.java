package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Login;

public class LoginDao extends EntityDao<Login> {
	private UserDao userDao;
	private UsernamePasswordDao usernamePasswordDao;

	public LoginDao() throws ClassNotFoundException, SQLException {
		userDao = new UserDao();
		usernamePasswordDao = new UsernamePasswordDao();
		super.getStatement().execute(ICommands.CREATE_TABLE_LOGIN);
	}

	@Override
	public Login insert(Login entity) throws ClassNotFoundException, SQLException {
		
		int idUser = entity.getUser().getId();
		int idUsernamePassword = entity.getUsernamePassword().getId();

		if (idUser == 0) {
			idUser = userDao.insert(entity.getUser()).getId();
		}
		if (idUsernamePassword == 0) {
			idUsernamePassword = usernamePasswordDao.insert(entity.getUsernamePassword()).getId();
		}

		PreparedStatement preparedStatement = super.getPreparedStatement(ICommands.INSERT_LOGIN);
		preparedStatement.setInt(1, idUser);
		preparedStatement.setInt(2, idUsernamePassword);
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}

		return entity;
	}

	@Override
	public Login delete(Login entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login update(Login entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login select(Login entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> select() {
		// TODO Auto-generated method stub
		return null;
	}
}
