package view.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Portrait {
	
	private static Image portrait;
	private static GameContainer container;
	
	public Portrait(Image portrait, GameContainer container){
		this.portrait = portrait;
		this.container = container;
	}

	public static void draw(){
		portrait.draw(container.getWidth()- ((((float) container.getWidth())/1920)*1412),
				container.getHeight()-((((float) container.getHeight())/1080)*208),
				(((float) container.getHeight())/1080)*1.0f);
	}
	public Image getPortrait() {
		return portrait;
	}
	public void setPortrait(Image portrait) {
		this.portrait = portrait;
	}
	public GameContainer getContainer() {
		return container;
	}
	public void setContainer(GameContainer container) {
		this.container = container;
	}

}
