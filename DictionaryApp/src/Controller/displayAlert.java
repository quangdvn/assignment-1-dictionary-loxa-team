package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class displayAlert {

    public void succeedAlert(String infor) {
        Alert alert  = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(infor);

        Stage stage;
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("Image/Succeed.png"));

        alert.showAndWait();
    }

    public  void confirmAlert() {
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to delete this word ?");
            ButtonType Yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType No = new ButtonType("No", ButtonBar.ButtonData.NO);

            Stage stage;
            stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Image/Succeed.png"));

            alert.getButtonTypes().setAll(Yes, No);
            Optional<ButtonType> result = alert.showAndWait();
        }

    }
}
