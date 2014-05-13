package view.game.buttons;

import java.util.Iterator;

import model.game.Barrack;
import model.game.Building;
import model.game.CommandHelper;
import model.game.Sector;
import model.game.Stronghold;
import model.game.User;

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

public class UpgradeBuildings implements ComponentListener{
	private GameContainer container;
	private String selection;
	boolean upgradeBuildingPressed;
	private Portrait portrait;
	
	private Image barrackLevel2Icon;
	private Image barrackLevel3Icon;
	private Image strongholdLevel2Icon;
	private Image strongholdLevel3Icon;
	private Image towerLevel2Icon;
	private Image towerLevel3Icon;
	private Image abortIcon;
	private Image emptyPortrait;
	private Image abortBuildIcon;
	private Image upgradeIcon;
	
	private MouseOverArea buildBarrackLevel2;
	private MouseOverArea buildBarrackLevel3;
	private MouseOverArea buildStrongholdLevel2;
	private MouseOverArea buildStrongholdLevel3;
	private MouseOverArea buildTowerLevel2;
	private MouseOverArea buildTowerLevel3;
	private MouseOverArea abortButton;
	private MouseOverArea abortBuildButton;
	private MouseOverArea upgradeButton;
	
	private CommandHelper commandHelper;
	private Sector currentSector;
	private int barracksLevel1;
	private int barracksLevel2;
	private int barracksLevel3;
	
	public UpgradeBuildings(GameContainer container, String selection, CommandHelper commandHelper,
			Sector currentSector){
		this.container = container;
		this.selection = selection;
		this.commandHelper = commandHelper;
		this.currentSector = currentSector;
	}
	
	public void init()throws SlickException{
		countBuildings();
		
		upgradeBuildingPressed = false;
		barrackLevel2Icon = new Image("res/Ingame/LowerBar/Icons/BarrackLevel2.png");
		barrackLevel3Icon = new Image("res/Ingame/LowerBar/Icons/BarrackLevel3.png");
		strongholdLevel2Icon = new Image("res/Ingame/LowerBar/Icons/StrongholdLevel2.png");
		strongholdLevel3Icon = new Image("res/Ingame/LowerBar/Icons/StrongholdLevel3.png");
		abortIcon = new Image("res/Ingame/LowerBar/Icons/Abort.png");
		towerLevel2Icon = new Image("res/Ingame/LowerBar/Icons/TowerLevel2.png");
		towerLevel3Icon = new Image("res/Ingame/LowerBar/Icons/TowerLevel3.png");
		emptyPortrait = new Image("res/Ingame/LowerBar/Portraits/Empty.png");
		abortBuildIcon = new Image("res/Ingame/LowerBar/Icons/AbortBuild.png");
		upgradeIcon = new Image("res/Ingame/LowerBar/Icons/Upgrade.png");
		
		upgradeButton = new MouseOverArea(container, upgradeIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		abortButton = new MouseOverArea(container, abortIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
				
		buildBarrackLevel2 = new MouseOverArea(container, barrackLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildBarrackLevel3 = new MouseOverArea(container, barrackLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
				
		buildTowerLevel2 = new MouseOverArea(container, towerLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildTowerLevel3 = new MouseOverArea(container, towerLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildStrongholdLevel2 = new MouseOverArea(container, strongholdLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildStrongholdLevel3 = new MouseOverArea(container, strongholdLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		abortBuildButton = new MouseOverArea(container, abortBuildIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
	}
	
	public boolean getUpgradeBuildingPressed(){
		return this.upgradeBuildingPressed;
	}
	
	public void setUpgradeBuildingPressed(boolean upgradeBuildingPressed){
		this.upgradeBuildingPressed = upgradeBuildingPressed;
	}
	
	public String getSelection(){
		return this.selection;
	}
	
	public void setSelection(String selection){
		this.selection = selection;
	}
	
	public void render(Graphics g)throws SlickException{
		if(selection.equals("strongholdLevel1")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			buildStrongholdLevel2.render(container, g);
			buildStrongholdLevel2.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("strongholdLevel2")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			buildStrongholdLevel3.render(container, g);
			buildStrongholdLevel3.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("strongholdLevel3")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("upgrade")){
			abortButton.render(container, g);
			abortButton.setNormalColor(new Color(1,1,1,0.7f));
			if(barracksLevel1>0){
				buildBarrackLevel2.render(container, g);
				buildBarrackLevel2.setNormalColor(new Color(1,1,1,0.7f));
			}
			if(barracksLevel2>0){
				buildBarrackLevel3.render(container, g);
				buildBarrackLevel3.setNormalColor(new Color(1,1,1,0.7f));
			}
		}else if(selection.equals("barrackLevel1")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			if(barracksLevel1>0 | barracksLevel2>0){
				upgradeButton.render(container, g);
				upgradeButton.setNormalColor(new Color(1,1,1,0.7f));
			}
		}else if(selection.equals("barrackLevel2")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			if(barracksLevel1>0 | barracksLevel2>0){
				upgradeButton.render(container, g);
				upgradeButton.setNormalColor(new Color(1,1,1,0.7f));
			}
		}else if(selection.equals("barrackLevel3")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			if(barracksLevel1>0 | barracksLevel2>0){
				upgradeButton.render(container, g);
				upgradeButton.setNormalColor(new Color(1,1,1,0.7f));
			}
		}else if(selection.equals("towerLevel1")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			buildTowerLevel2.render(container, g);
			buildTowerLevel2.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("towerLevel2")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			buildTowerLevel3.render(container, g);
			buildTowerLevel3.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("towerLevel3")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("farm")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("forge")){
			abortBuildButton.render(container, g);
			abortBuildButton.setNormalColor(new Color(1,1,1,0.7f));
			}
	}
	
	public void componentActivated(AbstractComponent source) {
		if(source == buildStrongholdLevel2){
			upgradeBuildingPressed = true;
			commandHelper.upgradeBuilding("Stronghold", 1);
		}else if(source == buildStrongholdLevel3){
			upgradeBuildingPressed = true;
			commandHelper.upgradeBuilding("Stronghold", 2);
		}else if(source == upgradeButton){
			selection = "upgrade";
			upgradeBuildingPressed = true;
		}else if(source == buildBarrackLevel2){
			upgradeBuildingPressed = true;
			commandHelper.upgradeBuilding("Barrack", 1);
		}else if(source == buildBarrackLevel3){
			upgradeBuildingPressed = true;
			commandHelper.upgradeBuilding("Barrack", 2);
		}else if(source == buildTowerLevel2){
			upgradeBuildingPressed = true;
			commandHelper.upgradeBuilding("Tower", 1);
		}else if(source == buildTowerLevel3){
			upgradeBuildingPressed = true;
			commandHelper.upgradeBuilding("Tower", 2);
		}else if(source == abortButton){
			if(barracksLevel3>0){
				selection = "barrackLevel3";
			}else if(barracksLevel2>0){
				selection = "barrackLevel2";
			}else{
				selection = "barrackLevel1";
			}
				upgradeBuildingPressed = true;
		}else if(source == abortBuildButton){
			upgradeBuildingPressed = true;
			String buildingtype = "unknown";
			if (selection.contains("stronghold")) {
				buildingtype = "Stronghold";
			} else if (selection.contains("barrack")) {
				buildingtype = "Barrack";
			} else if (selection.contains("tower")) {
				buildingtype = "Tower";
			} else if (selection.contains("farm")) {
				buildingtype = "Farm";
			} else if (selection.contains("forge")) {
				buildingtype = "Forge";
			} 			
			commandHelper.dropBuilding(buildingtype);
		}
		
	}
	
	public void countBuildings(){
		int baLvl1=0,baLvl2=0,baLvl3=0;
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		
		while(buildIter.hasNext()){
			Building build = buildIter.next();
			if(build instanceof Barrack && build.getLevel() == 1) {
				baLvl1++;
			}else if(build instanceof Barrack && build.getLevel() == 2) {
				baLvl2++;	
			}else if(build instanceof Barrack && build.getLevel() == 3) {
				baLvl3++;	
			}
		}
		barracksLevel1 = baLvl1;
		barracksLevel2 = baLvl2;
		barracksLevel3 = baLvl3;
		
	}

	public MouseOverArea getBuildBarrackLevel2() {
		return buildBarrackLevel2;
	}

	public MouseOverArea getBuildBarrackLevel3() {
		return buildBarrackLevel3;
	}

	public MouseOverArea getBuildStrongholdLevel2() {
		return buildStrongholdLevel2;
	}

	public MouseOverArea getBuildStrongholdLevel3() {
		return buildStrongholdLevel3;
	}

	public MouseOverArea getBuildTowerLevel2() {
		return buildTowerLevel2;
	}

	public MouseOverArea getBuildTowerLevel3() {
		return buildTowerLevel3;
	}

	public MouseOverArea getUpgradeButton() {
		return upgradeButton;
	}

	public MouseOverArea getAbortButton() {
		return abortButton;
	}

	public MouseOverArea getAbortBuildButton() {
		return abortBuildButton;
	}
}
