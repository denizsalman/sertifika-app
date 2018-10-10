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

public class TablePane extends VBox {
    private TableView<Person> tableView = new TableView<>();
    final Label label = new Label("Eğitim Alan Çalışanın Bilgileri");
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(

            );

    public TablePane() {
        setSpacing(5);
        Font fontHeader = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 15);
        label.setFont(fontHeader);

        tableView.setEditable(true);


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

        hb.getChildren().addAll(addFirstName, addLastName, addGorevUnvan, addButton);
        getChildren().addAll(hb);

    }

    public ObservableList<Person> getData() {
        return data;
    }


}
