package Controller;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class MainController implements Initializable {
    private Connection connection = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ObservableList<String> newItems = FXCollections.observableArrayList();
    private FilteredList<String> filteredDictionary = new FilteredList<>(newItems,Str -> true);
    private WebEngine webEngine = null;

    @FXML
    private ListView<String> myListView = new ListView<>();
    @FXML
    private TextField myTextField;
    @FXML
    private WebView myWebView;

    public void loadDataBase() {
        String Sql = "SELECT word FROM av ";
        try {
            preparedStatement = connection.prepareStatement(Sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newItems.add(resultSet.getString(1));
                myListView.setItems(newItems);
            }
        } catch (Exception Err) {
            System.err.print(Err);
        }
    }

    public void reloadDataBase() {
        String Sql = "SELECT word FROM av ";
        try {
            preparedStatement = connection.prepareStatement(Sql);
            resultSet = preparedStatement.executeQuery();
            newItems.clear();
            while (resultSet.next()) {
                newItems.add(resultSet.getString(1));
                myListView.setItems(newItems);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception Err) {
            System.err.print(Err);
        }
    }
    public void selectWord() {
        myListView.setOnMouseClicked(Str -> {
            try {

                String Query = "SELECT html FROM av WHERE word = ?";
                preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setString(1, (String) myListView.getSelectionModel().getSelectedItem());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    webEngine = myWebView.getEngine();
                    webEngine.loadContent(resultSet.getString(1));
                }
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException  Err) {
                System.out.println(Err.getMessage());
            }
        });
    }

    public void searchWord() {
        myListView.setItems(filteredDictionary);
        myTextField.textProperty().addListener( Obs -> {
            String filterInput = myTextField.getText();
            if(filterInput == null || filterInput.length() == 0) {
                filteredDictionary.setPredicate(Str -> true);
            }
            else {
                filteredDictionary.setPredicate(Str -> Str.toLowerCase().startsWith(filterInput));
            }
        });
        myListView.setItems(filteredDictionary);
    }

    public void showAddFunction() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Design/AddFunction.fxml"));
        Stage stage = new Stage();
        stage.setTitle("AddFunction");
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    }

    public void AddFunction(String newWord, String newDescription) {
        String Sql = "INSERT INTO av (word, html) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(Sql);
            preparedStatement.setString(1, newWord);
            preparedStatement.setString(2, newDescription);
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (Exception Err) {
            Err.printStackTrace();
        }
    }

    public void DeleteFunction(){
        displayAlert displayAlert = new displayAlert();
        displayAlert.confirmAlert();
        try {
            String Sql = "DELETE FROM av WHERE word = ?";
            preparedStatement = connection.prepareStatement(Sql);
            preparedStatement.setString(1, (String) myListView.getSelectionModel().getSelectedItem());
            preparedStatement.execute();
            webEngine = myWebView.getEngine();
            webEngine.loadContent("");
            preparedStatement.close();
            reloadDataBase();
        }
        catch (Exception Err) {
            Err.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataBase();
        selectWord();
    }
}
