package Model;

public class RecipeIngredient extends Ingredient {
	
	private int value;
	
	public RecipeIngredient(String name, String unit, int value) {
		// TODO Auto-generated constructor stub
		super(name, unit);
		this.value = value;
	}
	
	public RecipeIngredient(String id, String name, String unit, int value) {
		// TODO Auto-generated constructor stub
		super(id, name, unit);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean updateValue(String name, int value) {
		setValue(value);
		Database db = new Database();
		db.updateRecipeIngredientValue(name, value);
		return true;
	}
	
}
