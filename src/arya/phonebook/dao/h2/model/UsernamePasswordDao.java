package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.UsernamePassword;

public class UsernamePasswordDao extends EntityDao<UsernamePassword> {
	public UsernamePasswordDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(ICommands.CREATE_TABLE_USERNAMEPASSWORD);
	}

	@Override
	public UsernamePassword insert(UsernamePassword entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(ICommands.INSERT_USERNAMEPASSWORD);
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
	public UsernamePassword delete(UsernamePassword entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsernamePassword update(UsernamePassword entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsernamePassword select(UsernamePassword entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsernamePassword> select() {
		// TODO Auto-generated method stub
		return null;
	}
}
