package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.PropertyHandler;

import model.game.SectorElement;
import model.game.Game;
import model.game.Resource;
import model.game.Peon;

public class CollectingHandler extends PropertyHandler {
	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {

		// find the resource in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Resource newResource = new Resource();
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
					((Resource) sectElement).removeFromPeon((Peon) sectorElement);
				}
			}
			return;
		}
		
		// check if the resource already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectElement = sectorElementIterator.next();
			if (sectElement.getId().equals(id)) {
				newResource = (Resource) sectElement;
				ingame = true;
			}
		}

		// resource does not already exist
		if (!ingame) {
			newResource.setId(id);
			game.addToSectorElement(newResource);
		}

		// allign the resource to this sector element
		((Peon) sectorElement).setCollecting(newResource);
	}

}
