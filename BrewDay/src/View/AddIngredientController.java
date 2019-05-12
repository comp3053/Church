package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Database;
import Model.Ingredient;
import Model.StorageIngredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddIngredientController implements Initializable{
	
	@FXML
	private TextField ingredientName;
	@FXML
	private TextField ingredientValue;
	@FXML
	private TextField ingredientUnit;
	
	
	@FXML
	public void backToMaintainIngredient(ActionEvent event) {
		Start.getInstance().maintainIngredient();
	}
	
	@FXML
	public void addIngredient(ActionEvent event) {
		String name = ingredientName.getText();
		String value =  ingredientValue.getText();
		String unit = ingredientUnit.getText();
		
		Database db = new Database();
		db.addStorageIngredient(name, Integer.parseInt(value), unit);
		
		Start.getInstance().maintainIngredient();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
