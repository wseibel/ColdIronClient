package ai.controlCenter;

import model.ai.BuildingBuilder;
import model.game.CIClient;
import model.game.User;
import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;
import ai.helper.UserAssetsCounter;

/**
 * CreateBuildings decides whether a building should be built and if necessary
 * which building
 * 
 */
public class CreateBuildings {

	// The AI's strategy is manipulated by these values
	private final static Integer STRONGHOLD_LVL1_LIMIT = 1;
	private final static Integer STRONGHOLD_LVL2_LIMIT = 1;
	private final static Integer STRONGHOLD_LVL3_LIMIT = 1;
	private final static Integer BARRACK_LVL1_LIMIT = 1;
	private final static Integer BARRACK_LVL2_LIMIT = 1;
	private final static Integer BARRACK_LVL3_LIMIT = 1;
	private final static Integer FORGE_LVL1_LIMIT = 1;
	@SuppressWarnings("unused")
	private final static Integer TOWER_LVL1_LIMIT = 0;
	@SuppressWarnings("unused")
	private final static Integer TOWER_LVL2_LIMIT = 0;
	@SuppressWarnings("unused")
	private final static Integer TOWER_LVL3_LIMIT = 0;
	private final static Integer FARM_LVL1_LIMIT = 7;

	// how much units can be build with the existing farms
	private final static Integer UNIT_FARM_LIMIT = 2;

	private CIClient ciClient = null;
	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private BuildingBuilder buildingBuilder = null;
	private long timeOfLastOrder = System.currentTimeMillis();

	// helping variables
	private Boolean lvl2PriorityStrongh = false;
	private boolean lvl3PriorityStrongh = false;
	private Boolean lvl1PriorityBarrack = false;
	private Boolean lvl2PriorityBarrack = false;

	public CreateBuildings(CIClient ciClient, AIHelper aiHelper,
			AiModelAnalyzer aiModel, User currentUser) {
		super();
		this.setCiClient(ciClient);
		this.setAiHelper(aiHelper);
		this.aiModel = aiModel;

		buildingBuilder = new BuildingBuilder(aiHelper, aiModel, ciClient
				.getChainMaster().getCommandHelper(), ciClient, currentUser);

	}

	/**
	 * this method decide whether and which building should be built
	 * 
	 * @param mainAi
	 *            --> main ai class
	 */
	public void decideCreateBuilding(AIMain mainAi) {
//		if ((System.currentTimeMillis() - timeOfLastOrder) > mainAi.OrderInterval * 3) {

			aiModel.initValues();

			UserAssetsCounter myUserAssetsCounter = aiModel
					.getMyUserAssetsCounter();

			// if none stronghold exists build stronghold level 1
			if (myUserAssetsCounter.getStrongholdLevel1Count() < STRONGHOLD_LVL1_LIMIT) {
				if (myUserAssetsCounter.getStrongholdLevel2Count() == 0
						&& myUserAssetsCounter.getStrongholdLevel3Count() == 0) {
					if (buildingBuilder.buildStronghold_lvl1()) {
						return;
					}
				}
			}

			// will be needed stronghold level 2
			if (mainAi.level2Priority || this.lvl2PriorityStrongh) {
				if (myUserAssetsCounter.getStrongholdLevel1Count() > 0) {
					if (myUserAssetsCounter.getStrongholdLevel2Count() < STRONGHOLD_LVL2_LIMIT) {
						if (buildingBuilder.buildStronghold_lvl2()) {
							return;
						}
					}
				}

			}

			// will be needed stronghold level 3
			if (myUserAssetsCounter.getStrongholdLevel3Count() < STRONGHOLD_LVL3_LIMIT) {
				if (mainAi.level3Priority || this.lvl3PriorityStrongh) {
					if (myUserAssetsCounter.getStrongholdLevel2Count() > 0) {
						if (buildingBuilder.buildStronghold_lvl3()) {
							return;
						}
					} else {
						this.lvl2PriorityStrongh = true;
					}
				}
			}

			// will be needed farms
			if (myUserAssetsCounter.getFarmCount() < FARM_LVL1_LIMIT) {
				Integer allUnits = myUserAssetsCounter.getAllUnitsCount();
				Integer openRess = myUserAssetsCounter.getFarmCount() * 10
						- allUnits;

				if (openRess < UNIT_FARM_LIMIT) {
					// TODO enough peons?
					mainAi.needFarm = true;
					if (buildingBuilder.buildFarm_lvl1()) {
						mainAi.needFarm = false;
						return;
					}
				}

			}

			// will be needed barrack level 1
			if (myUserAssetsCounter.getBarrackLevel1Count() < BARRACK_LVL1_LIMIT) {
				if (mainAi.combatUnitsPriority || this.lvl1PriorityBarrack) {
					if (buildingBuilder.buildBarrack_lvl1()) {
						this.lvl1PriorityBarrack = false;
						mainAi.combatUnitsPriority = false;
						return;
					}
				}
			}

			// will be needed barrack level 2
			if (mainAi.level2Priority || this.lvl2PriorityBarrack) {
				if (myUserAssetsCounter.getBarrackLevel2Count() < BARRACK_LVL2_LIMIT) {
					if (myUserAssetsCounter.getBarrackLevel1Count() > 0) {
						if (myUserAssetsCounter.getStrongholdLevel2Count() > 0) {
							if (buildingBuilder.buildBarrack_lvl2()) {
								return;
							}
						} else {
							this.lvl2PriorityStrongh = true;
						}
					} else {
						this.lvl1PriorityBarrack = true;
					}
				}
			}

			// will be needed barrack level 3
			if (mainAi.level3Priority || this.lvl2PriorityBarrack) {
				if (myUserAssetsCounter.getBarrackLevel3Count() < BARRACK_LVL3_LIMIT) {
					if (myUserAssetsCounter.getBarrackLevel2Count() > 0) {
						if (myUserAssetsCounter.getStrongholdLevel3Count() > 0) {
							if (buildingBuilder.buildBarrack_lvl3()) {
								return;
							}
						} else {
							this.lvl3PriorityStrongh = true;
						}
					} else {
						this.lvl2PriorityBarrack = true;
					}
				}
			}

			// will be needed forge
			if (mainAi.catapultPriority) {
				if (myUserAssetsCounter.getForgeCount() < FORGE_LVL1_LIMIT) {
					if (myUserAssetsCounter.getBarrackLevel2Count() > 0) {
						if (buildingBuilder.buildForge_lvl1()) {
							return;
						}
					}
				} else {
					if (myUserAssetsCounter.getStrongholdLevel2Count() == 0) {
						this.lvl2PriorityBarrack = true;
					}
				}
			}

			timeOfLastOrder = System.currentTimeMillis();
			// TODO don't need tower level 1-3 ???
//		}
		return;
	}

	public CIClient getCiClient() {
		return ciClient;
	}

	public void setCiClient(CIClient ciClient) {
		this.ciClient = ciClient;
	}

	public AIHelper getAiHelper() {
		return aiHelper;
	}

	public void setAiHelper(AIHelper aiHelper) {
		this.aiHelper = aiHelper;
	}

}
