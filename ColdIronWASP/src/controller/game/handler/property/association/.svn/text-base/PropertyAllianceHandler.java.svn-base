package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Game;
import model.game.SectorElement;
import model.game.Alliance;
import model.game.UserAssets;

public class PropertyAllianceHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		// find the alliance in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Alliance newAlliance = new Alliance();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete alliance with id "oldValue" in game
		if (newValue == null) {
			// check if the alliance which should be deleted is in the game
			// and set it null
			for (Iterator<Alliance> allianceIterator = game
					.iteratorOfAlliance(); allianceIterator.hasNext();) {
				Alliance alliance = allianceIterator.next();
				if (alliance.getId().equals(oldValue)) {
					if (object instanceof UserAssets)
						alliance.removeFromUserAssets((UserAssets) object);
					if (object instanceof Game)
						alliance.removeYou();
				}
			}
			return;
		}

		// check if the alliance already exists in the game
		for (Iterator<Alliance> allianceIterator = game.iteratorOfAlliance(); allianceIterator
				.hasNext();) {
			Alliance alliance = allianceIterator.next();
			if (alliance.getId().equals(id)) {
				newAlliance = (Alliance) alliance;
				ingame = true;
			}
		}

		// alliance does not already exist
		if (!ingame) {
			newAlliance.setId(id);
			game.addToAlliance(newAlliance);
		}

		if (object instanceof UserAssets)
			// allign the alliance to the user assets
			((UserAssets) object).setAlliance(newAlliance);

		if (object instanceof Game)
			// allign the alliance to the user assets
			((Game) object).addToAlliance(newAlliance);
	}

}
