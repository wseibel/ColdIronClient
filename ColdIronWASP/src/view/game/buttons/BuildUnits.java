package view.game.buttons;

import java.util.Iterator;

import model.game.Barrack;
import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Forge;
import model.game.Stronghold;
import model.game.User;
import model.lobby.ServerConnection;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import view.game.Portrait;


public class BuildUnits implements ComponentListener{
	private GameContainer container;
	private String selection;

	private Image archerLevel1Icon;
	private Image archerLevel2Icon;
	private Image archerLevel3Icon;
	private Image catapultIcon;
	private Image knightLevel1Icon;
	private Image knightLevel2Icon;
	private Image knightLevel3Icon;
	private Image peonIcon;
	private Image swordsmanLevel1Icon;
	private Image swordsmanLevel2Icon;
	private Image swordsmanLevel3Icon;
	
	private MouseOverArea buildArcherLevel1;
	private MouseOverArea buildArcherLevel2;
	private MouseOverArea buildArcherLevel3;
	private MouseOverArea buildCatapult;
	private MouseOverArea buildKnightLevel1;
	private MouseOverArea buildKnightLevel2;
	private MouseOverArea buildKnightLevel3;
	private MouseOverArea buildPeon;
	private MouseOverArea buildSwordsmanLevel1;
	private MouseOverArea buildSwordsmanLevel2;
	private MouseOverArea buildSwordsmanLevel3;

	private CommandHelper commandHelper;
	
	public BuildUnits(GameContainer container, String selection, CommandHelper commandHelper){
		this.container = container;
		this.selection = selection;
		this.commandHelper = commandHelper;
	}
	
	public void init()throws SlickException{
		archerLevel1Icon = new Image("res/Ingame/LowerBar/Icons/ArcherLevel1.png");
		archerLevel2Icon = new Image("res/Ingame/LowerBar/Icons/ArcherLevel2.png");
		archerLevel3Icon = new Image("res/Ingame/LowerBar/Icons/ArcherLevel3.png");
		catapultIcon = new Image("res/Ingame/LowerBar/Icons/Catapult.png");
		knightLevel1Icon = new Image("res/Ingame/LowerBar/Icons/KnightLevel1.png");
		knightLevel2Icon = new Image("res/Ingame/LowerBar/Icons/KnightLevel2.png");
		knightLevel3Icon = new Image("res/Ingame/LowerBar/Icons/KnightLevel3.png");
		peonIcon = new Image("res/Ingame/LowerBar/Icons/Peon.png");
		swordsmanLevel1Icon = new Image("res/Ingame/LowerBar/Icons/SwordsmanLevel1.png");
		swordsmanLevel2Icon = new Image("res/Ingame/LowerBar/Icons/SwordsmanLevel2.png");
		swordsmanLevel3Icon = new Image("res/Ingame/LowerBar/Icons/SwordsmanLevel3.png");
				
		buildPeon = new MouseOverArea(container, peonIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildSwordsmanLevel1 = new MouseOverArea(container, swordsmanLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildSwordsmanLevel2 = new MouseOverArea(container, swordsmanLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		buildSwordsmanLevel3 = new MouseOverArea(container, swordsmanLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildArcherLevel1 = new MouseOverArea(container, archerLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildArcherLevel2 = new MouseOverArea(container, archerLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		buildArcherLevel3 = new MouseOverArea(container, archerLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*333)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildKnightLevel1 = new MouseOverArea(container, knightLevel1Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
		
		buildKnightLevel2 = new MouseOverArea(container, knightLevel2Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*158)),this);
		
		buildKnightLevel3 = new MouseOverArea(container, knightLevel3Icon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*228)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);
		
		buildCatapult = new MouseOverArea(container, catapultIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*437)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*237)),this);
	}
	
	public String getSelection(){
		return this.selection;
	}
	
	public void setSelection(String selection){
		this.selection = selection;
	}
	
	public void render(Graphics g)throws SlickException{
		if(selection.equals("strongholdLevel1")){
			buildPeon.render(container, g);
			buildPeon.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("strongholdLevel2")){
			buildPeon.render(container, g);
			buildPeon.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("strongholdLevel3")){
			buildPeon.render(container, g);
			buildPeon.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("barrackLevel1")){
			buildSwordsmanLevel1.render(container, g);
			buildSwordsmanLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildArcherLevel1.render(container, g);
			buildArcherLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildKnightLevel1.render(container, g);
			buildKnightLevel1.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("barrackLevel2")){
			buildSwordsmanLevel1.render(container, g);
			buildSwordsmanLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildSwordsmanLevel2.render(container, g);
			buildSwordsmanLevel2.setNormalColor(new Color(1,1,1,0.7f));
			buildArcherLevel1.render(container, g);
			buildArcherLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildArcherLevel2.render(container, g);
			buildArcherLevel2.setNormalColor(new Color(1,1,1,0.7f));
			buildKnightLevel1.render(container, g);
			buildKnightLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildKnightLevel2.render(container, g);
			buildKnightLevel2.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("barrackLevel3")){
			buildSwordsmanLevel1.render(container, g);
			buildSwordsmanLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildSwordsmanLevel2.render(container, g);
			buildSwordsmanLevel2.setNormalColor(new Color(1,1,1,0.7f));
			buildSwordsmanLevel3.render(container, g);
			buildSwordsmanLevel3.setNormalColor(new Color(1,1,1,0.7f));
			buildArcherLevel1.render(container, g);
			buildArcherLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildArcherLevel2.render(container, g);
			buildArcherLevel2.setNormalColor(new Color(1,1,1,0.7f));
			buildArcherLevel3.render(container, g);
			buildArcherLevel3.setNormalColor(new Color(1,1,1,0.7f));
			buildKnightLevel1.render(container, g);
			buildKnightLevel1.setNormalColor(new Color(1,1,1,0.7f));
			buildKnightLevel2.render(container, g);
			buildKnightLevel2.setNormalColor(new Color(1,1,1,0.7f));
			buildKnightLevel3.render(container, g);
			buildKnightLevel3.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("forge")){
			buildCatapult.render(container, g);
			buildCatapult.setNormalColor(new Color(1,1,1,0.7f));
		}else if(selection.equals("farm")){
			
		}
	}
	
	public void componentActivated(AbstractComponent source) {
		
		if(source == buildPeon){
			commandHelper.createUnit("stronghold", "peon", 1);			
		}else if(source == buildSwordsmanLevel1){
			commandHelper.createUnit("barrack", "swordsman", 1);	
		}else if(source == buildSwordsmanLevel2){
			commandHelper.createUnit("barrack", "swordsman", 2);	
		}else if(source == buildSwordsmanLevel3){
			commandHelper.createUnit("barrack", "swordsman", 3);	
		}else if(source == buildArcherLevel1){
			commandHelper.createUnit("barrack", "archer", 1);	
		}else if(source == buildArcherLevel2){
			commandHelper.createUnit("barrack", "archer", 2);	
		}else if(source == buildArcherLevel3){
			commandHelper.createUnit("barrack", "archer", 3);	
		}else if(source == buildCatapult){
			commandHelper.createUnit("forge", "catapult", 1);
		}else if(source == buildKnightLevel1){
			commandHelper.createUnit("barrack", "knight", 1);	
		}else if(source == buildKnightLevel2){
			commandHelper.createUnit("barrack", "knight", 2);	
		}else if(source == buildKnightLevel3){
			commandHelper.createUnit("barrack", "knight", 3);	
		}
	}

	public MouseOverArea getBuildPeon() {
		return buildPeon;
	}

	public MouseOverArea getBuildSwordsmanLevel1() {
		return buildSwordsmanLevel1;
	}

	public MouseOverArea getBuildSwordsmanLevel2() {
		return buildSwordsmanLevel2;
	}

	public MouseOverArea getBuildSwordsmanLevel3() {
		return buildSwordsmanLevel3;
	}

	public MouseOverArea getBuildArcherLevel1() {
		return buildArcherLevel1;
	}

	public MouseOverArea getBuildArcherLevel2() {
		return buildArcherLevel2;
	}

	public MouseOverArea getBuildArcherLevel3() {
		return buildArcherLevel3;
	}

	public MouseOverArea getBuildKnightLevel1() {
		return buildKnightLevel1;
	}

	public MouseOverArea getBuildKnightLevel2() {
		return buildKnightLevel2;
	}

	public MouseOverArea getBuildKnightLevel3() {
		return buildKnightLevel3;
	}

	public MouseOverArea getBuildCatapult() {
		return buildCatapult;
	}
}