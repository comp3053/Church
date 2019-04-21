package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Tool.ConnectDB;

public class Equipment {

	private String id;
	private int[] capacity;
	private String type;
	private boolean isAvaliable;
	
	public Equipment(int capacity, String type) {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean updateEquipmentType(String type) {
		
		//connect database
		Connection connection = ConnectDB.connectSqlite();
		
		int status = 0;
		
		//prepare SQL
		String sql = "UPDATE equipment SET type = ? WHERE equipment_id = ?;";
		
		try {
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			
			pStatement.setString(1, type);
			pStatement.setString(2, this.id);
			status = pStatement.executeUpdate();
			
			pStatement.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//return status
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
