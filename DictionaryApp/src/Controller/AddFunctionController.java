package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AddFunctionController {

    @FXML
    private TextField myNewWord;
    @FXML
    private TextArea myNewDescription;

    public void addWord() {
        String newWord = myNewWord.getText();
        String newDescription = myNewDescription.getText();
        try {
            MainController mainController = new MainController();
            displayAlert displayAlert = new displayAlert();
            mainController.AddFunction(newWord,newDescription);
            displayAlert.succeedAlert("Word has been added successfully !!");
            //mainController.reloadDataBase();
        }
        catch (Exception Err) {
            Err.printStackTrace();
        }


    }
}
