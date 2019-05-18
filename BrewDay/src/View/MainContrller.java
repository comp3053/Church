package View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainContrller implements Initializable {
	
	@FXML
	private ImageView iv;
	@FXML
	public void toMaintainRecipe(ActionEvent event) {
		Start.getInstance().maintainRecipe();
	}
	
	@FXML
	public void toMaintainEquipment(ActionEvent event) {
		Start.getInstance().maintainEquipment();
	}
	
	@FXML
	public void toMaintainIngredient(ActionEvent event) {
		Start.getInstance().maintainIngredient();
	}
	
	@FXML
	public void toRecommendRecipe(ActionEvent event) {
		Start.getInstance().recommendRecipe();
	}
	@FXML
	public void toWriteNotePage(ActionEvent event) {
		Start.getInstance().writeNotePage();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		// load the image
        Image image = new Image("file:resources/images/beer.jpg");

        // simple displays ImageView the image as is
        ImageView iv = new ImageView();
        iv.setImage(image);
		
	}


}
