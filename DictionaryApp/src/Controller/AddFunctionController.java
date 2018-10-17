package Controller;

import javafx.fxml.FXML;
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
            DisplayAlert displayAlert = new DisplayAlert();
            mainController.AddFunction(newWord,newDescription);
            displayAlert.succeedAlert("Word has been added successfully !!");
        }
        catch (Exception Err) {
            Err.printStackTrace();
        }
    }
}
