package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MaintainRecipeController implements Initializable{
	
	
	@FXML
	public void toMainPage(ActionEvent event) {
		Start.getInstance().mainPage();
	}
	
	@FXML
	public void toAddRecipe(ActionEvent event) {
		Start.getInstance().addRecipe();
	}
	
	@FXML
	public void toDeleteREcipe(ActionEvent event) {
		Start.getInstance().deleteRecipe();
	}
	
	ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
	@FXML
	private TableView<Recipe> recipeTableView = new TableView<Recipe>(recipeList);
	@FXML
	private TableColumn<Recipe, String> recipeName;
	@FXML
	private TableColumn<Recipe, String> recipeID;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		recipeList.clear();
		
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
		Database database = new Database();
		recipeArrayList = database.getRecipeList();
		System.out.println("raL: " + recipeArrayList.toArray().length);
		
		for(Recipe tmpR: recipeArrayList) {
			recipeList.add(tmpR);
		}
		
		System.out.println("rL: " + recipeList.toArray().length);
		//System.out.println(new PropertyValueFactory<Recipe, String>("name").getProperty());
		
		//render the ID
		recipeID.setCellFactory((col)->{
			TableCell<Recipe, String> cell = new TableCell<Recipe, String>(){
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);
					
					if(!empty) {
						int rowIndex = this.getIndex()+1;
						this.setText(String.valueOf(rowIndex));
					}
				}
			};
			return cell;
		});
		
		recipeName.setCellValueFactory(new PropertyValueFactory<Recipe, String>("name"));
		recipeTableView.setItems(recipeList);
	}

}
