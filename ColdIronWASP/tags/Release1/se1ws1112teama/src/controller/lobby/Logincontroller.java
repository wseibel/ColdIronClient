package controller.lobby;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import model.game.CIClient;
import model.lobby.ServerConnection;
import view.lobby.Login;

public class Logincontroller implements java.beans.PropertyChangeListener,
java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Login login_GUI;
	private String nickname;
	private String password;
	private Display display;
	private CIClient cIClient;
	private Thread currentThread;
	private boolean gotLoginAnswer =  false;
	private boolean loginsucceed = false;
	
	public Logincontroller (CIClient cIClient) {
		this.login_GUI = new Login();
		this.display = login_GUI.getDisplay();
		this.cIClient = cIClient;
	}

	public void start() {
		login_GUI.getShlSwtApplication().open();
		
		while (!login_GUI.getShlSwtApplication().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void initLoginGUIListener() {
		
		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == login_GUI.getButtonLogin()) {
					login();
				}
			}
		};
		login_GUI.getButtonLogin().addListener(SWT.Selection, listener);
		login_GUI.getTextPassword().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					login();
				}
				
			}
		});
		
		login_GUI.getTextLoginName().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					login_GUI.getTextPassword().setFocus();
				}
				
			}
		});
		
		login_GUI.getBtnQuit().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				stop();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		start();
	}

	private void login() {
		nickname = login_GUI.getTextLoginName().getText().trim();
		password = login_GUI.getTextPassword().getText().trim();
		cIClient.setServerConnection(new ServerConnection());
		initServerConnectionListener();
		cIClient.getServerConnection().login(nickname,password);
		currentThread = Thread.currentThread();
		
		// wait for fired Events from the ServerConnection().consumeMessage()
		// which will set "gotLoginAnswer" to true and also verify the logins
		// success by setting "loginsucceed"
		while (!gotLoginAnswer) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (gotLoginAnswer) {
			if (loginsucceed) {
//				System.err.println("about to handle fired event: \"loginSuccess\" \"true\"");
				LobbyController lobbyController = new LobbyController(login_GUI,
		                cIClient, display);
				cIClient.getServerConnection().startUpdate();
				cIClient.getServerConnection().startNOOP();
				login_GUI.getShlSwtApplication().setVisible(false);
				lobbyController.start();
				this.gotLoginAnswer = false;
			}else {
//				System.err.println("about to handle fired event: \"loginSuccess\" \"false\"");
				login_GUI.gettextError().setText("wrong username or password!");
				this.gotLoginAnswer = false;
			}
		}
		this.loginsucceed = false;
	}

	
	public void initServerConnectionListener() {
		cIClient.getServerConnection().addPropertyChangeListener(this); 
	}
	
	
	// handles fired Events from the ServerConnection().consumeMessage()
	// which verify the logins success
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("loginSuccess")) {
			if (evt.getNewValue().equals("true")) {
				this.loginsucceed = true;
			}
			if (evt.getNewValue().equals("false")) {	//Login failed
				this.loginsucceed = false;
			}
			this.gotLoginAnswer = true;
			synchronized (currentThread) {
				currentThread.notify();
			}
			
		} 
	}


	private void stop() {
		login_GUI.getShlSwtApplication().dispose();
	}
}