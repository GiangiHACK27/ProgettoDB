package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBConnectionPool  {
	
	private static List<Connection> freeDbConnections;

	/*Private constructor because SonarLint says utility classes (which are collections of static methods)
	* must have a private constructor otherwise Java creates a public one and allows you to instantiate it
	*/
	private DBConnectionPool() {
		
	}
	static {
		freeDbConnections = new LinkedList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//TO DO: add a dedicated logger instead of using System output
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		//Note: removed commented-out lines of code here
		String username = "client";
		String password = "client";

		//TO DO: store the credentials in a safer way
		newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameShop", username, password);

		newConnection.setAutoCommit(true);
		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection =  freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) {
		if(connection != null) freeDbConnections.add(connection);
	}
}