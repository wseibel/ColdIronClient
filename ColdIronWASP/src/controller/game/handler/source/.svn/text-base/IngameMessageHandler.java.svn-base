package controller.game.handler.source;

import java.util.Iterator;

import model.game.Game;
import model.game.Message;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles message related server messages
 */
public class IngameMessageHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		Message newMessage = new Message();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the message already exists in the game
		for (Iterator<Message> messageIter = game.iteratorOfMessage(); messageIter
				.hasNext();) {
			Message message = messageIter.next();
			if (id.equals(message.getId())) {
				newMessage = (Message) message;
				ingame = true;
			}
		}

		// message does not already exist
		if (!ingame) {
			newMessage.setId(id);
			game.addToMessage(newMessage);
		}

		// get new value
		String newValue = null;
		if (jsonObject.has("@nv")) {
			newValue = jsonObject.getString("@nv");
		}

		// get old value
		String oldValue = null;
		if (jsonObject.has("@ov")) {
			oldValue = jsonObject.getString("@ov");
		}

		// Let the specialized property handler handle the values
		PropertyHandler propertyHandler = getPropertyHandlerMap().get(
				jsonObject.get("@prop").toString());
		// Since the properties of a message don't match them of a message
		// element, the handler has to be casted
		// to non message element handler with overloaded setValue method
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newMessage);
	}

}
