package Tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {
	
	public static void initDB() {
		
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
			
			sql = "CREATE TABLE 'recipe' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'recipe_name' TEXT,'recipe_id' TEXT,'date' TEXT, 'literOfBeer' INTEGER, 'description' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'storge_ingredient' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'ingredient_id' TEXT,'stock' INTEGER,'name' TEXT, 'unit' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'brew' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'recipe_id' INTEGER, 'batch_size' INTEGER,'date' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'note' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'title' TEXT,'note_id' INTEGER,'date' TEXT, 'recipeName' TEXT, 'content' TEXT);";
			statement.execute(sql);
			
			sql = "CREATE TABLE 'equipment' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'capacity' INTEGER,'type' TEXT,'isAvailable' BOLB);";
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
