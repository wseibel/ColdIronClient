package view.lobby;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class ProgressBarJoin {

	protected Shell shellProgressBar;
	private ProgressBar progressBar;
	
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgressBarJoin window = new ProgressBarJoin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window and joins the game
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellProgressBar.open();
		shellProgressBar.layout();

		while (!shellProgressBar.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		shellProgressBar = new Shell();
		shellProgressBar.setSize(408, 146);
		shellProgressBar.setText("Start ColdIron");
		shellProgressBar.setLayout(new GridLayout(1, false));

		Composite compoProgressBar = new Composite(shellProgressBar, SWT.NONE);
		compoProgressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				true, 1, 1));

		Composite compoText = new Composite(shellProgressBar, SWT.NONE);
		compoText.setLayout(new GridLayout(1, false));
		compoText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true,
				1, 1));

		progressBar = new ProgressBar(compoProgressBar, SWT.INDETERMINATE);
		progressBar.setSelection(50);
		progressBar.setBounds(0, 0, 386, 52);

		Label lblPleaseWaitWhile = new Label(compoText, SWT.NONE);
		lblPleaseWaitWhile.setText("please wait while loading ...");

	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public Shell getShellProgressBar() {
		return shellProgressBar;
	}
}
