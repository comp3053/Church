package View;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.util.locale.provider.AvailableLanguageTags;

public class updateEquipmentController implements Initializable {
	
	
	ObservableList<Equipment> EquipmentList = FXCollections.observableArrayList();
	@FXML
	private TableView<Equipment> equipmentTable = new TableView<Equipment>(EquipmentList);
	@FXML
	private TableColumn<Equipment, String> equipmentNameList;
	@FXML
	private TableColumn<Equipment, Float> capacityList;
	@FXML
	private TableColumn<Equipment, Float> availableCapacity;
	
	@FXML
	private TextField capacityValue;
	
	@FXML
	public void toMaintainEquipment(ActionEvent event) {
		Start.getInstance().maintainEquipment();
	}
	
	@FXML
	public void toUpdateEquipment(ActionEvent event) {
		Equipment choosen = equipmentTable.getSelectionModel().getSelectedItem();
		
		float capacity = 0;
		
		try {
			capacity = Float.parseFloat(capacityValue.getText());
			
			Database database = new Database();
			database.updateEquipmentAvaCapacity(choosen, capacity);
			Start.getInstance().confirmMsg("Sucess", "Sucessfully update capacity");
			Start.getInstance().updateEquipment();
		} catch (NumberFormatException e) {
			// TODO: handle exception
			Start.getInstance().warningMsg("Error input", "Please input number");
			Start.getInstance().updateEquipment();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Start.getInstance().warningMsg("Error", "Please choose one equiment for upadte");
			Start.getInstance().updateEquipment();
		}
		
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		EquipmentList.clear();
		
		ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
		Database db = new Database();
		equipmentList = db.getEquipmentsList();
		
		for(Equipment equipment:equipmentList) {
			//System.out.println("equiment:"+equipment.getType()+" "+equipment.getAvaliableCapacity());
			EquipmentList.add(equipment);
		}
		
		
		equipmentNameList.setCellValueFactory(new PropertyValueFactory<Equipment, String>("EquipmentName"));
		capacityList.setCellValueFactory(new PropertyValueFactory<Equipment, Float>("Capacity"));
		availableCapacity.setCellValueFactory(new PropertyValueFactory<Equipment, Float>("AvaliableCapacity"));
		
		equipmentTable.setItems(EquipmentList);
	}
	

}
