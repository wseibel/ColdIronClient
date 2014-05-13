package controller.game.handler.source;

import java.util.Iterator;

import model.game.SectorElement;
import model.game.Swordsman;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles tower related server messages
 */
public class SwordsmanHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Swordsman swordsman = new Swordsman();
		String id = jsonObject.getString("@src");
		Boolean newSwordsman = true;

		// check if the swordsman already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> setForIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> iter = setForIterator; iter.hasNext();) {
			SectorElement sectorElement = iter.next();
			if (id.equals(sectorElement.getId())) {
				swordsman = (Swordsman) sectorElement;
				newSwordsman = false;
			}
		}

		// swordsman does not already exist
		if (newSwordsman) {
			swordsman.setId(id);
			getChainMaster().getCIClient().getGame()
					.addToSectorElement(swordsman);
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
		propertyHandler.setValue(newValue, oldValue, swordsman);
	}

}
