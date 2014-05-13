package tests;

import java.util.Iterator;

import model.game.CIClient;
import model.game.Game;
import model.game.UserAssets;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import controller.game.handler.ChainMaster;

public class UserAssetsHandlerTest {
	JSONObject jsonObject;
	String id;
	
	CIClient ciClient;
	Game game;
	ChainMaster chainMaster;
	UserAssets userAssets;
	
	/**
	 * Creates a json object which should be tested
	 * @throws JSONException
	 */
	public void init(){
		
		id = "UserAssets@158aac4";
		
		jsonObject = new JSONObject();
		// Creating test message
		try {
			jsonObject.put("@ts", String.valueOf(System.currentTimeMillis()));
			jsonObject.put("@src", id);
			jsonObject.put("@prop", "alliance");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ciClient = new CIClient();
		game = new Game();
		chainMaster = new ChainMaster();
		userAssets = new UserAssets();
		
		// Setting necessary bindings
		ciClient.setGame(game);
		ciClient.setChainMaster(chainMaster);
		userAssets.setGame(game);
	}
	
	@Test
	public void testUserAssetsHandler() throws JSONException, Exception{
		init();
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (@SuppressWarnings("unchecked")
		Iterator<UserAssets> userAssetIterator = game.iteratorOfUserAssets(); userAssetIterator.hasNext();) {
			UserAssets userAssets = (UserAssets) userAssetIterator.next();
			if(id.equals(userAssets.getId())) {
				this.userAssets = userAssets;
			}					
		}
		Assert.assertNotNull(this.userAssets);
	}
	
	@Test
	public void testChangeProp() throws JSONException, Exception{
		testUserAssetsHandler();
		
		// Change value in jsonObject
		jsonObject.remove("@prop");
		jsonObject.remove("@nv");
		jsonObject.put("@prop", "user");
		jsonObject.put("@nv", "User@1e140bf");
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (@SuppressWarnings("unchecked")
		Iterator<UserAssets> userAssetIter = game.iteratorOfUserAssets(); userAssetIter.hasNext();) {
			UserAssets userAssets = (UserAssets) userAssetIter.next();
			if(id.equals(userAssets.getId())) {
				this.userAssets = userAssets;
			}					
		}
		
		// See if the value change was committed to data model
		Assert.assertNotNull(userAssets);
		Assert.assertEquals(userAssets.getId(), id);
		Assert.assertEquals("User@1e140bf", userAssets.getUser().getId());
	}
}
