package view.game.buttons;

import java.util.Iterator;

import model.game.Archer;
import model.game.CIClient;
import model.game.Catapult;
import model.game.Knight;
import model.game.Peon;
import model.game.Sector;
import model.game.Swordsman;
import model.game.Unit;
import model.game.User;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import view.game.MarkElement;
import view.game.Portrait;
import view.game.ingameForCiDummy;
import view.game.ingameMain;

public class SelectUnits /*implements ComponentListener*/ {
	private GameContainer container;
	private String selection = "";
	private String playersColor;
	private Portrait portrait;
	private boolean selectUnitPressed;
	private Font selectUnitsFont;
	private Sector currentSector;

	private Image peonIcon;
	private Image peonAllIcon;
	private Image swordsmanLevel1Icon;
	private Image swordsmanLevel2Icon;
	private Image swordsmanLevel3Icon;
	private Image swordsmanAllIcon;
	private Image archerLevel1Icon;
	private Image archerLevel2Icon;
	private Image archerLevel3Icon;
	private Image archerAllIcon;
	private Image knightLevel1Icon;
	private Image knightLevel2Icon;
	private Image knightLevel3Icon;
	private Image knightAllIcon;
	private Image catapultIcon;
	private Image catapultAllIcon;

	private Image peonPortrait;
	private Image swordsmanPortrait;
	private Image archerPortrait;
	private Image knightPortrait;
	private Image catapultPortrait;
	private Image mixedPortrait;
	private Image emptyPortrait;

	private MouseOverArea peon;
	private MouseOverArea peonAll;
	private MouseOverArea swordsmanLevel1;
	private MouseOverArea swordsmanLevel2;
	private MouseOverArea swordsmanLevel3;
	private MouseOverArea swordsmanAll;
	private MouseOverArea archerLevel1;
	private MouseOverArea archerLevel2;
	private MouseOverArea archerLevel3;
	private MouseOverArea archerAll;
	private MouseOverArea knightLevel1;
	private MouseOverArea knightLevel2;
	private MouseOverArea knightLevel3;
	private MouseOverArea knightAll;
	private MouseOverArea catapult;
	private MouseOverArea catapultAll;

	private int numberPeons = 0;
	private int numberPeonsAll = 0;
	private int numberCatapults = 0;
	private int numberCatapultsAll = 0;
	private int numberSwordsmanLevel1 = 0;
	private int numberSwordsmanLevel2 = 0;
	private int numberSwordsmanLevel3 = 0;
	private int numberSwordsmanAll = 0;
	private int numberArcherLevel1 = 0;
	private int numberArcherLevel2 = 0;
	private int numberArcherLevel3 = 0;
	private int numberArcherAll = 0;
	private int numberKnightLevel1 = 0;
	private int numberKnightLevel2 = 0;
	private int numberKnightLevel3 = 0;
	private int numberKnightAll = 0;

	public int numberSelectedPeons = 0;
	public int getNumberSelectedPeons() {
		return numberSelectedPeons;
	}
	public int numberSelectedPeonsAll = 0;
	public int getNumberSelectedPeonsAll() {
		return numberSelectedPeonsAll;
	}
	private int numberSelectedCatapults = 0;
	public int getNumberSelectedCatapults() {
		return numberSelectedCatapults;
	}
	private int numberSelectedCatapultsAll = 0;
	public int getNumberSelectedCatapultsAll() {
		return numberSelectedCatapultsAll;
	}
	public int numberSelectedSwordsmanLevel1 = 0;
	public int getNumberSelectedSwordsmanLevel1() {
		return numberSelectedSwordsmanLevel1;
	}
	private int numberSelectedSwordsmanLevel2 = 0;
	public int getNumberSelectedSwordsmanLevel2() {
		return numberSelectedSwordsmanLevel2;
	}
	private int numberSelectedSwordsmanLevel3 = 0;
	public int getNumberSelectedSwordsmanLevel3() {
		return numberSelectedSwordsmanLevel3;
	}
	public int numberSelectedSwordsmanAll = 0;
	public int getNumberSelectedSwordsmanAll() {
		return numberSelectedSwordsmanAll;
	}
	private int numberSelectedArcherLevel1 = 0;
	public int getNumberSelectedArcherLevel1() {
		return numberSelectedArcherLevel1;
	}
	private int numberSelectedArcherLevel2 = 0;
	public int getNumberSelectedArcherLevel2() {
		return numberSelectedArcherLevel2;
	}
	private int numberSelectedArcherLevel3 = 0;
	public int getNumberSelectedArcherLevel3() {
		return numberSelectedArcherLevel3;
	}
	private int numberSelectedArcherAll = 0;
	public int getNumberSelectedArcherAll() {
		return numberSelectedArcherAll;
	}
	private int numberSelectedKnightLevel1 = 0;
	public int getNumberSelectedKnightLevel1() {
		return numberSelectedKnightLevel1;
	}
	private int numberSelectedKnightLevel2 = 0;
	public int getNumberSelectedKnightLevel2() {
		return numberSelectedKnightLevel2;
	}
	private int numberSelectedKnightLevel3 = 0;
	public int getNumberSelectedKnightLevel3() {
		return numberSelectedKnightLevel3;
	}
	private int numberSelectedKnightAll = 0;
	public int getNumberSelectedKnightAll() {
		return numberSelectedKnightAll;
	}

	private String selectedPeons;
	private String selectedPeonsAll;
	private String selectedCatapults;
	private String selectedCatapultsAll;
	private String selectedSwordsmanLevel1;
	private String selectedSwordsmanLevel2;
	private String selectedSwordsmanLevel3;
	private String selectedSwordsmanAll;
	private String selectedArcherLevel1;
	private String selectedArcherLevel2;
	private String selectedArcherLevel3;
	private String selectedArcherAll;
	private String selectedKnightLevel1;
	private String selectedKnightLevel2;
	private String selectedKnightLevel3;
	private String selectedKnightAll;
	private CIClient ciClient;
	private User currentUser;
	
	public SelectUnits(GameContainer container, String playersColor, CIClient ciClient,
			Sector currentSector, User currentUser){
		this.container = container;
		this.playersColor = playersColor;
		this.ciClient = ciClient;
		this.currentSector = currentSector;
		this.currentUser = currentUser;
	}

	public void setCurrentSector(Sector currentSector){
		currentSector.removeAllFromSelectedUnit();
		numberSelectedPeons = 0;
		numberSelectedPeonsAll = 0;
		numberSelectedCatapults = 0;
		numberSelectedCatapultsAll = 0;
		numberSelectedSwordsmanLevel1 = 0;
		numberSelectedSwordsmanLevel2 = 0;
		numberSelectedSwordsmanLevel3 = 0;
		numberSelectedSwordsmanAll = 0;
		numberSelectedArcherLevel1 = 0;
		numberSelectedArcherLevel2 = 0;
		numberSelectedArcherLevel3 = 0;
		numberSelectedArcherAll = 0;
		numberSelectedKnightLevel1 = 0;
		numberSelectedKnightLevel2 = 0;
		numberSelectedKnightLevel3 = 0;
		numberSelectedKnightAll = 0;
		this.currentSector = currentSector;
	}

	public Sector getCurrentSector(){
		return this.currentSector;
	}

	public void init() throws SlickException{
		selection = "";
		selectUnitPressed = false;

		if(container.getWidth() == 1920 && container.getHeight() == 1080){
			selectUnitsFont = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 27), true);
		}else if(container.getWidth() == 1600 && container.getHeight() == 900){
			selectUnitsFont = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 21), true);
		}else if(container.getWidth() == 1280 && container.getHeight() == 720){
			selectUnitsFont = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17), true);
		}else if(container.getWidth() == 800 && container.getHeight() == 450){
			selectUnitsFont = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10), true);
		}

		peonIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/Peon.png");
		peonAllIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/PeonAll.png");
		swordsmanLevel1Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/SwordsmanLevel1.png");
		swordsmanLevel2Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/SwordsmanLevel2.png");
		swordsmanLevel3Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/SwordsmanLevel3.png");
		swordsmanAllIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/SwordsmanAll.png");
		archerLevel1Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/ArcherLevel1.png");
		archerLevel2Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/ArcherLevel2.png");
		archerLevel3Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/ArcherLevel3.png");
		archerAllIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/ArcherAll.png");
		knightLevel1Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/KnightLevel1.png");
		knightLevel2Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/KnightLevel2.png");
		knightLevel3Icon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/KnightLevel3.png");
		knightAllIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/KnightAll.png");
		catapultIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/Catapult.png");
		catapultAllIcon = new Image("res/Ingame/LowerBar/BiggerWidthIcons/CatapultAll.png");
		peonPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Peon.png");
		swordsmanPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Swordsman.png");
		archerPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Archer.png");
		knightPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Knight.png");
		catapultPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Catapult.png");
		mixedPortrait = new Image("res/Ingame/LowerBar/Portraits/"+playersColor+"Player/Mixed.png");
		emptyPortrait = new Image("res/Ingame/LowerBar/Portraits/Empty.png");

		peon = new MouseOverArea(container, peonIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)));

		peonAll = new MouseOverArea(container, peonAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)));

		swordsmanLevel1 = new MouseOverArea(container, swordsmanLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)));

		swordsmanLevel2 = new MouseOverArea(container, swordsmanLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*157)));

		swordsmanLevel3 = new MouseOverArea(container, swordsmanLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*104)));

		swordsmanAll = new MouseOverArea(container, swordsmanAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)));

		archerLevel1 = new MouseOverArea(container, archerLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)));

		archerLevel2 = new MouseOverArea(container, archerLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*157)));

		archerLevel3 = new MouseOverArea(container, archerLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*104)));

		archerAll = new MouseOverArea(container, archerAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*996)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)));

		knightLevel1 = new MouseOverArea(container, knightLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)));

		knightLevel2 = new MouseOverArea(container, knightLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*157)));

		knightLevel3 = new MouseOverArea(container, knightLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*104)));

		knightAll = new MouseOverArea(container, knightAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*898)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)));

		catapult = new MouseOverArea(container, catapultIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*800)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*210)));

		catapultAll = new MouseOverArea(container, catapultAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*800)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*50)));





	}

	public void update(){
		countUnits();
	}
	
	

	public void render(Graphics g)throws SlickException{
		g.setFont(selectUnitsFont);
		g.setColor(Color.red);
		
		if(numberPeons > 0){
			peon.render(container, g);
			g.drawString(selectedPeons=String.valueOf(numberSelectedPeons), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1162)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*190)));
			if(numberSelectedPeons == 0){
				peon.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				peon.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberPeons > 0){
			peonAll.render(container, g);
			if(numberSelectedPeonsAll != 0){
				g.drawString(selectedPeonsAll=String.valueOf(numberSelectedPeonsAll), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1162)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*30)));
			}
			if(numberSelectedPeonsAll == 0){
				peonAll.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				peonAll.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberSwordsmanLevel1 > 0){
			swordsmanLevel1.render(container, g);
			g.drawString(selectedSwordsmanLevel1=String.valueOf(numberSelectedSwordsmanLevel1), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1064)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*190)));
			if(numberSelectedSwordsmanLevel1 == 0){
				swordsmanLevel1.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				swordsmanLevel1.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberSwordsmanLevel2 > 0){
			swordsmanLevel2.render(container, g);
			g.drawString(selectedSwordsmanLevel2=String.valueOf(numberSelectedSwordsmanLevel2), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1064)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*137)));
			if(numberSelectedSwordsmanLevel2 == 0){
				swordsmanLevel2.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				swordsmanLevel2.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberSwordsmanLevel3 > 0){
			swordsmanLevel3.render(container, g);
			g.drawString(selectedSwordsmanLevel3=String.valueOf(numberSelectedSwordsmanLevel3), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1064)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*84)));		
			if(numberSelectedSwordsmanLevel3 == 0){
				swordsmanLevel3.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				swordsmanLevel3.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberSwordsmanAll > 0){
			swordsmanAll.render(container, g);
			if(numberSelectedSwordsmanAll != 0){
				g.drawString(selectedSwordsmanAll=String.valueOf(numberSelectedSwordsmanAll), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1064)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*30)));
			}
			if(numberSelectedSwordsmanAll == 0){
				swordsmanAll.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				swordsmanAll.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberArcherLevel1 > 0){
			archerLevel1.render(container, g);
			g.drawString(selectedArcherLevel1=String.valueOf(numberSelectedArcherLevel1), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*966)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*190)));
			if(numberSelectedArcherLevel1 == 0){
				archerLevel1.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				archerLevel1.setNormalColor(new Color(1,1,1,0.9f));
			}
		}	

		if(numberArcherLevel2 > 0){
			archerLevel2.render(container, g);
			g.drawString(selectedArcherLevel2=String.valueOf(numberSelectedArcherLevel2), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*966)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*137)));
			if(numberSelectedArcherLevel2 == 0){
				archerLevel2.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				archerLevel2.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberArcherLevel3 > 0){
			archerLevel3.render(container, g);
			g.drawString(selectedArcherLevel3=String.valueOf(numberSelectedArcherLevel3), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*966)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*84)));
			if(numberSelectedArcherLevel3 == 0){
				archerLevel3.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				archerLevel3.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberArcherAll > 0){
			archerAll.render(container, g);
			if(numberSelectedArcherAll != 0){
				g.drawString(selectedArcherAll=String.valueOf(numberSelectedArcherAll), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*966)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*30)));
			}
			if(numberSelectedArcherAll == 0){
				archerAll.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				archerAll.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberKnightLevel1 > 0){
			knightLevel1.render(container, g);
			g.drawString(selectedKnightLevel1=String.valueOf(numberSelectedKnightLevel1), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*868)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*190)));
			if(numberSelectedKnightLevel1 == 0){
				knightLevel1.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				knightLevel1.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberKnightLevel2 > 0){
			knightLevel2.render(container, g);
			g.drawString(selectedKnightLevel2=String.valueOf(numberSelectedKnightLevel2), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*868)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*137)));
			if(numberSelectedKnightLevel2 == 0){
				knightLevel2.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				knightLevel2.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberKnightLevel3 > 0){
			knightLevel3.render(container, g);
			g.drawString(selectedKnightLevel3=String.valueOf(numberSelectedKnightLevel3), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*868)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*84)));
			if(numberSelectedKnightLevel3 == 0){
				knightLevel3.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				knightLevel3.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberKnightAll > 0){
			knightAll.render(container, g);
			if(numberSelectedKnightAll != 0){
				g.drawString(selectedKnightAll=String.valueOf(numberSelectedKnightAll), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*868)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*30)));
			}
			if(numberSelectedKnightAll == 0){
				knightAll.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				knightAll.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberCatapults > 0){
			catapult.render(container, g);
			g.drawString(selectedCatapults=String.valueOf(numberSelectedCatapults), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*770)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*190)));
			if(numberSelectedCatapults == 0){
				catapult.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				catapult.setNormalColor(new Color(1,1,1,0.9f));
			}
		}

		if(numberCatapultsAll > 0){
			catapultAll.render(container, g);
			if(numberSelectedCatapultsAll != 0){
				g.drawString(selectedCatapultsAll=String.valueOf(numberSelectedCatapultsAll), (int)(container.getWidth()- ((((float) container.getWidth())/1920)*770)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*30)));
			}
			if(numberSelectedCatapultsAll == 0){
				catapultAll.setNormalColor(new Color(1,1,1,0.5f));
			}else {
				catapultAll.setNormalColor(new Color(1,1,1,0.9f));
			}			
		}
		
		if(numberSelectedPeons > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1910)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedSwordsmanLevel1 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedSwordsmanLevel2 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*890))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedSwordsmanLevel3 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*790))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedArcherLevel1 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1837)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedArcherLevel2 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*890))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1837)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedArcherLevel3 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*790))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1837)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedKnightLevel1 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1834)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*982)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedKnightLevel2 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*860))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1834)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*982)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedKnightLevel3 > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*730))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1834)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*982)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(numberSelectedCatapults > 0) {
			g.setColor(Color.green);
			g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1600)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
					+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1771)),
					(int)(container.getHeight()-((((float) container.getHeight())/1080)*906)
					-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
		}
		
		if(selection.equals("")){
			numberSelectedPeons = 0;
			numberSelectedPeonsAll = 0;
			numberSelectedCatapults = 0;
			numberSelectedCatapultsAll = 0;
			numberSelectedSwordsmanLevel1 = 0;
			numberSelectedSwordsmanLevel2 = 0;
			numberSelectedSwordsmanLevel3 = 0;
			numberSelectedSwordsmanAll = 0;
			numberSelectedArcherLevel1 = 0;
			numberSelectedArcherLevel2 = 0;
			numberSelectedArcherLevel3 = 0;
			numberSelectedArcherAll = 0;
			numberSelectedKnightLevel1 = 0;
			numberSelectedKnightLevel2 = 0;
			numberSelectedKnightLevel3 = 0;
			numberSelectedKnightAll = 0;
			currentSector.removeAllFromSelectedUnit();
		}
		
	}

	public void clicked(int button, int x, int y, int clickCount){
		//select Peon Button
		if(numberPeons > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192))+(peonIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*210));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*210))+(peonIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedPeons < numberPeons) {
						numberSelectedPeons++;
						if(numberSelectedPeonsAll > 0){
							numberSelectedPeonsAll = 0;
							removeAllPeonFromSelection();
						}
						addPeonToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedPeons != 0){
						numberSelectedPeons--;
						removePeonFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select AllPeons Button
		if(numberPeonsAll > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1192))+(peonAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*50));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*50))+(peonAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0){
						numberSelectedPeonsAll = numberPeons;
						numberSelectedPeons = 0;
						addAllPeonToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedPeonsAll != 0){
						numberSelectedPeonsAll = 0;
						removeAllPeonFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select SwordsmanLevel1 Button
		if(numberSwordsmanLevel1 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094))+(swordsmanLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*210));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*210))+(swordsmanLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedSwordsmanLevel1 < numberSwordsmanLevel1 ){
						System.out.println("Swordsman");
						numberSelectedSwordsmanLevel1++;
						if(numberSelectedSwordsmanAll > 0){
							numberSelectedSwordsmanAll = 0;
							removeAllSwordsmanFromSelection();
						}
						addSwordsmanLevel1ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedSwordsmanLevel1 != 0){
						numberSelectedSwordsmanLevel1--;
						removeSwordsmanLevel1FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select SwordsmanLevel2 Button
		if(numberSwordsmanLevel2 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094))+(swordsmanLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*157));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*157))+(swordsmanLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedSwordsmanLevel2 < numberSwordsmanLevel2){
						System.out.println("Swordsman");
						numberSelectedSwordsmanLevel2++;
						if(numberSelectedSwordsmanAll > 0){
							numberSelectedSwordsmanAll = 0;
							removeAllSwordsmanFromSelection();
						}
						addSwordsmanLevel2ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedSwordsmanLevel2 != 0){
						numberSelectedSwordsmanLevel2--;
						removeSwordsmanLevel2FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select SwordsmanLevel3 Button
		if(numberSwordsmanLevel3 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094))+(swordsmanLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*104));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*104))+(swordsmanLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedSwordsmanLevel3 < numberSwordsmanLevel3){
						System.out.println("Swordsman");
						numberSelectedSwordsmanLevel3++;
						if(numberSelectedSwordsmanAll > 0){
							numberSelectedSwordsmanAll = 0;
							removeAllSwordsmanFromSelection();
						}
						addSwordsmanLevel3ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedSwordsmanLevel3 != 0){
						numberSelectedSwordsmanLevel3--;
						removeSwordsmanLevel3FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select AllSwordsman Button
		if(numberSwordsmanAll > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*1094))+(swordsmanAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*50));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*50))+(swordsmanAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0){
						numberSelectedSwordsmanAll = numberSwordsmanAll;
						numberSelectedSwordsmanLevel1 = 0;
						numberSelectedSwordsmanLevel2 = 0;
						numberSelectedSwordsmanLevel3 = 0;
						addAllSwordsmanToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedSwordsmanAll != 0){
						numberSelectedSwordsmanAll = 0;
						removeAllSwordsmanFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}


		//select ArcherLevel1 Button
		if(numberArcherLevel1 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996))+(archerLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*210));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*210))+(archerLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedArcherLevel1 < numberArcherLevel1){
						numberSelectedArcherLevel1++;
						if(numberSelectedArcherAll > 0){
							numberSelectedArcherAll = 0;
							removeAllArcherFromSelection();
						}
						addArcherLevel1ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedArcherLevel1 != 0){
						numberSelectedArcherLevel1--;
						removeArcherLevel1FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select ArcherLevel2 Button
		if(numberArcherLevel2 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996))+(archerLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*157));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*157))+(archerLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedArcherLevel2 < numberArcherLevel2){
						numberSelectedArcherLevel2++;
						if(numberSelectedArcherAll > 0){
							numberSelectedArcherAll = 0;
							removeAllArcherFromSelection();
						}
						addArcherLevel2ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedArcherLevel2 != 0){
						numberSelectedArcherLevel2--;
						removeArcherLevel2FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select ArcherLevel3 Button
		if(numberArcherLevel3 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996))+(archerLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*104));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*104))+(archerLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedArcherLevel3 < numberArcherLevel3){
						numberSelectedArcherLevel3++;
						if(numberSelectedArcherAll > 0){
							numberSelectedArcherAll = 0;
							removeAllArcherFromSelection();
						}
						addArcherLevel3ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedArcherLevel3 != 0){
						numberSelectedArcherLevel3--;
						removeArcherLevel3FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select AllArcher Button
		if(numberArcherAll > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*996))+(archerAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*50));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*50))+(archerAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0){
						numberSelectedArcherAll = numberArcherAll;
						numberSelectedArcherLevel1 = 0;
						numberSelectedArcherLevel2 = 0;
						numberSelectedArcherLevel3 = 0;
						addAllArcherToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedArcherAll != 0){
						numberSelectedArcherAll = 0;
						removeAllArcherFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select KnightLevel1 Button
		if(numberKnightLevel1 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898))+(knightLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*210));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*210))+(knightLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedKnightLevel1 < numberKnightLevel1){
						numberSelectedKnightLevel1++;
						if(numberSelectedKnightAll > 0){
							numberSelectedKnightAll = 0;
							removeAllKnightFromSelection();
						}
						addKnightLevel1ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedKnightLevel1 != 0){
						numberSelectedKnightLevel1--;
						removeKnightLevel1FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select KnightLevel2 Button
		if(numberKnightLevel2 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898))+(knightLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*157));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*157))+(knightLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedKnightLevel2 < numberKnightLevel2){
						numberSelectedKnightLevel2++;
						if(numberSelectedKnightAll > 0){
							numberSelectedKnightAll = 0;
							removeAllKnightFromSelection();
						}
						addKnightLevel2ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedKnightLevel2 != 0){
						numberSelectedKnightLevel2--;
						removeKnightLevel2FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select KnightLevel3 Button
		if(numberKnightLevel3 > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898))+(knightLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*104));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*104))+(knightLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedKnightLevel3 < numberKnightLevel3){
						numberSelectedKnightLevel3++;
						if(numberSelectedKnightAll > 0){
							numberSelectedKnightAll = 0;
							removeAllKnightFromSelection();
						}
						addKnightLevel3ToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedKnightLevel3 != 0){
						numberSelectedKnightLevel3--;
						removeKnightLevel3FromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select AllKnight Button
		if(numberKnightAll > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*898))+(knightAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*50));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*50))+(knightAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0){
						numberSelectedKnightAll = numberKnightAll;
						numberSelectedKnightLevel1 = 0;
						numberSelectedKnightLevel2 = 0;
						numberSelectedKnightLevel3 = 0;
						addAllKnightToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedKnightAll != 0){
						numberSelectedKnightAll = 0;
						removeAllKnightFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select Catapult Button
		if(numberCatapults > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*800));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*800))+(knightLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*210));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*210))+(knightLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0 && numberSelectedCatapults < numberCatapults){
						numberSelectedCatapults++;
						if(numberSelectedCatapultsAll > 0){
							numberSelectedCatapultsAll = 0;
							removeAllCatapultFromSelection();
						}
						addCatapultToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedCatapults != 0){
						numberSelectedCatapults--;
						removeCatapultFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		//select AllCatapults Button
		if(numberCatapultsAll > 0){
			for(int i = (int)(container.getWidth()- ((((float) container.getWidth())/1920)*800));
					i <= (int)(container.getWidth()- ((((float) container.getWidth())/1920)*800))+(knightAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getWidth(); i++ ){
				for(int j = (int)(container.getHeight()-((((float) container.getHeight())/1080)*50));
						j <= (int)(container.getHeight()- ((((float) container.getHeight())/1080)*50))+(knightAllIcon.getScaledCopy((((float) container.getHeight())/1080)*0.7f)).getHeight(); j++){
					if(i == x && j == y && button == 0){
						numberSelectedCatapultsAll = numberCatapultsAll;
						numberSelectedCatapults = 0;
						addAllCatapultToSelection();
						selectUnitPressed = true;
					}else if(i == x && j == y && button == 1 && numberSelectedCatapultsAll != 0){
						numberSelectedCatapultsAll = 0;
						removeAllCatapultFromSelection();
						selectUnitPressed = true;
					}
				}
			}
		}

		if(selectUnitPressed == true){
			this.setPortriat();
		}
	}	

	int isMixed(){
		return (numberSelectedPeons+numberSelectedPeonsAll+numberSelectedSwordsmanLevel1+numberSelectedSwordsmanLevel2+numberSelectedSwordsmanLevel3+numberSelectedSwordsmanAll
				+numberSelectedArcherLevel1+numberSelectedArcherLevel2+numberSelectedArcherLevel3+numberSelectedArcherAll+numberSelectedKnightLevel1
				+numberSelectedKnightLevel2+numberSelectedKnightLevel3+numberSelectedKnightAll+numberSelectedCatapults+numberSelectedCatapultsAll);
	}
	

	public void setPortriat(){
		if(numberSelectedPeons+numberSelectedPeonsAll > 0 && this.isMixed() == numberSelectedPeons+numberSelectedPeonsAll){
			portrait = new Portrait(peonPortrait, container);
			selection = "peon";
		}else if(numberSelectedSwordsmanLevel1+numberSelectedSwordsmanLevel2+numberSelectedSwordsmanLevel3+numberSelectedSwordsmanAll > 0 
				&& this.isMixed() == numberSelectedSwordsmanLevel1+numberSelectedSwordsmanLevel2+numberSelectedSwordsmanLevel3+numberSelectedSwordsmanAll){
			portrait = new Portrait(swordsmanPortrait, container);
			selection = "swordsman";
		}else if(this.numberSelectedArcherLevel1+numberSelectedArcherLevel2+numberSelectedArcherLevel3+numberSelectedArcherAll > 0 
				&& this.isMixed() == this.numberSelectedArcherLevel1+numberSelectedArcherLevel2+numberSelectedArcherLevel3+numberSelectedArcherAll){
			portrait = new Portrait(archerPortrait, container);
			selection = "archer";
		}else if(numberSelectedKnightLevel1+numberSelectedKnightLevel2+numberSelectedKnightLevel3+numberSelectedKnightAll > 0 
				&& this.isMixed() == numberSelectedKnightLevel1+numberSelectedKnightLevel2+numberSelectedKnightLevel3+numberSelectedKnightAll){
			portrait = new Portrait(knightPortrait, container);
			selection = "knight";
		}else if(numberSelectedCatapults+numberSelectedCatapultsAll > 0 && this.isMixed() == numberSelectedCatapults+numberSelectedCatapultsAll){
			portrait = new Portrait(catapultPortrait, container);
			selection = "catapult";
		}else if(this.isMixed() != 0){
			portrait = new Portrait(mixedPortrait, container);
			selection = "mixed";
		}else {
			portrait = new Portrait(emptyPortrait, container);
			selection = "";
		}
	}


	public String getSelection(){
		return selection;
	}

	public void setSelection(String selection){
		this.selection = selection;
	}

	public boolean getSelectUnitPressed(){
		return selectUnitPressed;
	}

	public void setSelectUnitPressed(boolean selectUnitPressed){
		this.selectUnitPressed = selectUnitPressed;
	}

	public boolean isNotWorking(Unit unit){
		boolean isNotWorking = false;
		if(((Peon) unit).getCollecting() == null && ((Peon) unit).getWorkingOn() == null){
			isNotWorking = true;
		}
		return isNotWorking;
	}

	public boolean isNotInTower(Unit unit){
		boolean isNotInTower = false;
		if(((Archer) unit).getTower() == null){
			isNotInTower = true;
		}
		return isNotInTower;
	}

	public void countUnits(){
		int p=0,s1=0,s2=0,s3=0,a1=0,a2=0,a3=0,k1=0,k2=0,k3=0,c=0;
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon && isNotWorking(unit) && unit.getUserAssets().equals(currentUser.getUserAssets())){
				p++;
			}else if(unit instanceof Swordsman && unit.getLevel() == 1 && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				s1++;
			}else if(unit instanceof Swordsman && unit.getLevel() == 2 && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				s2++;
			}else if(unit instanceof Swordsman && unit.getLevel() == 3 && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				s3++;
			}else if(unit instanceof Archer && unit.getLevel() == 1 && isNotInTower(unit) && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				a1++;
			}else if(unit instanceof Archer && unit.getLevel() == 2 && isNotInTower(unit) && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				a2++;
			}else if(unit instanceof Archer && unit.getLevel() == 3 && isNotInTower(unit) && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				a3++;
			}else if(unit instanceof Knight && unit.getLevel() == 1 && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				k1++;
			}else if(unit instanceof Knight && unit.getLevel() == 2 && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				k2++;
			}else if(unit instanceof Knight && unit.getLevel() == 3 && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				k3++;
			}else if(unit instanceof Catapult && unit.getUserAssets().equals(currentUser.getUserAssets())) {
				c++;
			}
		}
		numberPeons = p;
		numberPeonsAll = p;
		numberSwordsmanLevel1 = s1;
		numberSwordsmanLevel2 = s2;
		numberSwordsmanLevel3 = s3;
		numberSwordsmanAll = s1+s2+s3;
		numberArcherLevel1 = a1;
		numberArcherLevel2 = a2;
		numberArcherLevel3 = a3;
		numberArcherAll = a1+a2+a3;
		numberKnightLevel1 = k1;
		numberKnightLevel2 = k2;
		numberKnightLevel3 = k3;
		numberKnightAll = k1+k2+k3;
		numberCatapults = c;
		numberCatapultsAll = c;
	}

	public void addPeonToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon && isNotWorking(unit)){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removePeonFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addAllPeonToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon && isNotWorking(unit)){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
				}
			}
		}
	}

	public void removeAllPeonFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Peon){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
				}
			}
		}
	}

	public void addSwordsmanLevel1ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman && unit.getLevel() == 1){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeSwordsmanLevel1FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman && unit.getLevel() == 1){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addSwordsmanLevel2ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman && unit.getLevel() == 2){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeSwordsmanLevel2FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman && unit.getLevel() == 2){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addSwordsmanLevel3ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman && unit.getLevel() == 3){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeSwordsmanLevel3FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman && unit.getLevel() == 3){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addAllSwordsmanToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
				}
			}			
		}
	}

	public void removeAllSwordsmanFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Swordsman){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
				}
			}
		}
	}

	public void addArcherLevel1ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && unit.getLevel() == 1 && isNotInTower(unit)){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeArcherLevel1FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && unit.getLevel() == 1){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addArcherLevel2ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && unit.getLevel() == 2 && isNotInTower(unit)){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeArcherLevel2FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && unit.getLevel() == 2){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addArcherLevel3ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && unit.getLevel() == 3 && isNotInTower(unit)){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeArcherLevel3FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && unit.getLevel() == 3){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addAllArcherToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer && isNotInTower(unit)){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
				}
			}			
		}
	}

	public void removeAllArcherFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Archer){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
				}
			}
		}
	}

	public void addKnightLevel1ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight && unit.getLevel() == 1){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeKnightLevel1FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight && unit.getLevel() == 1){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addKnightLevel2ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight && unit.getLevel() == 2){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeKnightLevel2FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight && unit.getLevel() == 2){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addKnightLevel3ToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight && unit.getLevel() == 3){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeKnightLevel3FromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight && unit.getLevel() == 3){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addAllKnightToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
				}
			}			
		}
	}

	public void removeAllKnightFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Knight){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
				}
			}
		}
	}

	public void addCatapultToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Catapult){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void removeCatapultFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Catapult){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
					return;
				}
			}
		}
	}

	public void addAllCatapultToSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Catapult){
				if(!currentSector.hasInSelectedUnit(unit)){
					currentSector.addToSelectedUnit(unit);
				}
			}
		}
	}

	public void removeAllCatapultFromSelection(){
		Iterator<Unit> unitIter = currentSector.iteratorOfSectorUnits();
		while(unitIter.hasNext()){
			Unit unit = unitIter.next();
			if(unit instanceof Catapult){
				if(currentSector.hasInSelectedUnit(unit)){
					currentSector.removeFromSelectedUnit(unit);
				}
			}
		}
	}

	public MouseOverArea getPeon() {
		return peon;
	}

	public MouseOverArea getPeonAll() {
		return peonAll;
	}

	public MouseOverArea getSwordsmanAll() {
		return swordsmanAll;
	}

	public MouseOverArea getArcherAll() {
		return archerAll;
	}

	public MouseOverArea getKnightAll() {
		return knightAll;
	}

	public MouseOverArea getCatapultAll() {
		return catapultAll;
	}

	public int getNumberPeons() {
		return numberPeons;
	}

	public int getNumberCatapults() {
		return numberCatapults;
	}

	public int getNumberSwordsmanLevel1() {
		return numberSwordsmanLevel1;
	}

	public int getNumberSwordsmanLevel2() {
		return numberSwordsmanLevel2;
	}

	public int getNumberSwordsmanLevel3() {
		return numberSwordsmanLevel3;
	}

	public int getNumberArcherLevel1() {
		return numberArcherLevel1;
	}

	public int getNumberArcherLevel2() {
		return numberArcherLevel2;
	}

	public int getNumberArcherLevel3() {
		return numberArcherLevel3;
	}

	public int getNumberKnightLevel1() {
		return numberKnightLevel1;
	}

	public int getNumberKnightLevel2() {
		return numberKnightLevel2;
	}

	public int getNumberKnightLevel3() {
		return numberKnightLevel3;
	}
}
