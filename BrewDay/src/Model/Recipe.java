package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import View.Start;
import jdk.nashorn.internal.ir.annotations.Ignore;


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
		if(eList == null || eList.toArray().length == 0) {
			Start.getInstance().warningMsg("No available equipment", "There are no available equipments");
			return null;
		}

		else {
			//there are available equipments
			//get all the recipes
			ArrayList<Recipe> rList = db.getRecipes();
			for(Recipe r : rList) {
				System.out.println("Recipe 128 /RecipeName: " + r.getName());
				
				r.produceMissingIngredient(batchSize, r.getList());
				if(r.isAvaliable)
					recommendList.add(r);
			}
		}
		return recommendList;
	}

	//return a hashmap k:recipe ingredient, v: missing amount
	public HashMap<RecipeIngredient, Float> produceMissingIngredient(int batchSize, ArrayList<RecipeIngredient> ingredients){
		HashMap<RecipeIngredient, Float> missingList = new HashMap<RecipeIngredient, Float>();
		
		//ArrayList<RecipeIngredient> missingList = new ArrayList<RecipeIngredient>();
		Database db = new Database();
		for(RecipeIngredient i : ingredients) {
			float amount = convertMeasure(i, batchSize);
			StorageIngredient si = db.getStorageIngredient(i.getID());
			System.out.println("Recipe 143/ IngredientName: " + i.getName()+ " amount: " + amount + " stock: " 
			+ si.getStockFromDB(si.getName()));
			
			if(si.getStockFromDB(si.getName()) < amount) { //no enough stock
				missingList.put(i, (amount - si.getStockFromDB(si.getName())));
				isAvaliable = false;
			}
		}
		 //rec_Ingre_List.put(this, missingList);
		 return missingList;
		
	}

	//batchSize x % = amount
	public float convertMeasure(RecipeIngredient i, int batchSize) {
		System.out.println("Recipe/159 ingredient value: "+i.getValue());
		System.out.println("Recupe/160 recipe liter of beer: " + this.literOfbeer);
		System.out.println((i.getValue() / (float) this.literOfbeer));
		return (i.getValue() /(float) this.literOfbeer) * batchSize;
	}

}
