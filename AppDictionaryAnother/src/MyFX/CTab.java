package MyFX;

import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class CTab {
	private Tab tab;
	public CTab() {
		this.tab = new Tab();
	}
	public CTab(String name, AnchorPane anchor) {
		this.tab = new Tab();
		this.tab.setText(name);
		this.tab.setContent(anchor);
	}
	public Tab getTab() {
		return this.tab;
	}
}
