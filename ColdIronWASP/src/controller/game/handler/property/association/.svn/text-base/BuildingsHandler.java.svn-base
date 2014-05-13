package controller.game.handler.property.association;

import java.util.Iterator;

import controller.game.handler.NonSectorElementPropertyHandler;

import model.game.Archer;
import model.game.Barrack;
import model.game.Catapult;
import model.game.Farm;
import model.game.Forge;
import model.game.Knight;
import model.game.Peon;
import model.game.SectorElement;
import model.game.Game;
import model.game.Building;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Swordsman;
import model.game.Tower;
import model.game.Unit;
import model.game.UserAssets;

public class BuildingsHandler extends NonSectorElementPropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		throw new UnsupportedOperationException("not implemented");
	}

	@SuppressWarnings("unchecked")
	public void setValue(String newValue, String oldValue, Object object) {
		// find the building in the game or create a new one
		Game game = getMessageHandler().getChainMaster().getCIClient()
				.getGame();
		String id = newValue;
		Building newBuilding = null;
		if (newValue != null) {
			if (newValue.startsWith("Stronghold"))
				newBuilding = new Stronghold();
			else if (newValue.startsWith("Farm"))
				newBuilding = new Farm();
			else if (newValue.startsWith("Tower"))
				newBuilding = new Tower();
			else if (newValue.startsWith("Barrack"))
				newBuilding = new Barrack();
			else if (newValue.startsWith("Forge"))
				newBuilding = new Forge();
			else
				newBuilding = new Building();
			newBuilding.setId(newValue);
		}
		Boolean ingame = false;

		// if newValue is null, delete sectorElement with id "oldValue" in game
		if (newValue == null) {
			// check if the sectorElement which should be deleted is in the game
			// and set it null
			for (Iterator<SectorElement> sectorElementIterator = game
					.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
				SectorElement sectorElement = sectorElementIterator.next();
				if (sectorElement.getId().equals(oldValue)) {
					if (object instanceof Sector){
						sectorElement.removeYou();
					}
					if (object instanceof UserAssets)
						// allign the resource to the user assets
						((UserAssets) object).removeFromBuildings((Building)sectorElement);
				}
			}
			return;
		}

		// check if the building already exists in the game
		for (Iterator<SectorElement> sectorElementIterator = game
				.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
			SectorElement sectElement = sectorElementIterator.next();
			if (sectElement.getId().equals(id)) {
				newBuilding = (Building) sectElement;
				ingame = true;
			}
		}

		// building does not already exist
		if (!ingame) {
			newBuilding.setId(id);
			game.addToSectorElement(newBuilding);
		}

		if (object instanceof UserAssets)
			// allign the resource to the user assets
			((UserAssets) object).addToBuildings(newBuilding);

		if (object instanceof Sector){
			if (newBuilding.getRepairable() == true){
				((Sector) object).addToRepairableBuilding(newBuilding);
			}
			if (newBuilding.getRepairable() == false){
				((Sector) object).removeFromRepairableBuilding(newBuilding);
			}
			if (newBuilding.getConstructable() == true){
				((Sector) object).addToConstructableBuilding(newBuilding);
			}
			if (newBuilding.getConstructable() == false){
				((Sector) object).removeFromConstructableBuilding(newBuilding);
			}
			// allign the resource to the sector
			((Sector) object).addToSectorBuildings(newBuilding);
		}
	}

}
