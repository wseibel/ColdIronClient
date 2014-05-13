package model.lobby;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TimerTask;

import model.game.CIClient;

/**
 * 
 * @author Sebastian
 * a thread to update the registered players
 */
public class UpdatePlayersTask extends TimerTask {

   public void removeYou()
   {
   }

	private CIClient ci;
	public UpdatePlayersTask(CIClient ci) {
		this.ci = ci;
	}

	@Override
	public void run() {
		//		System.out.println("Update Player is running");
		LinkedList<PlayerInfo> playerList = ci.getPlayerList();
		if(playerList.size() != 0) {
			for (PlayerInfo pList : playerList) {
				if(ci.newPlayer(pList.getName())) {
					ci.addToPlayerInfo(pList);
				}
			}
			Iterator<PlayerInfo> iter = ci.iteratorOfPlayerInfo();
			PlayerInfo pInfo;
			while(iter.hasNext()) {
				pInfo = iter.next();
				if(!ci.isPlayerInList(pInfo.getName())) {
					ci.removeFromPlayerInfo(pInfo);
				}
			}
			ci.clearPlayerList();
		}
		ci.getServerConnection().getPlayers();
	}

}
