package MyScene;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class primaryCScene {
	private static Scene scene;
	private static AnchorPane root;
	public static void Init() {
		primaryCScene.root = new AnchorPane();
		primaryCScene.scene = new Scene(root,726.0,448.0);
		
	}
	public static AnchorPane getRoot() {
		return primaryCScene.root;
	}
	public static Scene getScene() {
		return primaryCScene.scene;
	}
}
