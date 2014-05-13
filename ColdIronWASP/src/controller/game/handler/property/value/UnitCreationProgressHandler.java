package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.Building;
import model.game.SectorElement;

public class UnitCreationProgressHandler extends PropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null)
			((Building) sectorElement).setUnitCreationProgress(Double
					.valueOf(newValue));
	}

}
