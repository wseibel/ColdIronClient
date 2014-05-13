package model.lobby;

import java.util.TimerTask;

/**
 * a thread to send the JSON-NOOP-Command
 */
public class JSONNoopTask extends TimerTask {
	
	private ServerConnection sc;

	public JSONNoopTask(ServerConnection sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
//		System.out.println("JSONnoop is running");
		sc.sendJSONnoop();
	}

}
