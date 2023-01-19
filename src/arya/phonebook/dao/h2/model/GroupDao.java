package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Group;

public class GroupDao extends EntityDao<Group> {
	public GroupDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(ICommands.CREATE_TABLE_GROUP);
	}

	@Override
	public Group insert(Group entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(ICommands.INSERT_GROUP);
		preparedStatement.setString(1, entity.getName());
		preparedStatement.setString(2, entity.getDescription());
		preparedStatement.executeUpdate();
		
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		
		return entity;
	}

	@Override
	public Group delete(Group entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group update(Group entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group select(Group entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> select() {
		// TODO Auto-generated method stub
		return null;
	}
}
