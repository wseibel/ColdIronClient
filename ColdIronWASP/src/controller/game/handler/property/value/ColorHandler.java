package controller.game.handler.property.value;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.Alliance;
import model.game.SectorElement;
import model.game.UserAssets;

public class ColorHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void setValue(String newValue, String oldValue, Object object) {
		/*
		if (object instanceof UserAssets)
			((UserAssets) object).setColor(newValue);*/
		if (object instanceof Alliance)
			((Alliance) object).setColor(newValue);
	}

}
