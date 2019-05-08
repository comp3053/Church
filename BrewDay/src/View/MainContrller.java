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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
