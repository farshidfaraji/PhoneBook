package arya.phonebook.business.server.internal.model;

import java.sql.SQLException;

import arya.phonebook.business.server.internal.model.abstracts.EntityService;
import arya.phonebook.dao.h2.model.RelLoginPhonebookDao;
import arya.phonebook.model.RelLoginPhonebook;

public class RelLoginPhonebookService extends EntityService<RelLoginPhonebook, RelLoginPhonebookDao> {

	public RelLoginPhonebookService() throws ClassNotFoundException, SQLException {
		dao = new RelLoginPhonebookDao();
	}
}
