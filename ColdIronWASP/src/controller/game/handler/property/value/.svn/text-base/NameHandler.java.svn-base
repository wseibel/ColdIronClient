package controller.game.handler.property.value;

import controller.game.handler.NonSectorElementPropertyHandler;
import model.game.Alliance;
import model.game.Game;
import model.game.Map;
import model.game.SectorElement;
import model.game.Team;

public class NameHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void setValue(String newValue, String oldValue, Object object) {
		// if the given object has one of the classes with setName method,
		// set the name
		if (new Game().getClass().isAssignableFrom(object.getClass())) {
			((Game) object).setName(newValue);
		}
		if (new Map().getClass().isAssignableFrom(object.getClass())) {
			((Map) object).setName(newValue);
		}
		if (new Team().getClass().isAssignableFrom(object.getClass())) {
			((Team) object).setName(newValue);
		}
		if (new Alliance().getClass().isAssignableFrom(object.getClass())) {
			((Alliance) object).setName(newValue);
		}
	}
}
