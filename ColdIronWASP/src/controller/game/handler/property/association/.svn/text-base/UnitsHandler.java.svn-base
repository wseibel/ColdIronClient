package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Archer;
import model.game.Catapult;
import model.game.Knight;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.Swordsman;
import model.game.Unit;
import model.game.Game;
import model.game.SectorElement;
import model.game.UserAssets;

public class UnitsHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		// find the unit in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();

		String id = newValue;
		Unit newUnit = null;
		if (newValue != null) {
			if (newValue.startsWith("Peon"))
				newUnit = new Peon();
			else if (newValue.startsWith("Swordsman"))
				newUnit = new Swordsman();
			else if (newValue.startsWith("Knight"))
				newUnit = new Knight();
			else if (newValue.startsWith("Archer"))
				newUnit = new Archer();
			else if (newValue.startsWith("Catapult"))
				newUnit = new Catapult();
			else
				newUnit = new Unit();
			newUnit.setId(newValue);
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
					if (object instanceof UserAssets)
						((UserAssets) object)
								.removeFromUnits((Unit) sectElement);
					if (object instanceof Sector)
						((Sector) object)
								.removeFromSectorUnits((Unit) sectElement);
				}
			}
			return;
		}

		// check if the unit already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectElement = sectorElementIterator.next();
			if (sectElement.getId().equals(id)) {
				newUnit = (Unit) sectElement;
				ingame = true;
			}
		}

		// unit does not already exist
		if (!ingame) {
			newUnit.setId(id);
			game.addToSectorElement(newUnit);
		}

		if (object instanceof UserAssets)
			// allign the resource to the user assets
			((UserAssets) object).addToUnits(newUnit);

		if (object instanceof Sector)
			// allign the resource to the sector
			((Sector) object).addToSectorUnits(newUnit);
	}

}
