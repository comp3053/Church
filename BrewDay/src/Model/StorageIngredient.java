package Model;

public class StorageIngredient extends Ingredient {

	private int stock;
	
	StorageIngredient(String name, String unit) {
		super(name, unit);
		// TODO Auto-generated constructor stub
	}
	
	public boolean addAmount(String name, int amount) {
		//use the name as key to find the value 
		//add the value to the amount in the database
		return true;
	}
	public boolean subtractAmount(String name, int amount) {
		//use the name as key to find the value
		// subtract the value from the amount in the database
		return true;
	}

}
