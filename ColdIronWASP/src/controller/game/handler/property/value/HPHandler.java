package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.Building;
import model.game.LevelHPElement;
import model.game.SectorElement;

public class HPHandler extends PropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null) {
			((LevelHPElement) sectorElement).setHp(Integer.valueOf(newValue));

			if (sectorElement instanceof Building) {
				if (((LevelHPElement) sectorElement).getMaxHP() != null) {
					if (Integer.valueOf(newValue) < ((LevelHPElement) sectorElement)
							.getMaxHP()) {
						if(((Building) sectorElement).getSector() != null){
							((Building) sectorElement).getSector()
								.addToRepairableBuilding(
										(Building) sectorElement);
						}
						else{
							((Building) sectorElement).setRepairable(true);
						}
					}
					if (Integer.valueOf(newValue) == ((LevelHPElement) sectorElement)
							.getMaxHP()) {
						if(((Building) sectorElement).getSector() != null){
							((Building) sectorElement).getSector()
								.removeFromRepairableBuilding((Building) sectorElement);
						}
						else{
							((Building) sectorElement).setRepairable(false);
						}
					}
				}
			}
		}
	}

}
