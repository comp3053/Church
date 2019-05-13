package Model;

public class Ingredient {

	private String ID;
	private String name;
	private String unit;

	Ingredient(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}
	
	public Ingredient(String id, String name, String unit) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.unit = unit;
		this.ID = id;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}