package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Recipe;
import Model.RecipeIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RecommendRecipeController implements Initializable{

	ObservableList<String> recipeList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> recipeListView = new ListView<String>(recipeList);

	@FXML
	private TextField inputBatchSizeBlank;

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
	public void recommendRecipe(ActionEvent event) {
		recipeList.clear();
		int batchSize = 0;
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

		try {
			//get the batch size from the user input
			batchSize = Integer.parseInt(inputBatchSizeBlank.getText());
			//pass the batch for recipe recommendation, get the recipe list
			
			recipeArrayList = Recipe.recommendRecipe(batchSize);

			if(recipeArrayList == null) {
				String str = "No recommended recipes";
				recipeList.add(str);
				recipeListView.setItems(recipeList);
				return;
			}

			for (Recipe tmp: recipeArrayList) {
				//get the content to render the table
				String str = getRecipeText(tmp, batchSize);
				recipeList.add(str);
			}
			recipeListView.setItems(recipeList);

		} catch (NumberFormatException e) {
			// TODO: handle exception
			Start.getInstance().warningMsg("Input Error", "Please input a number");			
			Start.getInstance().recommendRecipe();
			return;
		}
	}

	@FXML
	public void producingMissingIngredients(ActionEvent event) {

	}

	@FXML
	public void backToMain(ActionEvent event) {
		Start.getInstance().mainPage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub

	}

}
