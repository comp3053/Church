package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MaintainRecipeController implements Initializable{
	
	
	@FXML
	public void toMainPage(ActionEvent event) {
		Start.getInstance().mainPage();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}