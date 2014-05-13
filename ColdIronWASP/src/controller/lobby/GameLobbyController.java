package controller.lobby;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import model.game.CIClient;
import model.lobby.ChatUtilities;
import model.lobby.MapInfo;
import model.lobby.PlayerInfo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import view.lobby.GameLobby;
import view.lobby.Lobby;

public class GameLobbyController extends AbstractController {

	private GameLobby gameLobbyGui;
	private Lobby lobbyGUI;
	private CIClient cIClient;
	private LobbyController lobbyController;
	private ChatUtilities chatUtil;
	private String[] newMaps;
	private Display display;
	private String[] newPlayerTeamList;
	private String msgTo;
	private String destination = "";
	private String via = "";
	
	/**
	 * Constructor
	 * @param lobbyController
	 * @param cIClient
	 */
	public GameLobbyController(LobbyController lobbyController, CIClient cIClient, Display display, Lobby lobbyGUI) {
		this.cIClient = cIClient;
		this.gameLobbyGui = new GameLobby();
		this.lobbyGUI = lobbyGUI;
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
		gameLobbyGui.getGobalChatTextField().append(lobbyController.getAllMsg());
		
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
	
	private void removeUserFromAllowedPlayerList(final int index) {
		
		display.syncExec(new Runnable() {

            public void run() {
            	if(!gameLobbyGui.getShlColdiron().isDisposed()) {
            		gameLobbyGui.getListAllowedPlayers().remove(index);
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
				//lobbyController.open();
				GameLobbyController.this.stop();
				lobbyGUI.getShlColdiron().setVisible(true);
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
//					System.err.println("going to create a game without a map selected");
				}				
				// try to get the players 
				if (gameLobbyGui.getList().getSelection().length != 0) {
					for (String player: gameLobbyGui.getList().getSelection()) {
						players += player + " ";
					}
				} else {
//					System.err.println("going to create a game without players selected");
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
				GameLobbyController.this.stop();
				lobbyGUI.getShlColdiron().setVisible(true);
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
		
		gameLobbyGui.getList().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {

				removeUserFromAllowedPlayerList(gameLobbyGui.getList().getSelectionIndex());
				
			}
		});
		
		gameLobbyGui.getShlColdiron().addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				GameLobbyController.this.stop();
				lobbyGUI.getShlColdiron().setVisible(true);				
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
		});
		
		cIClient.addPropertyChangeListener(cIClient.PROPERTY_NEW_RCV_CHAT_MESSAGE, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
					final String rcvMsg = chatUtil.splitReceivedMsg((String) evt.getNewValue());
					
			  		display.syncExec(new Runnable() {

	                    public void run() {
	                    	if (!gameLobbyGui.getShlColdiron().isDisposed()) {
	                    		gameLobbyGui.getGobalChatTextField().append(rcvMsg+"\n");
	                    		refreshUI();
	                    	}
	                    }

	                });	
				}
				

		});
		
		/**
		 * PropertyChangeListener for available players
		 */
		cIClient.addPropertyChangeListener(cIClient.PROPERTY_PLAYER_INFO,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						
						if(cIClient.sizeOfPlayerInfo() != 0){
							
							// prepare the String[] to set in GUI
							String[] tempNewPlayers = new String[cIClient.sizeOfPlayerInfo()];
							String[] tempNewTeams = new String[cIClient.sizeOfPlayerInfo()];
							int countPlayer = 0;
							int countTeam = 0;
							PlayerInfo pInfo;
							Iterator gIter = cIClient.iteratorOfPlayerInfo();
							while (gIter.hasNext()) {
								pInfo = (PlayerInfo) gIter.next();
								tempNewPlayers[countPlayer++] = pInfo.getName();
								tempNewTeams[countTeam++] = pInfo.getTeam();
							}
							
							//the method getTeamCount counts the number of valid online Teams
							int numberOfTeams = chatUtil.getTeamCount(tempNewTeams, 0);
							int totalLengthOfList = numberOfTeams + tempNewPlayers.length;
							
							String[] fullList = new String[totalLengthOfList];
							
							//this method fillString, fills the string array up with values about teams
							//and their players
							newPlayerTeamList = chatUtil.fillString(tempNewTeams, tempNewPlayers, fullList, 0);

						}
						else{
							newPlayerTeamList = new String[1];
							newPlayerTeamList[0] = " ";
						}
						
						// insert into GUI
						display.syncExec(new Runnable() {

							public void run() {
								if (!gameLobbyGui.getShlColdiron().isDisposed()) {
									gameLobbyGui.getListPlayers().setItems(newPlayerTeamList);
									refreshUI();
								}
							}

						});
						
					}

				});
		
		gameLobbyGui.getListPlayers().addMouseListener(new MouseListener()
		{
			public void mouseDown(MouseEvent e){
			}
			
			public void mouseUp(MouseEvent e){
			}
			public void mouseDoubleClick(MouseEvent e)
			{
				if(gameLobbyGui.getListPlayers().getSelection().length != 0){
					msgTo = gameLobbyGui.getListPlayers().getSelection()[0] + " ";
				}
				
				//send to all
				if(msgTo == null){
				}
				//send to user
				else if((msgTo.charAt(0) =='-') && (msgTo.charAt(1) == ' ')){
					via = " user ";
					destination = msgTo.substring(2);
				}
				//send to team
				else{
					via = " team ";
					destination = msgTo;
				}
								
				display.syncExec(new Runnable() {
					public void run() {
						if (!gameLobbyGui.getShlColdiron().isDisposed()) {
							gameLobbyGui.getTextChatMessage().setText(via + destination);
						refreshUI();
						}
					}
				});
			}
	 
		});
		
	}

}
