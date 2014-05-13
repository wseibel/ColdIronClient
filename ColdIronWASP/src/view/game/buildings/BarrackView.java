package view.game.buildings;

import java.util.Iterator;

import model.game.Barrack;
import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Farm;
import model.game.Forge;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Tower;
import model.game.User;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import view.game.Fonts;
import view.game.Portrait;

public class BarrackView implements ComponentListener{
	
	private CIClient ciClient;
	private User currentUser;
	private Sector currentSector;
	private String playersColor;
	private String selection = "";
	private boolean selectbarrackPressed = false;
	private int constrAbleBarrack;
	private Image barrack;
	private Image barrackPortrait;
	private Image level1OfBuildings;
	private Image level2OfBuildings;
	private Image level3OfBuildings;
	private Image repairIcon;
	private GameContainer container;
	private MouseOverArea chooseBarrack;
	private MouseOverArea chooseRepairBarrack;
	private Portrait portrait;
	private float maxWidth;
	private Rectangle backgroundBar1;
	private Rectangle hpBar1;
	private int maxHp = 0;
	private int currentHp = 0;
	private int maxHealth1 = 0;
	private int currentHP1 = 0;
	private int amountOfBarrackLv1 = 0;
	private Rectangle backgroundBar2;
	private Rectangle hpBar2;
	private int maxHealth2 = 0;
	private int currentHP2 = 0;
	private int amountOfBarrackLv2 = 0;
	private Rectangle backgroundBar3;
	private Rectangle hpBar3;
	private int maxHealth3 = 0;
	private int currentHP3 = 0;
	private int amountOfBarrackLv3 = 0;
	private String numberOfBarrackLv1 = "";
	private String numberOfBarrackLv2 = "";
	private String numberOfBarrackLv3 = "";
	private boolean barrackAvailable = false;
	private boolean repairBarrack = false;
	
	private Fonts fonts;
	private Font font;
	
	private double buildProgressToBarrackLv2;
	private double buildProgressToBarrackLv3;
	private double intervalUpgrToBarrackLv2;
	private double intervalUpgrToBarrackLv3;
	private double widthOfBar = 170;
	private boolean barrackLv1isUpgrading = false;
	private boolean barrackLv2isUpgrading = false;

	private double barrackLv1CreationProgress = 0.00;
	private double intervalOfBarrackLv1;
	private boolean barrackLv1IsCreating = false;

	private double barrackLv2CreationProgress = 0.00;
	private double intervalOfBarrackLv2;
	private boolean barrackLv2IsCreating = false;

	private double barrackLv3CreationProgress = 0.00;
	private double intervalOfBarrackLv3;
	private boolean barrackLv3IsCreating = false;

	public BarrackView(CIClient ciClient, Sector current, GameContainer container, String playersColor, User currentUser) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.currentUser = currentUser;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);

			if(barrackAvailable){
				maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1765));
				chooseBarrack.render(container, g);
				chooseBarrack.setNormalColor(new Color(1,1,1,0.9f));
				if(amountOfBarrackLv1 > 0){
					backgroundBar1.setSize(maxWidth, (int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					g.setColor(Color.gray);
					g.fill(backgroundBar1);
					if(currentHP1 <= (maxHealth1 / 4)) {
						g.setColor(Color.red);
					} else if(currentHP1 <= (maxHealth1 / 2)) {
						g.setColor(Color.yellow);
					} else {
						g.setColor(Color.green);
					}
					hpBar1.setHeight((int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					g.fill(hpBar1);
					
					//bar for Upgrade
					g.setColor(Color.gray);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1006)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1750)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					
					if(barrackLv1isUpgrading){
						g.setColor(Color.blue);
						g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1006)),
								(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalUpgrToBarrackLv2))), 
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
						
						if(intervalUpgrToBarrackLv2 == widthOfBar){
							barrackLv1isUpgrading = false;
						}
						
						
					}					
					
					//bar for barrackLv1 creating
					g.setColor(Color.gray);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1011)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1750)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					
					
					if(barrackLv1IsCreating){
						g.setColor(Color.orange);
						g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1011)),
								(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalOfBarrackLv1))), 
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
						
						if(intervalOfBarrackLv1 == widthOfBar){
							barrackLv1IsCreating = false;
						}
					}
					
					//images and the amount of the level from barrack
					g.setColor(Color.white);
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.3f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
					g.drawString(numberOfBarrackLv1.valueOf(amountOfBarrackLv1),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1240)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
				}
				
				
				//Barrack LVL 2 =========================================================
				if(amountOfBarrackLv2 > 0){
					backgroundBar2.setSize(maxWidth, (int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					g.setColor(Color.gray);
					g.fill(backgroundBar2);
					if(currentHP2 <= (maxHealth2 / 4)) {
						g.setColor(Color.red);
					} else if(currentHP2 <= (maxHealth2 / 2)) {
						g.setColor(Color.yellow);
					} else {
						g.setColor(Color.green);
					}
					hpBar2.setHeight((int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					g.fill(hpBar2);
					
					//bar for Upgrade
					g.setColor(Color.gray);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*980)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1750)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					
					if(barrackLv2isUpgrading){
						g.setColor(Color.blue);
						g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*980)),
								(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalUpgrToBarrackLv3))), 
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
						if(intervalUpgrToBarrackLv3 == widthOfBar){
							barrackLv2isUpgrading = false;
						}
					}
					

					//bar for barrackLv2 creating
					g.setColor(Color.gray);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*985)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1750)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					
					
					if(barrackLv2IsCreating){
						g.setColor(Color.orange);
						g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*985)),
								(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalOfBarrackLv2))), 
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
						if(intervalOfBarrackLv2 == widthOfBar){
							barrackLv2IsCreating = false;
						}
					}
					
					//images and the amount of the level from barrack
					g.setColor(Color.white);
					g.drawImage(level2OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.3f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*995)));
					g.drawString(numberOfBarrackLv2.valueOf(amountOfBarrackLv2),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1240)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*995)));
				
					//BARRACK LVL 3 ================================================
				}if(amountOfBarrackLv3 > 0){
					backgroundBar3.setSize(maxWidth, (int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					g.setColor(Color.gray);
					g.fill(backgroundBar3);
					if(currentHP3 <= (maxHealth3 / 4)) {
						g.setColor(Color.red);
					} else if(currentHP3 <= (maxHealth3 / 2)) {
						g.setColor(Color.yellow);
					} else {
						g.setColor(Color.green);
					}
					hpBar3.setHeight((int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					g.fill(hpBar3);
					
					
					//bar for barrackLv3 creating
					g.setColor(Color.gray);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*955)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1750)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					
					
					if(barrackLv3IsCreating){
						g.setColor(Color.orange);
						g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*955)),
								(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalOfBarrackLv3))), 
								(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
						if(intervalOfBarrackLv3 == widthOfBar){
							barrackLv3IsCreating = false;
						}
					}
					
					
					//images and the amount of the level from barrack
					g.setColor(Color.white);
					g.drawImage(level3OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.3f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*970)));
					g.drawString(numberOfBarrackLv3.valueOf(amountOfBarrackLv3),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1240)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*970)));
				}
				if(repairBarrack){
					chooseRepairBarrack.render(container, g);
					chooseRepairBarrack.setNormalColor(new Color(1,1,1,0.8f));
				}
			}
	}
	
	public void init() throws SlickException {
		
		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		barrack = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/Barrack.png");
		
		repairIcon = new Image("res/Ingame/repairIcon.png");
		
		level1OfBuildings = new Image("res/Ingame/Level1.png");
		level2OfBuildings = new Image("res/Ingame/Level2.png");
		level3OfBuildings = new Image("res/Ingame/Level3.png");
		
		barrackPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Barrack.png");
		//mouseoverareas for units
		chooseBarrack = new MouseOverArea(container, barrack.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1400)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*950)),this);
		
		chooseRepairBarrack = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1400)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*923)),this);
		
		backgroundBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(container.getHeight()-((((float) container.getHeight())/1080)*1000)), 0, 0);
		hpBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(container.getHeight()-((((float) container.getHeight())/1080)*1000)), 0, 0);
		
		backgroundBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(container.getHeight()-((((float) container.getHeight())/1080)*975)), 0, 0);
		hpBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(container.getHeight()-((((float) container.getHeight())/1080)*975)), 0, 0);
		
		backgroundBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(container.getHeight()-((((float) container.getHeight())/1080)*950)), 0, 0);
		hpBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(container.getHeight()-((((float) container.getHeight())/1080)*950)), 0, 0);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutBarrack();
	}
	
	public void getInfoAboutBarrack(){
		amountOfBarrackLv1 = 0;
		currentHP1 = 0;
		amountOfBarrackLv2 = 0;
		currentHP2 = 0;
		amountOfBarrackLv3 = 0;
		currentHP3 = 0;
		constrAbleBarrack = 0;
		barrackAvailable = false;
		repairBarrack = false;
		
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		
		while(buildIter.hasNext()){
			Building build = buildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Barrack") && build.getLevel() == 1){
				barrackAvailable = true;
				amountOfBarrackLv1++;
				currentHP1 += build.getHp();
				maxHealth1 = amountOfBarrackLv1 * build.getMaxHP();
				
				if (build.getUnitTypeInCreation() != null) { //enemy builds won`t have that
					//show the actual interval of creating units in barrackLv1
					if(build.getUnitTypeInCreation().equals("ARCHER")
							|| build.getUnitTypeInCreation().equals("SWORDSMAN")
							|| build.getUnitTypeInCreation().equals("KNIGHT")){

						barrackLv1CreationProgress = build.getUnitCreationProgress();

						intervalOfBarrackLv1 = barrackLv1CreationProgress*widthOfBar;
						barrackLv1IsCreating = true;
					}

					//if barracklv1 is upgrading, get the actual buildprogress
					//and count the actual interval for the upgrade bar
					if(build.isUpgrading()){
						//stronghold is upgrading: true
						barrackLv1isUpgrading = build.isUpgrading();
						buildProgressToBarrackLv2 = build.getBuildProgress();
						intervalUpgrToBarrackLv2 = buildProgressToBarrackLv2*widthOfBar;
					}
				}
				
				//All thing for Barrack Lvl2
			}else if(id.equals("Barrack") && build.getLevel() == 2){
				barrackAvailable = true;
				amountOfBarrackLv2++;
				currentHP2 += build.getHp();
				maxHealth2 = amountOfBarrackLv2 * build.getMaxHP();
				
				if (build.getUnitTypeInCreation() != null) { //enemy builds won`t have that
					//show the actual interval of creating units in barrackLv2
					if(build.getUnitTypeInCreation().equals("ARCHER")
							|| build.getUnitTypeInCreation().equals("SWORDSMAN")
							|| build.getUnitTypeInCreation().equals("KNIGHT")){

						barrackLv2CreationProgress = build.getUnitCreationProgress();

						intervalOfBarrackLv2 = barrackLv2CreationProgress*widthOfBar;
						barrackLv2IsCreating = true;
					}

					//if barracklv2 is upgrading, get the actual buildprogress
					//and count the actual interval for the upgrade bar
					if(build.isUpgrading()){
						//stronghold is upgrading: true
						barrackLv2isUpgrading = build.isUpgrading();
						buildProgressToBarrackLv3 = build.getBuildProgress();
						intervalUpgrToBarrackLv3 = buildProgressToBarrackLv3*widthOfBar;
					}
				}
				
			}else if(id.equals("Barrack") && build.getLevel() == 3){
				barrackAvailable = true;
				amountOfBarrackLv3++;
				currentHP3 += build.getHp();
				maxHealth3 = amountOfBarrackLv3 * build.getMaxHP();
				
				if (build.getUnitTypeInCreation() != null) { //enemy builds won`t have that
					//show the actual interval of creating units in barrackLv3
					if(build.getUnitTypeInCreation().equals("ARCHER")
							|| build.getUnitTypeInCreation().equals("SWORDSMAN")
							|| build.getUnitTypeInCreation().equals("KNIGHT")){

						barrackLv3CreationProgress = build.getUnitCreationProgress();

						intervalOfBarrackLv3 = barrackLv3CreationProgress*widthOfBar;
						barrackLv3IsCreating = true;
					}
				}
			}
		}
		hpBar1.setWidth(maxWidth * currentHP1 / maxHealth1);
		hpBar2.setWidth(maxWidth * currentHP2 / maxHealth2);
		hpBar3.setWidth(maxWidth * currentHP3 / maxHealth3);
		
		maxHp = maxHealth1+maxHealth2+maxHealth3;
		currentHp = currentHP1+currentHP2+currentHP3;
		
		//is barrack damaged?
		Iterator<Building> repairBuildIter = currentSector.iteratorOfRepairableBuilding();
		
		while(repairBuildIter.hasNext()){
			Building build = repairBuildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Barrack") && build.getLevel() == 1  && build.getHp() < build.getMaxHP()){
				repairBarrack = true;
			}else if(id.equals("Barrack") && build.getLevel() == 2  && build.getHp() < build.getMaxHP()){
				repairBarrack = true;
			}else if(id.equals("Barrack") && build.getLevel() == 3  && build.getHp() < build.getMaxHP()){
				repairBarrack = true;
			}
		
		}
		
		
		//if barrack can be up builded, show me the amount of this buildings
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();
		
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			if(constr instanceof Barrack && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				constrAbleBarrack++;
			}
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseBarrack){
			portrait = new Portrait(barrackPortrait, container);
			
			if(amountOfBarrackLv3 != 0){
				selection ="barrackLevel3";
			}else if(amountOfBarrackLv2 != 0){
				selection ="barrackLevel2";
			}else if(amountOfBarrackLv1 != 0){
				selection ="barrackLevel1";
			}
			
			selectbarrackPressed = true;
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectbarrackPressed() {
		return selectbarrackPressed;
	}

	public void setSelectbarrackPressed(boolean selectbarrackPressed) {
		this.selectbarrackPressed = selectbarrackPressed;
	}

	public MouseOverArea getChooseBarrack() {
		return chooseBarrack;
	}

	public void setChooseBarrack(MouseOverArea chooseBarrack) {
		this.chooseBarrack = chooseBarrack;
	}
	
	public void clicked(int button, int x, int y, int clickCount, CommandHelper ch) {
		// only react on rigth-clicks when button == 1
		if (button == 1) {
			
			//build an building up with rightclick
			if(constrAbleBarrack > 0 && x >= chooseBarrack.getX() &&
					x <=  (chooseBarrack.getX() + chooseBarrack.getWidth()) &&
					y >= chooseBarrack.getY() &&
					y <=  (chooseBarrack.getY() + chooseBarrack.getHeight())){
					
				ch.buildUpBuilding("Barrack");
			}
			
			if (repairBarrack &&
					x >= chooseRepairBarrack.getX() &&
					x <=  (chooseRepairBarrack.getX() + chooseRepairBarrack.getWidth()) &&
					y >= chooseRepairBarrack.getY() &&
					y <=  (chooseRepairBarrack.getY() + chooseRepairBarrack.getHeight())) {
				// repair of Barracks got clicked but its unclear which level is meant -> 
				// first get a reparable building of the highest possible level 
				boolean foundOne = false;
				int maxLevel = 0;
				Building build;
				Iterator<Building> repairBuildIter = currentSector.iteratorOfRepairableBuilding();
				while(repairBuildIter.hasNext() && !foundOne){
					build = repairBuildIter.next();
					if(build.getClass().toString().contains("Barrack")){
						if (build.getLevel() == 3) {
							maxLevel = 3;
							foundOne = true;
						} else {
							if (build.getLevel() == 2) {
								maxLevel = 2;
								foundOne = true;
							} else {
								if (build.getLevel() == 1) {
									maxLevel = 1;
									foundOne = true;
								}
							}
						}
					
					}
				}
				if (foundOne) {
					// send peons for repair
					ch.repairBuilding("Barrack", maxLevel);
				} else {
					ciClient.getChainMaster().setChatString("ERROR_MESSAGE:ERROR:you have no barracks that need repair");
				}
				
			}
		}	
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

}
