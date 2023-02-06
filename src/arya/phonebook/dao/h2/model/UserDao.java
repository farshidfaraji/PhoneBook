package arya.phonebook.dao.h2.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.User;

public class UserDao extends EntityDao<User> {
	public UserDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(new GenerateCommand<>(User.class).createTable());
	}

	@Override
	public User insert(User entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(User.class).insertTable());
		preparedStatement.setString(1, entity.getFristname());
		preparedStatement.setString(2, entity.getLastname());
		preparedStatement.setBytes(3, entity.getPhoto());
		preparedStatement.setString(4, entity.getEmail());
		preparedStatement.setString(5, entity.getNationalID());
		preparedStatement.setString(6, entity.getAddress());
		if (entity.getBirthday() != null) {
			preparedStatement.setDate(7, new Date(entity.getBirthday().getTimeInMillis()));
			// preparedStatement.setTimestamp(7, new
			// Timestamp(entity.getBirthday().getTimeInMillis()));
		} else {
			preparedStatement.setDate(7, null);
		}
		preparedStatement.setString(8, entity.getPhone());
		preparedStatement.setString(9, entity.getGender().name());
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		return entity;
	}

	@Override
	public User delete(User entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(User.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public User update(User entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(User.class).updateTable());
		preparedStatement.setString(1, entity.getFristname());
		preparedStatement.setString(2, entity.getLastname());
		preparedStatement.setBytes(3, entity.getPhoto());
		preparedStatement.setString(4, entity.getEmail());
		preparedStatement.setString(5, entity.getNationalID());
		preparedStatement.setString(6, entity.getAddress());
		if (entity.getBirthday() != null) {
			preparedStatement.setDate(7, new Date(entity.getBirthday().getTimeInMillis()));
			// preparedStatement.setTimestamp(7, new
			// Timestamp(entity.getBirthday().getTimeInMillis()));
		} else {
			preparedStatement.setDate(7, null);
		}
		preparedStatement.setString(8, entity.getPhone());
		preparedStatement.setString(9, entity.getGender().name());
		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		return entity;
	}

	@Override
	public User select(User entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(User.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User userReult = new User();
			userReult.setAddress(resultSet.getString("ADDRESS"));
			userReult.setBirthday(null);  // FIXME
			userReult.setEmail(resultSet.getString("EMAIL"));
			userReult.setFristname(resultSet.getString("FRISTNAME"));
			userReult.setGender(null);  // FIXME
			userReult.setId(resultSet.getInt("ID"));
			userReult.setLastname(resultSet.getString("LASTNAME"));
			userReult.setNationalID(resultSet.getString("NATIONALID"));
			userReult.setPhone(resultSet.getString("PHONE"));
			userReult.setPhoto(null);  // FIXME
		}
		return entity;
	}
	
	@Override
	public List<User> selects() throws ClassNotFoundException, SQLException {
		List<User> resUsers = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(User.class).selectAll());
		while (resultSet.next()) {
			User userReult = new User();
			userReult.setAddress(resultSet.getString("ADDRESS"));
			userReult.setBirthday(null);  // FIXME
			userReult.setEmail(resultSet.getString("EMAIL"));
			userReult.setFristname(resultSet.getString("FRISTNAME"));
			userReult.setGender(null);  // FIXME
			userReult.setId(resultSet.getInt("ID"));
			userReult.setLastname(resultSet.getString("LASTNAME"));
			userReult.setNationalID(resultSet.getString("NATIONALID"));
			userReult.setPhone(resultSet.getString("PHONE"));
			userReult.setPhoto(null);  // FIXME
			resUsers.add(userReult);
		}
		return resUsers;
	}
	
	@Override
	public List<User> insert(List<User> entitys) throws ClassNotFoundException, SQLException {
		List<User> resUsers = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUsers.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUsers;
	}

	@Override
	public List<User> delete(List<User> entitys) throws ClassNotFoundException, SQLException {
		List<User> resUsers = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUsers.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUsers;
	}

	@Override
	public List<User> update(List<User> entitys) throws ClassNotFoundException, SQLException {
		List<User> resUsers = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resUsers.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resUsers;
	}

	@Override
	public User select(int id) throws ClassNotFoundException, SQLException {
		User resUser = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(User.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resUser = new User();
			resUser.setAddress(resultSet.getString("ADDRESS"));
			resUser.setBirthday(null);  // FIXME
			resUser.setEmail(resultSet.getString("EMAIL"));
			resUser.setFristname(resultSet.getString("FRISTNAME"));
			resUser.setGender(null);  // FIXME
			resUser.setId(resultSet.getInt("ID"));
			resUser.setLastname(resultSet.getString("LASTNAME"));
			resUser.setNationalID(resultSet.getString("NATIONALID"));
			resUser.setPhone(resultSet.getString("PHONE"));
			resUser.setPhoto(null);  // FIXME
		}
		return resUser;
	}

	
}
