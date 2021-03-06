package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.User;
import model.game.Game;
import model.game.SectorElement;
import model.game.UserAssets;

public class PropertyUserHandler extends NonSectorElementPropertyHandler {

	private static int userAssetsCounter = 0;

	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {

		// find the user in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		User newUser = new User();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete user with id "oldValue" in game
		if (newValue == null) {
			// check if the user which should be deleted is in the game
			// and set it null
			for (Iterator<User> userIterator = game.iteratorOfUser(); userIterator
					.hasNext();) {
				User user = userIterator.next();
				if (user.getId().equals(oldValue)) {
					user.removeYou();
				}
			}
			return;
		}
		// check if the user already exists in the game
		for (Iterator<User> userIterator = game.iteratorOfUser(); userIterator
				.hasNext();) {
			User user = userIterator.next();
			if (user.getId().equals(id)) {
				newUser = user;
				ingame = true;
			}
		}

		// user does not already exist
		if (!ingame) {
			newUser.setId(id);
			game.addToUser(newUser);

		}

		// allign the user to the user assets
		((UserAssets) object).setUser(newUser);

		
	}

}
