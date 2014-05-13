package model.lobby;

import java.util.TimerTask;

/**
 * 
 * @author Sebastian
 * a thread to send the NOOP-Command
 */
public class NoopTask extends TimerTask {

	private ServerConnection sc;
	
	public NoopTask(ServerConnection sc) {
		this.sc = sc;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("NOOP is runnig");
		sc.sendNOOP();
	}

}
