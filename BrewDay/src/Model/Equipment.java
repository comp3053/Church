package Model;

public class Equipment {

	private String id;
	private float capacity;
	private String type;
	private boolean isAvaliable;
	private float avaliableCapacity;
	
	public Equipment(float capacity, String type) {
		// TODO Auto-generated constructor stub
		this.id = null;
		this.type = type;
		this.capacity = capacity;
		this.isAvaliable = true;
		this.avaliableCapacity = capacity;
	}
	
	public Equipment(String id, String type,float capacity) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.type = type;
		this.capacity = capacity;
		this.isAvaliable = true;
		this.avaliableCapacity = capacity;
	}
	
	public Equipment(String id, float capacity, String type, boolean isAvaliable, float avaliableCapacity) {
		this.id = id;
		this.capacity = capacity;
		this.type = type;
		this.isAvaliable = isAvaliable;
		this.avaliableCapacity = avaliableCapacity;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public float getCapacity() {
		return this.capacity;
	}
	
	public String getEquipmentName() {
		return this.id + " " + this.type;
	}
	
	public void setAvaliableCapacity(int avaliableCapacity) {
		this.avaliableCapacity = avaliableCapacity;
	}
	
	public float getAvaliableCapacity() {
		return this.avaliableCapacity;
	}
	
	public void setCapacity(float f) {
		this.capacity = f;
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
	
	public boolean updateEquipmentCapacity(String name, Float amount) {
		
		Database db = new Database();
		return db.updateEquipmentCapacity(this, amount);
	}
}
