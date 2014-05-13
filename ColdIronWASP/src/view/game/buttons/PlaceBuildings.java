package view.game.buttons;

import java.util.Iterator;

import model.game.Barrack;
import model.game.Building;
import model.game.CommandHelper;
import model.game.Forge;
import model.game.Sector;
import model.game.Stronghold;
import model.game.Tower;
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
import view.game.ingameMain;

public class PlaceBuildings implements ComponentListener{
	private GameContainer container;
	private String playersColor;
	private Sector currentSector;
	private User currentUser;
	private Boolean AIOn;
	
	private int myStronghold;
	private int enemyStrongholds;
	private int myStrongholdLevel2;
	private int myConstrStronghold;
	private int myBarrackLevel2;
	
	private Image buildStrongholdIcon;
	private Image buildBarrackIcon;
	private Image buildForgeIcon;
	private Image buildTowerIcon;
	private Image buildFarmIcon;
	
	private MouseOverArea buildStronghold;
	private MouseOverArea buildBarrack;
	private MouseOverArea buildForge;
	private MouseOverArea buildTower;
	private MouseOverArea buildFarm;
	
		
	public PlaceBuildings(GameContainer container, String playersColor, Sector currentSector, User currentUser, Boolean AIOn){
		this.container = container;
		this.playersColor = playersColor;
		this.currentSector = currentSector;
		this.currentUser = currentUser;
		this.AIOn = AIOn;
	}
	
	public void setCurrentSector(Sector currentSector){
		this.currentSector = currentSector;
	}
	
	public Sector getCurrentSector(){
		return currentSector;
	}
	
	public void init() throws SlickException{
		countBuildings();
		
		buildStrongholdIcon = new Image("res/Ingame/LowerBar/Icons/BuildStronghold.png");
		buildBarrackIcon = new Image("res/Ingame/LowerBar/Icons/BuildBarrack.png");
		buildForgeIcon = new Image("res/Ingame/LowerBar/Icons/BuildForge.png");
		buildTowerIcon = new Image("res/Ingame/LowerBar/Icons/BuildTower.png");
		buildFarmIcon = new Image("res/Ingame/LowerBar/Icons/BuildFarm.png");
		
		buildStronghold = new MouseOverArea(container, buildStrongholdIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildBarrack = new MouseOverArea(container, buildBarrackIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);

		buildForge = new MouseOverArea(container, buildForgeIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildTower = new MouseOverArea(container, buildTowerIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildFarm = new MouseOverArea(container, buildFarmIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
			
	}
	

	public void update(){
		countBuildings();
	}
	

	public void render(Graphics g) throws SlickException{
		if(myStronghold==0 && enemyStrongholds==0 && myConstrStronghold==0 && myStrongholdLevel2==0){
			buildStronghold.render(container, g);
			buildStronghold.setNormalColor(new Color(1,1,1,0.7f));
		}else if(myStronghold > 0){
			buildBarrack.render(container, g);
			buildBarrack.setNormalColor(new Color(1,1,1,0.7f));
			buildTower.render(container, g);
			buildTower.setNormalColor(new Color(1,1,1,0.7f));
			buildFarm.render(container, g);
			buildFarm.setNormalColor(new Color(1,1,1,0.7f));
			if(myStrongholdLevel2 > 0 && myBarrackLevel2 > 0){
				buildForge.render(container, g);
				buildForge.setNormalColor(new Color(1,1,1,0.7f));
			}
		}
	}
	
	public void componentActivated(AbstractComponent source){
	  if (!AIOn) {
		if(source == buildStronghold){
//			System.out.println("calling create_building(\"" + currentSector.getId() + "\",\"STRONGHOLD\")"); 	
			currentSector.getGame().getCIClient().getServerConnection().create_building(currentSector.getId(), "STRONGHOLD");
		}else if(source == buildBarrack){
			currentSector.getGame().getCIClient().getServerConnection().create_building(currentSector.getId(), "BARRACK");
		}else if(source == buildForge){
			currentSector.getGame().getCIClient().getServerConnection().create_building(currentSector.getId(), "FORGE");
		}else if(source == buildTower){
			currentSector.getGame().getCIClient().getServerConnection().create_building(currentSector.getId(), "TOWER");
		}else if(source == buildFarm){
			currentSector.getGame().getCIClient().getServerConnection().create_building(currentSector.getId(), "FARM");
		}		
	  }
	}
	

	public void countBuildings(){
		
		int myS=0,enemS=0,myS2=0, myConS=0, myB2=0;
		Iterator<Building> buildIter = currentSector.iteratorOfSectorBuildings();
		Iterator<Building> constrIter = currentSector.iteratorOfConstructableBuilding();


		while(buildIter.hasNext()){
			Building build = buildIter.next();
			if(build instanceof Stronghold && build.getUserAssets().equals(currentUser.getUserAssets()) 
					&& build.getLevel() == 2) {
				myS2++;
			}else if(build instanceof Stronghold && build.getUserAssets().equals(currentUser.getUserAssets()) 
					&& build.getLevel() == 3) {
				myS2++;
			}else if(build instanceof Barrack && build.getUserAssets().equals(currentUser.getUserAssets()) 
					&& build.getLevel() == 2) {
				myB2++;
			}else if(build instanceof Barrack && build.getUserAssets().equals(currentUser.getUserAssets()) 
					&& build.getLevel() == 3) {
				myB2++;
			}
			
			if(build instanceof Stronghold && build.getUserAssets().equals(currentUser.getUserAssets()) ) {
				myS++;
			}else {
				// first check if there are alliances at all
				if (build.getUserAssets().getAlliance() != null) {
					// there are some
					if(build instanceof Stronghold && !build.getUserAssets().equals(currentUser.getUserAssets()) 
							&& !build.getUserAssets().getAlliance().equals(currentUser.getUserAssets().getAlliance())) {
						enemS++;
					} 
				} else {
					// there are none
					// now some players having no alliance doesn`t end up in concluding they have the same
					// (because null == null yields true) -> that was the worst bug to fix ever ;)
					if(build instanceof Stronghold && !build.getUserAssets().equals(currentUser.getUserAssets())) {
						enemS++;
					}
				}			
			}

			while(constrIter.hasNext()){
				Building constr = constrIter.next();
				if(constr instanceof Stronghold && constr.getUserAssets().equals(currentUser.getUserAssets())){
					myConS++;
				}else if(constr instanceof Stronghold && !constr.getUserAssets().equals(currentUser.getUserAssets()) 
						&& !constr.getUserAssets().getAlliance().equals(currentUser.getUserAssets().getAlliance())){
					enemS++;
				}
			}
		}
			myStronghold = myS;
			enemyStrongholds = enemS;
			myStrongholdLevel2 = myS2;
			myConstrStronghold = myConS;
			myBarrackLevel2 = myB2;
	}

	public MouseOverArea getBuildStronghold() {
		return buildStronghold;
	}

	public MouseOverArea getBuildBarrack() {
		return buildBarrack;
	}

	public MouseOverArea getBuildForge() {
		return buildForge;
	}

	public MouseOverArea getBuildTower() {
		return buildTower;
	}

	public MouseOverArea getBuildFarm() {
		return buildFarm;
	}
}
