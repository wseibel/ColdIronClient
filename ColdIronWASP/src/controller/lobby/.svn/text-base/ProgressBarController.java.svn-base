package controller.lobby;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.widgets.Display;

import view.game.ingameMain;
import view.lobby.ProgressBarJoin;

import model.game.CIClient;

/**
 * Starts view.lobby.ProgressBarJoin
 *
 */
public class ProgressBarController extends AbstractController {

	private ProgressBarJoin progressBar = null;
	private CIClient ciClient = null;
	private String gameName;

	public ProgressBarController(CIClient ciClient, String gameName) {
		super();
		this.progressBar = new ProgressBarJoin();
		this.ciClient = ciClient;
		this.gameName = gameName;
	}

	/**
	 * Starts GameLobbyGui
	 */
	@SuppressWarnings("static-access")
	public void start() {
		// start view
		Display display = Display.getDefault();
		progressBar.createContents();
		progressBar.getShellProgressBar().open();
		progressBar.getShellProgressBar().layout();

		// joins the game
		ciClient.getServerConnection().joinGame(gameName);

		// show view during events are incoming 
		while (!progressBar.getShellProgressBar().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

			// break when all events were incoming
			if (ciClient.getFirstSendEvents() >= ciClient
					.getNumberFirstSendingEvents()) {
				progressBar.getShellProgressBar().dispose();
			}

		}
		
		// start inGame
		ingameMain ingameMain = new ingameMain(ciClient);
		ingameMain.start();
		
		stop();
	}

	/**
	 * Closes gameLobbyGui
	 */
	public void stop() {
		super.stop();
		progressBar.getShellProgressBar().dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	public void refreshUI() {

	}

	@Override
	public void registerListeners() {

	}
}
