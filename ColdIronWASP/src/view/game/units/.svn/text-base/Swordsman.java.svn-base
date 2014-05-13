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

public class Swordsman implements ComponentListener{

	private CIClient ciClient;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	private String selectToMark;
	boolean selectSwordsmanPressed;
	private float maxWidth;
	private Rectangle backgroundBar1;
	private Rectangle hpBar1;
	private int maxHp = 0;
	private int currentHp = 0;
	private int maxHealth1 = 0;
	private int currentHP1 = 0;
	private int amountOfSwordsmanLv1 = 0;
	private Rectangle backgroundBar2;
	private Rectangle hpBar2;
	private int maxHealth2 = 0;
	private int currentHP2 = 0;
	private int amountOfSwordsmanLv2 = 0;
	private Rectangle backgroundBar3;
	private Rectangle hpBar3;
	private int maxHealth3 = 0;
	private int currentHP3 = 0;
	private int amountOfSwordsmanLv3 = 0;
	private String numberOfSwordsmanLv1 = "";
	private String numberOfSwordsmanLv2 = "";
	private String numberOfSwordsmanLv3 = "";
	private Image swordsmanLv1;
	private Image swordsmanLv2;
	private Image swordsmanLv3;
	private Image swordsmanPortrait;
	private GameContainer container;
	private MouseOverArea chooseSwordsmanLv1;
	private MouseOverArea chooseSwordsmanLv2;
	private MouseOverArea chooseSwordsmanLv3;
	private Portrait portrait;
	private boolean swordsmanAvailableLv1;
	private boolean swordsmanAvailableLv2;
	private boolean swordsmanAvailableLv3;
	private Fonts fonts;
	private Font font;
	private SelectUnits selectUnitButtons;

	public Swordsman(CIClient ciClient, Sector current, GameContainer container, String playersColor, SelectUnits selectUnitButtons) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.selectUnitButtons = selectUnitButtons;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);
		maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1875));
		if(swordsmanAvailableLv1){
			chooseSwordsmanLv1.render(container, g);
			chooseSwordsmanLv1.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfSwordsmanLv1.valueOf(amountOfSwordsmanLv1), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1828)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
		}if(swordsmanAvailableLv2){
			chooseSwordsmanLv2.render(container, g);
			chooseSwordsmanLv2.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfSwordsmanLv2.valueOf(amountOfSwordsmanLv2), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1828)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*918)));
		}if(swordsmanAvailableLv3){
			chooseSwordsmanLv3.render(container, g);
			chooseSwordsmanLv3.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfSwordsmanLv3.valueOf(amountOfSwordsmanLv3), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1828)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*818)));
		}
	}

	public void init() throws SlickException {

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		swordsmanLv1 = new Image("res/Ingame/Player/"+playersColor+"Player/units/SwordsmanLv1.png");
		swordsmanLv2 = new Image("res/Ingame/Player/"+playersColor+"Player/units/SwordsmanLv2.png");
		swordsmanLv3 = new Image("res/Ingame/Player/"+playersColor+"Player/units/SwordsmanLv3.png");

		swordsmanPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Swordsman.png");
		//mouseoverareas for units
		chooseSwordsmanLv1 = new MouseOverArea(container, swordsmanLv1.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*990)),this);

		chooseSwordsmanLv2 = new MouseOverArea(container, swordsmanLv2.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*890)),this);

		chooseSwordsmanLv3 = new MouseOverArea(container, swordsmanLv3.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*790)),this);

		backgroundBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1845)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
		hpBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1845)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);

		backgroundBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1845)),
				(container.getHeight()-((((float) container.getHeight())/1080)*895)), 0, 0);
		hpBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1845)),
				(container.getHeight()-((((float) container.getHeight())/1080)*895)), 0, 0);

		backgroundBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1845)),
				(container.getHeight()-((((float) container.getHeight())/1080)*795)), 0, 0);
		hpBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1845)),
				(container.getHeight()-((((float) container.getHeight())/1080)*795)), 0, 0);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutSwordsman();
	}

	public void getInfoAboutSwordsman(){
		amountOfSwordsmanLv1 = 0;
		currentHP1 = 0;
		swordsmanAvailableLv1 = false;
		amountOfSwordsmanLv2 = 0;
		currentHP2 = 0;
		swordsmanAvailableLv2 = false;
		amountOfSwordsmanLv3 = 0;
		currentHP3 = 0;
		swordsmanAvailableLv3 = false;

		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();

		while(unitIter.hasNext()){
			Unit unit = unitIter.next();

			String[] unitId = unit.getId().split("@");
			String id = unitId[0];

			if(id.equals("Swordsman") && unit.getLevel() == 1){
				amountOfSwordsmanLv1++;
				swordsmanAvailableLv1 = true;
				currentHP1 += unit.getHp();
				maxHealth1 = amountOfSwordsmanLv1 * unit.getMaxHP();
			}else if(id.equals("Swordsman") && unit.getLevel() == 2){
				amountOfSwordsmanLv2++;
				swordsmanAvailableLv2 = true;
				currentHP2 += unit.getHp();
				maxHealth2 = amountOfSwordsmanLv2 * unit.getMaxHP();
			}else if(id.equals("Swordsman") && unit.getLevel() == 3){
				amountOfSwordsmanLv3++;
				swordsmanAvailableLv3 = true;
				currentHP3 += unit.getHp();
				maxHealth3 = amountOfSwordsmanLv3 * unit.getMaxHP();
			}

		}
		hpBar1.setWidth(maxWidth * currentHP1 / maxHealth1);
		hpBar2.setWidth(maxWidth * currentHP2 / maxHealth2);
		hpBar3.setWidth(maxWidth * currentHP3 / maxHealth3);
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseSwordsmanLv1){
			portrait = new Portrait(swordsmanPortrait, container);
			selection = "swordsman";
			selectToMark = "swordsmanLv1";
			maxHp = maxHealth1;
			currentHp = currentHP1;
			selectSwordsmanPressed = true;	
			for (int i = 0; i < amountOfSwordsmanLv1; i++) {
				// simulate a left-click on the select-SwordsmanLv1-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)) + 1, 1);
			}
		}else if(source == chooseSwordsmanLv2){
			portrait = new Portrait(swordsmanPortrait, container);
			selection = "swordsman";
			selectToMark = "swordsmanLv2";
			maxHp = maxHealth2;
			currentHp = currentHP2;
			selectSwordsmanPressed = true;
			for (int i = 0; i < amountOfSwordsmanLv2; i++) {
				// simulate a left-click on the select-SwordsmanLv2-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*157)) + 1, 1);
			}
		}else if(source == chooseSwordsmanLv3){
			portrait = new Portrait(swordsmanPortrait, container);
			selection = "swordsman";
			selectToMark = "swordsmanLv3";
			maxHp = maxHealth3;
			currentHp = currentHP3;
			selectSwordsmanPressed = true;	
			for (int i = 0; i < amountOfSwordsmanLv3; i++) {
				// simulate a left-click on the select-SwordsmanLv3-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)) + 1,
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

	public boolean isSelectSwordsmanPressed() {
		return selectSwordsmanPressed;
	}

	public void setSelectSwordsmanPressed(boolean selectSwordsmanPressed) {
		this.selectSwordsmanPressed = selectSwordsmanPressed;
	}

	public String getSelectToMark() {
		return selectToMark;
	}

	public void setSelectToMark(String selectToMark) {
		this.selectToMark = selectToMark;
	}

	public MouseOverArea getChooseSwordsmanLv1() {
		return chooseSwordsmanLv1;
	}

	public void setChooseSwordsmanLv1(MouseOverArea chooseSwordsmanLv1) {
		this.chooseSwordsmanLv1 = chooseSwordsmanLv1;
	}
	public MouseOverArea getChooseSwordsmanLv2() {
		return chooseSwordsmanLv2;
	}

	public MouseOverArea getChooseSwordsmanLv3() {
		return chooseSwordsmanLv3;
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
