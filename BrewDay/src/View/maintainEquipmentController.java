package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.Equipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.pkcs11.Secmod.DbMode;

public class maintainEquipmentController implements Initializable{
	
	ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();
	@FXML
	private TableView<Equipment> equipmentTableView = new TableView<Equipment>(equipmentList);
	@FXML
	private TableColumn<Equipment, String> equipmentName;
	@FXML
	private TableColumn<Equipment, Float> equipmentCapacity;
	@FXML
	private TableColumn<Equipment, Float> equipmentAvailableCapacity;
	
	
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
		equipmentList.clear();
		
		ArrayList<Equipment> equipmentArrayList = new ArrayList<Equipment>();
		Database database = new Database();
		equipmentArrayList = database.getEquipmentsList();
		
		for(Equipment temp:equipmentArrayList) {
			equipmentList.add(temp);
		}
		
		equipmentName.setCellValueFactory(new PropertyValueFactory<Equipment, String>("EquipmentName"));
		equipmentCapacity.setCellValueFactory(new PropertyValueFactory<Equipment, Float>("Capacity"));
		equipmentAvailableCapacity.setCellValueFactory(new PropertyValueFactory<Equipment, Float>("AvaliableCapacity"));
		
		equipmentTableView.setItems(equipmentList);
	}

}
