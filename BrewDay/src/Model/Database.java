package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
	
	private Connection connection;
	
	//create connect object
	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:system.db");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addEquipment(Equipment equipment) {
		try {
			String sql = "INSERT INTO 'equipment' VALUES(?, ?, ?, ?);";
			
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setString(1, equipment.getID());
			pStatement.setString(2, equipment.getType());
			pStatement.setInt(3, equipment.getCapacity());
			pStatement.setBoolean(4, equipment.getAvaliable());
			
			//execute the sql language
			pStatement.executeUpdate();
			
			pStatement.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addNote(Note note) {
		try {
			String sql = "INSERT INTO 'note' VALUES(NULL, ?, ?, ?, ?, ?);";
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			
			pStatement.setString(1, note.getTitle());
			pStatement.setInt(2, note.getID());
			pStatement.setString(3, note.getDate());
			pStatement.setString(4, note.getRecipeName());
			pStatement.setString(5, note.getContent());
			
			pStatement.executeUpdate();
			
			pStatement.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean checkStorageIngredient() {
		return true;
	}
}
