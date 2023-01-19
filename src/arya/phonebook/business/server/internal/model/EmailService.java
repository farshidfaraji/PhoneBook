package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.EmailDetailDao;

public class EmailService extends EntityService {
	private EmailDetailDao emailDetailDao;
	public EmailService() throws ClassNotFoundException, SQLException {
		emailDetailDao = new EmailDetailDao();
	}

	

}
