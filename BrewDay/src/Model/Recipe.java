package Model;
import java.util.ArrayList;
public class Recipe {
	private String Recipe_ID;
	private int literOfbeer;
	private String name;
	ArrayList<RecipeIngredient> ingredient = new ArrayList<RecipeIngredient>();
	private String description;
	
	public Recipe(String iD, int literOfbeer, String name, ArrayList<RecipeIngredient> ingredient, String description) {
		
		super();
		Recipe_ID = iD;
		this.literOfbeer = literOfbeer;
		this.name = name;
		this.ingredient = new ArrayList<RecipeIngredient>();
		this.description = description;
		
	}
	
	public String getID() {
		return Recipe_ID;
	}
	
	public void setID(String iD) {
		Recipe_ID = iD;
	}
	
	public int getLiterOfbeer() {
		return literOfbeer;
	}
	
	public void setLiterOfbeer(int literOfbeer) {
		this.literOfbeer = literOfbeer;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<RecipeIngredient> getList() {
		return ingredient;
	}
	
	public void setList(ArrayList<RecipeIngredient> ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean addRecipeToDB() {
		return true;
	}
	// add an ingredient to the recipe
	public boolean addIngredient(String ingredientName, String unit, int value) {
		//go through the database to find whether the ingredient is exist
		//if exist, new a recipe ingredient, add it to the ingredient list, get the storage ingredient ID, and map it with the recipe id. 
		//if not, new an ingredient both as recipe ingredient and storage ingredient with zero stock. repeat previous step
		
		return true;
	}
	
}
