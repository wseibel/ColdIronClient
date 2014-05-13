package controller.game.handler.source;

import java.util.Iterator;

import model.game.Farm;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles farm related server messages
 */
public class FarmHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Farm farm = new Farm();
		String id = jsonObject.getString("@src");
		Boolean newFarm = true;

		// check if the farm already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> sectorIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> sectorElementIter = sectorIterator; sectorElementIter
				.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if (id.equals(sectorElement.getId())) {
				farm = (Farm) sectorElement;
				newFarm = false;
			}
		}

		// farm does not already exist
		if (newFarm) {
			farm.setId(id);
			getChainMaster().getCIClient().getGame().addToSectorElement(farm);
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
		propertyHandler.setValue(newValue, oldValue, farm);
	}

}
