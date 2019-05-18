package Model;

import java.util.ArrayList;

import View.Start;


public class Recipe {
	private String recipe_ID;
	private int literOfbeer;
	private String name;
	private ArrayList<RecipeIngredient> ingredients;
	private String description;
	private boolean isAvaliable;

	public Recipe() {
		//default setting
		recipe_ID = null;
		literOfbeer = -1;
		name = null;
		ingredients = null;
		description = null;
		this.isAvaliable = true;
	}
	public Recipe(String iD, int literOfbeer, String name, ArrayList<RecipeIngredient> ingredient, String description) {

		recipe_ID = iD;
		this.literOfbeer = literOfbeer;
		this.name = name;
		this.ingredients = ingredient;
		this.description = description;
		this.isAvaliable = true;

	}

	public Recipe(String id, int literOfBeer, String name, String description) {
		this.recipe_ID = id;
		this.literOfbeer = literOfBeer;
		this.name = name;
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

	public void updateRecipe(String recipe_Id, int literOfbeer, String name, String description, ArrayList<RecipeIngredient> ingredients) {
		//get a recipe from the db, set all the attributes of the recipe by the user input
		Database db = new Database();

		Recipe r = db.getRecipe(recipe_Id);

		r.setList(ingredients);
		r.setName(name);
		r.setLiterOfbeer(literOfbeer);

		db.updateRecipe(r);
	}

	public static ArrayList<Recipe> recommendRecipe(int batchSize) {
		Database db = new Database();
		ArrayList<Recipe> recommendList = new ArrayList<Recipe>();
		ArrayList<Equipment> eList = db.getAvailableEquipments(batchSize);
		if(eList == null) {
			Start.getInstance().warningMsg("No available equipment", "You have no equipments");
			return null;
		}
		if(eList.toArray().length == 0) {
			//no available equipments
			Start.getInstance().warningMsg("No available equipment", "There are no available equipments");
			return null;
		}
		else {
			//there are available equipments
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
	public float convertMeasure(RecipeIngredient i, int batchSize) {

		return (i.getValue() / this.literOfbeer) * batchSize;
	}

}
