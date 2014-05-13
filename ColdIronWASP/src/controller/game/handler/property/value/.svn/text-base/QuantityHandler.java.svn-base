package controller.game.handler.property.value;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.Resource;
import model.game.SectorElement;

public class QuantityHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null)
			((Resource) sectorElement).setQuantity(newValue);
	}

	@Override
	public void setValue(String newValue, String oldValue, Object object) {

	}
}
