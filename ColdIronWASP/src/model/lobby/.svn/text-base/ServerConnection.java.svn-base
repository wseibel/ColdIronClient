package model.lobby;

import model.game.Building;
import model.game.CIClient;
import model.game.Sector;
import model.game.Unit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Timer;

import org.json.JSONException;
import org.json.JSONStringer;

public class ServerConnection {
	private Socket socket;

	public Socket getSocket() {
		return this.socket;
	}

	private BufferedWriter bufferedWriter;

	public BufferedWriter getBufferedWriter() {
		return this.bufferedWriter;
	}

	private BufferedReader bufferedReader;

	public BufferedReader getBufferedReader() {
		return this.bufferedReader;
	}

	/**
	 * this Timer-Object manages the four threads
	 */
	private Timer updateTask, noopTask, jSONNoopTask;

	/**
	 * create and start the noop thread
	 */
	public void startNOOP() {
		noopTask.schedule(new NoopTask(this), 0, 590000);
	}

	/**
	 * finish the noop thread
	 */
	public void endNOOP() {
		noopTask.cancel();
	}

	/**
	 * create and start the update threads
	 */
	public void startUpdate() {
		updateTask.schedule(new UpdateGamesTask(getCIClient()), 0, 1000);
		updateTask.schedule(new UpdatePlayersTask(getCIClient()), 500, 1000);
	}

	/**
	 * finish the update threads
	 */
	public void endUpdate() {
		updateTask.cancel();
	}
	
	/**
	 * create and start the JSONNoop thread
	 */
	public void startJSONnoop() {
		jSONNoopTask.schedule(new JSONNoopTask(this), 0, 590000);
	}

	/**
	 * finish the JSONNoop thread
	 */
	public void endJSONnoop() {
		jSONNoopTask.cancel();
	}

	
	/**
	 * <pre>
	 *           1..1     1..1
	 * ServerConnection ------------------------- CIClient
	 *           serverConnection        &lt;       cIClient
	 * </pre>
	 */

	private CIClient cIClient;

	public boolean setCIClient(CIClient value) {
		boolean changed = false;

		if (this.cIClient != value) {
			CIClient oldValue = this.cIClient;
			if (this.cIClient != null) {
				this.cIClient = null;
				oldValue.setServerConnection(null);
			}
			this.cIClient = value;

			if (value != null) {
				value.setServerConnection(this);
			}
			changed = true;
		}
		return changed;
	}

	public CIClient getCIClient() {
		return this.cIClient;
	}

	/**
	 * Constructor that is private to enforce the use of public getInstance()
	 */
	public ServerConnection() {
		if (!connect()) {
			System.err.println("failed to connect to the SE1-server");
		}
		updateTask = new Timer();
		noopTask = new Timer();
		jSONNoopTask = new Timer();
		return;
	}

	/**
	 * asks the server for currently online users
	 */
	public void getPlayers() {
		send("list users\n");
		return;
	}

	/**
	 * asks the server for currently existing games
	 */
	public void getGames() {
		send("list games\n");
		return;
	}

	/**
	 * asks the server for currently available maps
	 */
	public void getMaps() {
		send("list maps\n");
		return;
	}

	/**
	 * sends message as a chat-Message to the server
	 * 
	 * @param message
	 */
	public void msg(String message) {
		send("msg " + message + "\n");
		return;
	}

	/**
	 * asks the server to change the password from pass1 to pass2
	 * 
	 * @param pass1
	 * @param pass2
	 */
	public void changePassword(String pass1, String pass2) {
		send("change password " + pass1 + " " + pass2 + "\n");
		return;
	}

	/**
	 * join the game with that name
	 * 
	 * @param name
	 */
	public void joinGame(String name) {
		send("join game " + name + "\n");
		endUpdate();
		endNOOP();
		startJSONnoop();
		return;
	}

	/**
	 * create the game with that name
	 * 
	 * @param name
	 */
	public void createGame(String name) {
		send("create game " + name + "\n");
		return;
	}

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}

	/**
	 * login as user with that name and that password
	 * 
	 * @param name
	 * @param password
	 */
	public void login(String name, String password) {
		cIClient.setCurrentPlayer(name);
		cIClient.setNonJSONMessageReceiver(new NonJSONMessageReceiver(cIClient
				.getServerConnection().getBufferedReader()));
		cIClient.getNonJSONMessageReceiver().start(); // needs to be
		// stopped and replaced by starting a JSONMessageReceiver
		// when joining a game (vice versa when leaving a game)
		send("login " + name + " " + password + "\n");
		return;
	}

	/**
	 * logout the current user, what does result in shutting down the current
	 * server-connection (equals facing a timeout)
	 */
	public void logout() {
		// interrupt causes problems (server sending repeated null)
		// cIClient.getNonJSONMessageReceiver().interrupt();
		cIClient.getNonJSONMessageReceiver().run = false;
		endNOOP();
		endUpdate();
		send("logout\n");
	}

	/**
	 * sends the String to the server
	 * 
	 */
	public void send(String string) {
		try {
			bufferedWriter.write(string);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tries to connect to the server returns true if managed to do so
	 */
	public boolean connect() {
		try {
			socket = new Socket("se1.cs.uni-kassel.de", 5000);

			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			OutputStream outputStream = socket.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					outputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return socket.isConnected();
	}

	/**
	 * evaluates the servers clear-text-messages and feeds the datamodel
	 * 
	 * @param msg
	 */
	public void consumeMessage(String msg) {
		if (msg.equals("ERROR: unknown user")) {
			System.err.println(msg);
			changes.firePropertyChange("loginSuccess", "", "false");
			return; // login failed
		}
		if (msg.contains("EMAIL=")) {
			changes.firePropertyChange("loginSuccess", "", "true");
			return; // user logged in successfully
		}
		if (msg.startsWith("ERROR")) {
			System.err.println(msg);
			return; // no general error-handling wanted
		}
		if (msg.startsWith("OK") || msg.startsWith("SE1")
				|| msg.contains("ID=")) {
			return; // ignore useless but expected info
		}
		if (msg.startsWith("MSG")) {
			cIClient.setNewRcvChatMessage(msg);
			return;
		}
		if (msg.startsWith("USER")) {

			// e.g.: USER NICK=ci-watcher TEAM=Team Nina STATE=LOBBY
			// bug was fixed ^^ now Team Nina can be showed right
			String[] string = msg.split("=");

			String pName = "";
			String tName = "";

			pName = string[1].substring(0, (string[1].length() - 5));
			tName = string[2].substring(0, (string[2].length() - 6));

			PlayerInfo pInfo = new PlayerInfo();
			pInfo.setName(pName);
			pInfo.setTeam(tName);
			getCIClient().addToPlayerList(pInfo);
			return;

		}
		if (msg.startsWith("GAME")) {
			// e.g.: GAME NAME=ci-watcher-test1322514284803 EVENTS=503
			// MAPNAME=Aruba_Island SECTORS=9 PLAYERS=1/2 STATUS=JOINING
			String[] splittedMsg = msg.split(" ");
			GameInfo gInfo = new GameInfo();
			gInfo.setName(splittedMsg[1].substring(5));
			gInfo.setEventCount(Integer.parseInt(splittedMsg[2].substring(7)));
			gInfo.setMapName(splittedMsg[3].substring(8));
			gInfo.setSectorCount(Integer.parseInt(splittedMsg[4].substring(8)));
			gInfo.setStatus(splittedMsg[6].substring(7));
			String[] splittedSubMsg_5 = splittedMsg[5].split("/");
			gInfo.setJoinedPlayerCount(Integer.parseInt(splittedSubMsg_5[0]
					.substring(8)));
			gInfo.setMaxPlayerCount(Integer.parseInt(splittedSubMsg_5[1]));
			getCIClient().addToGameList(gInfo);
			return;
		}

		if (msg.startsWith("MAP")) {
			// e.g.: MAP NAME=ARUBA_ISLAND SECTORS=9 START_SECTORS=4
			String[] splittedMsg = msg.split("=");
			// fixed one bug, cause of new maps, with the "_" symbol, u got
			// always
			// an exception, cause of ArrayIndexOutofBounds. now it works
			// and i inserted new variable startSector
			String mapName = "";
			String sector = "";
			String startSector = "";

			mapName = splittedMsg[1].substring(0, splittedMsg[1].length() - 8);
			sector = splittedMsg[2].substring(0, splittedMsg[1].length() - 19);
			startSector = splittedMsg[3];

			MapInfo mInfo = new MapInfo();
			mInfo.setName(mapName);
			mInfo.setSectorCount(Integer.parseInt(sector));
			mInfo.setStartSector(Integer.parseInt(startSector));
			mInfo.setCIClient(cIClient);
			return;
		}

		// default:
//		System.err.println("unexpected message from server: " + msg);
		return;
	}

	/**
	 * send a "noop" to server
	 */
	public void sendNOOP() {
		send("noop\n");
		return;
	}

	public void removeYou() {
		this.setCIClient(null);
	}

	/**
	 * sends the command CHOOSE_SECTOR
	 * 
	 * @param sector
	 *            the sector to choose e.g. Sector@1a3b6f
	 */
	public void choose_sector(String sector) {
		// e.g. {"@action":"CHOOSE_SECTOR","properties":{"entry":{"key":"sector","value":"Sector@1a3b6f"}}}
		String command = "{\"@action\":\"CHOOSE_SECTOR\",\"properties\":{\"entry\":{\"key\":\"sector\",\"value\":\""
				+ sector + "\"}}}\n";
		send(command);
	}

	/**
	 * sends the command START_GAME
	 */
	public void start_game() {
		// {"@action":"START_GAME"}
		String command = "{\"@action\":\"START_GAME\"}\n";
		send(command);
	}

	/**
	 * sends the command LEAVE_GAME (and may also handle the
	 * view/receiver-stuff)
	 * 
	 */
	public void leave_game() {
		// {"@action":"LEAVE_GAME"}
		String command = "{\"@action\":\"LEAVE_GAME\"}\n";
		send(command);
		// TODO: change back the messagereceiver to nonJSON
		// change back the JSONnoop- to the noop-Timertask and restart the update-tasks
		// close slick-view and reopen lobby 
	}

	/**
	 * sends the command CREATE_BUILDING
	 * 
	 * @param sector
	 *            the sector of the building e.g. SECTOR@d54a23
	 * @param buildingtype
	 *            the type of the building e.g. STRONGHOLD
	 */
	public void create_building(String sector, String buildingtype) {
		// e.g.	{"@action":"CREATE_Building","properties":{"entry":{"key":"sector","value":"SECTOR@d54a23"}
		// ,"entry":{"key":"buildingtype","value":"STRONGHOLD"}}}
		String command = "{\"@action\":\"CREATE_BUILDING\",\"properties\":{\"entry\":{\"key\":\"sector\",\"value\":\""
				+ sector
				+ "\"},\"entry\":{\"key\":\"buildingtype\",\"value\":\""
				+ buildingtype + "\"}}}\n";
		try {
			//avoid flooding
			Thread.sleep(500);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		send(command);
	}

	/**
	 * sends the command DROP_BUILDING
	 * 
	 * @param building
	 *            --> the building to be destroy e.g. STRONGHOLD@d43b23
	 */
	public void drop_building(String building) {
		// e.g. {"@action":"DROP_Building","properties":{"entry":{"key":"building","value":"STRONGHOLD@d43b23"}}}
		String command = "{\"@action\":\"DROP_BUILDING\",\"properties\":{\"entry\":{\"key\":\"building\",\"value\":\""
				+ building + "\"}}}\n";
		send(command);
	}

	/**
	 * sends the command CREATE_UNIT
	 * 
	 * @param building
	 *            the building where to create the unit e.g. STRONGHOLD@d54a23
	 * @param unittype
	 *            the type of the unit e.g. PEON
	 * @param level
	 *            the level of the unit e.g. 1
	 */
	public void create_unit(String building, String unittype, String level) {
		// e.g. {"@action":"CREATE_UNIT","properties":{"entry":{"key":"building","value":"STRONGHOLD@d54a23"}
		// ,"entry":{"key":"unittype","value":"PEON"},"entry":{"key":"level","value":"1"}}}
		String command = "{\"@action\":\"CREATE_UNIT\",\"properties\":{\"entry\":{\"key\":\"building\",\"value\":\""
				+ building
				+ "\"},\"entry\":{\"key\":\"unittype\",\"value\":\""
				+ unittype
				+ "\"},\"entry\":{\"key\":\"level\",\"value\":\""
				+ level + "\"}}}\n";
		send(command);
		
		try {
			//avoid flooding
			Thread.sleep(5);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}

	/**
	 * sends the command BUILD_UP (btw: no peons needed for upgrading)
	 * 
	 * @param building
	 *            the building to be build up at e.g. Stronghold@5974b827
	 * @param peons
	 *            the peons to do the work e.g. Peon@ghz4b827,Peon@ghz4b333
	 */
	public void build_up(String building, String peons) {
		// e.g. {"@action":"BUILD_UP","properties":{"entry":{"key":"building","value":"Stronghold@5974b827"}
		// ,"entry":{"key":"peons","value":"Peon@ghz4b827,Peon@ghz4b333"}}}
		String command = "{\"@action\":\"BUILD_UP\",\"properties\":{\"entry\":{\"key\":\"building\",\"value\":\""
				+ building
				+ "\"},\"entry\":{\"key\":\"peons\",\"value\":\""
				+ peons + "\"}}}\n";
		send(command);
		try {
			//avoid flooding
			Thread.sleep(5);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}

	/**
	 * sends the command MOVE_UNITS
	 * 
	 * @param units
	 *            the units to be moved e.g. Knight@d54a23,Swordsman@d533uf
	 * @param target
	 *            the target to be moved to (resource|sector|tower) e.g.
	 *            Sector@1a3b6f
	 */
	@SuppressWarnings("unchecked")
	public void move_units(String units, String target) {
		// e.g. {"@action":"MOVE_UNITS","properties":{"entry":{"key":"units","value":"Knight@d54a23
		// ,Swordsman@d533uf"},"entry":{"key":"target","value":"Sector@1a3b6f"}}}
		String command = "{\"@action\":\"MOVE_UNITS\",\"properties\":{\"entry\":{\"key\":\"units\",\"value\":\""
				+ units
				+ "\"},\"entry\":{\"key\":\"target\",\"value\":\""
				+ target + "\"}}}\n";
		send(command);
		try {
			//avoid flooding
			Thread.sleep(5);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
		// a movement is about to be started, so mark all sectors without own units or
		// own buildings as needy concerning getting refreshed in the data-model
		// (the refreshing itself is done in the SectorHandler)
		Sector tempSector = null;
		Unit tempUnit = null;
		Building tempBuilding = null;
		Boolean foundOne = false;
		for (Iterator<Sector> sectorIter = cIClient.getGame().iteratorOfSector();
					sectorIter.hasNext();) {
				tempSector = sectorIter.next();
				// check sector for own units
				for (Iterator<Unit> unitIter = tempSector.iteratorOfSectorUnits();
						unitIter.hasNext() && !foundOne;) {
					tempUnit = unitIter.next();
					if (tempUnit.getUserAssets().getUser().getNickname().equals(cIClient.getCurrentPlayer())) {
						// unit belongs to this client`s user 
						foundOne = true;
					}
				}
				if (!foundOne) {
					// check sector for own buildings
					for (Iterator<Building> buildingIter = tempSector.iteratorOfSectorBuildings();
							buildingIter.hasNext() && !foundOne;) {
						tempBuilding = buildingIter.next();
						if (tempBuilding.getUserAssets().getUser().getNickname().equals(cIClient.getCurrentPlayer())) {
							// building belongs to this client`s user 
							foundOne = true;
						}
					}
				}
				if (!foundOne) {
					// sector might be out of sync with the data-model -> delete the old entries in the data-model
					tempSector.setNeedsRefresh(true);
				}
				foundOne = false;
			
		}
			
		
		
		
		
		
	}

	/**
	 * sends the command UPGRADE
	 * 
	 * @param building
	 *            the building to be upgraded e.g. Stronghold@5974b827
	 * @param level
	 *            the level to be upgraded to e.g. 2
	 */
	public void upgrade(String building, String level) {
		// e.g. {"@action":"UPGRADE","properties":{"entry":{"key":"building","value":"Stronghold@5974b827"}
		// ,"entry":{"key":"level","value":"2"}}}
		String command = "{\"@action\":\"UPGRADE\",\"properties\":{\"entry\":{\"key\":\"building\",\"value\":\""
				+ building
				+ "\"},\"entry\":{\"key\":\"level\",\"value\":\""
				+ level + "\"}}}\n";
		send(command);
		try {
			//avoid flooding
			Thread.sleep(5);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}

	/**
	 * sends the command REPAIR
	 * 
	 * @param building
	 *            the building to be repaired e.g. Stronghold@5974b827
	 * @param peons
	 *            the peons to do the work e.g. Peon@ghz4b827,Peon@ghz4b333
	 */
	public void repair(String building, String peons) {
		// e.g. {"@action":"REPAIR","properties":{"entry":{"key":"building","value":"Stronghold@5974b827"}
		// ,"entry":{"key":"peons","value":"Peon@ghz4b827,Peon@ghz4b333"}}}
		String command = "{\"@action\":\"REPAIR\",\"properties\":{\"entry\":{\"key\":\"building\",\"value\":\""
				+ building
				+ "\"},\"entry\":{\"key\":\"peons\",\"value\":\""
				+ peons + "\"}}}\n";
		send(command);
		try {
			//avoid flooding
			Thread.sleep(5);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}

	/**
	 * sends the command CREATE_ALLIANCE
	 * 
	 * @param alliance
	 *            the alliance to be created e.g. "TestAlliance"
	 * @param color
	 *            the color of the alliance e.g. ffffff
	 */
	public void create_alliance(String alliance, String color) {
		String command = "{\"@action\":\"CREATE_ALLIANCE\",\"properties\":{\"entry\":{\"key\":\"name\",\"value\":\""
				+ alliance
				+ "\"}, \"entry\":{\"key\":\"color\",\"value\":\""
				+ color + "\"}}}\n";
		send(command);
	}

	/**
	 * sends the command JOIN_ALLIANCE
	 * 
	 * @param alliance
	 *            the alliance to be joined e.g. TestAlliance
	 */
	public void join_alliance(String alliance) {
		String command = "{\"@action\":\"JOIN_ALLIANCE\",\"properties\":{\"entry\":{\"key\":\"alliance\",\"value\":\""
				+ alliance + "\"}}}\n";
		send(command);
	}

	/**
	 * sends the command DELETE_ALLIANCE
	 * 
	 * @param alliance
	 *            the alliance to be deleted e.g. Alliance@1924b867
	 */
	public void delete_alliance(String alliance) {
		// e.g. {"@action":"DELETE_ALLIANCE","properties":{"entry":{"key":"alliance","value":"Alliance@1924b867"}}}
		String command = "{\"@action\":\"DELETE_ALLIANCE\",\"properties\":{\"entry\":{\"key\":\"alliance\",\"value\":\""
				+ alliance + "\"}}}\n";
		send(command);
	}

	/**
	 * sends the command LEAVE_ALLIANCE
	 * 
	 */
	public void leave_alliance() {
		// e.g. {"@action":"LEAVE_ALLIANCE"}
		String command = "{\"@action\":\"LEAVE_ALLIANCE\"}\n";
		send(command);
	}

	/**
	 * sends the command CHANGE_ALLIANCE_COLOR
	 * 
	 * @param alliance
	 *            the alliance who's color is to be changed e.g.
	 *            Alliance@1924b867
	 * @param color
	 *            the new color e.g. FFFF00
	 */
	public void change_alliance_color(String alliance, String color) {
		// e.g. {"@action":"CHANGE_ALLIANCE_COLOR","properties":{"entry":{"key":"alliance"
		// ,"value":"Alliance@1924b867"},"entry":{"key":"color","value":"FFFF00"}}}
		String command = "{\"@action\":\"CHANGE_ALLIANCE_COLOR\",\"properties\":{\"entry\":{\"key\":\"alliance\",\"value\":\""
				+ alliance
				+ "\"},\"entry\":{\"key\":\"color\",\"value\":\""
				+ color + "\"}}}\n";
		send(command);
	}

	/**
	 * sends the command MESSAGE
	 * 
	 * @param message
	 *            the message to be send e.g. Hallo
	 * @param audience
	 *            the audience of the message e.g. USER
	 * @param recipient
	 *            the recipient of the message e.g. zenobios
	 */
	public void message(String message, String audience, String recipient) {
		// e.g. {"@action":"MESSAGE","properties":{"entry":{"key":"message","value":"Hallo"}
		// ,"entry":{"key":"audience","value":"USER"},"entry":{"key":"recipient","value":"zenobios"}}}
		String command = "{\"@action\":\"MESSAGE\",\"properties\":{\"entry\":{\"key\":\"message\",\"value\":\""
				+ message
				+ "\"},\"entry\":{\"key\":\"audience\",\"value\":\""
				+ audience
				+ "\"},\"entry\":{\"key\":\"recipient\",\"value\":\""
				+ recipient + "\"}}}\n";
		send(command);
		try {
			//avoid flooding
			Thread.sleep(5);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}

	/**
	 * sends the command CHANGE_USER_COLOR
	 * 
	 * @param color
	 *            the new color for the user e.g. FFFF00
	 */
	public void change_user_color(String color) {
		// e.g. "@action":"CHANGE_USER_COLOR","properties":{"entry":{"key":"color","value":"FFFF00"}}}
		String command = "{\"@action\":\"CHANGE_USER_COLOR\",\"properties\":{\"entry\":{\"key\":\"color\",\"value\":\""
				+ color + "\"}}}\n";
		send(command);
	}
	
	/**
	 * sends the command NOOP
	 */
	public void sendJSONnoop() {
		// {"@action":"NOOP"}
		String command = "{\"@action\":\"NOOP\"}\n";
		send(command);
	}

}
