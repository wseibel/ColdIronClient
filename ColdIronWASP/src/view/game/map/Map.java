package view.game.map;

import java.util.Iterator;

import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Resource;
import model.game.Sector;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import view.game.Portrait;

public class Map implements ComponentListener{
	
	private CIClient ciClient;
	private Sector currentSector;
	private String currentMap;
	private Image ground;
	private Image wood;
	private Image stone;
	private Image iron;
	private Image nothingSelected;
	private Image emptyPortrait;
	private Image woodPortrait;
	private Image stonePortrait;
	private Image ironPortrait;
	private String selection;
	private boolean selectRessPressed;
	private GameContainer container;
	private MouseOverArea chooseWood;
	private MouseOverArea chooseOtherElse;
	private MouseOverArea chooseStone;
	private MouseOverArea chooseIron;
	private Portrait portrait;
	private String maxWood = "";
	private String maxStone = "";
	private String maxIron = "";
	private int quantity;

	public Map(CIClient ciClient, Sector current, GameContainer container, String mapName) {
		this.currentMap = mapName;
		this.container = container;
		this.ciClient = ciClient;
		this.currentSector = current;
	}

	public void render(Graphics g) throws SlickException {

		drawTheMap();
		chooseOtherElse.render(container, g);
		
		Iterator<Resource> resIter = currentSector.iteratorOfSectorResources();
		//je nachdem welche ressourcen vorhanden sind, werden diese auch angezeigt
		while(resIter.hasNext()){
			Resource res = resIter.next();
			if(res.getType().equals("WOOD")){
				chooseWood.render(container, g);
				chooseWood.setNormalColor(new Color(1,1,1,0.9f));
			}else if(res.getType().equals("STONE")){
				chooseStone.render(container, g);
				chooseStone.setNormalColor(new Color(1,1,1,0.9f));
			}else if(res.getType().equals("IRON")){
				chooseIron.render(container, g);
				chooseIron.setNormalColor(new Color(1,1,1,0.9f));
			}
		}
		
		
		
	}
	
	public void init() throws SlickException {
		
		ground = new Image("res/Ingame/maps/"+currentMap+"/Ground.png");
		nothingSelected = new Image("res/Ingame/nothing.png");
		
		wood = new Image("res/Ingame/maps/"+currentMap+"/Wood.png");
		stone = new Image("res/Ingame/maps/"+currentMap+"/Stone.png");
		iron = new Image("res/Ingame/maps/"+currentMap+"/Iron.png");

		woodPortrait = new Image("res/Ingame/LowerBar/Portraits/Ressources/Wood.png");
		stonePortrait = new Image("res/Ingame/LowerBar/Portraits/Ressources/Stone.png");
		ironPortrait = new Image("res/Ingame/LowerBar/Portraits/Ressources/Iron.png");
		
		emptyPortrait = new Image("res/Ingame/LowerBar/Portraits/Empty.png");
		
		chooseOtherElse = new MouseOverArea(container, ground, 0, 
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)), 
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*10)), 
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*350)), this);

		chooseWood = new MouseOverArea(container, wood.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*350)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*950)),this);
		
		chooseStone = new MouseOverArea(container, stone.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*600)),this);
		
		chooseIron = new MouseOverArea(container, iron.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*600)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*900)),this);
		
	}

	public void update(int delta) throws SlickException {
		getInfoAboutMap();
	}
	
	public void drawTheMap(){
		
		int displayHeight = container.getScreenHeight();
		int displayWidth = container.getScreenWidth();
		
		int printHorizontal = (displayWidth / ground.getWidth())+1;
		int printVertikal = (displayHeight / ground.getHeight())+1;
		
		for(int i = 0; i < printVertikal; i++){
			
			for(int j = 0; j < printHorizontal; j++){
				
				ground.draw((j*ground.getWidth()), (i*ground.getHeight()));
			}
		}
		
	}
	
	public void getInfoAboutMap(){
		maxWood = "";
		maxStone = "";
		maxIron = "";
		
		Iterator<Resource> resIter = currentSector.iteratorOfSectorResources();
		//je nachdem welche ressourcen vorhanden sind, werden diese auch angezeigt
		while(resIter.hasNext()){
			Resource res = resIter.next();
			if(res.getType().equals("WOOD")){
				maxWood = res.getQuantity();
			}else if(res.getType().equals("STONE")){
				maxStone = res.getQuantity();
			}else if(res.getType().equals("IRON")){
				maxIron = res.getQuantity();
			}
		}
		
		
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if(source == chooseWood){
			portrait = new Portrait(woodPortrait, container);
			selection ="wood";
			quantity = Integer.parseInt(maxWood);
			selectRessPressed = true;
		}else if(source == chooseStone){
			portrait = new Portrait(stonePortrait, container);
			selection ="stone";
			quantity = Integer.parseInt(maxStone);
			selectRessPressed = true;
		}else if(source == chooseIron){
			portrait = new Portrait(ironPortrait, container);
			selection ="iron";
			quantity = Integer.parseInt(maxIron);
			selectRessPressed = true;
		}else if(source == chooseOtherElse){
			portrait = new Portrait(emptyPortrait, container);
			selection = "";
			selectRessPressed = true;
		}
	}
	
	public void clicked(int button, int x, int y, int clickCount, CommandHelper ch){
		if(button == 1){
			//right clicks for wood
			if(x >= chooseWood.getX() &&
					x <=  (chooseWood.getX() + chooseWood.getWidth()) &&
					y >= chooseWood.getY() &&
					y <=  (chooseWood.getY() + chooseWood.getHeight())){
				ch.movePeonsToResource("WOOD");
			}
			//right clicks for stone
			else if(x >= chooseStone.getX() &&
					x <=  (chooseStone.getX() + chooseStone.getWidth()) &&
					y >= chooseStone.getY() &&
					y <=  (chooseStone.getY() + chooseStone.getHeight())){
				ch.movePeonsToResource("STONE");
			}
			//right clicks for iron
			else if(x >= chooseIron.getX() &&
					x <=  (chooseIron.getX() + chooseIron.getWidth()) &&
					y >= chooseIron.getY() &&
					y <=  (chooseIron.getY() + chooseIron.getHeight())){
				ch.movePeonsToResource("IRON");
			}
		}
		
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public boolean isSelectRessPressed() {
		return selectRessPressed;
	}

	public void setSelectRessPressed(boolean selectRessPressed) {
		this.selectRessPressed = selectRessPressed;
	}

	public MouseOverArea getChooseWood() {
		return chooseWood;
	}

	public void setChooseWood(MouseOverArea chooseWood) {
		this.chooseWood = chooseWood;
	}

	public MouseOverArea getChooseStone() {
		return chooseStone;
	}

	public void setChooseStone(MouseOverArea chooseStone) {
		this.chooseStone = chooseStone;
	}

	public MouseOverArea getChooseIron() {
		return chooseIron;
	}

	public void setChooseIron(MouseOverArea chooseIron) {
		this.chooseIron = chooseIron;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
