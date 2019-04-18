package Model;
import java.util.ArrayList;
public class Recipe {
	private String ID;
	private int literOfbeer;
	private String name;
	ArrayList<String> ingredient = new ArrayList<String>();
	private String description;
	
	public Recipe(String iD, int literOfbeer, String name, ArrayList<String> ingredient, String description) {
		
		super();
		ID = iD;
		this.literOfbeer = literOfbeer;
		this.name = name;
		this.ingredient = ingredient;
		this.description = description;
		
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
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
	
	public ArrayList<String> getList() {
		return ingredient;
	}
	
	public void setList(ArrayList<String> ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
