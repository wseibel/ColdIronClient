package view.game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import mdes.slick.sui.*;
import model.game.CIClient;
import model.game.User;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;

public class Chat {

	private TextArea chatOutput;
	private Label chatSelectedPlayerArea;
	private TextField chatInput;
	private GameContainer container;
	private Font fontChatCurrent;
	private Font fontChatR1920;
	private Font fontChatR1600;
	private Font fontChatR1280;
	private Font fontChatR800;

	private String chatText = "HAVE FUN!";
	private String output;
	private CIClient ciClient;

	// buttons
	private MouseOverArea buttonAudienceDec;
	private MouseOverArea buttonAudienceInc;
	private MouseOverArea buttonReplicentDec;
	private MouseOverArea buttonReplicentInc;
	private Image buttonIconDec;
	private Image buttonIconInc;
	private String audience = "TEAM";
	private String recipient;
	private int selectreplicent = 0;
	private int selectAudience = 3;
	private int amountofReplicent = 0;
	private int maxAudience=0;
	private int revMsg;
	private int revMsgMax = 4;
	Iterator<User> iter;

	// Resolution/scaling Var.
	private int chatHeight = 0;
	private int chatOutputPosY = 0;
	private int chatInputPosY = 0;
	private int chatButtonsPosY = 0;

	private String[] incomingMsg;
	private String[] replicentArray = new String[40];
	private String[] audienceArray = { "ALL", "USER", "TEAM", "ALLIANCE" };
	private String[] players = new String[20];
	private String[] teams = new String[20];
	private String[] ally = new String[20];
	private String[] currentReplicent = new String[20];
	private Button recipientButton;
	private Button audienceButton;

	// Colors
	private static final Color transparent = new Color(0.8f, 0.8f, 0.8f, 0.8f);

	public Chat(GameContainer container, CIClient ciClient) {
		this.container = container;
		this.ciClient = ciClient;
	}

	private void scaleChatToResolution() {

		if (container.getWidth() == 800) {
			chatHeight = 4;
			chatOutputPosY = 250;
			chatInputPosY = 320;
			chatButtonsPosY = 235;
			fontChatCurrent = fontChatR800;
			revMsgMax = 4;
		} else if (container.getWidth() == 1280) {
			chatHeight = 7;
			chatOutputPosY = 390;
			chatInputPosY = 510;
			chatButtonsPosY = 365;
			fontChatCurrent = fontChatR1280;
			revMsgMax = 6;
		} else if (container.getWidth() == 1600) {
			chatHeight = 8;
			chatOutputPosY = 500;
			chatInputPosY = 638;
			chatButtonsPosY = 470;
			fontChatCurrent = fontChatR1600;
			revMsgMax = 7;
		} else if (container.getWidth() == 1920) {
			chatHeight = 10;
			chatOutputPosY = 600;
			chatInputPosY = 770;
			chatButtonsPosY = 560;
			fontChatCurrent = fontChatR1920;
			revMsgMax = 9;
		}
	}

	// Chatstyle
	private void style() {
		chatInput.setBackgroundColor(transparent);
		chatInput.setBorderColor(null);
		chatInput.setTextColor(Color.blue);
		chatOutput.setBackground(transparent);
		chatOutput.setForeground(Color.blue);

		chatOutput.setFont(fontChatCurrent);
		chatText = "";
	}

	// def. new recipient and audience
	private void chooseRecipient() {

		audience = audienceArray[selectAudience];

		if (audience.equals("USER")) {
			currentReplicent = new String[players.length];
			System.arraycopy(players, 0, currentReplicent, 0, players.length);
			if (currentReplicent.length <= selectreplicent) {
				selectreplicent = 0;
			}
		}

		if (audience.equals("TEAM")) {
			currentReplicent = new String[teams.length];
			System.arraycopy(teams, 0, currentReplicent, 0, teams.length);
			if (currentReplicent.length <= selectreplicent) {
				selectreplicent = 0;
			}
		}

		if (audience.equals("ALLIANCE")) {
			currentReplicent = new String[ally.length];
			System.arraycopy(ally, 0, currentReplicent, 0, ally.length);
			if (currentReplicent.length <= selectreplicent) {
				selectreplicent = 0;
			}
		}
		recipient = currentReplicent[selectreplicent];

		if (audience.equals("ALL")) {
			recipient = "";
			selectreplicent = 0;
		}
	}

	@SuppressWarnings("deprecation")
	public void init() throws SlickException {

		// init Fonts for Resolutions
		fontChatR1920 = new TrueTypeFont(new java.awt.Font("Verdana",
				java.awt.Font.PLAIN, 14), true);
		fontChatR1600 = new TrueTypeFont(new java.awt.Font("Verdana",
				java.awt.Font.PLAIN, 14), true);
		fontChatR1280 = new TrueTypeFont(new java.awt.Font("Verdana",
				java.awt.Font.PLAIN, 12), true);
		fontChatR800 = new TrueTypeFont(new java.awt.Font("Verdana",
				java.awt.Font.PLAIN, 10), true);

		scaleChatToResolution();

		// fill audience arrays
		int iPlayer = 0;
		int iTeams = 0;
		int iAlly = 0;

		replicentArray[amountofReplicent] = "";
		teams[iTeams] = "";
		ally[iAlly] = "";
		amountofReplicent++;
		iter = ciClient.getGame().iteratorOfUser();
		while (iter.hasNext()) {
			User user = iter.next();
			if (user.getUserAssets() != null) {
				// players in this game
				replicentArray[amountofReplicent] = user.getNickname();
				players[iPlayer] = user.getNickname();

				boolean allyExsist = false;
				boolean teamExsist = false;

				//could be smaller, but I am to lazy to do this :D
				if (user.getUserAssets().getAlliance() != null) {
					maxAudience=audienceArray.length-1;

					String teamName = user.getUserAssets().getUser().getTeam()
							.getName();
					String allyName = user.getUserAssets().getAlliance()
							.getName();

					for (int i = 0; i < amountofReplicent; i++) {
						if (replicentArray[i].equals(allyName)) {
							allyExsist = true;
						}
					}
					if (allyExsist == false) {
						amountofReplicent++;
						replicentArray[amountofReplicent] = allyName;
						ally[iAlly] = allyName;

						iAlly++;
					}
					for (int i = 0; i < amountofReplicent; i++) {
						if (replicentArray[i].equals(teamName)) {
							teamExsist = true;
						}
					}
					if (teamExsist == false) {
						amountofReplicent++;
						replicentArray[amountofReplicent] = teamName;
						teams[iTeams] = teamName;

						iTeams++;
					}

				}
				else{
					selectAudience=1;
					maxAudience=audienceArray.length-2;
					
					String teamName = user.getUserAssets().getUser().getTeam()
							.getName();
					
					for (int i = 0; i < amountofReplicent; i++) {
						if (replicentArray[i].equals(teamName)) {
							teamExsist = true;
						}
					}
					if (teamExsist == false) {
						teams[iTeams] = teamName;

						iTeams++;
					}
					
					
				}
				amountofReplicent++;
				iPlayer++;
			}
		}
		// set default recipient
		chooseRecipient();

		// Buttons for the Audience
		buttonIconDec = new Image("res/IngameChat/buttonIconDec.png");
		buttonIconInc = new Image("res/IngameChat/buttonIconInc.png");

		buttonAudienceDec = new MouseOverArea(
				container,
				buttonIconDec.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				0, chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if (selectAudience > 0)
							selectAudience--;

						chooseRecipient();
					}
				});

		buttonAudienceInc = new MouseOverArea(
				container,
				buttonIconInc.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				buttonAudienceDec.getX() + buttonAudienceDec.getWidth(),
				chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if (selectAudience < maxAudience)
							selectAudience++;

						chooseRecipient();
					}
				});

		audienceButton = new Button(audience);
		audienceButton.setLocation(
				buttonAudienceInc.getX() + buttonAudienceInc.getWidth(),
				chatButtonsPosY - 3);

		recipientButton = new Button(recipient);
		recipientButton.setLocation(
				audienceButton.getX() + audienceButton.getWidth() + 2,
				chatButtonsPosY - 3);

		// Buttons for the Replicent
		buttonReplicentDec = new MouseOverArea(
				container,
				buttonIconDec.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				(int) (recipientButton.getX() + recipientButton.getWidth()),
				chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if (selectreplicent > 0)
							selectreplicent--;

						chooseRecipient();
					}
				});

		buttonReplicentInc = new MouseOverArea(
				container,
				buttonIconInc.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				buttonReplicentDec.getX() + buttonReplicentDec.getWidth(),
				chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if ((selectreplicent < (currentReplicent.length - 1))
								&& (!currentReplicent[selectreplicent + 1]
										.equals("")))
							selectreplicent++;

						chooseRecipient();
					}
				});

		// Input
		chatInput = new TextField(
				container,
				fontChatCurrent,
				// int x
				0,
				// int y
				chatInputPosY,
				// int width
				(int) (container.getWidth() - ((((float) container.getWidth()) / 1920) * 1445)),
				// int height
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 1050)),
				new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						chatInput.setFocus(true);
						if (chatInput.getText().equals("")) {
							return;
						}

						// Server Communication
						ciClient.getServerConnection().message(
								chatInput.getText(), audience, recipient);

						revMsg++;
						if (revMsgMax <= revMsg) {
							chatText = chatInput.getText();
							revMsg = 0;
						} else {
							if(!recipient.equals(""))
							{
							chatText += "\nYOU-"+recipient+": " + chatInput.getText();
							}
							else{
								chatText += "\nYOU-"+audience+": " + chatInput.getText();	
							}
						}
						chatOutput.setText(chatText);
						revMsg++;
						chatInput.setText("");
					}

				});
		// start off with focused field
		chatInput.setFocus(true);

		// Output
		chatOutput = new TextArea(chatText, 16, chatHeight);
		chatOutput.setLocation(0, chatOutputPosY);
		chatOutput
				.setSize((int) (container.getWidth() - ((((float) container
						.getWidth()) / 1920) * 1445)), 1);

		ciClient.getChainMaster().addPropertyChangeListener(
				ciClient.getChainMaster().PROPERTY_CHAT_STRING,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						incomingMsg = ((String) evt.getNewValue()).split(":");
						// MSG_TYPE:OWNER:MSG
						System.out.println(incomingMsg);
						if (incomingMsg[0].equals("TEAM_MESSAGE"))
							output = incomingMsg[1] + "-TEAM:"
									+ incomingMsg[2];
						if (incomingMsg[0].equals("ALLIANCE_MESSAGE"))
							output = incomingMsg[1] + "-ALLY:"
									+ incomingMsg[2];
						if (incomingMsg[0].equals("USER_MESSAGE"))
							output = incomingMsg[1] + ":" + incomingMsg[2];

						if (incomingMsg[0].equals("ERROR"))
							output = incomingMsg[1] + ":" + incomingMsg[2];
						if (incomingMsg[0].equals("MESSAGE"))
							output = incomingMsg[1] + ":" + incomingMsg[2];
						if (incomingMsg[0].equals("PUBLIC_MESSAGE"))
							output = incomingMsg[1] + "-ALL:" + incomingMsg[2];
						if (incomingMsg[0].equals("ERROR_MESSAGE"))
							output = incomingMsg[1] + ":" + incomingMsg[2];

						if (revMsgMax <= revMsg) {
							chatText = "";
							chatOutput.setText(chatText);
							chatText += output;
							revMsg = 0;
						} else {
							chatText += "\n" + output;
						}
						revMsg++;

						chatOutput.setText(chatText);
					}
				});

		// Chat style
		style();
	}

	public void update() throws SlickException {

		scaleChatToResolution();
		
		buttonAudienceDec = new MouseOverArea(
				container,
				buttonIconDec.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				0, chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if (selectAudience > 0)
							selectAudience--;

						chooseRecipient();
					}
				});

		buttonAudienceInc = new MouseOverArea(
				container,
				buttonIconInc.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				buttonAudienceDec.getX() + buttonAudienceDec.getWidth(),
				chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if (selectAudience < maxAudience)
							selectAudience++;

						chooseRecipient();
					}
				});

		audienceButton = new Button(audience);
		audienceButton.setLocation(
				buttonAudienceInc.getX() + buttonAudienceInc.getWidth(),
				chatButtonsPosY - 3);

		recipientButton = new Button(recipient);
		recipientButton.setLocation(
				audienceButton.getX() + audienceButton.getWidth() + 2,
				chatButtonsPosY - 3);

		// Buttons for the Replicent
		buttonReplicentDec = new MouseOverArea(
				container,
				buttonIconDec.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				(int) (recipientButton.getX() + recipientButton.getWidth()),
				chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if (selectreplicent > 0)
							selectreplicent--;

						chooseRecipient();
					}
				});

		buttonReplicentInc = new MouseOverArea(
				container,
				buttonIconInc.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				buttonReplicentDec.getX() + buttonReplicentDec.getWidth(),
				chatButtonsPosY, new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						if ((selectreplicent < (currentReplicent.length - 1))
								&& (!currentReplicent[selectreplicent + 1]
										.equals("")))
							selectreplicent++;

						chooseRecipient();
					}
				});

		// Input
		chatInput = new TextField(
				container,
				fontChatCurrent,
				// int x
				0,
				// int y
				chatInputPosY,
				// int width
				(int) (container.getWidth() - ((((float) container.getWidth()) / 1920) * 1445)),
				// int height
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 1050)),
				new ComponentListener() {
					public void componentActivated(AbstractComponent source) {
						chatInput.setFocus(true);
						if (chatInput.getText().equals("")) {
							return;
						}

						// Server Communication
						ciClient.getServerConnection().message(
								chatInput.getText(), audience, recipient);

						revMsg++;
						if (revMsgMax <= revMsg) {
							chatText = chatInput.getText();
							revMsg = 0;
						} else {
							if(!recipient.equals(""))
							{
							chatText += "\nYOU-"+recipient+": " + chatInput.getText();
							}
							else{
								chatText += "\nYOU-"+audience+": " + chatInput.getText();	
							}
						}
						chatOutput.setText(chatText);
						revMsg++;
						chatInput.setText("");
					}

				});
		// start off with focused field
		chatInput.setFocus(true);

		// Output
		chatOutput = new TextArea(chatText, 16, chatHeight);
		chatOutput.setLocation(0, chatOutputPosY);
		chatOutput
				.setSize((int) (container.getWidth() - ((((float) container
						.getWidth()) / 1920) * 1445)), 1);

		// Chat style
		style();
		
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
			container.exit();
	}

	public void render(Graphics g) throws SlickException {
		chatOutput.render(container, g);
		// chatSelectedPlayerArea.render(container, g);
		chatInput.render(container, g);

		// render audience and recipient buttons
		buttonAudienceDec.render(container, g);
		buttonAudienceDec.setNormalColor(new Color(1, 1, 1, 0.8f));
		buttonAudienceInc.render(container, g);
		buttonAudienceInc.setNormalColor(new Color(1, 1, 1, 0.8f));
		buttonReplicentDec.render(container, g);
		buttonReplicentDec.setNormalColor(new Color(1, 1, 1, 0.8f));
		buttonReplicentInc.render(container, g);
		buttonReplicentInc.setNormalColor(new Color(1, 1, 1, 0.8f));

		recipientButton.setText(recipient);
		audienceButton.setText(audience);
		recipientButton.render(container, g);
		audienceButton.render(container, g);

		g.setFont(fontChatCurrent);
	}

	public Font getFontChatCurrent() {
		return fontChatCurrent;
	}
}