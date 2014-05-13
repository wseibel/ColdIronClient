package controller.lobby;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import model.game.CIClient;
import model.lobby.ChatUtilities;
import model.lobby.GameInfo;
import model.lobby.JSONMessageReceiver;
import model.lobby.ChatUtilities;
import model.lobby.PlayerInfo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


import view.lobby.Lobby;
import view.lobby.Login;
import view.lobby.ChangePassword;


public class LobbyController extends AbstractController{

	private Lobby lobbyGUI;
	private CIClient ciClient;
	private Login login_GUI;
	private ChatUtilities chatUtil;
	private Display display;
	private String[] newGames;
	private String[] treeItem;
	private String[] treeTeam;
	private String teamName;
	private PlayerInfo pInfoName;
	
	/**
	 * Constructor
	 * 
	 * @param login_GUI
	 * @param cIClient
	 */
	public LobbyController(Login login_GUI, CIClient cIClient, Display display) {
		this.login_GUI = login_GUI;
		this.ciClient = cIClient;
		this.lobbyGUI = new Lobby(null);
		this.chatUtil = new ChatUtilities();
		this.display = display;
	}
	

	/**
	 * Starts the LobbyGui 
	 */
	public void start() {
		Display display = Display.getDefault();
		
		lobbyGUI.createContents();
		lobbyGUI.getShlColdiron().open();
		lobbyGUI.getShlColdiron().layout();
		
		super.start();
		
		while (!lobbyGUI.getShlColdiron().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Stops Controller and Gui
	 * @see controller.lobby.AbstractController#stop()
	 */
	public void stop() {
		super.stop();
		lobbyGUI.getShlColdiron().dispose();
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		
	}
	
	/**
	 * Refreshes UI
	 */
	public void refreshUI() {
		lobbyGUI.getShlColdiron().layout();
	}
	
	/**
	 * Reacts by clicking Logout-, Options-, CreateGame-, JoinGame- and 
	 * SendChatMessage-Button
	 * @see controller.lobby.AbstractController#start()
	 */
	public void registerListeners() {

		//Send msg with return and button
		Listener sendTextMsgListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (event.widget == lobbyGUI.getBtnSend()) {
					ciClient.getServerConnection().msg(lobbyGUI.getTextSendMsg().getText().trim());
					lobbyGUI.getTextSendMsg().setText("");
				}
			}
		};
		lobbyGUI.getBtnSend().addListener(SWT.Selection, sendTextMsgListener);
		lobbyGUI.getTextSendMsg().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					ciClient.getServerConnection().msg(lobbyGUI.getTextSendMsg().getText().trim());
					lobbyGUI.getTextSendMsg().setText("");
				}
				
			}
		});
		
		
		
		lobbyGUI.getBtnLogout().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ciClient.getServerConnection().logout();
				LobbyController.this.stop();
				//both already stopped 2 lines above
//				ciClient.getServerConnection().endNOOP();
//				ciClient.getServerConnection().endUpdate();
				login_GUI.getShlSwtApplication().setVisible(true);
				
				login_GUI.getTextLoginName().setText("nickname");
				login_GUI.getTextPassword().setText("password");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		lobbyGUI.getBtnChangePw().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ChangePasswordController optController = new ChangePasswordController(new ChangePassword(), ciClient);
				optController.start();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		lobbyGUI.getBtnCreateGame().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LobbyController.this.close();
				GameLobbyController gameLobbyController = new GameLobbyController
						(LobbyController.this, ciClient, display);
				gameLobbyController.start();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		lobbyGUI.getBtnJoinGame().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String gameName = "";
				// try to get the selected game from the displayed List of games
				if (lobbyGUI.getListGames().getSelection().length != 0) {
					gameName += lobbyGUI.getListGames().getSelection()[0];
				} else {
					System.err.println("unable to join game because no game selected");
					return;
				}
				//interrupt could cause problems (like server sending repeated null)
//				ciClient.getNonJSONMessageReceiver().interrupt();
				ciClient.getNonJSONMessageReceiver().stop();
				ciClient.setJSONMessageReceiver(new JSONMessageReceiver(
						   ciClient.getServerConnection().getBufferedReader()));
				ciClient.getJSONMessageReceiver().start();
				ciClient.getServerConnection().joinGame(gameName) ;
				
				// TODO:
				// start a (nonexisting) game-controller that on
				// exit (= returning to lobbyGui?) needs to stop
				// the JSONMessageReceiver and start a new
				// NonJSONMessageReceiver()
				// set visibility of lobbyGUI to false or stop it
							
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		
		ciClient.addPropertyChangeListener(ciClient.PROPERTY_NEW_RCV_CHAT_MESSAGE, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals(ciClient.PROPERTY_NEW_RCV_CHAT_MESSAGE)){
					
					final String rcvMsg = chatUtil.splitReceivedMsg((String) evt.getNewValue());
					chatUtil.chatLog(rcvMsg);
					
			  		display.syncExec(new Runnable() {

	                    public void run() {
	                    	if (!lobbyGUI.getShlColdiron().isDisposed()) {
	                    		lobbyGUI.getGobalChatTextField().append(rcvMsg+"\n");
	                    		refreshUI();
	                    	}
	                    }

	                });	
				}
				
			}

		});
	
		/**
		 * PropertyChangeListener for available games
		 * fills Lobby-GUI with games
		 */
		ciClient.addPropertyChangeListener(ciClient.PROPERTY_GAME_INFO, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(ciClient.PROPERTY_GAME_INFO)) {
					// prepare the String[] to set in GUI
					String[] tempNewGames = new String[ciClient.sizeOfGameInfo()];
					int count = 0;
			  		GameInfo gInfo;
			  		Iterator gIter = ciClient.iteratorOfGameInfo();
			  		while (gIter.hasNext()) {
			  			gInfo = (GameInfo) gIter.next();
			  			tempNewGames[count++] = gInfo.getName();
			  		}
			  		//store in a global variable
			  		newGames = tempNewGames;
			  		// insert into GUI
			  		display.syncExec(new Runnable() {

	                    public void run() {
	                    	if (!lobbyGUI.getShlColdiron().isDisposed()) {
	                    		lobbyGUI.getListGames().setItems(newGames);
	                    		refreshUI();
	                    	}
	                    }

	                });	
				}
				
			}
		});
		
		/**
		 * PropertyChangeListener for available players
		 */
		ciClient.addPropertyChangeListener(ciClient.PROPERTY_PLAYER_INFO, new PropertyChangeListener(){
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(ciClient.PROPERTY_PLAYER_INFO)) {
					// prepare the String[] to set in GUI
					final String[] tempNewPlayers = new String[ciClient.sizeOfPlayerInfo()];
					final String[] tempNewTeams = new String[ciClient.sizeOfPlayerInfo()];
					int count = 0;
			  		PlayerInfo pInfo;
			  		Iterator pIter = ciClient.iteratorOfPlayerInfo();

			  		while (pIter.hasNext()) {
			  			pInfo = (PlayerInfo) pIter.next();
			  			tempNewPlayers[count] = pInfo.getName();
			  			tempNewTeams[count] = pInfo.getTeam();
			  			count++;
			  		}
			  		//store in a global variable
			  		treeItem = tempNewPlayers;
			  		treeTeam = tempNewTeams;
			  		// insert into GUI
			  		display.syncExec(new Runnable() {

	                    public void run() {
	                    	if (!lobbyGUI.getShlColdiron().isDisposed()) {
	                    		//it shows only one player!
	                    		lobbyGUI.getRoot().removeAll();
	                    		
	                    		//lobbyGUI.getRootTwo().removeAll();
	                    		for(int i = 0; i < treeTeam.length; i++){
	                    			lobbyGUI.getRoot().setText("Global Players");
	                    			new TreeItem(lobbyGUI.getRoot(), 0).setText(treeItem[i]+" "+treeTeam[i]);
	                    		}
	                    		
	                    		refreshUI();
	                    	}
	                    }

	                });	
				}
				
			}
		});
		

	}

	/**
	 * Doesn't show lobbyGui anymore
	 */
	public void close() {
		lobbyGUI.getShlColdiron().setVisible(false);
	}
	
	/**
	 * Shows lobbyGui again
	 */
	public void open() {
		lobbyGUI.getShlColdiron().setVisible(true);
	}
}
