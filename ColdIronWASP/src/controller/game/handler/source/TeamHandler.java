package controller.game.handler.source;

import java.util.Iterator;

import model.game.Game;
import model.game.Team;

import org.json.JSONException;
import org.json.JSONObject;

import controller.game.handler.ChainMaster;
import controller.game.handler.MessageHandler;
import controller.game.handler.NonSectorElementPropertyHandler;
import controller.game.handler.PropertyHandler;

/**
 * Handles team related server messages
 */
public class TeamHandler extends MessageHandler {
	/**
	 * Forwards the properties of the jsonObject to a property handler
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public void handle(JSONObject jsonObject) throws JSONException {
		boolean ingame = false;
		Team newTeam = new Team();
		ChainMaster chainMaster = getChainMaster();
		String id = jsonObject.get("@src").toString();
		Game game = chainMaster.getCIClient().getGame();

		// check if the team already exists in the game
		for (Iterator<Team> teamIter = game.iteratorOfTeam(); teamIter
				.hasNext();) {
			Team team = teamIter.next();
			if (id.equals(team.getId())) {
				newTeam = team;
				ingame = true;
			}
		}

		// team does not already exist
		if (!ingame) {
			newTeam.setId(id);
			game.addToTeam(newTeam);
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
		// Since the properties of a team don't match them of a team
		// element, the handler has to be casted
		// to non team element handler with overloaded setValue method
		((NonSectorElementPropertyHandler) propertyHandler).setValue(newValue,
				oldValue, (Object) newTeam);
	}

}
