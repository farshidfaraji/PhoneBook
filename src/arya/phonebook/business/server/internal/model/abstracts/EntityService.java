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

	public T update(T entity) throws ClassNotFoundException, SQLException {
		return dao.update(entity);
	}

	public T select(T entity) throws ClassNotFoundException, SQLException {
		return dao.select(entity);
	}

	public abstract E insert(E entity) throws ClassNotFoundException, SQLException;

	public abstract List<E> insert(List<E> entitys) throws ClassNotFoundException, SQLException;

	public abstract E delete(E entity) throws ClassNotFoundException, SQLException;

	public abstract List<E> delete(List<E> entitys) throws ClassNotFoundException, SQLException;

	public abstract E update(E entity) throws ClassNotFoundException, SQLException;

	public abstract List<E> update(List<E> entitys) throws ClassNotFoundException, SQLException;

	public abstract E select(E entity) throws ClassNotFoundException, SQLException;

	public abstract E select(int id) throws ClassNotFoundException, SQLException;

	public abstract List<E> selects() throws ClassNotFoundException, SQLException;
}
