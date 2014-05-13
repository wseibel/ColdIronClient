package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Message;
import model.game.Game;
import model.game.SectorElement;
import model.game.Message;
import model.game.User;

public class MessagesHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		// find the message in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Message newMessage = new Message();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete message with id "oldValue" in game
		if (newValue == null) {
			// check if the message which should be deleted is in the game
			// and set it null
			for (Iterator<Message> messageIterator = game
					.iteratorOfMessage(); messageIterator.hasNext();) {
				Message message = messageIterator.next();
				if (message.getId().equals(oldValue)) {
					message.removeYou();
				}
			}
			return;
		}
		
		// check if the message already exists in the game
		for (Iterator<Message> messageIterator = game.iteratorOfMessage(); messageIterator
				.hasNext();) {
			Message message = messageIterator.next();
			if (message.getId().equals(id)) {
				newMessage = (Message) message;
				ingame = true;
			}
		}

		// message does not already exist
		if (!ingame) {
			newMessage.setId(id);
			game.addToMessage(newMessage);
		}

		// allign the message to the user
		((User) object).addToMessage(newMessage);
	}

}
