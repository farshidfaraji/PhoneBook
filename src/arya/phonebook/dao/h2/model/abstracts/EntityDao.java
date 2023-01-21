package arya.phonebook.dao.h2.model.abstracts;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import arya.phonebook.dao.h2.ConnectionH2;
import arya.phonebook.model.abstracts.Entity;

public abstract class EntityDao<E extends Entity> {
	private String url = "jdbc:h2:~/.db/phonebook/phonebook";
	private String username = "sa";
	private String password = "sa";
	private ConnectionH2 connectionH2;

	private ConnectionH2 getConnectionH2() {
		if (connectionH2 == null) {
			connectionH2 = ConnectionH2.getNewInstance(url, username, password);
		}
		return connectionH2;
	}

	public Statement getStatement() throws ClassNotFoundException, SQLException {
		return getConnectionH2().connect().createStatement();
	}

	public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
		return getConnectionH2().connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}
	
	public abstract E insert(E entity) throws ClassNotFoundException, SQLException;
	public abstract E delete(E entity);
	public abstract E update(E entity);
	public abstract E select(E entity);
	public abstract List<E> select();
	
}
