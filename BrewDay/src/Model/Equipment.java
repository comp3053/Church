package Model;

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
	
	public void setID(String id) {
		this.id = id;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean getAvaliable() {
		return this.isAvaliable;
	}
	
	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
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
