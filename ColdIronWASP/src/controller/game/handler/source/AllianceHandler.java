package controller.game.handler.source;

import java.util.Iterator;

import model.game.Game;
import model.game.Alliance;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

public class AllianceHandler extends MessageHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		Alliance newAlliance = new Alliance();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the alliance already exists in the game
		for (Iterator<Alliance> allianceIter = game.iteratorOfAlliance(); allianceIter
				.hasNext();) {
			Alliance alliance = allianceIter.next();
			if (id.equals(alliance.getId())) {
				newAlliance = alliance;
				ingame = true;
			}
		}

		// alliance does not already exist
		if (!ingame) {
			newAlliance.setId(id);
			game.addToAlliance(newAlliance);
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
		// Since the properties of a alliance don't match them of a
		// alliance element, the handler has to be casted
		// to non alliance element handler with overloaded setValue method
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newAlliance);
	}

}
