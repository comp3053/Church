package Model;

public class Ingredient {

	private String ID;
	private int value;
	private String name;
	private String unit;

	Ingredient(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

	public static void main (String[] args) {
		Ingredient ingre1 = new Ingredient("pijiuhua", "g"); 
		ingre1.setValue(20);
		System.out.println(ingre1.getValue());
		System.out.println(ingre1.getName());
	}

}