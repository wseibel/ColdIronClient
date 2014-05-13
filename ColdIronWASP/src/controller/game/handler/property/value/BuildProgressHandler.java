package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.SectorElement;
import model.game.Building;

public class BuildProgressHandler extends PropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null){
			((Building) sectorElement).setBuildProgress(Double
					.valueOf(newValue));
			if(Double.valueOf(newValue) == 0.01){
				if(((Building) sectorElement).getSector() != null){
					((Building) sectorElement).getSector().addToConstructableBuilding((Building) sectorElement);
				}
				else{
					((Building) sectorElement).setConstructable(true);
				}
			}
		}
		if (oldValue != null){
			if(Double.valueOf(newValue) == 1.0){
				if(((Building) sectorElement).getSector() != null){
					((Building) sectorElement).getSector().removeFromConstructableBuilding((Building) sectorElement);
				}
				else{
					((Building) sectorElement).setConstructable(false);
				}
			}
		}
	}

}