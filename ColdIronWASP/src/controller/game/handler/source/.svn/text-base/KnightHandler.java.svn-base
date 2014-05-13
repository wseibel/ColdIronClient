package controller.game.handler.source;

import java.util.Iterator;
import model.game.Knight;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles knight related server messages
 */
public class KnightHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	public void handle(JSONObject jsonObject) throws JSONException {

		Knight knight = new Knight();

		String id = jsonObject.getString("@src");
		Boolean newKnight = true;

		// check if the knight already exists in a sector
		for (@SuppressWarnings("unchecked")
		Iterator<SectorElement> iter = getChainMaster().getCIClient().getGame()
				.iteratorOfSectorElement(); iter.hasNext();) {
			SectorElement sectorElement = iter.next();
			if (id.equals(sectorElement.getId())) {
				knight = (Knight) sectorElement;
				newKnight = false;
			}
		}

		// knight does not already exist
		if (newKnight) {
			knight.setId(id);
			getChainMaster().getCIClient().getGame().addToSectorElement(knight);
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
		propertyHandler.setValue(newValue, oldValue, knight);
	}

}
