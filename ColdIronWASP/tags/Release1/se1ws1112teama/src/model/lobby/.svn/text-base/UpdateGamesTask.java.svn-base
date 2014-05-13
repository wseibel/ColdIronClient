package model.lobby;

import java.util.TimerTask;

import model.game.CIClient;

/**
 * 
 * @author Sebastian
 * a thread to update the available games
 */
public class UpdateGamesTask extends TimerTask {

	private CIClient ci;
	
	public UpdateGamesTask(CIClient ci) {
		this.ci = ci;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		System.out.println("Update Game is running");
		ci.removeAllFromGameInfo();
		ci.getServerConnection().getGames();
	}

}
