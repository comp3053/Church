package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sun.net.www.content.image.png;


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

	public int countRow(ResultSet rSet) {
		int row = 0;
		try {
			rSet.afterLast();
			row = rSet.getRow();
			rSet.beforeFirst();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return row;
	}

	public int addStorageIngredient(String ingredientName, int value, String unit) {

		String sql;
		PreparedStatement pStatement;

		if(!checkStorageIngredientExist(ingredientName)) {
			//result is empty, add a new ingredient
			sql = "INSERT INTO 'storage_ingredient' VALUES (null, ?, ?, ?);";
			try {

				pStatement = this.connection.prepareStatement(sql);

				pStatement.setInt(1, value);
				pStatement.setString(2, ingredientName);
				pStatement.setString(3, unit);

				pStatement.executeUpdate();

				pStatement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				return -1;
			}

		} else {
			//return 0, throw out a warning/
			//warning: the ingredient exists
			return 0;
		}

		


		return 1;
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

	public void addRecipeToDB(Recipe recipe) {
		String sql = "INSERT INTO 'recipe' VALUES(null, ?, ?, ?);";

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setString(1, recipe.getName());
			pStatement.setInt(2, recipe.getLiterOfbeer());
			pStatement.setString(3, recipe.getDescription());

			pStatement.executeUpdate();
			//pStatement.close();

			sql = "SELECT recipe_id FROM 'recipe' WHERE recipe_name = ?;";

			pStatement = this.connection.prepareStatement(sql);
			pStatement.setString(1, recipe.getName());

			ResultSet rSet = pStatement.executeQuery();


			int recipe_id = 0;
			while (rSet.next()) {
				recipe_id = rSet.getInt(1);
			}

			//System.out.println(recipe_id);

			sql = "INSERT INTO 'recipe_recipeIngredient' VALUES (null, ?, ?, ?);";

			ArrayList<RecipeIngredient> recipeIngredientList = new ArrayList<RecipeIngredient>();
			recipeIngredientList = recipe.getList();


			for(RecipeIngredient rIngredient: recipeIngredientList) {
				pStatement = this.connection.prepareStatement(sql);
				pStatement.setString(1, Integer.toString(recipe_id));
				pStatement.setString(2, rIngredient.getID());
				pStatement.setInt(3, rIngredient.getValue());

				pStatement.executeUpdate();

			}

			pStatement.close();


		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Recipe getRecipe(String id){
		//use id to return a recipe
		String sql = "SELECT* FROM recipe WHERE recipe_id = ?;";
		Recipe r = new Recipe();

		ArrayList<Equipment> equipmentsList = new ArrayList<Equipment>();

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);

			pStatement.setInt(1,Integer.parseInt(id));

			ResultSet rs = pStatement.executeQuery(sql);

			while (rs.next()) {
				Equipment tempEquipment = new Equipment(rs.getFloat(1), rs.getString(2));
				equipmentsList.add(tempEquipment);
			}

		} catch(SQLException e){
			// TODO: handle exception
			e.printStackTrace();
		}

		return r;

	}

	public ArrayList<Recipe> getRecipeList(){
		String sql = "SELECT * FROM 'recipe';";
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				Recipe tempRecipe = new Recipe(Integer.toString(rSet.getInt(1)), rSet.getInt(3), rSet.getString(2), rSet.getString(4));
				recipeList.add(tempRecipe);
			}

			pStatement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return recipeList;
	}

	public Note getNote(String id) {
		//use id to return a note
		String sql = "SELECT * FROM 'note' HWERE note_id = ?;";
		Note resutl = null;

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);

			pStatement.setInt(1, Integer.parseInt(id));

			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				Note noteTemp = new Note(Integer.toString(rSet.getInt(1)), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getString(5));
				resutl = noteTemp;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resutl;
	}

	public Equipment getEquipment(String id) {
		//use id to return a equipment
		String sql = "SELECT * FROM 'equipment' WHERE equipment_id = ?;";
		Equipment result = null;

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(id));

			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				Equipment equipmentTemp = new Equipment(Integer.toString(rSet.getInt(1)), rSet.getFloat(2), rSet.getString(4), rSet.getBoolean(5), rSet.getFloat(3));

				result = equipmentTemp;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		return result;
	}

	public StorageIngredient getStorageIngredient(String id) {
		//use id to return a storageIngredient
		String sql = "SELECT * FROM 'storage_ingredient' WHERE ingredient_id = ?;";
		StorageIngredient result = null;

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(id));

			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				StorageIngredient siTemp = new StorageIngredient(Integer.toString(rSet.getInt(1)), rSet.getString(3), rSet.getInt(2), rSet.getString(4));

				result = siTemp;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;

	}

	//***********************************Brew************************************************

	public Brew getBrew(String id) {
		//use id to return a brew
		String sql = "SELECT * FROM 'brew' WHERE brew_id = ?;";
		Brew result = null;

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(id));

			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	//***********************************Recipe************************************************

	public void deleteRecipe(Recipe recipe) {
		String sql = "DELETE FROM 'recipe' WHERE recipe_id = ?;";

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, Integer.parseInt(recipe.getID()));

			pStatement.executeUpdate();

			sql = "DELETE FROM 'recipe_recipeIngredient' WHERE recipe_id = ?;";
			pStatement = this.connection.prepareStatement(sql);
			pStatement.setString(1, recipe.getID());

			pStatement.executeUpdate();

			pStatement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

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

			ResultSet rs = pStatement.executeQuery();
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

	public boolean updateStorgeIngredient(StorageIngredient storageIngredient) {
		String sql = "UPDATE 'storage_ingredient' SET stock = ? WHERE name = ?;";

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			pStatement.setInt(1, storageIngredient.getStock());
			pStatement.setString(2, storageIngredient.getName());

			pStatement.executeUpdate();

			pStatement.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<StorageIngredient> getStorgeIngredientList() {
		String sql = "SELECT * FROM 'storage_ingredient';";

		ArrayList<StorageIngredient> ingredientList = new ArrayList<StorageIngredient>();

		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);
			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				StorageIngredient tempIngredient = new StorageIngredient(Integer.toString(rSet.getInt(1)), rSet.getString(3), rSet.getInt(2), rSet.getString(4));
				ingredientList.add(tempIngredient);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ingredientList;

	}

	public boolean addRecipe_Ingredient(RecipeIngredient recipeIngredient, String recipeID) {

		if(checkStorageIngredientExist(recipeIngredient.getName()))
			//the ingredient exists in the storage ingredient table
			//add a row into recipe_ingredient table
			return addRecipe_Ingredient_Relation(Integer.parseInt(recipeID),
					Integer.parseInt(recipeIngredient.getID()), 
					recipeIngredient.getValue());
		else {
			//new a storage ingredient, with 0 stock
			StorageIngredient si = new StorageIngredient(recipeIngredient.getName(), recipeIngredient.getUnit());
			addStorageIngredient(si.getName(), 0, si.getUnit());
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

		String sql = "SELECT * FROM 'storage_ingredient' WHERE 'name' = ?;";
		try {
			PreparedStatement pStatement = this.connection.prepareStatement(sql);

			pStatement.setString(1, ingredientName);

			ResultSet rs = pStatement.executeQuery();

			//result is empty, the ingredient does not exist
			if(rs.getRow() == 0)
				return false;
			//the ingredient exist
			else return true;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	public int getIngredientStock(String ingredientName) {
		//use the name as key to find the value
		// return the value in the database
		return 0;
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
