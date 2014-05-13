package model.ai;

import java.util.ArrayList;
import java.util.Iterator;

import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;
import model.game.Barrack;
import model.game.Building;
import model.game.CommandHelper;
import model.game.Forge;
import model.game.Stronghold;
import model.game.UserAssets;
import model.lobby.ServerConnection;

/**
 * BuildUnits decide which unit should be build and assigns the client
 * 
 */
public class UnitBuilder {

	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private String myUserAssetsID = null;
	private CommandHelper commandHelper = null;
	private ServerConnection servCon = null;
	private String buildingID = null;
	private UserAssets userAssets = null;
	private boolean DEBUG = true;
	
	/**
	 * constructor
	 * 
	 * @param aiHelper
	 * @param aiModel
	 * @param myUserAssetsID
	 * @param commandHelper
	 */
	public UnitBuilder(AIHelper aiHelper, AiModelAnalyzer aiModel,
			String myUserAssetsID, CommandHelper commandHelper) {
		super();
		this.aiHelper = aiHelper;
		this.aiModel = aiModel;
		this.myUserAssetsID = myUserAssetsID;
		this.commandHelper = commandHelper;
		servCon = commandHelper.getChainMaster().getCIClient().getServerConnection();
		@SuppressWarnings("unchecked")
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
	 * when player has enough resources then build peon level 1
	 * 
	 * @return --> true when peon level 1 will be build
	 */
	public Boolean buildPeon_lvl1() {
		
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getPEON_1_STONE()
					&& iron >= aiHelper.getPEON_1_IRON()
					&& wood >= aiHelper.getPEON_1_WOOD()) {
				buildingID = findBuildingID("stronghold", 1);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "PEON", "1");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a peon");
					}
					return false;
				}
				return true;
			}
		}

		return false;
	}

	

	/**
	 * when player has enough resources then build knight level 1
	 * 
	 * @return --> true when knight level 1 will be build
	 */
	public Boolean buildKnight_lvl1() {

		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getKNIGHT_1_STONE()
					&& iron >= aiHelper.getKNIGHT_1_IRON()
					&& wood >= aiHelper.getKNIGHT_1_WOOD()) {

				buildingID = findBuildingID("barrack", 1);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "KNIGHT", "1");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a knight of level 1");
					}
					return false;
				}

				return true;
			}
		}

		return false;
	}

	/**
	 * when player has enough resources then build knight level 2
	 * 
	 * @return --> true when knight level 2 will be build
	 */
	public Boolean buildKnight_lvl2() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getKNIGHT_2_STONE()
					&& iron >= aiHelper.getKNIGHT_2_IRON()
					&& wood >= aiHelper.getKNIGHT_2_WOOD()) {
				
				buildingID = findBuildingID("barrack", 2);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "KNIGHT", "2");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a knight of level 2");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build knight level 3
	 * 
	 * @return --> true when knight level 3 will be build
	 */
	public Boolean buildKnight_lvl3() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getKNIGHT_3_STONE()
					&& iron >= aiHelper.getKNIGHT_3_IRON()
					&& wood >= aiHelper.getKNIGHT_3_WOOD()) {
				commandHelper.createUnit("barrack", "knight", 3);
				buildingID = findBuildingID("barrack", 3);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "KNIGHT", "3");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a knight of level 3");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build swordsman level 1
	 * 
	 * @return --> true when swordsman level 1 will be build
	 */
	public Boolean buildSwordsman_lvl1() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getSWORDSMAN_1_STONE()
					&& iron >= aiHelper.getSWORDSMAN_1_IRON()
					&& wood >= aiHelper.getSWORDSMAN_1_WOOD()) {
				
				buildingID = findBuildingID("barrack", 1);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "SWORDSMAN", "1");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a swordsman of level 1");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build swordsman level 2
	 * 
	 * @return --> true when swordsman level 2 will be build
	 */
	public Boolean buildSwordsman_lvl2() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getSWORDSMAN_2_STONE()
					&& iron >= aiHelper.getSWORDSMAN_2_IRON()
					&& wood >= aiHelper.getSWORDSMAN_2_WOOD()) {
				
				buildingID = findBuildingID("barrack", 2);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "SWORDSMAN", "2");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a swordsman of level 2");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build swordsman level 3
	 * 
	 * @return --> true when swordsman level 3 will be build
	 */
	public Boolean buildSwordsman_lvl3() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getSWORDSMAN_3_STONE()
					&& iron >= aiHelper.getSWORDSMAN_3_IRON()
					&& wood >= aiHelper.getSWORDSMAN_3_WOOD()) {
				
				buildingID = findBuildingID("barrack", 3);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "SWORDSMAN", "3");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a swordsman of level 3");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build archer level 1
	 * 
	 * @return --> true when archer level 1 will be build
	 */
	public Boolean buildArcher_lvl1() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getARCHER_1_STONE()
					&& iron >= aiHelper.getARCHER_1_IRON()
					&& wood >= aiHelper.getARCHER_1_WOOD()) {
				
				buildingID = findBuildingID("barrack", 1);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "ARCHER", "1");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create an archer of level 1");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build archer level 2
	 * 
	 * @return --> true when archer level 2 will be build
	 */
	public Boolean buildArcher_lvl2() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getARCHER_2_STONE()
					&& iron >= aiHelper.getARCHER_2_IRON()
					&& wood >= aiHelper.getARCHER_2_WOOD()) {
				
				buildingID = findBuildingID("barrack", 2);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "ARCHER", "2");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create an archer of level 2");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build archer level 3
	 * 
	 * @return --> true when archer level 3 will be build
	 */
	public Boolean buildArcher_lvl3() {
		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getARCHER_3_STONE()
					&& iron >= aiHelper.getARCHER_3_IRON()
					&& wood >= aiHelper.getARCHER_3_WOOD()) {
				
				buildingID = findBuildingID("barrack", 1);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "ARCHER", "3");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create an archer of level 3");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * when player has enough resources then build catapult level 1
	 * 
	 * @return --> true when catapult level 1 will be build
	 */
	public Boolean buildCatapult_lvl1() {
		if (aiModel != null	&& aiModel.getMyUserAssetsCounter() != null) {

			Integer stone = aiModel.getMyUserAssetsCounter().getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter().getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter().getIronResourcesCount();

			if (stone >= aiHelper.getCATAPULT_1_STONE()
					&& iron >= aiHelper.getCATAPULT_1_IRON()
					&& wood >= aiHelper.getCATAPULT_1_WOOD()) {
				
				buildingID = findBuildingID("forge", 1);
				if (buildingID != "") {
					servCon.create_unit(buildingID, "CATAPULT", "1");
				} else {
					if (DEBUG) {
						System.err.println("ai found no building to create a catapult");
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	private String findBuildingID(String buildingType, int level) {
		// find a building where the unit can be build in
		String id = "";
		Building creatingBuilding = null;
		Iterator<Building> buildingIterator = (Iterator<Building>) userAssets.iteratorOfBuildings();
		while (buildingIterator.hasNext()) {
			Building building = buildingIterator.next();
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
			if (creatingBuilding != null) {
				break;
			}
		}
		if (creatingBuilding != null) {
			id = creatingBuilding.getId();
		}
		return id;
	}
}
