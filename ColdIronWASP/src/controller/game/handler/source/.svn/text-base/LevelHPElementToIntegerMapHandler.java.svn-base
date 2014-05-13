package controller.game.handler.source;

import java.util.Iterator;

import model.game.Archer;
import model.game.Game;
import model.game.LevelHPElement;
import model.game.SectorElement;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;

public class LevelHPElementToIntegerMapHandler extends MessageHandler {
	private LevelHPElement levelHPElement;

	@SuppressWarnings("unchecked")
	@Override
	public void handle(JSONObject jsonObject) throws JSONException {
		ChainMaster chainMaster = getChainMaster();
		Game game = chainMaster.getCIClient().getGame();
		
		// if the key is sent, get the element in the game and hold it locally
		if (jsonObject.getString("@prop").equals("key")) {
			String id = jsonObject.getString("@nv");
			// check if the archer already exists in a sector
			for (Iterator<SectorElement> sectorElementIter = game
					.iteratorOfSectorElement(); sectorElementIter.hasNext();) {
				SectorElement sectorElement = sectorElementIter.next();
				if (id.equals(sectorElement.getId())) {
					levelHPElement = ((LevelHPElement) sectorElement);
				}
			}
		}
		// if the value is sent, set the value to the local element
		if (jsonObject.getString("@prop").equals("value")) {
			String value = jsonObject.getString("@nv");
			if (levelHPElement != null)
				levelHPElement.setBuildingTime(Integer.valueOf(value));
		}

	}

}
