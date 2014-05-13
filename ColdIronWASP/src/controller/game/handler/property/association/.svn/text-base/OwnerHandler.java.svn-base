package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.User;
import model.game.Game;
import model.game.Message;
import model.game.SectorElement;

public class OwnerHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
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

		// if newValue is null, delete owner with id "oldValue" in game
		if (newValue == null) {
			// check if the owner which should be deleted is in the game
			// and set it null
			for (Iterator<User> ownerIterator = game.iteratorOfUser(); ownerIterator
					.hasNext();) {
				User owner = ownerIterator.next();
				if (owner.getId().equals(oldValue)) {
					owner.removeFromMessage((Message) object);
				}
			}
			return;
		}

		// check if the user already exists in the game
		for (Iterator<User> userIterator = game.iteratorOfUser(); userIterator
				.hasNext();) {
			User user = userIterator.next();
			if (user.getId().equals(id)) {
				newUser = (User) user;
				ingame = true;
			}
		}

		// user does not already exist
		if (!ingame) {
			newUser.setId(id);
			game.addToUser(newUser);
		}

		// allign the user to the message
		((Message) object).setOwner(newUser);
	}

}
