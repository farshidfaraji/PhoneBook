package arya.phonebook.business.server.internal.model.abstracts;

import java.sql.SQLException;
import java.util.List;

import arya.phonebook.dao.h2.model.abstracts.EntityDao;
import arya.phonebook.model.abstracts.Entity;

public abstract class EntityService<T extends Entity, E extends EntityDao<T>> {
	protected E dao;

	public T save(T entity) throws ClassNotFoundException, SQLException {
		return dao.insert(entity);
	}

	public T update(T entity) {
		return dao.update(entity);
	}

	public T select(T entity) {
		return dao.select(entity);
	}

	public List<T> select() {
		return dao.select();
	}
}
