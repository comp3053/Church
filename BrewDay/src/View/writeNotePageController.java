package View;

import java.awt.TextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Brew;
import Model.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class writeNotePageController implements Initializable{
	
	ObservableList<Brew> brewList =FXCollections.observableArrayList();
	
	@FXML
	private TableView<Brew> brewTableView = new TableView<Brew>(brewList);
	@FXML
	private TableColumn<Brew, String> brewId;
	@FXML
	private TableColumn<Brew, String> RecipeName;
	@FXML
	private TableColumn<Brew, String> brewDate;

	@FXML
	private TextArea note;
	
	@FXML
	public void toMainPage(ActionEvent event) {
		Start.getInstance().mainPage();
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		brewList.clear();
		
		ArrayList<Brew>brewArrayList = new ArrayList<Brew>();
		Database db = new Database();
		brewArrayList = db.getAllBrews();
		
		for(Brew temp:brewArrayList) {
			brewList.add(temp);
		}
		
		System.out.println(brewArrayList.toArray());
		System.out.println(brewList.toArray());
		
		brewId.setCellValueFactory(new PropertyValueFactory<Brew, String>("ID"));
		//render the ID
//		brewId.setCellFactory((col)->{
//			TableCell<Brew, String> cell = new TableCell<Brew, String>(){
//				@Override
//				public void updateItem(String item, boolean empty) {
//					super.updateItem(item, empty);
//					this.setText(null);
//					this.setGraphic(null);
//					
//					if(!empty) {
//						int rowIndex = this.getIndex()+1;
//						this.setText(String.valueOf(rowIndex));
//					}
//				}
//			};
//			return cell;
//		});
		
		RecipeName.setCellValueFactory(new PropertyValueFactory<Brew, String>("Recipe Name"));
		brewDate.setCellValueFactory(new PropertyValueFactory<Brew, String>("DATE"));
		
	}

}
