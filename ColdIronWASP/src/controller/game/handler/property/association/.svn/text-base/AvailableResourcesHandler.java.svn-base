package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Game;
import model.game.Resource;
import model.game.Sector;
import model.game.SectorElement;

public class AvailableResourcesHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		
		// find the sectorElement in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Resource newResource = new Resource();
		String id = newValue;
		Boolean ingame = false;
		
		// if newValue is null, delete resource with id "oldValue" in game
		if(newValue == null){
			// check if the sectorElement which should be deleted is in the game and set it null
			for (Iterator<SectorElement> sectorElementIterator = game
					.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
				SectorElement sectorElement = sectorElementIterator.next();
				if (sectorElement.getId().equals(oldValue)) {
					sectorElement.removeYou();
				}
			}
			return;
		}
		
		// check if the sectorElement already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectorElement = sectorElementIterator.next();
			if (sectorElement.getId().equals(id)) {
				newResource = (Resource) sectorElement;
				ingame = true;
			}
		}

		// sector element does not already exist
		if (!ingame) {
			newResource.setId(id);
			game.addToSectorElement(newResource);
		}

		// allign the sector element to the sector
		((Sector) object).addToSectorResources(newResource);
	}

}
