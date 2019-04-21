package Model;

import java.util.Date;

public class Note {

	private String title;
	private String ID;
	private String date;
	private String recipeName;
	private String content;
	
	public Note(String title, String recipeName, String content) {
		this.title = title;
		this.recipeName = recipeName;
		this.content = content;
		
		Date date = new Date();
		this.date = date.toString();
		
		Database nDatabase = new Database();
		nDatabase.addNote(this);
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
	
	
	public static void main (String[] args) {
		for(int i = 0; i <5; i++) {
			Note note = new Note("title", "111", "22222");
			System.out.println(note.ID);
			
		}
		
		Note note1 = new Note("title", "111", "22222");
		Note note2 = new Note("title", "111", "22222");
		System.out.println("note1 : "+note1.getID());
		System.out.println("note2 : "+note2.getID());
	}
}
