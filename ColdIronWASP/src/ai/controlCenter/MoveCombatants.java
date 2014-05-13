package ai.controlCenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import de.upb.tools.fca.FHashSet;
import de.upb.tools.fca.FPropHashSet;

import model.game.Barrack;
import model.game.Building;
import model.game.CIClient;
import model.game.Farm;
import model.game.Forge;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Tower;
import model.game.Unit;
import model.game.UserAssets;
import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;
import ai.helper.UserAssetsCounter;

/**
 * MoveCombatants decides whether combatants must move to an other sector
 *
 */
public class MoveCombatants {
	
	private CIClient ciClient = null;
	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private String myUserAssetsID = null;
	private AIMain mainAi;
	private UserAssets userAssets = null;
	private UserAssetsCounter myUserAssetsCounter = null;
	private final boolean DEBUG = true;
	private int taktik_case = 0;
	private long timeOfLastOrder = System.currentTimeMillis();
	

	
	@SuppressWarnings("unchecked")
	public MoveCombatants(CIClient ciClient, AIHelper  aiHelper, AiModelAnalyzer aiModel) {
		super();
		this.ciClient = ciClient;
		this.aiHelper = aiHelper;
		this.aiModel = aiModel; 
		aiModel.initValues();
		this.myUserAssetsCounter = aiModel.getMyUserAssetsCounter();
		this.myUserAssetsID = myUserAssetsCounter.getId();
		ArrayList<UserAssets> allUserAssets = (ArrayList<UserAssets>) aiModel.getAllUserAssets().clone();
		Iterator<UserAssets> iterUserAssets = null;
		for(iterUserAssets = allUserAssets.listIterator(); iterUserAssets.hasNext();) {
			UserAssets tempAssets = iterUserAssets.next();
			if(tempAssets.getId().equals(myUserAssetsID)) {
				userAssets = tempAssets;
			}
		}
	}
	
	/**
	 * this method decide whether combatants must move on the map
	 * 
	 * @param mainAi
	 * 		--> main ai class
	 */
	public void decideMoveCombatants(AIMain mainAi) {
		// decision whether any combatants must be moved
		this.mainAi = mainAi;
		// give orders only from time to time (avoid new orders while executing old)
		if ((System.currentTimeMillis() - timeOfLastOrder) > mainAi.OrderInterval) {
			// prepare the lists of own sectors and those under attack
			aiModel.checkOwnSectors();
			// prepare the lists of enemies sectors
			aiModel.checkEnemySectors();
			// check, if attacking is desired
			this.decideAttacking();
			if (!mainAi.goAttacking) {
				if (mainAi.ownSectorsUnderAttack.size() > 0) {
					// move all combatants to the best sector to be defended 
					if(DEBUG && taktik_case != 1) {
						System.out.println("new taktik: sending combatants to defense");
						taktik_case = 1;
					}
					this.goDefending();
				} else {
					// neither attack nor defense
					// -> spread combatants over all own sectors (intercept scouts)
					if(DEBUG && taktik_case != 2) {
						System.out.println("new taktik: spreading combatants to protect"); 
						taktik_case = 2;
					}
					this.goProtecting();
				}
			} else {
				if (mainAi.enemySectors.size() == 0) {
					mainAi.needScouting = true;
					if(DEBUG && taktik_case != 3) {
						System.out.println("moveCombatants needs scouted targets for attack"); 
						taktik_case = 4;
					}
				} else {
					// move all combatants to the best sector to be attacked 
					if(DEBUG && taktik_case != 4) {
						System.out.println("new taktik: sending combatants to attack"); 
						taktik_case = 4;
					}
					this.goAttacking();
				}
			} 
			timeOfLastOrder = System.currentTimeMillis();
		}
	}


	

	@SuppressWarnings("unchecked")
	private void decideAttacking() {
		
		// first evaluate the combat-value of the players armies
		long ownCombatStrength = 0;
		long enemyCombatStrength = 0;
		int unitStrength = 0;
		Iterator<Sector> sectorIter = null;
		Iterator<Unit> unitIter = null;
		Sector tempSector = null;
		Unit tempUnit = null;
		FPropHashSet<Sector> allSectors = (FPropHashSet<Sector>) ciClient.getGame().getSectors().clone();
		FHashSet<Unit> allUnits = null;
		sectorIter = allSectors.iterator();
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			// evaluate the combatants on each sector (no peons, no scouts)
			if (tempSector.getUnits() != null) {
				allUnits = (FHashSet<Unit>) tempSector.getUnits().clone();
				unitIter = allUnits.iterator(); 
				while (unitIter.hasNext()) {
					tempUnit = unitIter.next();
					// first get the units strength (peons not considered)
					if (tempUnit.getId().startsWith("Swordsman")) {
						if (tempUnit.getLevel() == 1) {
							unitStrength = aiHelper.getSWORDSMAN_1_STRENGTH();
						} else {
							if (tempUnit.getLevel() == 2) {
								unitStrength = aiHelper.getSWORDSMAN_2_STRENGTH();
							} else {
								if (tempUnit.getLevel() == 3) {
									unitStrength = aiHelper.getSWORDSMAN_3_STRENGTH();
								}
							}
						}
					}
					if (tempUnit.getId().startsWith("Archer")) {
						if (tempUnit.getLevel() == 1) {
							unitStrength = aiHelper.getARCHER_1_STRENGTH();
						} else {
							if (tempUnit.getLevel() == 2) {
								unitStrength = aiHelper.getARCHER_2_STRENGTH();
							} else {
								if (tempUnit.getLevel() == 3) {
									unitStrength = aiHelper.getARCHER_3_STRENGTH();
								}
							}
						}
					}
					if (tempUnit.getId().startsWith("Knight")) {
						if (tempUnit.getLevel() == 1) {
							unitStrength = aiHelper.getKNIGHT_1_STRENGTH();
						} else {
							if (tempUnit.getLevel() == 2) {
								unitStrength = aiHelper.getKNIGHT_2_STRENGTH();
							} else {
								if (tempUnit.getLevel() == 3) {
									unitStrength = aiHelper.getKNIGHT_3_STRENGTH();
								}
							}
						}
					}
					if (tempUnit.getId().startsWith("Catapult")) {
						unitStrength = aiHelper.getCATAPULT_1_STRENGTH();
					}
					// add the units combat-value
					if (tempUnit.getUserAssets().getId() == myUserAssetsID
							&& !(tempUnit instanceof Peon)
							&& !tempUnit.isScouting()) {
						ownCombatStrength += tempUnit.getHp()*unitStrength;
					} else {
						if (tempUnit.getUserAssets().getId() != myUserAssetsID
								&& !(tempUnit instanceof Peon)) {
							enemyCombatStrength += tempUnit.getHp()*unitStrength;
						}
					}
					unitStrength = 0;
				}
			}
		}
		if (DEBUG) {
			System.out.println("ownCombatStrength == " + ownCombatStrength
					+ " and enemyCombatStrength == "	+ enemyCombatStrength);
		}
		
		Boolean oldValue = mainAi.goAttacking;
		Boolean newValue = false; // we came in peace ;) 
		// check reasons pro change to attack
		if (myUserAssetsCounter.getAllUnitsCount() == aiHelper.getUNITLIMIT()) {
			newValue = true;
			// in case of own weakness we will get slots free for better units,
			// so its good to attack even when loosing more units than enemy
		} else {
			if (enemyCombatStrength > 0) {
				if ((ownCombatStrength / enemyCombatStrength) > mainAi.attackRatio) {
					newValue = true;
				}
			} else {
				if (enemyCombatStrength == 0 && ownCombatStrength >= 45 && mainAi.enemySectors.size() > 0) {
					newValue = true;
				}
			} 
		}
		
		mainAi.goAttacking = newValue;
		// influence ai-global variables once when changed to attack
		if (!oldValue && newValue) {
			// not implemented yet
		}
		// influence ai-global variables once when changed to defense
		if (oldValue && !newValue) {
			// not implemented yet
		}
	}

	@SuppressWarnings("unchecked")
	private void goProtecting() {
		// spread combatants over all own sectors to intercept scouts
		
		// first calculate the average combatant-count per sectorToProtect
		int aveCombCount = 0;
		LinkedList<Sector> ownSectors = (LinkedList<Sector>) mainAi.ownSectors.clone();
		if (ownSectors.size() > 0) { //avoid division by zero
			aveCombCount = (myUserAssetsCounter.getAllUnitsCount()
					- myUserAssetsCounter.getPeonCount())
					/ ownSectors.size();
		}
		// collect all combatants, that are in excess on a sector and all sectors
		// that are under the quota (and need therefore more protection)
		LinkedList<Unit> excessCombatants = new LinkedList<Unit>();
		LinkedList<Sector> needySectors = new LinkedList<Sector>();
		Iterator<Sector> sectorIter = ownSectors.listIterator();
		Iterator<Unit> unitIter = null;
		FHashSet<Unit> allUnits = null;
		Sector tempSector = null;
		Unit tempUnit = null;
		int combCount = 0;
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			allUnits = (FHashSet<Unit>) tempSector.getUnits().clone();
			unitIter = allUnits.iterator(); 
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				if (tempUnit.getUserAssets().getId() == myUserAssetsID
						&& !tempUnit.isScouting()) {
					combCount++;
					if (combCount > aveCombCount) {
						// excess-combatant detected
						excessCombatants.add(tempUnit);
					}
				}
			}
			if (combCount < aveCombCount) {
				// needy sector detected
				needySectors.add(tempSector);
			}
			combCount = 0;
		}
		
		// spread them all over the needy sectors
		if (excessCombatants.size() > 0 && needySectors.size() > 0) {
			unitIter = excessCombatants.listIterator();
			sectorIter = needySectors.listIterator();
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				tempSector = sectorIter.next();
				ciClient.getServerConnection().move_units(tempUnit.getId(), tempSector.getId());
				// if all sectors got served, start again with the first one
				if (!sectorIter.hasNext()) {
					sectorIter = needySectors.listIterator();
				}
			}

		}
		
	}

	@SuppressWarnings("unchecked")
	private void goAttacking() {
		// move all combatants to the best sector to be attacked 
		// first identify the best sector to be attacked
		LinkedList<Sector> enemySectors = (LinkedList<Sector>) mainAi.enemySectors.clone();
		Iterator<Sector> sectorIter = enemySectors.listIterator();
		Iterator<Unit> unitIter = null;
		FHashSet<Unit> allUnits = null;
		Iterator<Peon> peonIter = null;
		FPropHashSet<Peon> allPeons = null;
		Iterator<Resource> resourceIter = null;
		FHashSet<Resource> allResources = null;
		Iterator<Building> buildingIter = null;
		FHashSet<Building> allBuildings = null;
		Sector tempSector = null;
		Unit tempUnit = null;
		Peon tempPeon = null;
		Resource tempResource = null;
		Building tempBuilding = null;
		boolean foundWood = false;
		boolean foundStone = false;
		boolean foundIron = false;
		int tempPriority = 0;
		int maxPriority = 0;
		Sector bestSector = null;
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			// evaluate the enemy peons there
			allUnits = (FHashSet<Unit>) tempSector.getUnits().clone();
			unitIter = allUnits.iterator(); 
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				if (tempUnit.getUserAssets().getId() != myUserAssetsID
						&& tempUnit instanceof Peon) {
					tempPriority += mainAi.attackPeon;
				}
			}
			// evaluate the resources there
			allResources = (FHashSet<Resource>) tempSector.getResources().clone();
			resourceIter = allResources.iterator(); 
			while (resourceIter.hasNext()) {
				tempResource = resourceIter.next();
				if (!foundWood && tempResource.getType().toLowerCase().equals("wood")) {
					tempPriority += mainAi.attackWood;
					foundWood = true;
				}
				if (!foundStone && tempResource.getType().toLowerCase().equals("stone")) {
					tempPriority += mainAi.attackStone;
					foundStone = true;
				}
				if (!foundIron && tempResource.getType().toLowerCase().equals("iron")) {
					tempPriority += mainAi.attackIron;
					foundIron = true;
				}
				// evaluate the peons at the resource
				allPeons = (FPropHashSet<Peon>) tempResource.getPeons().clone();
				peonIter = allPeons.iterator(); 
				while (peonIter.hasNext()) {
					tempPeon = peonIter.next();
					if (tempPeon.getUserAssets().getId() != myUserAssetsCounter.getId()) {
						tempPriority += mainAi.attackPeon;
					}
				}
			}
			// evaluate the buildings there
			allBuildings = (FHashSet<Building>) tempSector.getBuildings().clone();
			buildingIter = allBuildings.iterator() ;
			while (buildingIter.hasNext()) {
				tempBuilding = buildingIter.next();
				if (tempBuilding.getUserAssets().getId() != myUserAssetsID) {
					if (tempBuilding instanceof Farm) {
						tempPriority += mainAi.attackFarm;
					} else {
						if (tempBuilding instanceof Stronghold) {
							tempPriority += mainAi.attackStronghold;
						} else {
							if (tempBuilding instanceof Barrack) {
								tempPriority += mainAi.attackBarrack;
							} else {
								if (tempBuilding instanceof Forge) {
									tempPriority += mainAi.attackForge;
								} else {
									if (tempBuilding instanceof Tower) {
										tempPriority += mainAi.attackTower;
									}
								}
							}
						}
					}
				}
			}
			if (tempPriority > maxPriority) {
				// new best sector found
				maxPriority = tempPriority;
				bestSector = tempSector;
			}
			tempPriority = 0;
			foundWood = false;
			foundStone = false;
			foundIron = false;
		}
		
		// send all combatants there (no scouts, no peons)
		FPropHashSet<Unit> ownUnits = (FPropHashSet<Unit>) userAssets.getUnits().clone();
		unitIter = ownUnits.iterator();
		while (unitIter.hasNext()) {
			tempUnit = unitIter.next();
			if (!(tempUnit instanceof Peon) && !tempUnit.isScouting()) {
				ciClient.getServerConnection().move_units(tempUnit.getId(), bestSector.getId());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void goDefending() {
		// move all combatants to the best sector to be defended
		// first identify the best sector to be defended
		LinkedList<Sector> ownSectorsUnderAttack = (LinkedList<Sector>) mainAi.ownSectorsUnderAttack.clone();
		Iterator<Sector> sectorIter = ownSectorsUnderAttack.listIterator();
		Iterator<Unit> unitIter = null;
		FHashSet<Unit> allUnits = null;
		Iterator<Peon> peonIter = null;
		FPropHashSet<Peon> allPeons = null;
		Iterator<Resource> resourceIter = null;
		FHashSet<Resource> allResources = null;
		Iterator<Building> buildingIter = null;
		FHashSet<Building> allBuildings = null;
		Sector tempSector = null;
		Unit tempUnit = null;
		Peon tempPeon = null;
		Resource tempResource = null;
		Building tempBuilding = null;
		boolean foundWood = false;
		boolean foundStone = false;
		boolean foundIron = false;
		int tempPriority = 0;
		int maxPriority = 0;
		Sector bestSector = null;
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			// evaluate the own peons there
			allUnits = (FHashSet<Unit>) tempSector.getUnits().clone();
			unitIter = allUnits.iterator(); 
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				if (tempUnit.getUserAssets().getId() == myUserAssetsID
						&& tempUnit instanceof Peon
						&& !tempUnit.isScouting()) {
					tempPriority += mainAi.defendPeon;
				}
			}
			// evaluate the resources there
			allResources = (FHashSet<Resource>) tempSector.getResources().clone();
			resourceIter = allResources.iterator(); 
			while (resourceIter.hasNext()) {
				tempResource = resourceIter.next();
				if (!foundWood && tempResource.getType().toLowerCase().equals("wood")) {
					tempPriority += mainAi.defendWood;
					foundWood = true;
				}
				if (!foundStone && tempResource.getType().toLowerCase().equals("stone")) {
					tempPriority += mainAi.defendStone;
					foundStone = true;
				}
				if (!foundIron && tempResource.getType().toLowerCase().equals("iron")) {
					tempPriority += mainAi.defendIron;
					foundIron = true;
				}
				// evaluate the peons at the resource
				allPeons = (FPropHashSet<Peon>) tempResource.getPeons().clone();
				peonIter = allPeons.iterator(); 
				while (peonIter.hasNext()) {
					tempPeon = peonIter.next();
					if (tempPeon.getUserAssets().getId() == myUserAssetsCounter.getId()) {
						tempPriority += mainAi.defendPeon;
					}
				}
			}
			// evaluate the buildings there
			allBuildings = (FHashSet<Building>) tempSector.getBuildings().clone();
			buildingIter = allBuildings.iterator() ;
			while (buildingIter.hasNext()) {
				tempBuilding = buildingIter.next();
				if (tempBuilding.getUserAssets().getId() == myUserAssetsID) {
					if (tempBuilding instanceof Farm) {
						tempPriority += mainAi.defendFarm;
					} else {
						if (tempBuilding instanceof Stronghold) {
							tempPriority += mainAi.defendStronghold;
						} else {
							if (tempBuilding instanceof Barrack) {
								tempPriority += mainAi.defendBarrack;
							} else {
								if (tempBuilding instanceof Forge) {
									tempPriority += mainAi.defendForge;
								} else {
									if (tempBuilding instanceof Tower) {
										tempPriority += mainAi.defendTower;
									}
								}
							}
						}
					}
				}
			}
			if (tempPriority > maxPriority) {
				// new best sector found
				maxPriority = tempPriority;
				bestSector = tempSector;
			}
			tempPriority = 0;
			foundWood = false;
			foundStone = false;
			foundIron = false;
		}

		// send all combatants there (no scouts, no peons)
		FPropHashSet<Unit> ownUnits = (FPropHashSet<Unit>) userAssets.getUnits().clone();
		unitIter = ownUnits.iterator();
		unitIter = userAssets.iteratorOfUnits();
		while (unitIter.hasNext()) {
			tempUnit = unitIter.next();
			if (!(tempUnit instanceof Peon) && !tempUnit.isScouting()) {
				ciClient.getServerConnection().move_units(tempUnit.getId(), bestSector.getId());
			}
		}
	}
}



