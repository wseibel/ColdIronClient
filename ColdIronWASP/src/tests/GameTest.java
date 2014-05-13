package tests;

import model.game.CIClient;
import model.game.Game;
import model.game.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests game class
 * 
 * @author Jens Beneke
 *
 */
public class GameTest {

	@Test
	public void testGame() {
		Game game = new Game();
		CIClient ciClient = new CIClient();
		Map map = new Map();
		//Alliances alliance = new Alliances();

		//set links from game to neighbors
		game.setCIClient(ciClient);
		game.setMap(map);
		//game.addToAlliances(alliance);
		
		//check whether links were set
		Assert.assertTrue(ciClient.getGame() == game);
		Assert.assertTrue(game.getCIClient().equals(ciClient));
		//Assert.assertTrue(map.getGame().equals(game));
		//Assert.assertTrue(alliance.getGame().equals(game));
		
	}
}
