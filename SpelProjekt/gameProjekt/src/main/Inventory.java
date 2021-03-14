package main;

public class Inventory {
	private GameObject[] items;

	public Inventory(GameObject[] items) {
		this.items = items;
	}
	
	public Inventory(int size) {
		this.items = new GameObject[size];
	}

	public GameObject[] getItems() {
		return items;
	}

	public void setItems(GameObject[] items) {
		this.items = items;
	}
	
	public boolean addItem(GameObject toAdd) {
		for (int i = 0; i < items.length; i++) {
			if(items[i]==null) {
				items[i] = toAdd;
				return true;
			}
		}
		return false;
	}
	
	public boolean removeItem(GameObject toRe) {
		for (int i = 0; i < items.length; i++) {
			if(items[i]==(toRe)) {
				items[i] = null;
				return true;
			}
		}
		return false;
	}
}
