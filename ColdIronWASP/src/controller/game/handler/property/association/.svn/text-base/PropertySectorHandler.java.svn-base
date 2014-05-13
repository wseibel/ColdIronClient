package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.PropertyHandler;

import model.game.Sector;
import model.game.Building;
import model.game.Game;
import model.game.Resource;
import model.game.Sector;
import model.game.SectorElement;
import model.game.Unit;

public class PropertySectorHandler extends PropertyHandler {

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {

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
			Iterator<Sector> sectorIterator = game.iteratorOfSector();
			while (sectorIterator.hasNext()) {
				Sector sector = sectorIterator.next();
				if (sector.getId().equals(oldValue)) {
					if (sectorElement instanceof Unit)
						sector.removeFromSectorUnits((Unit) sectorElement);
					if (sectorElement instanceof Building) {
						sector.removeFromSectorBuildings((Building) sectorElement);
						sector.removeFromConstructableBuilding((Building) sectorElement);
						sector.removeFromRepairableBuilding((Building) sectorElement);
					}
					if (sectorElement instanceof Resource)
						sector.removeFromSectorResources((Resource) sectorElement);
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

		if (sectorElement instanceof Unit)
			// allign the unit to this sector
			newSector.addToSectorUnits((Unit) sectorElement);
		if (sectorElement instanceof Building){
			// allign the building to this sector
			newSector.addToSectorBuildings((Building) sectorElement);
			if (((Building) sectorElement).getRepairable() == true){
				newSector.addToRepairableBuilding((Building) sectorElement);
			}
			if (((Building) sectorElement).getRepairable() == false){
				newSector.removeFromRepairableBuilding((Building) sectorElement);
			}
			if (((Building) sectorElement).getConstructable() == true){
				newSector.addToConstructableBuilding((Building) sectorElement);
			}
			if (((Building) sectorElement).getConstructable() == false){
				newSector.removeFromConstructableBuilding((Building) sectorElement);
			}
		}
		if (sectorElement instanceof Resource)
			// allign the Resource to this sector
			newSector.addToSectorResources((Resource) sectorElement);
	}

}
