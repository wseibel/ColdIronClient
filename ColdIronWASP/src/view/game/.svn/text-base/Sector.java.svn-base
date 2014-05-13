package view.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class Sector  extends BasicGame{
	
	//map, ressources
	private Image ground;
	private Image tree1;
	private Image tree2;
	private Image stone1;
	private Image stone2;
	private Image iron;
	
	//buildings
	private Image stronghold;
	private Image farm;
	private Image barrack;
	private Image forge;
	private Image tower;
	
	//units
	private Image peon;
	private Image swordsman;
	private Image knight;
	private Image archer;
	private Image catapult;
	
	private String currentMap;
	private int heightOfImage;
	private int widthOfImage;
	private String playersColor;
	private int amountOfWood;
	
	private static AppGameContainer container;

	public Sector() {
		super("Map");
	}

	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		currentMap = "Aipus_Island";
		playersColor = "Orange";
		amountOfWood = 10;
		

		ground = new Image("res/Ingame/maps/"+currentMap+"/Ground.png");
		heightOfImage = ground.getHeight();
		widthOfImage = ground.getWidth();
		
		tree1 = new Image("res/Ingame/maps/"+currentMap+"/Tree1.png");
		tree2 = new Image("res/Ingame/maps/"+currentMap+"/Tree2.png");
		
		stronghold = new Image("res/Ingame/Player/"+playersColor+"Player/buildings/Stronghold.png");
		
		peon = new Image("res/Ingame/Player/"+playersColor+"Player/units/Peon.png");
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {

		drawTheMap();
		drawTheWood(amountOfWood);
		
		stronghold.draw((container.getScreenWidth()/2)-(stronghold.getHeight()/2), (container.getScreenHeight()/2)-(stronghold.getWidth()/2));
		
		peon.draw(10,10);
		peon.draw(peon.getWidth()+10,10);
		peon.draw(10+peon.getWidth()*2,10);
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
	}
	
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}
	
	public void drawTheMap(){
		
		int displayHeight = container.getScreenHeight();
		int displayWidth = container.getScreenWidth();
		
		int printHorizontal = (displayWidth / widthOfImage)+1;
		int printVertikal = (displayHeight / heightOfImage)+1;
		
		for(int i = 0; i < printVertikal; i++){
			
			for(int j = 0; j < printHorizontal; j++){
				
				ground.draw((j*widthOfImage), (i*heightOfImage));
			}
		}
		
	}
	
	public void drawTheWood(int amountOfTrees){
		
		int x1 = container.getScreenWidth()-(tree1.getWidth()*2+10);
		int x2 = x1+tree1.getWidth()/2;
		int y = 10;
		
		for(int i = 0; i < amountOfTrees; i++){
			tree1.draw(x1, y);
			tree2.draw(x2, y);
			y = i*(tree1.getHeight()/3);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			container = new AppGameContainer(new Sector());
			container.setDisplayMode(container.getScreenWidth(),
					container.getScreenHeight(), true);
			container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

		
	}

}
