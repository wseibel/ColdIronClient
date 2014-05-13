package controller.game.handler.property.association;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.Game;
import model.game.Map;
import model.game.SectorElement;
import model.game.UserAssets;

public class PropertyGameHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void setValue(String newValue, String oldValue, Object object) {
		
		Game game = getMessageHandler().getChainMaster().getCIClient().getGame();

		if (object instanceof Map) {
			if (((Map) object).getGame() == null) {
				((Map) object).setGame(game);
			}
		}
		if (object instanceof UserAssets) {
			if (((UserAssets) object).getGame() == null) {
				((UserAssets) object).setGame(game);
			}
		}
	}

}
