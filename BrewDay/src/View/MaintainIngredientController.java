package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MaintainIngredientController implements Initializable{
	
	@FXML
	public void toMainPage(ActionEvent event) {
		Start.getInstance().mainPage();
	}
	
	@FXML
	public void toAddIngredient(ActionEvent event) {
		Start.getInstance().addIngredient();
	}
	
	@FXML
	public void toUpdateIngredient(ActionEvent event) {
		Start.getInstance().updateIngredient();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
