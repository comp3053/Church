package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Tool.ConnectDB;

public class Database {
	
	private Connection connection;
	
	//create connect object
	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:system.db");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean addStorageIngredient(StorageIngredient storageIngredient) {

		return true;
	}

	public boolean addBrew(Brew brew) {
		//add brew.recipe.getID() as recipe id; 
		
		return true;
	}

	public void addEquipment(Equipment equipment) {
		try {
			String sql = "INSERT INTO 'equipment' VALUES(?, ?, ?, ?);";
			
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setString(1, equipment.getID());
			pStatement.setString(2, equipment.getType());
			pStatement.setInt(3, equipment.getCapacity());
			pStatement.setBoolean(4, equipment.getAvaliable());
			
			//execute the sql language
			pStatement.executeUpdate();
			
			pStatement.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addNote(Note note) {
		try {
			String sql = "INSERT INTO 'note' VALUES(NULL, ?, ?, ?, ?, ?);";
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			
			pStatement.setString(1, note.getTitle());
			pStatement.setInt(2, note.getID());
			pStatement.setString(3, note.getDate());
			pStatement.setString(4, note.getRecipeName());
			pStatement.setString(5, note.getContent());
			
			pStatement.executeUpdate();
			
			pStatement.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//***********************************Brew************************************************
	
	//***********************************Recipe************************************************
	public void updateRecipe(Recipe recipe) {
		//check the content in the recipe if the input is null, skip, otherwise modify the content in the database
		
	}
	//***********************************Equipment************************************************
	public boolean updateEquipmentType(Equipment equip, String type) {
		int status = 0;
		
		//prepare SQL
		String sql = "UPDATE equipment SET type = ? WHERE equipment_id = ?;";
		
		try {
			
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			
			pStatement.setString(1, type);
			pStatement.setString(2, equip.getID());
			
			status = pStatement.executeUpdate();
			
			pStatement.close();
			
			this.connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//return status
		if (status == 0) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	public boolean updateEquipmentCapacity(Equipment equip, String name, int amount) {
		int status = 0;
		
		//prepare SQL
		String sql = "UPDATE equipment SET capacity = ? WHERE equipment_id = ?;";
		
		try {
			
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			
			pStatement.setInt(1, amount);
			pStatement.setInt(2, Integer.parseInt(equip.getID()));
			
			status = pStatement.executeUpdate();
			pStatement.close();
			
			this.connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		if (status == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	//***********************************Ingredient************************************************
	public boolean addRecipe_Ingredient(RecipeIngredient recipeIngredient, String recipeID) {
		
		if(checkStorageIngredientExist(recipeIngredient.getName()))
			//add a row into recipe_ingredient table
			return addRecipe_Ingredient_Relation(Integer.parseInt(recipeID),
										         Integer.parseInt(recipeIngredient.getID()), 
										         recipeIngredient.getValue());
		else {
			StorageIngredient si = new StorageIngredient(recipeIngredient.getName(), recipeIngredient.getUnit());
			addStorageIngredient(si);
		}
		return addRecipe_Ingredient_Relation(Integer.parseInt(recipeID),
				                             Integer.parseInt(recipeIngredient.getID()), 
				                             recipeIngredient.getValue());
	}
	
	public boolean addRecipe_Ingredient_Relation(int recipeID, int recipeIngredientID, int value) {
		//database table modify
		return true;
	}
	
	public boolean checkStorageIngredientExist(String ingredientName) {
		//check whether the ingredient exist
		return true;
	}
	
	public int getIngredientStock(String ingredientName) {
		//use the name as key to find the value
		// return the value in the database
		return 0;
	}
	
	public boolean addStock(String name, int amount) {
		//use the name as key to find the value 
		//add the value to the amount in the database
		return true;
	}
	
	public boolean subtractStock(String name, int amount) {
		//use the name as key to find the value
		// subtract the value from the amount in the database
		return true;
	}
	
	public boolean updateRecipeIngredientValue(String ingredientName, int value) {
		return true;
	}
	
	//***********************************Note************************************************
	
	public String getNoteContent(String title) {
		//use the title to search in the database and return the content;
		return null;
	}
}
