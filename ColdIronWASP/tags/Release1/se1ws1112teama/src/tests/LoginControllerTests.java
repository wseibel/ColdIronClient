package tests;

import model.game.CIClient;

import org.junit.Test;

import controller.lobby.Logincontroller;

public class LoginControllerTests {

	@Test
	public void testLogincontroller() {
		
		Logincontroller login_controller = new Logincontroller(new CIClient());
        login_controller.initLoginGUIListener();
	}
}
