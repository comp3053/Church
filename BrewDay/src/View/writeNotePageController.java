package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.webkit.ThemeClient;

import Model.Brew;
import Model.Database;
import Model.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
	
	@FXML
	public void saveNote(ActionEvent event) {
		Brew choosenBrew = brewTableView.getSelectionModel().getSelectedItem();
		String content = note.getText();
		
		Database database = new Database();
		
		if (choosenBrew.getNote() == null) {
			Note tempNote = new Note("Title", choosenBrew.getRecipe().getName(), content);
			choosenBrew.setNote(tempNote);
			System.out.println("Null");
			database.addBrewNote(choosenBrew);
		}
		else {
			Note tempNote = choosenBrew.getNote();
			tempNote.setContent(content);
			System.out.println("Already");
			database.updateBrewNote(choosenBrew);
		}
		
		Start.getInstance().writeNotePage();
	}
	

	public void showNote(Brew brew) {
		Note tempNote = brew.getNote();
		System.out.println("Brew id:"+brew.getID());
		
		if (tempNote == null) {
			//System.out.println("Null");
			note.setText("Brew id:"+brew.getID());
		}
		else {
			note.setText(tempNote.getContent());
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		brewList.clear();
		
		ArrayList<Brew>brewArrayList = new ArrayList<Brew>();
		Database db = new Database();
		brewArrayList = db.getBrewList();
		
		for(Brew temp:brewArrayList) {
			//System.out.println("Brew:"+temp.getRecipeName());
			brewList.add(temp);
		}
		
		//initialize tableView
		brewTableView.setRowFactory( tv -> {
		    TableRow<Brew> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Brew rowData = row.getItem();
		            showNote(rowData);
		        }
		    });
		    return row ;
		});
		
		//brewId.setCellValueFactory(new PropertyValueFactory<Brew, String>("ID"));
		//render the ID
		brewId.setCellFactory((col)->{
			TableCell<Brew, String> cell = new TableCell<Brew, String>(){
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
		
		RecipeName.setCellValueFactory(new PropertyValueFactory<Brew, String>("RecipeName"));
		brewDate.setCellValueFactory(new PropertyValueFactory<Brew, String>("Date"));
		
		brewTableView.setItems(brewList);
	}

}
