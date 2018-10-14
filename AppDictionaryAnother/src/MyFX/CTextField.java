package MyFX;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CTextField {
	private Pane root;
	private TextField textField;
	public CTextField(Pane root,String id) {
		this.textField = new TextField();
		this.textField.setId(id);
		this.root = root;
		
	}
	public void setTextField(TextField textField) {
		this.textField = textField;
	}
	public TextField getTextField() {
		return this.textField;
	}
	public void Init(double x, double y, double with, double height) {
		this.textField.setLayoutX(x);
		this.textField.setLayoutY(y);
		this.textField.setPrefSize(with, height);
		this.root.getChildren().add(this.textField);
	}
	public void handle() {
		
	}

}
