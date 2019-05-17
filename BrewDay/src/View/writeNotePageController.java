package View;

import java.awt.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Database;
import Model.Ingredient;
import Model.Note;
import Model.StorageIngredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class writeNotePageController implements Initializable{
	
	@FXML
	private TextField recipeName;
	@FXML
	private TextField brewID;
	@FXML
	private TextField date;
	@FXML
	private TextArea note;
	
	@FXML
	public void backToMaintainIngredient(ActionEvent event) {
		Start.getInstance().maintainIngredient();
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
