package model.lobby;

import java.util.TimerTask;

/**
 * 
 * @author Sebastian
 * a thread to send the NOOP-Command
 */
public class NoopTask extends TimerTask {

   public void removeYou()
   {
   }

	private ServerConnection sc;
	
	public NoopTask(ServerConnection sc) {
		this.sc = sc;
	}
	
	@Override
	public void run() {
//		System.out.println("NOOP is running");
		sc.sendNOOP();
	}

}
