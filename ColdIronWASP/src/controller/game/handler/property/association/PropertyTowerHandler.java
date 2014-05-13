package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.PropertyHandler;

import model.game.Building;
import model.game.Game;
import model.game.Tower;
import model.game.Archer;
import model.game.SectorElement;

public class PropertyTowerHandler extends PropertyHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// find the tower in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Tower newTower = new Tower();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete sectorElement with id "oldValue" in game
		if (newValue == null) {
			// check if the sectorElement which should be deleted is in the game
			// and set it null
			for (Iterator<SectorElement> sectorElementIterator = game
					.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
				SectorElement sectElement = sectorElementIterator.next();
				if (sectElement.getId().equals(oldValue)) {
					((Tower) sectElement)
							.removeFromArcher((Archer) sectElement);
				}
			}
			return;
		}

		// check if the tower already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectElement = sectorElementIterator.next();
			if (sectElement.getId().equals(id)) {
				newTower = (Tower) sectElement;
				ingame = true;
			}
		}

		// tower does not already exist
		if (!ingame) {
			newTower.setId(id);
			game.addToSectorElement(newTower);
		}

		if (sectorElement instanceof Archer)
			// allign the tower to the sector
			((Archer) sectorElement).setTower(newTower);

	}

}
