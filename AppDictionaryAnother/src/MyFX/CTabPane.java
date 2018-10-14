package MyFX;

import MyScene.primaryCScene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CTabPane {
	private TabPane tabPane;
	public CTabPane(){
		this.tabPane = new TabPane();
	}
	
	public CTabPane(double x, double y, double with, double height,Tab tab){
		this.tabPane = new TabPane();
		this.tabPane.setLayoutX(x);
		this.tabPane.setLayoutY(y);
		this.tabPane.setPrefSize(with, height);
		primaryCScene.getRoot().getChildren().add(this.tabPane);
		this.tabPane.getTabs().add(tab);
	}
	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
	public TabPane getTabPane() {
		return this.tabPane;
	}
	public void Init(double x, double y, double with, double height,Tab tab) {
		this.tabPane.setLayoutX(x);
		this.tabPane.setLayoutY(y);
		this.tabPane.setPrefSize(with, height);
		this.tabPane.getTabs().add(tab);
		primaryCScene.getRoot().getChildren().add(this.tabPane);
	}
}
