package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
		
		String sql = "INSERT INTO 'storage_ingredient' VALUES(?, ?, ?, ?);";
		
		int status = 0;
		
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			
			pStatement.setInt(1, Integer.parseInt(storageIngredient.getID()));
			pStatement.setInt(2, storageIngredient.getStock());
			pStatement.setString(3, storageIngredient.getName());
			pStatement.setString(4, storageIngredient.getUnit());
			
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
		String sql = "INSERT INTO 'recipe' VALUES(?, ?, ?, ?);";
		
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(recipe.getID()));
			pStatement.setString(2, recipe.getName());
			pStatement.setInt(3, recipe.getLiterOfbeer());
			pStatement.setString(4, recipe.getDescription());
			
			pStatement.executeUpdate();
			
			pStatement.close();
			this.connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Recipe getRecipe(String id){
		//use id to return a recipe
		
		
		return null;
	}
	
	public Brew getBrew(String id) {
		//use id to return a brew
		return null;
	}
	
	public Note getNote(String id) {
		//use id to return a note
		return null;
	}
	
	public Equipment getEquipment(String id) {
		//use id to return a equipment
		String sql = "SELECT * FROM 'equipment' WHERE equipment_id = ?;";
		
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(id));
			
			ResultSet rSet = pStatement.executeQuery();
			
			while (rSet.next()) {
				Equipment equipmentTemp = new Equipment(capacity, type);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public StorageIngredient getStorageIngredient(String id) {
		//use id to return a storageIngredient
		return null;
	}
	//***********************************Brew************************************************
	
	//***********************************Recipe************************************************
	public void updateRecipe(Recipe recipe) {
		//check the content in the recipe if the input is null, skip, otherwise modify the content in the database
		if (recipe != null) {
			int rId = Integer.parseInt(recipe.getID());
			
			String sql = "UPDATE 'recipe' SET recipe_name = ?, literOfBeer = ?, description = ? WHERE recipe_id = ?;";
			
			try {
				PreparedStatement pStatement = this.connection.prepareStatement(sql);
				
				pStatement.setString(1, recipe.getName());
				pStatement.setInt(2, recipe.getLiterOfbeer());
				pStatement.setString(3, recipe.getDescription());
				pStatement.setInt(4, rId);
				
				pStatement.executeUpdate();
				pStatement.close();
				this.connection.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	public ArrayList<Recipe> getRecipes(){
		String sql = "SELECT * FROM recipe JOIN recipe_recipeIngredient JOIN storage_ingredient WHERE recipe_recipeIngredient.ingredient_id = storage_ingredient.ingredient_id AND recipe.recipe_id = recipe_recipeIngredient.recipe_id";
		ArrayList<Recipe> rList = new ArrayList<Recipe>();
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			int last_id = 0;
			while (rSet.next()) {
				if(last_id != rSet.getInt(1)) {
					ArrayList<RecipeIngredient> riList = new ArrayList<RecipeIngredient>();
					RecipeIngredient rTemp = new RecipeIngredient(rSet.getString(11), rSet.getString(12), rSet.getInt(8));
					riList.add(rTemp);
					Recipe recipeTemp = new Recipe(Integer.toString(rSet.getInt(1)), rSet.getInt(3), rSet.getString(2), riList, rSet.getString(4));
					rList.add(recipeTemp);
					last_id = rSet.getInt(1);
				}
				else {
					RecipeIngredient rTemp = new RecipeIngredient(rSet.getString(11), rSet.getString(12), rSet.getInt(8));
					for(Recipe rL :rList) {
						if (Integer.parseInt(rL.getID()) == rSet.getInt(1)) {
							rL.addRecipeIngredient(rTemp);
						}
					}
				}
			}
		}
		catch(SQLException e){
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
		String sql = "INSERT INTO 'recipe_recipeIngredient' VALUE(?, ?, ?);";
		int status = 0;
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setString(1, Integer.toString(recipeID));
			pStatement.setString(2, Integer.toString(recipeIngredientID));
			pStatement.setInt(3, value);
			
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
