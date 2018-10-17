package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateFunctionController {
    private Connection connection = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @FXML
    private TextField fixedWord;
    @FXML
    private HTMLEditor fixedDescrip;

    public void selectWord() {

    }
}
