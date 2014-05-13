package controller.game.handler.source;

import java.util.Iterator;

import model.game.SectorElement;
import model.game.Tower;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles tower related server messages
 */
public class TowerHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 */
	public void handle(JSONObject jsonObject) throws JSONException {
		Tower tower = new Tower();

		String id = jsonObject.getString("@src");
		Boolean newTower = true;

		// check if the tower already exists in a sector
		@SuppressWarnings("unchecked")
		Iterator<SectorElement> sectorIterator = getChainMaster().getCIClient()
				.getGame().iteratorOfSectorElement();
		for (Iterator<SectorElement> sectorElementIter = sectorIterator; sectorElementIter
				.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if (id.equals(sectorElement.getId())) {
				tower = (Tower) sectorElement;
				newTower = false;
			}
		}

		// tower does not already exist
		if (newTower) {
			tower.setId(id);
			getChainMaster().getCIClient().getGame().addToSectorElement(tower);
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
		propertyHandler.setValue(newValue, oldValue, tower);
	}

}
