package controller.lobby;

import java.beans.PropertyChangeEvent;

import model.game.CIClient;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;

import view.lobby.ChangePassword;

public class ChangePasswordController extends AbstractController {

	private ChangePassword changePwGui;
	private CIClient cIClient;

	/**
	 * Constructor
	 * @param changePwGui
	 * @param cIClient
	 */
	public ChangePasswordController(ChangePassword changePwGui, CIClient cIClient) {
		this.changePwGui = changePwGui;
		this.cIClient = cIClient;
	}
	
	/**
	 * Starts GUI
	 * @see controller.lobby.AbstractController#start()
	 */
	public void start() {
		Display display = Display.getDefault();
		
		changePwGui.createContents();
		changePwGui.getShlOptions().open();
		changePwGui.getShlOptions().layout();
		
		super.start();
		
		while(!changePwGui.getShlOptions().isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Stops Controller and Gui
	 * @see controller.lobby.AbstractController#stop()
	 */
	public void stop() {
		super.stop();
		changePwGui.getShlOptions().dispose();
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
	}

	@Override
	public void refreshUI() {
	}

	/**
	 * listens to changes in gui. for example: click return button
	 * @see controller.lobby.AbstractController#registerListeners()
	 */
	@Override
	public void registerListeners() {
		changePwGui.getButtonReturn().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ChangePasswordController.this.stop();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		changePwGui.getTextNewPW().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					changePwGui.getTextRepeatPW().setFocus();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		changePwGui.getTextRepeatPW().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.keyCode == 13) {
					ChangePasswordController.this.changePassword();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		changePwGui.getBtnChangePassword().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ChangePasswordController.this.changePassword();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}
	
	/**
	 * passes passwords to model
	 */
	private void changePassword() {
		String repeatPw = changePwGui.getTextRepeatPW().getText();
		String newPw = changePwGui.getTextNewPW().getText();
		cIClient.getServerConnection().changePassword(newPw, repeatPw);
		changePwGui.getShlOptions().dispose();
	}

}
