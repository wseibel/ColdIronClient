package view.game.units;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Peon;
import model.game.Sector;
import model.game.Unit;
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
import view.game.buttons.SelectUnits;

public class PeonView implements ComponentListener{
	
	private CIClient ciClient;
	private CommandHelper ch;
	private User currentUser;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	boolean selectPeonPressed;
	private int amountOfFreePeons = 0;
	private int amountOfPeonsWood = 0;
	private int amountOfPeonsIron = 0;
	private int amountOfPeonsStone = 0;
	private String numberOfPeons = "";
	private Image peon;
	private Image peonPortrait;
	private Image gatherIcon;
	private Image abortIcon;
	private GameContainer container;
	private MouseOverArea choosePeon;
	private MouseOverArea cancelToGatherWood;
	private MouseOverArea cancelToGatherStone;
	private MouseOverArea cancelToGatherIron;
	private Portrait portrait;
	private Rectangle backgroundBar;
	private Rectangle hpBar;
	private float maxWidth;
	private int maxHealth = 0;
	private int currentHP = 0;
	private boolean peonAvailable;
	private boolean peonGatherWood;
	private boolean peonGatherStone;
	private boolean peonGatherIron;
	
	private Fonts fonts;
	private Font font;
	private SelectUnits selectUnitButtons;

	public PeonView(CIClient ciClient, Sector current, GameContainer container, String playersColor, SelectUnits selectUnitButtons, User currentUser) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.selectUnitButtons = selectUnitButtons;
		this.currentUser = currentUser;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);
			if(peonAvailable && amountOfFreePeons > 0){
				choosePeon.render(container, g);
				choosePeon.setNormalColor(new Color(1,1,1,0.9f));
				maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1878));
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
				g.drawString(numberOfPeons.valueOf(amountOfFreePeons), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1895)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
			}
			//if peons gathering ress, they will be displayed
			if(peonGatherWood){
				g.drawImage(peon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*380)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*600)));
				g.drawImage(gatherIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*375)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*625)));
				cancelToGatherWood.setNormalColor(new Color(1,1,1,0.0f));
				cancelToGatherWood.render(container, g);
			}
			if(peonGatherStone){
				g.drawImage(peon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1500)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*480)));
				g.drawImage(gatherIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1495)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*505)));
				cancelToGatherStone.setNormalColor(new Color(1,1,1,0.0f));
				cancelToGatherStone.render(container, g);
			}
			if(peonGatherIron){
				g.drawImage(peon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*530)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*760)));
				g.drawImage(gatherIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f), 
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*525)), 
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*785)));
				cancelToGatherIron.setNormalColor(new Color(1,1,1,0.0f));
				cancelToGatherIron.render(container, g);
			}
			
	}
	
	public void init() throws SlickException {
		
		ch = new CommandHelper();

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		peon = new Image("res/Ingame/Player/"+playersColor+"Player/units/Peon.png");
		gatherIcon = new Image("res/Ingame/gatherIcon.png");
		abortIcon = new Image("res/Ingame/abortIcon.png");
		
		peonPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Peon.png");
		//mouseoverareas for units
		choosePeon = new MouseOverArea(container, peon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1910)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*990)),this);
		
		cancelToGatherWood = new MouseOverArea(container, abortIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*375)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*625)),this);
		
		cancelToGatherStone = new MouseOverArea(container, abortIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1495)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*505)),this);
		
		cancelToGatherIron = new MouseOverArea(container, abortIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*525)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*785)),this);
		
		backgroundBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1905)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
		hpBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1905)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
		
		
	}

	public void update(int delta) throws Exception, SlickException {
		getInfoAboutPeons();
	}
	

	public void getInfoAboutPeons() throws Exception{
		
		currentHP = 0;
		amountOfFreePeons = 0;
		amountOfPeonsWood = 0;
		amountOfPeonsIron = 0;
		amountOfPeonsStone = 0;
		peonAvailable = false;
		peonGatherWood = false;
		peonGatherStone = false;
		peonGatherIron = false;
		
		if(currentSector.iteratorOfSectorUnits() != null){
			Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
			
			while(unitIter.hasNext()){
				Unit unitOnSector = unitIter.next();
				
				String[] unitId = unitOnSector.getId().split("@");
				String id = unitId[0];
			
				if(id.equals("Peon")){
					peonAvailable = true;
									
					Peon peon = (Peon) unitOnSector;
					//count only the free units
					if((peon.getCollecting() == null) && (peon.getWorkingOn() == null)){
						amountOfFreePeons++;
						currentHP += unitOnSector.getHp();
					}
					
					maxHealth = amountOfFreePeons * unitOnSector.getMaxHP();
				}
				
			}
			
			hpBar.setWidth(maxWidth * currentHP / maxHealth);
		}else
			System.err.println("Keine Peons vom Sektor");

		if(currentUser.getUserAssets().iteratorOfUnits() != null){
			//iterator of users units
			Iterator<Unit> unitOfUserIter = currentUser.getUserAssets().iteratorOfUnits();
			
			//proofs only the peons of userassets
			while(unitOfUserIter.hasNext()){
			
				Unit unitFromUser = unitOfUserIter.next();
				
				String[] unitId = unitFromUser.getId().split("@");
				String id = unitId[0];
			
				if(id.equals("Peon")){
									
					Peon peon = (Peon) unitFromUser;

					//show me the amount of peons, who are gathering res
					//show only peons at the current sector
					if(peon.getCollecting() != null && currentSector == peon.getCollecting().getSector()){
						if((peon.getCollecting().getType().equals("STONE"))){
							peonGatherStone = true;
							amountOfPeonsStone++;
						}else if((peon.getCollecting().getType().equals("WOOD"))){
							peonGatherWood = true;
							amountOfPeonsWood++;
						}else if((peon.getCollecting().getType().equals("IRON"))){
							peonGatherIron = true;
							amountOfPeonsIron++;
						}
					
					}
				}
			
			}
		}else
			System.err.println("Keine Peons von UserAssests");
	}
	

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == choosePeon){
			portrait = new Portrait(peonPortrait, container);
			selection ="peon";
			selectPeonPressed = true;
			// simulate a left-click on the select-AllPeons-Button
			selectUnitButtons.clicked(0,
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192)) + 1,
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)) + 1, 1);
		}else if(source == cancelToGatherWood){
			ch.removePeonsFromResource("WOOD", 1);
		}else if(source == cancelToGatherStone){
			ch.removePeonsFromResource("STONE", 1);
		}else if(source == cancelToGatherIron){
			ch.removePeonsFromResource("IRON", 1);
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectPeonPressed() {
		return selectPeonPressed;
	}

	public void setSelectPeonPressed(boolean selectPeonPressed) {
		this.selectPeonPressed = selectPeonPressed;
	}

	public MouseOverArea getChoosePeon() {
		return choosePeon;
	}

	public void setChoosePeon(MouseOverArea choosePeon) {
		this.choosePeon = choosePeon;
	}

	public CommandHelper getCh() {
		return ch;
	}

	public void setCh(CommandHelper ch) {
		this.ch = ch;
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
