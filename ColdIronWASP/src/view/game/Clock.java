package view.game;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Clock {
	
	public Clock(){
		
	}
	void render(GameContainer container, Graphics g){
		if(container.getWidth() == 800 && container.getHeight() == 450){
			g.drawString((new SimpleDateFormat("HH:mm").format(new Date())),
				container.getWidth()- ((((float) container.getWidth())/1920)*130),
				container.getHeight()-((((float) container.getHeight())/1080)*1075));
		}else if(container.getWidth() == 1280 && container.getHeight() == 720){
			g.drawString((new SimpleDateFormat("HH:mm:ss").format(new Date())),
					container.getWidth()- ((((float) container.getWidth())/1920)*130),
					container.getHeight()-((((float) container.getHeight())/1080)*1065));
		}else if(container.getWidth() == 1600 && container.getHeight() == 900){
			g.drawString((new SimpleDateFormat("HH:mm:ss").format(new Date())),
					container.getWidth()- ((((float) container.getWidth())/1920)*120),
					container.getHeight()-((((float) container.getHeight())/1080)*1060));
		}else if(container.getWidth() == 1920 && container.getHeight() == 1080){
			g.drawString((new SimpleDateFormat("HH:mm:ss").format(new Date())),
					container.getWidth()- ((((float) container.getWidth())/1920)*115),
					container.getHeight()-((((float) container.getHeight())/1080)*1060));
		}
	}
}
