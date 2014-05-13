package tests;

import junit.*;

import model.game.CIClient;

import org.junit.Test;

import view.lobby.*;
import controller.lobby.*;


public class ChangePasswordControllerTest {
	
	@Test
	public void testChgPwdController() {
		
		ChangePassword changePwGui = new ChangePassword();
		CIClient cIClient = new CIClient();
		ChangePasswordController chgPwCtr = new ChangePasswordController(changePwGui, cIClient);
		
		chgPwCtr.start();
	}
}
