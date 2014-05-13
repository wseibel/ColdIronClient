package tests;

import java.util.Iterator;

import model.game.CIClient;
import model.game.Catapult;
import model.game.Game;
import model.game.Map;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import controller.game.handler.ChainMaster;


/**
 * 
 * Tests the CatapultHandler
 *
 */
public class CatapultHandlerTest {
	JSONObject jsonObject;
	String id;
	
	CIClient ciClient;
	Game game;
	Map map;
	ChainMaster chainMaster;
	Catapult catapult;
	
	/**
	 * Creates a json object which should be tested
	 * @throws JSONException
	 */
	public void init(){
		
		id = "Catapult@12345";
		
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
		catapult = null;
		
		// Setting necessary bindings
		ciClient.setGame(game);
		game.setMap(map);
		ciClient.setChainMaster(chainMaster);
	}
	
	@Test
	public void testAddNewCatapult() throws JSONException, Exception{
		init();
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (Iterator<SectorElement> sectorElementIter = game.iteratorOfSectorElement(); sectorElementIter.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if(id.equals(sectorElement.getId())) {
				catapult = (Catapult)sectorElement;
			}					
		}
		Assert.assertNotNull(catapult);
	}
	
	@Test
	public void testChangeLevel() throws JSONException, Exception{
		testAddNewCatapult();
		
		// Change value in jsonObject
		jsonObject.remove("@nv");
		jsonObject.put("@nv", "20");
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (Iterator<SectorElement> sectorElementIter = game.iteratorOfSectorElement(); sectorElementIter.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if(id.equals(sectorElement.getId())) {
				catapult = (Catapult)sectorElement;
			}					
		}
		
		// See if the value change was committed to data model
		Assert.assertNotNull(catapult);
		Assert.assertEquals((long)20, (long)catapult.getLevel());
	}
}
