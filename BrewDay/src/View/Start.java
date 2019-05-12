package View;

import java.io.Console;

import Tool.InitDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application
{
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		stage = primaryStage;
		this.stage.setTitle("Brew Day");
		mainPage();
        stage.show();
        
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
