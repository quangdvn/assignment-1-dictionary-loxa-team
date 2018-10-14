package MyFX;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class CWebView {
	private VBox vBox;
	private WebView webView ;
	public CWebView(Pane root, String id){
		this.vBox = new VBox();
		this.webView = new WebView();
		this.vBox.getChildren().add(this.webView);
		root.getChildren().add(vBox);
	}
	public VBox getVBox() {
		return this.vBox;
	}
	public WebView getWebView() {
		return this.webView;
	}
	public void init(double x, double y, double with, double height) {
		this.vBox.setLayoutX(x);
		this.vBox.setLayoutY(y);
		this.vBox.setPrefSize(with, height);
	}
	
}
