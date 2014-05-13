package view.game.buildings;

import java.util.Iterator;

import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Tower;
import model.game.User;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import view.game.Portrait;

public class StrongholdView implements ComponentListener{
	
	private CIClient ciClient;
	private User currentUser;
	private model.game.Stronghold sh = null;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	boolean selectStrongholdPressed;
	private Image stronghold;
	private Image strongholdPortrait;
	private Image level1OfBuildings;
	private Image repairIcon;
	private GameContainer container;
	private MouseOverArea chooseStronghold;
	private MouseOverArea chooseRepairStronghold;
	private Portrait portrait;
	private Rectangle backgroundBar;
	private Rectangle hpBar;
	private float maxWidth;
	private int maxHealth;
	private int currentHP;
	private int currentLvl;
	private int constrAbleStronghold;
	private boolean strongholdAvailable = false;
	private boolean repairStronghold = false;
	
	private double buildProgress = 0.00;
	private double interval;
	private double widthOfBar = 220;
	private boolean strongholdIsUpgrading = false;
	
	private double peonCreationProgress = 0.00;
	private double intervalOfPeon;
	private boolean peonIsCreating = false;

	public StrongholdView(CIClient ciClient, Sector current, GameContainer container, String playersColor, User currentUser) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.currentUser = currentUser;
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
	}

	public void render(Graphics g) throws SlickException {

			if(strongholdAvailable){
				chooseStronghold.render(container, g);
				chooseStronghold.setNormalColor(new Color(1,1,1,0.9f));
				//bar for HP
				maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700));
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

				
				if(currentLvl == 1){
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.4f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*890)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*760)));
				}if(currentLvl == 2){
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.4f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*890)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*760)));
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.4f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*890)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*735)));
				}if(currentLvl == 3){
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.4f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*890)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*760)));
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.4f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*890)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*735)));
					g.drawImage(level1OfBuildings.getScaledCopy((((float) container.getHeight())/1080)*1.4f), 
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*890)), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*710)));
				}
				if(repairStronghold){
					chooseRepairStronghold.render(container, g);
					chooseRepairStronghold.setNormalColor(new Color(1,1,1,0.8f));
				}
				
				//bar for peons creating
				g.setColor(Color.gray);
				g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1060)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*782)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
				
				
				if(peonIsCreating){
					g.setColor(Color.orange);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1060)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*782)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-intervalOfPeon))), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					if(intervalOfPeon == widthOfBar){
						peonIsCreating = false;
					}
				}

				
				//bar for Upgrade
				g.setColor(Color.gray);
				g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1060)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*776)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
				
				if(strongholdIsUpgrading){
					g.setColor(Color.blue);
					g.fillRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1060)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*776)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*(1920-interval))), 
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1073)));
					if(interval == widthOfBar){
						peonIsCreating = false;
					}
				}
			}
	}
	
	public void init() throws SlickException {
		
		stronghold = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/Stronghold.png");
		
		repairIcon = new Image("res/Ingame/repairIcon.png");
		
		level1OfBuildings = new Image("res/Ingame/Level1.png");
		
		strongholdPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Stronghold.png");
		//mouseoverareas for units
		chooseStronghold = new MouseOverArea(container, stronghold.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1100)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*760)),this);
		
		chooseRepairStronghold = new MouseOverArea(container, repairIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1070)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*760)),this);
		
		backgroundBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1060)),
				(container.getHeight()-((((float) container.getHeight())/1080)*771)), 0, 5);
		hpBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1060)),
				(container.getHeight()-((((float) container.getHeight())/1080)*771)), 0, 5);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutStronghold();
	}
	
	public void getInfoAboutStronghold(){
		strongholdAvailable = false;
		repairStronghold = false;
		constrAbleStronghold = 0;
		buildProgress = 0;
		interval = 0;
		currentLvl = 0;
		peonCreationProgress = 0;
		intervalOfPeon = 0;
		
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		
		while(buildIter.hasNext()){
			Building build = buildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Stronghold")){
				strongholdAvailable = true;
				sh = (model.game.Stronghold) build;
				currentLvl = build.getLevel();
				maxHealth = build.getMaxHP();
				currentHP = build.getHp();

				if (build.getUnitTypeInCreation() != null) { // enemy Strongholds won`t have that
					//if peon is creating, get the actual interval for creating bar
					if (build.getUnitTypeInCreation().equals("PEON")) {
						peonCreationProgress = build.getUnitCreationProgress();
						intervalOfPeon = peonCreationProgress * widthOfBar;
						peonIsCreating = true;
					}
					//if stronghold is upgrading, get the actual buildprogress
					//and count the actual interval for the upgrade bar
					if (build.isUpgrading()) {
						//stronghold is upgrading: true
						strongholdIsUpgrading = build.isUpgrading();
						buildProgress = build.getBuildProgress();
						interval = buildProgress * widthOfBar;
					}
				}
				//if stronghold is found, break the loop
				break;
			}
		}
		hpBar.setWidth(maxWidth * currentHP / maxHealth);
		
		//is Stronghold damaged?
		Iterator<Building> repairBuildIter = currentSector.iteratorOfRepairableBuilding();
		
		while(repairBuildIter.hasNext()){
			Building build = repairBuildIter.next();
			
			String[] buildId = build.getId().split("@");
			String id = buildId[0];
			
			if(id.equals("Stronghold")  && build.getHp() < build.getMaxHP()){
				repairStronghold = true;
				break;
			}
		
		}
		
		//if tower can be up builded, show me the amount of this buildings
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();
		
		while(constrIter.hasNext()){
			Building constr = constrIter.next();
			if(constr instanceof Stronghold && constr.getUserAssets().equals(currentUser.getUserAssets()) 
					&& constr.getLevel() == 1){
				constrAbleStronghold++;
			}
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseStronghold){
			portrait = new Portrait(strongholdPortrait, container);
			selection ="strongholdLevel" + currentLvl;
			selectStrongholdPressed = true;
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectStrongholdPressed() {
		return selectStrongholdPressed;
	}

	public void setSelectStrongholdPressed(boolean selectStrongholdPressed) {
		this.selectStrongholdPressed = selectStrongholdPressed;
	}

	public MouseOverArea getChooseStronghold() {
		return chooseStronghold;
	}

	public void setChooseStronghold(MouseOverArea chooseStronghold) {
		this.chooseStronghold = chooseStronghold;
	}
	
	public void clicked(int button, int x, int y, int clickCount, CommandHelper ch) {
		// only react on rigth-clicks when button == 1
		if (button == 1) {
			
			//build an building up with rightclick
			if(constrAbleStronghold > 0 && x >= chooseStronghold.getX() &&
					x <=  (chooseStronghold.getX() + chooseStronghold.getWidth()) &&
					y >= chooseStronghold.getY() &&
					y <=  (chooseStronghold.getY() + chooseStronghold.getHeight())){
					
				ch.buildUpBuilding("Stronghold");
			}
			
			if (repairStronghold &&
					x >= chooseRepairStronghold.getX() &&
					x <=  (chooseRepairStronghold.getX() + chooseRepairStronghold.getWidth()) &&
					y >= chooseRepairStronghold.getY() &&
					y <=  (chooseRepairStronghold.getY() + chooseRepairStronghold.getHeight())) {
				// repair of Stronghold got clicked -> send Peons to repair it
				ch.repairBuilding("Stronghold", sh.getLevel());
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