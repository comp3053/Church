package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Tool.ConnectDB;

public class Equipment {

	private String id;
	private int capacity;
	private String type;
	private boolean isAvaliable;
	
	public Equipment(int capacity, String type) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.capacity = capacity;
		this.isAvaliable = true;
	}
	
	public String getID() {
		return this.id;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public String getType() {
		return this.type;
	}
	
	public boolean getAvaliable() {
		return this.isAvaliable;
	}
	
	
	
	public boolean updateEquipmentType(String type) {
	
		Database db = new Database();
		return db.updateEquipmentType(this, type);
	}
	
	public boolean updateEquipmentCapacity(String name, int amount) {
		
		Database db = new Database();
		return db.updateEquipmentCapacity(this, name, amount);
	}
	
}
