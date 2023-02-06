package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Login;
import arya.phonebook.model.User;
import arya.phonebook.model.UsernamePassword;

public class LoginDao extends EntityDao<Login> {
	private UserDao userDao;
	private UsernamePasswordDao usernamePasswordDao;

	public LoginDao() throws ClassNotFoundException, SQLException {
		userDao = new UserDao();
		usernamePasswordDao = new UsernamePasswordDao();
		super.getStatement().execute(new GenerateCommand<>(Login.class).createTable());
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

		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Login.class).insertTable());
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
	public Login delete(Login entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Login.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Login update(Login entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Login.class).updateTable());
		if (entity.getUser().getId() == 0) {
			preparedStatement.setInt(1, userDao.insert(entity.getUser()).getId());
		} else {
			preparedStatement.setInt(1, entity.getUser().getId());
		}
		if (entity.getUsernamePassword().getId() == 0) {
			preparedStatement.setInt(2, usernamePasswordDao.insert(entity.getUsernamePassword()).getId());
		} else {
			preparedStatement.setInt(2, entity.getUsernamePassword().getId());
		}
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Login select(Login entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Login.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Login loginReult = new Login();
			loginReult.setId(resultSet.getInt("ID"));
			User user = new User();
			user.setId(resultSet.getInt("ID_USER"));
			loginReult.setUser(new UserDao().select(user));
			UsernamePassword usernamePassword = new UsernamePassword();
			usernamePassword.setId(resultSet.getInt("ID_USERNAMEPASSWORD"));
			loginReult.setUsernamePassword(new UsernamePasswordDao().select(usernamePassword));
		}
		return entity;
	}

	@Override
	public List<Login> selects() throws ClassNotFoundException, SQLException {
		List<Login> resLogins = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(Login.class).selectAll());
		while (resultSet.next()) {
			Login loginReult = new Login();
			loginReult.setId(resultSet.getInt("ID"));
			User user = new User();
			user.setId(resultSet.getInt("ID_USER"));
			loginReult.setUser(new UserDao().select(user));
			UsernamePassword usernamePassword = new UsernamePassword();
			usernamePassword.setId(resultSet.getInt("ID_USERNAMEPASSWORD"));
			loginReult.setUsernamePassword(new UsernamePasswordDao().select(usernamePassword));
			resLogins.add(loginReult);
		}
		return resLogins;
	}

	@Override
	public List<Login> insert(List<Login> entitys) throws ClassNotFoundException, SQLException {
		List<Login> resLogins = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resLogins.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resLogins;
	}

	@Override
	public List<Login> delete(List<Login> entitys) throws ClassNotFoundException, SQLException {
		List<Login> resLogins = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resLogins.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resLogins;
	}

	@Override
	public List<Login> update(List<Login> entitys) throws ClassNotFoundException, SQLException {
		List<Login> resLogins = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resLogins.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resLogins;
	}

	@Override
	public Login select(int id) throws ClassNotFoundException, SQLException {
		Login resLogin = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Login.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resLogin = new Login();
			resLogin.setId(resultSet.getInt("ID"));
			User user = new User();
			user.setId(resultSet.getInt("ID_USER"));
			resLogin.setUser(new UserDao().select(user));
			UsernamePassword usernamePassword = new UsernamePassword();
			usernamePassword.setId(resultSet.getInt("ID_USERNAMEPASSWORD"));
			resLogin.setUsernamePassword(new UsernamePasswordDao().select(usernamePassword));
		}
		return resLogin;
	}

}
