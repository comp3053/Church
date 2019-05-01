package Model;

import java.util.Date;

public class Note {
	private static int increase = 0;
	private String title;
	private String ID;
	private String date;
	private String recipeName; 
	private String content;
	
	public Note(String title, String recipeName, String content) {
		increase++;
		
		this.title = title;		
		this.recipeName = recipeName;
		this.content = content;
		this.ID = Integer.toString(increase);
		
		Date date = new Date();
		this.date = date.toString();
	}
	
	public Note(String id, String title, String date, String recipeName, String content) {
		this.ID = id;
		this.title = title;
		this.date = date;
		this.recipeName = recipeName;
		this.content = content;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date.toString();
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return this.content;
	}
		
	public String getRecipeName() {
		return this.recipeName;
	}
	
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void modifyNote(String content) {
		setContent(content);
		//sent the note to db
		Database db = new Database();
		db.modifyNote(this);
	}
	
}
