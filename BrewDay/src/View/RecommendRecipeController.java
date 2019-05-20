package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Model.Brew;
import Model.Database;
import Model.Equipment;
import Model.Recipe;
import Model.RecipeIngredient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RecommendRecipeController implements Initializable{

	//recipe list is the list content for recommended recipe list rendering
	ObservableList<String> recipeList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> recipeListView = new ListView<String>(recipeList);
	
	//missing list is the list content for recipes with missing ingredients
	ObservableList<String> missingList = FXCollections.observableArrayList();
	@FXML
	private ListView<String> missingListView = new ListView<String>(missingList);

	@FXML
	private TextField inputBatchSizeBlank;

	private String currentItemSelected;
	private int batchSize;
	
	@FXML
	public void backToMain(ActionEvent event) {
		Start.getInstance().mainPage();
	}

	//return the string that display in the list view
	public static String getRecipeText(Recipe recipe, int batchSize) {
		String text = "Recipe_ID: " + recipe.getID() + " : " + recipe.getName() + " _ " + "Ingredients: ";
		int count = 1;
		for(RecipeIngredient rg:recipe.getList()) {
			float ingredientValue = recipe.convertMeasure(rg, batchSize);
			text = text + " Ingredient_" + count + " : " + rg.getName() + " -- " + ingredientValue + " " + rg.getUnit() + " ||";
			count++;
		}
		return text;
	}
	
	@FXML
	public void recommendRecipe() {
		recipeList.clear();
		batchSize = 0;
		ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

		try {
			//get the batch size from the user input
			batchSize = Integer.parseInt(inputBatchSizeBlank.getText());
			//pass the batch for recipe recommendation, get the recipe list
			if(batchSize<=0) {
				Start.getInstance().warningMsg("Input Error", "Please input a positive number!");
				String str = "No recommended recipes";
				recipeList.add(str);
				recipeListView.setItems(recipeList);
				return;
			}
			recipeArrayList = Recipe.recommendRecipe(batchSize);
//			System.out.println("Recommend: Im here line 53 ");
			
			if(recipeArrayList == null || recipeArrayList.size() == 0) {
				String str = "No recommended recipes";
				recipeList.add(str);
//				System.out.println("Recommend: Im here line 57");
				recipeListView.setItems(recipeList);
				return;
			}

			for (Recipe tmp: recipeArrayList) {
//				System.out.println("Recommend: Im here line 63");
				//get the content to render the table
				String str = getRecipeText(tmp, batchSize);
				recipeList.add(str);
			}
//			System.out.println("Recommend: Im here line 68");
			recipeListView.setItems(recipeList);

		} catch (NumberFormatException e) {
			//e.printStackTrace();
			// TODO: handle exception
			Start.getInstance().warningMsg("Input Error", "Please input a number for batch size");			
			Start.getInstance().recommendRecipe();
			return;
		}
		finally{
			producingMissingIngredients(batchSize);
		}
	}

	public void producingMissingIngredients(int batchSize) {
		missingList.clear();
		HashMap<RecipeIngredient, Float> missingReciepList = new HashMap<RecipeIngredient, Float>();
		
		Database db = new Database();
		ArrayList<Recipe> rList = db.getRecipes();
		
		for(Recipe r : rList) {
			missingReciepList = r.produceMissingIngredient(batchSize, r.getList());
			String str = "Recipe_ID: " + r.getID() + " : " + r.getName() + " _ ingredients: ";
			int count = 1;
			for(HashMap.Entry<RecipeIngredient, Float> entry : missingReciepList.entrySet()) {
				str += "ingredient_" + count + " : " + entry.getKey().getName() + " -- " + entry.getValue() +" "+ entry.getKey().getUnit() + " missing ||";
				missingList.add(str);
			}
			
		}
		missingListView.setItems(missingList);
		return;
	}

	public void addBrew(String seletctedString, int batchSize, Equipment equipment){
		//Update equipment capacity
		Float curr = equipment.getCapacity();

		String[] str = seletctedString.split(" :");
		String firstPart = str[0];
		//System.out.println(firstPart);
		String[] str2 =  firstPart.split(": ");
		String recipe_id = str2[1];
		//System.out.println("Recommend 135/ Recipe ID: " + recipe_id);
		Database db = new Database();
		db.updateEquipmentAvaCapacity(equipment, curr - batchSize);
		Recipe recipe = db.getRecipe(recipe_id.toString());
		
		Brew brew = new Brew(batchSize,recipe);
		
		System.out.println("recommend 140/ batch Size: " + batchSize + "recipeID: " + recipe.getName());
		System.out.println("recommend 141/ Brew: " + brew.getBatchSize() + " brewID: " + brew.getID());
		
		if(db.addBrew(brew)) {
			Start.getInstance().confirmMsg("Success!", "Add Brew Success! ");
		}
			
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		recipeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle (MouseEvent click) {
				if(click.getClickCount() == 2) {
					//Use ListView's getSeleted Item
					//the listview contains string
					currentItemSelected = recipeListView.getSelectionModel().getSelectedItem();
					chooseEquipment(currentItemSelected);
					//Start.getInstance().chooseEquipment();
					//addBrew(currentItemSelected, batchSize);
				}
			}
		});
		
		// TODO Auto-generated method stub

	}
	
	public void chooseEquipment(String seletctedString) {
	    ObservableList<String> equipmentList = FXCollections.observableArrayList();
	    Database database = new Database();
	    ArrayList<Equipment> equipments = database.getAvailableEquipments(batchSize);
	    for(Equipment equipment : equipments) {
	    	equipmentList.add(equipment.getType()+"_"+equipment.getID()+" - Capacity: "+equipment.getAvaliableCapacity());
	    }
	    ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Choose a equipment",equipmentList);
	    choiceDialog.setTitle("Confirm!");
	    choiceDialog.setHeaderText("Confirm!");
	    choiceDialog.setContentText("Please Choose an equipment: ");
	    ButtonType okType = new ButtonType("OK");
	    choiceDialog.getDialogPane().getButtonTypes().set(0, okType);
	    
	    choiceDialog.getDialogPane().getButtonTypes().remove(1);
	    
	    choiceDialog.selectedItemProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		System.out.println("recommend recipe/195: " + newValue);
	    		Button ok = (Button)choiceDialog.getDialogPane().lookupButton(okType);
	    		//System.out.println(ok);
	    		ok.setOnAction(new EventHandler<ActionEvent>() {
	    			@Override
	    			public void handle(ActionEvent event) {
	    				if(event != null) {
	    					String[] str = newValue.split(" - ");
	    					String[] str2 = str[0].split("_");
	    					System.out.println("EquipmentID: " + str2[1]);
	    					Equipment equipment = database.getEquipment(str2[1]);
	    					addBrew(seletctedString, batchSize, equipment);
	    					
	    				}
	    					
	    					//System.out.println("OK: " + newValue);
	    			}
				});
	    	}
		});
	   
	    choiceDialog.show();
	}

}
