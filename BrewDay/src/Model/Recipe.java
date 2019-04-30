package Model;

import java.util.ArrayList;


public class Recipe {
	private String recipe_ID;
	private int literOfbeer;
	private String name;
	ArrayList<RecipeIngredient> ingredients;
	private String description;
	private boolean isAvaliable;
	
	public Recipe(String iD, int literOfbeer, String name, ArrayList<RecipeIngredient> ingredient, String description) {
		
		recipe_ID = iD;
		this.literOfbeer = literOfbeer;
		this.name = name;
		this.ingredients = new ArrayList<RecipeIngredient>();
		this.description = description;
		this.isAvaliable = true;
		
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
	
	public void addRecipeIngredient(RecipeIngredient ri) {
		this.ingredients.add(ri);
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
		
		//Recipe r = db.getRecipe();
		//r.setIngredients();
		//r.setName("");
		//r.setLiterOfBeer(5);
		//db.updateRecipe(r);
	}
	
	public ArrayList<Recipe> recommendRecipe(int batchSize) {
		Database db = new Database();
		ArrayList<Recipe> recommendList = new ArrayList<Recipe>();
		ArrayList<Equipment> eList = db.getAvailableEquipments(batchSize);
		if(eList == null) {
			//no available equipments
		}
		else {
			ArrayList<Recipe> rList = db.getRecipes();
			for(Recipe r : rList) {
				
				if(r.isAvaliable)
					recommendList.add(r);
			}
		}
		return recommendList;
	}
	
	public ArrayList<RecipeIngredient> produceMissingIngredient(int batchSize){
		ArrayList<RecipeIngredient> missingList = new ArrayList<RecipeIngredient>();
		Database db = new Database();
		for(RecipeIngredient i : ingredients) {
			float amount = convertMeasure(i, batchSize);
			StorageIngredient si = db.getStorageIngredient(i.getID());
			if(si.getStockFromDB(si.getName()) < amount) { //no enough stock
				missingList.add(i);
				isAvaliable = false;
			}
		}
		return missingList;
	}
	
	//batchSize x % = amount
	private float convertMeasure(RecipeIngredient i, int batchSize) {
		
		return (i.getValue() / this.literOfbeer) * batchSize;
	}
	
}
