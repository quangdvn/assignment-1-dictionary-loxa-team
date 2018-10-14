package MyFX;

import java.util.TreeSet;

import Database.Controller;

public class ListWord {
	private static TreeSet<String> listTarget = Controller.loadDataBase();
	public ListWord(){
		ListWord.listTarget = new TreeSet<String>();
	}
	public static void setListTarget(TreeSet<String> arrayList) {
		ListWord.listTarget = arrayList;
	}
	public static TreeSet<String> getListTarget(){
		return ListWord.listTarget;
	}
	
	
}
