package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.PropertyHandler;

import model.game.Barrack;
import model.game.Farm;
import model.game.Forge;
import model.game.SectorElement;
import model.game.Game;
import model.game.Peon;
import model.game.Building;
import model.game.SectorElement;
import model.game.Stronghold;
import model.game.Tower;

public class WorkingOnHandler extends PropertyHandler {
	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// find the building in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		String id = newValue;
		Building newBuilding = null;
		if (newValue != null) {
			if (newValue.startsWith("Stronghold"))
				newBuilding = new Stronghold();
			else if (newValue.startsWith("Farm"))
				newBuilding = new Farm();
			else if (newValue.startsWith("Tower"))
				newBuilding = new Tower();
			else if (newValue.startsWith("Barrack"))
				newBuilding = new Barrack();
			else if (newValue.startsWith("Forge"))
				newBuilding = new Forge();
			else
				newBuilding = new Building();
			newBuilding.setId(newValue);
		}
		Boolean ingame = false;

		// if newValue is null, delete sectorElement with id "oldValue" in game
		if (newValue == null) {
			// check if the sectorElement which should be deleted is in the game
			// and set it null
			for (Iterator<SectorElement> sectorElementIterator = game
					.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
				SectorElement sectElement = sectorElementIterator.next();
				if (sectElement.getId().equals(oldValue)) {
					((Peon) sectorElement).setWorkingOn(null);
				}
			}
			return;
		}
		
		// check if the building already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectElement = sectorElementIterator.next();
			if (sectElement.getId().equals(id)) {
				newBuilding = (Building) sectElement;
				ingame = true;
			}
		}

		// building does not already exist
		if (!ingame) {
			newBuilding.setId(id);
			game.addToSectorElement((Building) newBuilding);
		}

		// allign the building to this sector element
		((Peon) sectorElement).setWorkingOn((Building) newBuilding);
	}

}
