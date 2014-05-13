package model.lobby;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TimerTask;

import model.game.CIClient;

/**
 * 
 * @author Sebastian
 * a thread to update the available games
 */
public class UpdateGamesTask extends TimerTask {

   public void removeYou()
   {
   }

	private CIClient ci;
	
	public UpdateGamesTask(CIClient ci) {
		this.ci = ci;
	}
	
	@Override
	public void run() {
		LinkedList<GameInfo> gameList = ci.getGameList();
		if(gameList.size() != 0) {
			for (GameInfo gList : gameList) {
				if(ci.newGame(gList.getName())) {
					ci.addToGameInfo(gList);
				}
			}
			Iterator<GameInfo> iter = ci.iteratorOfGameInfo();
			GameInfo gInfo;
			while(iter.hasNext()) {
				gInfo = iter.next();
				if(!ci.isGameInList(gInfo.getName())) {
					ci.removeFromGameInfo(gInfo);
				}
			}
			ci.clearGameList();
		}
		ci.getServerConnection().getGames();
	}

}
