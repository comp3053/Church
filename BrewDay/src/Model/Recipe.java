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
		this.ingredient = ingredient;
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
}
