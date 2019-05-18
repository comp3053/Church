package View;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.runtime.Name;

import Model.Database;
import Model.Equipment;
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
		int capacity=0;
		String type= null;
		
		try {
			capacity=Integer.parseInt(equipmentCapacity.getText());
			type = equipmentType.getValue();
			System.out.println(capacity);
			if(capacity != 0 && type != null) {
				if(capacity < 0 ) {
					//check if the value is positive
					Start.getInstance().warningMsg("Invalid input", "The capacity should larger than 0");
					Start.getInstance().addEquipment();
					return;
				}
				else {
//					//check if the name is beginning with numbers
//					if( Character.isLetter(capacity) ) {
//						Start.getInstance().warningMsg("Invalid input", "The capacity should be number");
//						Start.getInstance().addEquipment();
//						return;
//					}
//					else {
						//pass all input check, load to DB
						Database db = new Database();
						Equipment equipment= new Equipment(capacity,type);
						db.addEquipment(equipment);
					}
				//}
			}
			else {
				Start.getInstance().warningMsg("Invalid input", "Please complete all the information!");
			}
		}
		catch (NumberFormatException e) {
			//  TODO: handle exception
			e.printStackTrace();
			if(capacity == 0 || type == null) {

				Start.getInstance().warningMsg("Invalid input", "Please complete all the information!");
				return;
			}
			
			Start.getInstance().warningMsg("Invalid input", "The value should be integer(s)");

			Start.getInstance().addEquipment();
			return;

		}
		Start.getInstance().confirmMsg("Success!", "The Equipment has been added!");
		Start.getInstance().addEquipment();
	}

	@FXML
	public void toMaintainEquipment(ActionEvent event) {
		Start.getInstance().maintainEquipment();
	}

}
