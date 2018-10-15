package MyMain;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Database.Controller;
import MyFX.AlertBoxAdd;
import MyFX.AlertBoxTranslate;
import MyFX.CButton;
import MyFX.CListView;
import MyFX.CTextField;
import MyFX.CWebView;
import MyFX.ListWord;
import MyFX.NotInternet;
import MyScene.primaryCScene;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Handle {

	public static Scene primaryScene() {
		
		primaryCScene.Init();
			// Init
			CButton button_translate = new CButton(primaryCScene.getRoot(),"button");
			CButton button_add = new CButton(primaryCScene.getRoot(),"button-add");
			CButton button_game = new CButton(primaryCScene.getRoot(),"button");
			CButton button_info = new CButton(primaryCScene.getRoot(),"button");
			CButton button_quit = new CButton(primaryCScene.getRoot(),"button-quit");
			CButton button_search = new CButton(primaryCScene.getRoot(),"button");
			CButton button_sound = new CButton(primaryCScene.getRoot(),"button");
			CButton button_restore = new CButton(primaryCScene.getRoot(),"button");
			CButton button_like = new CButton(primaryCScene.getRoot(),"button");
			CButton button_remove = new CButton(primaryCScene.getRoot(),"button");
			CButton button_login = new CButton(primaryCScene.getRoot(),"button");
					
			ObservableList<String> list = FXCollections.observableArrayList(
	                "Dictionary", "Like List", "List Delete", "History");
			ChoiceBox<String> choiceBox = new ChoiceBox<>();
			 

	        choiceBox.setPrefSize(179.0, 37.0);
	        choiceBox.setLayoutX(146.0);          choiceBox.setLayoutY(14.0);
	        choiceBox.setItems(list);
	        choiceBox.getSelectionModel().selectFirst();
	 
	        choiceBox.getSelectionModel().selectedIndexProperty()
	                .addListener(new ChangeListener<Number>() {
	                    public void changed(ObservableValue ov, Number value, Number new_value) {
	                    	System.out.println(list.get(new_value.intValue()).toUpperCase());
	                    }
	                });
	        
			primaryCScene.getRoot().getChildren().addAll(choiceBox);
			
			CWebView web_view = new CWebView(primaryCScene.getRoot(),"web-view");
			CListView list_view = new CListView(primaryCScene.getRoot(),"list-view");
			CTextField text_filde_search = new CTextField(primaryCScene.getRoot(),"text-filde-search");
			// Google translate button
			button_translate.Init(14.0, 14.0, 122.0, 73.0, "Translate");
			button_translate.setImage("google.png", 40, 60);
			button_translate.getButton().setOnAction(e -> AlertBoxTranslate.display("Google Translate"));
			// add button 
			button_add.Init(608.0, 54.0, 108.0, 37.0, "Add");
			button_add.setImage("add.png", 50, 25);
			button_add.getButton().setOnAction(new EventHandler<ActionEvent>() {
			       @Override
			       public void handle(ActionEvent event) {
			    	   AlertBoxAdd.display("Add new word");
			    	   //Controller.deleteWord(list_view.getListView(), web_view.getWebView());
			           ListWord.setListTarget(Controller.loadDataBase());
			           list_view.showListView(text_filde_search.getTextField().getText());
			       }
			});
			
			
			
			// Game button
			button_game.Init(14.0, 108.0, 122.0, 73.0, "Game");
			button_game.setImage("game.png", 40, 60);
			// Info button
			button_info.Init(14.0, 196.0, 122.0, 73.0, "Info");
			button_info.setImage("info.png", 40, 60);
			button_info.getButton().setOnAction(e -> {
	            if(Desktop.isDesktopSupported())
	            {
	            	try {
	                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/NguyenNgocHaiNewVersion")); 
	                } catch (IOException e1) {
	                	NotInternet.display();	
	                } catch (URISyntaxException e1) {
	                	NotInternet.display();
	                }
	            }
	        });
			// Quit button
			button_quit.Init(14.0, 369.0, 122.0, 73.0, "Quit");
			button_quit.setImage("quit.png", 40, 60);
			button_quit.getButton().setOnAction(new EventHandler<ActionEvent>() {
			       @Override
			       public void handle(ActionEvent event) {
			    	   Platform.exit();
	    		       System.exit(0);
			       }
			});
			// Button search
			button_search.Init(608.0, 17.0, 108.0, 31.0, "Search");
			button_search.setImage("search.png",30,25);
			button_search.getButton().setOnAction(new EventHandler<ActionEvent>() {
			       @Override
			       public void handle(ActionEvent event) {
			    	   Controller.displaySelectedItem(list_view.getListView(), web_view.getWebView(),text_filde_search.getTextField().getText());
			       }
			});
			// Button sound
			button_sound.Init(332.0, 57.0, 81.0, 31.0, "Sound");
			button_sound.setImage("sound.png", 20, 20);
			// Restore Button
			button_restore.Init(518.0, 57.0, 81.0, 31.0, "Restore");
			button_restore.setImage("restore.png", 20, 20);
			// Like Button
			button_like.Init(662.0, 99.0, 52.0, 31.0, "Like");
			button_like.setImage("like.png", 20, 20);
			// Remove button
			button_remove.Init(425.0, 57.0, 81.0, 31.0, "Remove");
			button_remove.setImage("remove.png", 20, 20);
			/**
			 * remove word and update database
			 */
			button_remove.getButton().setOnAction(new EventHandler<ActionEvent>() {
			       @Override
			       /**
			        * hadle data
			        */
			       public void handle(ActionEvent event) {
			              Controller.deleteWord(list_view.getListView(), web_view.getWebView());
			              ListWord.setListTarget(Controller.loadDataBase());
			              list_view.showListView(text_filde_search.getTextField().getText());
			       }
			});
			// Login button
			button_login.Init(14.0, 281.0, 122.0, 73.0, "Login");
			button_login.setImage("login.png", 40, 60);

			// web view
			web_view.init(332.0, 95.0, 388.0, 347.0);
			// ListView
			list_view.Init(146.0, 59.0, 179.0, 384.0);
			list_view.getListView().setItems(CListView.items);
			list_view.getListView().getSelectionModel().selectedItemProperty().addListener(
					(ObservableValue<? extends String> ov, String old_val,
							String new_val) -> {
			       	 Controller.displaySelectedItem(list_view.getListView(), web_view.getWebView(),new_val);
			});
			// TextFilde search
			text_filde_search.Init(332.0, 17.0, 267.0, 31.0);
			text_filde_search.getTextField().setPromptText("Search word here...");
			text_filde_search.getTextField().setOnKeyReleased(new EventHandler<InputEvent>() {  
	            @Override  
	            public void handle(InputEvent arg0) {  
	                // TODO Auto-generated method stub  
	            	list_view.showListView(text_filde_search.getTextField().getText());
	            }  
	        }); 
		
		return primaryCScene.getScene();
	}
}
