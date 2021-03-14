package main;

import java.io.Serializable;

public class Npc implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String pre;
	private int position;
	private Inventory npvInv;
	private String line;

	public Npc(String name, String pre, int position, String line) {
		super();
		this.name = name;
		this.pre = pre;
		this.position = position;
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Inventory getNpvInv() {
		return npvInv;
	}

	public void setNpvInv(Inventory npvInv) {
		this.npvInv = npvInv;
	}
	
	public GameObject[] getItems() {
		return npvInv.getItems();
	}
	
	public boolean addItem(GameObject toAdd) {
		return this.npvInv.addItem(toAdd);
	}
	
	public boolean removeItem(GameObject toRe) {
		return this.npvInv.removeItem(toRe);
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
