package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MaintainRecipeController implements Initializable{
	
	
	@FXML
	public void toMainPage(ActionEvent event) {
		Start.getInstance().mainPage();
	}
	
	@FXML
	public void toAddRecipe(ActionEvent event) {
		Start.getInstance().addRecipe();
	}
	
	@FXML
	public void toDeleteREcipe(ActionEvent event) {
		Start.getInstance().deleteRecipe();
	}
	
	ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
	@FXML
	private TableView<Recipe> recipeTableView = new TableView<Recipe>(recipeList);
	@FXML
	private TableColumn<Recipe, String> recipeName;
	@FXML
	private TableColumn<Recipe, Integer> recipeID;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		recipeList.clear();
		
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
		Database database = new Database();
		recipeArrayList = database.getRecipeList();
		System.out.println(recipeArrayList);
		
	}

}
