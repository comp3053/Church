package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainContrller implements Initializable {
	
	
	@FXML
	public void toMaintainRecipe(ActionEvent event) {
		Start.getInstance().maintainRecipe();
	}
	
	@FXML
	public void toMaintainEquipment(ActionEvent event) {
		Start.getInstance().maintainEquipment();
	}
	
	@FXML
	public void toMaintainIngredient(ActionEvent event) {
		Start.getInstance().maintainIngredient();
	}
	
	@FXML
	public void toRecommendRecipe(ActionEvent event) {
		Start.getInstance().recommendRecipe();
	}
	@FXML
	public void toWriteNotePage(ActionEvent event) {
		Start.getInstance().writeNotePage();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
