package Controller;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private DisplayAlert displayAlert = new DisplayAlert();

    @FXML
    private BorderPane mainScene;
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
        //String getSelectedWord = myListView.getSelectionModel().getSelectedItem();
        myListView.setOnMouseClicked(Str -> {
            try {
                String Query = "SELECT html FROM av WHERE word = ?";
                preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setString(1,  myListView.getSelectionModel().getSelectedItem());
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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 500, 400));
        stage.getIcons().add(new Image("/Image/Pencil.png"));
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

    public void showUpdateFunction() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Design/UpdateFunction.fxml"));
        Stage stage = new Stage();
        stage.setTitle("UpdateFunction");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 700, 500));
        stage.getIcons().add(new Image("/Image/Update.png"));
        stage.show();
    }

    public void DeleteFunction(){
        String getSelectedWord = myListView.getSelectionModel().getSelectedItem();
        if(getSelectedWord != null) {
            if(displayAlert.confirmAlert()) {
                try {
                    String Sql = "DELETE FROM av WHERE word = ?";
                    preparedStatement = connection.prepareStatement(Sql);
                    preparedStatement.setString(1, getSelectedWord);
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
        }
        else displayAlert.warningAlert("Please choose a word to Delete !!!");
    }

    public void Pronunciation() {
        VoiceController voiceController = new VoiceController();
        String getSelectedWord = myListView.getSelectionModel().getSelectedItem();
        if(getSelectedWord != null) {
            try {
                String Sql = "SELECT word FROM av WHERE word = ?";
                preparedStatement = connection.prepareStatement(Sql);
                preparedStatement.setString(1, getSelectedWord);
                preparedStatement.execute();
                String text = myListView.getSelectionModel().getSelectedItem();
                voiceController.Speak(text);
            }
            catch (Exception Err) {
                Err.printStackTrace();
            }
        }
        else displayAlert.warningAlert("Please choose a word to Pronunce !!!");
    }

    public void addFavorite() throws Exception{
        String getSelectedWord = myListView.getSelectionModel().getSelectedItem();
        if(getSelectedWord != null) {
            String Query1 = "SELECT html FROM av WHERE word = ?";
            preparedStatement = connection.prepareStatement(Query1);
            preparedStatement.setString(1, getSelectedWord);
            resultSet = preparedStatement.executeQuery();
            String getSelectdHTML = resultSet.getString(1);

            String Query2 = "INSERT INTO favoriteword (word, html) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(Query2);
            preparedStatement.setString(1, getSelectedWord);
            preparedStatement.setString(2,getSelectdHTML);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Succeed");
        }
        else  displayAlert.warningAlert("Please choose a word to set as Favorite !!!");
    }

    private void FadeIn(Parent root) {
        FadeTransition FadeIn = new FadeTransition(Duration.seconds(0.5),root);
        FadeIn.setFromValue(0.0);
        FadeIn.setToValue(1.0);
        FadeIn.play();
    }

    public void googleLoadUp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Design/GoogleTranslate.fxml"));
        FadeIn(root);
        mainScene.setCenter(root);
    }

    public void mainLoadUp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Design/MainDictionary.fxml"));
        FadeIn(root);
        mainScene.setCenter(root);
    }

    public void favoriteLoadUp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Design/Favorite.fxml"));
        FadeIn(root);
        mainScene.setCenter(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataBase();
    }
}
