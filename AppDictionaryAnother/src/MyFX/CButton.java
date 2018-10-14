package MyFX;

import MyScene.primaryCScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class CButton {
	private Pane root;
	private Button button;
	public CButton(Pane root,String id){
		this.button = new Button();
		this.button.setId(id);
		this.root = root;
		primaryCScene.getScene().getStylesheets().add(getClass().getResource("CSS.css").toExternalForm());
	}
	public void setButton(Button button) {
		this.button = button;		
	}
	public Button getButton() {
		return this.button;
	}
	public void Init(double x, double y, double with, double height, String name) {
		this.button.setLayoutX(x);
		this.button.setLayoutY(y);
		this.button.setPrefSize(with, height);
		this.button.setText(name);
		this.root.getChildren().add(this.button);
	}
	public void setImage(String url) {

	}
	public void Init(double x, double y, String name) {
		this.button.setLayoutX(x);
		this.button.setLayoutY(y);
		this.button.setText(name);
		this.root.getChildren().add(this.button);
	}
	
	
	public void handleClick() {
		
	}
	
	
	
	
	
	
}
