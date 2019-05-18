package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.StorageIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UpdateRecipeController implements Initializable{
	
	//for show data
		ObservableList<StorageIngredient> StorageIngredientList = FXCollections.observableArrayList();
		@FXML
		private TableView<StorageIngredient> ingredientTable = new TableView<StorageIngredient>(StorageIngredientList);
		@FXML
		private TableColumn<StorageIngredient, String> nameList;
		@FXML
		private TableColumn<StorageIngredient, Integer> stockList;
		@FXML
		private TableColumn<StorageIngredient, String> unitList;
		
		
		@FXML
		private TextField inputValue;
		
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub

	}
	@FXML
	public void toMaintainRecipe(ActionEvent event) {
		Start.getInstance().maintainRecipe();
	}

}
