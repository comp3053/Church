package Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class testConnectDB {
	
	public static void main(String[] args) {
		Connection connection = ConnectDB.connectSqlite();
		
		Scanner scanner = new Scanner(System.in);
		
		if (scanner.hasNext()) {
			String input_sql = scanner.next();
            try {
            	PreparedStatement pStatement = connection.prepareStatement(input_sql);
                //pStatement.executeUpdate();
                
                ResultSet rSet = pStatement.executeQuery();
                while (rSet.next()) {
					System.out.println(rSet);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }
		
		scanner.close();
	}

}
