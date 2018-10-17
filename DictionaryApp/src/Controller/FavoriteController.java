package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {
    private Connection connection = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ObservableList<String> newItems = FXCollections.observableArrayList();
    private FilteredList<String> filteredDictionary = new FilteredList<>(newItems, Str -> true);
    private WebEngine webEngine = null;
    private DisplayAlert displayAlert = new DisplayAlert();

    @FXML
    private ListView<String> myFavoriteListView = new ListView<>();
    @FXML
    private TextField myFavoriteTextField;
    @FXML
    private WebView myFavoriteWebView;

    public void loadFavoriteDataBase() {
        String Sql = "SELECT word FROM favoriteword ";
        try {
            preparedStatement = connection.prepareStatement(Sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newItems.add(resultSet.getString(1));
                myFavoriteListView.setItems(newItems);
            }
        } catch (Exception Err) {
            System.err.print(Err);
        }
    }

    public void reloadFavoriteDataBase() {
        String Sql = "SELECT word FROM favoriteword ";
        try {
            preparedStatement = connection.prepareStatement(Sql);
            resultSet = preparedStatement.executeQuery();
            newItems.clear();
            while (resultSet.next()) {
                newItems.add(resultSet.getString(1));
                myFavoriteListView.setItems(newItems);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception Err) {
            System.err.print(Err);
        }
    }

    public void selectFavoriteWord() {
        String getSelectedWord = myFavoriteListView.getSelectionModel().getSelectedItem();
        try {
            String Query = "SELECT html FROM favoriteword WHERE word = ?";
            preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setString(1, getSelectedWord );
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                webEngine = myFavoriteWebView.getEngine();
                webEngine.loadContent(resultSet.getString(1));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException Err) {
            System.out.println(Err.getMessage());
        }
    }

    public void deleteFavorite(){
        String getSelectedWord = myFavoriteListView.getSelectionModel().getSelectedItem();
        if(getSelectedWord != null) {
            if(displayAlert.confirmAlert()) {
                try {
                    String Sql = "DELETE FROM favoriteword WHERE word = ?";
                    preparedStatement = connection.prepareStatement(Sql);
                    preparedStatement.setString(1, getSelectedWord);
                    preparedStatement.execute();
                    webEngine = myFavoriteWebView.getEngine();
                    webEngine.loadContent("");
                    preparedStatement.close();
                    reloadFavoriteDataBase();
                }
                catch (Exception Err) {
                    Err.printStackTrace();
                }
            }
        }
        else displayAlert.warningAlert("Please choose a word to remove !!!");
    }

    public void favoritePronunciation() {
        VoiceController voiceController = new VoiceController();
        String getSelectedWord = myFavoriteListView.getSelectionModel().getSelectedItem();
        if(getSelectedWord != null) {
            try {
                String Sql = "SELECT word FROM favoriteword WHERE word = ?";
                preparedStatement = connection.prepareStatement(Sql);
                preparedStatement.setString(1, getSelectedWord);
                preparedStatement.execute();
                String text = myFavoriteListView.getSelectionModel().getSelectedItem();
                voiceController.Speak(text);
            }
            catch (Exception Err) {
                Err.printStackTrace();
            }
        }
        else displayAlert.warningAlert("Please choose a word to Pronunce !!!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFavoriteDataBase();
    }
}
