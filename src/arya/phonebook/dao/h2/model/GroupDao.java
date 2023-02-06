package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Group;

public class GroupDao extends EntityDao<Group> {
	public GroupDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(new GenerateCommand<>(Group.class).createTable());
	}

	@Override
	public Group insert(Group entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Group.class).createTable());
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
	public Group delete(Group entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Group.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Group update(Group entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Group.class).updateTable());
		preparedStatement.setString(1, entity.getName());
		preparedStatement.setString(2, entity.getDescription());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public Group select(Group entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(Group.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Group groupReult = new Group();
			groupReult.setDescription(resultSet.getString("DESCRIPTION"));
			groupReult.setName(resultSet.getString("NAME"));
			groupReult.setId(resultSet.getInt("ID"));
		}
		return entity;
	}

	@Override
	public List<Group> selects() throws ClassNotFoundException, SQLException {
		List<Group> resGroups = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(Group.class).selectAll());
		while (resultSet.next()) {
			Group groupReult = new Group();
			groupReult.setDescription(resultSet.getString("DESCRIPTION"));
			groupReult.setName(resultSet.getString("NAME"));
			groupReult.setId(resultSet.getInt("ID"));
			resGroups.add(groupReult);
		}
		return resGroups;
	}

	@Override
	public List<Group> insert(List<Group> entitys) throws ClassNotFoundException, SQLException {
		List<Group> resGroups = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resGroups.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resGroups;
	}

	@Override
	public List<Group> delete(List<Group> entitys) throws ClassNotFoundException, SQLException {
		List<Group> resGroups = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resGroups.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resGroups;
	}

	@Override
	public List<Group> update(List<Group> entitys) throws ClassNotFoundException, SQLException {
		List<Group> resGroups = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resGroups.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resGroups;
	}

	@Override
	public Group select(int id) throws ClassNotFoundException, SQLException {
		Group resGroups = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(
				new GenerateCommand<>(Group.class).selectAll());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Group groupReult = new Group();
			groupReult.setDescription(resultSet.getString("DESCRIPTION"));
			groupReult.setName(resultSet.getString("NAME"));
			groupReult.setId(resultSet.getInt("ID"));
		}
		return resGroups;
	}
}
