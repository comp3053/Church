package Model;

public class RecipeIngredient extends Ingredient {
	private int value;
	public RecipeIngredient(String name, String unit) {
		// TODO Auto-generated constructor stub
		super(name, unit);
	}
	
	public boolean updateAmount(String name, int amount) {
		return true;
	}
	
}
