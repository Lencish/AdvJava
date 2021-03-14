package main;

public class Update implements Runnable{
	
	public Update() {
	}

	@Override
	public void run() {
		while(GameRun.running){
			try {
				Thread.sleep(40000);
				GameRun.moveNpc();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
