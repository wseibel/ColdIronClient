package model.lobby;

import java.util.TimerTask;

import model.game.CIClient;

/**
 * 
 * @author Sebastian
 * a thread to update the registered players
 */
public class UpdatePlayersTask extends TimerTask {

	private CIClient ci;
	public UpdatePlayersTask(CIClient ci) {
		this.ci = ci;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		System.out.println("Update Player is running");
		ci.removeAllFromPlayerInfo();
		ci.getServerConnection().getPlayers();
	}

}
