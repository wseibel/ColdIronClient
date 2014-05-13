package controller.game.handler.source;

import java.util.Iterator;

import model.game.Game;
import model.game.User;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles user related server messages
 */
public class UserHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		User newUser = new User();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the user already exists in the game
		for (Iterator<User> userIter = game.iteratorOfUser(); userIter
				.hasNext();) {
			User user = userIter.next();
			if (id.equals(user.getId())) {
				newUser = user;
				ingame = true;
			}
		}

		// user does not already exist
		if (!ingame) {
			newUser.setId(id);
			game.addToUser(newUser);
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
		// Since the properties of a user don't match them of a user
		// element, the handler has to be casted
		// to non user element handler with overloaded setValue method
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newUser);
	}
}
