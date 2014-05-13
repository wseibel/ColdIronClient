package ai.controlCenter;

import java.util.Iterator;
import java.util.LinkedList;

import model.ai.MovePeonTo;
import model.game.Building;
import model.game.CIClient;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.Unit;
import model.game.UserAssets;
import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;

/**
 * MovePeons decides whether peon should be moved. For example to resources
 *
 */
public class MovePeons {

	// The AI's strategy is manipulated by these values
	private static final int PEON_PUFFER = 0;

	private CIClient ciClient = null;
	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private String myUserAssetsID = null;
	private UserAssets userAssets = null;
	private Sector startSector = null;
	private LinkedList<Peon> freePeons = new LinkedList<Peon>();

	public MovePeons(CIClient ciClient, AIHelper aiHelper, AiModelAnalyzer aiModel) {
		super();
		this.ciClient = ciClient;
		this.aiHelper = aiHelper;
		this.aiModel = aiModel;

		this.myUserAssetsID = this.aiModel.getMyUserAssetsCounter().getId();
		Iterator<UserAssets> iterUserAssets = null;
		for(iterUserAssets = ciClient.getGame().iteratorOfUserAssets(); iterUserAssets.hasNext();) {
			UserAssets tempAssets = iterUserAssets.next();
			if(tempAssets.getId().equals(myUserAssetsID)) {
				userAssets = tempAssets;
			}
		}
		startSector = userAssets.getStartSector();
		//		System.err.println("MovePeons got inited and ciClient == " + ciClient 
		//				+ " and ciClient.getChainMaster() == " + ciClient.getChainMaster()
		//				+ " and ciClient.getChainMaster().getCommandHelper() == " + ciClient.getChainMaster().getCommandHelper()); //TODO: remove me
	}

	/**
	 * this method decide whether peons must move 
	 * 
	 * @param mainAi
	 * 		--> main ai class
	 */
	public void decideMovePeon(AIMain mainAi) {
		// decision whether some peons should be moved
		Unit unit = null;
		Peon tempPeon = null;
		MovePeonTo movePeon = new MovePeonTo(aiHelper, aiModel,
				myUserAssetsID, ciClient.getChainMaster().getCommandHelper());
		Iterator<Unit> iteratorOfUnits = userAssets.iteratorOfUnits();
		// collecting all free peons
		while(iteratorOfUnits.hasNext()) {
			unit = iteratorOfUnits.next();
			if(unit instanceof Peon) {
				tempPeon = (Peon) unit;
				if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null && !tempPeon.isScouting()) {
					freePeons.add(tempPeon);
				}
			}
		}

		if(freePeons.size() <= PEON_PUFFER) {
			mainAi.buildPeonPriority = 10;
			freePeons.clear();
			return;
		}

		LinkedList<Peon> clone = (LinkedList<Peon>) freePeons.clone();
		// Iterate over cloned LinkedList of freePeons to avoid concurrentModificationException
		for(Iterator<Peon> iteratorPeon = clone.iterator();iteratorPeon.hasNext() && freePeons.size() > PEON_PUFFER;) {
			tempPeon = iteratorPeon.next();

			if(startSector.sizeOfConstructableBuilding() > 0) {
				if(movePeon.moveToConstructableBuildung(tempPeon, startSector)) {
					freePeons.remove(tempPeon);
					mainAi.lastMovedPeon = tempPeon;
					continue;
				}
			}

			if(startSector.sizeOfRepairableBuilding() > 0) {
				if(movePeon.moveToRepairableBuilding(tempPeon, startSector)) {
					freePeons.remove(tempPeon);
					mainAi.lastMovedPeon = tempPeon;
					continue;
				}
			}

			if(movePeon.moveToRessource(tempPeon, startSector)) {
				freePeons.remove(tempPeon);
				mainAi.lastMovedPeon = tempPeon;
				continue;
			}

			Iterator<Sector> iteratorOfSector = userAssets.iteratorOfSector();
			Sector sector = null;
			while(iteratorOfSector.hasNext()) {
				sector = iteratorOfSector.next();
				if(!sector.getId().equals(startSector.getId())) {
					if(isEnemyOnSector(sector)) {
						continue;
					}

					if(sector.sizeOfConstructableBuilding() > 0) {
						if(movePeon.moveToConstructableBuildung(tempPeon, sector)) {
							freePeons.remove(tempPeon);
							mainAi.lastMovedPeon = tempPeon;
							break;
						}
					}

					if(sector.sizeOfRepairableBuilding() > 0) {
						if(movePeon.moveToRepairableBuilding(tempPeon, sector)) {
							freePeons.remove(tempPeon);
							mainAi.lastMovedPeon = tempPeon;
							break;
						}
					}
					
					if(movePeon.moveToRessource(tempPeon, sector)) {
						freePeons.remove(tempPeon);
						mainAi.lastMovedPeon = tempPeon;
						break;
					}
				}
			}
		}

		if(freePeons.size() <= PEON_PUFFER) {
			mainAi.buildPeonPriority = 10;
		}

		freePeons.clear();
	}

	private boolean isEnemyOnSector(Sector sector) {
		Iterator<Unit> iteratorOfSectorUnits = sector.iteratorOfSectorUnits();
		Unit unit = null;
		while(iteratorOfSectorUnits.hasNext()) {
			unit = iteratorOfSectorUnits.next();
			if(!myUserAssetsID.equals(unit.getUserAssets().getId())) {
				return true;
			}
		}
		Iterator<Building> iteratorOfSectorBuildings = sector.iteratorOfSectorBuildings();
		Building build = null;
		while(iteratorOfSectorBuildings.hasNext()) {
			build = iteratorOfSectorBuildings.next();
			if(!myUserAssetsID.equals(build.getUserAssets().getId())) {
				return true;
			}
		}
		return false;
	}

}
