package controller.game.handler.source;

import java.util.Iterator;

import model.game.Game;
import model.game.UserAssets;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles userAssets related server messages
 */
public class UserAssetsHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		UserAssets newUserAssets = new UserAssets();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the userAssets already exists in the game
		for (Iterator<UserAssets> userAssetsIter = game.iteratorOfUserAssets(); userAssetsIter
				.hasNext();) {
			UserAssets userAssets = userAssetsIter.next();
			if (id.equals(userAssets.getId())) {
				newUserAssets = userAssets;
				ingame = true;
			}
		}

		// userAssets does not already exist
		if (!ingame) {
			newUserAssets.setId(id);
			game.addToUserAssets(newUserAssets);
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
		// Since the properties of a userAssets don't match them of a
		// userAssets element, the handler has to be casted
		// to non userAssets element handler with overloaded setValue method
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newUserAssets);
	}

}
