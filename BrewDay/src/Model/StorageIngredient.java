package Model;

public class StorageIngredient extends Ingredient {

	private int stock;
	
	StorageIngredient(String name, String unit) {
		super(name, unit);
		// TODO Auto-generated constructor stub
	}
	
	public StorageIngredient(String name, int stock, String unit) {
		super(name, unit);
		this.stock = stock;
	}
	
	public StorageIngredient(String id, String name, int stock, String unit) {
		super(id, name, unit);
		this.stock = stock;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStockFromDB(String ingredientName) {
		Database db = new Database();
		return db.getIngredientStock(ingredientName);
	}

}
