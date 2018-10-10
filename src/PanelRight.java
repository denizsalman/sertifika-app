import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class PanelRight extends VBox {
    private Label label = new Label("İSG Temel Eğitim Konuları\n");
    private Label labelGenelKonular = new Label("Genel Konular");
    private Label labelSaglikKonular = new Label("Sağlık Konuları");
    private Label labelTeknikKonular = new Label("Teknik Konular");
    private CheckBox[] cbGenelKonular = new CheckBox[4];
    private CheckBox[] cbSaglikKonular  = new CheckBox[5];
    private CheckBox[] cbTeknikKonular= new CheckBox[11];

    public PanelRight() {
        setPadding(new Insets(0, 20, 20, 20));
        Font fontBaslik = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontAltBaslik = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 15);
        label.setFont(fontBaslik);
        labelGenelKonular.setFont(fontAltBaslik);
        labelSaglikKonular.setFont(fontAltBaslik);
        labelTeknikKonular.setFont(fontAltBaslik);
        getChildren().addAll(label);

        getChildren().add(labelGenelKonular);
        genelKonularDoldur();
        konularEkle(cbGenelKonular);
        getChildren().add(labelSaglikKonular);
        saglikKonularDoldur();
        konularEkle(cbSaglikKonular);
        getChildren().add(labelTeknikKonular);
        teknikKonularDoldur();
        konularEkle(cbTeknikKonular);
    }

    private void teknikKonularDoldur() {
        cbTeknikKonular[0] = new CheckBox("Kimyasal, Fiziksel Ve Ergonomik Risk Etmenleri");
        cbTeknikKonular[1] = new CheckBox("Elle Kaldırma Ve Taşıma");
        cbTeknikKonular[2] = new CheckBox("Parlama, Patlama, Yangın Ve Yangından Korunma");
        cbTeknikKonular[3] = new CheckBox("İş Ekipmanlarının Güvenli Kullanımı");
        cbTeknikKonular[4] = new CheckBox("Ekranlı Araçlarla Çalışma");
        cbTeknikKonular[5] = new CheckBox("Elektrik, Tehlikeleri, Riskleri Ve Önlemleri");
        cbTeknikKonular[6] = new CheckBox("İş Kazalarının Sebepleri Ve Korunma Prensipleri İle Tekniklerinin Uygulanması");
        cbTeknikKonular[7] = new CheckBox("Güvenlik Ve Sağlık İşaretleri");
        cbTeknikKonular[8] = new CheckBox("Kişisel Koruyucu Donanım Kullanımı");
        cbTeknikKonular[9] = new CheckBox("İş Sağlığı Ve Güvenliği Genel Kuralları Ve Güvenlik Kültürü");
        cbTeknikKonular[10] = new CheckBox("Tahliye Ve Kurtarma");
    }

    private void saglikKonularDoldur() {
        cbSaglikKonular[0] = new CheckBox("Meslek Hastalıklarının Sebepleri");
        cbSaglikKonular[1] = new CheckBox("Hastalıktan Korunma Prensipleri Ve Korunma Tekniklerinin Uygulanması");
        cbSaglikKonular[2] = new CheckBox("Biyolojik Ve Psikososyal Risk Etmenleri");
        cbSaglikKonular[3] = new CheckBox("İlkyardım");
        cbSaglikKonular[4] = new CheckBox("Tütün Ürünlerinin Zararları Ve Pasif Etkilenim");
    }

    private void genelKonularDoldur() {
        cbGenelKonular[0] = new CheckBox("Çalışma Mevzuatı İle İlgili Bilgiler");
        cbGenelKonular[1] = new CheckBox("Çalışanların Yasal Hak Ve Sorumlulukları");
        cbGenelKonular[2] = new CheckBox("İşyeri Temizliği ve Düzeni");
        cbGenelKonular[3] = new CheckBox("İş Kazası Ve Meslek Hastalığından Doğan Hukuki Sonuçlar");
    }

    public void konularEkle(CheckBox[] checkBoxes) {
        for (int i = 0; i < checkBoxes.length;i++) {
            getChildren().add(checkBoxes[i]);
        }
    }

    public ArrayList<String> getGenelKonular() {

        ArrayList<String> listGenelKonular = new ArrayList<>();
        for (int i = 0; i < cbGenelKonular.length; i++) {
            if (cbGenelKonular[i].isSelected()) {
                listGenelKonular.add(cbGenelKonular[i].getText());
            }
        }
        return listGenelKonular;
    }

    public ArrayList<String> getSaglikKonular() {

        ArrayList<String> listSaglikKonular = new ArrayList<>();
        for (int i = 0; i < cbSaglikKonular.length; i++) {
            if (cbSaglikKonular[i].isSelected()) {
                listSaglikKonular.add(cbSaglikKonular[i].getText());
            }
        }
        return listSaglikKonular;
    }

    public ArrayList<String> getTeknikKonular() {

        ArrayList<String> listTeknikKonular = new ArrayList<>();
        for (int i = 0; i < cbTeknikKonular.length; i++) {
            if (cbTeknikKonular[i].isSelected()) {
                listTeknikKonular.add(cbTeknikKonular[i].getText());
            }
        }
        return listTeknikKonular;
    }

    public void hello() {

    }
}
