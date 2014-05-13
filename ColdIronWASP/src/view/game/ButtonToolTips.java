package view.game;

import mdes.slick.sui.Button;
import mdes.slick.sui.Display;
import model.game.CIClient;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import view.game.buttons.BuildUnits;
import view.game.buttons.CommandUnits;
import view.game.buttons.PlaceBuildings;
import view.game.buttons.UpgradeBuildings;

public class ButtonToolTips {

	private GameContainer container;
	private BuildUnits buildUnitButtons;
//	private Display display;
	private Button toolTip;
	private String toolTipText;
	private CIClient ciClient;
	private Button blaab;
	private UpgradeBuildings upgradeBuildingButtons;
	private CommandUnits commandUnitButtons;
	private String selection;
	private PlaceBuildings placeBuildingButtons;

	public ButtonToolTips(GameContainer container, UpgradeBuildings upgradeBuildingButtons, BuildUnits buildUnitButtons, CommandUnits commandUnitButtons, CIClient ciClient, String selection) {
		this.container = container;
//		this.placeBuildingButtons = placeBuildingButtons;
		this.upgradeBuildingButtons = upgradeBuildingButtons;
		this.buildUnitButtons = buildUnitButtons;
		this.commandUnitButtons = commandUnitButtons;
		this.ciClient = ciClient;
		this.selection = selection;
	}

	public void init() throws SlickException {
//		display = new Display(container);
//		Every useless, because it cause  gamecrash after changing the resolution!
				
		//just a single button is needed
		toolTip = new Button();
		toolTip.setVisible(true);
	}
	
	public void update(GameContainer container, int delta) {
		//dont needed
	}
	
	public void render(Graphics g) throws SlickException {
		renderBuildUnits();
		renderUpgradeBuildingButtons();
		renderCommandUnitButtons();
		renderPlaceBuildingButtons();
		toolTip.render(container, g);
	}
	
	private void calcToolTip() {
		toolTip.setVisible(true);
		toolTip = new Button(toolTipText);
		float toolTipX;
		float toolTipY;
		if (Mouse.getX() + toolTip.getWidth() > container.getWidth()) {
			toolTipX = Mouse.getX() - toolTip.getWidth() - 10;
		} else {
			toolTipX = Mouse.getX() + 10;
		}
		if (Mouse.getY() - toolTip.getHeight() < container.getHeight()) {
			toolTipY = -Mouse.getY() - toolTip.getHeight() - 10
					+ container.getHeight();
		} else {
			toolTipY = -Mouse.getY() + 10 + container.getHeight();

		}
		toolTip.setLocation(toolTipX, toolTipY);
		// set font by resolution
		// toolTip.setFont(chatWindow.getFontChatCurrent());
	}
	
	private void renderBuildUnits(){
		// Tool tips for build Units
		//Peon
		if ((buildUnitButtons.getBuildPeon().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("strongholdLevel1"))
				|| (buildUnitButtons.getBuildPeon().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("strongholdLevel2"))
				|| (buildUnitButtons.getBuildPeon().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("strongholdLevel3"))) {
			toolTipText = "Create Peon\nPeons build/repair buildings and\ngather resources.\nHP:                 20\n\rStrength:          1\nTimeToBuild:    5s\nCosts:\n -  10  wood";
			calcToolTip();
		} else {
			toolTip.setVisible(false);
		}
		//Swordsman Level 1
		if ((buildUnitButtons.getBuildSwordsmanLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel1"))
				|| (buildUnitButtons.getBuildSwordsmanLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel2"))
				|| (buildUnitButtons.getBuildSwordsmanLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Swordsman Level 1\nThe Swordsman is the classic melee unit.\nHP:                 30\n\rStrength:          3\nTimeToBuild:   10s\nCosts:\n -  20  Wood";
			calcToolTip();
		}
		//Swordsman Level 2
		if ((buildUnitButtons.getBuildSwordsmanLevel2().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel2"))
				|| (buildUnitButtons.getBuildSwordsmanLevel2().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Swordsman Level 2\nThe Swordsman is the classic melee unit.\nHP:                 70\n\rStrength:          6\nTimeToBuild:   20s\nCosts:\n -  30  Wood\n -  20  Iron";
			calcToolTip();
		}
		//Swordsman Level 3
		if ((buildUnitButtons.getBuildSwordsmanLevel3().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Swordsman Level 3\nThe Swordsman is the classic melee unit.\nHP:                100\n\rStrength:          9\nTimeToBuild:   40s\nCosts:\n -  40  Wood\n -  50  Iron";
			calcToolTip();
		}
		//Archer Level 1
		if ((buildUnitButtons.getBuildArcherLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel1"))
				|| (buildUnitButtons.getBuildArcherLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel2"))
				|| (buildUnitButtons.getBuildArcherLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Archer Level 1\nThe Archer is a ranged unit.\nThey can occupy Towers to improve your defence.\nHP:                 20\n\rStrength:          4\nTimeToBuild:   10s\nCosts:\n -  20  Wood";
			calcToolTip();
		}
		//Archer Level 2
		if ((buildUnitButtons.getBuildArcherLevel2().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel2"))
				|| (buildUnitButtons.getBuildArcherLevel2().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Archer Level 2\nThe Archer is a ranged unit.\nThey can occupy Towers to improve your defence.\nHP:                 50\n\rStrength:          7\nTimeToBuild:   20s\nCosts:\n -  30  Wood\n -  20  Iron";
			calcToolTip();
		}
		//Archer Level 3
		if ((buildUnitButtons.getBuildArcherLevel3().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Archer Level 3\nThe Archer is a ranged unit.\nThey can occupy Towers to improve your defence.\nHP:                 70\n\rStrength:        10\nTimeToBuild:   40s\nCosts:\n -  40  Wood\n -  50  Iron";
			calcToolTip();
		}
		//Knight Level 1
		if ((buildUnitButtons.getBuildKnightLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel1"))
				|| (buildUnitButtons.getBuildKnightLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel2"))
				|| (buildUnitButtons.getBuildKnightLevel1().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Knight Level 1\nEvery strong and expensive. But in 1v1, they rock!\nHP:                 40\n\rStrength:          5\nTimeToBuild:   20s\nCosts:\n -  30  Wood\n -  30  Iron";
			calcToolTip();
		}
		//Knight Level 2
		if ((buildUnitButtons.getBuildKnightLevel2().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel2"))
				|| (buildUnitButtons.getBuildKnightLevel2().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Knight Level 2\nEvery strong and expensive. But in 1v1, they rock!\nHP:                 80\n\rStrength:          8\nTimeToBuild:   40s\nCosts:\n -  40  Wood\n -  40  Iron";
			calcToolTip();
		}
		//Knight Level 3
		if ((buildUnitButtons.getBuildKnightLevel3().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("barrackLevel3"))) {
			toolTipText = "Create Knight Level 3\nEvery strong and expensive. But in 1v1, they rock!\nHP:                120\n\rStrength:         11\nTimeToBuild:   60s\nCosts:\n -  50  Wood\n -  70  Iron";
			calcToolTip();
		}
		//Catapult
		if ((buildUnitButtons.getBuildCatapult().isMouseOver())
				&& (buildUnitButtons.getSelection().equals("forge"))) {
			toolTipText = "Create Catapult\nThis heavy Engine is very effectiv. It deals extra damage to buildings.\nHP:                200\n\rStrength:         20\nTimeToBuild:   60s\nCosts:\n - 100  Wood\n - 150  Stone\n - 150  Iron";
			calcToolTip();
		}
	}
	
	private void renderUpgradeBuildingButtons(){
		//Barrack
		if(upgradeBuildingButtons.getBuildBarrackLevel2().isMouseOver() && upgradeBuildingButtons.getSelection().equals("upgrade")){
			toolTipText = "Upgrade Barrack to Level 2\nThen you can build more powerfull Units.\nHP:               2000\nTimeToBuild:   70s\nCosts:\n - 200  Wood\n - 100  Stone";
			calcToolTip();
		}
		if((upgradeBuildingButtons.getBuildBarrackLevel3().isMouseOver()  && upgradeBuildingButtons.getSelection().equals("upgrade"))){
			toolTipText = "Upgrade Barrack to Level 3\nThen you can build more powerfull Units.\nHP:               5000\nTimeToBuild:   90s\nCosts:\n - 250  Wood\n - 250  Stone";
			calcToolTip();
		}
		if ((upgradeBuildingButtons.getUpgradeButton().isMouseOver() && upgradeBuildingButtons
				.getSelection().equals("barrackLevel1"))
				|| (upgradeBuildingButtons.getSelection().equals(
						"barrackLevel2")
						&& (upgradeBuildingButtons.getUpgradeButton()
								.isMouseOver()) || (upgradeBuildingButtons
						.getSelection().equals("barrackLevel3") && (upgradeBuildingButtons
						.getUpgradeButton().isMouseOver())))) {
			toolTipText = "Switch to 'Upgrade' Mode.";
			calcToolTip();
		}
		if ((upgradeBuildingButtons.getAbortBuildButton().isMouseOver() && upgradeBuildingButtons
				.getSelection().equals("barrackLevel1"))
				|| (upgradeBuildingButtons.getSelection().equals(
						"barrackLevel2")
						&& (upgradeBuildingButtons.getAbortBuildButton()
								.isMouseOver()) || (upgradeBuildingButtons
						.getSelection().equals("barrackLevel3") && (upgradeBuildingButtons
						.getAbortBuildButton().isMouseOver())))) {
			toolTipText = "Drop Barrack.";
			calcToolTip();
		}
		if((upgradeBuildingButtons.getAbortButton().isMouseOver() &&  upgradeBuildingButtons.getSelection().equals("upgrade"))){
			toolTipText = "Back to 'Create Unit' Mode.";
			calcToolTip();
		}
		//Tower
		if(upgradeBuildingButtons.getBuildTowerLevel2().isMouseOver() && upgradeBuildingButtons.getSelection().equals("towerLevel1")){
			toolTipText = "Upgrade Barrack to Level 2\nStronger Tower with more space for archer.\nMax: 3 Archer\nHP:               1200\nTimeToBuild:   60s\nCosts:\n - 100  Wood\n - 150  Stone";
			calcToolTip();
		}
		if(upgradeBuildingButtons.getAbortBuildButton().isMouseOver() && upgradeBuildingButtons.getSelection().equals("towerLevel1")){
			toolTipText = "Drop this Tower.";
			calcToolTip();
		}
		if(upgradeBuildingButtons.getBuildTowerLevel3().isMouseOver() && upgradeBuildingButtons.getSelection().equals("towerLevel2")){
			toolTipText = "Upgrade Barrack to Level 3\nStronger Tower with more space for archer.\nMax: 4 Archer\nHP:               2000\nTimeToBuild:   80s\nCosts:\n - 120  Wood\n - 200  Stone";
			calcToolTip();
		}
		if(upgradeBuildingButtons.getAbortBuildButton().isMouseOver() && upgradeBuildingButtons.getSelection().equals("towerLevel2")){
			toolTipText = "Drop this Tower.";
			calcToolTip();
		}
		if(upgradeBuildingButtons.getAbortBuildButton().isMouseOver() && upgradeBuildingButtons.getSelection().equals("towerLevel3")){
			toolTipText = "Drop this Tower.";
			calcToolTip();
		}
		if(upgradeBuildingButtons.getAbortBuildButton().isMouseOver() && upgradeBuildingButtons.getSelection().equals("farm")){
			toolTipText = "Drop a Farm.";
			calcToolTip();
		}
		//Stronghold
		// TODO!
		// Upgrade to Lvl 2,3
		if (upgradeBuildingButtons.getBuildStrongholdLevel2().isMouseOver() && upgradeBuildingButtons
				.getSelection().equals("strongholdLevel1")) {
			toolTipText = "Upgrade Stronghold to Level 2\nAllows you to build Barrack Level 2, Tower and Forge.\nHP:               2000\nTimeToBuild:   70s\nCosts:\n - 200  Wood\n - 100  Stone";
			calcToolTip();
		}
		if (upgradeBuildingButtons.getBuildStrongholdLevel3().isMouseOver() && upgradeBuildingButtons
				.getSelection().equals("strongholdLevel2")) {
			toolTipText = "Upgrade Stronghold to Level 3\nAllows you to build Barrack and Tower Level 3.\nHP:               5000\nTimeToBuild:   90s\nCosts:\n - 250  Wood\n - 250  Stone";
			calcToolTip();
		}
		//Drop Stronghold
		if ((upgradeBuildingButtons.getAbortBuildButton().isMouseOver() && upgradeBuildingButtons
				.getSelection().equals("strongholdLevel1"))
				|| (upgradeBuildingButtons.getSelection().equals(
						"strongholdLevel2")
						&& (upgradeBuildingButtons.getAbortBuildButton()
								.isMouseOver()) || (upgradeBuildingButtons
						.getSelection().equals("strongholdLevel3") && (upgradeBuildingButtons
						.getAbortBuildButton().isMouseOver())))) {
			toolTipText = "Drop Stronghold.";
			calcToolTip();
		}
	}
	
	private void renderCommandUnitButtons(){
		//Peon
		if(commandUnitButtons.getSelection().equals("peon") && commandUnitButtons.getBuildButton().isMouseOver()){
			toolTipText = "Build Buildings.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("peon") && commandUnitButtons.getRepairBuilding().isMouseOver()){
			toolTipText = "Repair Buildings.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("peon") && commandUnitButtons.getGatherResource().isMouseOver()){
			toolTipText = "Gather Resource.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("peon") && commandUnitButtons.getGatherResource().isMouseOver()){
			toolTipText = "Gather Resource.";
			calcToolTip();
		}
		if (commandUnitButtons.getAbortButton().isMouseOver()
				&& commandUnitButtons.getSelection().equals("build")
				|| commandUnitButtons.getAbortButton().isMouseOver()
				&& commandUnitButtons.getSelection().equals("repair")
				|| commandUnitButtons.getAbortButton().isMouseOver()
				&& commandUnitButtons.getSelection().equals("gather")) {
			toolTipText = "Back.";
			calcToolTip();
		}
		//Peon - build
		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpBarrackLevel1().isMouseOver() && commandUnitButtons.getBarrackBuildUp()>0){
			toolTipText = "Build Barrack Level 1.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpFarm().isMouseOver() && commandUnitButtons.getFarmBuildUp()>0){
			toolTipText = "Build Farm.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpForge().isMouseOver() && commandUnitButtons.getForgeBuildUp()>0){
			toolTipText = "Build Forge.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpStrongholdLevel1().isMouseOver() && commandUnitButtons.getStrongholdBuildUp()>0){
			toolTipText = "Build Stronghold.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpTowerLevel1().isMouseOver() && commandUnitButtons.getTowerBuildUp()>0){
			toolTipText = "Build Tower Level 1.";
			calcToolTip();
		}
		//Peon - repair
		if(commandUnitButtons.getSelection().equals("repair") && commandUnitButtons.getRepairBarrack().isMouseOver()){
			toolTipText = "Repair Barrack.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("repair") && commandUnitButtons.getRepairFarm().isMouseOver()){
			toolTipText = "Repair Farm.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("repair") && commandUnitButtons.getRepairForge().isMouseOver()){
			toolTipText = "Repair Forge.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("repair") && commandUnitButtons.getRepairStronghold().isMouseOver()){
			toolTipText = "Repair Stronghold.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("repair") && commandUnitButtons.getRepairTower().isMouseOver()){
			toolTipText = "Repair Tower.";
			calcToolTip();
		}
		//Peon - gather
		if(commandUnitButtons.getSelection().equals("gather") && commandUnitButtons.getGatherWood().isMouseOver()){
			toolTipText = "Gather Wood.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("gather") && commandUnitButtons.getGatherStone().isMouseOver()){
			toolTipText = "Gather Stone.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("gather") && commandUnitButtons.getGatherIron().isMouseOver()){
			toolTipText = "Gather Iron.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("archer") && commandUnitButtons.getMoveInTower1().isMouseOver()){
			toolTipText = "Move Archer in the Level 1 Tower.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("archer") && commandUnitButtons.getMoveInTower2().isMouseOver()){
			toolTipText = "Move Archer in the Level 2 Tower.";
			calcToolTip();
		}
		if(commandUnitButtons.getSelection().equals("archer") && commandUnitButtons.getMoveInTower3().isMouseOver()){
			toolTipText = "Move Archer in the Level 3 Tower.";
			calcToolTip();
		}
		if (commandUnitButtons.getSelection().equals("towerLevel1")
				&& commandUnitButtons.getMoveOutTower().isMouseOver()
				|| commandUnitButtons.getSelection().equals("towerLevel2")
				&& commandUnitButtons.getMoveOutTower().isMouseOver()
				|| commandUnitButtons.getSelection().equals("towerLevel3")
				&& commandUnitButtons.getMoveOutTower().isMouseOver()) {
			toolTipText = "Move Archer out of Tower.";
			calcToolTip();
		}
	}
	
	private void renderPlaceBuildingButtons(){
//		System.out.println(selection);
//		//TODO selection = ""
//		if(placeBuildingButtons.getBuildBarrack().isMouseOver() && selection.equals("")){
//			System.out.println("SHOW ME!!");
//		}
		
//		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpBarrackLevel1().isMouseOver()){
//			toolTipText = "Build Barrack Level 1\nCreate Units.\n- Swordman\n - Archer\n - Knight\nHP:               500\nTimeToBuild:   30s\nCosts:\n - 120  Wood";
//			calcToolTip();
//		}
//		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpFarm().isMouseOver()){
//			toolTipText = "Build Farm\nIncrease the population limit by 10.\nMax: 70 Units.\nHP:               400\nTimeToBuild:   20s\nCosts:\n - 80  Wood";
//			calcToolTip();
//		}
//		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpForge().isMouseOver()){
//			toolTipText = "Build Forge\nCreates Catapults.\n\nHP:               800\nTimeToBuild:   90s\nCosts:\n - 200  Wood\n - 300  Iron";
//			calcToolTip();
//		}
//		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpStrongholdLevel1().isMouseOver()){
//			toolTipText = "Build Stronghold.\nCreates Peons.\nHP:               1000\nTimeToBuild:   50s\nCosts:\n - 200  Wood";
//			calcToolTip();
//		}
//		if(commandUnitButtons.getSelection().equals("build") && commandUnitButtons.getBuildUpTowerLevel1().isMouseOver()){
//			toolTipText = "Build Tower Level 1\nTower with space for archer.\nMax: 2 Archer\nHP:               800\nTimeToBuild:   40s\nCosts:\n - 80  Wood\n - 100  Stone";
//			calcToolTip();
//		}
	}
}
