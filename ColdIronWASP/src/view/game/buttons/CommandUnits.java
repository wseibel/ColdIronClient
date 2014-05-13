package view.game.buttons;

import java.util.Iterator;

import javax.annotation.Resources;

import model.game.Barrack;
import model.game.Building;
import model.game.CommandHelper;
import model.game.Farm;
import model.game.Forge;
import model.game.Resource;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Tower;
import model.game.User;
import model.game.UserAssets;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import view.game.Portrait;

public class CommandUnits implements ComponentListener{
	private GameContainer container;
	private String selection;
	boolean commandUnitPressed;
	private Portrait portrait;
	
	int strongholdBuildUp;
	int barrackBuildUp;
	int forgeBuildUp;
	int farmBuildUp;
	int towerBuildUp;
	int wood;
	int stone;
	int iron;
	
	private Image buildBarrackIcon;
	private Image buildFarmIcon;
	private Image buildForgeIcon;
	private Image buildTowerIcon;
	private Image buildStrongholdIcon;
	private Image repairIcon;
	private Image abortIcon;
	private Image ironIcon;
	private Image stoneIcon;
	private Image woodIcon;
	private Image gather;
	private Image barrackIcon;
	private Image forgeIcon;
	private Image farmIcon;
	private Image strongholdIcon;
	private Image towerIcon;
	private Image moveInTowerLvl1;
	private Image moveInTowerLvl2;
	private Image moveInTowerLvl3;
	private Image moveArcherOut;
	private Image emptyPortrait;
	private Image buildButtonIcon;
	
	private MouseOverArea buildButton;
	private MouseOverArea buildUpBarrackLevel1;
	private MouseOverArea buildUpFarm;
	private MouseOverArea buildUpForge;
	private MouseOverArea buildUpTowerLevel1;
	private MouseOverArea buildUpStrongholdLevel1;
	private MouseOverArea gatherResource;
	private MouseOverArea gatherIron;
	private MouseOverArea gatherStone;
	private MouseOverArea gatherWood;
	private MouseOverArea abortButton;
	private MouseOverArea repairBuilding;
	private MouseOverArea repairBarrack;
	private MouseOverArea repairStronghold;
	private MouseOverArea repairTower;
	private MouseOverArea repairFarm;
	private MouseOverArea repairForge;
	private MouseOverArea moveButton;
	private MouseOverArea moveInTower1;
	private MouseOverArea moveInTower2;
	private MouseOverArea moveInTower3;
	private MouseOverArea moveOutTower;
	
	private CommandHelper commandHelper;
	private Sector currentSector;
	public void  setCurrentSector (Sector currentSector){
		this.currentSector = currentSector;
	}
	private User currentUser;
	
	public CommandUnits(GameContainer container, String selection, CommandHelper commandHelper,
			Sector currentSector, User currentUser){
		this.container = container;
		this.selection = selection;
		this.commandHelper = commandHelper;
		this.currentSector = currentSector;
		this.currentUser = currentUser;
	}
	
	public void init() throws SlickException{
		countBuildingsToBuildUp();
		countResources();
		
		abortIcon = new Image("res/Ingame/LowerBar/Icons/Abort.png");
		buildBarrackIcon = new Image("res/Ingame/LowerBar/Icons/BuildBarrack.png");
		buildFarmIcon = new Image("res/Ingame/LowerBar/Icons/BuildFarm.png");
		buildForgeIcon = new Image("res/Ingame/LowerBar/Icons/BuildForge.png");
		buildTowerIcon = new Image("res/Ingame/LowerBar/Icons/BuildTower.png");
		buildStrongholdIcon = new Image("res/Ingame/LowerBar/Icons/BuildStronghold.png");
		ironIcon = new Image("res/Ingame/LowerBar/Icons/Iron.png");
		stoneIcon = new Image("res/Ingame/LowerBar/Icons/Stone.png");
		woodIcon = new Image("res/Ingame/LowerBar/Icons/Wood.png");
		repairIcon = new Image("res/Ingame/LowerBar/Icons/Repair.png");
		gather = new Image("res/Ingame/LowerBar/Icons/Gather.png");
		forgeIcon = new Image("res/Ingame/LowerBar/Icons/Forge.png");
		farmIcon = new Image("res/Ingame/LowerBar/Icons/Farm.png");
		barrackIcon = new Image("res/Ingame/LowerBar/Icons/Barrack.png");
		strongholdIcon = new Image("res/Ingame/LowerBar/Icons/Stronghold.png");
		towerIcon = new Image("res/Ingame/LowerBar/Icons/Tower.png");
		moveInTowerLvl1 = new Image("res/Ingame/LowerBar/Icons/MoveInTowerLevel1.png");
		moveInTowerLvl2 = new Image("res/Ingame/LowerBar/Icons/MoveInTowerLevel2.png");
		moveInTowerLvl3 = new Image("res/Ingame/LowerBar/Icons/MoveInTowerLevel3.png");
		moveArcherOut = new Image("res/Ingame/LowerBar/Icons/MoveArcherOut.png");
		emptyPortrait = new Image("res/Ingame/LowerBar/Portraits/Empty.png");
		buildButtonIcon = new Image("res/Ingame/LowerBar/Icons/Buildings.png");
		
		abortButton = new MouseOverArea(container, abortIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildButton = new MouseOverArea(container, buildButtonIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildUpFarm = new MouseOverArea(container, buildFarmIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildUpBarrackLevel1 = new MouseOverArea(container, buildBarrackIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildUpForge = new MouseOverArea(container, buildForgeIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildUpTowerLevel1 = new MouseOverArea(container, buildTowerIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildUpStrongholdLevel1 = new MouseOverArea(container, buildStrongholdIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		gatherResource = new MouseOverArea(container, gather.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		gatherWood = new MouseOverArea(container, woodIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		gatherStone = new MouseOverArea(container, stoneIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		gatherIron = new MouseOverArea(container, ironIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		repairBuilding = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		repairStronghold = new MouseOverArea(container, strongholdIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		repairBarrack = new MouseOverArea(container, barrackIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		repairForge = new MouseOverArea(container, forgeIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		repairTower = new MouseOverArea(container, towerIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		repairFarm = new MouseOverArea(container, farmIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		moveInTower1 = new MouseOverArea(container, moveInTowerLvl1.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		moveInTower2 = new MouseOverArea(container, moveInTowerLvl2.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		moveInTower3 = new MouseOverArea(container, moveInTowerLvl3.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		moveOutTower = new MouseOverArea(container, moveArcherOut.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
	
	}
	
	public boolean getCommandUnitPressed(){
		return this.commandUnitPressed;
	}
	
	public void setCommandUnitPressed(boolean commandUnitPressed){
		this.commandUnitPressed = commandUnitPressed;
	}
	
	public String getSelection(){
		return this.selection;
	}
	
	public void setSelection(String selection){
		this.selection = selection;
	}
	

	public void render(Graphics g) throws SlickException{
		if(selection.equals("peon")){
			buildButton.render(container, g);
			buildButton.setNormalColor(new Color(1,1,1,0.7f));
			repairBuilding.render(container, g);
			repairBuilding.setNormalColor(new Color(1,1,1,0.7f));
			if(stone > 0 || wood > 0 || iron > 0){
				gatherResource.render(container, g);
				gatherResource.setNormalColor(new Color(1,1,1,0.7f));
			}
		}else if(selection.equals("build")){
			if(strongholdBuildUp>0){
				buildUpStrongholdLevel1.render(container, g);
				buildUpStrongholdLevel1.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(barrackBuildUp>0){
				buildUpBarrackLevel1.render(container, g);
				buildUpBarrackLevel1.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(forgeBuildUp>0){
				buildUpForge.render(container, g);
				buildUpForge.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(farmBuildUp>0){
				buildUpFarm.render(container, g);
				buildUpFarm.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(towerBuildUp>0){
				buildUpTowerLevel1.render(container, g);
				buildUpTowerLevel1.setNormalColor(new Color(1,1,1,0.7f));
			}
			abortButton.render(container, g);
			abortButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("swordsman")){
	
		}else if(selection.equals("archer")){
			moveInTower1.render(container, g);
			moveInTower1.setNormalColor(new Color(1,1,1,0.7f));
			moveInTower2.render(container, g);
			moveInTower2.setNormalColor(new Color(1,1,1,0.7f));
			moveInTower3.render(container, g);
			moveInTower3.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("knight")){
			
		}else if(selection.equals("catapult")){
			
		}else if(selection.equals("gather")){
			if(wood > 0){
				gatherWood.render(container, g);
				gatherWood.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(stone > 0){
				gatherStone.render(container, g);
				gatherStone.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(iron > 0){
				gatherIron.render(container, g);
				gatherIron.setNormalColor(new Color(1,1,1,0.7f));
			}
			abortButton.render(container, g);
			abortButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("repair")){
			repairStronghold.render(container, g);
			repairStronghold.setNormalColor(new Color(1,1,1,0.7f));
			repairBarrack.render(container, g);
			repairBarrack.setNormalColor(new Color(1,1,1,0.7f));
			repairForge.render(container, g);
			repairForge.setNormalColor(new Color(1,1,1,0.7f));
			repairTower.render(container, g);
			repairTower.setNormalColor(new Color(1,1,1,0.7f));
			repairFarm.render(container, g);
			repairFarm.setNormalColor(new Color(1,1,1,0.7f));
			abortButton.render(container, g);
			abortButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("mixed")){
			
		}else if(selection.equals("towerLevel1")){
			moveOutTower.render(container, g);
			moveOutTower.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("towerLevel2")){
			moveOutTower.render(container, g);
			moveOutTower.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("towerLevel3")){
			moveOutTower.render(container, g);
			moveOutTower.setNormalColor(new Color(1,1,1,0.7f));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void componentActivated(AbstractComponent source) {
		int level = 0;
		int maxLevel = 0;
		Building tempBuilding = null;
		Iterator<Building> buildingIter = null;
		Boolean foundOne = false;
		Sector currentSector = null;
		UserAssets currentUser = null;
		if (commandHelper.getDummyOn()) {
			commandHelper.init();
			currentSector = commandHelper.getMainDummy().getCurrentSector();
			currentUser = commandHelper.getMainDummy().getCurrentUser().getUserAssets();
		} else {
			commandHelper.init();
			currentSector = commandHelper.getMain().getCurrentSector();
			currentUser = commandHelper.getMain().getCurrentUser().getUserAssets();
		}
		
		if(source == abortButton){
				selection = "peon";
				commandUnitPressed = true;
		}else if(source == buildButton){
			countBuildingsToBuildUp();
			selection = "build";
			commandUnitPressed = true;
		}else if(source == buildUpFarm){
			commandHelper.buildUpBuilding("Farm");
			countBuildingsToBuildUp();
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == buildUpBarrackLevel1){
			commandHelper.buildUpBuilding("Barrack");
			countBuildingsToBuildUp();
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == buildUpForge){
			commandHelper.buildUpBuilding("Forge");
			countBuildingsToBuildUp();
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == buildUpTowerLevel1){
			commandHelper.buildUpBuilding("Tower");
			countBuildingsToBuildUp();
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == buildUpStrongholdLevel1){
			commandHelper.buildUpBuilding("Stronghold");
			countBuildingsToBuildUp();
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == repairBuilding){
			selection = "repair";
			commandUnitPressed = true;
		}else if(source == repairStronghold){
			// first get the level of the stronghold
			for (buildingIter = currentSector.iteratorOfSectorBuildings();
					buildingIter.hasNext() && !foundOne;) {
				tempBuilding = buildingIter.next();
				if (tempBuilding.getClass().toString().contains("Stronghold") &&
						tempBuilding.getUserAssets() == currentUser) {
					level = tempBuilding.getLevel();
					foundOne = true;
				}
			}
			if (foundOne) {
				// send peons for repair
				commandHelper.repairBuilding("Stronghold", level);
			} else {
				currentSector.getGame().getCIClient().getChainMaster().setChatString("ERROR_MESSAGE:ERROR:you have no Stronghold that needs repair");
			}
		}else if(source == repairBarrack){
			// find a reparable Barrack of maximum level
			buildingIter = currentSector.iteratorOfRepairableBuilding();
			while(buildingIter.hasNext() && !foundOne){
				tempBuilding = buildingIter.next();
				if(tempBuilding.getClass().toString().contains("Barrack")){
					if (tempBuilding.getLevel() == 3) {
						maxLevel = 3;
						foundOne = true;
					} else {
						if (tempBuilding.getLevel() == 2) {
							maxLevel = 2;
							foundOne = true;
						} else {
							if (tempBuilding.getLevel() == 1) {
								maxLevel = 1;
								foundOne = true;
							}
						}
					}
				
				}
			}
			if (foundOne) {
				// send peons for repair
				commandHelper.repairBuilding("Barrack", maxLevel);
			} else {
				currentSector.getGame().getCIClient().getChainMaster().setChatString("ERROR_MESSAGE:ERROR:you have no Barrack that needs repair");
			}	
		}else if(source == repairForge){
			commandHelper.repairBuilding("Forge", 1);
		}else if(source == repairTower){
			// find a reparable Tower of maximum level
			buildingIter = currentSector.iteratorOfRepairableBuilding();
			while(buildingIter.hasNext() && !foundOne){
				tempBuilding = buildingIter.next();
				if(tempBuilding.getClass().toString().contains("Tower")){
					if (tempBuilding.getLevel() == 3) {
						maxLevel = 3;
						foundOne = true;
					} else {
						if (tempBuilding.getLevel() == 2) {
							maxLevel = 2;
							foundOne = true;
						} else {
							if (tempBuilding.getLevel() == 1) {
								maxLevel = 1;
								foundOne = true;
							}
						}
					}
				
				}
			}
			if (foundOne) {
				// send peons for repair
				commandHelper.repairBuilding("Tower", maxLevel);
			} else {
				currentSector.getGame().getCIClient().getChainMaster().setChatString("ERROR_MESSAGE:ERROR:you have no Tower that needs repair");
			}	
		}else if(source == repairFarm){
			commandHelper.repairBuilding("Farm", 1);
		}else if(source == gatherResource){
			selection = "gather";
			commandUnitPressed = true;
		}else if(source == gatherWood){
			commandHelper.movePeonsToResource("WOOD");
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == gatherIron){
			commandHelper.movePeonsToResource("IRON");
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == gatherStone){
			commandHelper.movePeonsToResource("STONE");
			selection = "";
			commandUnitPressed = true;
			currentSector.removeAllFromSelectedUnit();
			portrait = new Portrait(emptyPortrait, container);
		}else if(source == moveInTower1){
			commandHelper.moveArchersIntoTower(1);
		}else if(source == moveInTower2){
			commandHelper.moveArchersIntoTower(2);
		}else if(source == moveInTower3){
			commandHelper.moveArchersIntoTower(3);
		}else if(source == moveOutTower){
			// find the level of the current selected towers
			if(selection.equals("towerLevel1")) {
				level = 1;
				foundOne = true;
			} else {
				if(selection.equals("towerLevel2")) {
					level = 2;
					foundOne = true;
				} else {
					if(selection.equals("towerLevel3")) {
						level = 3;
						foundOne = true;
					} 
				}
			}
			if (foundOne) {
				// remove an archer
				commandHelper.removeArchersFromTower(level, 1);
			} else {
				System.err.println("no Towers of level 1 or 2 or 3 selected but got click \"moveOutTower\"");
			}	
		} 

	}
	

	public void update(){
		countBuildingsToBuildUp();
		countResources();
	}
	
	
	public void countBuildingsToBuildUp(){
		int stBU=0, baBU=0, foBU=0, faBU=0, toBU=0;
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();
		
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			if(constr instanceof Stronghold && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				stBU++;
			}else if(constr instanceof Barrack && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				baBU++;
			}else if(constr instanceof Forge && constr.getUserAssets().equals(currentUser.getUserAssets())){
				foBU++;
			}else if(constr instanceof Farm && constr.getUserAssets().equals(currentUser.getUserAssets())){
				faBU++;
			}else if(constr instanceof Tower && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				toBU++;
			}
		}
		strongholdBuildUp=stBU;
		barrackBuildUp=baBU;
		forgeBuildUp=foBU;
		farmBuildUp=faBU;
		towerBuildUp=toBU;
	}
	
	public void countResources(){
		int wo=0, st=0, ir=0;
		Iterator<Resource> resIter = currentSector.iteratorOfSectorResources();
		
		while(resIter.hasNext()){
			Resource res = resIter.next();
			if(res.getType().contains("WOOD") ){
				wo++;
			}else if(res.getType().contains("STONE") ){
				st++;
			}else if(res.getType().contains("IRON") ){
				ir++;
			}
		}
		wood = wo;
		stone = st;
		iron = ir;
	}

	public MouseOverArea getBuildButton() {
		return buildButton;
	}

	public MouseOverArea getBuildUpBarrackLevel1() {
		return buildUpBarrackLevel1;
	}

	public MouseOverArea getBuildUpFarm() {
		return buildUpFarm;
	}

	public MouseOverArea getBuildUpForge() {
		return buildUpForge;
	}

	public MouseOverArea getBuildUpTowerLevel1() {
		return buildUpTowerLevel1;
	}

	public MouseOverArea getBuildUpStrongholdLevel1() {
		return buildUpStrongholdLevel1;
	}

	public MouseOverArea getRepairBuilding() {
		return repairBuilding;
	}

	public MouseOverArea getRepairBarrack() {
		return repairBarrack;
	}

	public MouseOverArea getRepairStronghold() {
		return repairStronghold;
	}

	public MouseOverArea getRepairTower() {
		return repairTower;
	}

	public MouseOverArea getRepairFarm() {
		return repairFarm;
	}

	public MouseOverArea getRepairForge() {
		return repairForge;
	}

	public MouseOverArea getGatherResource() {
		return gatherResource;
	}

	public MouseOverArea getGatherIron() {
		return gatherIron;
	}

	public MouseOverArea getGatherStone() {
		return gatherStone;
	}

	public MouseOverArea getGatherWood() {
		return gatherWood;
	}

	public int getStrongholdBuildUp() {
		return strongholdBuildUp;
	}

	public int getBarrackBuildUp() {
		return barrackBuildUp;
	}

	public int getForgeBuildUp() {
		return forgeBuildUp;
	}

	public int getFarmBuildUp() {
		return farmBuildUp;
	}

	public int getTowerBuildUp() {
		return towerBuildUp;
	}

	public MouseOverArea getAbortButton() {
		return abortButton;
	}

	public MouseOverArea getMoveInTower1() {
		return moveInTower1;
	}

	public MouseOverArea getMoveInTower2() {
		return moveInTower2;
	}

	public MouseOverArea getMoveInTower3() {
		return moveInTower3;
	}

	public MouseOverArea getMoveOutTower() {
		return moveOutTower;
	}
}
