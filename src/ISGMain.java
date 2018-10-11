import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.controlsfx.control.Notifications;

import java.io.*;
import java.util.ArrayList;

public class ISGMain extends Application {
    BorderPane borderPane = new BorderPane();
    PanelLeft panelLeft = new PanelLeft();
    PanelRight panelRight = new PanelRight();
    TablePane tablePane = new TablePane();
    private boolean btCalisanBilgiPressed = false;
    @Override
    public void start(Stage primaryStage) {
        borderPane.setPadding(new Insets(20));

        Text text = new Text("YAZDIR");
        text.setFill(Color.WHITE);

        Button button = new Button("", text);
        button.setStyle("-fx-background-color: #2196f3; " +
                "-fx-font-size: 20; " +
                "-fx-background-radius: 5;");

        Button btCalisanBilgi = new Button("Eğitim Alan Personeller");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(panelLeft, btCalisanBilgi);

        borderPane.setLeft(vBox);
        borderPane.setRight(panelRight);
        borderPane.setBottom(button);
        BorderPane.setAlignment(button, Pos.CENTER);

        Scene sceneTable = new Scene(tablePane);

        btCalisanBilgi.setOnAction(event -> {
            btCalisanBilgiPressed = true;
            Stage stageTable = new Stage();
            stageTable.initModality(Modality.APPLICATION_MODAL);
            stageTable.setScene(sceneTable);
            stageTable.showAndWait();
        });

        button.setOnAction(event -> {
            if (!btCalisanBilgiPressed) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("UYARI!!!");
                alert.setHeaderText("DİKKAT!");
                alert.setContentText("Öncelikle \"Eğitim Alan Personelleri Seç\" butonuna tıklayarak personel eklemelisiniz.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            }
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Kaydedilecek Konum Seç");
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            ObservableList<Person> persons = tablePane.getData();

            for (int i = 0; i < persons.size(); i++) {
                excelRapor(persons.get(i), selectedDirectory);
            }

            if (selectedDirectory != null) {
                //Success Notification\\
                ImageView imageView = new ImageView("img/confirm.png");
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                Notifications notificationBuilder = Notifications.create()
                        .title("İşlem Başarılı")
                        .text("  " + selectedDirectory + " klasörüne kaydedildi")
                        .graphic(imageView)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                //End of Success Notification\\
            } else {
                ImageView imageView = new ImageView("img/error.png");
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                Notifications notificationBuilder = Notifications.create()
                        .title("İşlem Başarısız")
                        .text("Kayıt yapılamadı. Kayıt edilecek klasörün doğru seçildiğine emin olun.")
                        .graphic(imageView)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.show();
            }


        });

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Boğaziçi Akademi - OSGB Sertifika Programı");
        primaryStage.show();
    }

    private void excelRapor(Person person, File selectedDirectory) {
        try {
            File file = new File("Bogazici.xls");
            FileInputStream inputStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            BilgiForm form = panelLeft.createForm();

            Cell cell = null;
            CellStyle cellStyle = workbook.createCellStyle();
            Font fontBold = workbook.createFont();
            fontBold.setFontName("Calibri");
            fontBold.setFontHeightInPoints((short) 14);
            fontBold.setBold(true);
            cellStyle.setFont(fontBold);

            cell = sheet.getRow(2).getCell('L' - 'A');
            cell.setCellValue(person.getFirstName() + " " + person.getLastName());
            cell.setCellStyle(cellStyle);
            cell = sheet.getRow(3).getCell('L' - 'A');
            cell.setCellValue(person.getGorevUnvan());
            cell.setCellStyle(cellStyle);
            cell = sheet.getRow(8).getCell('L' - 'A');
            cell.setCellValue(form.getEgitimTarih1() + " - " + form.getEgitimTarih2());
            cell = sheet.getRow(9).getCell('L' - 'A');
            cell.setCellValue(form.getEgitimSure());
            cell = sheet.getRow(10).getCell('L' - 'A');
            cell.setCellValue(form.getEgitimTuru());
            cell = sheet.getRow(11).getCell('L' - 'A');
            cell.setCellValue(form.getAdSoyad1());
            cell = sheet.getRow(11).getCell('X' - 'A');
            cell.setCellValue(form.getAdSoyad2());
            cell = sheet.getRow(12).getCell('L' - 'A');
            cell.setCellValue(form.getGorevUnvan1());
            cell = sheet.getRow(12).getCell('X' - 'A');
            cell.setCellValue(form.getGorevUnvan2());
            cell = sheet.getRow(16).getCell(0);
            cell.setCellValue(form.getKurumUnvan());
            cell = sheet.getRow(18).getCell(0);
            cell.setCellValue(form.getIsyeriUnvan());
            cell = sheet.getRow(19).getCell('N' - 'A');
            cell.setCellValue(form.getIsverenAdSoyad());

            ArrayList<String> listGenelKonular = panelRight.getGenelKonular();
            ArrayList<String> listSaglikKonular = panelRight.getSaglikKonular();
            ArrayList<String> listTeknikKonular = panelRight.getTeknikKonular();

            int colNumber = 23;
            cell = sheet.getRow(colNumber).getCell(0);
            cell.setCellValue("Genel Konular");
            for (int i = 0; i < listGenelKonular.size(); i++) {
                cell = sheet.getRow(colNumber++).getCell('G' - 'A');
                cell.setCellValue(listGenelKonular.get(i));
            }

            cell = sheet.getRow(++colNumber).getCell(0);
            cell.setCellValue("Sağlık Konuları");
            for (int i = 0; i < listSaglikKonular.size(); i++) {
                cell = sheet.getRow(colNumber++).getCell('G' - 'A');
                cell.setCellValue(listSaglikKonular.get(i));
            }

            cell = sheet.getRow(++colNumber).getCell(0);
            cell.setCellValue("Teknik Konuları");
            for (int i = 0; i < listTeknikKonular.size(); i++) {
                cell = sheet.getRow(colNumber++).getCell('G' - 'A');
                cell.setCellValue(listTeknikKonular.get(i));
            }
            cell = sheet.getRow(colNumber++).getCell('G' - 'A');
            cell.setCellValue("k  o  n  u     s  o  n  u  -----------------------------------------------------------------------------------------------------");


            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(selectedDirectory +
                    "/Sertifika-"+ person.getFirstName() + ".xls");
            workbook.write(outputStream);
            outputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
}
