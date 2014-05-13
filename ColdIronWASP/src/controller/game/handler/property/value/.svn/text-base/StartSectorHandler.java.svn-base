package controller.game.handler.property.value;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Game;
import model.game.SectorElement;
import model.game.Sector;
import model.game.User;
import model.game.UserAssets;

public class StartSectorHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {

		// if the object has the class Sector, set the boolean value
		if (new Sector().getClass().isAssignableFrom(object.getClass()))
			((Sector) object).setStartSector(Boolean.valueOf(newValue));

		// if the object has the class UserAssets, allign the sector to the user
		// asset
		if (new UserAssets().getClass().isAssignableFrom(object.getClass())) {
			Game game = getMessageHandler().getChainMaster().getCIClient()
					.getGame();
			Sector newSector = new Sector();
			String id = newValue;
			Boolean ingame = false;
			
			// if newValue is null, delete sector with id "oldValue" in game
			if (newValue == null) {
				// check if the sector which should be deleted is in the game
				// and set it null
				for (Iterator<Sector> sectorIterator = game.iteratorOfSector(); sectorIterator
						.hasNext();) {
					Sector sector = sectorIterator.next();
					if (sector.getId().equals(oldValue)) {
						((UserAssets) object).setStartSector(null);
					}
				}
				return;
			}

			// check if the sector already exists in the game
			for (Iterator<Sector> sectorIterator = game.iteratorOfSector(); sectorIterator
					.hasNext();) {
				Sector sector = sectorIterator.next();
				if (sector.getId().equals(id)) {
					newSector = (Sector) sector;
					ingame = true;
				}
			}

			// sector does not already exist
			if (!ingame) {
				newSector.setId(id);
				game.addToSector(newSector);
			}

			// allign the sector to the userAssets as start sector
			((UserAssets) object).setStartSector(newSector);
		}
	}

}
