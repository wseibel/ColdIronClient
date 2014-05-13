package ai.helper;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import de.upb.tools.fca.FPropHashSet;

import ai.controlCenter.AIMain;

import model.game.Archer;
import model.game.Barrack;
import model.game.Building;
import model.game.CIClient;
import model.game.Catapult;
import model.game.Farm;
import model.game.Forge;
import model.game.Knight;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Swordsman;
import model.game.Tower;
import model.game.Unit;
import model.game.UserAssets;

public class AiModelAnalyzer {

	private CIClient ciClient;
	private ArrayList<UserAssets> allUserAssets = new ArrayList<UserAssets>();
	private UserAssetsCounter myUserAssetsCounter;
	private ArrayList<UserAssetsCounter> opponentsUserAssetsCounter = new ArrayList<UserAssetsCounter>();
	private AIMain mainAi;

	private enum Change {
		ADD, SUBTRACT
	};

	public AiModelAnalyzer(CIClient ciClient) {
		this.ciClient = ciClient;
	}

	/**
	 * Initializes the userAssetsCounters with values
	 */
	@SuppressWarnings("unchecked")
	public void initValues() {
		allUserAssets = new ArrayList<UserAssets>();
		myUserAssetsCounter = null;
		opponentsUserAssetsCounter = new ArrayList<UserAssetsCounter>();
		// find your and your opponents user assets in game
		try {
			Iterator<UserAssets> userAssetsIterator = ciClient.getGame()
					.iteratorOfUserAssets();
			while (userAssetsIterator.hasNext()) {
				UserAssets userAssets = userAssetsIterator.next();
				allUserAssets.add(userAssets);
				if (userAssets.getUser().getStartUser()) {
					myUserAssetsCounter = new UserAssetsCounter(
							userAssets.getId());
				} else {
					opponentsUserAssetsCounter.add(new UserAssetsCounter(
							userAssets.getId()));
				}
			}
		} catch (Exception e) {
			System.err.println("AiModelAnalyzer: Could not find user assets");
		}

		// get all units, assign them to the user asset and separate them by
		// unit type and level
		for (int i = 0; i < allUserAssets.size(); i++) {
			try {
				Iterator<Unit> unitIterator = allUserAssets.get(i)
						.iteratorOfUnits();
				while (unitIterator.hasNext()) {
					changeUnitCount(allUserAssets.get(i), unitIterator.next(),
							Change.ADD);
				}

			} catch (Exception e) {
				System.err
						.println("AiModelAnalyzer: Error in analyzing units of user "
								+ allUserAssets.get(i).getUser().getNickname());
			}
		}

		// get all buildings, assign them to the user asset and separate them by
		// building type and level
		for (int i = 0; i < allUserAssets.size(); i++) {
			try {
				Iterator<Building> buildingIterator = allUserAssets.get(i)
						.iteratorOfBuildings();
				while (buildingIterator.hasNext()) {
					changeBuildingCount(allUserAssets.get(i),
							buildingIterator.next(), Change.ADD);
				}
			} catch (Exception e) {
				System.err
						.println("AiModelAnalyzer: Error in analyzing buildings of user "
								+ allUserAssets.get(i).getUser().getNickname());
			}
		}

		// get all collected resources, assign them to the user asset and
		// separate them by type
		for (int i = 0; i < allUserAssets.size(); i++) {
			try {
				Iterator<Resource> resourceIterator = allUserAssets.get(i)
						.iteratorOfCollectedResources();
				while (resourceIterator.hasNext()) {
					changeResourceCount(allUserAssets.get(i),
							resourceIterator.next(), Change.ADD);

				}
			} catch (Exception e) {
				System.err
						.println("AiModelAnalyzer: Error in analyzing resources of user "
								+ allUserAssets.get(i).getUser().getNickname());
			}
		}

		// get the number of sectors every user asset is occupying
		for (int i = 0; i < allUserAssets.size(); i++) {
			try {
				Iterator<Sector> sectorIterator = allUserAssets.get(i)
						.iteratorOfSector();
				while (sectorIterator.hasNext()) {
					sectorIterator.next();
					changeSectorCount(allUserAssets.get(i), Change.ADD);
				}
			} catch (Exception e) {
				System.err
						.println("AiModelAnalyzer: Error in analyzing sectors of user "
								+ allUserAssets.get(i).getUser().getNickname());
			}
		}

		//addPropertyListeners();
		
				
	}

	/**
	 * Implements property change listeners for every user assets
	 */
	@SuppressWarnings("unchecked")
	private void addPropertyListeners() {
		Iterator<UserAssets> userAssetsIterator = ciClient.getGame()
				.iteratorOfUserAssets();
		while (userAssetsIterator.hasNext()) {
			UserAssets userAssets = userAssetsIterator.next();

			userAssets.addPropertyChangeListener(userAssets.PROPERTY_UNITS,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent arg0) {
							if (arg0.getNewValue() != null) {
								UserAssets userAssets = ((Unit) arg0
										.getNewValue()).getUserAssets();
								Unit unit = (Unit) arg0.getNewValue();
								changeUnitCount(userAssets, unit, Change.ADD);
							}
							if (arg0.getNewValue() == null
									&& arg0.getOldValue() != null) {
								UserAssets userAssets = ((Unit) arg0
										.getOldValue()).getUserAssets();
								Unit unit = (Unit) arg0.getOldValue();
								changeUnitCount(userAssets, unit,
										Change.SUBTRACT);
							}
						}
					});

			userAssets.addPropertyChangeListener(userAssets.PROPERTY_BUILDINGS,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent arg0) {
							if (arg0.getNewValue() != null) {
								UserAssets userAssets = ((Building) arg0
										.getNewValue()).getUserAssets();
								Building building = (Building) arg0
										.getNewValue();
								changeBuildingCount(userAssets, building,
										Change.ADD);
							}
							if (arg0.getNewValue() == null
									&& arg0.getOldValue() != null) {
								UserAssets userAssets = ((Building) arg0
										.getOldValue()).getUserAssets();
								Building building = (Building) arg0
										.getOldValue();
								changeBuildingCount(userAssets, building,
										Change.SUBTRACT);
							}
						}
					});

			userAssets.addPropertyChangeListener(
					userAssets.PROPERTY_COLLECTED_RESOURCES,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent arg0) {
							if (arg0.getNewValue() != null) {
								UserAssets userAssets = ((Resource) arg0
										.getNewValue()).getUserAssets();
								Resource resource = (Resource) arg0
										.getNewValue();
								changeResourceCount(userAssets, resource,
										Change.ADD);
							}
							if (arg0.getNewValue() == null
									&& arg0.getOldValue() != null) {
								UserAssets userAssets = ((Resource) arg0
										.getOldValue()).getUserAssets();
								Resource resource = (Resource) arg0
										.getOldValue();
								changeResourceCount(userAssets, resource,
										Change.SUBTRACT);
							}
						}
					});

			userAssets.addPropertyChangeListener(userAssets.PROPERTY_SECTOR,
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent arg0) {
							if (arg0.getNewValue() != null) {
								UserAssets userAssets = ((Sector) arg0
										.getNewValue()).getUserAssets();
								changeSectorCount(userAssets, Change.ADD);
							}
							if (arg0.getNewValue() == null
									&& arg0.getOldValue() != null) {
								UserAssets userAssets = ((Sector) arg0
										.getOldValue()).getUserAssets();
								changeSectorCount(userAssets, Change.SUBTRACT);
							}
						}
					});
		}
	}

	/**
	 * Adds or subtracts to the resources count for the given userAssets
	 * 
	 * @param userAssets
	 *            the userAssets that is assigned to the userAssetsCounter which
	 *            is supposed to be changed
	 * @param resource
	 *            the resource which has a change
	 * @param change
	 *            an enum which decides if add or subtract from resource counter
	 */
	private void changeUnitCount(UserAssets userAssets, Unit unit, Change change) {
		
		// find the right userAssetsCounter to the userAssets
		UserAssetsCounter userAssetsCounter = null;
		if (userAssets.getId().equals(myUserAssetsCounter.getId())) {
			userAssetsCounter = myUserAssetsCounter;
		} else {
			for (int i = 0; i < opponentsUserAssetsCounter.size(); i++) {
				if (userAssets.getId().equals(
						opponentsUserAssetsCounter.get(i).getId())) {
					userAssetsCounter = opponentsUserAssetsCounter.get(i);
				}
			}
		}

		// determine if the unit was added or removed
		int number = 0;
		if (change.compareTo(Change.ADD) == 0) {
			number = 1;
		} else if (change.compareTo(Change.SUBTRACT) == 0) {
			number = -1;
		}

		if (1 == unit.getLevel()) {
			if (unit instanceof Peon) {
				userAssetsCounter.setPeonCount(userAssetsCounter.getPeonCount()
						+ number);
			} else if (unit instanceof Swordsman) {
				userAssetsCounter.setSwordsmanLevel1Count(userAssetsCounter
						.getSwordsmanLevel1Count() + number);
			} else if (unit instanceof Archer) {
				userAssetsCounter.setArcherLevel1Count(userAssetsCounter
						.getArcherLevel1Count() + number);
			} else if (unit instanceof Knight) {
				userAssetsCounter.setKnightLevel1Count(userAssetsCounter
						.getKnightLevel1Count() + number);
			} else if (unit instanceof Catapult) {
				userAssetsCounter.setCatapultCount(userAssetsCounter
						.getCatapultCount() + number);
			}
		} else if (2 == unit.getLevel()) {
			if (unit instanceof Swordsman) {
				userAssetsCounter.setSwordsmanLevel2Count(userAssetsCounter
						.getSwordsmanLevel2Count() + number);
			} else if (unit instanceof Archer) {
				userAssetsCounter.setArcherLevel2Count(userAssetsCounter
						.getArcherLevel2Count() + number);
			} else if (unit instanceof Knight) {
				userAssetsCounter.setKnightLevel2Count(userAssetsCounter
						.getKnightLevel2Count() + number);
			}
		} else if (3 == unit.getLevel()) {
			if (unit instanceof Swordsman) {
				userAssetsCounter.setSwordsmanLevel3Count(userAssetsCounter
						.getSwordsmanLevel3Count() + number);
			} else if (unit instanceof Archer) {
				userAssetsCounter.setArcherLevel3Count(userAssetsCounter
						.getArcherLevel3Count() + number);
			} else if (unit instanceof Knight) {
				userAssetsCounter.setKnightLevel3Count(userAssetsCounter
						.getKnightLevel3Count() + number);
			}
		}
	}

	/**
	 * Adds or subtracts to the buildings count for the given userAssets
	 * 
	 * @param userAssets
	 *            the userAssets that is assigned to the userAssetsCounter which
	 *            is supposed to be changed
	 * @param resource
	 *            the building which has a change
	 * @param change
	 *            an enum which decides if add or subtract from building counter
	 */
	private void changeBuildingCount(UserAssets userAssets, Building building,
			Change change) {

		// find the right userAssetsCounter to the userAssets
		UserAssetsCounter userAssetsCounter = null;
		if (userAssets.getId().equals(myUserAssetsCounter.getId())) {
			userAssetsCounter = myUserAssetsCounter;
		} else {
			for (int i = 0; i < opponentsUserAssetsCounter.size(); i++) {
				if (userAssets.getId().equals(
						opponentsUserAssetsCounter.get(i).getId())) {
					userAssetsCounter = opponentsUserAssetsCounter.get(i);
				}
			}
		}

		// determine if the building was added or removed
		int number = 0;
		if (change.compareTo(Change.ADD) == 0) {
			number = 1;
		} else if (change.compareTo(Change.SUBTRACT) == 0) {
			number = -1;
		}

		if (1 == building.getLevel()) {
			if (building instanceof Stronghold) {
				userAssetsCounter.setStrongholdLevel1Count(userAssetsCounter
						.getStrongholdLevel1Count() + number);
			} else if (building instanceof Farm) {
				userAssetsCounter.setFarmCount(userAssetsCounter.getFarmCount()
						+ number);
			} else if (building instanceof Barrack) {
				userAssetsCounter.setBarrackLevel1Count(userAssetsCounter
						.getBarrackLevel1Count() + number);
			} else if (building instanceof Forge) {
				userAssetsCounter.setForgeCount(userAssetsCounter
						.getForgeCount() + number);
			} else if (building instanceof Tower) {
				userAssetsCounter.setTowerLevel1Count(userAssetsCounter
						.getTowerLevel1Count() + number);
			}
		} else if (2 == building.getLevel()) {
			if (building instanceof Stronghold) {
				userAssetsCounter.setStrongholdLevel2Count(userAssetsCounter
						.getStrongholdLevel2Count() + number);
			} else if (building instanceof Barrack) {
				userAssetsCounter.setBarrackLevel2Count(userAssetsCounter
						.getBarrackLevel2Count() + number);
			} else if (building instanceof Tower) {
				userAssetsCounter.setTowerLevel2Count(userAssetsCounter
						.getTowerLevel2Count() + number);
			}
		} else if (3 == building.getLevel()) {
			if (building instanceof Stronghold) {
				userAssetsCounter.setStrongholdLevel3Count(userAssetsCounter
						.getStrongholdLevel3Count() + number);
			} else if (building instanceof Barrack) {
				userAssetsCounter.setBarrackLevel3Count(userAssetsCounter
						.getBarrackLevel3Count() + number);
			} else if (building instanceof Tower) {
				userAssetsCounter.setTowerLevel3Count(userAssetsCounter
						.getTowerLevel3Count() + number);
			}
		}
	}

	/**
	 * Adds or subtracts to the resources count for the given userAssets
	 * 
	 * @param userAssets
	 *            the userAssets that is assigned to the userAssetsCounter which
	 *            is supposed to be changed
	 * @param resource
	 *            the resource which has a change
	 * @param change
	 *            an enum which decided if add or subtract from resource counter
	 */
	private void changeResourceCount(UserAssets userAssets, Resource resource,
			Change change) {

		// find the right userAssetsCounter to the userAssets
		UserAssetsCounter userAssetsCounter = null;
		if (userAssets.getId().equals(myUserAssetsCounter.getId())) {
			userAssetsCounter = myUserAssetsCounter;
		} else {
			for (int i = 0; i < opponentsUserAssetsCounter.size(); i++) {
				if (userAssets.getId().equals(
						opponentsUserAssetsCounter.get(i).getId())) {
					userAssetsCounter = opponentsUserAssetsCounter.get(i);
				}
			}
		}

		// determine if the resource was added or removed
		int number = 0;
		if (change.compareTo(Change.ADD) == 0) {
			number = Integer.valueOf(resource.getQuantity());
		} else if (change.compareTo(Change.SUBTRACT) == 0) {
			number = -Integer.valueOf(resource.getQuantity());
		}

		if (resource.getQuantity() != null) {
			if (resource.getType().equals("WOOD")) {
				userAssetsCounter.setWoodResourcesCount(userAssetsCounter
						.getWoodResourcesCount() + number);
			} else if (resource.getType().equals("STONE")) {
				userAssetsCounter.setStoneResourcesCount(userAssetsCounter
						.getStoneResourcesCount() + number);
			} else if (resource.getType().equals("IRON")) {
				userAssetsCounter.setIronResourcesCount(userAssetsCounter
						.getIronResourcesCount() + number);
			}
		}
	}

	/**
	 * Adds or subtracts to the sector count for the given userAssets
	 * 
	 * @param userAssets
	 *            the userAssets that is assigned to the userAssetsCounter which
	 *            is supposed to be changed
	 * @param change
	 *            an enum which decides if add or subtract from sector counter
	 */
	private void changeSectorCount(UserAssets userAssets, Change change) {

		// find the right userAssetsCounter to the userAssets
		UserAssetsCounter userAssetsCounter = null;
		if (userAssets.getId().equals(myUserAssetsCounter.getId())) {
			userAssetsCounter = myUserAssetsCounter;
		} else {
			for (int i = 0; i < opponentsUserAssetsCounter.size(); i++) {
				if (userAssets.getId().equals(
						opponentsUserAssetsCounter.get(i).getId())) {
					userAssetsCounter = opponentsUserAssetsCounter.get(i);
				}
			}
		}

		// determine if the sector was added or removed
		int number = 0;
		if (change.compareTo(Change.ADD) == 0) {
			number = 1;
		} else if (change.compareTo(Change.SUBTRACT) == 0) {
			number = -1;
		}
		userAssetsCounter.setSectorsCount(userAssetsCounter.getSectorsCount()
				+ number);
	}

	/**
	 * Refreshes the counters of seriously hit units ( <50% HP) for every
	 * userAssetsCounter
	 */
	@SuppressWarnings("unchecked")
	public void refreshHitUnitCounter() {
		for (int i = 0; i < allUserAssets.size(); i++) {
			try {
				UserAssets userAssets = allUserAssets.get(i);
				// find the right userAssetsCounter to the userAssets
				UserAssetsCounter userAssetsCounter = null;
				if (userAssets.getId().equals(myUserAssetsCounter.getId())) {
					userAssetsCounter = myUserAssetsCounter;
				} else {
					for (int j = 0; j < opponentsUserAssetsCounter.size(); j++) {
						if (userAssets.getId().equals(
								opponentsUserAssetsCounter.get(j).getId())) {
							userAssetsCounter = opponentsUserAssetsCounter
									.get(j);
						}
					}
				}

				userAssetsCounter.setSeriouslyHitArcherLevel1Count(0);
				userAssetsCounter.setSeriouslyHitArcherLevel2Count(0);
				userAssetsCounter.setSeriouslyHitArcherLevel3Count(0);
				userAssetsCounter.setSeriouslyHitCatapultCount(0);
				userAssetsCounter.setSeriouslyHitKnightLevel1Count(0);
				userAssetsCounter.setSeriouslyHitKnightLevel2Count(0);
				userAssetsCounter.setSeriouslyHitKnightLevel3Count(0);
				userAssetsCounter.setSeriouslyHitPeonCount(0);
				userAssetsCounter.setSeriouslyHitSwordsmanLevel1Count(0);
				userAssetsCounter.setSeriouslyHitSwordsmanLevel2Count(0);
				userAssetsCounter.setSeriouslyHitSwordsmanLevel3Count(0);

				Iterator<Unit> unitIterator = userAssets.iteratorOfUnits();
				while (unitIterator.hasNext()) {
					Unit unit = unitIterator.next();
					if (unit.getHp() <= unit.getMaxHP() / 2) {
						if (1 == unit.getLevel()) {
							if (unit instanceof Peon) {
								userAssetsCounter
										.setSeriouslyHitPeonCount(userAssetsCounter
												.getSeriouslyHitPeonCount() + 1);
							} else if (unit instanceof Swordsman) {
								userAssetsCounter
										.setSeriouslyHitSwordsmanLevel1Count(userAssetsCounter
												.getSeriouslyHitSwordsmanLevel1Count() + 1);
							} else if (unit instanceof Archer) {
								userAssetsCounter
										.setSeriouslyHitArcherLevel1Count(userAssetsCounter
												.getSeriouslyHitArcherLevel1Count() + 1);
							} else if (unit instanceof Knight) {
								userAssetsCounter
										.setSeriouslyHitKnightLevel1Count(userAssetsCounter
												.getSeriouslyHitKnightLevel1Count() + 1);
							} else if (unit instanceof Catapult) {
								userAssetsCounter
										.setSeriouslyHitCatapultCount(userAssetsCounter
												.getSeriouslyHitCatapultCount() + 1);
							}
						} else if (2 == unit.getLevel()) {
							if (unit instanceof Swordsman) {
								userAssetsCounter
										.setSeriouslyHitSwordsmanLevel2Count(userAssetsCounter
												.getSeriouslyHitSwordsmanLevel2Count() + 1);
							} else if (unit instanceof Archer) {
								userAssetsCounter
										.setSeriouslyHitArcherLevel2Count(userAssetsCounter
												.getSeriouslyHitArcherLevel2Count() + 1);
							} else if (unit instanceof Knight) {
								userAssetsCounter
										.setSeriouslyHitKnightLevel2Count(userAssetsCounter
												.getSeriouslyHitKnightLevel2Count() + 1);
							}
						} else if (3 == unit.getLevel()) {
							if (unit instanceof Swordsman) {
								userAssetsCounter
										.setSeriouslyHitSwordsmanLevel3Count(userAssetsCounter
												.getSeriouslyHitSwordsmanLevel3Count() + 1);
							} else if (unit instanceof Archer) {
								userAssetsCounter
										.setSeriouslyHitArcherLevel3Count(userAssetsCounter
												.getSeriouslyHitArcherLevel3Count() + 1);
							} else if (unit instanceof Knight) {
								userAssetsCounter
										.setSeriouslyHitKnightLevel3Count(userAssetsCounter
												.getSeriouslyHitKnightLevel3Count() + 1);
							}
						}

					}
				}

			} catch (Exception e) {
				System.err
						.println("AiModelAnalyzer: Error in refershing hit units of user "
								+ allUserAssets.get(i).getUser().getNickname());
			}
		}
	}

	public void setMyUserAssetsCounter(UserAssetsCounter myUserAssetsCounter) {
		this.myUserAssetsCounter = myUserAssetsCounter;
	}

	public void setOpponentsUserAssetsCounter(
			ArrayList<UserAssetsCounter> opponentsUserAssetsCounter) {
		this.opponentsUserAssetsCounter = opponentsUserAssetsCounter;
	}

	public CIClient getCiClient() {
		return ciClient;
	}

	public ArrayList<UserAssets> getAllUserAssets() {
		return allUserAssets;
	}

	public UserAssetsCounter getMyUserAssetsCounter() {
		return myUserAssetsCounter;
	}

	public ArrayList<UserAssetsCounter> getOpponentsUserAssetsCounter() {
		return opponentsUserAssetsCounter;
	}

	public void setAIMain(AIMain mainAi) {
		this.mainAi = mainAi; 
	}
	
	
	@SuppressWarnings("unchecked")
	public void checkEnemySectors() {
		// prepare the list of enemies sectors
		if (mainAi.enemySectors == null) { 
			mainAi.enemySectors = new LinkedList<Sector>();
		} else {
			mainAi.enemySectors.clear();
		}
		Sector tempSector = null;
		Unit tempUnit = null;
		Building tempBuilding = null;
		Resource tempResource = null;
		FPropHashSet<Sector> allSectors = (FPropHashSet<Sector>) ciClient.getGame().getSectors().clone();
		Iterator<Sector> sectorIter = allSectors.iterator();
		// do a lot more of cloning to prevent thread-conflicts if necessary
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
		Iterator<Resource> resourceIter = null;
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			unitIter = tempSector.iteratorOfSectorUnits();
			// check for units on the sector
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				if (tempUnit.getUserAssets().getId() != myUserAssetsCounter.getId()) {
					mainAi.enemySectors.add(tempSector);
					break;
				}
			}
			// check for peons at the sectors resources
			resourceIter = tempSector.iteratorOfSectorResources();
			while (resourceIter.hasNext()) {
				tempResource = resourceIter.next();
				unitIter = tempResource.iteratorOfPeon();
				while (unitIter.hasNext()) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets().getId() != myUserAssetsCounter.getId()) {
						mainAi.enemySectors.add(tempSector);
						break;
					}
				}
			}
			// check for buildings on the sector
			buildingIter = tempSector.iteratorOfSectorBuildings();
			while (buildingIter.hasNext()) {
				tempBuilding = buildingIter.next();
				if (tempBuilding.getUserAssets().getId() != myUserAssetsCounter.getId()) {
					mainAi.enemySectors.add(tempSector);
					break;
				}
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void checkOwnSectors() {
		// prepare the lists of the own sectors and those being under attack
		if (mainAi.ownSectors == null) { 
			mainAi.ownSectors = new LinkedList<Sector>();
		} else {
			mainAi.ownSectors.clear();
		}
		Sector tempSector = null;
		Unit tempUnit = null;
		Building tempBuilding = null;
		Resource tempResource = null;
		// do a lot of cloning to prevent thread-conflicts if necessary
		Iterator<Sector> sectorIter = ciClient.getGame().iteratorOfSector();
		Iterator<Unit> unitIter = null;
		Iterator<Building> buildingIter = null;
		Iterator<Resource> resourceIter = null;
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			unitIter = tempSector.iteratorOfSectorUnits();
			// check for units on the sector
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				if (tempUnit.getUserAssets().getId() == myUserAssetsCounter.getId()
						&& !tempUnit.isScouting()) {
					mainAi.ownSectors.add(tempSector);
					break;
				}
			}
			// check for peons at the sectors resources
			resourceIter = tempSector.iteratorOfSectorResources();
			while (resourceIter.hasNext()) {
				tempResource = resourceIter.next();
				unitIter = tempResource.iteratorOfPeon();
				while (unitIter.hasNext()) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets().getId() == myUserAssetsCounter.getId()) {
						mainAi.ownSectors.add(tempSector);
						break;
					}
				}
			}
			// check for buildings on the sector
			buildingIter = tempSector.iteratorOfSectorBuildings();
			while (buildingIter.hasNext()) {
				tempBuilding = buildingIter.next();
				if (tempBuilding.getUserAssets().getId() == myUserAssetsCounter.getId()) {
					mainAi.ownSectors.add(tempSector);
					break;
				}
			}
		}
		if (mainAi.ownSectorsUnderAttack == null) { 
			mainAi.ownSectorsUnderAttack = new LinkedList<Sector>();
		} else {
			mainAi.ownSectorsUnderAttack.clear();
		}
		if (mainAi.ownSectors.size() == 0) {
			return;
		}
		sectorIter = mainAi.ownSectors.listIterator();
		while (sectorIter.hasNext()) {
			tempSector = sectorIter.next();
			unitIter = tempSector.iteratorOfSectorUnits();
			// check for enemy units on the sector
			while (unitIter.hasNext()) {
				tempUnit = unitIter.next();
				if (tempUnit.getUserAssets().getId() != myUserAssetsCounter.getId()) {
					mainAi.ownSectorsUnderAttack.add(tempSector);
					break;
				}
			}
			// check for enemy peons at the sectors resources
			resourceIter = tempSector.iteratorOfSectorResources();
			while (resourceIter.hasNext()) {
				tempResource = resourceIter.next();
				unitIter = tempResource.iteratorOfPeon();
				while (unitIter.hasNext()) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() != null
							&& tempUnit.getUserAssets().getId() != null
							&& myUserAssetsCounter != null
							&& myUserAssetsCounter.getId() != null) {
						if (tempUnit.getUserAssets().getId() != myUserAssetsCounter.getId()) {
							mainAi.ownSectorsUnderAttack.add(tempSector);
							break;
						}
					} else {
						System.err.println("something null in checkOwnSectors");
					}
				}
			}
		}
	}

}
