package view.game;

import java.util.ArrayList;
import java.util.Iterator;

import mdes.slick.sui.Label;
import model.game.Alliance;
import model.game.Building;
import model.game.CIClient;
import model.game.CiDummy;
import model.game.CommandHelper;
import model.game.Game;
import model.game.Resource;
import model.game.Sector;
import model.game.User;
import model.game.UserAssets;
import model.lobby.ServerConnection;

import org.eclipse.ui.internal.actions.SelectWorkingSetsAction;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
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

import view.game.allystartsector.AllyStartSectorWindow;
import view.game.buildings.*;
import view.game.map.Map;
import view.game.units.*;
import view.game.menu.*;
import view.game.buttons.*;


public class ingameForCiDummy extends BasicGame implements ComponentListener{

	//new stuff
	private GameMenu gameMenu;
	private OptionsMenu optionsMenu;
	private MenuButtons menuButtons;
	private PlaceBuildings selectBuildingButtons;
	private UpgradeBuildings upgradeBuildingButtons;
	private BuildUnits buildUnitButtons;
	private CommandUnits commandUnitButtons;
	private Clock clock;
	private SelectUnits selectUnitButtons;
	private ButtonToolTips buttonToolTips;
	
	public SelectUnits getSelectUnitButtons() {
		return selectUnitButtons;
	}
	private GameContainer container;

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

	String selection = "";
	public void setSelection(String selection){
		this.selection = selection;
	}
	String selection2 = "";
	String selectedColor = "";
	private String selectToMark = "";
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
	private Image abortIcon;
	private MouseOverArea abortButton;


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

	//new stuff
	private CiDummy ciDummy;
	private MarkElement markEl;
	private MouseOverArea markedArea = null;
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

	public void initDummy(){
		ciDummy = new CiDummy();
		ciDummy.generateMap();
		ciDummy.generateUsers();
		ciDummy.generateBuildings();
		ciDummy.generateUnits();

	}

	public ingameForCiDummy(){
		super("ingame");
		initDummy();
		startSector = new Sector();
		this.ciClient = ciDummy.getCiClient();
		this.startSector = showYourOwnSector(ciClient);
	}


	public void initBuildings(CIClient ciClient, Sector current, GameContainer container, String playersColor) throws SlickException{

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

	public void initUnits(CIClient ciClient, Sector current, GameContainer container, String playersColor) throws SlickException{

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

	String sektor = "";


	public void init(GameContainer container) throws SlickException {

		this.container = container;
		startSector = showYourOwnSector(ciClient);
		currentSector = new Sector();
		currentSector = startSector;
		currentUser = startSector.getUserAssets().getUser();

		menuOpen = false;
		helpOpen = false;
		optionsOpen = false;

		sektor = currentSector.getId();


		currentMap = ciClient.getGame().getMap().getName();

		// set the starting sector as the initial currentSector
		//		for (int i = 0; i < sectors.size(); i++) {
		//			if (sectors.get(i).isStartSector() && sectors.get(i).getStartingUser() != null) {
		//				if (sectors.get(i).getStartingUser().getUser().getNickname().equals(ciClient.getCurrentPlayer())) {
		//					currentSector = sectors.get(i);
		//				}
		//			}
		//			
		//		}
		ch = new CommandHelper();
		ch.setMainDummy(this);
		ch.dummyOn = true;

		playersColor = currentSector.getUserAssets().getColor();

		amountOfWood = 999999;
		amountOfStone = 1000;
		amountOfIron = 100;
		amountOfUnit = 10;


		//new Stuff
		gameMenu = new GameMenu(container);
		gameMenu.init();
		optionsMenu = new OptionsMenu(container);
		optionsMenu.init();
		menuButtons = new MenuButtons(container, app);
		menuButtons.init();
		selectBuildingButtons = new PlaceBuildings(container, playersColor,currentSector,currentUser, false);
		selectBuildingButtons.init();
		upgradeBuildingButtons = new UpgradeBuildings(container, selection, ch,currentSector);
		upgradeBuildingButtons.init();
		buildUnitButtons = new BuildUnits(container, selection, ch);
		buildUnitButtons.init();
		commandUnitButtons = new CommandUnits(container, selection, ch,currentSector, currentUser);
		commandUnitButtons.init();
		clock = new Clock();
		markEl = new MarkElement(container);
		selectUnitButtons = new SelectUnits(container, playersColor, ciClient,currentSector, currentUser);
		selectUnitButtons.init();
		initMap(ciClient, currentSector, container, currentMap);


		initBuildings(ciClient, currentSector, container, playersColor);

		//initResource();

		initUnits(ciClient, currentSector, container, playersColor);

		emptyPortrait = new Image("res/Ingame/LowerBar/Portraits/Empty.png");
		portrait = new Portrait(emptyPortrait, container);



		chatWindow = new Chat(container, ciClient);
		//	chatWindow.init();

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
		abortIcon = new Image("res/Ingame/LowerBar/Icons/Abort.png");

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

	public void update(GameContainer container, int delta) throws SlickException{

		menuOpen = menuButtons.getMenuOpen();
		optionsOpen = menuButtons.getOptionsOpen();
		resolutionChanged = menuButtons.getResolutionChanged();

		//if menu && the options are open, or the resolution is changed
		//so dont update the other stuff, else other will be updated
		if(!menuOpen && !optionsOpen || resolutionChanged){
			//new Stuff
			selectBuildingButtons.update();
			selectUnitButtons.update();

			if(upgradeBuildingButtons.getUpgradeBuildingPressed()){
				selection2 = selection = upgradeBuildingButtons.getSelection();
				upgradeBuildingButtons.setUpgradeBuildingPressed(false);
			}

	/*		if(buildUnitButtons.getBuildUnitPressed()){
				selection = buildUnitButtons.getSelection();
				buildUnitButtons.setBuildUnitPressed(false);
			}
	*/
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

				this.init(container);
				if(app.isFullscreen()){
					app.setFullscreen(true);
				}
				menuButtons.setResolutionChanged(false);
			}

			//peon.update(delta);
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



	public void render(GameContainer container, Graphics g) throws SlickException{

		//draw the map + ressources
		map.render(g);

		g.setColor(Color.white);

		g.drawString(sektor, 600, 80);

		upperBar.draw(0, 0, (((float) container.getHeight())/1080)*1.0f);
		if(changedResolution != container.getWidth()) {
			if(container.getWidth() == 800 && container.getHeight() == 450) {
				textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
				textWood.setText(String.valueOf(amountOfWood));
				textWood.setTextColor(Color.white);
				textWood.setBorderColor(Color.black);
				textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
				textStone.setText(String.valueOf(amountOfStone));
				textStone.setTextColor(Color.white);
				textStone.setBorderColor(Color.black);
				textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
				textIron.setText(String.valueOf(amountOfIron));
				textIron.setTextColor(Color.white);
				textIron.setBorderColor(Color.black);
				textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 12), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 50, 15);
				textUnit.setText(amountOfUnit + "/70");
				textUnit.setTextColor(Color.white);
				textUnit.setBorderColor(Color.black);
				changedResolution = container.getWidth();
			} else if(container.getWidth() == 1280 && container.getHeight() == 720) {
				textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
				textWood.setText(String.valueOf(amountOfWood));
				textWood.setTextColor(Color.white);
				textWood.setBorderColor(Color.black);
				textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
				textStone.setText(String.valueOf(amountOfStone));
				textStone.setTextColor(Color.white);
				textStone.setBorderColor(Color.black);
				textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
				textIron.setText(String.valueOf(amountOfIron));
				textIron.setTextColor(Color.white);
				textIron.setBorderColor(Color.black);
				textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 17), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 100, 20);
				textUnit.setText(amountOfUnit + "/70");
				textUnit.setTextColor(Color.white);
				textUnit.setBorderColor(Color.black);
				changedResolution = container.getWidth();
			} else if(container.getWidth() == 1600 && container.getHeight() == 900) {
				textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
				textWood.setText(String.valueOf(amountOfWood));
				textWood.setTextColor(Color.white);
				textWood.setBorderColor(Color.black);
				textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
				textStone.setText(String.valueOf(amountOfStone));
				textStone.setTextColor(Color.white);
				textStone.setBorderColor(Color.black);
				textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
				textIron.setText(String.valueOf(amountOfIron));
				textIron.setTextColor(Color.white);
				textIron.setBorderColor(Color.black);
				textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 24), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1075)), 100, 27);
				textUnit.setText(amountOfUnit + "/70");
				textUnit.setTextColor(Color.white);
				textUnit.setBorderColor(Color.black);
				changedResolution = container.getWidth();
			} else if(container.getWidth() == 1920 && container.getHeight() == 1080) {
				textWood = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1750)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
				textWood.setText(String.valueOf(amountOfWood));
				textWood.setTextColor(Color.white);
				textWood.setBorderColor(Color.black);
				textStone = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1470)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
				textStone.setText(String.valueOf(amountOfStone));
				textStone.setTextColor(Color.white);
				textStone.setBorderColor(Color.black);
				textIron = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*1190)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
				textIron.setText(String.valueOf(amountOfIron));
				textIron.setTextColor(Color.white);
				textIron.setBorderColor(Color.black);
				textUnit = new TextField(container, new TrueTypeFont(new Font("New Times Roman", Font.PLAIN, 34), true),
						(int) (container.getWidth()- ((((float) container.getWidth())/1920)*910)),
						(int) (container.getHeight()-((((float) container.getHeight())/1080)*1070)), 150, 37);
				textUnit.setText(amountOfUnit + "/70");
				textUnit.setTextColor(Color.white);
				textUnit.setBorderColor(Color.black);
				changedResolution = container.getWidth();
			}
		}
		textWood.render(container, g);
		textStone.render(container, g);
		textIron.render(container, g);
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

		//new Stuff
		gameMenu.draw(menuOpen);
		optionsMenu.draw(optionsOpen);
		menuButtons.render(g, menuOpen, optionsOpen);
		upgradeBuildingButtons.render(g);
		buildUnitButtons.render(g);
		commandUnitButtons.render(g);
		selectUnitButtons.render(g);

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
			if (sectors.get(i).sizeOfSectorBuildings() != 0) {
				randomBuilding = (Building) sectors.get(i).iteratorOfSectorBuildings().next();
				if (randomBuilding.getUserAssets().getAlliance().getColor() != null) {
					// sectors owner has an alliance -> use the alliances color
					usedColor = randomBuilding.getUserAssets().getAlliance().getColor();
					if (usedColor.startsWith("0x") || usedColor.startsWith("0X") || usedColor.startsWith("#")) {
						g.setColor(Color.decode(usedColor));
					} else {
						g.setColor(Color.decode("0x" + usedColor));
					}
				} else{
					// sectors owner has no alliance -> use owners color instead
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
									g.setColor(Color.magenta); // who ever did choose purple - slick does not know it! (i was close to take pink instead ;)
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
		buttonToolTips.render(g);

	}



	public void initButtons(GameContainer container){			
		abortButton = new MouseOverArea(container, abortIcon.getScaledCopy((((float) container.getHeight())/1080)*1.0f),
				(int)(container.getWidth()- ((((float) container.getWidth())/1920)*124)),
				(int)(container.getHeight()-((((float) container.getHeight())/1080)*80)),this);

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
		if (currentSector.getUserAssets() != null) {
			playersColor = currentSector.getUserAssets().getColor();
		}
		initMap(ciClient, currentSector, container, currentMap);
		initBuildings(ciClient, currentSector, container, playersColor);
		initUnits(ciClient, currentSector, container, playersColor);
		selectBuildingButtons.setCurrentSector(currentSector);
		selectUnitButtons.setCurrentSector(currentSector);
		markEl = new MarkElement(container);
		portrait = new Portrait(emptyPortrait, container);
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
							// right click -> generate a move-command
							if (selection.equals("")) {
								System.out.println("no units selected but tried to move some via minimap");
							} else {
								ciClient.setServerConnection(new ServerConnection());
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

	public void componentActivated(AbstractComponent source) {
		if(source == abortButton){
			selection = "";
			selectedColor = "";	
		}else if (source == menuButton) {
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


	public MarkElement getMarkEl() {
		return markEl;
	}

	//	   So muss der Teil aussehen der ausgeführt werden muss, damit man etwas sieht!
	//	   Die obere und untere Bar sind nur komplett zu sehen wenn eine 16:9 Auflösung 
	//	   im Windows eingestellt ist, oder manuell eingegeben wird.
	//new stuff, delete then
	public static void main(String[] arg0){
		try {
			app = new AppGameContainer(new ingameForCiDummy());
			app.setDisplayMode(1280, 720, false);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
	public Swordsman getSwordsman() {
		return swordsman;
	}
	public Knight getKnight() {
		return knight;
	}



}

