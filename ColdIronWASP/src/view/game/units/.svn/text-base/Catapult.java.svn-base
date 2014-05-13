package view.game.units;

import java.util.Iterator;

import model.game.CIClient;
import model.game.Sector;
import model.game.Unit;

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

public class Catapult implements ComponentListener{
	
	private CIClient ciClient;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	boolean selectCatapultPressed;
	private int amountOfCatapult = 0;
	private String numberOfCatapult = "";
	private Image catapult;
	private Image catapultPortrait;
	private GameContainer container;
	private MouseOverArea chooseCatapult;
	private Portrait portrait;
	private Rectangle backgroundBar;
	private Rectangle hpBar;
	private float maxWidth;
	private int maxHealth = 0;
	private int currentHP = 0;
	private boolean catapultAvailable;
	private Fonts fonts;
	private Font font;
	private SelectUnits selectUnitButtons;

	public Catapult(CIClient ciClient, Sector current, GameContainer container, String playersColor, SelectUnits selectUnitButtons) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.selectUnitButtons = selectUnitButtons;
	}

	public void render(Graphics g) throws SlickException {

		g.setFont(font);
			if(catapultAvailable){
				chooseCatapult.render(container, g);
				chooseCatapult.setNormalColor(new Color(1,1,1,0.9f));
				maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1780));
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
				g.drawString(numberOfCatapult.valueOf(amountOfCatapult), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1512)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
			}		
	}
	

	public void init() throws SlickException {

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		catapult = new Image("res/Ingame/Player/"+playersColor+"Player/units/Catapult.png");
		
		catapultPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Catapult.png");
		//mouseoverareas for units
		chooseCatapult = new MouseOverArea(container, catapult.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1600)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*990)),this);
		
		backgroundBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1595)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
		hpBar = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1595)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutCatapult();
	}
	
	public void getInfoAboutCatapult(){
		amountOfCatapult = 0;
		currentHP = 0;
		catapultAvailable = false;
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			
			String[] unitId = unit.getId().split("@");
			String id = unitId[0];
		
			if(id.equals("Catapult")){
				catapultAvailable = true;
				amountOfCatapult++;
				currentHP += unit.getHp();
				maxHealth = amountOfCatapult * unit.getMaxHP();
			}
		
		}
		hpBar.setWidth(maxWidth * currentHP / maxHealth);
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseCatapult){
			portrait = new Portrait(catapultPortrait, container);
			selection = "catapult";
			selectCatapultPressed = true;
			// simulate a left-click on the select-AllCatapults-Button
			selectUnitButtons.clicked(0,
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*800)) + 1,
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)) + 1, 1);
		}
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectCatapultPressed() {
		return selectCatapultPressed;
	}

	public void setSelectCatapultPressed(boolean selectPeonPressed) {
		this.selectCatapultPressed = selectPeonPressed;
	}

	public MouseOverArea getChooseCatapult() {
		return chooseCatapult;
	}

	public void setChooseCatapult(MouseOverArea chooseCatapult) {
		this.chooseCatapult = chooseCatapult;
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
