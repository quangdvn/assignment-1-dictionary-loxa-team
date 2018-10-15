package MyFX;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NotInternet {
	public static void display() {
		Stage window = new Stage();
		window.setTitle("No Internet");
		StackPane root = new StackPane();
		root.setPrefSize(400.0, 200.0);
		CButton button = new CButton(root,"");
		button.Init(10.0, 100.0, "No Internet");
		button.getButton().setOnAction(e -> window.close());
		
		Scene scene = new Scene(root,400,200);
		window.setScene(scene);
		window.showAndWait();
	}
}
