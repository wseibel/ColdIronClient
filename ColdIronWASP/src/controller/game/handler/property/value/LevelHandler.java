package controller.game.handler.property.value;

import controller.game.handler.PropertyHandler;
import model.game.Archer;
import model.game.Barrack;
import model.game.Catapult;
import model.game.Farm;
import model.game.Forge;
import model.game.Knight;
import model.game.Peon;
import model.game.SectorElement;
import model.game.LevelHPElement;
import model.game.Stronghold;
import model.game.Swordsman;
import model.game.Tower;

public class LevelHandler extends PropertyHandler {
	public void setValue(String newValue, String oldValue,
			SectorElement sectorElement) {
		if (newValue != null){
			switch(Integer.valueOf(newValue)){
			case 1:
				if(sectorElement instanceof Stronghold){
					((Stronghold) sectorElement).setMaxHP(1000);
					((Stronghold) sectorElement).setBuildingTime(50);
				}
				if(sectorElement instanceof Farm){
					((Farm) sectorElement).setMaxHP(400);
					((Farm) sectorElement).setBuildingTime(20);
				}
				if(sectorElement instanceof Barrack){
					((Barrack) sectorElement).setMaxHP(500);
					((Barrack) sectorElement).setBuildingTime(30);
				}
				if(sectorElement instanceof Forge){
					((Forge) sectorElement).setMaxHP(800);
					((Forge) sectorElement).setBuildingTime(90);
				}
				if(sectorElement instanceof Tower){
					((Tower) sectorElement).setMaxHP(800);
					((Tower) sectorElement).setBuildingTime(40);
				}
				if(sectorElement instanceof Peon){
					((Peon) sectorElement).setMaxHP(20);
					((Peon) sectorElement).setBuildingTime(5);
				}
				if(sectorElement instanceof Swordsman){
					((Swordsman) sectorElement).setMaxHP(30);
					((Swordsman) sectorElement).setBuildingTime(10);
				}
				if(sectorElement instanceof Archer){
					((Archer) sectorElement).setMaxHP(20);
					((Archer) sectorElement).setBuildingTime(10);
				}
				if(sectorElement instanceof Knight){
					((Knight) sectorElement).setMaxHP(40);
					((Knight) sectorElement).setBuildingTime(20);
				}
				if(sectorElement instanceof Catapult){
					((Catapult) sectorElement).setMaxHP(200);
					((Catapult) sectorElement).setBuildingTime(60);
				}break;
				
			case 2:
				if(sectorElement instanceof Stronghold){
					((Stronghold) sectorElement).setMaxHP(2000);
					((Stronghold) sectorElement).setBuildingTime(70);
				}
				if(sectorElement instanceof Barrack){
					((Barrack) sectorElement).setMaxHP(750);
					((Barrack) sectorElement).setBuildingTime(40);
				}
				if(sectorElement instanceof Tower){
					((Tower) sectorElement).setMaxHP(1200);
					((Tower) sectorElement).setBuildingTime(60);
				}
				if(sectorElement instanceof Swordsman){
					((Swordsman) sectorElement).setMaxHP(70);
					((Swordsman) sectorElement).setBuildingTime(20);
				}
				if(sectorElement instanceof Archer){
					((Archer) sectorElement).setMaxHP(50);
					((Archer) sectorElement).setBuildingTime(20);
				}
				if(sectorElement instanceof Knight){
					((Knight) sectorElement).setMaxHP(80);
					((Knight) sectorElement).setBuildingTime(40);
				}break;

			case 3:
				if(sectorElement instanceof Stronghold){
					((Stronghold) sectorElement).setMaxHP(5000);
					((Stronghold) sectorElement).setBuildingTime(90);
				}
				if(sectorElement instanceof Barrack){
					((Barrack) sectorElement).setMaxHP(1000);
					((Barrack) sectorElement).setBuildingTime(50);
				}
				if(sectorElement instanceof Tower){
					((Tower) sectorElement).setMaxHP(2000);
					((Tower) sectorElement).setBuildingTime(80);
				}
				if(sectorElement instanceof Swordsman){
					((Swordsman) sectorElement).setMaxHP(100);
					((Swordsman) sectorElement).setBuildingTime(40);
				}
				if(sectorElement instanceof Archer){
					((Archer) sectorElement).setMaxHP(70);
					((Archer) sectorElement).setBuildingTime(40);
				}
				if(sectorElement instanceof Knight){
					((Knight) sectorElement).setMaxHP(120);
					((Knight) sectorElement).setBuildingTime(60);
				}break;
			}
			((LevelHPElement) sectorElement)
					.setLevel(Integer.valueOf(newValue));
		}
	}
}
