package View;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Brew;
import Model.Database;
import Model.Ingredient;
import Model.Recipe;
import Model.RecipeIngredient;
import Model.StorageIngredient;
//import apple.laf.JRSUIConstants.ShowArrows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class UpdateRecipeController implements Initializable{
	
	
	ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
	@FXML
	private TableView<Recipe> recipeTableView = new TableView<Recipe>(recipeList);
	@FXML
	private TableColumn<Recipe, String> recipeID;
	@FXML
	private TableColumn<Recipe, String> recipeName;
	
	ObservableList<RecipeIngredient> recipeIngredientList = FXCollections.observableArrayList();
	@FXML
	private TableView<RecipeIngredient> recipeIngredientTabelView = new TableView<RecipeIngredient>(recipeIngredientList);
	@FXML
	private TableColumn<RecipeIngredient, String> ingredientName;
	@FXML
	private TableColumn<RecipeIngredient, Integer> ingredientValue;
	@FXML
	private TableColumn<RecipeIngredient, String> ingredientUnit;
	
	@FXML
	private TextField liftOfBeer;
	@FXML
	private Label name;
	@FXML
	private TextField updateValue;
	
	
	@FXML
	public void toMaintainRecipe(ActionEvent event) {
		Start.getInstance().maintainRecipe();
	}
	
	@FXML
	public void updateRecipe(ActionEvent event) {
		Recipe choosenRecipe = recipeTableView.getSelectionModel().getSelectedItem();
		ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>();
		for(RecipeIngredient tempRecipeIngredient:recipeIngredientList) {
			recipeIngredients.add(tempRecipeIngredient);
		}
		
		choosenRecipe.setList(recipeIngredients);
		
		int lfb = 0;
		
		try {
			lfb = Integer.parseInt(liftOfBeer.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			Start.getInstance().warningMsg("Input Error", "LiftOfBeer input is invaild!");
		}
		
		choosenRecipe.setLiterOfbeer(lfb);
		
		Database database = new Database();
		database.updateRecipeWithIngrdient(choosenRecipe);
		
		Start.getInstance().updateRecipe();
	}
	
	@FXML
	public void changeIngredientValue(ActionEvent event) {
		int value = 0;
		try {
			value = Integer.parseInt(updateValue.getText());
		} 
		catch (NumberFormatException e) {
			// TODO: handle exception
			if(value == 0) {
				Start.getInstance().warningMsg("Input Empty", "Please input number into value box");
			}
			else {
				Start.getInstance().warningMsg("Input Invaild", "Please input number!");
			}
		}
		
		RecipeIngredient chooseIngredient = recipeIngredientTabelView.getSelectionModel().getSelectedItem();
		
		ArrayList<RecipeIngredient> tempRecipeIngredientList = new ArrayList<RecipeIngredient>();
		
		for(RecipeIngredient temp: recipeIngredientList) {
			tempRecipeIngredientList.add(temp);
		}
		
		recipeIngredientList.clear();
		
		for(RecipeIngredient temp: tempRecipeIngredientList) {
			if(temp == chooseIngredient) {
				temp.setValue(value);
			}
		}
		
		for(RecipeIngredient temp:tempRecipeIngredientList) {
			recipeIngredientList.add(temp);
		}
		
		recipeIngredientTabelView.setItems(recipeIngredientList);
		
		updateValue.setText("");
	}
	
	public void displayIngredientData(RecipeIngredient chooseRecipeIngredint) {
		updateValue.setText(Integer.toString(chooseRecipeIngredint.getValue()));
	}
	
	public void showIngrdientList(Recipe choosenRecipe) {
		recipeIngredientList.clear();
		
		ArrayList<RecipeIngredient> tempRecipeIngredientList = choosenRecipe.getList();
		
		for(RecipeIngredient tempIngredient: tempRecipeIngredientList) {
			recipeIngredientList.add(tempIngredient);
		}
		
		recipeIngredientTabelView.setRowFactory( tv -> {
			TableRow<RecipeIngredient> row_r = new TableRow<>();
			row_r.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row_r.isEmpty()) ) {
					RecipeIngredient rowData = row_r.getItem();
					displayIngredientData(rowData);
					}
			});
			return row_r ;
		});
		
		name.setText(choosenRecipe.getName());
		liftOfBeer.setText(Integer.toString(choosenRecipe.getLiterOfbeer()));
		
		ingredientName.setCellValueFactory(new PropertyValueFactory<RecipeIngredient, String>("Name"));
		ingredientValue.setCellValueFactory(new PropertyValueFactory<RecipeIngredient, Integer>("Value"));
		ingredientUnit.setCellValueFactory(new PropertyValueFactory<RecipeIngredient, String>("Unit"));
		
		recipeIngredientTabelView.setItems(recipeIngredientList);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		recipeList.clear();
		recipeIngredientList.clear();
		
		ArrayList<Recipe> tempArrayList = new ArrayList<Recipe>();
		Database database = new Database();
		tempArrayList = database.getRecipes();
		
		for(Recipe tempRecipe: tempArrayList) {
			//System.out.println(tempRecipe.getID());
			recipeList.add(tempRecipe);
		}
		
		//initialize tableView
		recipeTableView.setRowFactory( tv -> {
		TableRow<Recipe> row = new TableRow<>();
		row.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
				Recipe rowData = row.getItem();
				showIngrdientList(rowData);
				}
		});
		return row ;
	});
		
		recipeID.setCellValueFactory(new PropertyValueFactory<Recipe, String>("ID"));
		recipeName.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Name"));
		
		recipeTableView.setItems(recipeList);
	}
	

}
