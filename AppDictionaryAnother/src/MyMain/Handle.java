package MyMain;

import Database.Controller;
import MyFX.CButton;
import MyFX.CListView;
import MyFX.CTextField;
import MyFX.CWebView;
import MyScene.primaryCScene;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;

public class Handle {
	public static Scene primaryScene() {
		primaryCScene.Init();

		// Google translate button
		CButton button_translate = new CButton(primaryCScene.getRoot(),"button");
		button_translate.Init(14.0, 59.0, 122.0, 88.0, "Translate");

		// Game button
		CButton button_game = new CButton(primaryCScene.getRoot(),"button");
		button_game.Init(14.0, 160.0, 122.0, 88.0, "Game");
		// Info button
		CButton button_info = new CButton(primaryCScene.getRoot(),"button");
		button_info.Init(14.0, 254.0, 122.0, 88.0, "Info");
		// Quit button
		CButton button_quit = new CButton(primaryCScene.getRoot(),"button-quit");
		button_quit.Init(602.0, 353.0, 114.0, 88.0, "Quit");
		// Button search
		CButton button_search = new CButton(primaryCScene.getRoot(),"button");
		button_search.Init(602.0, 59.0, 114.0, 31.0, "Search");
		
		// Button sound
		CButton button_sound = new CButton(primaryCScene.getRoot(),"button");
		button_sound.Init(602.0, 95.0, 114.0, 59.0, "Sound");
		// Restore Button
		CButton button_restore = new CButton(primaryCScene.getRoot(),"button");
		button_restore.Init(602.0, 289.0, 114.0, 59.0, "Restore");
		// Like Button
		CButton button_like = new CButton(primaryCScene.getRoot(),"button");
		button_like.Init(602.0, 160.0, 114.0, 59.0, "Like");
		// Remove button
		CButton button_remove = new CButton(primaryCScene.getRoot(),"button");
		button_remove.Init(602.0, 224.0, 114.0, 59.0, "Remove");
		// Login button
		CButton button_login = new CButton(primaryCScene.getRoot(),"button");
		button_login.Init(14.0, 353.0, 122.0, 88.0, "Login");
		// Like list Button
		CButton button_like_list = new CButton(primaryCScene.getRoot(),"button");
		button_like_list.Init(146.0, 14.0, 179.0, 37.0, "Like List");
		// History button
		CButton button_history = new CButton(primaryCScene.getRoot(),"button");
		button_history.Init(332.0, 14.0, 133.0, 37.0, "History");
		// Delelist Button
		CButton button_delete_list = new CButton(primaryCScene.getRoot(),"button");
		button_delete_list.Init(470.0, 14.0, 133.0, 37.0, "Delete List");
		
  
		//
		
/*		// TextArea
		CTextArea text_area = new CTextArea(primaryCScene.getRoot(),"text-area");
		text_area.Init(332.0, 95.0, 267.0, 347.0);*/
		
		CWebView web_view = new CWebView(primaryCScene.getRoot(),"web-view");
		web_view.init(332.0, 95.0, 267.0, 347.0);
		
		// ListView
		CListView list_view = new CListView(primaryCScene.getRoot(),"list-view");
		list_view.Init(146.0, 59.0, 179.0, 384.0);
		list_view.getListView().setItems(CListView.items);
		list_view.getListView().getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> ov, String old_val,
						String new_val) -> {
		       	 Controller.displaySelectedItem(list_view.getListView(), web_view.getWebView());
		});
		// TextFilde search
		CTextField text_filde_search = new CTextField(primaryCScene.getRoot(),"text-filde-search");
		text_filde_search.Init(332.0, 59.0, 267.0, 31.0);
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
