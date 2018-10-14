package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import MyFX.CListView;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;

public class Controller {
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static Connection connection = DatabaseConnection.getConnection();
    
    /**
     * reset database
     */
    public static void refreshDatabase() {
        String query = "SELECT word FROM av";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	resultSet = preparedStatement.executeQuery();

            CListView.items.clear();
            while (resultSet.next()) {
            	CListView.items.add(resultSet.getString(1));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Load Databas
     * @return
     */
    public static TreeSet<String> loadDataBase() {
    	String Sql = "SELECT word FROM av";
    	TreeSet<String> arr = new TreeSet<String>();
        try {
            preparedStatement = connection.prepareStatement(Sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                arr.add(resultSet.getString(1));

            }
        } catch (Exception Err) {
            System.err.print(Err);
        }
		
        return arr;
    }
    /**
     * Display listView
     * @param listWord
     * @param webView
     */
    public static void displaySelectedItem(ListView<String> listWord, WebView webView, String word) {
        String query = "SELECT html FROM av WHERE word=?";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	//preparedStatement.setString(1, listWord.getSelectionModel().getSelectedItem());
        	preparedStatement.setString(1, word);
        	resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	webView.getEngine().loadContent(resultSet.getString(1));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    /**
     * Delete Word
     * @param listWord
     * @param webView
     */
    public static void deleteWord(ListView<String> listWord, WebView webView){
        try {
            String selected = listWord.getSelectionModel().getSelectedItem();
            if(selected != null){
                System.out.println(selected);

                if (selected != null) {
                    String query = "DELETE FROM av WHERE word=?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, selected);
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    resultSet.close();
                    refreshDatabase();
                    webView.getEngine().loadContent("");
                }
            }
            else{
                //showAlert.AlertInfo("Please choose a word you want to delete!!");
            	System.out.println("Please choose a word you want to delete!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Add word
     * @param Word
     * @param Explain
     */
    public static void addWord(String Word, String Explain){
        String query = "INSERT INTO av (word, html) VALUES(?,?)";
        try{
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, Word);
            preparedStatement.setString(2, Explain);
            preparedStatement.execute();
            preparedStatement.close();
            refreshDatabase();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
}
