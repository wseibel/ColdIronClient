package controller.game.handler.property.value;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.SectorElement;
import model.game.Resource;
import model.game.Message;

public class TypeHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		((Resource) sectorElement).setType(newValue);
	}

	@Override
	public void setValue(String newValue, String oldValue, Object object) {
		((Message) object).setType(newValue);

	}
}
