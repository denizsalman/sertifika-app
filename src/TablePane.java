import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TablePane extends VBox {
    private TableView<Person> tableView = new TableView<>();
    final Label label = new Label("Eğitim Alan Çalışanın Bilgileri");
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(

            );
    private Button btImport = new Button("Excelden Aktar");
    
    public TablePane() {
        setSpacing(5);
        Font fontHeader = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 15);
        label.setFont(fontHeader);

        tableView.setEditable(true);
       
        Stage stage = new Stage();
        
	    btImport.setOnAction(event -> {
	    	   FileChooser fileChooser = new FileChooser();
	    	   fileChooser.setTitle("Excel Dosyası Seç");
	    	   FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
	    			   "Excel files (*.xlsx)","*.xlsx");
	    	   fileChooser.getExtensionFilters().add(extensionFilter);
	    	   File fileImport = fileChooser.showOpenDialog(stage);
	    	   excelImport(fileImport);
	    	   
	      });
        

        getChildren().addAll(label, tableView);
        setAlignment(Pos.CENTER);

        TableColumn firstNameCol = new TableColumn("Ad");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Soyad");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn gorevUnvanCol = new TableColumn("Görev Ünvanı");
        gorevUnvanCol.setMinWidth(200);
        gorevUnvanCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("gorevUnvan"));
        tableView.getColumns().addAll(firstNameCol, lastNameCol, gorevUnvanCol);
        tableView.setItems(data);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Ad");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Soyad");
        final TextField addGorevUnvan = new TextField();
        addGorevUnvan.setMaxWidth(gorevUnvanCol.getPrefWidth());
        addGorevUnvan.setMinWidth(120);
        addGorevUnvan.setPromptText("Görev Ünvanı");

        HBox hb = new HBox();
        final Button addButton = new Button("Ekle");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                data.add(new Person(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addGorevUnvan.getText()
                ));
                addFirstName.clear();
                addLastName.clear();
                addGorevUnvan.clear();
            }
        });
        
        final Button deleteButton = new Button("Sil");
        deleteButton.setOnAction(event -> {
        	Person selectedItem = tableView.getSelectionModel().getSelectedItem();
        	tableView.getItems().remove(selectedItem);
        });
        
        hb.getChildren().addAll(addFirstName, addLastName, addGorevUnvan, addButton, deleteButton, btImport);
        getChildren().addAll(hb);

    }

    private void excelImport(File fileImport) {
		try {
			FileInputStream inputStream = new FileInputStream(fileImport);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int row;
			int col;
			
			for(row = 1;row < sheet.getPhysicalNumberOfRows(); row++) {
				ArrayList<String> personFields = new ArrayList<>();
				for(col = 0; col < sheet.getRow(row).getLastCellNum(); col++) {
					Cell cell = sheet.getRow(row).getCell(col);
					personFields.add(cell.getStringCellValue());
				}
				data.add(new Person(personFields.get(0), personFields.get(1), personFields.get(2)));
			}
			
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public ObservableList<Person> getData() {
        return data;
    }


}
