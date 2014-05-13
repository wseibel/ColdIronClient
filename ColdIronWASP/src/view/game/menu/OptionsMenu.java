package view.game.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class OptionsMenu {
	private  Image optionsMenu;
	private  GameContainer container;
	
	public OptionsMenu(GameContainer container){
		this.container = container;
	}
	
	public void init() throws SlickException {
		optionsMenu = new Image("res/Ingame/UpperBar/OptionsMenu.png");
	}
	
	public void draw(boolean optionsOpen){
		if (optionsOpen) {
			optionsMenu.draw(container.getWidth() - ((((float) container.getWidth()) / 1920) * 1297),
					container.getHeight() - ((((float) container.getHeight()) / 1080) * 771),
					(((float) container.getHeight()) / 1080) * 1.0f);
		}
	}
}
