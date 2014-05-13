package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.User;
import model.game.Game;
import model.game.Sector;
import model.game.SectorElement;
import model.game.UserAssets;

public class StartingUserHandler extends NonSectorElementPropertyHandler {

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		// find the userAssets in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		UserAssets newUserAssets = new UserAssets();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete startingUser with id "oldValue" in game
		if (newValue == null) {
			// check if the startingUser which should be deleted is in the game
			// and set it null
			for (Iterator<UserAssets> userAssetsIterator = game
					.iteratorOfUserAssets(); userAssetsIterator.hasNext();) {
				UserAssets userAssets = userAssetsIterator.next();
				if (userAssets.getId().equals(oldValue)) {
					userAssets.setStartSector(null);
				}
			}
			return;
		}
		// check if the userAssets already exists in the game
		for (Iterator<UserAssets> userAssetsIterator = game
				.iteratorOfUserAssets(); userAssetsIterator.hasNext();) {
			UserAssets userAssets = userAssetsIterator.next();
			if (userAssets.getId().equals(id)) {
				newUserAssets = userAssets;
				ingame = true;
			}
		}

		// user asset does not already exist
		if (!ingame) {
			newUserAssets.setId(id);
			game.addToUserAssets(newUserAssets);
		}

		// allign the userAssets to the sector startingUser
		((Sector) object).setStartingUser(newUserAssets);
	}

	@Override
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {

	}

}
