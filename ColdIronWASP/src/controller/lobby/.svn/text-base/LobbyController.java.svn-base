package controller.lobby;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import model.game.CIClient;
import model.lobby.ChatUtilities;
import model.lobby.GameInfo;
import model.lobby.JSONMessageReceiver;
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

import view.game.ingameMain;
import view.lobby.ChangePassword;
import view.lobby.Lobby;
import view.lobby.Login;

public class LobbyController extends AbstractController {

	private Lobby lobbyGUI;
	private CIClient ciClient;
	private Login login_GUI;
	private ChatUtilities chatUtil;
	private Display display;
	private String[] newGames;
	private String[] newPlayerTeamList;
	private String msgTo;
	private String destination = "";
	private String via = "";
	private String allMsg = "";
	private String selectedGame = null;

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
	 * 
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
	 * 
	 * @see controller.lobby.AbstractController#start()
	 */
	@SuppressWarnings("static-access")
	public void registerListeners() {

		// Send msg with return and button
		Listener sendTextMsgListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (event.widget == lobbyGUI.getBtnSend()) {

					ciClient.getServerConnection().msg(
							lobbyGUI.getTextSendMsg().getText().trim());
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
				if (arg0.keyCode == 13) {
					ciClient.getServerConnection().msg(
							lobbyGUI.getTextSendMsg().getText().trim());
					lobbyGUI.getTextSendMsg().setText("");
				}

			}
		});

		lobbyGUI.getBtnLogout().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ciClient.getServerConnection().logout();
				LobbyController.this.stop();
				// both already stopped 2 lines above
				// ciClient.getServerConnection().endNOOP();
				// ciClient.getServerConnection().endUpdate();
				login_GUI.getShlSwtApplication().setVisible(true);

				login_GUI.getTextLoginName().setText("nickname");
				login_GUI.getTextPassword().setText("password");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		lobbyGUI.getShlColdiron().addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				ciClient.getServerConnection().logout();
				LobbyController.this.stop();
				login_GUI.getShlSwtApplication().setVisible(true);
			}
		});

		lobbyGUI.getBtnChangePw().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ChangePasswordController optController = new ChangePasswordController(
						new ChangePassword(), ciClient);
				optController.start();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		lobbyGUI.getBtnCreateGame().addSelectionListener(
				new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						LobbyController.this.close();
						GameLobbyController gameLobbyController = new GameLobbyController(
								LobbyController.this, ciClient, display,
								lobbyGUI);
						lobbyGUI.getShlColdiron().setVisible(false);
						gameLobbyController.start();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}
				});

		lobbyGUI.getListGames().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// get the selected game from the displayed List of games
				try {
					if (lobbyGUI.getListGames().getSelection() != null) {
						if (lobbyGUI.getListGames().getSelection()[0] != null) {
							selectedGame = lobbyGUI.getListGames()
									.getSelection()[0];
						}
					}
				} catch (Exception e) {
					// accidental clicking below the last entry causes an
					// "indexoutofbound" -> ignore
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		lobbyGUI.getBtnJoinGame().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String gameName = "";
				// try to get the selected game
				if (selectedGame != null) {
					gameName += selectedGame;
				} else {
					System.err
							.println("unable to join game because no game selected");
					return;
				}

				ProgressBarController progressBarController = new ProgressBarController(
						ciClient, gameName);

				// can't still read nonJsonMessages
				ciClient.getNonJSONMessageReceiver().run = false;// .stop();

				// now can handle jsonMessages
				ciClient.setJSONMessageReceiver(new JSONMessageReceiver(
						ciClient.getServerConnection().getBufferedReader()));
				ciClient.getJSONMessageReceiver().start();
				
				// set LobbyGUI's visibility false
				lobbyGUI.getShlColdiron().setVisible(false);

				// start progress bar
				// info: ciClient joins game in view.lobby.ProgressBarJoin not
				// in controller.lobby.ProgressBarController
				progressBarController.start();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		ciClient.addPropertyChangeListener(
				ciClient.PROPERTY_NEW_RCV_CHAT_MESSAGE,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {

						final String rcvMsg = chatUtil
								.splitReceivedMsg((String) evt.getNewValue());
						// chatUtil.chatLog(rcvMsg);
						allMsg = allMsg + rcvMsg + "\n";

						display.syncExec(new Runnable() {

							public void run() {
								if (!lobbyGUI.getShlColdiron().isDisposed()) {
									lobbyGUI.getGobalChatTextField().append(
											rcvMsg + "\n");
									refreshUI();
								}
							}

						});

					}

				});

		/**
		 * PropertyChangeListener for available games fills Lobby-GUI with games
		 */
		ciClient.addPropertyChangeListener(ciClient.PROPERTY_GAME_INFO,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						// prepare the String[] to set in GUI
						String[] tempNewGames = new String[ciClient
								.sizeOfGameInfo()];
						int count = 0;
						GameInfo gInfo;
						@SuppressWarnings("unchecked")
						Iterator<GameInfo> gIter = ciClient
								.iteratorOfGameInfo();
						while (gIter.hasNext()) {
							gInfo = (GameInfo) gIter.next();
							tempNewGames[count++] = gInfo.getName();
						}
						// store in a global variable
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
				});

		/**
		 * PropertyChangeListener for available players
		 */
		ciClient.addPropertyChangeListener(ciClient.PROPERTY_PLAYER_INFO,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {

						if (ciClient.sizeOfPlayerInfo() != 0) {

							// prepare the String[] to set in GUI
							String[] tempNewPlayers = new String[ciClient
									.sizeOfPlayerInfo()];
							String[] tempNewTeams = new String[ciClient
									.sizeOfPlayerInfo()];
							int countPlayer = 0;
							int countTeam = 0;
							PlayerInfo pInfo;
							@SuppressWarnings("unchecked")
							Iterator<PlayerInfo> gIter = ciClient
									.iteratorOfPlayerInfo();
							while (gIter.hasNext()) {
								pInfo = (PlayerInfo) gIter.next();
								tempNewPlayers[countPlayer++] = pInfo.getName();
								tempNewTeams[countTeam++] = pInfo.getTeam();
							}

							// the method getTeamCount counts the number of
							// valid online Teams
							int numberOfTeams = chatUtil.getTeamCount(
									tempNewTeams, 0);
							int totalLengthOfList = numberOfTeams
									+ tempNewPlayers.length;

							String[] fullList = new String[totalLengthOfList];

							// this method fillString, fills the string array up
							// with values about teams
							// and their players
							newPlayerTeamList = chatUtil.fillString(
									tempNewTeams, tempNewPlayers, fullList, 0);

						} else {
							newPlayerTeamList = new String[1];
							newPlayerTeamList[0] = " ";
						}

						// insert into GUI
						display.syncExec(new Runnable() {

							public void run() {
								if (!lobbyGUI.getShlColdiron().isDisposed()) {
									lobbyGUI.getListPlayers().setItems(
											newPlayerTeamList);
									refreshUI();
								}
							}

						});

					}

				});

		lobbyGUI.getListPlayers().addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {
			}

			public void mouseUp(MouseEvent e) {
			}

			public void mouseDoubleClick(MouseEvent e) {
				if (lobbyGUI.getListPlayers().getSelection().length != 0) {
					msgTo = lobbyGUI.getListPlayers().getSelection()[0] + " ";
				}

				// send to all
				if (msgTo == null) {
				}
				// send to user
				else if ((msgTo.charAt(0) == '-') && (msgTo.charAt(1) == ' ')) {
					via = " user ";
					destination = msgTo.substring(2);
				}
				// send to team
				else {
					via = " team ";
					destination = msgTo;
				}

				display.syncExec(new Runnable() {
					public void run() {
						if (!lobbyGUI.getShlColdiron().isDisposed()) {
							lobbyGUI.getTextSendMsg()
									.setText(via + destination);
							refreshUI();
						}
					}
				});
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

	public String getAllMsg() {
		return allMsg;
	}
}
