package controller.game.handler.source;

import java.util.Iterator;

import model.game.Peon;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles peon related server messages
 */
public class PeonHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Peon peon = new Peon();

		String id = jsonObject.getString("@src");
		Boolean newPeon = true;

		// check if the peon already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> setForIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> iter = setForIterator; iter.hasNext();) {
			SectorElement sectorElement = iter.next();

			if (id.equals(sectorElement.getId())) {
				newPeon = false;
				peon = (Peon) sectorElement;
			}

		}

		// peon does not already exist
		if (newPeon) {
			peon.setId(id);
			getChainMaster().getCIClient().getGame().addToSectorElement(peon);
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

		// let the specialized property handler handle the values
		PropertyHandler propertyHandler = getPropertyHandlerMap().get(
				jsonObject.getString("@prop"));
		propertyHandler.setValue(newValue, oldValue, peon);
	}

}
