package controller.lobby;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import model.game.CIClient;
import model.lobby.MapInfo;
import model.lobby.ChatUtilities;

import view.lobby.GameLobby;

public class GameLobbyController extends AbstractController {

	private GameLobby gameLobbyGui;
	private CIClient cIClient;
	private LobbyController lobbyController;
	private ChatUtilities chatUtil;
	private String[] newMaps;
	private Display display;
	
	/**
	 * Constructor
	 * @param lobbyController
	 * @param cIClient
	 */
	public GameLobbyController(LobbyController lobbyController, CIClient cIClient, Display display) {
		this.cIClient = cIClient;
		this.gameLobbyGui = new GameLobby();
		this.lobbyController = lobbyController;
		this.chatUtil = new ChatUtilities();
		this.display = display;
	}

	/**
	 * Starts GameLobbyGui
	 */
	public void start() {
		Display display = Display.getDefault();
		
		gameLobbyGui.createContents();
		gameLobbyGui.getShlColdiron().open();
		gameLobbyGui.getShlColdiron().layout();
		
		super.start();
		
		cIClient.removeAllFromMapInfo();
		cIClient.getServerConnection().getMaps();
		
		while (!gameLobbyGui.getShlColdiron().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Closes gameLobbyGui
	 */
	public void stop() {
		super.stop();
		gameLobbyGui.getShlColdiron().dispose();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		
	}

	@Override
	public void refreshUI() {
		gameLobbyGui.getShlColdiron().layout();
	}
	
	//have to update the global chat field
	private void updateGlobalTextChatField(PropertyChangeEvent evt){
		String rcvMsg = chatUtil.splitReceivedMsg((String) evt.getNewValue());
	//	gameLobbyGui.gettabFolderMsgInput().setText(rcvMsg);
		chatUtil.chatLog(rcvMsg);
		
		refreshUI();
	}
	
	private void addUsertoAllowedPlayerList() {
		final String user = gameLobbyGui.getTextAddUsr().getText();
		
		display.syncExec(new Runnable() {

            public void run() {
            	if(!gameLobbyGui.getShlColdiron().isDisposed()) {
            		gameLobbyGui.getListAllowedPlayers().add(user);
            		refreshUI();
            	}
            }

        });	
	}

	/**
	 * Reacts by clicking Cancel- and StartGame-Button
	 */
	@Override
	public void registerListeners() {
		
		//Send msg with return and button.
		Listener sendTextMsgListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (event.widget == gameLobbyGui.getBtnSend()) {
					cIClient.getServerConnection().msg(gameLobbyGui.getTextChatMessage().getText().trim());
					gameLobbyGui.getTextChatMessage().setText("");
				}
			}
		};
		gameLobbyGui.getBtnSend().addListener(SWT.Selection, sendTextMsgListener);
		gameLobbyGui.getTextChatMessage().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					cIClient.getServerConnection().msg(gameLobbyGui.getTextChatMessage().getText().trim());
					gameLobbyGui.getTextChatMessage().setText("");
				}
				
			}
		});
		
		gameLobbyGui.getBtnCancel().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				lobbyController.open();
				GameLobbyController.this.stop();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		gameLobbyGui.getBtnStart().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String gameParam = "";
				String gameName = "";
				String players = "";
				String map = "";
				
				// try to get the gameName 
				if (gameLobbyGui.getText().getText() != "") {
					gameName += gameLobbyGui.getText().getText();
				} else {
					System.err.println("unable to create a game because no name entered");
					return;
				}
				// try to get the map 
				if (gameLobbyGui.getListMaps().getSelection().length != 0) {
					map += gameLobbyGui.getListMaps().getSelection()[0];
				} else {
					System.err.println("going to create a game without a map selected");
				}				
				// try to get the players 
				if (gameLobbyGui.getList().getSelection().length != 0) {
					for (String player: gameLobbyGui.getList().getSelection()) {
						players += player + " ";
					}
				} else {
					System.err.println("going to create a game without players selected");
				}
				// prepare the parameter for "create game"
				// CREATE GAME <gamename> (-players (<player>|<team>)+)? (-map <mapname>)?
				gameParam += gameName;
				if (players != "") {
					gameParam += " -players " + players;
				}
				if (map != "") {
					gameParam += " -map " + map;					
				}
				cIClient.getServerConnection().createGame(gameParam);
				cIClient.getServerConnection().getGames();				
				lobbyController.open();
				GameLobbyController.this.stop();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		gameLobbyGui.getBtnAddUsr().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				addUsertoAllowedPlayerList();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		gameLobbyGui.getTextAddUsr().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					addUsertoAllowedPlayerList();
				}
			}
		});
		
		cIClient.getServerConnection().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateGlobalTextChatField(evt);
			}

		});
		
		/*
		 * PropertyChangeListener for available maps
		 * fills GameLobby-GUI with maps
		 */
		cIClient.addPropertyChangeListener(cIClient.PROPERTY_MAP_INFO, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(cIClient.PROPERTY_MAP_INFO)) {
					// prepare the String[] to set in GUI
					String[] tempNewMaps = new String[cIClient.sizeOfMapInfo()];
					int count = 0;
			  		MapInfo mInfo;
			  		Iterator mIter = cIClient.iteratorOfMapInfo();
			  		while (mIter.hasNext()) {
			  			mInfo = (MapInfo) mIter.next();
			  			tempNewMaps[count++] = mInfo.getName();
			  		}
			  		//store in a global variable
			  		newMaps = tempNewMaps;
			  		
			  		// insert into GUI
			  		display.syncExec(new Runnable() {

	                    public void run() {
	                        if(!gameLobbyGui.getShlColdiron().isDisposed()) {
	                        	gameLobbyGui.getListMaps().setItems(newMaps);
	    			  		    refreshUI();
	                        }
	                    }

	                });	
			  		
					
				}
				
			}
		});
		
		cIClient.addPropertyChangeListener(cIClient.PROPERTY_NEW_RCV_CHAT_MESSAGE, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals(cIClient.PROPERTY_NEW_RCV_CHAT_MESSAGE)){
					
					final String rcvMsg = chatUtil.splitReceivedMsg((String) evt.getNewValue());
					chatUtil.chatLog(rcvMsg);
					
			  		display.syncExec(new Runnable() {

	                    public void run() {
	                    	if (!gameLobbyGui.getShlColdiron().isDisposed()) {
	                    		gameLobbyGui.getGobalChatTextField().append(rcvMsg+"\n");
	                    		refreshUI();
	                    	}
	                    }

	                });	
				}
				
			}

		});
		
	}

}
