package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

	
public class addEquipmentController implements Initializable {
	@FXML
	private ComboBox<String> equipmentType;
	
	@FXML
	private TextField equipmentCapacity;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub

	}
	@FXML
	public void toAddEquipment(ActionEvent event) {
		Start.getInstance().addEquipment();
	}
	@FXML
	public void toMaintainEquipment(ActionEvent event) {
		Start.getInstance().maintainEquipment();
	}

}
