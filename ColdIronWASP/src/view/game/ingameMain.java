package view.game;

import java.util.ArrayList;
import java.util.Iterator;

import mdes.slick.sui.Label;
import model.game.Alliance;
import model.game.Building;
import model.game.CIClient;
import model.game.CommandHelper;
import model.game.Game;
import model.game.Peon;
import model.game.Resource;
import model.game.Sector;
import model.game.SectorElement;
import model.game.Unit;
import model.game.User;
import model.game.UserAssets;
import model.lobby.ServerConnection;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;

import java.awt.Container;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.Color;

import ai.controlCenter.AIMain;
import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;

import view.game.allystartsector.AllyStartSectorWindow;
import view.game.buildings.*;
import view.game.map.Map;
import view.game.units.*;
import view.game.menu.*;
import view.game.buttons.*;


public class ingameMain extends BasicGame implements ComponentListener{

	//new stuff
	private GameMenu gameMenu;
	private OptionsMenu optionsMenu;
	private MenuButtons menuButtons;
	private PlaceBuildings placeBuildingButtons;
	private UpgradeBuildings upgradeBuildingButtons;
	private BuildUnits buildUnitButtons;
	private CommandUnits commandUnitButtons;
	private Clock clock;
	private SelectUnits selectUnitButtons;
	private ButtonToolTips buttonToolTips;
	
	public SelectUnits getSelectUnitButtons() {
		return selectUnitButtons;
	}

	private static AppGameContainer app;
	boolean resolutionChanged = false;

	private static CIClient ciClient;
	public CIClient getCiClient() {
		return ciClient;
	}
	public void setCiClient(CIClient ciClient) {
		this.ciClient = ciClient;
	}
	private CommandHelper ch;
	
	private User currentUser;
	public User getCurrentUser() {
		return currentUser;
	}
	private Sector startSector;
	private Sector currentSector = null;
	public Sector getCurrentSector() {
		return currentSector;
	}
	//direction for sectors
	private int direction = 0;
	private boolean changeSector = false;

	String selection2 = "";
	String selection = "";
	public void setSelection(String selection){
		this.selection = selection;
	}
	private String selectToMark = "";
	String selectedColor = "";
	boolean menuOpen;

	boolean getMenuOpen(){
		return this.menuOpen;
	}
	void setMenuOpen(boolean menuOpen){
		this.menuOpen = menuOpen;
	}

	boolean chatOn;
	boolean alliesOpen;
	boolean optionsOpen;
	boolean helpOpen;

	private Image directionRight;
	private Image directionLeft;
	private Image directionTop;
	private Image directionBottom;
	private Image upperBar;
	private Image lowerBar;

	//some button images
	private Image allies;
	private Image chat;
	private Image menu;

	//buttons for upper bar
	private MouseOverArea alliesButton;
	private MouseOverArea chatButton;
	private MouseOverArea menuButton;

	private MouseOverArea nextRightSector;
	private MouseOverArea nextLeftSector;
	private MouseOverArea nextTopSector;
	private MouseOverArea nextBottomSector;

	//stuff concerning the minimap
	private MouseOverArea sectorFields[];
	private ArrayList<Sector> sectors = new ArrayList<Sector>();
	private Image imgOneField; // the displayed icon for a sector-square
	int XtoStartFrom; // the X-coordinate where to start drawing the minimap
	int YtoStartFrom; // the Y-coordinate where to start drawing the minimap
	int fieldLength; // the length of one displayed sector-square
	int minimapWidth;
	int minimapHeight;
	boolean changeSectorbyMinimap;
	boolean stillNeedFirstUserAsset = false;
	boolean finishedSettingPlayerColors = false;

	private String currentMap;
	private String playersColor;
	// Resources
	private TextField textWood;
	private int amountOfWood;
	private TextField textStone;
	private int amountOfStone;
	private TextField textIron;
	private int amountOfIron;
	private TextField textUnit;
	private int amountOfUnit;
	private int changedResolution = 0;

	private Chat chatWindow;

	private int keyPressedMenuCount = 2;
	private int keyPressedChatCount = 2;
	private int keyPressedAlliesCount = 2;

	//NEW
	private PeonView peon;
	private ArcherView archer;
	private Catapult catapult;
	private Swordsman swordsman;
	private Knight knight;
	private FarmView farm;
	private ForgeView forge;
	private TowerView tower;
	private BarrackView barrack;
	private StrongholdView stronghold;
	private Map map;
	private Portrait portrait;
	private Image emptyPortrait;
	private AllyStartSectorWindow allyStartSectorWindow;
	private MarkElement markEl;
	private MouseOverArea markedArea;


	private boolean ingameStart = false;
	private boolean initOnce = true;
	private boolean AIgotHandled = true;

	private boolean anyAllButtonPressed = false;
	private boolean selectSwordsmanAllPressed = false;
	private boolean exSwordsmanLv1 = false;
	private boolean exSwordsmanLv2 = false;
	private boolean exSwordsmanLv3 = false;
	private boolean selectPeonAllPressed = false;
	private boolean selectArcherAllPressed = false;
	private boolean exArcherLv1 = false;
	private boolean exArcherLv2 = false;
	private boolean exArcherLv3 = false;
	private boolean selectKnightAllPressed = false;
	private boolean exKnightLv1 = false;
	private boolean exKnightLv2 = false;
	private boolean exKnightLv3 = false;
	private boolean selectCatapultAllPressed = false;
	
	public ingameMain(CIClient ciClient){
		super("ingame");
		this.ciClient = ciClient;
	}

	public Sector showYourOwnSector(CIClient ciClient){

		Sector ownSector = null;

		Iterator<User> userIter = ciClient.getGame().iteratorOfUser();
		while(userIter.hasNext()){
			User player = userIter.next();
			if(player.getStartUser()){
				ownSector = player.getUserAssets().getStartSector();
			}
		}

		return ownSector;
	}


	public void initAllyStartSectorWindow(GameContainer container, CIClient ciClient) throws SlickException {
		if(allyStartSectorWindow!=null) {
			allyStartSectorWindow = new view.game.allystartsector.AllyStartSectorWindow(container, ciClient, allyStartSectorWindow.isStarted());
			allyStartSectorWindow.init();
		}
	}

	public void initBuildings(GameContainer container) throws SlickException{

		farm = new FarmView(ciClient, currentSector, container, playersColor, currentUser);
		farm.init();
		forge = new ForgeView(ciClient, currentSector, container, playersColor, currentUser);
		forge.init();
		tower = new TowerView(ciClient, currentSector, container, playersColor, currentUser);
		tower.init();
		barrack = new BarrackView(ciClient, currentSector, container, playersColor, currentUser);
		barrack.init();
		stronghold = new StrongholdView(ciClient, currentSector, container, playersColor, currentUser);
		stronghold.init();

	}

	public void initUnits(GameContainer container) throws SlickException{

		peon = new PeonView(ciClient, currentSector, container, playersColor, selectUnitButtons, currentUser);
		peon.init();
		peon.setCh(ch);
		archer = new ArcherView(ciClient, currentSector, container, playersColor, selectUnitButtons);
		archer.init();
		swordsman = new Swordsman(ciClient, currentSector, container, playersColor, selectUnitButtons);
		swordsman.init();
		knight = new Knight(ciClient, currentSector, container, playersColor, selectUnitButtons);
		knight.init();
		catapult = new Catapult(ciClient, currentSector, container, playersColor, selectUnitButtons);
		catapult.init();

	}

	public void initMap(CIClient ciClient, Sector currentSector, GameContainer container, String mapName) throws SlickException{

		map = new Map(ciClient, currentSector, container, mapName);
		map.init();

	}

	public void initMinimap(GameContainer container) {

		if (ciClient != null) {
			if (ciClient.getGame() != null) {
				if (ciClient.getGame().getMap() != null) {
					if (ciClient.getGame().getMap().iteratorOfSector() != null) {

						int maxXCount = 0; //stores max number of horizontal neighboring sectors
						int maxYCount = 0; //stores max number of vertical neighboring sectors
						Sector s;
						int i = 0;
						Iterator<model.game.Sector> t = ciClient.getGame()
								.getMap().iteratorOfSector();
						while (t.hasNext()) {
							s = ((model.game.Sector) t.next());
							if (s.getXCoordinate() > maxXCount) {
								maxXCount = s.getXCoordinate();
							}
							if (s.getYCoordinate() > maxYCount) {
								maxYCount = s.getYCoordinate();
							}
							i++;

						}
						maxXCount++;
						maxYCount++;

						minimapWidth = 	(int) ((float) container.getWidth() / 1920 * 395); 
						minimapHeight = (int) ((float) container.getHeight() / 1080 * 248);
						XtoStartFrom = (int) ((float) container.getWidth() / 1920 * 30); 
						YtoStartFrom = (int) (container.getHeight() - (minimapHeight + ((float) container.getWidth() / 1920 * 11)));

						if (maxYCount >= maxXCount) {
							// the height is limiting the fitting of the minimapsectors into the minimap
							fieldLength = minimapHeight/(maxYCount); 
							XtoStartFrom = XtoStartFrom + ((minimapWidth - (fieldLength * (maxXCount))) / 2);
						} else {
							// the minimapsectors have more height than width
							if (maxXCount/maxYCount > (minimapWidth/minimapHeight)) {
								// the width is limiting the fitting of the minimapsectors into the minimap
								fieldLength = minimapWidth/(maxXCount);
								YtoStartFrom = YtoStartFrom + ((minimapHeight - (fieldLength * (maxYCount))) / 2);
							} else {
								// the height is limiting the fitting of the minimapsectors into the minimap
								fieldLength = minimapHeight/(maxYCount);
								XtoStartFrom = XtoStartFrom + ((minimapWidth - (fieldLength * (maxXCount))) / 2);
							}
						}
						sectorFields = new MouseOverArea[i];
						t = ciClient.getGame().getMap().iteratorOfSector();
						i = 0;
						while (t.hasNext()) {
							s = ((model.game.Sector) t.next());
							sectors.add(s);

							sectorFields[i] = new MouseOverArea(container, imgOneField.getScaledCopy(fieldLength, fieldLength),
									(XtoStartFrom + s.getXCoordinate() * fieldLength),
									(YtoStartFrom + s.getYCoordinate() * fieldLength), this);

							i++;
						}
					} else {
						System.out
						.println("ciClient.getGame().getMap().iteratorOfSector() == null");
					}
				} else {
					System.out.println("ciClient.getGame().getMap() == null");
				}
			} else {
				System.out.println("ciClient.getGame() == null");
			}
		} else {
			System.out.println("ciClient == null");
		}
	}

	public void initResource() {
		// With reservation, because we had no such events
		final UserAssets userAssets = currentUser.getUserAssets();
		// Units
		userAssets.addPropertyChangeListener(UserAssets.PROPERTY_UNITS, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				amountOfUnit = userAssets.sizeOfUnits();
			}
		});

		// Resources
		amountOfWood = amountOfStone = amountOfIron = 0;
		userAssets.addPropertyChangeListener(UserAssets.PROPERTY_COLLECTED_RESOURCES, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				final Resource resource = (Resource) evt.getNewValue();
				resource.addPropertyChangeListener(Resource.PROPERTY_QUANTITY, new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt2) {
						if("WOOD".equals(resource.getType())) {
							amountOfWood += ((Integer) evt2.getOldValue() - (Integer) evt2.getNewValue());
							return;
						}
						if("STONE".equals(resource.getType())) {
							amountOfStone += ((Integer) evt2.getOldValue() - (Integer) evt2.getNewValue());
							return;
						}
						if("IRON".equals(resource.getType())) {
							amountOfIron += ((Integer) evt2.getOldValue() - (Integer) evt2.getNewValue());
							return;
						}
					}
				});
			}
		});
	}

	public void initIngameMain(GameContainer container) throws SlickException {
		currentSector = new Sector();
		startSector = showYourOwnSector(ciClient);
		currentSector = startSector;
		Iterator<User> it = ciClient.getGame().iteratorOfUser();
		while (it.hasNext()) {
			User user = it.next();
			if (user.getUserAssets() != null
					&& ciClient.getCurrentPlayer().equals(
							user.getNickname())) {
				System.out.println("user found: " + user.getNickname());
				currentUser = user;
			}
		}
		markEl = new MarkElement(container);


		menuOpen = false;
		helpOpen = false;
		optionsOpen = false;

		currentMap = ciClient.getGame().getMap().getName();
		
		if (currentSector.getUserAssets() != null) {
			playersColor = currentSector.getUserAssets().getColor(); // never happened till now
		} else {
			//setting playersColor to Orange because no UserAssets for currentSector in model yet (avoid null)
			playersColor = "Orange";
			stillNeedFirstUserAsset = true; // will be handled within the update of the minimap-color
		}
		
		ch = new CommandHelper();
		ch.setMain(this);
		ch.dummyOn = false;
		ch.setAIOn(allyStartSectorWindow.getAi());
		

		//new Stuff
		gameMenu = new GameMenu(container);
		gameMenu.init();
		optionsMenu = new OptionsMenu(container);
		optionsMenu.init();
		menuButtons = new MenuButtons(container, app);
		menuButtons.init();
		placeBuildingButtons = new PlaceBuildings(container, playersColor,currentSector,currentUser, allyStartSectorWindow.getAi());
		placeBuildingButtons.init();
		upgradeBuildingButtons = new UpgradeBuildings(container, selection, ch,currentSector);
		upgradeBuildingButtons.init();
		buildUnitButtons = new BuildUnits(container, selection, ch);
		buildUnitButtons.init();
		commandUnitButtons = new CommandUnits(container, selection, ch,currentSector, currentUser);
		commandUnitButtons.init();
		clock = new Clock();
		selectUnitButtons = new SelectUnits(container, playersColor, ciClient, currentSector, currentUser);
		selectUnitButtons.init();

		initMap(ciClient, currentSector, container, currentMap);


		initBuildings(container);

		//initResource();




		initUnits(container);

		emptyPortrait = new Image("res/Ingame/LowerBar/Portraits/Empty.png");
		portrait = new Portrait(emptyPortrait, container);



		chatWindow = new Chat(container, ciClient);
		chatWindow.init();

		directionRight = new Image("res/Ingame/right.png");
		directionLeft = new Image("res/Ingame/left.png");
		directionTop = new Image("res/Ingame/top.png");
		directionBottom = new Image("res/Ingame/bot.png");
		//assign images
		upperBar = new Image("res/Ingame/UpperBar/UpperBar.png");
		lowerBar = new Image("res/Ingame/LowerBar/LowerBar.png");
		allies = new Image("res/Ingame/UpperBar/Buttons/Allies.png"); 
		chat = new Image("res/Ingame/UpperBar/Buttons/Chat.png");
		menu = new Image("res/Ingame/UpperBar/Buttons/Menu.png");

		//mouseoverareas for choosing the sectors
		nextRightSector = new MouseOverArea(container, directionRight.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*40)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*600)),this);
		nextLeftSector = new MouseOverArea(container, directionLeft.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1917)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*600)),this);
		nextTopSector = new MouseOverArea(container, directionTop.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)),this);
		nextBottomSector = new MouseOverArea(container, directionBottom.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*286)),this);

		initButtons(container);

		// prepare the minimap
		imgOneField = new Image("res/Ingame/maps/" + currentMap + "/Ground.png");
		initMinimap(container);
		
		//init the button tool tips
		buttonToolTips = new ButtonToolTips(container, upgradeBuildingButtons, buildUnitButtons, commandUnitButtons, ciClient, selection);
		buttonToolTips.init();
		
				
	}

	public void init(GameContainer container) throws SlickException {	

		allyStartSectorWindow = new AllyStartSectorWindow(container, ciClient);
		allyStartSectorWindow.init();
	}

	int loopForPrintStackTrace = 0;
	
	
	public void update(GameContainer container, int delta) throws SlickException{
	try {
		if(initOnce && allyStartSectorWindow.isStarted()){
			initIngameMain(container);
			ingameStart = true;
			initOnce = false;
			AIgotHandled = !allyStartSectorWindow.getAi(); // set to false if there is an AI that still needs to be handled


			// start AI
		}
		if (!AIgotHandled && finishedSettingPlayerColors) {
			AIMain mainAi = new AIMain(ciClient, new AIHelper(),
					new AiModelAnalyzer(ciClient), currentUser);

			if (!mainAi.isAlive()) {
				ch.init(); // includes setting the commandHelper to the chainMaster
				// starts AI
				mainAi.start();
			}
			AIgotHandled = true;	
		}

		if(ingameStart){
			
			menuOpen = menuButtons.getMenuOpen();
			optionsOpen = menuButtons.getOptionsOpen();
			resolutionChanged = menuButtons.getResolutionChanged();
			upgradeBuildingButtons.countBuildings();
			
			if(!menuOpen && !optionsOpen || resolutionChanged){
				
				//new Stuff
				placeBuildingButtons.update();
				selectUnitButtons.update();
				commandUnitButtons.update();

				if(upgradeBuildingButtons.getUpgradeBuildingPressed()){
					selection2 = selection = upgradeBuildingButtons.getSelection();
					upgradeBuildingButtons.setUpgradeBuildingPressed(false);
				}

				if(commandUnitButtons.getCommandUnitPressed()){
					selection2 = selection = commandUnitButtons.getSelection();
					commandUnitButtons.setCommandUnitPressed(false);
				}

				if(selectUnitButtons.getSelectUnitPressed()){
					selection2 = selection = selectUnitButtons.getSelection();
					selectUnitButtons.setSelectUnitPressed(false);
				}
				//new stuff
				if(map.isSelectRessPressed()){
					//get the right width and height for the marked selection
					if(map.getSelection().equals("wood")){
						markedArea = map.getChooseWood();
						markEl.setCurrentHp(map.getQuantity());
						markEl.setInited(true);
					}
					if(map.getSelection().equals("stone")){
						markedArea = map.getChooseStone();
						markEl.setCurrentHp(map.getQuantity());
						markEl.setInited(true);
					}
					if(map.getSelection().equals("iron")){
						markedArea = map.getChooseIron();
						markEl.setCurrentHp(map.getQuantity());
						markEl.setInited(true);
					}//if u click on map, the mark will be deleted
					else if(map.getSelection().equals("")){
						markEl.setSelection(map.getSelection());
					}
					selection2 = selection = map.getSelection();
					selectToMark = map.getSelection();
					map.setSelectRessPressed(false);

				}

				if(stronghold.isSelectStrongholdPressed()){
					selection = stronghold.getSelection();
					markEl.setCurrentHp(stronghold.getCurrentHP());
					markEl.setMaxHp(stronghold.getMaxHealth());
					selectToMark = selection2 = selection;
					markedArea = stronghold.getChooseStronghold();
					markEl.setInited(true);
					stronghold.setSelectStrongholdPressed(false);
				}

				if(forge.isSelectForgePressed()){
					selection = forge.getSelection();
					markEl.setCurrentHp(forge.getCurrentHP());
					markEl.setMaxHp(forge.getMaxHealth());
					selectToMark = selection2 = selection;
					markedArea = forge.getChooseForge();
					markEl.setInited(true);
					forge.setSelectForgePressed(false);
				}

				if(farm.isSelectFarmPressed()){
					markedArea = farm.getChooseFarm();
					markEl.setCurrentHp(farm.getCurrentHP());
					markEl.setMaxHp(farm.getMaxHealth());
					markEl.setInited(true);
					selection = farm.getSelection();
					selectToMark = selection2 = selection;
					farm.setSelectFarmPressed(false);
				}

				if(tower.isSelectTowerPressed()){
					//get the right width and height for the marked selection
					if(tower.getSelection().equals("towerLevel1")){
						markedArea = tower.getChooseTowerLv1();
						markEl.setCurrentHp(tower.getCurrentHp());
						markEl.setMaxHp(tower.getMaxHp());
					}
					if(tower.getSelection().equals("towerLevel2")){
						markedArea = tower.getChooseTowerLv2();
						markEl.setCurrentHp(tower.getCurrentHp());
						markEl.setMaxHp(tower.getMaxHp());
					}
					if(tower.getSelection().equals("towerLevel3")){
						markedArea = tower.getChooseTowerLv3();
						markEl.setCurrentHp(tower.getCurrentHp());
						markEl.setMaxHp(tower.getMaxHp());
					}
					selection2 = selection = tower.getSelection();
					markEl.setInited(true);
					selectToMark = tower.getSelection();
					tower.setSelectTowerPressed(false);
				}

				if(barrack.isSelectbarrackPressed()){
					markedArea = barrack.getChooseBarrack();
					markEl.setCurrentHp(barrack.getCurrentHp());
					markEl.setMaxHp(barrack.getMaxHp());
					markEl.setInited(true);
					selection2 = selection = barrack.getSelection();
					selectToMark = selection;
					barrack.setSelectbarrackPressed(false);

				}

				if(peon.isSelectPeonPressed()){
					markedArea = peon.getChoosePeon();
					markEl.setCurrentHp(peon.getCurrentHP());
					markEl.setMaxHp(peon.getMaxHealth());
					markEl.setInited(true);
					selection = peon.getSelection();
					selectToMark = selection2 = selection;
					peon.setSelectPeonPressed(false);
					selectPeonAllPressed = false;
				}
				
				if(catapult.isSelectCatapultPressed()){
					markedArea = catapult.getChooseCatapult();
					markEl.setCurrentHp(catapult.getCurrentHP());
					markEl.setMaxHp(catapult.getMaxHealth());
					markEl.setInited(true);
					selection = catapult.getSelection();
					selectToMark = selection2 = selection;
					catapult.setSelectCatapultPressed(false);
					selectCatapultAllPressed = false;
				}

				if(archer.isSelectArcherPressed()){
					if(archer.getSelectToMark().equals("archerLv1")){
						markedArea = archer.getChooseArcherLv1();
						markEl.setCurrentHp(archer.getCurrentHp());
						markEl.setMaxHp(archer.getMaxHp());
					}
					if(archer.getSelectToMark().equals("archerLv2")){
						markedArea = archer.getChooseArcherLv2();
						markEl.setCurrentHp(archer.getCurrentHp());
						markEl.setMaxHp(archer.getMaxHp());
					}
					if(archer.getSelectToMark().equals("archerLv3")){
						markedArea = archer.getChooseArcherLv3();
						markEl.setCurrentHp(archer.getCurrentHp());
						markEl.setMaxHp(archer.getMaxHp());
					}
					markEl.setInited(true);
					selectToMark = archer.getSelectToMark();
					selection = selection2 = archer.getSelection();
					archer.setSelectArcherPressed(false);
				}

				if(swordsman.isSelectSwordsmanPressed()){
					if(swordsman.getSelectToMark().equals("swordsmanLv1")){
						markedArea = swordsman.getChooseSwordsmanLv1();
						markEl.setCurrentHp(swordsman.getCurrentHp());
						markEl.setMaxHp(swordsman.getMaxHp());
					}
					if(swordsman.getSelectToMark().equals("swordsmanLv2")){
						markedArea = swordsman.getChooseSwordsmanLv2();
						markEl.setCurrentHp(swordsman.getCurrentHp());
						markEl.setMaxHp(swordsman.getMaxHp());
					}
					if(swordsman.getSelectToMark().equals("swordsmanLv3")){
						markedArea = swordsman.getChooseSwordsmanLv3();
						markEl.setCurrentHp(swordsman.getCurrentHp());
						markEl.setMaxHp(swordsman.getMaxHp());
					}
					markEl.setInited(true);
					selectToMark = swordsman.getSelectToMark();
					selection2 = selection = swordsman.getSelection();
					swordsman.setSelectSwordsmanPressed(false);
				}

				if(knight.isSelectKnightPressed()){
					if(knight.getSelectToMark().equals("knightLv1")){
						markedArea = knight.getChooseKnightLv1();
						markEl.setCurrentHp(knight.getCurrentHp());
						markEl.setMaxHp(knight.getMaxHp());
						
					}
					if(knight.getSelectToMark().equals("knightLv2")){
						markedArea = knight.getChooseKnightLv2();
						markEl.setCurrentHp(knight.getCurrentHp());
						markEl.setMaxHp(knight.getMaxHp());
					}
					if(knight.getSelectToMark().equals("knightLv3")){
						markedArea = knight.getChooseKnightLv3();
						markEl.setCurrentHp(knight.getCurrentHp());
						markEl.setMaxHp(knight.getMaxHp());
					}
					markEl.setInited(true);
					selectToMark = knight.getSelectToMark();
					selection2 = selection = knight.getSelection();
					knight.setSelectKnightPressed(false);
				}

				if(anyAllButtonPressed) {
					markedArea = selectUnitButtons.getPeonAll();
					markEl.setInited(true);
					selectToMark = selection = "allButton";
					anyAllButtonPressed = false;
				}
				
				upgradeBuildingButtons.setSelection(selection);
				buildUnitButtons.setSelection(selection);
				commandUnitButtons.setSelection(selection2);
				selectUnitButtons.setSelection(selection);
				markEl.setPeonAllPressed(selectPeonAllPressed);
				markEl.setSwordsmanAllInfo(selectSwordsmanAllPressed, exSwordsmanLv1, exSwordsmanLv2, exSwordsmanLv3);
				markEl.setArcherAllInfo(selectArcherAllPressed, exArcherLv1, exArcherLv2, exArcherLv3);
				markEl.setKnightAllInfo(selectKnightAllPressed, exKnightLv1, exKnightLv2, exKnightLv3);
				markEl.setCatapultAllPressed(selectCatapultAllPressed);
				//its necessary, cause the selection was always ""
				//but i need to change the selection for marklement by Selecting units in menu
				if(!selectToMark.equals("") && markedArea != null){
					markEl.setSelection(selectToMark);
					markEl.setMarkElement(markedArea);
				}
				buttonToolTips.update(container, delta);
				if(markEl.isInited()){
					markEl.init();
				}

				if(changeSector){
					sectorEnterable(direction, container);
				}
				
				if(changeSectorbyMinimap){
					sectorEnterableByMinimap(container);
				}

				if(resolutionChanged){

					initMap(ciClient, currentSector, container, currentMap);

					initBuildings(container);

					initUnits(container);

					initMinimap(container);

					chatWindow.update();

					//mouseoverareas for choosing the sectors
					nextRightSector = new MouseOverArea(container, directionRight.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*40)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*600)),this);
					nextLeftSector = new MouseOverArea(container, directionLeft.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1917)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*600)),this);
					nextTopSector = new MouseOverArea(container, directionTop.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*1020)),this);
					nextBottomSector = new MouseOverArea(container, directionBottom.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
							(int)(container.getWidth()- ((((float) container.getWidth())/1920)*1000)),
							(int)(container.getHeight()-((((float) container.getHeight())/1080)*286)),this);

					initButtons(container);

					//new Stuff
					placeBuildingButtons.init();
					menuButtons.init();
					menuButtons.setOptionsOpen(true);
					upgradeBuildingButtons.init();
					buildUnitButtons.init();
					commandUnitButtons.init();
					selectUnitButtons.init();

					if(app.isFullscreen()){
						app.setFullscreen(true);
					}
					menuButtons.setResolutionChanged(false);
				}

				peon.update(delta);
				archer.update(delta);
				swordsman.update(delta);
				knight.update(delta);
				catapult.update(delta);
				tower.update(delta);
				forge.update(delta);
				farm.update(delta);
				stronghold.update(delta);
				barrack.update(delta);
				map.update(delta);
				
			}

		}
	} catch (Exception e) {
		// don`t spam the console 
		if (loopForPrintStackTrace++ == 300) { 
			System.err.println("Ingame.update() got interrupted");
			e.printStackTrace();
			loopForPrintStackTrace = 0;
		}
		
	}
	}	
	
	
	int loopForPrintStackTrace2 = 0;
	
	@SuppressWarnings("unchecked")
	public void render(GameContainer container, Graphics g) throws SlickException{
	try {
		if(!allyStartSectorWindow.isStarted()) {
			allyStartSectorWindow.render(g);
		}
		else {

		}

		if(ingameStart){
			
			// set color for user assets (but only once)
			if (!finishedSettingPlayerColors) {
				String color = null;
				Iterator<UserAssets> iteratorOfUserAssets = ciClient.getGame().iteratorOfUserAssets();

				for (int i = 0; iteratorOfUserAssets.hasNext(); i++) {
					switch (i) {
					case 0:
						color = "Blue"; 
						break;
					case 1:
						color = "Green";
						break;
					case 2:
						color = "Red";
						break;
					case 3:
						color = "Orange";
						break;
					case 4:
						color = "Yellow";
						break;
					case 5:
						color = "Purple";
						break;
					default:
						color = "Purple"; // as agreed we stick to our 6 colors 
					}
					iteratorOfUserAssets.next().setColor(color);
				}
				finishedSettingPlayerColors = true;
			}
			
			//draw the map + resources
			map.render(g);

			upperBar.draw(0, 0, (((float) container.getHeight())/1080)*1.0f);
			if(changedResolution != container.getWidth()) {
				if(container.getWidth() == 800 && container.getHeight() == 450) {
					textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
					textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
					textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
					textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
					changedResolution = container.getWidth();
				} else if(container.getWidth() == 1280 && container.getHeight() == 720) {
					textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
					textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
					textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
					textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
					changedResolution = container.getWidth();
				} else if(container.getWidth() == 1600 && container.getHeight() == 900) {
					textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
					textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
					textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
					textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
					changedResolution = container.getWidth();
				} else if(container.getWidth() == 1920 && container.getHeight() == 1080) {
					textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
					textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
					textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
					textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
							(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
							(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
					changedResolution = container.getWidth();
				}
			}
			
			UserAssets currentUserUserAssets = currentUser.getUserAssets();
			Iterator<Resource> iteratorOfCollectedResources = currentUserUserAssets.iteratorOfCollectedResources();
			if(!iteratorOfCollectedResources.hasNext()) {
				// No collected resources found
				amountOfWood = 0;
				amountOfStone = 0;
				amountOfIron = 0;
			}
			else {
				while(iteratorOfCollectedResources.hasNext()) {
					Resource next2 = iteratorOfCollectedResources.next();
					if(next2.getType().equals("WOOD")) {
						try {
							amountOfWood = Integer.parseInt(next2.getQuantity());
						} catch (NumberFormatException e) {
							System.out.println("Error: Unable to parse resource quantity");
							amountOfWood = 0;
						}
					}
					if(next2.getType().equals("STONE")) {
						try {
							amountOfStone = Integer.parseInt(next2.getQuantity());
						} catch (NumberFormatException e) {
							System.out.println("Error: Unable to parse resource quantity");
							amountOfStone = 0;
						}
					}
					if(next2.getType().equals("IRON")) {
						try {
							amountOfIron = Integer.parseInt(next2.getQuantity());
						} catch (NumberFormatException e) {
							System.out.println("Error: Unable to parse resource quantity");
							amountOfIron = 0;
						}
					}
				}
			}
			Iterator<Unit> iteratorOfUnits = currentUserUserAssets.iteratorOfUnits();
			int tmpAmountOfUnit = 0;
			while(iteratorOfUnits.hasNext()) {
				iteratorOfUnits.next();
				tmpAmountOfUnit++;
			}
			amountOfUnit = tmpAmountOfUnit;
			
			Iterator<Building> iteratorOfBuildings = currentUserUserAssets.iteratorOfBuildings();
			int sizeOfFarms = 0;
			Building building = null;
			while(iteratorOfBuildings.hasNext()) {
				building = iteratorOfBuildings.next();
				if(building.getId().startsWith("Farm")) { 
					sizeOfFarms++;
				}
			}
			
			textWood.setText(String.valueOf(amountOfWood));
			textWood.setTextColor(Color.white);
			textWood.setBorderColor(Color.black);
			textWood.render(container, g);
			textStone.setText(String.valueOf(amountOfStone));
			textStone.setTextColor(Color.white);
			textStone.setBorderColor(Color.black);
			textStone.render(container, g);
			textIron.setText(String.valueOf(amountOfIron));
			textIron.setTextColor(Color.white);
			textIron.setBorderColor(Color.black);
			textIron.render(container, g);
			textUnit.setText(amountOfUnit + "/" + sizeOfFarms * 10);
			textUnit.setTextColor(Color.white);
			textUnit.setBorderColor(Color.black);
			textUnit.render(container, g);

			lowerBar.draw(0, container.getHeight()-((((float) container.getHeight())/1080)*lowerBar.getHeight()),
					(((float) container.getHeight())/1080)*1.0f);

			clock.render(container, g);

			nextTopSector.render(container, g);
			nextTopSector.setNormalColor(new Color(1,1,1,0.5f));
			nextBottomSector.render(container, g);
			nextBottomSector.setNormalColor(new Color(1,1,1,0.5f));
			nextRightSector.render(container, g);
			nextRightSector.setNormalColor(new Color(1,1,1,0.5f));
			nextLeftSector.render(container, g);
			nextLeftSector.setNormalColor(new Color(1,1,1,0.5f));

			menuButton.render(container, g);
			menuButton.setNormalColor(new Color(1,1,1,0.8f));
			alliesButton.render(container, g);
			alliesButton.setNormalColor(new Color(1,1,1,0.8f));
			chatButton.render(container, g);
			chatButton.setNormalColor(new Color(1,1,1,0.8f));

			//NEW
			//units render
			catapult.render(g);
			peon.render(g);
			archer.render(g);
			swordsman.render(g);
			knight.render(g);

			//buildings render
			farm.render(g);
			forge.render(g);
			tower.render(g);
			barrack.render(g);
			stronghold.render(g);

			portrait.draw();
			
			markEl.render(g);

			if(chatOn){
				chatWindow.render(g);	
			}


			if(selection.equals("")){
				placeBuildingButtons.render(g);
			}
			
			

			//new Stuff
			gameMenu.draw(menuOpen);
			optionsMenu.draw(optionsOpen);
			menuButtons.render(g, menuOpen, optionsOpen);
			upgradeBuildingButtons.render(g);
			buildUnitButtons.render(g);
			commandUnitButtons.render(g);
			selectUnitButtons.render(g);
			
			buttonToolTips.render(g);

			//minimap...

			for (int i = 0; i < sectorFields.length; i++) {
				if (sectorFields[i].isMouseOver()) {
					sectorFields[i].setMouseOverColor(new Color(0.8f, 0.8f, 0.8f, 0.8f));
				}
				sectorFields[i].render(container, g);
			}

			
			
			// mark the sector as being owned by an alliance or a player
			Building randomBuilding;
			String usedColor;
			for (int i = 0; i < sectors.size(); i++) {
				if (sectors.get(i).sizeOfSectorBuildings() > 0) {
					randomBuilding = (Building) sectors.get(i).iteratorOfSectorBuildings().next();
					if(randomBuilding.getUserAssets() != null) {
						if (randomBuilding.getUserAssets().getAlliance() != null && randomBuilding.getUserAssets().getAlliance().getColor() != null) {
							// sectors owner has an alliance -> use the alliances color
							usedColor = randomBuilding.getUserAssets().getAlliance().getColor();
							if (usedColor.startsWith("0x") || usedColor.startsWith("0X") || usedColor.startsWith("#")) {
								g.setColor(Color.decode(usedColor));
							} else {
								g.setColor(Color.decode("0x" + usedColor));
							}
						} else {
							// sectors owner has no alliance -> use owners color instead
							if(randomBuilding.getUserAssets().getColor() != null) {
								if (randomBuilding.getUserAssets().getColor().equals("Blue")) {
									g.setColor(Color.blue);
								} else {
									if (randomBuilding.getUserAssets().getColor().equals("Green")) {
										g.setColor(Color.green);
									} else {
										if (randomBuilding.getUserAssets().getColor().equals("Orange")) {
											g.setColor(Color.blue);
										} else {
											if (randomBuilding.getUserAssets().getColor().equals("Purple")) {
												g.setColor(Color.magenta);
											} else {
												if (randomBuilding.getUserAssets().getColor().equals("Red")) {
													g.setColor(Color.red);
												} else {
													if (randomBuilding.getUserAssets().getColor().equals("Yellow")) {
														g.setColor(Color.yellow);
													} else {
														System.err.println("users color \""+ randomBuilding.getUserAssets().getColor()+ "\" is unknown");
													}
												}
											}
										}
									}
								}
							} else {
								System.err.println("randomBuilding.getUserAssets().getColor() == null -> using white to mark it");
								g.setColor(Color.white);
							}
						}
						if (sectors.get(i) == currentSector) {
							playersColor = randomBuilding.getUserAssets().getColor();
							if (stillNeedFirstUserAsset) {
								// init the main map again because now an userasset has been found
								initBuildings(container);
								initUnits(container);
								stillNeedFirstUserAsset = false;
							}
						}
					} else {
						System.err.println("randomBuilding.getUserAssets()  == null -> unknown users building on sector");
					}
					
					g.drawRect(XtoStartFrom + sectors.get(i).getXCoordinate() * fieldLength + 3,
								YtoStartFrom + sectors.get(i).getYCoordinate() * fieldLength + 3, fieldLength - 6, fieldLength - 6);
				}
			}

			// mark the currentSector on minimap
			float temp = g.getLineWidth();
			g.setLineWidth(temp + 2);
			g.setColor(Color.black);
			g.drawRoundRect(XtoStartFrom + currentSector.getXCoordinate() * fieldLength,
					YtoStartFrom + currentSector.getYCoordinate() * fieldLength, fieldLength, fieldLength, 0 , 5);
			g.setColor(Color.gray);
			g.setLineWidth(temp);

			// mark the sectors where fights are going on
			Iterator<Unit> unitIter = null;
			Iterator<Peon> peonIter = null;
			Iterator<Resource> resourceIter = null;
			Unit tempUnit = null;
			Peon tempPeon = null;
			Resource tempResource = null;
			Boolean foundOwn = false;
			Boolean foundEnemy = false;
			for (int i = 0; i < sectors.size(); i++) {
				// check units on sector
				if (sectors.get(i).sizeOfSectorUnits() > 0) {
					unitIter = (Iterator<Unit>) sectors.get(i).iteratorOfSectorUnits();
					while (unitIter.hasNext()) {
						tempUnit = unitIter.next();
						if(tempUnit.getUserAssets() != null && currentUserUserAssets != null) {
							if (tempUnit.getUserAssets().equals(currentUserUserAssets)) {
								foundOwn = true;
							} else {
								foundEnemy = true;
							}
						}
					}
				}
				// check peons at sectors resources
				if (!(foundOwn && foundEnemy)) {
					if (sectors.get(i).sizeOfSectorResources() > 0) {
						resourceIter = (Iterator<Resource>) sectors.get(i).iteratorOfSectorResources();
						while (resourceIter.hasNext()) {
							tempResource = resourceIter.next();
							peonIter = (Iterator<Peon>) tempResource.iteratorOfPeon();
							while (peonIter.hasNext()) {
								tempPeon = peonIter.next();
								if(tempPeon.getUserAssets() != null && currentUserUserAssets != null) {
									if (tempPeon.getUserAssets().equals(currentUserUserAssets)) {
										foundOwn = true;
									} else {
										foundEnemy = true;
									}
								}
							}
						}
					}
				}
				if (foundOwn && foundEnemy) {
					// mark red for there is a fight going on
					temp = g.getLineWidth();
					g.setLineWidth(temp + 2);
					g.setColor(Color.red);
					g.drawRect(XtoStartFrom + sectors.get(i).getXCoordinate() * fieldLength + 6,
							YtoStartFrom + sectors.get(i).getYCoordinate() * fieldLength + 6, fieldLength - 12, fieldLength - 12);
					g.setColor(Color.gray);
					g.setLineWidth(temp);
				}
				foundOwn = false;
				foundEnemy = false;
			}
			
		}
} catch (Exception e) {
	// don`t spam the console 
	if (loopForPrintStackTrace2++ == 300) { 
		System.err.println("Ingame.render() got interrupted");
		e.printStackTrace();
		loopForPrintStackTrace2 = 0;
	}

}
	}


	public void initButtons(GameContainer container){			
		menuButton = new MouseOverArea(container, menu.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*355)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*1070)),this);

		alliesButton = new MouseOverArea(container, allies.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*560)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*1070)),this);

		chatButton = new MouseOverArea(container, chat.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*765)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*1070)),this);

	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
			//Menu
		}else if(key == Input.KEY_F10){
			keyPressedMenuCount++;
			if((keyPressedMenuCount % 2) == 0){
				menuButtons.setMenuOpen(false);
				keyPressedMenuCount = 2;
			}
			else{
				menuButtons.setMenuOpen(true);
			}
			//Chat	
		}else if(key == Input.KEY_F12){
			keyPressedChatCount++;
			if((keyPressedChatCount % 2) == 0){
				chatOn = false;
				keyPressedChatCount = 2;
			}
			else{
				chatOn = true;
			}
			//Allies	
		}else if(key == Input.KEY_F11){
			keyPressedAlliesCount++;
			if((keyPressedAlliesCount % 2) == 0){
				alliesOpen = false;
				keyPressedAlliesCount = 2;
			}
			else{
				alliesOpen = true;
			}
		}else if(key == Input.KEY_UP){
			//u can choose the next available sector by the up button
			direction = 1;
			changeSector = true;
		}else if(key == Input.KEY_DOWN){
			//u can choose the next available sector by the down button
			direction = 2;
			changeSector = true;
		}else if(key == Input.KEY_LEFT){
			//u can choose the next available sector by the left button
			direction = 3;
			changeSector = true;
		}else if(key == Input.KEY_RIGHT){
			//u can choose the next available sector by the right button
			direction = 4;
			changeSector = true;
		}
	}

	public void initAfterSectorChange(GameContainer container) throws SlickException{
		if (currentSector.sizeOfSectorBuildings() != 0) {
			Building randomBuilding = (Building) currentSector.iteratorOfSectorBuildings().next();
			if(randomBuilding.getUserAssets() != null) {
				// there is a sector-owner -> use his color as displayed playersColor 
				if(randomBuilding.getUserAssets().getColor() != null) {
					if (randomBuilding.getUserAssets().getColor().equals("Blue")) {
						playersColor = "Blue";
					} else {
						if (randomBuilding.getUserAssets().getColor().equals("Green")) {
							playersColor = "Green";
						} else {
							if (randomBuilding.getUserAssets().getColor().equals("Orange")) {
								playersColor = "Orange";
							} else {
								if (randomBuilding.getUserAssets().getColor().equals("Purple")) {
									playersColor = "Purple";
								} else {
									if (randomBuilding.getUserAssets().getColor().equals("Red")) {
										playersColor = "Red";
									} else {
										if (randomBuilding.getUserAssets().getColor().equals("Yellow")) {
											playersColor = "Yellow";
										} else {
											System.err.println("users color \""+ randomBuilding.getUserAssets().getColor()+ "\" is unknown");
										}
									}
								}
							}
						}
					}
				} else {
					System.err.println("randomBuilding.getUserAssets().getColor() == null -> using Purple to mark it");
					playersColor = "Purple";
				}
			} else {
				System.err.println("randomBuilding.getUserAssets()  == null -> unknown users building on sector");
			}
		} 
		initMap(ciClient, currentSector, container, currentMap);
		initBuildings(container);
		initUnits(container);
		placeBuildingButtons.setCurrentSector(currentSector);
		selectUnitButtons.setCurrentSector(currentSector);
		markEl = new MarkElement(container);
		portrait = new Portrait(emptyPortrait, container);
		selection = "";
		// now its necessary to keep the selection == "", which is ensured by the following
		map.setSelection("");
		map.setSelectRessPressed(true); 
		// now the selection of the unit must change too (impossible to wait till a concerning button is clicked)
		commandUnitButtons.setCurrentSector(currentSector);
		commandUnitButtons.countBuildingsToBuildUp();
		commandUnitButtons.countResources();
	}


	public void sectorEnterable(int direction, GameContainer container) throws SlickException{

		int x;
		int y;

		x = currentSector.getXCoordinate();
		y = currentSector.getYCoordinate();

		switch(direction){
		case 1 : {
			for(int i = 0; i < sectorFields.length; i++){
				//top way
				if((x) == sectors.get(i).getXCoordinate() &&  (y-1) == sectors.get(i).getYCoordinate()){
					currentSector = sectors.get(i);
					initAfterSectorChange(container);
				}
			}
			changeSector = false;
			break;
		}
		case 2 : {
			for(int i = 0; i < sectorFields.length; i++){
				//bottom way
				if((x == sectors.get(i).getXCoordinate()) &&  ((y+1) == sectors.get(i).getYCoordinate())){
					currentSector = sectors.get(i);
					initAfterSectorChange(container);
				}
			}
			changeSector = false;
			break;
		}
		case 3 : {
			for(int i = 0; i < sectorFields.length; i++){
				//left way
				if((x-1) == sectors.get(i).getXCoordinate() &&  y == sectors.get(i).getYCoordinate()){
					currentSector = sectors.get(i);
					initAfterSectorChange(container);
				}
			}
			changeSector = false;
			break;
		}
		case 4 : {
			for(int i = 0; i < sectorFields.length; i++){
				//right way
				if((x+1) == sectors.get(i).getXCoordinate() &&  y == sectors.get(i).getYCoordinate()){
					currentSector = sectors.get(i);
					initAfterSectorChange(container);
				}
			}
			changeSector = false;
			break;
		}
		}

	}

	public void sectorEnterableByMinimap(GameContainer container) throws SlickException{
		initAfterSectorChange(container);
		changeSectorbyMinimap = false;
	}

	public void mouseClicked(int button, int x, int y, int clickCount) {
		//button 0 = left
		//button 1 = right
		if(ingameStart){
			selectUnitButtons.clicked(button, x, y, clickCount);
			tower.clicked(button, x, y, clickCount, ch);
			farm.clicked(button, x, y, clickCount, ch);
			forge.clicked(button, x, y, clickCount, ch);
			barrack.clicked(button, x, y, clickCount, ch);
			stronghold.clicked(button, x, y, clickCount, ch);
			map.clicked(button, x, y, clickCount, ch);
		
			//check if a sector on the minimap got clicked and react according 
			if (x >= XtoStartFrom && x <= XtoStartFrom + minimapWidth && y >= YtoStartFrom && y <= YtoStartFrom + minimapHeight) {
				// the click is within the area of the minimap, check if a sector got clicked
				for (int i = 0; i < sectorFields.length;i++) {
					if (x > sectorFields[i].getX() && x < sectorFields[i].getX() + fieldLength
							&& y > sectorFields[i].getY() && y < sectorFields[i].getY() + fieldLength) {
						// a sector got clicked
						if (button == 0) {
							// left click -> change CurrentSector
							currentSector = sectors.get(i);
							changeSectorbyMinimap = true;	
						} else {
							if (button == 1) {
								// rigth click -> generate a move-command
								if (selection.equals("")) {
//									System.out.println("no units selected but tried to move some via minimap"); 
								} else {
									ch.moveUnitsToSector(sectors.get(i).getId());

								}
							}
						}			
					}
				}			
			}
			// peonAll-Button
			if(x >= selectUnitButtons.getPeonAll().getX() && x <= selectUnitButtons.getPeonAll().getX()+selectUnitButtons.getPeonAll().getWidth()
					&& y >= selectUnitButtons.getPeonAll().getY() && y <= selectUnitButtons.getPeonAll().getY()+selectUnitButtons.getPeonAll().getHeight()) {
				if(button == 0) {
					selectPeonAllPressed = true;
					selection2 = "peon";
					anyAllButtonPressed = true;
				} else {
					selectPeonAllPressed = false;
					selection2 = "";
				}
			}
			// swordsmanAll-Button
			else if(x >= selectUnitButtons.getSwordsmanAll().getX() && x <= selectUnitButtons.getSwordsmanAll().getX()+selectUnitButtons.getSwordsmanAll().getWidth()
					&& y >= selectUnitButtons.getSwordsmanAll().getY() && y <= selectUnitButtons.getSwordsmanAll().getY()+selectUnitButtons.getSwordsmanAll().getHeight()) {
				if(button == 0) {
					anyAllButtonPressed = true;
					selectSwordsmanAllPressed = true;
					exSwordsmanLv1 = (selectUnitButtons.getNumberSwordsmanLevel1() > 0 ? true : false);
					exSwordsmanLv2 = (selectUnitButtons.getNumberSwordsmanLevel2() > 0 ? true : false);
					exSwordsmanLv3 = (selectUnitButtons.getNumberSwordsmanLevel3() > 0 ? true : false);
				} else {
					selectSwordsmanAllPressed = false;
				}
			}
			// archerAll-Button
			else if(x >= selectUnitButtons.getArcherAll().getX() && x <= selectUnitButtons.getArcherAll().getX()+selectUnitButtons.getArcherAll().getWidth()
					&& y >= selectUnitButtons.getArcherAll().getY() && y <= selectUnitButtons.getArcherAll().getY()+selectUnitButtons.getArcherAll().getHeight()) {
				if(button == 0) {
					anyAllButtonPressed = true;
					selectArcherAllPressed = true;
					selection2 = "archer";
					exArcherLv1 = (selectUnitButtons.getNumberArcherLevel1() > 0 ? true : false);
					exArcherLv2 = (selectUnitButtons.getNumberArcherLevel2() > 0 ? true : false);
					exArcherLv3 = (selectUnitButtons.getNumberArcherLevel3() > 0 ? true : false);
				} else {
					selectArcherAllPressed = false;
					selection2 = "";
				}
			}
			// knightAll-Button
			else if(x >= selectUnitButtons.getKnightAll().getX() && x <= selectUnitButtons.getKnightAll().getX()+selectUnitButtons.getKnightAll().getWidth()
					&& y >= selectUnitButtons.getKnightAll().getY() && y <= selectUnitButtons.getKnightAll().getY()+selectUnitButtons.getKnightAll().getHeight()) {
				if(button == 0) {
					anyAllButtonPressed = true;
					selectKnightAllPressed = true;
					exKnightLv1 = (selectUnitButtons.getNumberKnightLevel1() > 0 ? true : false);
					exKnightLv2 = (selectUnitButtons.getNumberKnightLevel2() > 0 ? true : false);
					exKnightLv3 = (selectUnitButtons.getNumberKnightLevel3() > 0 ? true : false);
				} else {
					selectKnightAllPressed = false;
				}
			}
			// catapultAll-Button
			else if(x >= selectUnitButtons.getCatapultAll().getX() && x <= selectUnitButtons.getCatapultAll().getX()+selectUnitButtons.getCatapultAll().getWidth()
					&& y >= selectUnitButtons.getCatapultAll().getY() && y <= selectUnitButtons.getCatapultAll().getY()+selectUnitButtons.getCatapultAll().getHeight()) {
				if(button == 0) {
					anyAllButtonPressed = true;
					selectCatapultAllPressed = true;
				} else {
					selectCatapultAllPressed = false;
				}
			}
			else {
				selectPeonAllPressed = false;
				selectSwordsmanAllPressed = false;
				selectArcherAllPressed = false;
				selectKnightAllPressed = false;
				selectCatapultAllPressed = false;
			}
		}
	}

	public void componentActivated(AbstractComponent source) {
		 if (source == menuButton) {
			keyPressedMenuCount++;
			if((keyPressedMenuCount % 2) == 0){
				menuButtons.setMenuOpen(false);
				keyPressedMenuCount = 2;
			}
			else{
				menuButtons.setMenuOpen(true);
			}
		}else if(source == chatButton){
			keyPressedChatCount++;
			if((keyPressedChatCount % 2) == 0){
				chatOn = false;
				keyPressedChatCount = 2;
			}
			else{
				chatOn = true;
			}
		}else if(source == alliesButton){
			keyPressedAlliesCount++;
			if((keyPressedAlliesCount % 2) == 0){
				alliesOpen = false;
				keyPressedAlliesCount = 2;
			}
			else{
				alliesOpen = true;
			}	
		}else if(source == nextTopSector){
			direction = 1;
			changeSector = true;
		}else if(source == nextBottomSector){
			direction = 2;
			changeSector = true;
		}else if(source == nextLeftSector){
			direction = 3;
			changeSector = true;
		}else if(source == nextRightSector){
			direction = 4;
			changeSector = true;
		}

	}


	//	   So muss der Teil aussehen der ausgeführt werden muss, damit man etwas sieht!
	//	   Die obere und untere Bar sind nur komplett zu sehen wenn eine 16:9 Auflösung 
	//	   im Windows eingestellt ist, oder manuell eingegeben wird.

	public static void start(){
		try {
			app = new AppGameContainer(new ingameMain(ciClient));
			app.setDisplayMode(800, 450, false);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public MarkElement getMarkEl() {
		return markEl;
	}
	public void setMarkEl(MarkElement markEl) {
		this.markEl = markEl;
	}
	public PeonView getPeon() {
		return peon;
	}
	public ArcherView getArcher() {
		return archer;
	}
	public Catapult getCatapult() {
		return catapult;
	}
	public Knight getKnight() {
		return knight;
	}
	public Swordsman getSwordsman() {
		return swordsman;
	}

}

