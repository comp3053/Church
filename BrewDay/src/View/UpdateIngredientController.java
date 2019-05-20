package View;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.StorageIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UpdateIngredientController implements Initializable{

	//for show data
	ObservableList<StorageIngredient> StorageIngredientList = FXCollections.observableArrayList();
	@FXML
	private TableView<StorageIngredient> ingredientTable = new TableView<StorageIngredient>(StorageIngredientList);
	@FXML
	private TableColumn<StorageIngredient, String> nameList;
	@FXML
	private TableColumn<StorageIngredient, Integer> stockList;
	@FXML
	private TableColumn<StorageIngredient, String> unitList;


	@FXML
	private TextField inputValue;


	@FXML
	public void addStock(ActionEvent event) {
		StorageIngredient storageIngredient = ingredientTable.getSelectionModel().getSelectedItem();
		if(storageIngredient == null) {
			Start.getInstance().warningMsg("Invalid input", "Please select a ingredient to update!");
			return;
		}
		int stock = 0;
		try{
			stock = Integer.parseInt(inputValue.getText());
			updateIngredient(storageIngredient, 1, stock);
		}catch (NumberFormatException e) {
			// TODO: handle exception
			if(stock == 0) {
				Start.getInstance().warningMsg("Invalid input", "Please input the update information");
				Start.getInstance().updateIngredient();
				return;
			}
			Start.getInstance().warningMsg("Invalid input", "The value should be positive integer(s)");
			Start.getInstance().updateIngredient();
			return;
		}

	}

	@FXML
	public void subtractStock(ActionEvent event) {
		StorageIngredient storageIngredient = ingredientTable.getSelectionModel().getSelectedItem();
		if(storageIngredient == null) {
			Start.getInstance().warningMsg("Invalid input", "Please select a ingredient to update!");
			return;
		}
		int stock= 0;
		try {
			stock = Integer.parseInt(inputValue.getText());

			if (stock > storageIngredient.getStock()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Out of stock");
				alert.setContentText("The current stock is unavailable for the operation!");
				ButtonType buttonTypeOK = new ButtonType("Back");
				alert.getButtonTypes().setAll(buttonTypeOK);
				alert.showAndWait();
			}
			else {
				updateIngredient(storageIngredient, 0, stock); //subtract
			}
		}catch (NumberFormatException e) {
			// TODO: handle exception
			if(stock == 0) {
				Start.getInstance().warningMsg("Invalid input", "Please input the update information");
				Start.getInstance().updateIngredient();
				return;
			}
			Start.getInstance().warningMsg("Invalid input", "The value should be positive integer(s)");
			Start.getInstance().updateIngredient();
			return;
		}
	}

	//choice 0:  subtract 
	//choice 1:  add
	public void updateIngredient(StorageIngredient ingredient, int choice, int stock) {
		int tempStock = ingredient.getStock();
		if(stock<0) {
			Start.getInstance().warningMsg("Invalid input", "The value should be positive integer(s)");
			return;
		}
		//subtract ingredient stock
		if (choice == 0) {
			if(tempStock - stock == 0) {
				ingredient.setStock(0);
			}
			else {
				ingredient.setStock(tempStock - stock);
			}
		}
		else if (choice == 1) {
			ingredient.setStock(tempStock + stock);
		}

		Database database = new Database();
		database.updateStorgeIngredient(ingredient);

		Start.getInstance().confirmMsg("Success!", "The Ingredient has been updated!");
		Start.getInstance().updateIngredient();
	}

	@FXML
	public void backToMaintainIngredient(ActionEvent event) {
		Start.getInstance().maintainIngredient();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		StorageIngredientList.clear();

		ArrayList<StorageIngredient> ingredientList = new ArrayList<StorageIngredient>();
		Database db = new Database();
		ingredientList = db.getStorgeIngredientList();

		for(StorageIngredient ingredient:ingredientList) {
			StorageIngredientList.add(ingredient);
		}

		nameList.setCellValueFactory(new PropertyValueFactory<StorageIngredient, String>("Name"));
		stockList.setCellValueFactory(new PropertyValueFactory<StorageIngredient, Integer>("Stock"));
		unitList.setCellValueFactory(new PropertyValueFactory<StorageIngredient, String>("Unit"));

		ingredientTable.setItems(StorageIngredientList);
	}

}
