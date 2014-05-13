package controller.game.handler.property.association;

import java.util.Iterator;

import model.game.Alliance;
import model.game.SectorElement;
import model.game.User;
import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.Game;

public class WinnerHandler extends NonSectorElementPropertyHandler  {

	@Override
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(String newValue, String oldValue, Object object) {
		try{
			User userWinner = null;
			Alliance allianceWinner = null;
			String id = newValue;
			Game game = getMessageHandler().getChainMaster().getCIClient().getGame();
			
			// Try to find the user who won the game
			for (Iterator<User> userIterator = game.iteratorOfUser(); userIterator
					.hasNext();) {
				User user = userIterator.next();
				if (user.getId().equals(id)) {
					userWinner = user;
				}
			}
			if(userWinner != null){
				((Game) object).setUserWinner(userWinner);
				return;
			}
			
			// Try to find the alliance which won the game
			for (Iterator<Alliance> allianceIterator = game.iteratorOfUser(); allianceIterator
					.hasNext();) {
				Alliance alliance = allianceIterator.next();
				if (alliance.getId().equals(id)) {
					allianceWinner = alliance;
				}
			}
			if(allianceWinner != null){
				((Game) object).setAllianceWinner(allianceWinner);
				return;
			}
			// If neither a user nor an alliance was found, throw exception
			else{
				throw new Exception ();
			}
		}
		catch (Exception e){
			System.err.println("Winner could not be found in game");
		}
		
	}

}
