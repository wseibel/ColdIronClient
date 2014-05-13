package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.SectorElement;
import model.game.Unit;


public class StrengthHandler extends PropertyHandler{

	@Override
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null)
			((Unit) sectorElement)
					.setStrength(Integer.valueOf(newValue));
		
	}

}
