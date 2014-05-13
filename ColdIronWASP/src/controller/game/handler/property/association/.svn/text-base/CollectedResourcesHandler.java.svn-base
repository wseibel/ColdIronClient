package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.SectorElement;
import model.game.Game;
import model.game.Resource;
import model.game.UserAssets;

public class CollectedResourcesHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {

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
				SectorElement sectorElement = sectorElementIterator.next();
				if (sectorElement.getId().equals(oldValue)) {
					((UserAssets) object)
							.removeFromCollectedResources((Resource) sectorElement);
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

		if (object instanceof UserAssets)
			// allign the resource to the user assets
			((UserAssets) object).addToCollectedResources(newResource);
	}

}
