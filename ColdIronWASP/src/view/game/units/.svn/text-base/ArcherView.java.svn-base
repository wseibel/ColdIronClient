package view.game.units;

import java.util.Iterator;

import model.game.Archer;
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

public class ArcherView implements ComponentListener{

	private CIClient ciClient;
	private Sector currentSector;
	private String playersColor;
	private String selection;
	private String selectToMark;
	boolean selectArcherPressed;
	private float maxWidth;
	private Rectangle backgroundBar1;
	private Rectangle hpBar1;
	private int maxHp = 0;
	private int currentHp = 0;
	private int maxHealth1 = 0;
	private int currentHP1 = 0;
	private int amountOfFreeArcherLv1 = 0;
	private Rectangle backgroundBar2;
	private Rectangle hpBar2;
	private int maxHealth2 = 0;
	private int currentHP2 = 0;
	private int amountOfFreeArcherLv2 = 0;
	private Rectangle backgroundBar3;
	private Rectangle hpBar3;
	private int maxHealth3 = 0;
	private int currentHP3 = 0;
	private int amountOfFreeArcherLv3 = 0;
	private String numberOfArcherLv1 = "";
	private String numberOfArcherLv2 = "";
	private String numberOfArcherLv3 = "";
	private Image archerLv1;
	private Image archerLv2;
	private Image archerLv3;
	private Image archerPortrait;
	private GameContainer container;
	private MouseOverArea chooseArcherLv1;
	private MouseOverArea chooseArcherLv2;
	private MouseOverArea chooseArcherLv3;
	private Portrait portrait;
	private boolean archerLv1Available;
	private boolean archerLv2Available;
	private boolean archerLv3Available;
	private Fonts fonts;
	private Font font;
	private SelectUnits selectUnitButtons;

	public ArcherView(CIClient ciClient, Sector current, GameContainer container, String playersColor, SelectUnits selectUnitButtons) {
		this.playersColor = playersColor;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
		this.selectUnitButtons = selectUnitButtons;
	}

	public void render(Graphics g) throws SlickException {
		
		g.setFont(font);
		maxWidth = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1855));
		if(archerLv1Available && amountOfFreeArcherLv1 > 0){
			chooseArcherLv1.render(container, g);
			chooseArcherLv1.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfArcherLv1.valueOf(amountOfFreeArcherLv1), 
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1755)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)));
		}if(archerLv2Available  && amountOfFreeArcherLv2 > 0){
			chooseArcherLv2.render(container, g);
			chooseArcherLv2.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfArcherLv2.valueOf(amountOfFreeArcherLv2), 
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1755)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*918)));
		}if(archerLv3Available  && amountOfFreeArcherLv3 > 0){
			chooseArcherLv3.render(container, g);
			chooseArcherLv3.setNormalColor(new Color(1,1,1,0.9f));
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
			g.drawString(numberOfArcherLv3.valueOf(amountOfFreeArcherLv3), 
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1755)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*818)));
		}
	}

	public void init() throws SlickException {

		fonts = new Fonts(container);
		font = fonts.getFontForStrength();
		
		archerLv1 = new Image("res/Ingame/Player/"+playersColor+"Player/units/ArcherLv1.png");
		archerLv2 = new Image("res/Ingame/Player/"+playersColor+"Player/units/ArcherLv2.png");
		archerLv3 = new Image("res/Ingame/Player/"+playersColor+"Player/units/ArcherLv3.png");

		archerPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Archer.png");
		//mouseoverareas for units
		chooseArcherLv1 = new MouseOverArea(container, archerLv1.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*990)),this);

		chooseArcherLv2 = new MouseOverArea(container, archerLv2.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*890)),this);

		chooseArcherLv3 = new MouseOverArea(container, archerLv3.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*790)),this);

		backgroundBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1785)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);
		hpBar1 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1785)),
				(container.getHeight()-((((float) container.getHeight())/1080)*995)), 0, 0);

		backgroundBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1785)),
				(container.getHeight()-((((float) container.getHeight())/1080)*895)), 0, 0);
		hpBar2 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1785)),
				(container.getHeight()-((((float) container.getHeight())/1080)*895)), 0, 0);

		backgroundBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1785)),
				(container.getHeight()-((((float) container.getHeight())/1080)*795)), 0, 0);
		hpBar3 = new Rectangle((container.getWidth()- ((((float) container.getWidth())/1920)*1785)),
				(container.getHeight()-((((float) container.getHeight())/1080)*795)), 0, 0);
	}

	public void update(int delta) throws SlickException {
		getInfoAboutArcher();

	}

	public void getInfoAboutArcher(){
		amountOfFreeArcherLv1 = 0;
		currentHP1 = 0;
		archerLv1Available = false;
		amountOfFreeArcherLv2 = 0;
		currentHP2 = 0;
		archerLv2Available = false;
		amountOfFreeArcherLv3 = 0;
		currentHP3 = 0;
		archerLv3Available = false;
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();

		while(unitIter.hasNext()){
			Unit unit = unitIter.next();

			String[] unitId = unit.getId().split("@");
			String id = unitId[0];

			if(id.equals("Archer") && unit.getLevel() == 1){
				archerLv1Available = true;
				amountOfFreeArcherLv1++;
				//archer lv1 at towers
				Archer archer = (Archer) unit;
				if(archer.getTower() != null){
					amountOfFreeArcherLv1--;
				} else {
					currentHP1 += unit.getHp();
				}
				maxHealth1 = amountOfFreeArcherLv1 * unit.getMaxHP();
			}else if(id.equals("Archer") && unit.getLevel() == 2){
				archerLv2Available = true;
				amountOfFreeArcherLv2++;
				//archer lv1 at towers
				Archer archer = (Archer) unit;
				if(archer.getTower() != null){
					amountOfFreeArcherLv2--;
				} else {
					currentHP2 += unit.getHp();
				}
				maxHealth2 = amountOfFreeArcherLv2 * unit.getMaxHP();
			}else if(id.equals("Archer") && unit.getLevel() == 3){
				archerLv3Available = true;
				amountOfFreeArcherLv3++;
				//archer lv1 at towers
				Archer archer = (Archer) unit;
				if(archer.getTower() != null){
					amountOfFreeArcherLv3--;
				} else {
					currentHP3 += unit.getHp();
				}
				maxHealth3 = amountOfFreeArcherLv3 * unit.getMaxHP();
			}

		}
		hpBar1.setWidth(maxWidth * currentHP1 / maxHealth1);
		hpBar2.setWidth(maxWidth * currentHP2 / maxHealth2);
		hpBar3.setWidth(maxWidth * currentHP3 / maxHealth3);
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseArcherLv1){
			portrait = new Portrait(archerPortrait, container);
			selection = "archer";
			selectToMark = "archerLv1";
			maxHp = maxHealth1;
			currentHp = currentHP1;
			selectArcherPressed = true;
			for (int i = 0; i < amountOfFreeArcherLv1; i++) {
				// simulate a left-click on the select-ArcherLevel1-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)) + 1, 1);
			}
		}else if(source == chooseArcherLv2){
			portrait = new Portrait(archerPortrait, container);
			selection = "archer";
			selectToMark = "archerLv2";
			maxHp = maxHealth2;
			currentHp = currentHP2;
			selectArcherPressed = true;
			for (int i = 0; i < amountOfFreeArcherLv2; i++) {
				// simulate a left-click on the select-ArcherLevel2-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)) + 1,
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*157)) + 1, 1);
			}
		}else if(source == chooseArcherLv3){
			portrait = new Portrait(archerPortrait, container);
			selection = "archer";
			selectToMark = "archerLv3";
			maxHp = maxHealth3;
			currentHp = currentHP3;
			selectArcherPressed = true;
			for (int i = 0; i < amountOfFreeArcherLv3; i++) {
				// simulate a left-click on the select-ArcherLevel3-Button
				selectUnitButtons.clicked(0,
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)) + 1,
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

	public boolean isSelectArcherPressed() {
		return selectArcherPressed;
	}

	public void setSelectArcherPressed(boolean selectArcherPressed) {
		this.selectArcherPressed = selectArcherPressed;
	}

	public String getSelectToMark() {
		return selectToMark;
	}

	public void setSelectToMark(String selectToMark) {
		this.selectToMark = selectToMark;
	}

	public MouseOverArea getChooseArcherLv1() {
		return chooseArcherLv1;
	}

	public void setChooseArcherLv1(MouseOverArea chooseArcherLv1) {
		this.chooseArcherLv1 = chooseArcherLv1;
	}

	public MouseOverArea getChooseArcherLv2() {
		return chooseArcherLv2;
	}

	public MouseOverArea getChooseArcherLv3() {
		return chooseArcherLv3;
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
