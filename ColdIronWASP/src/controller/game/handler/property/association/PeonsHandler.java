package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;
import model.game.Archer;
import model.game.Building;
import model.game.Catapult;
import model.game.Game;
import model.game.Knight;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.SectorElement;
import model.game.Swordsman;
import model.game.Unit;
import model.game.UserAssets;

public class PeonsHandler extends NonSectorElementPropertyHandler {
	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// find the unit in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();

		String id = newValue;
		Peon newPeon = new Peon();
		if (newValue != null) {
			newPeon.setId(newValue);
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
					if (sectElement instanceof Resource) {
						((Resource) sectorElement)
								.removeFromPeon((Peon) sectElement);
					}
					if (sectElement instanceof Building) {
						((Building) sectElement).removeFromPeon(newPeon);
					}
				}
			}
			return;
		}

		// check if the unit already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectElement = sectorElementIterator.next();
			if (sectElement.getId().equals(id)) {
				newPeon = (Peon) sectElement;
				ingame = true;
			}
		}

		// unit does not already exist
		if (!ingame) {
			newPeon.setId(id);
			game.addToSectorElement(newPeon);
		}
		if (sectorElement instanceof Resource) {
			// allign the resource to the user assets
			((Resource) sectorElement).addToPeon(newPeon);
		}
		if (sectorElement instanceof Building) {
			((Building) sectorElement).addToPeon(newPeon);
		}
	}

	public void setValue(String newValue, String oldValue, Object object) {

	}

}
