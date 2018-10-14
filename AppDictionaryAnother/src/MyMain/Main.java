package MyMain;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	public void start(Stage primaryStage) throws Exception {  
		this.window = primaryStage;

		this.window.setScene(Handle.primaryScene());  
        primaryStage.setTitle("Dictionary Application");  
        primaryStage.show();  
    }
	public static void main(String []args) {
    	launch(args);
    }

}
