package arya.phonebook.dao.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import arya.phonebook.dao.IConnection;

public class ConnectionH2 extends IConnection {
	private static ConnectionH2 connectionH2;

	private ConnectionH2(String url, String username, String password) {
		super("org.h2.Driver", url, username, password);
	}

	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}
	
	public static ConnectionH2 getNewInstance(String url, String username, String password) {
		if (connectionH2 == null) {
			connectionH2 = new ConnectionH2(url, username, password);
		}
		return connectionH2;
	}
}
