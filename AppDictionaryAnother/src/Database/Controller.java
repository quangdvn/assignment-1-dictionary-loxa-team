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
    protected void refreshDatabase() {
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
    
    public static void displaySelectedItem(ListView<String> listWord, WebView webView) {
        String query = "SELECT html FROM av WHERE word=?";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, listWord.getSelectionModel().getSelectedItem());
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
    
}
