package arya.phonebook.dao.h2.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.User;

public class UserDao extends EntityDao<User> {
	public UserDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(ICommands.CREATE_TABLE_USER);
	}

	@Override
	public User insert(User entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(ICommands.INSERT_USER);
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
	public User delete(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User select(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> select() {
		// TODO Auto-generated method stub
		return null;
	}
}
