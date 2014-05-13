package controller.game.handler.property.value;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.User;
import model.game.SectorElement;

public class NicknameHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void setValue(String newValue, String oldValue, Object object) {

		// set user as current player if nickname matches login name
   		if (getMessageHandler().getChainMaster().getCIClient()
   				.getCurrentPlayer().equals(newValue)) {
   			((User) object).setStartUser(true);
   		}
   		else
   			((User) object).setStartUser(false);
   		((User) object).setNickname(newValue);
   	}

}
