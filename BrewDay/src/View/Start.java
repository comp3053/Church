package View;

import Tool.InitDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application
{
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		stage = primaryStage;
		this.stage.getIcons().add(new Image("file:resources/images/beer.png"));
		this.stage.setTitle("Brew Day");	 
		mainPage();
		this.stage.show();

	}

	private static Start instance;

	public Start() 
	{
		instance = this;
	}

	public static Start getInstance() 
	{
		return instance;
	}


	public void mainPage()
	{
		try 
		{
			replaceSceneContent("Main.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void maintainRecipe()
	{
		try 
		{
			replaceSceneContent("MaintainRecipe.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	public void maintainEquipment(){
		try 
		{
			replaceSceneContent("MaintainEquipment.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}


	public void maintainIngredient(){
		try 
		{
			replaceSceneContent("MaintainIngredient.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	public void addIngredient(){
		try 
		{
			replaceSceneContent("AddIngredient.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}


	public void updateIngredient(){
		try 
		{
			replaceSceneContent("UpdateIngredient.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	public void addRecipe(){
		try 
		{
			replaceSceneContent("AddRecipe.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	public void deleteRecipe(){
		try 
		{
			replaceSceneContent("DeleteRecipe.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	public void recommendRecipe(){
		try 
		{
			replaceSceneContent("RecommendRecipe.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	public void chooseEquipment() {
		try 
		{
			replaceSceneContent("ChooseEquipment.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void writeNotePage() {
		try 
		{
			replaceSceneContent("writeNotePage.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	public void updateEquipment() {
		try 
		{
			replaceSceneContent("UpdateEquipment.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	public void addEquipment() {
		try 
		{
			replaceSceneContent("addEquipment.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	public void updateRecipe() {
		try 
		{
			replaceSceneContent("updateRecipe.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}


	public void warningMsg (String title, String warning) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("warning");
		alert.setHeaderText(title);
		alert.setContentText(warning);
		ButtonType buttonTypeOK = new ButtonType("OK");
		alert.getButtonTypes().setAll(buttonTypeOK);
		alert.showAndWait();
	}

	public void confirmMsg (String title, String msg) {
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle("Confirmation");
		alert.setHeaderText(title);
		alert.setContentText(msg);
		ButtonType buttonTypeOK = new ButtonType("OK");
		alert.getButtonTypes().setAll(buttonTypeOK);
		alert.showAndWait();
	}
	public static void main(String[] args) {
		InitDataBase.initDB();
		launch(args);
	}

	private Parent replaceSceneContent(String fxml) throws Exception 
	{
		Parent page = (Parent) FXMLLoader.load(Start.class.getResource(fxml), null, new JavaFXBuilderFactory());
		Scene scene = stage.getScene();

		if (scene == null) 
		{
			scene = new Scene(page);
			stage.setScene(scene);
		} 
		else 
		{
			stage.getScene().setRoot(page);
		}
		stage.sizeToScene();
		stage.setResizable(false);
		return page;
	}

}
