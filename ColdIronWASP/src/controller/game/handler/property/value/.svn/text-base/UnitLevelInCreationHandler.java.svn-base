package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.Building;
import model.game.LevelHPElement;
import model.game.SectorElement;

public class UnitLevelInCreationHandler extends PropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null)
			((Building) sectorElement)
					.setUnitLevelInCreation(Integer.valueOf(newValue));
	}

}
