package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.commands.ICommands;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.EmailDetail;

public class EmailDetailDao extends EntityDao<EmailDetail> {

	public EmailDetailDao() throws ClassNotFoundException, SQLException {
		getStatement().execute(ICommands.CREATE_TABLE_EMAILDETAIL);
	}

	@Override
	public EmailDetail insert(EmailDetail entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = getPreparedStatement(ICommands.INSERT_EMAILDETAIL);
		preparedStatement.setString(1, entity.getEmail());
		preparedStatement.setString(2, entity.getDescription());
		preparedStatement.executeUpdate();
		return null;
	}

	@Override
	public EmailDetail delete(EmailDetail entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailDetail update(EmailDetail entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailDetail select(EmailDetail entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailDetail> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
