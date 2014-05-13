package tests;


import java.util.Iterator;

import junit.framework.Assert;
import org.junit.Test;

import controller.lobby.Logincontroller;

import model.game.CIClient;
import model.lobby.GameInfo;
import model.lobby.MapInfo;
import model.lobby.NonJSONMessageReceiver;
import model.lobby.PlayerInfo;
import model.lobby.ServerConnection;
import model.lobby.Start;


public class ServerTests{
   
   public void removeYou()
   {
   }

	@Test
	public void testFirstSession() { 
		
		CIClient cIClient = new CIClient(); 
  		cIClient.setServerConnection(new ServerConnection());
  				
  		cIClient.getServerConnection().login("rdoben", "start1");
  		cIClient.getServerConnection().msg("user rdoben testmessage1");
  		Assert.assertEquals("wrong returning chatMessage", "MSG FROM rdoben TO YOU: \"testmessage1\"",
  				cIClient.getNewRcvChatMessage());
  		cIClient.getServerConnection().msg("user rdoben testmessage2");
  		Assert.assertEquals("wrong returning chatMessage", "MSG FROM rdoben TO YOU: \"testmessage2\"",
  				cIClient.getNewRcvChatMessage());
  		cIClient.getServerConnection().createGame("Testgame1");
  		cIClient.getServerConnection().createGame("Testgame2");
  		cIClient.getServerConnection().getGames();
  		cIClient.getServerConnection().getMaps();
  		cIClient.getServerConnection().getPlayers();
  		
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
  		Iterator pIter = cIClient.iteratorOfPlayerInfo();
  		while (pIter.hasNext()) {
  			pInfo = (PlayerInfo) pIter.next();
  			System.out.println("PlayerInfo Nr." + count++ +":");
  			System.out.println("name= " + pInfo.getName());
  			System.out.println("team= " + pInfo.getTeam());
  			System.out.println("status= " + pInfo.getStatus());
  			System.out.println();
  		}
  		
  		
    	cIClient.getServerConnection().logout();
    	
  		
  		
  		
//  		ServerConnection server = ServerConnection.getInstance();
   		// connect wurde schon bei getInstance() aufgerufen, also Test sinnlos
//		// connect
//		boolean isConnect = server.connect();
//		Assert.assertTrue("Connection to server failed", isConnect);
//		//System.out.println("Connect got: " + connect);
  		
  		
  		/*
		// login
		result = server.login("rdoben", "start1"); // loginname und passwort kommen von der GUI
		Assert.assertTrue("login failed", result);
		
		
		// getPlayers
		String playerList = server.getPlayers();
		Assert.assertNotNull("found no Player at all but should be at least 1", playerList);
		System.out.println(playerList);
		
		// getGames
		String gamesList = server.getGames();
		Assert.assertNotNull("returned null but should return at least `NO GAMES IN PROGRESS`", gamesList);
		System.out.println(gamesList);
		
		// getMaps
		String mapsList = server.getMaps();
		Assert.assertNotNull("found no maps", mapsList);
		System.out.println(mapsList);
		
						
		
		// create game
		result = server.createGame("Test"); // Gamename kommt von der GUI
		Assert.assertTrue("create game failed", result);
		
		// join game
		result = server.joinGame("A-Team"); //Gamename kommt von der GUI
		Assert.assertTrue("join game failed", result);
		 */
	}

}
