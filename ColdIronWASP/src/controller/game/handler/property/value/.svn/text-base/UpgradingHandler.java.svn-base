package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.SectorElement;
import model.game.LevelHPElement;

public class UpgradingHandler extends PropertyHandler {

	@Override
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null)
			((LevelHPElement) sectorElement).setUpgrading(Boolean
					.valueOf(newValue));
	}

}
