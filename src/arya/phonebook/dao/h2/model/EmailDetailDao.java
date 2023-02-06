package arya.phonebook.dao.h2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arya.phonebook.dao.h2.commands.GenerateCommand;
import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.EmailDetail;
import arya.phonebook.model.UserContact;

public class EmailDetailDao extends EntityDao<EmailDetail> {
	
	private UserContactDao userContactDao;
	
	public EmailDetailDao() throws ClassNotFoundException, SQLException {
		super.getStatement().execute(new GenerateCommand<>(EmailDetail.class).createTable());
	}

	@Override
	public EmailDetail insert(EmailDetail entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(EmailDetail.class).createTable());
		preparedStatement.setString(1, entity.getEmail());
		preparedStatement.setString(2, entity.getDescription());
		if (entity.getUserContact().getId() == 0) {
			preparedStatement.setInt(3, userContactDao.insert(entity.getUserContact()).getId());
		}else {
			preparedStatement.setInt(3, entity.getUserContact().getId());
		}
		preparedStatement.executeUpdate();
		
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		while (resultSet.next()) {
			entity.setId(resultSet.getInt(1));
		}
		
		return entity;
	}

	@Override
	public EmailDetail delete(EmailDetail entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(EmailDetail.class).deleteTable());
		preparedStatement.setInt(1, entity.getId());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public EmailDetail update(EmailDetail entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(EmailDetail.class).updateTable());
		preparedStatement.setString(1, entity.getEmail());
		preparedStatement.setString(2, entity.getDescription());
		preparedStatement.executeUpdate();
		return entity;
	}

	@Override
	public EmailDetail select(EmailDetail entity) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(EmailDetail.class).select());
		preparedStatement.setInt(1, entity.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			EmailDetail emailDetailReult = new EmailDetail();
			emailDetailReult.setDescription(resultSet.getString("DESCRIPTION"));
			emailDetailReult.setEmail(resultSet.getString("EMAIL"));
			emailDetailReult.setId(resultSet.getInt("ID"));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			emailDetailReult.setUserContact(new UserContactDao().select(userContact));
		}
		return entity;
	}

	@Override
	public List<EmailDetail> selects() throws ClassNotFoundException, SQLException {
		List<EmailDetail> resEmailDetails = new ArrayList<>();
		ResultSet resultSet = super.getStatement().executeQuery(new GenerateCommand<>(EmailDetail.class).selectAll());
		while (resultSet.next()) {
			EmailDetail emailDetailReult = new EmailDetail();
			emailDetailReult.setDescription(resultSet.getString("DESCRIPTION"));
			emailDetailReult.setEmail(resultSet.getString("EMAIL"));
			emailDetailReult.setId(resultSet.getInt("ID"));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			emailDetailReult.setUserContact(new UserContactDao().select(userContact));
			resEmailDetails.add(emailDetailReult);
		}
		return resEmailDetails;
	}

	@Override
	public List<EmailDetail> insert(List<EmailDetail> entitys) throws ClassNotFoundException, SQLException {
		List<EmailDetail> resEmailDetails = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resEmailDetails.add(insert(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resEmailDetails;
	}

	@Override
	public List<EmailDetail> delete(List<EmailDetail> entitys) throws ClassNotFoundException, SQLException {
		List<EmailDetail> resEmailDetails = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resEmailDetails.add(delete(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resEmailDetails;
	}

	@Override
	public List<EmailDetail> update(List<EmailDetail> entitys) throws ClassNotFoundException, SQLException {
		List<EmailDetail> resEmailDetails = new ArrayList<>();
		entitys.forEach(entity -> {
			try {
				resEmailDetails.add(update(entity));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		});
		return resEmailDetails;
	}

	@Override
	public EmailDetail select(int id) throws ClassNotFoundException, SQLException {
		EmailDetail resEmailDetail = null;
		PreparedStatement preparedStatement = super.getPreparedStatement(new GenerateCommand<>(EmailDetail.class).select());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			resEmailDetail = new EmailDetail();
			resEmailDetail.setDescription(resultSet.getString("DESCRIPTION"));
			resEmailDetail.setEmail(resultSet.getString("EMAIL"));
			resEmailDetail.setId(resultSet.getInt("ID"));
			UserContact userContact = new UserContact();
			userContact.setId(resultSet.getInt("ID_USERCONTACT"));
			resEmailDetail.setUserContact(new UserContactDao().select(userContact));
		}
		return resEmailDetail;
	}

}
