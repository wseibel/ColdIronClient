package tests;


import java.util.Iterator;

import junit.framework.Assert;
import model.game.CIClient;
import model.lobby.GameInfo;
import model.lobby.MapInfo;
import model.lobby.PlayerInfo;
import model.lobby.ServerConnection;

import org.junit.Test;


public class ServerTests{
   
   public void removeYou()
   {
   }
   
  
   
	@Test
	public void testFirstSession() { 
		
		final int CHILLTIME = 600; // time [ms] for the current thread to wait  
		//e.g. 500 milliseconds were sometimes too short - on new trouble first try to increase here
		
		CIClient cIClient = new CIClient(); 
  		cIClient.setServerConnection(new ServerConnection());
  	
  		
  		cIClient.getServerConnection().login("rdoben", "start1");
  		cIClient.getServerConnection().msg("user rdoben testmessage1");
  		try {
			Thread.currentThread().sleep(CHILLTIME); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  		Assert.assertEquals("wrong returning chatMessage", "MSG FROM rdoben TO YOU: \"testmessage1\"",
  				cIClient.getNewRcvChatMessage());
  		cIClient.getServerConnection().msg("user rdoben testmessage2");
  		try {
			Thread.currentThread().sleep(CHILLTIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  		Assert.assertEquals("wrong returning chatMessage", "MSG FROM rdoben TO YOU: \"testmessage2\"",
  				cIClient.getNewRcvChatMessage());
  		cIClient.getServerConnection().createGame("Testgame1");
  		cIClient.getServerConnection().getGames();
  		cIClient.getServerConnection().getMaps();
  		cIClient.getServerConnection().getPlayers();
  		try {
			Thread.currentThread().sleep(CHILLTIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  		Assert.assertTrue("failed to create the game or to detect it", (cIClient.getGameList().size() > 0));
  		Assert.assertTrue("failed to detect the maps", (cIClient.sizeOfMapInfo() > 0));
  		Assert.assertTrue("failed to detect this tests player", cIClient.isPlayerInList("rdoben"));
  		 		
  		
  		
  		
  		// now some "visual tests" because attributes are initialized with
  		// unpredictable values from server 
  		System.out.println();
  		int count = 0;
  		GameInfo gInfo;
  		Iterator gIter = cIClient.iteratorOfGameInfo();
  		while (gIter.hasNext()) {
  			gInfo = (GameInfo) gIter.next();
  			System.out.println("GameInfo Nr." + count++ +":");
  			System.out.println("name= " + gInfo.getName());
  			System.out.println("eventCount= " + gInfo.getEventCount());
  			System.out.println("mapName= " + gInfo.getMapName());
  			System.out.println("sectorCount= " + gInfo.getSectorCount());
  			System.out.println("joinedPlayerCount= " + gInfo.getJoinedPlayerCount());
  			System.out.println("maxPlayerCount= " + gInfo.getMaxPlayerCount());
  			System.out.println("status= " + gInfo.getStatus());
  			System.out.println();
  		}
  		count = 0;
  		MapInfo mInfo;
  		Iterator mIter = cIClient.iteratorOfMapInfo();
  		while (mIter.hasNext()) {
  			mInfo = (MapInfo) mIter.next();
  			System.out.println("MapInfo Nr." + count++ +":");
  			System.out.println("name= " + mInfo.getName());
  			System.out.println("sectorCount= " + mInfo.getSectorCount() );
  			System.out.println();
  		}
  		count = 0;
  		PlayerInfo pInfo;
  		Iterator pIter = cIClient.getPlayerList().iterator();
  		while (pIter.hasNext()) {
  			pInfo = (PlayerInfo) pIter.next();
  			System.out.println("PlayerInfo Nr." + count++ +":");
  			System.out.println("name= " + pInfo.getName());
  			System.out.println("team= " + pInfo.getTeam());
  			System.out.println("status= " + pInfo.getStatus());
  			System.out.println();
  		}
  		
  		
    	cIClient.getServerConnection().logout();
    	
	}	
 		

}
