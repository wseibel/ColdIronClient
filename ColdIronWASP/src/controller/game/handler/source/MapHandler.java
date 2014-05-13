package controller.game.handler.source;

import model.game.Game;
import model.game.Map;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles map related server messages
 */
public class MapHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Game game = getChainMaster().getCIClient().getGame();
		String id = jsonObject.getString("@src");
		Map newMap = new Map();
		newMap.setId(id);

		if (game.getMap() != null) {
			newMap = game.getMap();
		}

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
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newMap);
	}

}
