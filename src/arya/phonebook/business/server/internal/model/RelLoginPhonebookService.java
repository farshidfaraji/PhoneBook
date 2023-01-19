package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.RelLoginPhonebookDao;

public class RelLoginPhonebookService extends EntityService{
	private RelLoginPhonebookDao relLoginPhonebookDao;
	public RelLoginPhonebookService() throws ClassNotFoundException, SQLException {
		relLoginPhonebookDao = new RelLoginPhonebookDao();
	}
}
