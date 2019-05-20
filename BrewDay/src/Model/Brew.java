package Model;

import java.util.Date;

public class Brew {
	private String ID;
	private int batchSize;
	private String date;
	private Recipe recipe;
	private Note note;
	private String recipeName;
	
	public Brew(int batchSize, Recipe recipe){
		Date date = new Date();
		this.recipe = recipe;
		this.date = date.toString();
		this.batchSize = batchSize;
		this.note = null;
	}
	
	public Brew(String id, int batchSize, Recipe recipe, Note note, String date, String recipeName) {
		this.ID = id;
		this.batchSize = batchSize;
		this.recipe = recipe;
		this.note = note;
		this.date = date;
		this.recipeName = recipeName;
	}
	
	public Brew(int batchSize, Recipe recipe,Note note){
		Date date = new Date();
		this.recipe = recipe;
		this.date = date.toString();
		this.batchSize = batchSize;
		this.note = note;
	}
	public Brew(String id, int batchSize, Recipe recipe, Note note, String date) {
		this.ID = id;
		this.batchSize = batchSize;
		this.recipe = recipe;
		this.note = note;
		this.date = date;
	}
	public String getID() {
		return this.ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public int getBatchSize() {
		return this.batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public Recipe getRecipe() {
		return this.recipe;
	}
	
	public String getRecipeName() {
		return this.recipeName;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public String getDate() {
		return this.date;
	}
	public Note getNote() {
		return this.note;
	}
	
	public void setNote(Note note) {
		this.note = note;
	}
	
	public void addNote(String title, String recipeName, String content) {
		this.note = new Note(title, recipeName, content);
		Database db = new Database();
		db.addNote(this.note);
	}
}
