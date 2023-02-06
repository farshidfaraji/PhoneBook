package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.UsernamePassword;

public class UsernamePasswordDao extends EntityDao<UsernamePassword> {
	public UsernamePasswordDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(new GenerateCommand<>(UsernamePassword.class).createTable());
	}

	@Override
	public UsernamePassword insert(UsernamePassword entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UsernamePassword.class).insertTable());
		preparedStatement.setString(1, entity.getUsername());
		preparedStatement.setString(2, entity.getPassword());
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		return entity;
	}

	@Override
	public UsernamePassword delete(UsernamePassword entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(UsernamePassword.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public UsernamePassword update(UsernamePassword entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UsernamePassword.class).updateTable());
		preparedStatement.setString(1, entity.getUsername());
		preparedStatement.setString(2, entity.getPassword());
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		return entity;
	}

	@Override
	public UsernamePassword select(UsernamePassword entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UsernamePassword.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			UsernamePassword usernamePasswordResult = new UsernamePassword();
			usernamePasswordResult.setId(resultSet.getInt("ID"));
			usernamePasswordResult.setPassword(resultSet.getString("PASSWORD"));
			usernamePasswordResult.setUsername(resultSet.getString("USERNAME"));
		}
		return entity;
	}

	@Override
	public List<UsernamePassword> selects() throws ClassNotFoundException, SQLException {
		List<UsernamePassword> resUsernamePasswords = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(UsernamePassword.class).selectAll());
		while (resultSet.next()) {
			UsernamePassword usernamePasswordResult = new UsernamePassword();
			usernamePasswordResult.setId(resultSet.getInt("ID"));
			usernamePasswordResult.setPassword(resultSet.getString("PASSWORD"));
			usernamePasswordResult.setUsername(resultSet.getString("USERNAME"));
			resUsernamePasswords.add(usernamePasswordResult);
		}
		return resUsernamePasswords;
	}
	
	@Override
	public List<UsernamePassword> insert(List<UsernamePassword> entitys) throws ClassNotFoundException, SQLException {
		List<UsernamePassword> resUsernamePasswords = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUsernamePasswords.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUsernamePasswords;
	}

	@Override
	public List<UsernamePassword> delete(List<UsernamePassword> entitys) throws ClassNotFoundException, SQLException {
		List<UsernamePassword> resUsernamePasswords = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUsernamePasswords.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUsernamePasswords;
	}

	@Override
	public List<UsernamePassword> update(List<UsernamePassword> entitys) throws ClassNotFoundException, SQLException {
		List<UsernamePassword> resUsernamePasswords = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUsernamePasswords.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUsernamePasswords;
	}

	@Override
	public UsernamePassword select(int id) throws ClassNotFoundException, SQLException {
		UsernamePassword resUsernamePassword = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(UsernamePassword.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resUsernamePassword = new UsernamePassword();
			resUsernamePassword.setId(resultSet.getInt("ID"));
			resUsernamePassword.setPassword(resultSet.getString("PASSWORD"));
			resUsernamePassword.setUsername(resultSet.getString("USERNAME"));
		}
		return resUsernamePassword;
	}

}
