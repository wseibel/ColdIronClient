package model.ai;

import java.util.Iterator;

import model.game.Building;
import model.game.CommandHelper;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;

public class MovePeonTo {

	// The AI's strategy is manipulated by these values
	private static final int PEON_LIMIT_TO_CONSTRUCT = 2;
	private static final int PEON_LIMIT_TO_REPAIR = 2;
	private static final int PEON_LIMIT_TO_COLLECTING_WOOD = 2;
	private static final int PEON_LIMIT_TO_COLLECTING_STONE = 2;
	private static final int PEON_LIMIT_TO_COLLECTING_IRON = 2;
	
	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private String myUserAssetsID = null;
	private CommandHelper commandHelper = null;

	/**
	 * constructor
	 * 
	 * @param aiHelper
	 * @param aiModel
	 * @param myUserAssetsID
	 * @param commandHelper
	 */
	public MovePeonTo(AIHelper aiHelper, AiModelAnalyzer aiModel,
			String myUserAssetsID, CommandHelper commandHelper) {
		super();
		this.aiHelper = aiHelper;
		this.aiModel = aiModel;
		this.myUserAssetsID = myUserAssetsID;
		this.commandHelper = commandHelper;
//		System.err.println("MovePeonTo got inited with commandHelper == " + commandHelper); //TODO: remove me
	}

	public boolean moveToConstructableBuildung(Peon peon, Sector sector) {
		// choose constructable building
		Iterator<Building> constructableBuilding = sector.iteratorOfConstructableBuilding();
		Building build, min = constructableBuilding.next();
		while(constructableBuilding.hasNext()) {
			build = constructableBuilding.next();
			if(build.sizeOfPeon() < PEON_LIMIT_TO_CONSTRUCT && build.sizeOfPeon() < min.sizeOfPeon()) {
				min = build;
			}
		}
		if(min.sizeOfPeon() < PEON_LIMIT_TO_CONSTRUCT) {
			peon.setWorkingOn(min);
			commandHelper.buildUpBuilding(min.getId(), peon.getId());
			return true;
		} else {
			return false;
		}
	}

	public boolean moveToRepairableBuilding(Peon peon, Sector sector) {
		// choose repariable building
		Iterator<Building> repairableBuilding = sector.iteratorOfRepairableBuilding();
		Building build, min = repairableBuilding.next();
		while(repairableBuilding.hasNext()) {
			build = repairableBuilding.next();
			if(build.sizeOfPeon() < PEON_LIMIT_TO_REPAIR && build.sizeOfPeon() < min.sizeOfPeon()) {
				min = build;
			}
		}
		if(min.sizeOfPeon() < PEON_LIMIT_TO_REPAIR) {
			peon.setWorkingOn(min);
			commandHelper.repairBuilding(min.getId(), peon.getId());
			return true;
		} else {
			return false;
		}
	}

	public boolean moveToRessource(Peon peon, Sector sector) {
		// choose resource
		Iterator<Resource> resources = sector.iteratorOfSectorResources();
		Resource tempRes, min = resources.next();
		if("IRON".equals(min.getType()) && min.sizeOfPeon() < PEON_LIMIT_TO_COLLECTING_IRON) {
			peon.setCollecting(min);
			commandHelper.movePeonsToResource(min.getId(), peon.getId());
			return true;
		}
		while(resources.hasNext()) {
			tempRes = resources.next();
			if("IRON".equals(tempRes.getType()) && tempRes.sizeOfPeon() < PEON_LIMIT_TO_COLLECTING_IRON) {
				peon.setCollecting(tempRes);
				commandHelper.movePeonsToResource(tempRes.getId(), peon.getId());
				return true;
			}
			if("WOOD".equals(tempRes.getType()) && tempRes.sizeOfPeon() < PEON_LIMIT_TO_COLLECTING_WOOD && tempRes.sizeOfPeon() < min.sizeOfPeon()) {
				min = tempRes;
				continue;
			}
			if("STONE".equals(tempRes.getType()) && tempRes.sizeOfPeon() < PEON_LIMIT_TO_COLLECTING_STONE && tempRes.sizeOfPeon() < min.sizeOfPeon()) {
				min = tempRes;
				continue;
			}
		}
		if("WOOD".equals(min.getType()) && min.sizeOfPeon() < PEON_LIMIT_TO_COLLECTING_WOOD) {
			peon.setCollecting(min);
			commandHelper.movePeonsToResource(min.getId(), peon.getId());
			return true;
		}
		if("STONE".equals(min.getType()) && min.sizeOfPeon() < PEON_LIMIT_TO_COLLECTING_STONE) {
			peon.setCollecting(min);
			commandHelper.movePeonsToResource(min.getId(), peon.getId());
			return true;
		}
		return false;
	}
}
