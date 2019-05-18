package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class maintainEquipmentController implements Initializable{
	
	@FXML
	public void toMainPage(ActionEvent event) {
		Start.getInstance().mainPage();
	}
	@FXML
	public void toUpdateEquipment(ActionEvent event) {
		Start.getInstance().updateEquipment();
	}
	@FXML
	public void toAddEquipment(ActionEvent event) {
		Start.getInstance().addEquipment();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
