package inluppStreams_lambda1;

public class MyObject {
	private boolean bool;
	private int value;
	private String name;
	
	public MyObject(boolean bool, int value, String name) {
		super();
		this.bool = bool;
		this.value = value;
		this.name = name;
	}
	
	public boolean isBool() {
		return bool;
	}
	
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyObject [bool=" + bool + ", value=" + value + ", name=" + name + "]";
	}
	
}
