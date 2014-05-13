package view.game.menu;

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

public class MenuButtons implements ComponentListener{
	
	private AppGameContainer app;
	private Image backToTheGame;
	private Image help;
	private Image menu;
	private Image options;
	private Image quitGame;
	private Image back;
	private Image res1;
	private Image res2;
	private Image res3;
	private Image res4;
	private GameContainer container;
	boolean menuOpen;
	boolean optionsOpen;
	boolean resolutionChanged;
	
	private MouseOverArea backToTheGameButton;
	private MouseOverArea helpButton;
	private MouseOverArea optionsButton;
	private MouseOverArea quitGameButton;
	private MouseOverArea backButton;
	private MouseOverArea resolution1;
	private MouseOverArea resolution2;
	private MouseOverArea resolution3;
	private MouseOverArea resolution4;
	
	public MenuButtons(GameContainer container, AppGameContainer app){
		this.container = container;
		this.app = app;
	}
	
	
	public void init()throws SlickException{
		menuOpen = false;
		optionsOpen= false;
		resolutionChanged = false;
		help  = new Image("res/Ingame/UpperBar/Buttons/Help.png");
		backToTheGame = new Image("res/Ingame/UpperBar/Buttons/BackToTheGame.png");
		options = new Image("res/Ingame/UpperBar/Buttons/Options.png");
		quitGame = new Image("res/Ingame/UpperBar/Buttons/QuitGame.png");
		back = new Image("res/Ingame/UpperBar/Buttons/Back.png");
		res1 = new Image("res/Ingame/UpperBar/Buttons/1920x1080.png");
		res2 = new Image("res/Ingame/UpperBar/Buttons/1600x900.png");
		res3 = new Image("res/Ingame/UpperBar/Buttons/1280x720.png");
		res4 = new Image("res/Ingame/UpperBar/Buttons/800x450.png");
		
		backToTheGameButton = new MouseOverArea(container, backToTheGame.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1107)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*390)),this);
		
		quitGameButton = new MouseOverArea(container, quitGame.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1107)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*500)),this);
		
		optionsButton = new MouseOverArea(container, options.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1107)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*575)),this);
		
		helpButton = new MouseOverArea(container, help.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1107)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*650)),this);
		
		backButton = new MouseOverArea(container, back.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1107)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*390)),this);
		
		resolution1 = new MouseOverArea(container, res1.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1250)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*630)),this);
		
		resolution2 = new MouseOverArea(container, res2.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1250)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*570)),this);
		
		resolution3 = new MouseOverArea(container, res3.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1250)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*510)),this);
		
		resolution4 = new MouseOverArea(container, res4.getScaledCopy((((float) container.getHeight())/1080)*1.5f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1250)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*450)),this);
		
	}
	
	public void render(Graphics g, boolean menuOpen, boolean optionsOpen) throws SlickException{
		if(menuOpen){
				backToTheGameButton.render(container, g);
				backToTheGameButton.setNormalColor(new Color(1,1,1,0.7f));
				quitGameButton.render(container, g);
				quitGameButton.setNormalColor(new Color(1,1,1,0.7f));
				optionsButton.render(container, g);
				optionsButton.setNormalColor(new Color(1,1,1,0.7f));
				helpButton.render(container, g);
				helpButton.setNormalColor(new Color(1,1,1,0.7f));
		}else if(optionsOpen){
			backButton.render(container, g);
			backButton.setNormalColor(new Color(1,1,1,0.7f));
			resolution1.render(container, g);
			if(container.getWidth()==1920 && container.getHeight()==1080){
				resolution1.setNormalColor(new Color(1,1,1,1.0f));
			}else {
				resolution1.setNormalColor(new Color(1,1,1,0.7f));
			}
			resolution2.render(container, g);
			if(container.getWidth()==1600 && container.getHeight()==900){
				resolution2.setNormalColor(new Color(1,1,1,1.0f));
			}else {
				resolution2.setNormalColor(new Color(1,1,1,0.7f));
			}
			resolution3.render(container, g);
			if(container.getWidth()==1280 && container.getHeight()==720){
				resolution3.setNormalColor(new Color(1,1,1,1.0f));
			}else {
				resolution3.setNormalColor(new Color(1,1,1,0.7f));
			}
			resolution4.render(container, g);
			if(container.getWidth()==800 && container.getHeight()==450){
				resolution4.setNormalColor(new Color(1,1,1,1.0f));
			}else {
				resolution4.setNormalColor(new Color(1,1,1,0.7f));
			}
		}
	}
	
	public boolean getOptionsOpen(){
		return this.optionsOpen;
	}
	
	public boolean getMenuOpen(){
		return this.menuOpen;
	}
	
	public boolean getResolutionChanged(){
		return this.resolutionChanged;
	}
	
	public void setOptionsOpen(boolean optionsOpen){
		this.optionsOpen = optionsOpen;
	}
	
	public void setMenuOpen(boolean menuOpen){
		this.menuOpen = menuOpen;
	}
	
	public void setResolutionChanged(boolean resolutionChanged){
		this.resolutionChanged = resolutionChanged;
	}
	
	public void componentActivated(AbstractComponent source) {
		if (source == backToTheGameButton) {
			this.menuOpen = false;
		}else if (source == quitGameButton) {
			System.exit(0);
		}else if (source == optionsButton) {
			this.menuOpen = false;
			this.optionsOpen = true;
		}else if (source == helpButton) {
			
		}else if (source == backButton) {
			this.optionsOpen = false;
			this.menuOpen = true;
		}else if (source == resolution1) {
			if(app.getWidth()!= 1920){
				try {
					app.setDisplayMode(1920, 1080, false);
					this.resolutionChanged = true;
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}else if (source == resolution2) {
			if(app.getWidth()!= 1600){
				try {
					app.setDisplayMode(1600, 900, false);
					this.resolutionChanged = true;
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}else if (source == resolution3) {
			if(app.getWidth()!= 1280){
				try {
					app.setDisplayMode(1280, 720, false);
					this.resolutionChanged = true;
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}else if (source == resolution4) {
			if(app.getWidth()!= 800){
				try {
					app.setDisplayMode(800, 450, false);
					this.resolutionChanged = true;
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
