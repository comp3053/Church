package View;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddIngredientController implements Initializable{

	@FXML
	private TextField ingredientName;
	@FXML
	private TextField ingredientValue;
	@FXML
	private ComboBox<String> ingredientUnit;

	@FXML
	public void backToMaintainIngredient(ActionEvent event) {
		Start.getInstance().maintainIngredient();
	}

	@FXML
	public void addIngredient(ActionEvent event) {
		String name = null;
		String unit = null;
		int value = -1;


		try {
			name = ingredientName.getText();
			value =  Integer.parseInt(ingredientValue.getText());
			unit = ingredientUnit.getValue();

			if(name != null && unit != null && value != -1) {
				//all input are completed
				if(value <= 0 ) {
					//check if the value is positive
					Start.getInstance().warningMsg("Invalid input", "The value should larger than 0");
					Start.getInstance().addIngredient();
					return;
				}
				else {
					//check if the name is beginning with numbers
					if( Character.isDigit(name.charAt(0)) ) {
						Start.getInstance().warningMsg("Invalid input", "The ingredient name should starting with a letter");
						Start.getInstance().addIngredient();
						return;
					}
					else {
						//pass all input check, load to DB
						Database db = new Database();
						int rs = db.addStorageIngredient(name, value, unit);
						if(rs == 0) {
							Start.getInstance().warningMsg("Ingredient exists", "The ingredient has already been added");
							Start.getInstance().addIngredient();
							return;
						}
						else if(rs == -1) {
							Start.getInstance().warningMsg("Database error!", "Fail to add the ingredient!");
							Start.getInstance().addIngredient();
							return;
						}
					}
				}
			}
			else {
				Start.getInstance().warningMsg("Invalid input", "Please complete all the information!!!!!!");
				return;
			}

		} catch (NumberFormatException e) {
			//  TODO: handle exception
			if(name == null || unit == null) {

				Start.getInstance().warningMsg("Invalid input", "Please complete all the information!");
				return;
			}
			Start.getInstance().warningMsg("Invalid input", "The value should be integer(s)");

			Start.getInstance().addIngredient();
			return;

		}

		Start.getInstance().confirmMsg("Success!", "The ingredient has been added!");
		Start.getInstance().maintainIngredient();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub

	}

}
