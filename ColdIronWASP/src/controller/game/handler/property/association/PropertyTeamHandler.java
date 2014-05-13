package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Team;
import model.game.Game;
import model.game.SectorElement;
import model.game.User;

public class PropertyTeamHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(String newValue, String oldValue, Object object) {
		// find the team in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		Team newTeam = new Team();
		String id = newValue;
		Boolean ingame = false;

		// if newValue is null, delete team with id "oldValue" in game
		if (newValue == null) {
			// check if the team which should be deleted is in the game
			// and set it null
			for (Iterator<Team> teamIterator = game
					.iteratorOfTeam(); teamIterator.hasNext();) {
				Team team = teamIterator.next();
				if (team.getId().equals(oldValue)) {
					team.removeFromUser((User) object);
				}
			}
			return;
		}
		// check if the team already exists in the game
		for (Iterator<Team> teamIterator = game.iteratorOfTeam(); teamIterator
				.hasNext();) {
			Team team = teamIterator.next();
			if (team.getId().equals(id)) {
				newTeam = team;
				ingame = true;
			}
		}

		// user asset does not already exist
		if (!ingame) {
			newTeam.setId(id);
			game.addToTeam(newTeam);
		}

		// allign the team to the user
		((User) object).setTeam(newTeam);

	}

}
