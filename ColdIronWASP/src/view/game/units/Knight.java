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

public class Knight implements ComponentListener{

	private CIClient ciClient;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	private String selectToMark;
	boolean selectKnightPressed;
	private float maxWidth;
	private Rectangle backgroundBar1;
	private Rectangle hpBar1;
	private int maxHp = 0;
	private int currentHp = 0;
	private int maxHealth1 = 0;
	private int currentHP1 = 0;
	private int amountOfKnightLv1 = 0;
	private Rectangle backgroundBar2;
	private Rectangle hpBar2;
	private int maxHealth2 = 0;
	private int currentHP2 = 0;
	private int amountOfKnightLv2 = 0;
	private Rectangle backgroundBar3;
	private Rectangle hpBar3;
	private int maxHealth3 = 0;
	private int currentHP3 = 0;
	private int amountOfKnightLv3 = 0;
	private String numberOfKnightLv1 = "";
	private String numberOfKnightLv2 = "";
	private String numberOfKnightLv3 = "";
	private Image knightLv1;
	private Image knightLv2;
	private Image knightLv3;
	private Image knightPortrait;
	private GameContainer container;
	private MouseOverArea chooseKnightLv1;
	private MouseOverArea chooseKnightLv2;
	private MouseOverArea chooseKnightLv3;
	private Portrait portrait;
	private boolean knightLv1Available;
	private boolean knightLv2Available;
	private boolean knightLv3Available;
	
	private Fonts fonts;
	private Font font;
	private SelectUnits selectUnitButtons;

	public Knight(CIClient ciClient, Sector current, GameContainer container, String playersColor, SelectUnits selectUnitButtons) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.selectUnitButtons = selectUnitButtons;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);
		maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1845));
		if(knightLv1Available){
			chooseKnightLv1.render(container, g);
			chooseKnightLv1.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfKnightLv1.valueOf(amountOfKnightLv1), 
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1660)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
		}if(knightLv2Available){
			chooseKnightLv2.render(container, g);
			chooseKnightLv2.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfKnightLv2.valueOf(amountOfKnightLv2), 
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1660)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*889)));
		}if(knightLv3Available){
			chooseKnightLv3.render(container, g);
			chooseKnightLv3.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfKnightLv3.valueOf(amountOfKnightLv3), 
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1660)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*755)));
		}
	}

	public void init() throws SlickException {

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		knightLv1 = new Image("res/Ingame/Player/"+playersColor+"Player/units/KnightLv1.png");
		knightLv2 = new Image("res/Ingame/Player/"+playersColor+"Player/units/KnightLv2.png");
		knightLv3 = new Image("res/Ingame/Player/"+playersColor+"Player/units/KnightLv3.png");

		knightPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Knight.png");
		//mouseoverareas for units
		chooseKnightLv1 = new MouseOverArea(container, knightLv1.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*990)),this);

		chooseKnightLv2 = new MouseOverArea(container, knightLv2.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*860)),this);

		chooseKnightLv3 = new MouseOverArea(container, knightLv3.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*730)),this);

		backgroundBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1695)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
		hpBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1695)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);

		backgroundBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1695)),
				(container.getHeight()-((((float) container.getHeight())/1080)*865)), 0, 0);
		hpBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1695)),
				(container.getHeight()-((((float) container.getHeight())/1080)*865)), 0, 0);

		backgroundBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1695)),
				(container.getHeight()-((((float) container.getHeight())/1080)*735)), 0, 0);
		hpBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1695)),
				(container.getHeight()-((((float) container.getHeight())/1080)*735)), 0, 0);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutKnight();

	}

	public void getInfoAboutKnight(){
		amountOfKnightLv1 = 0;
		currentHP1 = 0;
		knightLv1Available = false;
		amountOfKnightLv2 = 0;
		currentHP2 = 0;
		knightLv2Available = false;
		amountOfKnightLv3 = 0;
		currentHP3 = 0;
		knightLv3Available = false;
		
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();

		while(unitIter.hasNext()){
			Unit unit = unitIter.next();

			String[] unitId = unit.getId().split("@");
			String id = unitId[0];

			if(id.equals("Knight") && unit.getLevel() == 1){
				knightLv1Available = true;
				amountOfKnightLv1++;
				currentHP1 += unit.getHp();
				maxHealth1 = amountOfKnightLv1 * unit.getMaxHP();
			}else if(id.equals("Knight") && unit.getLevel() == 2){
				knightLv2Available = true;
				amountOfKnightLv2++;
				currentHP2 += unit.getHp();
				maxHealth2 = amountOfKnightLv2 * unit.getMaxHP();
			}else if(id.equals("Knight") && unit.getLevel() == 3){
				knightLv3Available = true;
				amountOfKnightLv3++;
				currentHP3 += unit.getHp();
				maxHealth3 = amountOfKnightLv3 * unit.getMaxHP();
			}

		}
		hpBar1.setWidth(maxWidth * currentHP1 / maxHealth1);
		hpBar2.setWidth(maxWidth * currentHP2 / maxHealth2);
		hpBar3.setWidth(maxWidth * currentHP3 / maxHealth3);
	}


	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseKnightLv1){
			portrait = new Portrait(knightPortrait, container);
			selection = "knight";
			selectToMark = "knightLv1";
			maxHp = maxHealth1;
			currentHp = currentHP1;
			selectKnightPressed = true;
			for (int i = 0; i < amountOfKnightLv1; i++) {
				// simulate a left-click on the select-KnightLevel1-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)) + 1, 1);
			}
		}else if(source == chooseKnightLv2){
			portrait = new Portrait(knightPortrait, container);
			selection = "knight";
			selectToMark = "knightLv2";
			maxHp = maxHealth2;
			currentHp = currentHP2;
			selectKnightPressed = true;
			for (int i = 0; i < amountOfKnightLv2; i++) {
				// simulate a left-click on the select-KnightLevel2-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*157)) + 1, 1);
			}
		}
		else if(source == chooseKnightLv3){
			portrait = new Portrait(knightPortrait, container);
			selection = "knight";
			selectToMark = "knightLv3";
			maxHp = maxHealth3;
			currentHp = currentHP3;
			selectKnightPressed = true;
			for (int i = 0; i < amountOfKnightLv3; i++) {
				// simulate a left-click on the select-KnightLevel3-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*104)) + 1, 1);
			}
		}

	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectKnightPressed() {
		return selectKnightPressed;
	}

	public void setSelectKnightPressed(boolean selectKnightPressed) {
		this.selectKnightPressed = selectKnightPressed;
	}

	public String getSelectToMark() {
		return selectToMark;
	}

	public void setSelectToMark(String selectToMark) {
		this.selectToMark = selectToMark;
	}

	public MouseOverArea getChooseKnightLv1() {
		return chooseKnightLv1;
	}

	public void setChooseKnightLv1(MouseOverArea chooseKnightLv1) {
		this.chooseKnightLv1 = chooseKnightLv1;
	}

	public MouseOverArea getChooseKnightLv2() {
		return chooseKnightLv2;
	}

	public MouseOverArea getChooseKnightLv3() {
		return chooseKnightLv3;
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
