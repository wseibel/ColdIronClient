package controller.game.handler.property.association;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.SectorElement;
import model.game.Game;

public class UsersToStartHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void setValue(String newValue, String oldValue, Object object) {
		if (newValue != null)
			((Game) object).setUsersToStart(Integer.valueOf(newValue));
	}

}
