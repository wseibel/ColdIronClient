package view.game.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameMenu {
	private Image gameMenu;
	private GameContainer container;
	
	public GameMenu(GameContainer container){
		this.container = container;
	}
	
	public void init() throws SlickException {
		gameMenu = new Image("res/Ingame/UpperBar/GameMenu.png");
	}
	
	public void draw(boolean menuOpen){
		if(menuOpen){
			gameMenu.draw(container.getWidth() - ((((float) container.getWidth()) / 1920) * 1297),
					container.getHeight() - ((((float) container.getHeight()) / 1080) * 771),
					(((float) container.getHeight()) / 1080) * 1.0f);
		}
	}
}
