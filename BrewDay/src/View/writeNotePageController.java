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
		Brew choosenBrew = null;
		String content = note.getText();
		try {
			choosenBrew = brewTableView.getSelectionModel().getSelectedItem();
			if(choosenBrew == null) {
				Start.getInstance().warningMsg("Invalid Input", "Please select a brew to add note!");
				Start.getInstance().writeNotePage();
				return;
			}
		} catch (Exception e) {
			Start.getInstance().warningMsg("Invalid Input", "Please select a brew to add note!");
			Start.getInstance().writeNotePage();
			return;
		}

		Database database = new Database();
		//if the brew has no note, attach a new note to the brew
		if (choosenBrew.getNote() == null) {
			//check the user input content for note
			if(content.equals("")) {
				Start.getInstance().warningMsg("Invalid Input", "Please input the note content!");
				Start.getInstance().writeNotePage();
				return;
			}
			else {
				Note tempNote = new Note("Title", choosenBrew.getRecipe().getName(), content);
				choosenBrew.setNote(tempNote);
				System.out.println("Null");
				database.addBrewNote(choosenBrew);
			}
		}
		//the brew has a note already, update the note content
		else {
			if(content.equals("")) {
				Start.getInstance().warningMsg("Invalid Input", "Please input the note content!");
				Start.getInstance().writeNotePage();
				return;
			}
			Note tempNote = choosenBrew.getNote();
			tempNote.setContent(content);
			System.out.println("Already");
			database.updateBrewNote(choosenBrew);
			Start.getInstance().confirmMsg("Success!", "The note has been added!");
		}

		Start.getInstance().writeNotePage();
	}

	//display the note content
	public void showNote(Brew brew) {
		Note tempNote = brew.getNote();
		if (tempNote == null||tempNote.getContent().equals("")) {
			//System.out.println("Null");
			Start.getInstance().warningMsg("Invalid Input", "This brew has no note, please write a note for it");
			note.setText("");
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
		brewArrayList = db.getBrewListName();

		for(Brew temp:brewArrayList) {
			System.out.println("Brew:"+temp.getRecipeName());
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
