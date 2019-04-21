package Model;

import java.util.Date;

public class Brew {
	private String ID;
	private String batchSize;
	private String date;
	public Recipe recipe;
	
	Brew(String batchSize, Recipe recipe){
		Date date = new Date();
		this.recipe = recipe;
		this.date = date.toString();
		this.batchSize = batchSize;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getBatchSize() {
		return this.batchSize;
	}
	
	public Recipe getRecipe() {
		return this.recipe;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}
}
