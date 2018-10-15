package MyFX;

import java.io.IOException;

import APIGoogle.GoogleTranslate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBoxTranslate {
	public static String lang;
	public static void display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		AnchorPane root = new AnchorPane();
		root.setPrefSize(600.0, 273.0);
		
		Scene scene = new Scene(root,600,273);
		
		ObservableList<String> listLeft = FXCollections.observableArrayList("Document");
		ChoiceBox<String> choiceBoxLeft = new ChoiceBox<>();
		 

		choiceBoxLeft.setPrefSize(252.0, 31.0);
		choiceBoxLeft.setLayoutX(14.0);          choiceBoxLeft.setLayoutY(23.0);
		choiceBoxLeft.setItems(listLeft);
		choiceBoxLeft.getSelectionModel().selectFirst();
 
		choiceBoxLeft.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                	
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                    	
                    }
                });
        
		root.getChildren().addAll(choiceBoxLeft);
		
		
		ObservableList<String> listRight = FXCollections.observableArrayList(
               "VI", "EN", "JA");
		ChoiceBox<String> choiceBoxRight = new ChoiceBox<>();
		 

		choiceBoxRight.setPrefSize(252.0, 31.0);
		choiceBoxRight.setLayoutX(334.0);          choiceBoxRight.setLayoutY(23.0);
		choiceBoxRight.setItems(listRight);
		choiceBoxRight.getSelectionModel().selectFirst();
 
		CButton button = new CButton(root,"button");
		
		choiceBoxRight.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                    	AlertBoxTranslate.lang = listRight.get(new_value.intValue()).toLowerCase();
                    }
                });
        
		root.getChildren().addAll(choiceBoxRight);
		
		CTextArea textLeft = new CTextArea(root,"");
		textLeft.Init(14.0, 61.0, 252.0, 164.0);
		textLeft.getTextArea().setPromptText("Document here...");
		CTextArea textRight = new CTextArea(root,"button");
		textRight.Init(334.0, 61.0, 252.0, 164.0);
		textRight.getTextArea().setPromptText("Means here...");
		
		
		button.Init(268.0, 131.0, 64.0 , 31.0, "=>");
		button.setImage("arrow.png", 30, 40);
		button.getButton().setOnAction(e -> AlertBoxTranslate.Translate(textLeft, textRight));
		button.setCSS(scene);
		
		
		CButton exit = new CButton(root,"button-quit");
		exit.Init(14.0, 228.0,  572.0, 41.0, "Exit");
		exit.setImage("quit.png", 30, 40);
		exit.getButton().setOnAction(e -> window.close());
		exit.setCSS(scene);
		
		
		
		window.setTitle(title);
		window.setScene(scene);
		window.showAndWait();
	}
	public static void Translate(CTextArea textLeft, CTextArea textRight) {
		boolean isRun = false;
		while (!isRun) {
			try {
				textRight.getTextArea().setText(GoogleTranslate.translate(AlertBoxTranslate.lang, 
				textLeft.getTextArea().getText()));
				isRun = true;
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				NotInternet.display();
				isRun = true;
				//e1.printStackTrace();
			}
		}
	}
}
























