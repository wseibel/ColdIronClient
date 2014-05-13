package tests;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;

import model.game.Archer;
import model.game.CIClient;
import model.game.Game;
import model.game.Map;
import model.game.SectorElement;

public class ArcherHandlerTest {	

	JSONObject jsonObject;
	String id;
	
	CIClient ciClient;
	Game game;
	Map map;
	ChainMaster chainMaster;
	Archer archer;
	
	/**
	 * Creates a json object which should be tested
	 * @throws JSONException
	 */
	public void init(){
		
		id = "Archer@12345";
		
		jsonObject = new JSONObject();
		// Creating test message
		try {
			jsonObject.put("@ts", String.valueOf(System.currentTimeMillis()));
			jsonObject.put("@src", id);
			jsonObject.put("@prop", "level");
			jsonObject.put("@nv", "1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ciClient = new CIClient();
		game = new Game();
		chainMaster = new ChainMaster();
		map = new Map();
		archer = null;
		
		// Setting necessary bindings
		ciClient.setGame(game);
		game.setMap(map);
		ciClient.setChainMaster(chainMaster);
	}
	
	@Test
	public void testAddNewArcher() throws JSONException, Exception{
		init();
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (Iterator<SectorElement> sectorElementIter = game.iteratorOfSectorElement(); sectorElementIter.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if(id.equals(sectorElement.getId())) {
				archer = (Archer)sectorElement;
			}					
		}
		Assert.assertNotNull(archer);
	}
	
	@Test
	public void testChangeLevel() throws JSONException, Exception{
		testAddNewArcher();
		
		// Change value in jsonObject
		jsonObject.remove("@nv");
		jsonObject.put("@nv", "20");
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (Iterator<SectorElement> sectorElementIter = game.iteratorOfSectorElement(); sectorElementIter.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if(id.equals(sectorElement.getId())) {
				archer = (Archer)sectorElement;
			}					
		}
		
		// See if the value change was committed to data model
		Assert.assertNotNull(archer);
		Assert.assertEquals((long)20, (long)archer.getLevel());
	}
}
