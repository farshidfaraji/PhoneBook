package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.Contact;

public class ContactDao extends EntityDao<Contact>{
	public ContactDao() throws ClassNotFoundException, SQLException {
		getStatement().execute(ICommands.CREATE_TABLE_CONTACT);
	}

	@Override
	public Contact insert(Contact entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement =  getPreparedStatement(ICommands.INSERT_CONTACT);
		preparedStatement.setString(1, entity.getType());
		preparedStatement.setString(2, entity.getPhone());
		preparedStatement.setString(3, entity.getAddress());
		preparedStatement.setString(4, entity.getDescription());
		preparedStatement.executeUpdate();
		
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		
		return entity;
	}

	@Override
	public Contact delete(Contact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact update(Contact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact select(Contact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> select() {
		// TODO Auto-generated method stub
		return null;
	}
}
