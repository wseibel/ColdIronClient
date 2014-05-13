package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Sector;
import model.game.Game;
import model.game.Map;
import model.game.Sector;
import model.game.SectorElement;

public class PropertySectorsHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		// find the sector in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Sector newSector = new Sector();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete sector with id "oldValue" in game
		if (newValue == null) {
			// check if the sector which should be deleted is in the game
			// and set it null
			for (Iterator<Sector> sectorIterator = game
					.iteratorOfSector(); sectorIterator.hasNext();) {
				Sector sector = sectorIterator.next();
				if (sector.getId().equals(oldValue)) {
					sector.removeYou();
				}
			}
			return;
		}
		
		// check if the sector already exists in the game
		for (Iterator<Sector> sectorIterator = game.iteratorOfSector(); sectorIterator
				.hasNext();) {
			Sector sector = sectorIterator.next();
			if (sector.getId().equals(id)) {
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

		// allign the sector to the map
		((Map) object).addToSector(newSector);
	}

}
