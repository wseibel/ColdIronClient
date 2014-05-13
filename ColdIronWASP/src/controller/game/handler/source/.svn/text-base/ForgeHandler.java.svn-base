package controller.game.handler.source;

import java.util.Iterator;

import model.game.Forge;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles forge related server messages
 */
public class ForgeHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {
		Forge forge = new Forge();
		String id = jsonObject.getString("@src");
		Boolean newForge = true;

		// check if the forge already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> sectorIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> sectorElementIter = sectorIterator; sectorElementIter
				.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if (id.equals(sectorElement.getId())) {
				forge = (Forge) sectorElement;
				newForge = false;
			}
		}

		// forge does not already exist
		if (newForge) {
			forge.setId(id);
			getChainMaster().getCIClient().getGame().addToSectorElement(forge);
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
		propertyHandler.setValue(newValue, oldValue, forge);
	}

}
