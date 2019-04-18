package Model;

public class Note {

	private String title;
	static private int ID = 0;
	private String date;
	private String recipeName;
	private String content;
	public Note(String title, String recipeName, String content) {
		this.title = title;
		this.recipeName = recipeName;
		this.content = content;
		this.ID +=1; 
	}
	
	
	public static void main (String[] args) {
		for(int i = 0; i <5; i++) {
			Note note = new Note("title", "111", "22222");
			System.out.println(note.ID);
			
		}
		
	}
}
