package view.game.buildings;

import java.util.Iterator;

import model.game.Barrack;
import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Farm;
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

public class FarmView implements ComponentListener{
	
	private CIClient ciClient;
	private User currentUser;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	boolean selectFarmPressed;
	private Image farm;
	private Image farmPortrait;
	private Image repairIcon;
	private GameContainer container;
	private MouseOverArea chooseFarm;
	private MouseOverArea chooseRepairFarm;
	private Portrait portrait;
	private Rectangle backgroundBar;
	private Rectangle hpBar;
	private float maxWidth;
	private int constrAbleFarm;
	private int maxHealth = 0;
	private int currentHP = 0;
	private int amountOfFarm = 0;
	private String numberOfFarm = "";
	private boolean farmAvailable = false;
	private boolean repairFarm = false;
	private Fonts fonts;
	private Font font;
	
	public int getAmountOfFarm() {
		return amountOfFarm;
	}

	public FarmView(CIClient ciClient, Sector current, GameContainer container, String playersColor, User currentUser) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.currentUser = currentUser;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);

			if(farmAvailable){
				chooseFarm.render(container, g);
				chooseFarm.setNormalColor(new Color(1,1,1,0.9f));
				maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1840));
				backgroundBar.setSize(maxWidth, (int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
				g.setColor(Color.gray);
				g.fill(backgroundBar);
				if(currentHP <= (maxHealth / 4)) {
					g.setColor(Color.red);
				} else if(currentHP <= (maxHealth / 2)) {
					g.setColor(Color.yellow);
				} else {
					g.setColor(Color.green);
				}
				hpBar.setHeight((int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
				g.fill(hpBar);
				g.setColor(Color.white);
				g.drawString(numberOfFarm.valueOf(amountOfFarm), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*790)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*790)));
				if(repairFarm){
					chooseRepairFarm.render(container, g);
					chooseRepairFarm.setNormalColor(new Color(1,1,1,0.8f));
				}
			}
		
	}
	
	public void init() throws SlickException {
		
		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		farm = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/Farm.png");
		
		repairIcon = new Image("res/Ingame/repairIcon.png");
		
		farmPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Farm.png");
		//mouseoverareas for units
		chooseFarm = new MouseOverArea(container, farm.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*790)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*800)),this);
		
		chooseRepairFarm = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*740)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*800)),this);
		

		backgroundBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*780)),
				(container.getHeight()-((((float) container.getHeight())/1080)*805)), 0, 5);
		hpBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*780)),
				(container.getHeight()-((((float) container.getHeight())/1080)*805)), 0, 5);

	}

	public void update(int delta) throws SlickException {
		getInfoAboutFarm();
	}
	
	public void getInfoAboutFarm(){
		constrAbleFarm = 0;
		amountOfFarm = 0;
		currentHP = 0;
		farmAvailable = false;
		repairFarm = false;
		
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		
		while(buildIter.hasNext()){
			Building build = buildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Farm")){
				farmAvailable = true;
				amountOfFarm++;
				currentHP += build.getHp();
				maxHealth = amountOfFarm * build.getMaxHP();
			}
		}
		hpBar.setWidth(maxWidth * currentHP / maxHealth);
		
		//is Farm damaged?
		Iterator<Building> repairBuildIter = currentSector.iteratorOfRepairableBuilding();
		
		while(repairBuildIter.hasNext()){
			Building build = repairBuildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Farm") && build.getHp() < build.getMaxHP()){
				repairFarm = true;
				break;
			}
		
		}
		
		//if farm can be up builded, show me the amount of this buildings
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();
		
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			if(constr instanceof Farm && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				constrAbleFarm++;
			}
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseFarm){
			portrait = new Portrait(farmPortrait, container);
			selection = "farm";
			selectFarmPressed = true;
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectFarmPressed() {
		return selectFarmPressed;
	}

	public void setSelectFarmPressed(boolean selectFarmPressed) {
		this.selectFarmPressed = selectFarmPressed;
	}

	public MouseOverArea getChooseFarm() {
		return chooseFarm;
	}

	public void setChooseFarm(MouseOverArea chooseFarm) {
		this.chooseFarm = chooseFarm;
	}
	
	public void clicked(int button, int x, int y, int clickCount, CommandHelper ch) {
		// only react on rigth-clicks when button == 1
		if (button == 1) {
			
			//build an building up with rightclick
			if(constrAbleFarm > 0 && x >= chooseFarm.getX() &&
					x <=  (chooseFarm.getX() + chooseFarm.getWidth()) &&
					y >= chooseFarm.getY() &&
					y <=  (chooseFarm.getY() + chooseFarm.getHeight())){
					
				ch.buildUpBuilding("Farm");
			}
			
			if (repairFarm &&
					x >= chooseRepairFarm.getX() &&
					x <=  (chooseRepairFarm.getX() + chooseRepairFarm.getWidth()) &&
					y >= chooseRepairFarm.getY() &&
					y <=  (chooseRepairFarm.getY() + chooseRepairFarm.getHeight())) {
				// repair of Farms got clicked -> send Peons to repair one	
				ch.repairBuilding("Farm", 1);
			}
		}	
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

}
