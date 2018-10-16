package tr.com.bogaziciakademi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class PanelLeft extends GridPane {
    private Label labelIsyeriUnvan = new Label("İŞYERİ ÜNVANI: ");
    private TextField tfIsyeriUnvan = new TextField();
    private DatePicker dpEgitimTarihi1 = new DatePicker();
    private DatePicker dpEgitimTarihi2 = new DatePicker();
    private TextField tfEgitimSuresi = new TextField();
    private RadioButton rbOrgunEgitim = new RadioButton("Örgun Eğitim");
    private RadioButton rbUzaktanEgitim = new RadioButton("Uzaktan Eğitim");
    private Label labelDigerEgitim = new Label("Diğer Eğitim Bilgileri:");
    private TextField tfDigerEgitim = new TextField();
    private Label labelEgitimci1 = new Label("EĞİTİMCİ 1");
    private ComboBox<String> cbEgitimci1 = new ComboBox<>();
    private Label labelEgitimci2 = new Label("EĞİTİMCİ 2");
    private ComboBox<String> cbEgitimci2 = new ComboBox<>();
    private Label labelIsveren = new Label("İŞVEREN / İŞVEREN VEKİLİ");
    private TextField tfAdSoyadIsveren = new TextField();
    private Label labelKurum = new Label("VARSA EĞİTİMİ VEREN KURUMUN/KURULUŞUN");
    private TextField tfUnvan = new TextField();
    private ToggleGroup group = new ToggleGroup();
    private String[] items = {"Ela Subaşı Ersöz", "Eren Salman", "Murat Uyanık",
    		"Mehmet Giray Ersöz", "Bülent Genç"};
    private ObservableList<String> observableList = FXCollections.observableArrayList(items);
    private String[] egitimciAdSoyad = new String[5];
    private String[] egitimciGorevUnvan = new String[5];
    
    public PanelLeft() {
        setPadding(new Insets(30, 20, 20, 20));
        setVgap(5);
        
        
        
        for(int i = 0; i < 5; i++) {
        	egitimciAdSoyad[i] = items[i].toUpperCase();
        }
        
        
        egitimciGorevUnvan[0] = "İŞ GÜVENLİĞİ UZMANI / BELGE NO:129996";
        egitimciGorevUnvan[1] = "İŞ GÜVENLİĞİ UZMANI / BELGE NO:146800";
        egitimciGorevUnvan[2] = "İŞ GÜVENLİĞİ UZMANI / BELGE NO:148041";
        egitimciGorevUnvan[3] = "İŞ YERİ HEKİMİ / BELGE NO:12653";
        egitimciGorevUnvan[4] = "İŞ YERİ HEKİMİ / BELGE NO:10648/94";        
        
       
        cbEgitimci1.setItems(observableList);
        cbEgitimci1.setValue("");
        cbEgitimci2.setItems(observableList);
        cbEgitimci2.setValue("");
        
        Font fontAltBaslik = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 15);
        labelEgitimci1.setFont(fontAltBaslik);
        labelEgitimci2.setFont(fontAltBaslik);
        labelKurum.setFont(fontAltBaslik);
        labelIsveren.setFont(fontAltBaslik);
        rbOrgunEgitim.setSelected(true);
        rbOrgunEgitim.setToggleGroup(group);
        rbUzaktanEgitim.setToggleGroup(group);

        int row = 0;
        add(labelIsyeriUnvan, 0, row);
        add(tfIsyeriUnvan, 1, row++);
        add(new Label("Eğitim Başlangıç:"), 0,row);
        add(dpEgitimTarihi1, 1, row++);
        add(new Label("Eğitim Bitiş:"), 0, row);
        add(dpEgitimTarihi2, 1, row++);
        add(new Label("Eğitim Süresi:"), 0, row);
        add(tfEgitimSuresi, 1, row++);
        add(rbOrgunEgitim, 0, row);
        add(rbUzaktanEgitim, 1, row++);
        add(labelDigerEgitim, 0,row);
        add(tfDigerEgitim, 1, row++);

        add(labelEgitimci1, 0, row++);
        add(cbEgitimci1, 0, row++, 2, 1);
    

        add(labelEgitimci2, 0, row++);
        add(cbEgitimci2, 0, row++, 2, 1);
 

        add(labelIsveren, 0, row++, 2, 1);
        add(new Label("Ad Soyad:"), 0, row);
        add(tfAdSoyadIsveren, 1, row++);
        add(labelKurum, 0, row++,2,1);
        add(new Label("Ünvanı: "), 0, row);
        add(tfUnvan, 1, row);

    }

    public BilgiForm createForm() {
        BilgiForm form = new BilgiForm();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        form.setIsyeriUnvan(tfIsyeriUnvan.getText());
        if (dpEgitimTarihi1.getValue() != null)
            form.setEgitimTarih1(dateFormat.format(
                    Date.valueOf(dpEgitimTarihi1.getValue())));
        if (dpEgitimTarihi2.getValue() != null)
            form.setEgitimTarih2(dateFormat.format(
                    Date.valueOf(dpEgitimTarihi2.getValue())));
        form.setEgitimSure(tfEgitimSuresi.getText());
        RadioButton chk = (RadioButton) group.getSelectedToggle();
        form.setEgitimTuru(chk.getText());
        form.setDigerIsg(tfDigerEgitim.getText());
        form.setIsverenAdSoyad(tfAdSoyadIsveren.getText());
        form.setKurumUnvan(tfUnvan.getText());
        
        int index;
        if (observableList.contains(cbEgitimci1.getValue())) {
	        index = observableList.indexOf(cbEgitimci1.getValue());
	        form.setAdSoyad1(egitimciAdSoyad[index]);
	        form.setGorevUnvan1(egitimciGorevUnvan[index]);
        }
        
        if (observableList.contains(cbEgitimci2.getValue())) {
            index = observableList.indexOf(cbEgitimci2.getValue());
            form.setAdSoyad2(egitimciAdSoyad[index]);
            form.setGorevUnvan2(egitimciGorevUnvan[index]);
		}

        return form;
    }
    
    
}
