package ai.controlCenter;

import java.util.Iterator;

import view.game.buttons.SelectUnits;

import model.game.Archer;
import model.game.Barrack;
import model.game.Building;
import model.game.CIClient;
import model.game.Catapult;
import model.game.CommandHelper;
import model.game.Knight;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.Farm;
import model.game.Stronghold;
import model.game.Swordsman;
import model.game.Unit;
import model.game.User;
import model.game.UserAssets;

public class RushAI {
	private CommandHelper commandHelper;
	private Sector mySector;
	private User me;
	private int peons=3;
	private int peonsAll = 3;
	private int wood=0;
	private CIClient ciClient;
	
	public RushAI(CommandHelper commandHelper, Sector mySector,User me,CIClient ciClient){
		this.commandHelper = commandHelper;
		this.mySector = mySector;
		this.me = me;
		this.ciClient = ciClient;
	}
	
	public void run(){
		peonRush();		
	}
	
	//not working
	public void addAllPeonToSelection(){
		Iterator<Unit> unitIter = mySector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon && isNotWorking(unit)){
				if(!mySector.hasInSelectedUnit(unit)){
					mySector.addToSelectedUnit(unit);
				}
			}
		}
	}
	
	public boolean isNotWorking(Unit unit){
		boolean isNotWorking = false;
		if(((Peon) unit).getCollecting() == null && ((Peon) unit).getWorkingOn() == null){
			isNotWorking = true;
		}
		return isNotWorking;
	}
	
	//not working Peons
	public void countPeons(Sector tempSec){
		int p=0;
		Iterator<Unit> unitIter = tempSec.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon && isNotWorking(unit) && unit.getUserAssets().equals(me.getUserAssets())){
				p++;
			}
		}
		peons = p;
	}
	
	public void countAllPeons(){
		int pA=0;
		Iterator<Unit> unitIter = null;
		Iterator<Resource> resIter = null;
		Resource tempResource = null;
		Unit tempUnit = null;
		String units = "";
		String resource = "WOOD";
		int count = 1;
		resIter = mySector.iteratorOfSectorResources();
		while(resIter.hasNext()){
			tempResource = resIter.next();
			if (tempResource.getType().contains(resource)) {
				unitIter = tempResource.iteratorOfPeon(); 
				while(unitIter.hasNext()){
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() == me.getUserAssets()) {
						pA++;
					}
					
				}
			}
		}

		unitIter = mySector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon && isNotWorking(unit) && unit.getUserAssets().equals(me.getUserAssets())){
				pA++;
			}
		}
		peonsAll = pA;
	}
	
	public void countWood(){
		UserAssets currentUserUserAssets = me.getUserAssets();
		Iterator<Resource> iteratorOfCollectedResources = currentUserUserAssets.iteratorOfCollectedResources();
		if(!iteratorOfCollectedResources.hasNext()) {
			wood = 0;
		}
		else {
			while(iteratorOfCollectedResources.hasNext()) {
				Resource next2 = iteratorOfCollectedResources.next();
				if(next2.getType().equals("WOOD")) {
					try {
						wood = Integer.parseInt(next2.getQuantity());
					} catch (NumberFormatException e) {
						System.out.println("Error: Unable to parse resource quantity");
						wood = 0;
					}
				}
			}
		}
	}
	
	public boolean checkEnemySector(Sector enemySector){
		int enemys=0;
		Iterator<Building> buildIter = enemySector.iteratorOfSectorBuildings();
		Iterator<Building> constrIter = enemySector.iteratorOfConstructableBuilding();
		Iterator<Unit> unitIter = enemySector.iteratorOfSectorUnits();

		while(buildIter.hasNext()){
			Building build = buildIter.next();
			enemys++;
		}
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			enemys++;
		}
		
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(!unit.getUserAssets().equals(me.getUserAssets())){
				enemys++;
			}
		}
		
		if(enemys > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public String getAllPeonsNotWorking(Sector sector){
		String units = "";
		Unit tempUnit = null;
		Iterator<Unit> unitIter = null;
		
		for (unitIter = sector.iteratorOfSectorUnits(); unitIter.hasNext();) {
			tempUnit = unitIter.next();
			if (tempUnit instanceof Peon && tempUnit.getUserAssets() == me.getUserAssets()) {
				Peon tempPeon = (Peon) tempUnit;
				if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
					units += tempUnit.getId() + ",";
				}
			}
		}
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		}
		return units;
	}
	
	public String getAllPeonsFromWood(Sector sector){
		String units = "";
		Unit tempUnit = null;
		Resource tempResource = null;
		Iterator<Unit> unitIter = null;
		Iterator<Resource> resIter = null;
		
		for (resIter = mySector.iteratorOfSectorResources(); resIter.hasNext();) {
			tempResource = resIter.next();
			if (tempResource.getId()==getMyWoodID()) {
				// ...get some peons that could be removed
				for (unitIter = tempResource.iteratorOfPeon(); unitIter.hasNext();) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets() == me.getUserAssets()) {
						units += tempUnit.getId() + ",";
					}
					
				}
			}
		}
		if (!units.equals("")) {
			units = units.substring(0, units.length() - 1);
		}
		return units;
	}
	
	public String getMyBuildingID(String buildingType){
		Iterator<Building> buildIter = null;
		Building tempBuild = null;
		String buildingID = "";
		for(buildIter = mySector.iteratorOfSectorBuildings(); buildIter.hasNext();){
			tempBuild = buildIter.next();
			if(tempBuild.getClass().toString().contains(buildingType) && tempBuild.getUserAssets().equals(me.getUserAssets())){
				buildingID = tempBuild.getId();
			}
		}
		return buildingID;
	}
	
	public String getMyWoodID(){
		Iterator<Resource> resIter = null;
		Resource tempResource = null;
		String resourceID = "";
		for(resIter = mySector.iteratorOfSectorResources(); resIter.hasNext();){
			tempResource = resIter.next();
			if(tempResource.getType().contains("WOOD")){
				resourceID = tempResource.getId();
			}
		}
		return resourceID;
	}
	
	public void peonRush(){
		String myWoodID = getMyWoodID();		
		commandHelper.movePeonsToResource(myWoodID, getAllPeonsNotWorking(mySector));
			
		while(peonsAll < 10){
			countWood();
			while(wood < 10){
				countWood();
			}
			countPeons(mySector);
			while(peons >= 1){
				countPeons(mySector);
			}
			commandHelper.createUnit(getMyBuildingID("Stronghold"), "PEON", "1");
			while(peons == 0){
				countPeons(mySector);
			}
			commandHelper.movePeonsToResource(myWoodID, getAllPeonsNotWorking(mySector));
			peons = 0;
			countAllPeons();
		}
		Sector enemySector = null;
		Iterator<Sector> secIter = ciClient.getGame().iteratorOfSector();
		while(secIter.hasNext()){
			Sector sec = secIter.next();
			try{
			if(sec != mySector && sec.isStartSector()){
				enemySector = sec;
			}
			}catch (Exception e){
				
			}
		}
		countWood();
		while(wood < 80){
			countWood();
		}
		mySector.getGame().getCIClient().getServerConnection().create_building(mySector.getId(), "FARM");
		
		int i = 0;
		while(i == 0){
			Iterator<Building> constrIter = mySector.iteratorOfConstructableBuilding();
			System.out.println("DEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERPPPPPPPPP");
			while(constrIter.hasNext()){
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				Building constr = constrIter.next();
				if(constr instanceof Farm && constr.getUserAssets().equals(me.getUserAssets())){
					i++;
				}
			}
		}
		
		countAllPeons();
		commandHelper.moveUnitsToSector(mySector.getId(), getAllPeonsFromWood(mySector));
		countPeons(mySector);
		while(peons != peonsAll){
			countPeons(mySector);
		}	
		commandHelper.buildUpBuilding(getMyBuildingID("Farm"), getAllPeonsNotWorking(mySector));
		
		i = 0;
		while(i == 0){
			i=1;
			Iterator<Building> constrIter = mySector.iteratorOfConstructableBuilding();
			while(constrIter.hasNext()){
				Building constr = constrIter.next();
				if(constr instanceof Farm && constr.getUserAssets().equals(me.getUserAssets())){
					i=0;
				}
			}
		}
		countPeons(mySector);
		while(peons < peonsAll){
			countPeons(mySector);
		}
		commandHelper.movePeonsToResource(myWoodID, getAllPeonsNotWorking(mySector));
		
		while(peonsAll < 14){
			countWood();
			while(wood < 10){
				countWood();
			}
			countPeons(mySector);
			while(peons >= 1){
				countPeons(mySector);
			}
			commandHelper.createUnit(getMyBuildingID("Stronghold"), "PEON", "1");
			while(peons == 0){
				countPeons(mySector);
			}
			countAllPeons();
			if(peonsAll != 14){
				commandHelper.movePeonsToResource(myWoodID, getAllPeonsNotWorking(mySector));
				peons = 0;
			}
			countAllPeons();
		}
		
		countAllPeons();
		int temp = peonsAll-1;
		commandHelper.moveUnitsToSector(mySector.getId(), getAllPeonsFromWood(mySector));
		countPeons(mySector);
		while(peons != peonsAll){
			countPeons(mySector);
		}
		
		commandHelper.moveUnitsToSector(enemySector.getId(), getAllPeonsNotWorking(mySector));
		
		countWood();
		while(wood >= 20){
			countPeons(mySector);
			while(peons >= 1){
				countPeons(mySector);
			}
			commandHelper.createUnit(getMyBuildingID("Stronghold"), "PEON", "1");
			countPeons(mySector);
			while(peons == 0){
				countPeons(mySector);
			}
			commandHelper.moveUnitsToSector(enemySector.getId(), getAllPeonsNotWorking(mySector));
			countWood();
		}
		
		while(checkEnemySector(enemySector)){

		}
		
		Iterator<Sector> secIter2 = ciClient.getGame().iteratorOfSector();
		Sector unitSec = enemySector;
		Sector tempSec = null;
		
		while(secIter2.hasNext()){
			countPeons(unitSec);
			tempSec = secIter2.next();
			try{
				commandHelper.moveUnitsToSector(tempSec.getId(), getAllPeonsNotWorking(unitSec));
				while(checkEnemySector(tempSec)){
						
				}
				unitSec = tempSec;
			}catch (Exception e){
					
			}
		}
	}
	
}
