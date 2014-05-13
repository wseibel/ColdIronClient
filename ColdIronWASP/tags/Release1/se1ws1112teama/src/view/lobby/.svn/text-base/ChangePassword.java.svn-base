package view.lobby;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ChangePassword {

	protected Shell shlChgPw;
	private Button buttonReturn;
	private Text textNewPW;
	private Text textRepeatPW;
	private Button btnChangePassword;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ChangePassword window = new ChangePassword();
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
		shlChgPw.open();
		shlChgPw.layout();
		while (!shlChgPw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shlChgPw = new Shell();
		shlChgPw.setMinimumSize(new Point(350, 160));
		shlChgPw.setImage(SWTResourceManager.getImage(ChangePassword.class, "/waspLogo2.png"));
		shlChgPw.setSize(412, 168);
		shlChgPw.setText("change Password");
		shlChgPw.setLayout(new GridLayout(1, false));
		
		Composite compoAll = new Composite(shlChgPw, SWT.NONE);
		compoAll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compoAll.setLayout(new GridLayout(2, false));
		
		Composite compoOptions = new Composite(compoAll, SWT.NONE);
		GridData gd_compoOptions = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_compoOptions.heightHint = 96;
		compoOptions.setLayoutData(gd_compoOptions);
		compoOptions.setLayout(new GridLayout(1, false));
		
		Composite compoChange = new Composite(compoOptions, SWT.NONE);
		GridData gd_compoChange = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_compoChange.heightHint = 89;
		compoChange.setLayoutData(gd_compoChange);
		compoChange.setLayout(new GridLayout(1, false));
		
		Composite compoNewPw = new Composite(compoChange, SWT.NONE);
		compoNewPw.setLayout(new GridLayout(2, false));
		compoNewPw.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewPassword = new Label(compoNewPw, SWT.NONE);
		lblNewPassword.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblNewPassword.setText("new password:");
		
		textNewPW = new Text(compoNewPw, SWT.BORDER | SWT.PASSWORD);
		textNewPW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite compoRePw = new Composite(compoChange, SWT.NONE);
		compoRePw.setLayout(new GridLayout(2, false));
		compoRePw.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(compoRePw, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_1.setText("new password:");
		
		textRepeatPW = new Text(compoRePw, SWT.BORDER | SWT.PASSWORD);
		textRepeatPW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite compoChangePW = new Composite(compoAll, SWT.NONE);
		compoChangePW.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		compoChangePW.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		btnChangePassword = new Button(compoChangePW, SWT.NONE);
		btnChangePassword.setText("change password");
		new Label(compoAll, SWT.NONE);
		
		Composite compoReturn = new Composite(compoAll, SWT.NONE);
		compoReturn.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		compoReturn.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		buttonReturn = new Button(compoReturn, SWT.NONE);
		buttonReturn.setText("Return");

	}

	public Button getButtonReturn() {
		return buttonReturn;
	}
	public Shell getShlOptions() {
		return this.shlChgPw;
	}
	public Button getBtnChangePassword() {
		return btnChangePassword;
	}
	public Text getTextRepeatPW() {
		return textRepeatPW;
	}
	public Text getTextNewPW() {
		return textNewPW;
	}
}
