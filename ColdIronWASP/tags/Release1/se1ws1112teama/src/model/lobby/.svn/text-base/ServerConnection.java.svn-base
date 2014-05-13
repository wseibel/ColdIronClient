package model.lobby;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.beans.*;

import model.game.CIClient;

public class ServerConnection{
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
	 * this Timer-Object manages the three threads
	 */
	private Timer updateTask, noopTask;

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
		updateTask.schedule(new UpdateGamesTask(getCIClient()), 0, 5000);
		updateTask.schedule(new UpdatePlayersTask(getCIClient()), 1000, 5000);
	}

	/**
	 * finish the update threads
	 */
	public void endUpdate() {
		updateTask.cancel();
	}

	// we need MORE THAN ONE server connection e.g. after logout for new login
	//   private static ServerConnection instance;
	//   public static  synchronized ServerConnection getInstance () 
	//   {
	//      if (instance == null) {
	//         			instance = new ServerConnection();
	//         		}
	//         		return instance;
	//         	}
	/**
	 * <pre>
	 *           1..1     1..1
	 * ServerConnection ------------------------- CIClient
	 *           serverConnection        &lt;       cIClient
	 * </pre>
	 */

	private CIClient cIClient;

	public boolean setCIClient (CIClient value)		
	{
		boolean changed = false;

		if (this.cIClient != value)
		{
			CIClient oldValue = this.cIClient;
			if (this.cIClient != null)
			{
				this.cIClient = null;
				oldValue.setServerConnection(null);
			}
			this.cIClient = value;

			if (value != null)
			{
				value.setServerConnection(this);
			}
			changed = true;
		}
		return changed;
	}

	public CIClient getCIClient ()	
	{
		return this.cIClient;
	}
	/**
	 *  Constructor that is private to enforce the use of public getInstance()
	 */
	 public ServerConnection () {
		 connect();
		 updateTask = new Timer();
		 noopTask = new Timer();
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
	  * @param message
	  */
	 public void msg(String message) {
		 send("msg " + message + "\n");
		 return;
	 }

	 /**
	  * asks the server to change the password from pass1 to pass2
	  * @param pass1
	  * @param pass2
	  */
	 public void changePassword(String pass1, String pass2) {
		 send("change password " + pass1 + " " + pass2 + "\n");
		 return;
	 }

	 /**
	  * join the game with that name
	  * @param name
	  */
	 public void joinGame(String name) {
		 send("join game " + name + "\n");
		 return;
	 }

	 /**
	  * create the game with that name
	  * @param name
	  */
	 public void createGame(String name) {
		 send("create game " + name + "\n");
		 return;
	 }



	 private PropertyChangeSupport changes =
			 new PropertyChangeSupport(this);
	 public void addPropertyChangeListener(
			 PropertyChangeListener l)
	 {
		 changes.addPropertyChangeListener(l);
	 }
	 public void removePropertyChangeListener(
			 PropertyChangeListener l)
	 {
		 changes.removePropertyChangeListener(l);
	 }


	 /**
	  * login as user with that name and that password
	  * @param name
	  * @param password
	  * @param logincontroller 
	  */
	 public void login(String name, String password) {
		 cIClient.setNonJSONMessageReceiver(new NonJSONMessageReceiver(
				 cIClient.getServerConnection().getBufferedReader()));
		 cIClient.getNonJSONMessageReceiver().start(); // needs to be
		 // stopped and replaced by starting a JSONMessageReceiver
		 // when joining a game (vice versa when leaving a game)
		 send("login " + name + " " + password + "\n");
		 return;
	 }

	 /**
	  *  logout the current user, what does result in shutting
	  *  down the current server-connection (equals facing a timeout)
	  */
	 public void logout() {
		 //interrupt causes problems (server sending repeated null)
//		 cIClient.getNonJSONMessageReceiver().interrupt();
		 cIClient.getNonJSONMessageReceiver().stop();
		 endNOOP();
		 endUpdate();
		 send("logout\n");
	 }

	 private void send(String string) {
		 try {
			 bufferedWriter.write(string);
			 bufferedWriter.flush();
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }

	 /**
	  *  tries to connect to the server
	  *  returns true if managed to do so
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
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return socket.isConnected();
	 }

	 /**
	  * evaluates the servers clear-text-messages and feeds the datamodel
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
			 // e.g.: USER NICK=rdoben TEAM=TeamA STATE=LOBBY
			 String[] splittedMsg = msg.split(" ");
			 PlayerInfo pInfo = new PlayerInfo();
			 pInfo.setName(splittedMsg[1].substring(5));			 
			 pInfo.setTeam(splittedMsg[2].substring(5));
			 /* Provisional arrangemend for substring "Team Nina"
			  * Has to be fixed later
			  */
			 if(splittedMsg[2].substring(5).equals("Team")){
				 pInfo.setStatus("n.a.");
			 }
			 else
				 pInfo.setStatus(splittedMsg[3].substring(6));
			 pInfo.setCIClient(cIClient);
			 return;
		 }
		 if (msg.startsWith("GAME")) {
			 // e.g.: GAME NAME=ci-watcher-test1322514284803 EVENTS=503
			 //       MAPNAME=Aruba_Island SECTORS=9 PLAYERS=1/2 STATUS=JOINING
			 String[] splittedMsg = msg.split(" ");
			 GameInfo gInfo = new GameInfo();
			 gInfo.setName(splittedMsg[1].substring(5));
			 gInfo.setEventCount(Integer.parseInt(splittedMsg[2].substring(7)));
			 gInfo.setMapName(splittedMsg[3].substring(8));
			 gInfo.setSectorCount(Integer.parseInt(splittedMsg[4].substring(8)));
			 gInfo.setStatus(splittedMsg[6].substring(7));
			 String[] splittedSubMsg_5 = splittedMsg[5].split("/");
			 gInfo.setJoinedPlayerCount(Integer.parseInt(splittedSubMsg_5[0].substring(8)));
			 gInfo.setMaxPlayerCount(Integer.parseInt(splittedSubMsg_5[1]));
			 gInfo.setCIClient(cIClient);
			 return;
		 }

		 if (msg.startsWith("MAPNAME")) {
			 // e.g.: MAPNAME: Aruba, SECTORS: 9
			 String[] splittedMsg = msg.split(","); 
			 String[] splittedSubMsg_0 = splittedMsg[0].split(" ");
			 String[] splittedSubMsg_1 = splittedMsg[1].split(" "); 
			 MapInfo mInfo = new MapInfo();
			 mInfo.setName(splittedSubMsg_0[1]);
			 mInfo.setSectorCount(Integer.parseInt(splittedSubMsg_1[2]));
			 mInfo.setCIClient(cIClient);
			 return;
		 }

		 // default:
		 System.err.println("unexpected message from server: " + msg); 
		 return;
	 }

	 /**
	  * send a "noop" to server
	  * @param name
	  */
	 public void sendNOOP() {
		 send("noop\n");
		 return;
	 }


	 public void removeYou()
	 {
		 this.setCIClient (null);
	 }

}
