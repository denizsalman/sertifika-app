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
    private TextField tfAdSoyad1 = new TextField();
    private TextField tfGorev1 = new TextField();
    private Label labelEgitimci2 = new Label("EĞİTİMCİ 2");
    private TextField tfAdSoyad2 = new TextField();
    private TextField tfGorev2 = new TextField();
    private Label labelIsveren = new Label("İŞVEREN / İŞVEREN VEKİLİ");
    private TextField tfAdSoyadIsveren = new TextField();
    private Label labelKurum = new Label("VARSA EĞİTİMİ VEREN KURUMUN/KURULUŞUN");
    private TextField tfUnvan = new TextField();
    private ToggleGroup group = new ToggleGroup();


    public PanelLeft() {
        setPadding(new Insets(30, 20, 20, 20));
        setVgap(5);

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
        add(new Label("Ad Soyad:"), 0, row);
        add(tfAdSoyad1, 1, row++);
        add(new Label("Görev Ünvanı:"), 0, row);
        add(tfGorev1, 1, row++);

        add(labelEgitimci2, 0, row++);
        add(new Label("Ad Soyad:"), 0, row);
        add(tfAdSoyad2, 1, row++);
        add(new Label("Görev Ünvanı:"), 0, row);
        add(tfGorev2, 1, row++);

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
        form.setAdSoyad1(tfAdSoyad1.getText());
        form.setGorevUnvan1(tfGorev1.getText());
        form.setAdSoyad2(tfAdSoyad2.getText());
        form.setGorevUnvan2(tfGorev2.getText());
        form.setIsverenAdSoyad(tfAdSoyadIsveren.getText());
        form.setKurumUnvan(tfUnvan.getText());
        return form;
    }
}
