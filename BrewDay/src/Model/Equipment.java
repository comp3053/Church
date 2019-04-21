package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.ConnectDB;

public class Equipment {

	private String id;
	private int[] capacity;
	private String type;
	private boolean isAvaliable;
	
	
	public boolean updateEquipmentType(String type) {
		
		//connect database
		Connection connection = ConnectDB.connectSqlite();
		
		int status = 0;
		
		try {
			
			//prepare SQL
			String sql = "UPDATE equipment SET type = ? WHERE equipment_id = ?;";
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			
			pStatement.setString(1, type);
			pStatement.setString(2, this.id);
			status = pStatement.executeUpdate();
			
			pStatement.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			
		}
		
		if (status == 0) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	public boolean updateEquipmentCapacity(String name, int amount) {
		return true;
	}
	
}
