package view.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.MouseOverArea;

public class MarkElement {

	private GameContainer container;
	private String selection = "";
	private MouseOverArea markElement;
	private int width = 0;
	private int height = 0;
	private int x = 0;
	private int y = 0;
	private int maxHp = 0;
	private int currentHp = 0;
	private String maxHpS = "";
	private String currentHpS = "";
	private boolean inited = false;
	private boolean peonAllPressed = false;
	private boolean swordsmanAllPressed = false;
	private boolean exSwordsmanLv1 = false;
	private boolean exSwordsmanLv2 = false;
	private boolean exSwordsmanLv3 = false;
	private boolean archerAllPressed = false;
	private boolean exArcherLv1 = false;
	private boolean exArcherLv2 = false;
	private boolean exArcherLv3 = false;
	private boolean knightAllPressed = false;
	private boolean exKnightLv1 = false;
	private boolean exKnightLv2 = false;
	private boolean exKnightLv3 = false;
	private boolean catapultAllPressed = false;

	public MarkElement(GameContainer container){
		this.container = container;
	}

	public void init(){
		if(inited){
			width = markElement.getWidth();
			height = markElement.getHeight();
			x = markElement.getX();
			y = markElement.getY();

			inited = false;
		}

	}

	public void render(Graphics g){
		g.setColor(Color.white);
		
		if(selection.equals("wood") || selection.equals("stone") || selection.equals("iron") 
				&& currentHp >= 0){
			g.drawString(currentHpS.valueOf(currentHp), 
					container.getWidth()- ((((float) container.getWidth())/1920)*1340),
					container.getHeight()-((((float) container.getHeight())/1080)*54));
		}else if(!selection.equals("") && currentHp >= 0 && maxHp >= 0){
			g.drawString(currentHpS.valueOf(currentHp), 
					container.getWidth()- ((((float) container.getWidth())/1920)*1330),
					container.getHeight()-((((float) container.getHeight())/1080)*54));
			g.drawString(maxHpS.valueOf(maxHp), 
					container.getWidth()- ((((float) container.getWidth())/1920)*1330),
					container.getHeight()-((((float) container.getHeight())/1080)*30));
		}
		
		g.setColor(Color.green);
		g.setLineWidth(2);

		if(selection.equals("wood")){
			g.drawRoundRect(x, y, width, height, 0);
			
		}
		if(selection.equals("stone")){
			g.drawRoundRect(x, y, width, height, 0);
		}
		if(selection.equals("iron")){
			g.drawRoundRect(x, y, width, height, 0);
		}
		if(selection.equals("strongholdLevel1") || 
				selection.equals("strongholdLevel2") || 
				selection.equals("strongholdLevel3")){

			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1110)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1110)), 0);
		}
		if(selection.equals("farm")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1090)),
					width, height, 0);
		}
		if(selection.equals("forge")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1095)),
					width, height, 0);
		}
		if(selection.equals("barrackLevel1") || 
				selection.equals("barrackLevel2") || 
				selection.equals("barrackLevel3")){

			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1150)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1150)), 0);
		}
		if(selection.equals("towerLevel1")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1105)),
					width, height, 0);
		}

		if(selection.equals("towerLevel2")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1105)),
					width, height, 0);
		}
		if(selection.equals("towerLevel3")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1105)),
					width, height, 0);
		}
		if(selection.equals("peon")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("archerLv1")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("archerLv2")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("archerLv3")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("swordsmanLv1")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("swordsmanLv2")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("swordsmanLv3")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("knightLv1")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("knightLv2")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("knightLv3")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("catapult")){
			g.drawRoundRect(x, y+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
					width, height-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)), 0);
		}
		if(selection.equals("allButton")) {
			if(peonAllPressed) {
				g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1910)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
						+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
						-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
			}
			if(swordsmanAllPressed) {
				if(exSwordsmanLv1) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
				if(exSwordsmanLv2) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*890))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
				if(exSwordsmanLv3) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1850)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*790))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1866)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
			}
			if(archerAllPressed) {
				if(exArcherLv1) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1837)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
				if(exArcherLv2) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*890))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1837)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
				if(exArcherLv3) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1790)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*790))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1837)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1012)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
			}
			if(knightAllPressed) {
				if(exKnightLv1) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1834)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*982)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
				if(exKnightLv2) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*860))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1834)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*982)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
				if(exKnightLv3) {
					g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1700)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*730))
							+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1834)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*982)
							-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
				}
			}
			if(catapultAllPressed) {
				g.drawRect((int)(container.getWidth()- ((((float) container.getWidth())/1920)*1600)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*990))
						+(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108)),
						(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1771)),
						(int)(container.getHeight()-((((float) container.getHeight())/1080)*906)
						-(int)(container.getHeight()-((((float) container.getHeight())/1080)*1108))));
			}
		}
	}

	public void update(){

	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public MouseOverArea getMarkElement() {
		return markElement;
	}

	public void setMarkElement(MouseOverArea markElement) {
		this.markElement = markElement;
	}

	public boolean isInited() {
		return inited;
	}

	public void setInited(boolean inited) {
		this.inited = inited;
	}

	public void setPeonAllPressed(boolean peonAllPressed) {
		this.peonAllPressed = peonAllPressed;
	}

	public void setSwordsmanAllInfo(boolean swordsmanAllPressed, boolean lv1, boolean lv2, boolean lv3) {
		this.swordsmanAllPressed = swordsmanAllPressed;
		this.exSwordsmanLv1 = lv1;
		this.exSwordsmanLv2 = lv2;
		this.exSwordsmanLv3 = lv3;
	}

	public void setArcherAllInfo(boolean archerAllPressed, boolean lv1, boolean lv2, boolean lv3) {
		this.archerAllPressed = archerAllPressed;
		this.exArcherLv1 = lv1;
		this.exArcherLv2 = lv2;
		this.exArcherLv3 = lv3;
	}

	public void setKnightAllInfo(boolean knightAllPressed, boolean lv1, boolean lv2, boolean lv3) {
		this.knightAllPressed = knightAllPressed;
		this.exKnightLv1 = lv1;
		this.exKnightLv2 = lv2;
		this.exKnightLv3 = lv3;
	}

	public void setCatapultAllPressed(boolean catapultAllPressed) {
		this.catapultAllPressed = catapultAllPressed;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
}
