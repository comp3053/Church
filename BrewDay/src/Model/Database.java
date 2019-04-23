package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			String sql = "INSERT INTO 'equipment' VALUES(?, ?, ?, ?, ?);";
			
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(equipment.getID()));
			pStatement.setFloat(2, equipment.getCapacity());
			pStatement.setFloat(3, equipment.getAvaliableCapacity());
			pStatement.setString(4, equipment.getType());
			pStatement.setBoolean(5, equipment.getAvaliable());
			
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
			pStatement.setInt(2, Integer.parseInt(note.getID()));
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

	public void addRecipe(Recipe recipe) {
		
	}
	
	public Recipe getRecipe(){
		return null;
	}
	
	public Brew getBrew() {
		return null;
	}
	
	public Note getNote() {
		return null;
	}
	
	public Equipment getEquipment() {
		return null;
	}
	

	public StorageIngredient getStorageIngredient(String id) {
		return null;
	}
	//***********************************Brew************************************************
	
	//***********************************Recipe************************************************
	public void updateRecipe(Recipe recipe) {
		//check the content in the recipe if the input is null, skip, otherwise modify the content in the database
		
		
	}
	public ArrayList<Recipe> getRecipes(){
		String sql = "SELECT * FROM recipe";
		ArrayList<Recipe> rList = new ArrayList<Recipe>();
		
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);			
			ResultSet rs = pStatement.executeQuery(sql);
			while (rs.next()) {
				sql = "SELECT * FROM recipe_recipeIngredient join storage_ingredient";
				Statement statement = this.connection.createStatement();
				ResultSet rs_1 = statement.executeQuery(sql);
				ArrayList<RecipeIngredient> rIngredients = new ArrayList<RecipeIngredient>();
				while (rs_1.next()) {
					RecipeIngredient tempIngredient = new RecipeIngredient(rs_1.getString(7), 
																		   rs_1.getString(8), 
																		   rs_1.getInt(4));
					rIngredients.add(tempIngredient);
				}
				Recipe tempRecipe = new Recipe(Integer.toString(rs.getInt(1)), rs.getInt(3), rs.getString(2),rIngredients,rs.getString(4));
				rList.add(tempRecipe);
			}
			
		} catch(SQLException e){
			// TODO: handle exception
			e.printStackTrace();
		}
		return rList;

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

	public ArrayList<Equipment> getAvailableEquipments(int batchSize){
		String sql = "SELECT * FROM equipment WHERE avaliableCapacity >= ?";
		
		ArrayList<Equipment> equipmentsList = new ArrayList<Equipment>();
		
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			
			pStatement.setInt(1,batchSize);
			
			ResultSet rs = pStatement.executeQuery(sql);
			while (rs.next()) {
				Equipment tempEquipment = new Equipment(rs.getFloat(1), rs.getString(2));
				equipmentsList.add(tempEquipment);
			}
			
		} catch(SQLException e){
			// TODO: handle exception
			e.printStackTrace();
		}
		return equipmentsList;
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
	
	public void modifyNote(Note note) {
		int id = Integer.parseInt(note.getID());
		//use the id to find the note and modify the content;
	}
}
