package controller.game.handler.source;

import java.util.Iterator;

import model.game.Barrack;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles barrack related server messages
 */
public class BarrackHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Barrack barrack = new Barrack();

		String id = jsonObject.getString("@src");
		Boolean newBarrack = true;

		// check if the barrack already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> sectorIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> sectorElementIter = sectorIterator; sectorElementIter
				.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if (id.equals(sectorElement.getId())) {
				barrack = (Barrack) sectorElement;
				newBarrack = false;
			}
		}

		// barrack does not already exist
		if (newBarrack) {
			barrack.setId(id);
			getChainMaster().getCIClient().getGame()
					.addToSectorElement(barrack);
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
		propertyHandler.setValue(newValue, oldValue, barrack);
	}

}
