package view.game;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.TrueTypeFont;

public class Fonts {
	
	private Font fontForStrength;
	private GameContainer container;
	
	public Fonts(GameContainer container){
		this.container = container;
	}

	public Font getFontForStrength() {
		
		fontForStrength = new TrueTypeFont(new java.awt.Font("Verdana",
				java.awt.Font.BOLD, (int)(container.getHeight()-((((float) container.getHeight())/1080)*1060))), true);
		
		return fontForStrength;
	}

	public void setFontForStrength(Font fontForStrength) {
		this.fontForStrength = fontForStrength;
	}

}
