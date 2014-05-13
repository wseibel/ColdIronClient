package controller.game.handler.property.association;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.Map;
import model.game.Game;
import model.game.Sector;
import model.game.SectorElement;

public class PropertyMapHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void setValue(String newValue, String oldValue, Object object) {
		// find the map in the game or create a new one
		Map newMap = new Map();

		String id = newValue;
		newMap.setId(id);

		// map does already exist
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();

		if (game.getMap() != null) {
			newMap = game.getMap();
		}

		if (object instanceof Game) {
			// if newValue is null, delete map with id "oldValue" in game
			if (newValue == null) {
				// check if the map which should be deleted is in the game
				// and set it null
				game.setMap(null);
				return;
			}

			((Game) object).setMap(newMap);
		}
		if (object instanceof Sector) {
			if (newValue == null) {
				newMap.removeFromSector((Sector) object);
				return;
			}
			((Sector) object).setMap(newMap);
		}
	}

}
