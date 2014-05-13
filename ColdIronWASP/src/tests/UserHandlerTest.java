package tests;

import java.util.Iterator;

import model.game.CIClient;
import model.game.Game;
import model.game.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import controller.game.handler.ChainMaster;

/**
 * Tests "UserHandler" class
 *
 */
public class UserHandlerTest {

	JSONObject jsonObject;
	String id;
	
	CIClient ciClient;
	Game game;
	ChainMaster chainMaster;
	User user;
	
	/**
	 * Creates a json object which should be tested
	 * @throws JSONException
	 */
	public void init(){
		
		id = "User@1f5205c";
		
		jsonObject = new JSONObject();
		// Creating test message
		try {
			jsonObject.put("@ts", String.valueOf(System.currentTimeMillis()));
			jsonObject.put("@src", id);
			jsonObject.put("@prop", "nickname");
			jsonObject.put("@nv", "Team@1794494");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ciClient = new CIClient();
		game = new Game();
		chainMaster = new ChainMaster();
		user = new User();
		
		// Setting necessary bindings
		ciClient.setGame(game);
		ciClient.setChainMaster(chainMaster);
		user.setGame(game);
	}
	
	@Test
	public void testAddNetUser() throws JSONException, Exception{
		init();
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (@SuppressWarnings("unchecked")
		Iterator<User> userIterator = game.iteratorOfUser(); userIterator.hasNext();) {
			User user = (User) userIterator.next();
			if(id.equals(user.getId())) {
				this.user = user;
			}					
		}
		Assert.assertNotNull(this.user);
	}
	
	@Test
	public void testChangeUsername() throws JSONException, Exception{
		testAddNetUser();
		
		String nickname = "wseibe";
		
		// Change value in jsonObject
		jsonObject.remove("@nv");
		jsonObject.remove("@prop");
		jsonObject.put("@prop", "nickname");	
		jsonObject.put("@nv", nickname);
		
		// Doing its job
		chainMaster.consumeMessage(jsonObject);
		
		// Check if the server message was brought to data model
		for (@SuppressWarnings("unchecked")
		Iterator<User> userIter = game.iteratorOfUser(); userIter.hasNext();) {
			User user = (User) userIter.next();
			if(id.equals(user.getId())) {
				this.user = user;
			}					
		}
		
		// See if the value change was committed to data model
		Assert.assertNotNull(user);
		Assert.assertEquals(nickname, user.getNickname());
	}
}
