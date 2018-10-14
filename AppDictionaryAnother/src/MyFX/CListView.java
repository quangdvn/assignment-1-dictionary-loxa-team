package MyFX;

import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class CListView {
	public static ObservableList<String> items = FXCollections.observableArrayList(ListWord.getListTarget());
	private Pane root;
	private ListView<String> listView;
	public CListView(Pane root,String id) {
		this.listView = new ListView<String>();
		this.listView.setId(id);
		this.root = root;
	}
	public void setListView(ListView<String> listView) {
		this.listView = listView;
	}
	public ListView<String> getListView() {
		return this.listView;
	}
	public void Init(double x, double y, double with, double height) {
		this.listView.setLayoutX(x);
		this.listView.setLayoutY(y);
		this.listView.setPrefSize(with, height);
		this.root.getChildren().add(this.listView);
	}
	public void showListView(String str) {
		TreeSet<String> arr = new TreeSet<String>();
		for (String temp : ListWord.getListTarget()) {
			if (temp.toLowerCase().contains(str.toLowerCase())) {
				arr.add(new String(temp));
			}
		}
		CListView.items = FXCollections.observableArrayList(arr);
		this.getListView().setItems(items);
	}
}















