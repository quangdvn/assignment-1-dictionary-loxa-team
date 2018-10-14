package MyFX;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class CTextArea {
	private Pane root;
	private TextArea textArea;
	public CTextArea(Pane root,String id) {
		this.textArea = new TextArea();
		this.textArea.setId(id);
		this.root = root;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}
	public TextArea getTextArea() {
		return this.textArea;
	}
	public void Init(double x, double y, double with, double height) {
		this.textArea.setLayoutX(x);
		this.textArea.setLayoutY(y);
		this.textArea.setPrefSize(with, height);
		this.root.getChildren().add(this.textArea);
	}
	public void handle() {
		
	}
	public void showTextArea() {
		
	}
}
