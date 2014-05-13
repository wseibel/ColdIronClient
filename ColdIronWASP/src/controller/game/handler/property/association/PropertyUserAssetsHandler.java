package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Alliance;
import model.game.Resource;
import model.game.User;
import model.game.UserAssets;
import model.game.Building;
import model.game.Game;
import model.game.Unit;
import model.game.SectorElement;

public class PropertyUserAssetsHandler extends NonSectorElementPropertyHandler {

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {

		UserAssets newUserAssets = new UserAssets();
		String id = newValue;
		Boolean ingame = false;
		Game game = getMessageHandler().getChainMaster().getCIClient().getGame();

		// if newValue is null, delete userAssets with id "oldValue" in game
		if (newValue == null) {
			// check if the userAssets which should be deleted is in the game
			// and set it null
			for (Iterator<UserAssets> userAssetsIterator = game.iteratorOfUserAssets(); userAssetsIterator.hasNext();) {
				UserAssets userAssets = userAssetsIterator.next();
				if (userAssets.getId().equals(oldValue)) {
					if(object instanceof Game)
						userAssets.removeYou();
					if(object instanceof Alliance)
						((Alliance)object).removeFromUserAssets(userAssets);
					if(object instanceof User)
						((User)object).setUserAssets(null);
				}
			}
			return;
		}

		// check if the userAssets already exists in the game
		for (Iterator<UserAssets> userAssetsIterator = game
				.iteratorOfUserAssets(); userAssetsIterator.hasNext();) {
			UserAssets userAssets = userAssetsIterator.next();
			if (id.equals(userAssets.getId())) {
				newUserAssets = (UserAssets) userAssets;
				ingame = true;
			}
		}

		// user asset does not already exist
		if (!ingame) {
			newUserAssets.setId(id);
			game.addToUserAssets(newUserAssets);
		}
		
		if (object instanceof Alliance){
			((Alliance)object).addToUserAssets(newUserAssets);
		}
		if (object instanceof Game){
			((Game) object).addToUserAssets(newUserAssets);
		}
		if (object instanceof User){
			((User) object).setUserAssets(newUserAssets);
		}

	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {

		// find the userAssets in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		UserAssets newUserAssets = new UserAssets();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete userAssets with id "oldValue" in game
		if (newValue == null) {
			// check if the userAssets which should be deleted is in the game
			// and set it null
			for (Iterator<UserAssets> userAssetsIterator = game
					.iteratorOfUserAssets(); userAssetsIterator.hasNext();) {
				UserAssets userAssets = userAssetsIterator.next();
				if (userAssets.getId().equals(oldValue)) {
					if(sectorElement instanceof Unit){
						userAssets.removeFromUnits((Unit) sectorElement);
					}
					if(sectorElement instanceof Building){
						userAssets.removeFromBuildings((Building) sectorElement);
					}
					if(sectorElement instanceof Resource){
						userAssets.removeFromCollectedResources((Resource) sectorElement);
					}
				}
			}
			return;
		}
		// check if the userAssets already exists in the game
		for (Iterator<UserAssets> userAssetsIterator = game
				.iteratorOfUserAssets(); userAssetsIterator.hasNext();) {
			UserAssets userAssets = userAssetsIterator.next();
			if (userAssets.getId().equals(id)) {
				newUserAssets = (UserAssets) userAssets;
				ingame = true;
			}
		}

		// user asset does not already exist
		if (!ingame) {
			newUserAssets.setId(id);
			game.addToUserAssets(newUserAssets);
		}

		// allign the sector element to the userAssets
		if (sectorElement instanceof Unit)
			newUserAssets.addToUnits((Unit) sectorElement);
		if (sectorElement instanceof Building)
			newUserAssets.addToBuildings((Building) sectorElement);
		if (sectorElement instanceof Resource)
			newUserAssets.addToCollectedResources((Resource) sectorElement);
	}
}
