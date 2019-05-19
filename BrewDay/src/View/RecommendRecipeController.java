package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Model.Database;
import Model.Recipe;
import Model.RecipeIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RecommendRecipeController implements Initializable{

	//recipe list is the list content for recommended recipe list rendering
	ObservableList<String> recipeList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> recipeListView = new ListView<String>(recipeList);
	
	//missing list is the list content for recipes with missing ingredients
	ObservableList<String> missingList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> missingListView = new ListView<String>(missingList);

	@FXML
	private TextField inputBatchSizeBlank;

	@FXML
	public void backToMain(ActionEvent event) {
		Start.getInstance().mainPage();
	}

	//return the string that display in the list view
	public static String getRecipeText(Recipe recipe, int batchSize) {
		String text = "Recipe: " + recipe.getName() + " || " + "Ingredients: ";
		int count = 1;
		for(RecipeIngredient rg:recipe.getList()) {
			float ingredientValue = recipe.convertMeasure(rg, batchSize);
			text = text + " Ingredient_" + count + " " + rg.getName() + " -- " + ingredientValue + " " + rg.getUnit() + " ||";
			count++;
		}
		return text;
	}
	
	@FXML
	public void recommendRecipe() {
		recipeList.clear();
		int batchSize = 0;
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

		try {
			//get the batch size from the user input
			batchSize = Integer.parseInt(inputBatchSizeBlank.getText());
			//pass the batch for recipe recommendation, get the recipe list
			
			recipeArrayList = Recipe.recommendRecipe(batchSize);
//			System.out.println("Recommend: Im here line 53 ");
			
			if(recipeArrayList == null || recipeArrayList.size() == 0) {
				String str = "No recommended recipes";
				recipeList.add(str);
//				System.out.println("Recommend: Im here line 57");
				recipeListView.setItems(recipeList);
				return;
			}

			for (Recipe tmp: recipeArrayList) {
//				System.out.println("Recommend: Im here line 63");
				//get the content to render the table
				String str = getRecipeText(tmp, batchSize);
				recipeList.add(str);
			}
//			System.out.println("Recommend: Im here line 68");
			recipeListView.setItems(recipeList);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			// TODO: handle exception
			Start.getInstance().warningMsg("Input Error", "Please input a number");			
			Start.getInstance().recommendRecipe();
			return;
		}
		finally{
			producingMissingIngredients(batchSize);
		}
	}

	public void producingMissingIngredients(int batchSize) {
		missingList.clear();
		HashMap<RecipeIngredient, Integer> missingReciepList = new HashMap<RecipeIngredient, Integer>();
		
		Database db = new Database();
		ArrayList<Recipe> rList = db.getRecipes();
		
		for(Recipe r : rList) {
			missingReciepList = r.produceMissingIngredient(batchSize, r.getList());
			String str = "recipe: " + r.getName() + " || ingredients: ";
			int count = 1;
			for(HashMap.Entry<RecipeIngredient, Integer> entry : missingReciepList.entrySet()) {
				str += "ingredient_" + count + entry.getKey().getName() + " -- " + entry.getValue() +" "+ entry.getKey().getUnit() + " missing ||";
				missingList.add(str);
			}
			
		}
		missingListView.setItems(missingList);
		return;
	}

	//@FXML 
	//public void addBrew(ActionEvent event) {

	//}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		recipeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle (MouseEvent click) {
				if(click.getClickCount() == 2) {
					//Use ListView's getSeleted Item
					//the listview contains string
					String currentItemSelected = recipeListView.getSelectionModel().getSelectedItem();
					System.out.println(currentItemSelected);
				}
			}
		});
		// TODO Auto-generated method stub

	}

}
