package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Database;
import Model.Ingredient;
import Model.StorageIngredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
		String name = null;
		String unit = null;
		int value = -1;
		
		int status = 0;
		
		try {
			name = ingredientName.getText();
			value =  Integer.parseInt(ingredientValue.getText());
			unit = ingredientUnit.getText();
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			status = 1;
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Input Error");
			alert.setContentText("The cunrrent input is unavailable!");
			ButtonType buttonTypeOK = new ButtonType("OK");
			alert.getButtonTypes().setAll(buttonTypeOK);
			alert.showAndWait();
			
			Start.getInstance().addIngredient();
		}
		
		if(name == null || unit == null || value == -1) {
			status = 1;
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Not input");
			alert.setContentText("No enough input in TextFeild");
			ButtonType buttonTypeOK = new ButtonType("OK");
			alert.getButtonTypes().setAll(buttonTypeOK);
			alert.showAndWait();
			
			Start.getInstance().addIngredient();
		}
		
		if (status == 0) {
			Database db = new Database();
			db.addStorageIngredient(name, value, unit);
			
			Start.getInstance().maintainIngredient();
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
