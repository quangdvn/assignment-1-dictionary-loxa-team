package MyFX;

import Database.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBoxAdd {
	public static void display(String title) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		AnchorPane root = new AnchorPane();
		root.setPrefSize(599.0, 250.0);
		Scene scene = new Scene(root,599,250);
		
		CTextArea target = new CTextArea(root,"");
		target.Init(14.0, 14.0, 252.0, 184.0);
		target.getTextArea().setPromptText("Word target here...");
		CTextArea explain = new CTextArea(root,"");
		explain.getTextArea().setPromptText("Word explain here...");
		explain.Init(333.0, 14.0, 252.0, 184.0);
		
		CButton button = new CButton(root,"button");
		button.Init(267.0, 73.0, 65.0, 68.0, "Okie");
		button.setImage("google.png", 30, 40);
		button.setCSS(scene);
		
		CButton exit = new CButton(root,"button-quit");
		exit.setImage("quit.png", 30, 40);
		exit.Init(14.0, 200.0, 570.0, 40.0, "Exit");
		exit.setCSS(scene);
		
		
		
		exit.getButton().setOnAction(e -> window.close());
		
		
		button.getButton().setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent event) {
		    	   Controller.addWord(target.getTextArea().getText(), explain.getTextArea().getText());
		    	   window.close();
		       }
		});
		
		
		window.setTitle(title);
		window.setScene(scene);
		window.showAndWait();
	}
	
}
