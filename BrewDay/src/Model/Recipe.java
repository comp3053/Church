package Model;

import java.util.ArrayList;


public class Recipe {
	private String recipe_ID;
	private int literOfbeer;
	private String name;
	ArrayList<RecipeIngredient> ingredients;
	private String description;
	
	public Recipe(String iD, int literOfbeer, String name, ArrayList<RecipeIngredient> ingredient, String description) {
		
		recipe_ID = iD;
		this.literOfbeer = literOfbeer;
		this.name = name;
		this.ingredients = new ArrayList<RecipeIngredient>();
		this.description = description;
		
	}
	
	public String getID() {
		return recipe_ID;
	}
	
	public void setID(String iD) {
		recipe_ID = iD;
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
		return ingredients;
	}
	
	public void setList(ArrayList<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// add an ingredient to the recipe
	public void addRecipeIngredientRelation() {
		Database db = new Database();
		//For each element in the list
		for(RecipeIngredient n : ingredients)
			db.addRecipe_Ingredient(n, this.recipe_ID);
	}
	
	public void updateRecipe() {
		//get a recipe from the db, set all the attributes of the recipe by the user input
		//wrap the recipe to database class
		Database db = new Database();
		
		//Recipe r = db======;
		//r.setIngredients();
		//r.setName("");
		//r.setLiterOfBeer(5);
		
		//db.updateRecipe(r);
	}
	
	
}
