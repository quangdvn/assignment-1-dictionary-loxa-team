package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleTranslateController implements Initializable{

        @FXML
        private WebView myGG;
        @FXML
        private WebEngine webEngine;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            LoadGG();
        }
        public void LoadGG() {
            try {
                if (InternetConnection.IsConnecting(2000)) {
                    webEngine = myGG.getEngine();
                    webEngine.load("https://translate.google.com/?hl=vi");
                } else {
                    DisplayAlert displayAlert = new DisplayAlert();
                    displayAlert.succeedAlert("Cant connect to the Net !!!!");
                }
            }
            catch (Exception Err) {
                Err.printStackTrace();
            }
        }
}
