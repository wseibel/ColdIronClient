package controller.game.handler.source;

import java.util.Iterator;

import model.game.Catapult;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles catapult related server messages
 */
public class CatapultHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Catapult catapult = null;

		String id = jsonObject.getString("@src");
		Boolean newCatapult = true;

		// check if the catapult already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> setForIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> iter = setForIterator; iter.hasNext();) {
			SectorElement sectorElement = (SectorElement) iter.next();
			if (id.equals(sectorElement.getId())) {
				catapult = (Catapult) sectorElement;
				newCatapult = false;
			}
		}

		// catapult does not already exist
		if (newCatapult) {
			catapult = new Catapult();
			catapult.setId(id);
			getChainMaster().getCIClient().getGame()
					.addToSectorElement(catapult);
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

		PropertyHandler propertyHandler = getPropertyHandlerMap().get(
				jsonObject.getString("@prop"));
		propertyHandler.setValue(newValue, oldValue, catapult);
	}

}
