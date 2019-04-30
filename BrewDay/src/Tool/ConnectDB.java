package Tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
		//create the connection method for member to use
		//connect the local dataBase and return a connection object
		public static Connection connectSqlite() {
			
			Connection connection = null;
			
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:system.db");
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Connect database fail, please check the database existing");
				e.printStackTrace();
			}
				
			return connection;
		}
		
		public static void closeConnect(Connection connect) {
			try {
				connect.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

}
