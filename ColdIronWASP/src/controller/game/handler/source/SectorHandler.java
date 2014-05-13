package controller.game.handler.source;

import java.util.Iterator;

import model.game.Sector;
import model.game.Game;
import model.game.Unit;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles sector related server messages
 */
public class SectorHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		Sector newSector = new Sector();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the sector already exists in the game
		for (Iterator<Sector> sectorIter = game.iteratorOfSector(); sectorIter
				.hasNext();) {
			Sector sector = sectorIter.next();
			if (id.equals(sector.getId())) {
				newSector = sector;
				ingame = true;
			}
		}

		// sector does not already exist
		if (!ingame) {
			newSector.setId(id);
			game.addToSector(newSector);
		} else {
			// check whether the sector might be out of sync with the data-model
			if (newSector.needsRefresh()) {
				newSector.removeAllFromSectorBuildings();
				newSector.removeAllFromSectorResources();
				newSector.removeAllFromSectorUnits();
				newSector.removeAllFromSelectedUnit();
				newSector.setNeedsRefresh(false);
			}			
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
		// Since the properties of a sector don't match them of a sector
		// element, the handler has to be casted
		// to non sector element handler with overloaded setValue method
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newSector);

	}
}
