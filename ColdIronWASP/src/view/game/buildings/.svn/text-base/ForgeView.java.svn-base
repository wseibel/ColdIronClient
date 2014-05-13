package view.game.buildings;

import java.util.Iterator;

import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Farm;
import model.game.Forge;
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

public class ForgeView implements ComponentListener{
	
	private CIClient ciClient;
	private User currentUser;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	boolean selectForgePressed;
	private Image forge;
	private Image forgePortrait;
	private Image repairIcon;
	private GameContainer container;
	private MouseOverArea chooseForge;
	private MouseOverArea chooseRepairForge;
	private Portrait portrait;
	private Rectangle backgroundBar;
	private Rectangle hpBar;
	private float maxWidth;
	private int constrAbleForge;
	private int maxHealth = 0;
	private int currentHP = 0;
	private int amountOfForge = 0;
	private String numberOfForge = "";
	private boolean forgeAvailable = false;
	private boolean repairForge = false;
	private Fonts fonts;
	private Font font;
	
	private double widthOfBar = 160;
	private double catapultCreationProgress = 0.00;
	private double interval;
	private boolean catapultIsCreating = false;

	public ForgeView(CIClient ciClient, Sector current, GameContainer container, String playersColor, User currentUser) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.currentUser = currentUser;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);

			if(forgeAvailable){
				chooseForge.render(container, g);
				chooseForge.setNormalColor(new Color(1,1,1,0.9f));
				maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1760));
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
				
				//bar for catapult creation
				g.setColor(Color.gray);
				g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1365)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*721)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1760)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
			
				
				if(catapultIsCreating){
					g.setColor(Color.orange);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1365)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*721)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-interval))), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					if(interval == widthOfBar){
						catapultIsCreating = false;
					}
				}
	
				g.setColor(Color.white);
				g.drawString(numberOfForge.valueOf(amountOfForge), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1370)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*700)));
				if(repairForge){
					chooseRepairForge.render(container, g);
					chooseRepairForge.setNormalColor(new Color(1,1,1,0.8f));
				}
			}		
	}
	
	public void init() throws SlickException {

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		forge = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/Forge.png");
		
		repairIcon = new Image("res/Ingame/repairIcon.png");
		
		forgePortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Forge.png");
		//mouseoverareas for units
		chooseForge = new MouseOverArea(container, forge.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1390)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*710)),this);
		
		chooseRepairForge = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1330)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*700)),this);
		

		backgroundBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1365)),
				(container.getHeight()-((((float) container.getHeight())/1080)*715)), 0, 5);
		hpBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1365)),
				(container.getHeight()-((((float) container.getHeight())/1080)*715)), 0, 5);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutForge();
		
	}
	
	public void getInfoAboutForge(){
		amountOfForge = 0;
		constrAbleForge = 0;
		currentHP = 0;
		forgeAvailable = false;
		repairForge = false;
		
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		
		while(buildIter.hasNext()){
			Building build = buildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Forge")){
				forgeAvailable = true;
				amountOfForge++;
				currentHP += build.getHp();
				maxHealth = amountOfForge * build.getMaxHP();
				
				if (build.getUnitTypeInCreation() != null) { //enemy builds won`t have that
					//show me the bar, if catapult is creating
					if(build.getUnitTypeInCreation().equals("CATAPULT")){
						catapultCreationProgress = build.getUnitCreationProgress();
						interval = widthOfBar*catapultCreationProgress;

						catapultIsCreating = true;
					}
				}
			}
		}
		hpBar.setWidth(maxWidth * currentHP / maxHealth);
		
		//is Forge damaged?
		Iterator<Building> repairBuildIter = currentSector.iteratorOfRepairableBuilding();
		
		while(repairBuildIter.hasNext()){
			Building build = repairBuildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Forge")  && build.getHp() < build.getMaxHP()){
				repairForge = true;
				break;
			}
		
		}
		
		//if forge can be up builded, show me the amount of this buildings
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();
		
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			if(constr instanceof Forge && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				constrAbleForge++;
			}
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseForge){
			portrait = new Portrait(forgePortrait, container);
			selection = "forge";
			selectForgePressed = true;
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectForgePressed() {
		return selectForgePressed;
	}

	public void setSelectForgePressed(boolean selectForgePressed) {
		this.selectForgePressed = selectForgePressed;
	}

	public MouseOverArea getChooseForge() {
		return chooseForge;
	}

	public void setChooseForge(MouseOverArea chooseForge) {
		this.chooseForge = chooseForge;
	}
	
	public void clicked(int button, int x, int y, int clickCount, CommandHelper ch) {
		// only react on rigth-clicks when button == 1
		if (button == 1) {
			
			//build an building up with rightclick
			if(constrAbleForge > 0 && x >= chooseForge.getX() &&
					x <=  (chooseForge.getX() + chooseForge.getWidth()) &&
					y >= chooseForge.getY() &&
					y <=  (chooseForge.getY() + chooseForge.getHeight())){
					
				ch.buildUpBuilding("Forge");
			}
			
			if (repairForge &&
					x >= chooseRepairForge.getX() &&
					x <=  (chooseRepairForge.getX() + chooseRepairForge.getWidth()) &&
					y >= chooseRepairForge.getY() &&
					y <=  (chooseRepairForge.getY() + chooseRepairForge.getHeight())) {
				// repair of Farms got clicked -> send Peons to repair one
				ch.repairBuilding("Forge", 1);
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