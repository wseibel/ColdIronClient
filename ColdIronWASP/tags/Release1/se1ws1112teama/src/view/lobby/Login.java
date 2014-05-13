package view.lobby;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.FormData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.KeyEvent;

public class Login{
   private Text textLoginName;
   
   public Text getTextLoginName() {
      return this.textLoginName;
   }
   private Text textPassword;
   
   public Text getTextPassword() {
      return this.textPassword;
   }
   private Button buttonLogin;
   
   public Button getButtonLogin() {
      return this.buttonLogin;
   }
   private Display display;
   
   public Display getDisplay() {
      return this.display;
   }
   
   public void removeYou()
   {
   }

	protected Shell shlSwtApplication;
	private Text textresponse;
	private Button btnQuit;

	public Login() {
		createContents();
	}

	/**
	  * Create contents of the window.
	 */
	protected void createContents() {

		display = new Display();
		shlSwtApplication = new Shell(display);
		shlSwtApplication.setMinimumSize(new Point(510, 250));
		shlSwtApplication.setImage(SWTResourceManager.getImage(Login.class, "/waspLogo2.png"));
		shlSwtApplication.setSize(504, 248);
		shlSwtApplication.setText("ColdIron");
		shlSwtApplication.setLayout(new GridLayout(2, false));
		
		Composite compoLogin = new Composite(shlSwtApplication, SWT.NONE);
		compoLogin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		compoLogin.setLayout(new GridLayout(1, false));
		
		Composite login_response = new Composite(compoLogin, SWT.NONE);
		login_response.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		login_response.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textresponse = new Text(login_response,  SWT.CENTER);
		textresponse.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		textresponse.setEnabled(false);
		textresponse.setEditable(false);
		textresponse.setBackground(SWTResourceManager.getColor(SWT.TRANSPARENCY_ALPHA));
		
		Composite compoLoginName = new Composite(compoLogin, SWT.NONE);
		compoLoginName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoLoginName.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textLoginName = new Text(compoLoginName, SWT.BORDER | SWT.CENTER);
		textLoginName.setText("nickname");
		textLoginName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(textLoginName.getText().equals("nickname")) {
					textLoginName.setText("");
				}	
			}
		});
		
		Composite compoPassword = new Composite(compoLogin, SWT.NONE);
		compoPassword.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoPassword.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textPassword = new Text(compoPassword, SWT.BORDER | SWT.PASSWORD | SWT.CENTER);
		textPassword.setText("password");
		textPassword.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {	
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(textPassword.getText().equals("password")) {
					textPassword.setText("");
				}
				
			}
		});
		
		Composite compoLoginBtn = new Composite(compoLogin, SWT.NONE);
		compoLoginBtn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoLoginBtn.setLayout(null);
		
		buttonLogin = new Button(compoLoginBtn, SWT.PUSH);
		buttonLogin.setText("Login");
		buttonLogin.setBounds(52, 0, 69, 25);
		
		Composite compoQuit = new Composite(compoLogin, SWT.NONE);
		compoQuit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoQuit.setLayout(new GridLayout(1, false));
		
		btnQuit = new Button(compoQuit, SWT.NONE);
		btnQuit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		btnQuit.setBounds(0, 0, 75, 25);
		btnQuit.setText("Quit");
		
		Composite compoLogo = new Composite(shlSwtApplication, SWT.NONE);
		compoLogo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		compoLogo.setBackgroundImage(null);
		compoLogo.setLayout(new FormLayout());
		
		final Image logo = SWTResourceManager.getImage(Lobby.class, "/waspLogo3.png");
		Canvas canvas = new Canvas(compoLogo,SWT.NO_REDRAW_RESIZE);
		canvas.setLayout(new GridLayout(1, false));
		FormData fd_canvas = new FormData();
		fd_canvas.height = 225;
		fd_canvas.width = 300;
		canvas.setLayoutData(fd_canvas);
	    canvas.addPaintListener(new PaintListener() {
	        public void paintControl(PaintEvent e) {
	         e.gc.drawImage(logo,0,0);
	        }
	    });
	}

	public void loginEnd(){
			    display.dispose();
	}
	public Text gettextError() {
		return textresponse;
	}

	public Shell getShlSwtApplication() {
		return shlSwtApplication;
	}
	public Button getBtnQuit() {
		return btnQuit;
	}
}
