package controller.game.handler.source;

import java.util.Iterator;

import model.game.Archer;
import model.game.SectorElement;
import model.game.Game;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles archer related server messages
 */
public class ArcherHandler extends MessageHandler {

	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		Archer newArcher = new Archer();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the archer already exists in a sector
		for (Iterator<SectorElement> sectorElementIter = game
				.iteratorOfSectorElement(); sectorElementIter.hasNext();) {
			SectorElement sectorElement = sectorElementIter.next();
			if (id.equals(sectorElement.getId())) {
				newArcher = (Archer) sectorElement;
				ingame = true;
			}
		}

		// archer does not already exist
		if (!ingame) {
			newArcher.setId(id);
			game.addToSectorElement(newArcher);
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
		propertyHandler.setValue(newValue, oldValue, newArcher);

	}

}
