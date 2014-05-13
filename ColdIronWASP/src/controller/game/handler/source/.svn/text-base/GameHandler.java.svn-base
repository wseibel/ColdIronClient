package controller.game.handler.source;

import model.game.Game;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles game related server messages
 */
public class GameHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		// No need to look if there is already a game because it is created here
		// Create a new game and put it in the data model
		Game newGame = new Game();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		
		if(chainMaster.getCIClient().getGame() != null)
			newGame = chainMaster.getCIClient().getGame();

		newGame.setId(id);
		if(null == chainMaster.getCIClient().getGame())
			chainMaster.getCIClient().setGame(newGame);

		// get new value
		String newValue = null;
		if (jsonObject.has("@nv")) {
			newValue = jsonObject.getString("@nv");
		}

		// get old value
		String oldValue = null;
		if (jsonObject.has("@ov")) {
			oldValue = jsonObject.getString("@ov");
		}

		// Let the specialized property handler handle the values
		PropertyHandler propertyHandler = getPropertyHandlerMap().get(
				jsonObject.get("@prop").toString());
		// Since the properties of a game don't match them of a sector
		// element, the handler has to be casted
		// to non sector element handler with overloaded setValue method
		if (propertyHandler != null)
			((NonSectorElementPropertyHandler) propertyHandler).setValue(
					newValue, oldValue, (Object) newGame);
	}

}
