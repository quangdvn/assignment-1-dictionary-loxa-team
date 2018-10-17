package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

class DisplayAlert {

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

    public boolean confirmAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure to delete this word ?");

        Stage stage;
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("Image/Confirm.jpg"));

        Optional<ButtonType> result = alert.showAndWait();
        if( result.get() == ButtonType.OK ) return true;
        else return false;
    }

    public void warningAlert(String Infor) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(Infor);

        Stage stage;
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("Image/Warning.png"));

        alert.showAndWait();
    }
}
