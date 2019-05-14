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
		int batchSize = 0;
		
		try {
			batchSize = Integer.parseInt(inputBatchSizeBlank.getText());
		} catch (Exception e) {
			// TODO: handle exception
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Input Error");
			alert.setContentText("Please input a number");
			ButtonType buttonTypeOK = new ButtonType("OK");
			alert.getButtonTypes().setAll(buttonTypeOK);
			alert.showAndWait();
			
			Start.getInstance().recommendRecipe();
		}
		
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
		recipeArrayList = Recipe.recommendRecipe(batchSize);
		
		for(Recipe tempR:recipeArrayList) {
			recipeList.add(getRecipeText(tempR));
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
