package View;

import java.io.Console;

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

	public void jump()
	{
		try 
		{
			replaceSceneContent("maintainRecipe.fxml");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	
	private Parent replaceSceneContent(String fxml) throws Exception 
	{
        System.out.println("before");
        System.out.println(Start.class.getResource(fxml).toString());
		Parent page = (Parent) FXMLLoader.load(Start.class.getResource(fxml), null, new JavaFXBuilderFactory());
		System.out.println("after");
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
