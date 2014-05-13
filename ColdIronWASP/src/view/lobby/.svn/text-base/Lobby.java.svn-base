package view.lobby;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Lobby{
	
   private Text textSendMsg;
   
   public Text getTextSendMsg() {
      return this.textSendMsg;
   }
   
   private List listGames;
   
   public List getListGames() {
	      return this.listGames;
	   }
   

	private List listPlayers;
	
	public List getListPlayers() {
		return this.listPlayers;
	}
	
   private Button btnJoinGame;
   
   public Button getBtnJoinGame() {
      return this.btnJoinGame;
   }
   private Button btnCreateGame;
   
   public Button getBtnCreateGame() {
      return this.btnCreateGame;
   }
   private Button btnChgPw;
   
   public Button getBtnChangePw() {
      return this.btnChgPw;
   }
   private Button btnLogout;
   
   public Button getBtnLogout() {
      return this.btnLogout;
   }
   private Button btnSend;
   
   public Button getBtnSend() {
      return this.btnSend;
   }
   
   public void removeYou()
   {
   }

	protected Shell shlColdiron;
	private Display display;
	private Text gobalChatTextField;

	public Lobby(Display display){
		this.display = display;
		createContents();
	}

	/**
	  * Create contents of the window.
	 */
	public void createContents() {
		
		shlColdiron = new Shell(display);
		shlColdiron.setMinimumSize(new Point(730, 510));
		shlColdiron.setImage(SWTResourceManager.getImage(Lobby.class, "/waspLogo2.png"));
		shlColdiron.setSize(709, 512);
		shlColdiron.setText("ColdIron");
		shlColdiron.setLayout(new GridLayout(2, false));
		
		Composite compoInfo = new Composite(shlColdiron, SWT.NONE);
		compoInfo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoInfo.setLayout(new GridLayout(1, false));
		
		Composite compoGameInfo = new Composite(compoInfo, SWT.NONE);
		compoGameInfo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoGameInfo.setLayout(new GridLayout(2, false));
		
		Composite compoGames = new Composite(compoGameInfo, SWT.NONE);
		compoGames.setLayout(new GridLayout(1, false));
		compoGames.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblCurrentGames = new Label(compoGames, SWT.NONE);
		lblCurrentGames.setText("Current Games");
		
		listGames = new List(compoGames, SWT.BORDER);
		GridData gd_listGames = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_listGames.minimumWidth = 90;
		listGames.setLayoutData(gd_listGames);
		
		Composite copmpoPlayers = new Composite(compoGameInfo, SWT.NONE);
		copmpoPlayers.setLayout(new GridLayout(1, false));
		copmpoPlayers.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblPlayerAndTeam = new Label(copmpoPlayers, SWT.NONE);
		lblPlayerAndTeam.setText("Player and Team List");
		
		listPlayers = new List(copmpoPlayers, SWT.BORDER);
		GridData gd_listPlayers = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_listPlayers.minimumWidth = 90;
		listPlayers.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite compoChat = new Composite(compoInfo, SWT.NONE);
		compoChat.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoChat.setLayout(new GridLayout(1, false));
		
		Label lblChat = new Label(compoChat, SWT.NONE);
		lblChat.setText("Chat");
		
		CTabFolder cTabFolderMsgInput = new CTabFolder(compoChat, SWT.NONE);
		cTabFolderMsgInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabItem tabGlobalChat = new CTabItem(cTabFolderMsgInput, SWT.NONE);
		tabGlobalChat.setText("GlobalChat");
		cTabFolderMsgInput.setSelection(tabGlobalChat);
		
		gobalChatTextField = new Text(cTabFolderMsgInput, SWT.READ_ONLY | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		gobalChatTextField.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		gobalChatTextField.setText("If you doubleclick on a Player or Team in the " +
				"PlayersList, you can send a Message to them\n");
		tabGlobalChat.setControl(gobalChatTextField);
		
		Composite compoMsgOut = new Composite(compoChat, SWT.NONE);
		compoMsgOut.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		compoMsgOut.setLayout(new GridLayout(2, false));
		
		Composite compoMsgInput = new Composite(compoMsgOut, SWT.NONE);
		compoMsgInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compoMsgInput.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textSendMsg = new Text(compoMsgInput, SWT.BORDER);
		
		Composite compoSendBtn = new Composite(compoMsgOut, SWT.NONE);
		compoSendBtn.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		compoSendBtn.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		btnSend = new Button(compoSendBtn, SWT.CENTER);
		btnSend.setText("Send");
		
		Composite compoMenue = new Composite(shlColdiron, SWT.NONE);
		compoMenue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		compoMenue.setLayout(new GridLayout(1, false));
		
		Composite compoLogo = new Composite(compoMenue, SWT.NONE);
		compoLogo.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, true, 1, 1));
		compoLogo.setLayout(null);
		
		final Image logo = SWTResourceManager.getImage(Lobby.class, "/waspLogo3.png");
		Canvas canvas = new Canvas(compoLogo,SWT.NO_REDRAW_RESIZE);
		canvas.setBounds(0, 0, 300, 225);
	    canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(logo,0,0);
	        }
	    });
	    
		btnJoinGame = new Button(compoMenue, SWT.NONE);
		btnJoinGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnJoinGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnJoinGame.setText("Join Game");
		
		btnCreateGame = new Button(compoMenue, SWT.NONE);
		btnCreateGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnCreateGame.setText("Create Game");
		
		btnChgPw = new Button(compoMenue, SWT.NONE);
		btnChgPw.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnChgPw.setText("Change User Password");
		
		btnLogout = new Button(compoMenue, SWT.NONE);
		btnLogout.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLogout.setText("Logout");

	}
	
	
	public void lobby_end(){
		shlColdiron.open();
		
		while (!shlColdiron.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public Shell getShlColdiron() {
		return shlColdiron;
	}

	public Text getGobalChatTextField() {
		return gobalChatTextField;
	}
	
}
