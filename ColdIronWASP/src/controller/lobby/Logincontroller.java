package controller.lobby;

import java.beans.PropertyChangeEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.game.CIClient;
import model.lobby.ServerConnection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import controller.game.handler.ChainMaster;

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
		//first check, if the server is reachable at all
		Socket socket = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
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
			login_GUI.gettextError().setText("no access to the internet!");
			System.err.println("no access to internet at all (se1.cs.uni-kassel.de is unknown host)");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			login_GUI.gettextError().setText("no access to ColdIron-Server!");
			System.err.println("no access to SE1 ColdIron-Server (socket to se1.cs.uni-kassel.de is not connectable)");
			e.printStackTrace();
			return;
		}
		try {
			Thread.currentThread().sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			String greeting = bufferedReader.readLine();
			if (!greeting.startsWith("SE1 ColdIron-Server")) {
				throw new IOException();
			}
		} catch (IOException e) {
			login_GUI.gettextError().setText("the gameserver is down!");
			System.err.println("SE1 ColdIron-Server seems to be down (there is access to the internet but the server doesn`t reply with the expected greeting)");
			return;
		}
		try {
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// server is reachable, so start the login
		
		nickname = login_GUI.getTextLoginName().getText().trim();
		password = login_GUI.getTextPassword().getText().trim();
		cIClient.setServerConnection(new ServerConnection());
		cIClient.setChainMaster(new ChainMaster());
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
				e.printStackTrace();
			}
		}
		
		if (gotLoginAnswer) {
			if (loginsucceed) {
				LobbyController lobbyController = new LobbyController(login_GUI,
		                cIClient, display);
				cIClient.getServerConnection().startUpdate();
				cIClient.getServerConnection().startNOOP();
				login_GUI.getShlSwtApplication().setVisible(false);
				lobbyController.start();
				this.gotLoginAnswer = false;
			}else {
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