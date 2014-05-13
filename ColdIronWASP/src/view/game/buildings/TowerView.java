package view.game.buildings;

import java.util.Iterator;

import model.game.Archer;
import model.game.Building;
import model.game.CommandHelper;
import model.game.Forge;
import model.game.Tower;
import model.game.CIClient;
import model.game.Sector;
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

public class TowerView implements ComponentListener{
	
	private CIClient ciClient;
	private User currentUser;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	boolean selectTowerPressed;
	private Image towerLv1;
	private Image towerLv2;
	private Image towerLv3;
	private Image towerPortrait;
	private Image repairIcon;
	private Image archerAtTowerIcon;
	private GameContainer container;
	private MouseOverArea chooseTowerLv1;
	private MouseOverArea chooseTowerLv2;
	private MouseOverArea chooseTowerLv3;
	private MouseOverArea chooseRepairTowerLv1;
	private MouseOverArea chooseRepairTowerLv2;
	private MouseOverArea chooseRepairTowerLv3;
	private Portrait portrait;
	private int constrAbleTower;
	private float maxWidth;
	private Rectangle backgroundBar1;
	private Rectangle hpBar1;
	private int maxHp = 0;
	private int currentHp = 0;
	private int maxHealth1 = 0;
	private int currentHP1 = 0;
	private int amountOfTowerLv1 = 0;
	private Rectangle backgroundBar2;
	private Rectangle hpBar2;
	private int maxHealth2 = 0;
	private int currentHP2 = 0;
	private int amountOfTowerLv2 = 0;
	private Rectangle backgroundBar3;
	private Rectangle hpBar3;
	private int maxHealth3 = 0;
	private int currentHP3 = 0;
	private int amountOfTowerLv3 = 0;
	private int archerInTowerLv1 = 0;
	private int archerInTowerLv2 = 0;
	private int archerInTowerLv3 = 0;
	private String numberOfTowers = "";
	private String numberOfArchersInTowers = "";
	private boolean towerLv1Available = false;
	private boolean towerLv2Available = false;
	private boolean towerLv3Available = false;
	private boolean repairTowerLv1 = false;
	private boolean repairTowerLv2 = false;
	private boolean repairTowerLv3 = false;
	private boolean archerAtTowerLv1 = false;
	private boolean archerAtTowerLv2 = false;
	private boolean archerAtTowerLv3 = false;
	private Fonts fonts;
	private Font font;
	
	private double buildProgressToTowerLv2;
	private double buildProgressToTowerLv3;
	private double intervalUpgrToTowerLv2;
	private double intervalUpgrToTowerLv3;
	private double widthOfBar = 90;
	private boolean towerLv1isUpgrading = false;
	private boolean towerLv2isUpgrading = false;


	public TowerView(CIClient ciClient, Sector current, GameContainer container, String playersColor, User currentUser) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.currentUser = currentUser;
	}

	public void render(Graphics g) throws SlickException {
			//if tower  Lv1 is available, so render the mouseoverarea and show the amount
			//of the towers
		
		g.setFont(font);
		maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1840));
		//TOWER LEVEL1
			if(towerLv1Available){
				chooseTowerLv1.render(container, g);
				chooseTowerLv1.setNormalColor(new Color(1,1,1,0.9f));
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
				g.setColor(Color.white);
				g.drawString(numberOfTowers.valueOf(amountOfTowerLv1), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*770)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*570)));
				
				if(archerAtTowerLv1){
					g.drawImage(archerAtTowerIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*740)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*570)));
					g.setColor(Color.white);
					g.drawString(numberOfArchersInTowers.valueOf(archerInTowerLv1), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*700)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*570)));
				}
				if(repairTowerLv1){
					chooseRepairTowerLv1.render(container, g);
					chooseRepairTowerLv1.setNormalColor(new Color(1,1,1,0.8f));
				}
				//upgrade bar for towerlv1
				g.setColor(Color.gray);
				g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*760)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*580)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1830)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
				
				if(towerLv1isUpgrading){
					g.setColor(Color.blue);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*760)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*580)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalUpgrToTowerLv2))), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					if(intervalUpgrToTowerLv2 == widthOfBar){
						towerLv1isUpgrading = false;
					}
				}
				
				
			//TOWER LEVEL2	
			}if(towerLv2Available){
				chooseTowerLv2.render(container, g);
				chooseTowerLv2.setNormalColor(new Color(1,1,1,0.9f));
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
				g.setColor(Color.white);
				g.drawString(numberOfTowers.valueOf(amountOfTowerLv2), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1240)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*500)));
				if(archerAtTowerLv2){
					g.drawImage(archerAtTowerIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1210)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*500)));
					g.setColor(Color.white);
					g.drawString(numberOfArchersInTowers.valueOf(archerInTowerLv2), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1170)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*500)));
				}
				if(repairTowerLv2){
					chooseRepairTowerLv2.render(container, g);
					chooseRepairTowerLv2.setNormalColor(new Color(1,1,1,0.8f));
				}
				//if tower is upgrading, start the new thread, to build up the bar
				//upgrade bar for towerlv2
				g.setColor(Color.gray);
				g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1230)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*510)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1830)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
				if(towerLv2isUpgrading){
					g.setColor(Color.blue);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1230)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*510)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalUpgrToTowerLv3))), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					
					if(intervalUpgrToTowerLv3 == widthOfBar){
						towerLv2isUpgrading = false;
					}
				}
				
				
			//TOWER LEVEL3
			}if(towerLv3Available){
				chooseTowerLv3.render(container, g);
				chooseTowerLv3.setNormalColor(new Color(1,1,1,0.9f));
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
				g.setColor(Color.white);
				g.drawString(numberOfTowers.valueOf(amountOfTowerLv3), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*950)));
				if(archerAtTowerLv3){
					g.drawImage(archerAtTowerIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*970)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*950)));
					g.setColor(Color.white);
					g.drawString(numberOfArchersInTowers.valueOf(archerInTowerLv3), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*940)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*950)));
				}
				if(repairTowerLv3){
					chooseRepairTowerLv3.render(container, g);
					chooseRepairTowerLv3.setNormalColor(new Color(1,1,1,0.8f));
				}
			}
	}
	
	public void init() throws SlickException {

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();

		towerLv1 = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/TowerLv1.png");
		towerLv2 = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/TowerLv2.png");
		towerLv3 = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/TowerLv3.png");
		
		repairIcon = new Image("res/Ingame/repairIcon.png");
		
		archerAtTowerIcon = new Image("res/Ingame/archerIcon.png");
		
		towerPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Tower.png");
		//mouseoverareas for units
		chooseTowerLv1 = new MouseOverArea(container, towerLv1.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*770)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*560)),this);
	
		chooseTowerLv2 = new MouseOverArea(container, towerLv2.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1240)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*490)),this);
		
		chooseTowerLv3 = new MouseOverArea(container, towerLv3.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*940)),this);
		
		chooseRepairTowerLv1 = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*770)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*545)),this);
		
		chooseRepairTowerLv2 = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1240)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*475)),this);
		
		chooseRepairTowerLv3 = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*925)),this);
		
		backgroundBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*760)),
				(container.getHeight()-((((float) container.getHeight())/1080)*575)), 0, 5);
		hpBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*760)),
				(container.getHeight()-((((float) container.getHeight())/1080)*575)), 0, 5);

		backgroundBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1230)),
				(container.getHeight()-((((float) container.getHeight())/1080)*504)), 0, 5);
		hpBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1230)),
				(container.getHeight()-((((float) container.getHeight())/1080)*504)), 0, 5);

		backgroundBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*990)),
				(container.getHeight()-((((float) container.getHeight())/1080)*955)), 0, 5);
		hpBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*990)),
				(container.getHeight()-((((float) container.getHeight())/1080)*955)), 0, 5);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutTowers();
		
	}
	
	public void getInfoAboutTowers(){
		amountOfTowerLv1 = 0;
		amountOfTowerLv2 = 0;
		amountOfTowerLv3 = 0;
		currentHP1 = 0;
		currentHP2 = 0;
		currentHP3 = 0;
		archerInTowerLv1 = 0;
		archerInTowerLv2 = 0;
		archerInTowerLv3 = 0;
		constrAbleTower = 0;
		repairTowerLv1 = false;
		repairTowerLv2 = false;
		repairTowerLv3= false;
		towerLv1Available = false;
		towerLv2Available = false;
		towerLv3Available = false;
		archerAtTowerLv1 = false;
		archerAtTowerLv2 = false;
		archerAtTowerLv3 = false;
		
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		
		while(buildIter.hasNext()){
			Building build = buildIter.next();
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Tower") && build.getLevel() == 1){
				towerLv1Available = true;
				amountOfTowerLv1++;
				currentHP1 += build.getHp();
				maxHealth1 = amountOfTowerLv1 * build.getMaxHP();

				// if towerlv1 is upgrading, get the actual buildprogress
				// and count the actual interval for the upgrade bar
				if (build.isUpgrading()) {
					towerLv1isUpgrading = build.isUpgrading();
					buildProgressToTowerLv2 = build.getBuildProgress();
					intervalUpgrToTowerLv2 = buildProgressToTowerLv2
							* widthOfBar;

				}
				//count archer at towers
				//run the iterator and sum the archer at towerLv1
				Iterator<Archer> archer = ((Tower) build).iteratorOfArcher();
				while(archer.hasNext()){
					archer.next();
					archerAtTowerLv1 = true;
					archerInTowerLv1++;
				}
				
				
			}else if(id.equals("Tower") && build.getLevel() == 2){
				towerLv2Available = true;
				amountOfTowerLv2++;
				currentHP2 += build.getHp();
				maxHealth2 = amountOfTowerLv2 * build.getMaxHP();
				
				// if towerlv2 is upgrading, get the actual buildprogress
				// and count the actual interval for the upgrade bar
				if (build.isUpgrading()) {
					towerLv2isUpgrading = build.isUpgrading();
					buildProgressToTowerLv3 = build.getBuildProgress();
					intervalUpgrToTowerLv3 = buildProgressToTowerLv3
							* widthOfBar;
				}
				
				//count archer at towers
				//run the iterator and sum the archer at towerLv2
				Iterator<Archer> archer = ((Tower) build).iteratorOfArcher();
				while(archer.hasNext()){
					archer.next();
					archerAtTowerLv2 = true;
					archerInTowerLv2++;
				}
			}else if(id.equals("Tower") && build.getLevel() == 3){
				towerLv3Available = true;
				amountOfTowerLv3++;
				currentHP3 += build.getHp();
				maxHealth3 = amountOfTowerLv3 * build.getMaxHP();
				//count archer at towers
				//run the iterator and sum the archer at towerLv3
				Iterator<Archer> archer = ((Tower) build).iteratorOfArcher();
				while(archer.hasNext()){
					archer.next();
					archerAtTowerLv3 = true;
					archerInTowerLv3++;
				}
			}
		
		}
		hpBar1.setWidth(maxWidth * currentHP1 / maxHealth1);
		hpBar2.setWidth(maxWidth * currentHP2 / maxHealth2);
		hpBar3.setWidth(maxWidth * currentHP3 / maxHealth3);
		
		//is tower damaged?
		Iterator<Building> repairBuildIter = currentSector.iteratorOfRepairableBuilding();
		
		while(repairBuildIter.hasNext()){
			Building build = repairBuildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Tower") && build.getLevel() == 1  && build.getHp() < build.getMaxHP()){
				repairTowerLv1 = true;
			}else if(id.equals("Tower") && build.getLevel() == 2  && build.getHp() < build.getMaxHP()){
				repairTowerLv2 = true;
			}else if(id.equals("Tower") && build.getLevel() == 3  && build.getHp() < build.getMaxHP()){
				repairTowerLv3 = true;
			}
		
		}
		
		//if tower can be up builded, show me the amount of this buildings
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();
		
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			if(constr instanceof Tower && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				constrAbleTower++;
			}
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseTowerLv1){
			portrait = new Portrait(towerPortrait, container);
			maxHp = maxHealth1;
			currentHp = currentHP1;
			selection = "towerLevel1";
			selectTowerPressed = true;
		}else if(source == chooseTowerLv2){
			portrait = new Portrait(towerPortrait, container);
			maxHp = maxHealth2;
			currentHp = currentHP2;
			selection = "towerLevel2";
			selectTowerPressed = true;
		}else if(source == chooseTowerLv3){
			portrait = new Portrait(towerPortrait, container);
			maxHp = maxHealth3;
			currentHp = currentHP3;
			selection = "towerLevel3";
			selectTowerPressed = true;
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectTowerPressed() {
		return selectTowerPressed;
	}

	public void setSelectTowerPressed(boolean selectTowerPressed) {
		this.selectTowerPressed = selectTowerPressed;
	}

	public MouseOverArea getChooseTowerLv1() {
		return chooseTowerLv1;
	}

	public void setChooseTowerLv1(MouseOverArea chooseTowerLv1) {
		this.chooseTowerLv1 = chooseTowerLv1;
	}
	
	public MouseOverArea getChooseTowerLv2() {
		return chooseTowerLv2;
	}

	public void setChooseTowerLv2(MouseOverArea chooseTowerLv2) {
		this.chooseTowerLv2 = chooseTowerLv2;
	}

	public MouseOverArea getChooseTowerLv3() {
		return chooseTowerLv3;
	}

	public void setChooseTowerLv3(MouseOverArea chooseTowerLv3) {
		this.chooseTowerLv3 = chooseTowerLv3;
	}

	public void clicked(int button, int x, int y, int clickCount, CommandHelper ch) {
		// only react on rigth-clicks when button == 1
		if (button == 1) {
			
			//build an building up with rightclick
			if(constrAbleTower > 0 && x >= chooseTowerLv1.getX() &&
					x <=  (chooseTowerLv1.getX() + chooseTowerLv1.getWidth()) &&
					y >= chooseTowerLv1.getY() &&
					y <=  (chooseTowerLv1.getY() + chooseTowerLv1.getHeight())){
					
				ch.buildUpBuilding("Tower");
			}
			
			// tricky: a repair-button appears within the chooseTower-button -> first 
			// check if repair could have been meant (because it was displayed) or
			// assume, that the area was presented and therefore meant as chooseTower
			if (repairTowerLv1 &&
					x >= chooseRepairTowerLv1.getX() &&
					x <=  (chooseRepairTowerLv1.getX() + chooseRepairTowerLv1.getWidth()) &&
					y >= chooseRepairTowerLv1.getY() &&
					y <=  (chooseRepairTowerLv1.getY() + chooseRepairTowerLv1.getHeight())) {
				// repair of Towers of level 1 got clicked -> send Peons to repair one	
				ch.repairBuilding("Tower", 1);
			} else  {
				if (repairTowerLv2 &&
						x >= chooseRepairTowerLv2.getX() &&
						x <=  (chooseRepairTowerLv2.getX() + chooseRepairTowerLv2.getWidth()) &&
						y >= chooseRepairTowerLv2.getY() &&
						y <=  (chooseRepairTowerLv2.getY() + chooseRepairTowerLv2.getHeight())) {
					// repair of Towers of level 2 got clicked -> send Peons to repair one	
					ch.repairBuilding("Tower", 2);
				} else  {
					if (repairTowerLv3 &&
							x >= chooseRepairTowerLv3.getX() &&
							x <=  (chooseRepairTowerLv3.getX() + chooseRepairTowerLv3.getWidth()) &&
							y >= chooseRepairTowerLv3.getY() &&
							y <=  (chooseRepairTowerLv3.getY() + chooseRepairTowerLv3.getHeight())) {
						// repair of Towers of level 3 got clicked -> send Peons to repair one	
						ch.repairBuilding("Tower", 3);
					} else {
						// now no repair-icon could have been visible
						if (towerLv1Available &&
								x >= chooseTowerLv1.getX() &&
								x <=  (chooseTowerLv1.getX() + chooseTowerLv1.getWidth()) &&
								y >= chooseTowerLv1.getY() &&
								y <=  (chooseTowerLv1.getY() + chooseTowerLv1.getHeight())) {
							//Towers of level 1 got clicked -> send Archers to them	
							ch.moveArchersIntoTower(1);
						} else  {
							if (towerLv2Available &&
									x >= chooseTowerLv2.getX() &&
									x <=  (chooseTowerLv2.getX() + chooseTowerLv2.getWidth()) &&
									y >= chooseTowerLv2.getY() &&
									y <=  (chooseTowerLv2.getY() + chooseTowerLv2.getHeight())) {
								//Towers of level 2 got clicked -> send Archers to them	
								ch.moveArchersIntoTower(2);
							} else  {
								if (towerLv3Available &&
										x >= chooseTowerLv3.getX() &&
										x <=  (chooseTowerLv3.getX() + chooseTowerLv3.getWidth()) &&
										y >= chooseTowerLv3.getY() &&
										y <=  (chooseTowerLv3.getY() + chooseTowerLv3.getHeight())) {
									//Towers of level 3 got clicked -> send Archers to them	
									ch.moveArchersIntoTower(3);
								}
							}
						} 
					}
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
