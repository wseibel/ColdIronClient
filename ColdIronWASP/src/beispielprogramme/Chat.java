package beispielprogramme;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class Chat{
   private Text textChatMessage;
   
   public Text getTextChatMessage() {
      return this.textChatMessage;
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
   
   public Button getBtnOptions() {
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
	private Text text;

	public Chat(Display display){
		this.display = display;
		createContents();
	}

	/**
	  * Create contents of the window.
	 */
	public void createContents() {
		
		shlColdiron = new Shell(display);
		shlColdiron.setMinimumSize(new Point(730, 510));
		shlColdiron.setImage(SWTResourceManager.getImage(Chat.class, "/waspLogo2.png"));
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
		
		List listGames = new List(compoGames, SWT.BORDER);
		GridData gd_listGames = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_listGames.minimumWidth = 90;
		listGames.setLayoutData(gd_listGames);
		
		Composite copmpoPlayers = new Composite(compoGameInfo, SWT.NONE);
		copmpoPlayers.setLayout(new GridLayout(1, false));
		copmpoPlayers.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		List listPlayers = new List(copmpoPlayers, SWT.BORDER);
		GridData gd_listPlayers = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_listPlayers.minimumWidth = 90;
		listPlayers.setLayoutData(gd_listPlayers);
		
		Composite compoChat = new Composite(compoInfo, SWT.NONE);
		compoChat.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoChat.setLayout(new GridLayout(1, false));
		
		TabFolder tabFolderMsgInput = new TabFolder(compoChat, SWT.NONE);
		tabFolderMsgInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabFolder tabItem = new CTabFolder(tabFolderMsgInput, 0);
		
		TabItem tabItem_1 = new TabItem(tabFolderMsgInput, SWT.NONE);
		tabItem_1.setText("New Item");
		
		text = new Text(tabFolderMsgInput, SWT.NONE);
		tabItem_1.setControl(text);
		
		Composite compoMsgOut = new Composite(compoChat, SWT.NONE);
		compoMsgOut.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		compoMsgOut.setLayout(new GridLayout(2, false));
		
		Composite compoMsgInput = new Composite(compoMsgOut, SWT.NONE);
		compoMsgInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compoMsgInput.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textChatMessage = new Text(compoMsgInput, SWT.BORDER);
		
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
		
		final Image logo = SWTResourceManager.getImage(Chat.class, "/waspLogo3.png");
		Canvas canvas = new Canvas(compoLogo,SWT.NO_REDRAW_RESIZE);
		canvas.setBounds(0, 0, 300, 225);
	    canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(logo,0,0);
	        }
	    });
	    
		btnJoinGame = new Button(compoMenue, SWT.NONE);
		btnJoinGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnJoinGame.setText("Join Game");
		
		btnCreateGame = new Button(compoMenue, SWT.NONE);
		btnCreateGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnCreateGame.setText("Create Game");
		
		btnChgPw = new Button(compoMenue, SWT.NONE);
		btnChgPw.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnChgPw.setText("Change User Pasword");
		
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
}