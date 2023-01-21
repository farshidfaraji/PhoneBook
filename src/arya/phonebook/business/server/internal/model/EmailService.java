package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.EmailDetailDao;
import arya.phonebook.model.EmailDetail;

public class EmailService extends EntityService<EmailDetail, EmailDetailDao> {

	public EmailService() throws ClassNotFoundException, SQLException {
		dao = new EmailDetailDao();
	}

}
