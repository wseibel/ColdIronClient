package view.lobby;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class GameLobby {
	private Text textChatMessage;

	public Text getTextChatMessage() {
		return this.textChatMessage;
	}

	private Text text;

	public Text getText() {
		return this.text;
	}

	private Button btnStart;

	public Button getBtnStart() {
		return this.btnStart;
	}

	private Button btnCancel;

	public Button getBtnCancel() {
		return this.btnCancel;
	}

	private List list;

	public List getList() {
		return this.list;
	}

	private Button btnSend;

	public Button getBtnSend() {
		return this.btnSend;
	}

	private List listMaps;

	public List getListMaps() {
		return this.listMaps;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	private List listPlayers;

	public List getListPlayers() {
		return this.listPlayers;
	}

	public void removeYou() {
	}

	protected Shell shlColdiron;
	private Text textAddUsr;
	private Button btnAddUsr;
	private Text gobalChatTextField;

	public Shell getShlColdiron() {
		return shlColdiron;
	}

	public void setShlColdiron(Shell shlColdiron) {
		this.shlColdiron = shlColdiron;
	}

	public static void main(String[] args) {
		try {
			GameLobby window = new GameLobby();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlColdiron.open();
		shlColdiron.layout();
		while (!shlColdiron.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shlColdiron = new Shell();
		shlColdiron.setMinimumSize(new Point(790, 450));
		shlColdiron.setImage(SWTResourceManager.getImage(GameLobby.class,
				"/waspLogo2.png"));
		shlColdiron.setSize(791, 450);
		shlColdiron.setText("ColdIron");
		shlColdiron.setLayout(new GridLayout(3, false));

		Composite compoInfo = new Composite(shlColdiron, SWT.NONE);
		compoInfo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		compoInfo.setLayout(new GridLayout(1, false));

		Composite compoGameInfo = new Composite(compoInfo, SWT.NONE);
		compoGameInfo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		compoGameInfo.setLayout(new GridLayout(2, false));

		Composite compoMaps = new Composite(compoGameInfo, SWT.NONE);
		compoMaps.setLayout(new GridLayout(1, false));
		compoMaps.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		Label lblNewLabel_1 = new Label(compoMaps, SWT.NONE);
		lblNewLabel_1.setText("Map List");

		listMaps = new List(compoMaps, SWT.BORDER);
		GridData gd_listMaps = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1);
		gd_listMaps.minimumWidth = 90;
		listMaps.setLayoutData(gd_listMaps);

		Composite compoPlayers = new Composite(compoGameInfo, SWT.NONE);
		compoPlayers.setLayout(new GridLayout(1, false));
		compoPlayers.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		Label lblPlayerAndTeam = new Label(compoPlayers, SWT.NONE);
		lblPlayerAndTeam.setText("Player and Team List");

		listPlayers = new List(compoPlayers, SWT.BORDER);
		GridData gd_listPlayers = new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1);
		gd_listPlayers.minimumWidth = 90;
		listPlayers.setLayoutData(gd_listPlayers);

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
		tabGlobalChat.setControl(gobalChatTextField);
		
		Composite compoMsgOut = new Composite(compoChat, SWT.NONE);
		compoMsgOut.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		compoMsgOut.setLayout(new GridLayout(2, false));
		
		Composite compoMsgInput = new Composite(compoMsgOut, SWT.NONE);
		compoMsgInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));
		compoMsgInput.setLayout(new FillLayout(SWT.HORIZONTAL));

		textChatMessage = new Text(compoMsgInput, SWT.BORDER);

		Composite compoSendBtn = new Composite(compoMsgOut, SWT.NONE);
		compoSendBtn.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false,
				false, 1, 1));
		compoSendBtn.setLayout(new RowLayout(SWT.HORIZONTAL));

		btnSend = new Button(compoSendBtn, SWT.CENTER);
		btnSend.setText("Send");

		Composite compoListPlayer = new Composite(shlColdiron, SWT.NONE);
		GridData gd_compoListPlayer = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compoListPlayer.widthHint = 99;
		compoListPlayer.setLayoutData(gd_compoListPlayer);
		compoListPlayer.setLayout(new GridLayout(1, false));

		Composite compoNmbPlr = new Composite(compoListPlayer, SWT.NONE);
		compoNmbPlr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		compoNmbPlr.setLayout(new GridLayout(2, false));

		Label lblNumberPlayers = new Label(compoNmbPlr, SWT.NONE);
		lblNumberPlayers.setText("AI's");

		final ToolBar toolBarDropDown = new ToolBar(compoNmbPlr, SWT.FLAT
				| SWT.RIGHT);
		final ToolItem tltmPlayers = new ToolItem(toolBarDropDown,
				SWT.DROP_DOWN);
		tltmPlayers.setToolTipText("");
		tltmPlayers.setText("1");
		tltmPlayers.setEnabled(true);

		DropdownSelectionListener listenerOne = new DropdownSelectionListener(
				tltmPlayers);
		for(int i=0;i<9;i++) {
			listenerOne.add(String.valueOf(i));
		}
		tltmPlayers.addSelectionListener(listenerOne);



		// ArrayList<Integer> cbValues = new ArrayList<Integer>();
		// cbValues.add(0);
		// cbValues.add(1);
		// cbValues.add(2);
		//
		// String[] sl = cbValues.toArray(new String[cbValues.size()]);
		// tltmPlayers.setData(sl);
		//
		Composite compoAllowedPl = new Composite(compoListPlayer, SWT.NONE);
		compoAllowedPl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		compoAllowedPl.setLayout(new GridLayout(1, false));

		Label lblJoin = new Label(compoAllowedPl, SWT.NONE);
		lblJoin.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1));
		lblJoin.setText("Allowed Player(s)");

		list = new List(compoAllowedPl, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_list.minimumWidth = 90;
		list.setLayoutData(gd_list);
		list.setSize(143, 68);
		
		Composite compoAddUsr = new Composite(compoListPlayer, SWT.NONE);
		compoAddUsr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compoAddUsr.setLayout(new GridLayout(2, false));
		
		textAddUsr = new Text(compoAddUsr, SWT.BORDER);
		textAddUsr.setText("username");
		textAddUsr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textAddUsr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textAddUsr.setText("");
			}
		});
		
		btnAddUsr = new Button(compoAddUsr, SWT.NONE);
		btnAddUsr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnAddUsr.setText("add");

		Composite compoMenue = new Composite(shlColdiron, SWT.NONE);
		compoMenue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		compoMenue.setLayout(new GridLayout(1, false));

		Composite compoLogo = new Composite(compoMenue, SWT.NONE);
		compoLogo.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, true,
				1, 1));
		compoLogo.setLayout(null);

		final Image logo = SWTResourceManager.getImage(GameLobby.class,
				"/waspLogo3.png");
		Canvas canvas = new Canvas(compoLogo, SWT.NO_REDRAW_RESIZE);
		canvas.setBounds(0, 0, 300, 225);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(logo, 0, 0);
			}
		});

		Composite compoGameName = new Composite(compoMenue, SWT.NONE);
		compoGameName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		compoGameName.setLayout(new GridLayout(2, false));

		Label lblGame = new Label(compoGameName, SWT.NONE);
		lblGame.setText("Game Name:");

		text = new Text(compoGameName, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnStart = new Button(compoMenue, SWT.NONE);
		btnStart.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		btnStart.setText("Start Game");

		btnCancel = new Button(compoMenue, SWT.NONE);
		btnCancel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		btnCancel.setText("Cancel");

	}

	/**
	 * This class provides the "drop down" functionality for our dropdown tool
	 * items.
	 */
	class DropdownSelectionListener extends SelectionAdapter {
		private ToolItem dropdown;
		private Menu menu;

		/**
		 * Constructs a DropdownSelectionListener
		 * 
		 * @param dropdown
		 *            the dropdown this listener belongs to
		 */
		public DropdownSelectionListener(ToolItem dropdown) {
			this.dropdown = dropdown;
			menu = new Menu(dropdown.getParent().getShell());
		}

		/**
		 * Adds an item to the dropdown list
		 * 
		 * @param item
		 *            the item to add
		 */
		public void add(String item) {
			MenuItem menuItem = new MenuItem(menu, SWT.NONE);
			menuItem.setText(item);
			menuItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MenuItem selected = (MenuItem) event.widget;
					dropdown.setText(selected.getText());
				}
			});
		}

		/**
		 * Called when either the button itself or the dropdown arrow is clicked
		 * 
		 * @param event
		 *            the event that trigged this call
		 */
		public void widgetSelected(SelectionEvent event) {
			// If they clicked the arrow, we show the list
			if (event.detail == SWT.ARROW) {
				// Determine where to put the dropdown list
				ToolItem item = (ToolItem) event.widget;
				Rectangle rect = item.getBounds();
				Point pt = item.getParent()
						.toDisplay(new Point(rect.x, rect.y));
				menu.setLocation(pt.x, pt.y + rect.height);
				menu.setVisible(true);
			} else {
				// They pushed the button; take appropriate action
				// ToolBarComplex.showMessage(dropdown.getParent().getShell(),
				// dropdown
				// .getText()
				// + " Pressed");
			}
		}
	}

	public Text getTextAddUsr() {
		return textAddUsr;
	}
	public Button getBtnAddUsr() {
		return btnAddUsr;
	}
	public List getListAllowedPlayers() {
		return list;
	}

	public Text getGobalChatTextField() {
		return gobalChatTextField;
	}
}
