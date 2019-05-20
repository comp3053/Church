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

public class DeleteRecipeController implements Initializable{
	
	ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
	@FXML
	private TableView<Recipe> recipeTableView = new TableView<Recipe>(recipeList);
	@FXML
	private TableColumn<Recipe, String> recipeName;
	
	
	@FXML
	public void deleteRecipe(ActionEvent event) {
		Recipe choosen = recipeTableView.getSelectionModel().getSelectedItem();
		
		try {
			Database database = new Database();
			database.deleteRecipe(choosen);
			Start.getInstance().confirmMsg("Success!", "The Recipe has been deleted!");
			Start.getInstance().deleteRecipe();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Start.getInstance().warningMsg("Warning!", "Please select a recipe!");
		}

	}
	
	@FXML
	public void backToMaintainRecipe(ActionEvent event) {
		Start.getInstance().maintainRecipe();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		recipeList.clear();
		
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
		Database database = new Database();
		recipeArrayList = database.getRecipeList();
		
		for(Recipe tempR:recipeArrayList) {
			recipeList.add(tempR);
		}
		
		recipeName.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Name"));
		
		recipeTableView.setItems(recipeList);
	}
	
}
