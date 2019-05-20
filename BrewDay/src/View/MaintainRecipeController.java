package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Database;
import Model.Recipe;
import Model.RecipeIngredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



public class MaintainRecipeController implements Initializable{
	
	ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
	@FXML
	private TableView<Recipe> recipeTableView = new TableView<Recipe>(recipeList);
	@FXML
	private TableColumn<Recipe, String> recipeName;
	@FXML
	private TableColumn<Recipe, String> recipeID;
	
	ObservableList<String> recipeIngredientList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> recipeIngredientListView = new ListView<String>(recipeIngredientList);

	
	@FXML
	private Label recipeNameLabel;
	@FXML
	private Label literOfBeerLabel;
	
	
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
	
	@FXML
	public void toUpdateRecipe(ActionEvent event) {
		Start.getInstance().updateRecipe();
	}
	
	
	public void showRecipeDetail(Recipe recipe) {
		if(recipe!=null) {
			//fill the labels with info from the person object
			recipeNameLabel.setText(recipe.getName());
			literOfBeerLabel.setText(Integer.toString(recipe.getLiterOfbeer()));
			//fill the list view
			for(RecipeIngredient rIngredient : recipe.getList()) {
				recipeIngredientList.add(rIngredient.getName()+": " + rIngredient.getValue() + rIngredient.getUnit() );
			}
			recipeIngredientListView.setItems(recipeIngredientList);
		}
		else {
			//recipe is null, remove all contents
			recipeNameLabel.setText("");
			literOfBeerLabel.setText("");
			recipeIngredientList.clear();
			recipeIngredientListView.setItems(recipeIngredientList);
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		recipeList.clear();
		
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
		Database database = new Database();
		recipeArrayList = database.getRecipes();
		
		for(Recipe tmpR: recipeArrayList) {
			recipeList.add(tmpR);
		}
		
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
		
		//initial the content is blank
		showRecipeDetail(null);
		
		//listen for selection changes and show the recipe details
		
		recipeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent click) {
				showRecipeDetail(null);
				if(click.getClickCount() >= 1) {
					//Use ListView's getSeleted Item
					//the listview contains string
					Recipe currentItemSelected = recipeTableView.getSelectionModel().getSelectedItem();
					//System.out.println("Clicked--recipeList!");
					showRecipeDetail(currentItemSelected);
					
				}
			}
		});
	}

}
