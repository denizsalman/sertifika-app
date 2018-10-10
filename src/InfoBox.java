import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InfoBox {
    private static Stage window = new Stage();

    public static void success(String title, String message) {
        window.setTitle(title);

        Label label = new Label(message);
        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
}