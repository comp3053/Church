package Model;

public class StorageIngredient extends Ingredient {

	private int stock;
	
	StorageIngredient(String name, String unit) {
		super(name, unit);
		// TODO Auto-generated constructor stub
	}
	
	public int getStock(String ingredientName) {
		Database db = new Database();
		return db.getIngredientStock(ingredientName);
	}
	
	public boolean addStock(String name, int amount) {
		Database db = new Database();
		return db.addStock(name, amount);

	}
	public boolean subtractStock(String name, int amount) {
		Database db = new Database();
		return db.subtractStock(name, amount);
	}

}
