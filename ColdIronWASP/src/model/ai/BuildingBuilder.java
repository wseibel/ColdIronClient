package model.ai;

import java.util.Iterator;

import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;
import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.User;

/**
 * BuildBuildings decide which building should be build and assigns the client
 * 
 */
public class BuildingBuilder {

	@SuppressWarnings("unused")
	private CommandHelper commandHelper = null;
	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private CIClient ciClient = null;
	private User currentUser = null;

	/**
	 * constructor
	 * 
	 * @param aiHelper
	 * @param aiModel
	 * @param myUserAssetsID
	 * @param commandHelper
	 */
	public BuildingBuilder(AIHelper aiHelper, AiModelAnalyzer aiModel,
			CommandHelper commandHelper, CIClient ciClient, User currentUser) {
		super();
		this.aiHelper = aiHelper;
		this.aiModel = aiModel;
		this.commandHelper = commandHelper;
		this.currentUser = currentUser;
		this.ciClient = ciClient;
	}

	/**
	 * when player has enough resources then build stronghold level 1
	 * 
	 * @return --> when stronghold level 1 will be build
	 */
	public Boolean buildStronghold_lvl1() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getSTRONGHOLD_1_STONE()
						&& iron >= aiHelper.getSTRONGHOLD_1_IRON()
						&& wood >= aiHelper.getSTRONGHOLD_1_WOOD()) {

					if (ciClient != null) {
						if (ciClient.getGame().getCurrentSector() == null) {
							ciClient.getServerConnection().create_building(
									currentUser.getUserAssets()
											.getStartSector().getId(),
									"STRONGHOLD");
						} else {
							ciClient.getServerConnection().create_building(
									ciClient.getGame().getCurrentSector()
											.getId(), "STRONGHOLD");
						}
						return true;
					}

				}
			}

		}
		return false;
	}

	/**
	 * when player has enough resources then build stronghold level 2
	 * 
	 * @return --> when barrack stronghold 2 will be build
	 */
	public Boolean buildStronghold_lvl2() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getSTRONGHOLD_2_STONE()
						&& iron >= aiHelper.getSTRONGHOLD_2_IRON()
						&& wood >= aiHelper.getSTRONGHOLD_2_WOOD()) {

					return sendUpgradeCommand("Stronghold", 1);
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build stronghold level 3
	 * 
	 * @return --> when barrack stronghold 3 will be build
	 */
	public Boolean buildStronghold_lvl3() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getSTRONGHOLD_3_STONE()
						&& iron >= aiHelper.getSTRONGHOLD_3_IRON()
						&& wood >= aiHelper.getSTRONGHOLD_3_WOOD()) {

					return sendUpgradeCommand("Stronghold", 2);
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build farm level 1
	 * 
	 * @return --> when farm level 1 will be build
	 */
	public Boolean buildFarm_lvl1() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getFARM_1_STONE()
						&& iron >= aiHelper.getFARM_1_IRON()
						&& wood >= aiHelper.getFARM_1_WOOD()) {

					if (ciClient != null) {
						if (ciClient.getGame().getCurrentSector() == null) {
							ciClient.getServerConnection().create_building(
									currentUser.getUserAssets()
											.getStartSector().getId(), "FARM");
						} else {
							ciClient.getServerConnection().create_building(
									ciClient.getGame().getCurrentSector()
											.getId(), "FARM");
						}
						return true;
					}
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build barrack level 1
	 * 
	 * @return --> when barrack level 1 will be build
	 */
	public Boolean buildBarrack_lvl1() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getBARRACK_1_STONE()
						&& iron >= aiHelper.getBARRACK_1_IRON()
						&& wood >= aiHelper.getBARRACK_1_WOOD()) {
					if (ciClient != null) {
						if (ciClient.getGame().getCurrentSector() == null) {
							ciClient.getServerConnection().create_building(
									currentUser.getUserAssets()
											.getStartSector().getId(),
									"BARRACK");
							sendUpgradeCommand("Barrack", 1);
						} else {
							ciClient.getServerConnection().create_building(
									ciClient.getGame().getCurrentSector()
											.getId(), "BARRACK");
							sendUpgradeCommand("Barrack", 1);
						}
						return true;
					}
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build barrack level 2
	 * 
	 * @return --> when barrack level 2 will be build
	 */
	public Boolean buildBarrack_lvl2() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getBARRACK_2_STONE()
						&& iron >= aiHelper.getBARRACK_2_IRON()
						&& wood >= aiHelper.getBARRACK_2_WOOD()) {

					return sendUpgradeCommand("Barrack", 1);
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build barrack level 3
	 * 
	 * @return --> when barrack level 3 will be build
	 */
	public Boolean buildBarrack_lvl3() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getBARRACK_3_STONE()
						&& iron >= aiHelper.getBARRACK_3_IRON()
						&& wood >= aiHelper.getBARRACK_3_WOOD()) {

					return sendUpgradeCommand("Barrack", 2);
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build forge level 1
	 * 
	 * @return --> when forge level 1 will be build
	 */
	public Boolean buildForge_lvl1() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getFORGE_1_STONE()
						&& iron >= aiHelper.getFORGE_1_IRON()
						&& wood >= aiHelper.getFORGE_1_WOOD()) {
					if (ciClient != null) {
						if (ciClient.getGame().getCurrentSector() == null) {
							ciClient.getServerConnection().create_building(
									currentUser.getUserAssets()
											.getStartSector().getId(), "FORGE");
						} else {
							ciClient.getServerConnection().create_building(
									ciClient.getGame().getCurrentSector()
											.getId(), "FORGE");
						}
						return true;
					}
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build tower level 1
	 * 
	 * @return --> when tower level 1 will be build
	 */
	public Boolean buildTower_lvl1() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getTOWER_1_STONE()
						&& iron >= aiHelper.getTOWER_1_IRON()
						&& wood >= aiHelper.getTOWER_1_WOOD()) {
					if (ciClient != null) {
						if (ciClient.getGame().getCurrentSector() == null) {
							ciClient.getServerConnection().create_building(
									currentUser.getUserAssets()
											.getStartSector().getId(), "TOWER");
						} else {
							ciClient.getServerConnection().create_building(
									ciClient.getGame().getCurrentSector()
											.getId(), "TOWER");
						}
						return true;
					}
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build tower level 2
	 * 
	 * @return --> when tower level 2 will be build
	 */
	public Boolean buildTower_lvl2() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getTOWER_2_STONE()
						&& iron >= aiHelper.getTOWER_2_IRON()
						&& wood >= aiHelper.getTOWER_2_WOOD()) {

					return sendUpgradeCommand("Tower", 1);
				}
			}

		}

		return false;
	}

	/**
	 * when player has enough resources then build tower level 3
	 * 
	 * @return --> when tower level 3 will be build
	 */
	public Boolean buildTower_lvl3() {

		if (aiModel != null && aiModel.getMyUserAssetsCounter() != null) {
			Integer stone = aiModel.getMyUserAssetsCounter()
					.getStoneResourcesCount();
			Integer wood = aiModel.getMyUserAssetsCounter()
					.getWoodResourcesCount();
			Integer iron = aiModel.getMyUserAssetsCounter()
					.getIronResourcesCount();

			if (stone != null && wood != null && iron != null) {
				if (stone >= aiHelper.getTOWER_3_STONE()
						&& iron >= aiHelper.getTOWER_3_IRON()
						&& wood >= aiHelper.getTOWER_3_WOOD()) {

					return sendUpgradeCommand("Tower", 2);
				}
			}

		}

		return false;
	}

	/**
	 * send upgrade command to serverConnection
	 * 
	 * @param building
	 * @param currentLevel
	 * @return
	 */
	public Boolean sendUpgradeCommand(String building, Integer currentLevel) {
		Building tempBuilding = null;
		Integer targetLevel = (currentLevel + 1);

		// get a Building of the buildingtype and the currentLevel
		for (@SuppressWarnings("unchecked")
		Iterator<Building> buildingIter = currentUser.getUserAssets()
				.getStartSector().iteratorOfSectorBuildings(); buildingIter
				.hasNext();) {
			tempBuilding = buildingIter.next();
			if (tempBuilding.getClass().toString().contains(building)
					&& tempBuilding.getLevel() == currentLevel
					&& !tempBuilding.isUpgrading()) {
				ciClient.getServerConnection().upgrade(tempBuilding.getId(),
						targetLevel.toString());
				return true;
			}
		}

		return false;
	}
}
