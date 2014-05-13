package controller.game.handler.source;

import java.util.Iterator;

import model.game.SectorElement;
import model.game.Stronghold;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles stronghold related server messages
 */
public class StrongholdHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Stronghold stronghold = new Stronghold();
		String id = jsonObject.getString("@src");
		Boolean newStronghold = true;

		// check if the stronghold already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> sectorIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> sectorElementIter = sectorIterator; sectorElementIter
				.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if (id.equals(sectorElement.getId())) {
				stronghold = (Stronghold) sectorElement;
				newStronghold = false;
			}
		}

		// stronghold does not already exist
		if (newStronghold) {
			stronghold.setId(id);
			getChainMaster().getCIClient().getGame()
					.addToSectorElement(stronghold);
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
		propertyHandler.setValue(newValue, oldValue, stronghold);
	}

}
