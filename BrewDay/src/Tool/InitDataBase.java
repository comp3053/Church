package Tool;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {
	
	public static void initDB() {
		File dbFile = new File("system.db");
		
		System.out.println("Checking database file...");
		
		if (dbFile.exists() == false) {
			System.out.println("Database file not found, creating...");
			createDB();
		}
		
		else {
			System.out.println("Database file exsits, starting system");
		}
	}
	
	public static void createDB() {
		
		Connection connection = null;
		
		//call the reference library
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//create db
		try {	
			connection = DriverManager.getConnection("jdbc:sqlite:system.db");
			Statement statement = connection.createStatement();
			
			System.out.println("Create database file successful");
			
			String sql = null;
			
			sql = "CREATE TABLE 'recipe_recipeIngredient' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'recipe_id' TEXT,'ingredient_id' TEXT,'value' INTEGER);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'recipe' ('recipe_id' INTEGER PRIMARY KEY AUTOINCREMENT,'recipe_name' TEXT, 'literOfBeer' INTEGER, 'description' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'storage_ingredient' ('ingredient_id' INTEGER PRIMARY KEY AUTOINCREMENT,'stock' INTEGER,'name' TEXT, 'unit' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'brew' ('brew_id' INTEGER PRIMARY KEY AUTOINCREMENT,'recipe_id' INTEGER, 'batch_size' INTEGER,'date' TEXT, 'note_id' INTEGER, 'recipeName' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'note' ('note_id' INTEGER PRIMARY KEY AUTOINCREMENT,'title' TEXT,'date' TEXT, 'recipeName' TEXT, 'content' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'equipment' ('equipment_id' INTEGER PRIMARY KEY AUTOINCREMENT,'capacity' REAL ,'avaliableCapacity' REAL ,'type' TEXT,'isAvailable' BOLB);";
			statement.execute(sql);
			
			System.out.println("Create table successful");
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		InitDataBase.initDB();
	}

}
