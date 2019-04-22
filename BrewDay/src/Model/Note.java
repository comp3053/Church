package Model;

import java.util.Date;

public class Note {
	private static int increse = 0;
	private String title;
	private String ID;
	private String date;
	private String recipeName;
	private String content;
	
	public Note(String title, String recipeName, String content) {
		increse++;
		this.title = title;
		this.recipeName = recipeName;
		this.content = content;
		this.ID = Integer.toString(increse);
		
		Date date = new Date();
		this.date = date.toString();
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getRecipeName() {
		return this.recipeName;
	}
	
	public int getID() {
		return 0;
	}
	
	public String getTitle() {
		return this.title;
	}
		
	public String displayNote(String title) {
		Database db = new Database();
		db.getNoteContent(title);
		return null;
	}
}
