package model.game;

import java.util.Iterator;
import java.util.LinkedList;

import controller.game.handler.ChainMaster;
import view.game.ingameForCiDummy;
import view.game.ingameMain;
import view.game.buttons.SelectUnits;
import model.lobby.ServerConnection;



/*
 * helps to generate commands when choices are possible after a user-decision
 * e.g. select individual units to do a job on an individual building when only
 * unitnumber and buildingtype has been specified   
 */
public class CommandHelper {
	
	//enable late initialization (on method calls) to avoid nulls at early stages
	public boolean dummyOn = false; 
	public boolean getDummyOn() { 
		return this.dummyOn;
	}
	
	public boolean AIOn = false; 
	public void setAIOn(boolean AIOn) { 
		this.AIOn = AIOn;
	}
	
	private ingameForCiDummy mainDummy; 
	
	public void setMainDummy(ingameForCiDummy mainDummy) { 
		this.mainDummy = mainDummy;
	}
	public ingameForCiDummy getMainDummy() { 
		return this.mainDummy;
	}
	
	private ingameMain main; 
	
	public void setMain(ingameMain main) { 
		this.main = main;
	}
	public ingameMain getMain() { 
		return this.main;
	}
	
	private SelectUnits selUnits;
	private ServerConnection servCon;
	private Sector currentSector;
	public void setCurrentSector(Sector currentSector) { 
		this.currentSector = currentSector;
	}
	private UserAssets currentUser;
	private ChainMaster chainMaster;
	
	public ChainMaster getChainMaster() {
		return chainMaster;
	}
	
	public Boolean setChainMaster(ChainMaster chainMaster) {
		
		Boolean changed = false;

		if(this.chainMaster != chainMaster) {
			ChainMaster oldValue = this.chainMaster;
			if(this.chainMaster != null) {
				this.chainMaster = null;
				oldValue.setCommandHelper(null);
			}
			this.chainMaster = chainMaster;
			
			if(chainMaster != null) {
				chainMaster.setCommandHelper(this);
			}
			changed = true;
		}
		
		
		return changed;
	}
	
	
	public void init() {
			if (dummyOn) {
				selUnits = mainDummy.getSelectUnitButtons();
				servCon = mainDummy.getCiClient().getServerConnection();
				currentSector = mainDummy.getCurrentSector();
				currentUser = mainDummy.getCurrentUser().getUserAssets();
				this.setChainMaster(currentSector.getGame().getCIClient().getChainMaster());
			} else {
				if (!AIOn) {
					selUnits = main.getSelectUnitButtons();
					servCon = main.getCiClient().getServerConnection();
					currentSector = main.getCurrentSector();
					currentUser = main.getCurrentUser().getUserAssets();
					this.setChainMaster(currentSector.getGame().getCIClient().getChainMaster());
				} else {
					// AI is on -> currentSector must be set fresh each time the AI calls
					// methods from here (and the currentSector must be disconnected from the
					// inGame-currentSector which gets changed by GUI-interaction)
					selUnits = null;
					servCon = main.getCiClient().getServerConnection();
					currentSector = null;
					currentUser = main.getCurrentUser().getUserAssets();
					this.setChainMaster(currentUser.getGame().getCIClient().getChainMaster());
				}
			}
	}
	
	
	/*
	 * moves some units equal to the current unit-selection to a sector 
	 * @Param sectorID
	 * 	the ID of the sector to move to 
	 */
	@SuppressWarnings("unchecked")
	public void moveUnitsToSector(String sectorID) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Iterator<Unit> unitIter = null;
		int stillNeededLv1 = 0;
		int stillNeededLv2 = 0;
		int stillNeededLv3 = 0;
		
		// get the selected peons
		if (selUnits.getNumberSelectedPeonsAll() == 0){
			// only some got selected and only level 1 possible
			stillNeededLv1 = selUnits.getNumberSelectedPeons();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
						Peon tempPeon = (Peon) tempUnit;
						if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
							//Peon is idle - select him for being moved
							units += tempUnit.getId() + ",";
							stillNeededLv1--;
						}
					}
			}
			if (stillNeededLv1 > 0) {
				System.err.println("more peons than are available got selected for being moved to sector");
			}
		} else {
			// all got selected
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
						Peon tempPeon = (Peon) tempUnit;
						if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
							//Peon is idle - select him for being moved
							units += tempUnit.getId() + ",";
						}
					}
			}
		}
		
		// get the selected swordsmen
		if (selUnits.getNumberSelectedSwordsmanAll() == 0){
			// only some got selected - level matters
			stillNeededLv1 = selUnits.getNumberSelectedSwordsmanLevel1();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Swordsman && tempUnit.getLevel() == 1 && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv1--;				
					}
			}
			stillNeededLv2 = selUnits.getNumberSelectedSwordsmanLevel2();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv2 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Swordsman && tempUnit.getLevel() == 2 && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv2--;				
					}
			}
			stillNeededLv3 = selUnits.getNumberSelectedSwordsmanLevel3();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv3 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Swordsman && tempUnit.getLevel() == 3 && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv3--;				
					}
			}
			if (stillNeededLv1 > 0 || stillNeededLv2 > 0 || stillNeededLv3 > 0) {
				System.err.println("more swordsman than are available got selected for being moved to sector");
			}
			
		} else {
			// all got selected - level does not matter
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Swordsman && tempUnit.getUserAssets() == currentUser) {
							units += tempUnit.getId() + ",";
						}
			}
		}
		
		// get the selected archers
		if (selUnits.getNumberSelectedArcherAll() == 0){
			// only some got selected - level matters
			stillNeededLv1 = selUnits.getNumberSelectedArcherLevel1();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getLevel() == 1 && tempUnit.getUserAssets() == currentUser) {
						Archer tempArcher = (Archer) tempUnit;
						if (tempArcher.getTower() == null) {
							//Archer is idle - select him for being moved
							units += tempUnit.getId() + ",";
							stillNeededLv1--;
						}
					}
			}
			stillNeededLv2 = selUnits.getNumberSelectedArcherLevel2();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv2 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getLevel() == 2 && tempUnit.getUserAssets() == currentUser) {
						Archer tempArcher = (Archer) tempUnit;
						if (tempArcher.getTower() == null) {
							//Archer is idle - select him for being moved
							units += tempUnit.getId() + ",";
							stillNeededLv2--;
						}			
					}
			}
			stillNeededLv3 = selUnits.getNumberSelectedArcherLevel3();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv3 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getLevel() == 3 && tempUnit.getUserAssets() == currentUser) {
						Archer tempArcher = (Archer) tempUnit;
						if (tempArcher.getTower() == null) {
							//Archer is idle - select him for being moved
							units += tempUnit.getId() + ",";
							stillNeededLv3--;
						}				
					}
			}
			if (stillNeededLv1 > 0 || stillNeededLv2 > 0 || stillNeededLv3 > 0) {
				System.err.println("more archer than are available got selected for being moved to sector");
			}
		} else {
			// all got selected - level does not matter
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getUserAssets() == currentUser) {
							units += tempUnit.getId() + ",";
						}
			}
		}
	
		// get the selected knights
		if (selUnits.getNumberSelectedKnightAll() == 0){
			// only some got selected - level matters
			stillNeededLv1 = selUnits.getNumberSelectedKnightLevel1();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Knight && tempUnit.getLevel() == 1 && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv1--;				
					}
			}
			stillNeededLv2 = selUnits.getNumberSelectedKnightLevel2();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv2 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Knight && tempUnit.getLevel() == 2 && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv2--;				
					}
			}
			stillNeededLv3 = selUnits.getNumberSelectedKnightLevel3();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv3 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Knight && tempUnit.getLevel() == 3 && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv3--;				
					}
			}
			if (stillNeededLv1 > 0 || stillNeededLv2 > 0 || stillNeededLv3 > 0) {
				System.err.println("more knights than are available got selected for being moved");
			}
			
		} else {
			// all got selected - level does not matter
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Knight && tempUnit.getUserAssets() == currentUser) {
							units += tempUnit.getId() + ",";
						}
			}
		}
		
		// get the selected catapults
		if (selUnits.getNumberSelectedCatapultsAll() == 0){
			// only some got selected and only level 1 possible
			stillNeededLv1 = selUnits.getNumberSelectedCatapults();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Catapult && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						stillNeededLv1--;
					}
			}
			if (stillNeededLv1 > 0) {
				System.err.println("more catapults than are available got selected for being moved to sector");
			}
		} else {
			// all got selected
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Catapult && tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
					}
			}
		}
		
		//remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		} else {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have not selected any units for being moved");
			return;
		}
		
		servCon.move_units(units, sectorID);
		
		if (dummyOn) {
			mainDummy.setSelection("");
		}else {
			main.setSelection("");
		}
	}
	
	
	/*
	 * moves some archers equal to the current unit-selection to some towers
	 * of one level on the current sector
	 * @Param level
	 * 	the level of the towers to move in 
	 */
	@SuppressWarnings("unchecked")
	public void moveArchersIntoTower(int level) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		LinkedList<String> units = new LinkedList<String>();
		LinkedList<String> towerSlots = new LinkedList<String>();
		Unit tempUnit = null;
		Building tempBuilding = null;
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
		int stillNeededLv1 = 0;
		int stillNeededLv2 = 0;
		int stillNeededLv3 = 0;
		
		// get the selected archers 
		if (selUnits.getNumberSelectedArcherAll() == 0){
			// only some got selected - level matters
			stillNeededLv1 = selUnits.getNumberSelectedArcherLevel1();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getLevel() == 1 && tempUnit.getUserAssets() == currentUser) {
						Archer tempArcher = (Archer) tempUnit;
						if (tempArcher.getTower() == null) {
							//Archer is idle - select him for being moved to tower
							units.add(tempUnit.getId());
							stillNeededLv1--;
						}
					}
			}
			stillNeededLv2 = selUnits.getNumberSelectedArcherLevel2();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv2 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getLevel() == 2 && tempUnit.getUserAssets() == currentUser) {
						Archer tempArcher = (Archer) tempUnit;
						if (tempArcher.getTower() == null) {
							//Archer is idle - select him for being moved to tower
							units.add(tempUnit.getId());
							stillNeededLv2--;
						}			
					}
			}
			stillNeededLv3 = selUnits.getNumberSelectedArcherLevel3();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv3 > 0);) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getLevel() == 3 && tempUnit.getUserAssets() == currentUser) {
						Archer tempArcher = (Archer) tempUnit;
						if (tempArcher.getTower() == null) {
							//Archer is idle - select him for being moved to tower
							units.add(tempUnit.getId());
							stillNeededLv3--;
						}				
					}
			}
			if (stillNeededLv1 > 0 || stillNeededLv2 > 0 || stillNeededLv3 > 0) {
				System.err.println("more archer than are available got selected for being moved into towers");
			}
		} else {
			// all got selected - level does not matter
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
					if (tempUnit instanceof Archer && tempUnit.getUserAssets() == currentUser) {
						units.add(tempUnit.getId());
						}
			}
		}
		
		//get the free towers of the selected level (
		for (buildingIter = currentSector.iteratorOfSectorBuildings(); buildingIter.hasNext();) {
			tempBuilding = buildingIter.next();
				if (tempBuilding instanceof Tower && tempBuilding.getLevel() == level && tempBuilding.getUserAssets() == currentUser) {
					Tower tempTower = (Tower) tempBuilding;
					int freeSlots = level + 1 - tempTower.sizeOfArcher();
					while(freeSlots > 0) {
						//if there is space for more archers select this tower once for each free slot
						towerSlots.add(tempTower.getId());
						freeSlots--;
					}
				}
		}
		
		//place the selected archers into the free towers
		if (units.size() != 0) {
			if (towerSlots.size() != 0) {
				if (units.size() <= towerSlots.size()) {
					for (int i = 0; i < units.size(); i++) {
						servCon.move_units(units.get(i), towerSlots.get(i));
						if (dummyOn) {
							mainDummy.setSelection("");
						}else {
							main.setSelection("");
						}
					}
				} else {
					currentSector.getGame().getCIClient().getChainMaster().setChatString("ERROR_MESSAGE:ERROR:in the towers of level "
							+ level +" is not enough space for all selected Archers");
				}
			} else {
				currentSector.getGame().getCIClient().getChainMaster().setChatString("ERROR_MESSAGE:ERROR:in the towers of level "
						+ level +" is no more space for any Archers");
			}
		} else {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have not selected any archers for being moved into towers");
		}
		
	}
	
	
	/*
	 * moves some peons equal to the current unit-selection to harvest
	 * a resource on the current sector
	 * @Param resource
	 * 	the type of the resource to harvest ("WOOD"|"STONE|"IRON"") 
	 */
	@SuppressWarnings("unchecked")
	public void movePeonsToResource(String resource) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Resource tempResource = null;
		Iterator<Unit> unitIter = null;
		Iterator<Resource> resIter = null;
		int stillNeededLv1 = 0;
		
		// get the selected peons
		if (selUnits.getNumberSelectedPeonsAll() == 0){
			// only some got selected and only level 1 possible
			stillNeededLv1 = selUnits.getNumberSelectedPeons();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
				if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
					Peon tempPeon = (Peon) tempUnit;
					if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
						//Peon is idle - select him for being moved to resource
						units += tempUnit.getId() + ",";
						stillNeededLv1--;
					}
				}
			}
			if (stillNeededLv1 > 0) {
				System.err.println("more peons than are available got selected for being moved to resource");
			}
		} else {
			// all got selected
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
				if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
					Peon tempPeon = (Peon) tempUnit;
					if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
						//Peon is idle - select him for being moved to resource
						units += tempUnit.getId() + ",";
					}
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		} else {
			System.out.println("no peons selected but tried to move some to resource"); 
			return;
		}
		
		// get the resource and move the peons to it
		for (resIter = currentSector.iteratorOfSectorResources(); resIter.hasNext();) {
			tempResource = resIter.next();
			if (tempResource.getType().contains(resource)) { 
				servCon.move_units(units, tempResource.getId());
				if (dummyOn) {
					mainDummy.setSelection("");
				}else {
					main.setSelection("");
				}
				return;
			}
		}
		chainMaster.setChatString("ERROR_MESSAGE:ERROR:no " + resource + " on the current sector");
		
	}
	
	public void movePeonsToResource(String resource, String peon) {
		servCon.move_units(peon, resource);
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public void moveUnitsToSector(String sectorID, String units) {
		// TODO Auto-generated method stub
		servCon.move_units(units, sectorID);
		/*
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	/*
	 * upgrade a building of a buildingtype an a level on the current sector to 
	 * the next level
	 *@Param buildingtype
	 * the type of the building e.g. "Barrack" (Barrack|Stronghold|Tower)  
	 *@Param level
	 *  the current level of the building  
	 */
	@SuppressWarnings("unchecked")
	public void upgradeBuilding(String buildingtype, int currentLevel) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		Building tempBuilding = null;
		Iterator<Building> buildingIter = null;
		Integer targetLevel = (currentLevel + 1);
				
		// get a Building of the buildingtype and the currentLevel
		for (buildingIter = currentSector.iteratorOfSectorBuildings(); buildingIter.hasNext();) {
			tempBuilding = buildingIter.next();
			if (tempBuilding.getClass().toString().contains(buildingtype)
					&& tempBuilding.getUserAssets() == currentUser
					&& tempBuilding.getLevel() == currentLevel
					&& !tempBuilding.isUpgrading()) {
				servCon.upgrade(tempBuilding.getId(), targetLevel.toString());
				return;
			}
		}
		chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have no " + buildingtype + " that could be upgraded");
	}
	
	
	/*
	 * let some peons equal to the current unit-selection repair 
	 * a building of a buildingtype of a level on the current sector 
	 *@Param buildingtype
	 * the type of the building e.g. "Barrack" (Farm|Forge|Barrack|Stronghold|Tower)  
	 *@Param level
	 *  the level of the building (should be 1 in case of Farm and Forge)
	 */
	@SuppressWarnings("unchecked")
	public void repairBuilding(String buildingtype, int level) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
			Unit tempUnit = null;
		Building tempBuilding = null;
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
		int stillNeededLv1 = 0;
				
		// get the selected peons
		if (selUnits.getNumberSelectedPeonsAll() == 0){
			// only some got selected and only level 1 possible
			stillNeededLv1 = selUnits.getNumberSelectedPeons();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
				if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
					Peon tempPeon = (Peon) tempUnit;
					if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
						//Peon is idle - select him for repairing a building
						units += tempUnit.getId() + ",";
						stillNeededLv1--;
					}
				}
			}
			if (stillNeededLv1 > 0) {
				System.err.println("more peons than are available got selected for repairing a "
									+ buildingtype +" of level " + level);
			}
		} else {
			// all got selected
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
				if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
					Peon tempPeon = (Peon) tempUnit;
					if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
						//Peon is idle - select him for repairing a building
						units += tempUnit.getId() + ",";
					}
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		} else {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have not selected any peons for repair");
			return;
		}
				
		// get a Building of the buildingtype and level that needs repair and send the peons to it
		for (buildingIter = currentSector.iteratorOfRepairableBuilding(); buildingIter.hasNext();) {
				tempBuilding = buildingIter.next();
				if (tempBuilding.getClass().toString().contains(buildingtype)
						&& tempBuilding.getLevel() == level 
						&& tempBuilding.getUserAssets() == currentUser) {
					servCon.repair(tempBuilding.getId(), units);
					if (dummyOn) {
						mainDummy.setSelection("");
					}else {
						main.setSelection("");
					}
					return;
				}
			}
		chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have no "
				+ buildingtype + " of level " +	level + " that needs repair");
	}
	
	public void repairBuilding(String building, String peons) {
		servCon.repair(building, peons);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * let some peons equal to the current unit-selection build up 
	 * a building of a buildingtype on the current sector 
	 * @Param buildingtype
	 *  the type of the building e.g. "Barrack" (Farm|Forge|Barrack|Stronghold|Tower)  
	 */
	@SuppressWarnings("unchecked")
	public void buildUpBuilding(String buildingtype) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Building tempBuilding = null;
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
		int stillNeededLv1 = 0;
		

		// get the selected peons
		if (selUnits.getNumberSelectedPeonsAll() == 0){
			// only some got selected and only level 1 possible
			stillNeededLv1 = selUnits.getNumberSelectedPeons();
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext() && (stillNeededLv1 > 0);) {
				tempUnit = unitIter.next();
				if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
					Peon tempPeon = (Peon) tempUnit;
					if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
						//Peon is idle - select him for repairing a building
						units += tempUnit.getId() + ",";
						stillNeededLv1--;
					}
				}
			}
			if (stillNeededLv1 > 0) {
				System.err.println("more peons than are available got selected for building up a " + buildingtype);
			}
		} else {
			// all got selected
			for (unitIter = currentSector.iteratorOfSectorUnits(); unitIter.hasNext();) {
				tempUnit = unitIter.next();
				if (tempUnit instanceof Peon && tempUnit.getUserAssets() == currentUser) {
					Peon tempPeon = (Peon) tempUnit;
					if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
						//Peon is idle - select him for building up a building
						units += tempUnit.getId() + ",";
					}
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		} else {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have no peons selected for building up");
			return;
		}

		// get a Building of the buildingtype that can be built up and send the peons to it
		for (buildingIter = currentSector.iteratorOfConstructableBuilding(); buildingIter.hasNext();) {
			tempBuilding = buildingIter.next();
			if (tempBuilding.getClass().toString().contains(buildingtype)
					&& tempBuilding.getUserAssets() == currentUser) {
				servCon.build_up(tempBuilding.getId(), units);
				if (dummyOn) {
					mainDummy.setSelection("");
				}else {
					main.setSelection("");
				}
				return;
			}
		}		
		chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have no " + buildingtype +
				           " that could be building up");
	}
	
	public void buildUpBuilding(String building, String peons) {
		servCon.build_up(building, peons);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * destroys an unfinished building of a buildingtype on the
	 * current sector (to get invested resources back)
	 *@Param buildingtype
	 * the type of the building e.g. "Barrack" (Farm|Forge|Barrack|Stronghold|Tower)  
	 */
	@SuppressWarnings("unchecked")
	public void dropBuilding(String buildingtype) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		Building tempBuilding = null;
		Iterator<Building> buildingIter = null;
		
		// get a Building of the buildingtype that can be dropped and drop it
		for (buildingIter = currentSector.iteratorOfConstructableBuilding(); buildingIter.hasNext();) {
			tempBuilding = buildingIter.next();
			if (tempBuilding.getClass().toString().contains(buildingtype) && tempBuilding.getUserAssets() == currentUser) {
				servCon.drop_building(tempBuilding.getId());
				return;
			}
		}
		System.err.println("tried to drop " + buildingtype + " but no such building can be dropped on the current sector");
	}
	
	
	/*
	 * builds a unit of a unittype and a level in a building of a buildingtype on the
	 * current sector
	 * @Param unittype
	 * 	the type of the unit e.g. "swordsman" 
	 * @Param buildingtype
	 * 	the type of the building e.g. "barrack"
	 * @Param level
	 *  the level of the unit
	 *    
	 */
	@SuppressWarnings("unchecked")
	public void createUnit(String buildingType, String unitType, int level) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		Building creatingBuilding = null;
		Iterator<Building> buildingIterator = currentUser.iteratorOfBuildings();
		while (buildingIterator.hasNext()) {
			Building building = buildingIterator.next();
			if (building.getSector().equals(currentSector)) {
				if (buildingType.toLowerCase().equals("forge")) {
					if (building instanceof Forge
							&& building.getUnitTypeInCreation().equals("UNKNOWN")
							&& building.getLevel() >= level
							&& building.getBuildProgress() == 1.0) {
						creatingBuilding = (Forge) building;
					}
				} else if (buildingType.toLowerCase().equals("barrack")) {
					if (building instanceof Barrack
							&& building.getUnitTypeInCreation().equals("UNKNOWN")
							&& building.getLevel() >= level
							&& building.getBuildProgress() == 1.0) {
						creatingBuilding = (Barrack) building;
					}
				} else if (buildingType.toLowerCase().equals("stronghold")) {
					if (building instanceof Stronghold
							&& building.getUnitTypeInCreation().equals("UNKNOWN")
							&& building.getLevel() >= level
							&& building.getBuildProgress() == 1.0) {
						creatingBuilding = (Stronghold) building;
					}
				}
			}
		}
		if(creatingBuilding != null){
			System.out.println("creating in " + buildingType + " a/an " + unitType + " of level " + level);
			servCon.create_unit(creatingBuilding.getId(), unitType.toUpperCase(), String.valueOf(level));
		}
		else{
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you have no building available to create a/an " +
					unitType);
		}
	}
	
	public void createUnit(String buildingID, String unitType, String level) {
		if(buildingID != "" && unitType != "" && level != ""){
			servCon.create_unit(buildingID, unitType, level);
			/*
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}else{
			System.err.println("Tryed to create Unit, but one or more strings not null!");
		}
	}
	
	
	
	/*
	 * removes some peons from harvesting
	 * a resource on the current sector (sets them back to that sector)
	 * @Param resource
	 * 	the type of the resource to harvest ("WOOD"|"STONE|"IRON"") 
	 * @Param count
	 *  the number of peons to be removed
	 */
	@SuppressWarnings("unchecked")
	public void removePeonsFromResource(String resource, int count) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Resource tempResource = null;
		Iterator<Unit> unitIter = null;
		Iterator<Resource> resIter = null;
			
		// for all resources of the type...
		for (resIter = currentSector.iteratorOfSectorResources(); resIter.hasNext() && (count > 0);) {
			tempResource = resIter.next();
			if (tempResource.getType().contains(resource)) {
				// ...get some peons that could be removed
				for (unitIter = tempResource.iteratorOfPeon(); unitIter.hasNext() && (count > 0);) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						count--;
					}
					
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		}
		if (count > 0) {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you can not remove more peons than are currently harvesting");
			return;
		}
		// send them back to the current sector				
		servCon.move_units(units, currentSector.getId());
	}
	
	
	/*
	 * removes some peons from repairing a building of a level on the current sector (sets them back to that sector)
	 * @Param buildingtype
	 * the type of the building e.g. "Barrack" (Farm|Forge|Barrack|Stronghold|Tower)  
	 *  @Param level
	 *  the level of the building (
	 * @Param count
	 *  the number of peons to be removed
	 */
	@SuppressWarnings("unchecked")
	public void stopRepairBuilding(String buildingtype, int level, int count) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Building tempBuilding = null;
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
				
		// for all repairable buildings of the type and the level...
		for (buildingIter = currentSector.iteratorOfRepairableBuilding(); buildingIter.hasNext() && (count > 0);) {
			tempBuilding = buildingIter.next();
			if (tempBuilding.getClass().toString().contains(buildingtype)
					&& tempBuilding.getUserAssets() == currentUser
					&& tempBuilding.getLevel() == level) { 
				// ...get some peons that could be removed 
				for (unitIter = tempBuilding.iteratorOfPeon(); unitIter.hasNext() && (count > 0);) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						count--;
					}
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		}
		if (count > 0) {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you can not remove more peons than are currently repairing");
			return;
		}
		// send them back to the current sector				
		servCon.move_units(units, currentSector.getId());
	}
	
	
	/*
	 * removes some peons from building up a building on the current sector (sets them back to that sector)
	 * @Param buildingtype
	 * 	the type of the building e.g. "Barrack" (Farm|Forge|Barrack|Stronghold|Tower)  
	 * @Param count
	 *  the number of peons to be removed
	 */
	@SuppressWarnings("unchecked")
	public void stopBuildUpBuilding(String buildingtype, int count) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Building tempBuilding = null;
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
			
		// for all constructible buildings of the type...
		for (buildingIter = currentSector.iteratorOfConstructableBuilding(); buildingIter.hasNext() && (count > 0);) {
			tempBuilding = buildingIter.next();
			if (tempBuilding.getClass().toString().contains(buildingtype)
					&& tempBuilding.getUserAssets() == currentUser) { 
				// ...get some peons that could be removed 
				for (unitIter = tempBuilding.iteratorOfPeon(); unitIter.hasNext() && (count > 0);) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						count--;
					}
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		}
		if (count > 0) {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you can not remove more peons than are building up");
			return;
		}
		// send them back to the current sector				
		servCon.move_units(units, currentSector.getId());	
	}
	
	
	/*
	 * removes some archers from towers of a level on the current sector (sets them back to that sector)
	 * @Param level
	 * 	the level of the towers e.g. 1 (1|2|3)  
	 * @Param count
	 *  the number of archers to be removed
	 */
	@SuppressWarnings("unchecked")
	public void removeArchersFromTower(int level, int count) {
		init();
//		if (AIOn) {
//			chainMaster.setChatString("ERROR_MESSAGE:ERROR:yes, you can do nothing but watch the AI");
//			return;
//		}
		String units = "";
		Unit tempUnit = null;
		Building tempBuilding = null;
		Tower tempTower = null;
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
			
		// for all towers of the level...
		for (buildingIter = currentSector.iteratorOfSectorBuildings(); buildingIter.hasNext() && (count > 0);) {
			tempBuilding = buildingIter.next();
			if (tempBuilding instanceof Tower && tempBuilding.getLevel() == level
					&& tempBuilding.getUserAssets() == currentUser) {
				 tempTower = (Tower) tempBuilding;
				// ...get some archers that could be removed 
				for (unitIter = tempTower.iteratorOfArcher(); unitIter.hasNext() && (count > 0);) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() == currentUser) {
						units += tempUnit.getId() + ",";
						count--;
					}
				}
			}
		}
		// remove the last ","
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		}
		if (count > 0) {
			chainMaster.setChatString("ERROR_MESSAGE:ERROR:you can not remove more archers than are currently there in your Towers of level "
								+ level);
			return;
		}
		// send them back to the current sector				
		servCon.move_units(units, currentSector.getId());
	}
	
}

