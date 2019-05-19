package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.Ingredient;
import Model.Recipe;
import Model.RecipeIngredient;
import Model.StorageIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.pkcs11.Secmod.DbMode;

public class AddRecipeController implements Initializable {
	
	ObservableList<StorageIngredient> StorageIngredientList = FXCollections.observableArrayList();
	@FXML
	private TableView<StorageIngredient> IngredientList = new TableView<StorageIngredient>(StorageIngredientList);
	@FXML
	private TableColumn<StorageIngredient, String> IngredientName;
	@FXML
	private TableColumn<StorageIngredient, String> IngredientUnit;
	
	ObservableList<RecipeIngredient> RecipeIngredientList = FXCollections.observableArrayList();
	@FXML
	private TableView<RecipeIngredient> RecipeIngredientTable = new TableView<RecipeIngredient>(RecipeIngredientList);
	@FXML
	private TableColumn<RecipeIngredient, String> RecipeIngredientName;
	@FXML
	private TableColumn<RecipeIngredient, Integer> RecipeIngredientValue;
	@FXML
	private TableColumn<RecipeIngredient, String> RecipeIngredientUnit;
	
	@FXML
	private TextField inputValue;
	@FXML
	private TextField Input_liftOfBeer;
	@FXML
	private TextField Input_recipeName;
	
	
	@FXML
	public void addRecipeTodb(ActionEvent event) {
		String recipeName = Input_recipeName.getText();
		int liftOfBeer = Integer.parseInt(Input_liftOfBeer.getText());
		String description = null;
		String id = null;
		
		ArrayList<RecipeIngredient> RecipeIngredientArray = new ArrayList<RecipeIngredient>();
		for(RecipeIngredient tempIngredient: RecipeIngredientList) {
			RecipeIngredientArray.add(tempIngredient);
		}
		
		Recipe tempRecipe = new Recipe(id, liftOfBeer, recipeName, RecipeIngredientArray, description);
		
		Database database = new Database();
		database.addRecipeToDB(tempRecipe);
		
		Start.getInstance().addRecipe();
	}
	
	
	@FXML
	public void addIngredientToRecipe(ActionEvent event) {
		try {
			int value = Integer.parseInt(inputValue.getText());
			System.out.println(value);
			StorageIngredient choosenIngredient = IngredientList.getSelectionModel().getSelectedItem();
			
			RecipeIngredient tempIngredient = new RecipeIngredient(choosenIngredient.getID(), choosenIngredient.getName(), choosenIngredient.getUnit(), value);
			
			RecipeIngredientList.add(tempIngredient);
		}
		catch(RuntimeException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Wrong Input");
			alert.setContentText("Please input the information for the recipe!");
			ButtonType buttonTypeOK = new ButtonType("Back");
			alert.getButtonTypes().setAll(buttonTypeOK);
			alert.showAndWait();
		}

		RecipeIngredientName.setCellValueFactory(new PropertyValueFactory<RecipeIngredient, String>("Name"));
		RecipeIngredientValue.setCellValueFactory(new PropertyValueFactory<RecipeIngredient, Integer>("Value"));
		RecipeIngredientUnit.setCellValueFactory(new PropertyValueFactory<RecipeIngredient, String>("Unit"));
		RecipeIngredientTable.setItems(RecipeIngredientList);
		
		inputValue.setText("");
		
	}
	
	@FXML
	public void removeIngredientToRecipe(ActionEvent event) {
		System.out.println("remove");
		RecipeIngredient choosenIngredient = RecipeIngredientTable.getSelectionModel().getSelectedItem();
		//RecipeIngredient tmpIngre = new RecipeIngredient(choosenIngredient.getID(), choosenIngredient.getName(), choosenIngredient.getUnit(), 0);
		RecipeIngredientList.remove(choosenIngredient);
		RecipeIngredientTable.setItems(RecipeIngredientList);
		
	}
	
	@FXML
	public void toMaintainRecipe(ActionEvent event) {
		Start.getInstance().maintainRecipe();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		ArrayList<StorageIngredient> storageIngredientList = new ArrayList<StorageIngredient>();
		
		Database database = new Database();
		storageIngredientList = database.getStorgeIngredientList();
		
		StorageIngredientList.clear();
		RecipeIngredientList.clear();
		
		for(StorageIngredient ingredient: storageIngredientList) {
			StorageIngredientList.add(ingredient);
		}
		
		IngredientName.setCellValueFactory(new PropertyValueFactory<StorageIngredient, String>("Name"));
		IngredientUnit.setCellValueFactory(new PropertyValueFactory<StorageIngredient, String>("Unit"));
		IngredientList.setItems(StorageIngredientList);
		
	}

}
