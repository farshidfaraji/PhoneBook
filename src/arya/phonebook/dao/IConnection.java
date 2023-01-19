package arya.phonebook.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class IConnection {
	protected String driver;
	protected String url;
	protected String username;
	protected String password;

	public IConnection(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public abstract Connection connect() throws ClassNotFoundException, SQLException;
	
}
