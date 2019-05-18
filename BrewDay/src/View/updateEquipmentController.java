package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.Equipment;
import Model.StorageIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class updateEquipmentController implements Initializable {
	ObservableList<Equipment> EquipmentList = FXCollections.observableArrayList();
	@FXML
	private TableView<Equipment>  equipmentTable= new TableView<Equipment>(EquipmentList);
	@FXML
	private TableColumn<Equipment, String> equipmentNameList;
	@FXML
	private TableColumn<Equipment, Float> capacityList;
	@FXML
	private javafx.scene.control.TextField capacityValue;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		EquipmentList.clear();
		
		ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
		Database db = new Database();
		equipmentList = db.getEquipmentsList();
		
		for(Equipment equipment:equipmentList) {
			
			EquipmentList.add(equipment);
		}
		
		equipmentNameList.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Equipment Name"));
		capacityList.setCellValueFactory(new PropertyValueFactory<Equipment, Float>("Capacity"));
		
		
		equipmentTable.setItems(EquipmentList);
	}
	@FXML
	public void toMaintainEquipment(ActionEvent event) {
		Start.getInstance().maintainEquipment();
	}
	
	@FXML
	public void toUpdateEquipment(ActionEvent event) {
		
	}

}
