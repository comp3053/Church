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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class RecommendRecipeController implements Initializable{
	
	ObservableList<String> recipeList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> recipeListView = new ListView<String>(recipeList);
	
	@FXML
	private TextField inputBatchSizeBlank;
	
	public static String getRecipeText(Recipe recipe) {
		String text = recipe.getName() + " ";
		
		for(RecipeIngredient rg:recipe.getList()) {
			text = text + " " + rg.getName() + ": " + rg.getValue() + rg.getUnit();
		}
		
		return text;
	}
	
	@FXML
	public void recommendRecipe(ActionEvent event) {
		
		recipeList.clear();
		
		int batchSize = Integer.parseInt(inputBatchSizeBlank.getText());
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
		
		recipeArrayList = Recipe.recommendRecipe(batchSize);
		
		for(Recipe tempR:recipeArrayList) {
			recipeList.add(getRecipeText(tempR));
			System.out.println(getRecipeText(tempR));
		}
		
		recipeListView.setItems(recipeList);
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
