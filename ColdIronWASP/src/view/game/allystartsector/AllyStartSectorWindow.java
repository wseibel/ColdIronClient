package view.game.allystartsector;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import mdes.slick.sui.Button;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.MouseEvent;
import mdes.slick.sui.event.MouseListener;
import model.game.Alliance;
import model.game.CIClient;
import model.game.Field;
import model.game.Game;
import model.game.Map;
import model.game.Resource;
import model.game.Sector;
import model.game.User;
import model.game.UserAssets;

import org.lwjgl.input.Mouse;
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
import org.newdawn.slick.opengl.ImageData;

public class AllyStartSectorWindow implements ComponentListener {
	private static CIClient ciClient;
	private Image imgOneField;

	private MouseOverArea sectorFields[];
	private String message = "";
	private ArrayList<Sector> sectors = new ArrayList<Sector>();
	private static ArrayList<User> players = new ArrayList<User>();
	private static ArrayList<Alliance> alliances = new ArrayList<Alliance>();

	private TextField allianceField;
	private int currentAllyColorListIndex;

	private LinkedHashMap<String, Color> colorMap = new LinkedHashMap<String, Color>();
	private static ArrayList<String> colorList = new ArrayList<String>();
	private static GameContainer container;
	private MouseOverArea colorMouseOverArea;

	private Image img;
	private Image imgBorder;
	private MouseOverArea btnAi;
	private MouseOverArea btnCreateAlliance;
	private MouseOverArea btnJoinAlliance;
	private MouseOverArea btnLeaveAlliance;
	private MouseOverArea btnStartGame;
	private Image imgAiOff;
	private Image imgAiOn;
	private Image imgCreateAlliance;
	private Image imgJoinAlliance;
	private Image imgLeaveAlliance;
	private Image imgStartGame;

	private Display display;
	private Button tip;
	private boolean started = false;

	private int XtoStartFrom; // the X-coordinate where to start drawing the
								// minimap
	private int YtoStartFrom; // the Y-coordinate where to start drawing the
								// minimap
	private int fieldLength; // the length of one displayed sector-square
	private int minimapWidth;
	private int minimapHeigth;
	private int allianceStart = 0;
	private Button incAlliance;
	private Button decAlliance;
	private Boolean ai = false;

	public Boolean getAi() {
		return ai;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public CIClient getCiClient() {
		return ciClient;
	}

	public void setCiClient(CIClient ciClient) {
		this.ciClient = ciClient;
	}

	public AllyStartSectorWindow(GameContainer container, CIClient ciClient) {
		this.ciClient = ciClient;
		this.container = container;
	}

	public AllyStartSectorWindow(GameContainer container, CIClient ciClient,
			Boolean started) {
		this.ciClient = ciClient;
		this.container = container;
		this.started = started;
	}

	public void init() throws SlickException {

		colorMap.put("red", Color.red);
		colorList.add("red");
		colorMap.put("yellow", Color.yellow);
		colorList.add("yellow");
		colorMap.put("blue", Color.blue);
		colorList.add("blue");
		colorMap.put("green", Color.green);
		colorList.add("green");

		display = new Display(container);

		tip = new Button();
		// tip.setFont(font);
		tip.setVisible(false);
		display.add(tip);

		incAlliance = new Button();
		incAlliance.setText("prev");
		incAlliance.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (allianceStart > 0) {
					allianceStart--;
				}
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

		});
		display.add(incAlliance);

		decAlliance = new Button();
		decAlliance.setText("next");
		decAlliance.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if ((allianceStart + 1) > (alliances.size() - 1)) {
					// allianceStart = alliances.size();
				} else {
					allianceStart++;
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {

			}
		});
		display.add(decAlliance);

		TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Verdana",
				java.awt.Font.PLAIN, 14), true);

		currentAllyColorListIndex = 0;

		imgBorder = new Image("res/Ingame/border.png");
		imgOneField = new Image("res/Ingame/maps/Aipus_Island/Ground.png");
		imgAiOff = new Image(
				"/res/Ingame/AllyStartSectorWindow/Buttons/aiOff.png");
		imgAiOn = new Image(
				"/res/Ingame/AllyStartSectorWindow/Buttons/aiOn.png");
		imgCreateAlliance = new Image(
				"/res/Ingame/AllyStartSectorWindow/Buttons/createAlliance.png");
		imgJoinAlliance = new Image(
				"/res/Ingame/AllyStartSectorWindow/Buttons/joinAlliance.png");
		imgLeaveAlliance = new Image(
				"/res/Ingame/AllyStartSectorWindow/Buttons/leaveAlliance.png");
		imgStartGame = new Image(
				"/res/Ingame/AllyStartSectorWindow/Buttons/startGame.png");

		img = new Image(new ImageData() {

			@Override
			public int getWidth() {
				return 0;
			}

			@Override
			public int getTexWidth() {
				return 0;
			}

			@Override
			public int getTexHeight() {
				return 0;
			}

			@Override
			public ByteBuffer getImageBufferData() {
				return null;
			}

			@Override
			public int getHeight() {
				return 0;
			}

			@Override
			public int getDepth() {
				return 0;
			}
		});
		img.setAlpha(0);

		start();

		if (ciClient != null) {
			if (ciClient.getGame() != null) {
				if (ciClient.getGame().getMap() != null) {
					if (ciClient.getGame().getMap().iteratorOfSector() != null) {

						int maxXCount = 0; // stores max number of horizontal
											// neighboring sectors
						int maxYCount = 0; // stores max number of vertical
											// neighboring sectors
						Sector s;
						int i = 0;
						Iterator<model.game.Sector> t = ciClient.getGame()
								.getMap().iteratorOfSector();
						while (t.hasNext()) {
							s = ((model.game.Sector) t.next());
							if (s.getXCoordinate() > maxXCount) {
								maxXCount = s.getXCoordinate();
							}
							if (s.getYCoordinate() > maxYCount) {
								maxYCount = s.getYCoordinate();
							}
							i++;

						}
						maxXCount++;
						maxYCount++;

						minimapWidth = (int) ((float) container.getWidth() / 1920 * 500);
						minimapHeigth = (int) ((float) container.getHeight() / 1080 * 500);
						XtoStartFrom = 100;
						YtoStartFrom = 100;

						if (maxYCount >= maxXCount) {
							// the height is limiting the fitting of the
							// minimapsectors into the minimap
							fieldLength = minimapHeigth / (maxYCount);

							XtoStartFrom = XtoStartFrom
									+ ((minimapWidth - (fieldLength * (maxXCount))) / 2);
						} else {
							// the minimapsectors have more height than width
							if (maxXCount / maxYCount > (minimapWidth / minimapHeigth)) {
								// the width is limiting the fitting of the
								// minimapsectors into the minimap
								fieldLength = minimapWidth / (maxXCount);

								YtoStartFrom = YtoStartFrom
										+ ((minimapHeigth - (fieldLength * (maxYCount))) / 2);
							} else {
								// the height is limiting the fitting of the
								// minimapsectors into the minimap
								fieldLength = minimapHeigth / (maxYCount);

								XtoStartFrom = XtoStartFrom
										+ ((minimapWidth - (fieldLength * (maxXCount))) / 2);
							}
						}
						sectorFields = new MouseOverArea[i];
						t = ciClient.getGame().getMap().iteratorOfSector();
						i = 0;
						while (t.hasNext()) {
							s = ((model.game.Sector) t.next());
							System.out.println("sector found: " + s.getId()
									+ "(" + s.getXCoordinate() + "/"
									+ s.getYCoordinate() + ")");
							sectors.add(s);

							sectorFields[i] = new MouseOverArea(container,
									imgOneField.getScaledCopy(fieldLength,
											fieldLength),
									(XtoStartFrom + s.getXCoordinate()
											* fieldLength),
									(YtoStartFrom + s.getYCoordinate()
											* fieldLength), this);

							i++;
						}
					} else {
						System.out
								.println("ciClient.getGame().getMap().iteratorOfSector() == null");
					}
				} else {
					System.out.println("ciClient.getGame().getMap() == null");
				}
			} else {
				System.out.println("ciClient.getGame() == null");
			}
		} else {
			System.out.println("ciClient == null");
		}

		allianceField = new TextField(
				container,
				font,
				minimapWidth + XtoStartFrom
						+ (int) ((float) container.getWidth() / 100 * 9),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				100, 20, null);

		colorMouseOverArea = new MouseOverArea(
				container,
				img,
				minimapWidth + XtoStartFrom
						+ (int) ((float) container.getWidth() / 100 * 5),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				15, 15, this);

		btnStartGame = new MouseOverArea(
				container,
				imgStartGame.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				(int) (minimapWidth + XtoStartFrom
						+ (float) container.getWidth() / 100 * 9
						+ allianceField.getWidth() + 0
						* (float) imgCreateAlliance.getWidth() / 1920
						* container.getWidth() + 1 * (float) container
						.getWidth() / 100 * 2),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 180)),
				this);

		btnAi = new MouseOverArea(
				container,
				imgAiOff.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				minimapWidth + XtoStartFrom
						+ (int) ((float) container.getWidth() / 100 * 30) - 55,
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 360)),
				this);

		btnCreateAlliance = new MouseOverArea(
				container,
				imgCreateAlliance.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				(int) (minimapWidth + XtoStartFrom
						+ (float) container.getWidth() / 100 * 9
						+ allianceField.getWidth() + 0
						* (float) imgCreateAlliance.getWidth() / 1920
						* container.getWidth() + 1 * (float) container
						.getWidth() / 100 * 2),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				this);

		btnJoinAlliance = new MouseOverArea(
				container,
				imgJoinAlliance.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				(int) (minimapWidth + XtoStartFrom
						+ (float) container.getWidth() / 100 * 9
						+ allianceField.getWidth() + 1
						* (float) imgCreateAlliance.getWidth() / 1920
						* container.getWidth() + 2 * (float) container
						.getWidth() / 100 * 2),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				this);

		btnLeaveAlliance = new MouseOverArea(
				container,
				imgLeaveAlliance.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
				(int) (minimapWidth + XtoStartFrom
						+ (float) container.getWidth() / 100 * 9
						+ allianceField.getWidth() + 2
						* (float) imgCreateAlliance.getWidth() / 1920
						* container.getWidth() + 3 * (float) container
						.getWidth() / 100 * 2),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				this);

	}

	public void update(GameContainer container, int delta) {
		display.update(container, delta);
	}

	public void render(Graphics g) {

		started = ciClient.getGame().isRunning();

		imgBorder.draw(0, 0, (((float) container.getHeight()) / 1080) * 1.0f);
		btnStartGame.render(container, g);
		btnStartGame.setNormalColor(new Color(1, 1, 1, 0.8f));
		btnAi.render(container, g);
		btnAi.setNormalColor(new Color(1, 1, 1, 0.8f));
		btnCreateAlliance.render(container, g);
		btnCreateAlliance.setNormalColor(new Color(1, 1, 1, 0.8f));
		btnJoinAlliance.render(container, g);
		btnJoinAlliance.setNormalColor(new Color(1, 1, 1, 0.8f));
		btnLeaveAlliance.render(container, g);
		btnLeaveAlliance.setNormalColor(new Color(1, 1, 1, 0.8f));
		tip.setVisible(false);

		g.drawString("use AI", btnAi.getX() + btnAi.getWidth() + 20, btnAi.getY());

		for (int i = 0; i < sectorFields.length; i++) {
			if (sectorFields[i].isMouseOver()) {

				String toolTipText = "";
				Iterator<Resource> it = sectors.get(i)
						.iteratorOfSectorResources();
				while (it.hasNext()) {
					Resource tmpRes = it.next();
					toolTipText += tmpRes.getType() + ": "
							+ tmpRes.getQuantity() + "\r\n";
				}
				if (toolTipText.equals("")) {
					toolTipText = " \r\n";
				}
				tip.setVisible(true);
				tip.setText(toolTipText);
				tip.setLocation(Mouse.getX() + 20, -Mouse.getY() + 10
						+ container.getHeight());
				tip.pack();
			} else {
			}
			sectorFields[i].render(container, g);
		}

		g.drawString(message, minimapWidth + XtoStartFrom
				+ (int) ((float) container.getWidth() / 100 * 30), 100);

		for (int i = 0; i < sectors.size(); i++) {
			if (sectors.get(i).isStartSector()) {
				g.setColor(Color.gray);
				g.drawRect(XtoStartFrom + sectors.get(i).getXCoordinate()
						* fieldLength, YtoStartFrom
						+ sectors.get(i).getYCoordinate() * fieldLength,
						fieldLength, fieldLength);
			}
		}

		for (int i = 0; i < players.size(); i++) {

			try {
				g.setColor(colorMap.get(players.get(i).getUserAssets()
						.getAlliance().getColor()));
			} catch (Exception e) {
				g.setColor(colorMap.get(0));
			}
			g.drawRect(
					minimapWidth + XtoStartFrom
							+ (int) ((float) container.getWidth() / 100 * 5),
					102 + i * 50, 15, 15);
			g.fillRect(
					minimapWidth + XtoStartFrom
							+ (int) ((float) container.getWidth() / 100 * 5),
					102 + i * 50, 15, 15);

			g.setColor(Color.white);

			String ally = "";
			try {
				ally = players.get(i).getUserAssets().getAlliance().getName();
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			}
			g.drawString(
					players.get(i).getNickname() + " (" + ally + ")",
					minimapWidth + XtoStartFrom
							+ (int) ((float) container.getWidth() / 100 * 9),
					100 + i * 50);
			if (players.get(i).getUserAssets().getStartSector() != null) {
				// g.drawString(players.get(i).getUserAssets().getStartSector()
				// .getId(), 550, 100 + i * 50);
				Sector tmp = players.get(i).getUserAssets().getStartSector();
				g.setColor(colorMap.get(players.get(i).getUserAssets()
						.getColor()));
				g.drawRect(XtoStartFrom + tmp.getXCoordinate() * fieldLength,
						YtoStartFrom + tmp.getYCoordinate() * fieldLength,
						fieldLength, fieldLength);
			}
			g.setColor(Color.white);

		}

		allianceField.render(container, g);
		g.setColor(colorMap.get(colorList.get(currentAllyColorListIndex)));
		g.drawRect(
				minimapWidth + XtoStartFrom
						+ (int) ((float) container.getWidth() / 100 * 5),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				15, 15);
		g.fillRect(
				minimapWidth + XtoStartFrom
						+ (int) ((float) container.getWidth() / 100 * 5),
				(int) (container.getHeight() - ((((float) container.getHeight()) / 1080) * 270)),
				15, 15);

		g.setColor(Color.white);
		colorMouseOverArea.render(container, g);

		incAlliance.setLocation(minimapWidth + XtoStartFrom
				+ (int) ((float) container.getWidth() / 100 * 30) - 55, 200);
		incAlliance.pack();

		decAlliance.setLocation(minimapWidth + XtoStartFrom
				+ (int) ((float) container.getWidth() / 100 * 30) - 55, 250);
		decAlliance.pack();

		g.drawString("Alliances: ", minimapWidth + XtoStartFrom
				+ (int) ((float) container.getWidth() / 100 * 30), 175);
		int count = 0;
		if (allianceStart > alliances.size()) {
			allianceStart = alliances.size();
		}
		int allianceEnd = allianceStart + 3;
		if (alliances.size() < allianceEnd) {
			allianceEnd = alliances.size();
		}
		for (int i = allianceStart; i < allianceEnd; i++) {
			Alliance alliance = alliances.get(i);
			try {
				String allianceName = alliance.getName();
				g.drawString(allianceName, minimapWidth + XtoStartFrom
						+ (int) ((float) container.getWidth() / 100 * 30),
						200 + 25 * count++);
			} catch (Exception e) {
			}
		}

		display.render(container, g);

	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static void start() {

		// CIClient ciClient1 = createTestCIClient();
		// ciClient = ciClient1;

		if (ciClient != null) {
			if (ciClient.getGame() != null) {

				Iterator<User> it = ciClient.getGame().iteratorOfUser();
				while (it.hasNext()) {
					User user = it.next();
					if (user.getUserAssets() != null
							&& ciClient.getCurrentPlayer().equals(
									user.getNickname())) {
						System.out.println("user found: " + user.getNickname());
						players.add(user);
					}
				}

				it = ciClient.getGame().iteratorOfUser();
				while (it.hasNext()) {
					User user = it.next();
					if (user.getUserAssets() != null) {
						if (!players.contains(user)) {
							System.out.println("user found: "
									+ user.getNickname());
							players.add(user);
							System.out.println();
						}
					}
				}

				System.out.println(ciClient.getGame().getName());

				ciClient.getGame().addPropertyChangeListener(
						Game.PROPERTY_USER, new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (evt.getNewValue() != null) {
									User p = (User) evt.getNewValue();
									System.out.println(p.getNickname());
									if (p.getUserAssets() != null) {
										if (!players.contains(p))
											players.add(p);
									}
								}
								if (evt.getNewValue() == null
										&& evt.getOldValue() != null) {
									User p = (User) evt.getNewValue();
									players.remove(p);
								}
							}

						});

				ciClient.getGame().addPropertyChangeListener(
						Game.PROPERTY_USER_ASSETS,
						new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (evt.getNewValue() != null) {
									UserAssets p = (UserAssets) evt
											.getNewValue();
									if (p.getUser() != null) {
										System.out.println(p.getUser()
												.getNickname());
										if (!players.contains(p.getUser()))
											players.add(p.getUser());
									}
								}
							}

						});

				ciClient.getGame().addPropertyChangeListener(
						Game.PROPERTY_ALLIANCE, new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {

								System.out.println("Received Event");
								Alliance p = (Alliance) evt.getNewValue();
								System.out.println(p.getId());
								alliances.add(p);
							}

						});
			} else {
				System.out.println("ciClient.getGame() == null");
			}
		} else {
			System.out.println("ciClient == null");
		}

		// changeCIClient(ciClient);

	}

	public void componentActivated(AbstractComponent source) {
		System.out.println("test");
		for (int i = 0; i < sectorFields.length; i++) {
			if (source == sectorFields[i]) {

				if (sectors.get(i).getStartingUser() == null) {

					if (sectors.get(i).isStartSector()) {
						System.out.println("testAreas " + (i + 1)
								+ " selected (" + sectors.get(i).getId() + ")");
						message = "";

						System.out.println("new sector id: "
								+ sectors.get(i).getId());
						players.get(0).getUserAssets()
								.setStartSector(sectors.get(i));
						ciClient.getServerConnection().choose_sector(
								sectors.get(i).getId());
					} else {
						message = "This sector is\nno start sector!\n\nChoose another one.";
					}

				} else {
					message = "Sector already token!\nChoose another one.";
				}

			}
		}

		if (source == colorMouseOverArea) {
			System.out.println("colorMouseOverArea clicked");
			message = "colorMouseOverArea clicked";
			if ((currentAllyColorListIndex + 1) < colorList.size()) {
				currentAllyColorListIndex += 1;
				System.out.println("currentAllyColorListIndex inc");
			} else {
				currentAllyColorListIndex = 0;
				System.out.println("currentAllyColorListIndex res");
			}
			System.out.println(currentAllyColorListIndex);

		}

		if (source == btnAi) {
			if (!ai) {
				ai = true;
				btnAi = new MouseOverArea(
						container,
						imgAiOn.getScaledCopy((((float) container.getHeight()) / 1080) * 1.0f),
						minimapWidth
								+ XtoStartFrom
								+ (int) ((float) container.getWidth() / 100 * 30)
								- 55,
						(int) (container.getHeight() - ((((float) container
								.getHeight()) / 1080) * 360)), this);
			} else {
				ai = false;
				if (!ai) {
					btnAi = new MouseOverArea(
							container,
							imgAiOff.getScaledCopy((((float) container
									.getHeight()) / 1080) * 1.0f),
							minimapWidth
									+ XtoStartFrom
									+ (int) ((float) container.getWidth() / 100 * 30)
									- 55,
							(int) (container.getHeight() - ((((float) container
									.getHeight()) / 1080) * 360)), this);
				}
			}
		}
		if (source == btnCreateAlliance) {
			if (!allianceField.getText().equals("")) {
				createAlliance(allianceField.getText());
			}
		}
		if (source == btnJoinAlliance) {
			if (!allianceField.getText().equals("")) {
				joinAlliance(allianceField.getText());
			}
		}
		if (source == btnLeaveAlliance) {
			leaveAlliance();
		}

		if (source == btnStartGame) {
			// started = true;
			ciClient.getServerConnection().start_game();
		}
	}

	public void createAlliance(String allyName) {

		java.awt.Color currentAllianceColorAWT = new java.awt.Color(colorMap
				.get(colorList.get(currentAllyColorListIndex)).getRed(),
				colorMap.get(colorList.get(currentAllyColorListIndex))
						.getGreen(), colorMap.get(
						colorList.get(currentAllyColorListIndex)).getBlue());

		ciClient.getServerConnection().create_alliance(
				allyName,
				Integer.toHexString(currentAllianceColorAWT.getRGB())
						.substring(2));
		allianceField.setText("");

	}

	public void joinAlliance(String allianceName) {

		String allianceId = "";

		for (int i = 0; i < alliances.size(); i++) {
			if (alliances.get(i).getName().equals(allianceName)) {
				allianceId = alliances.get(i).getId();
			}
		}

		if (!allianceId.equals("")) {
			ciClient.getServerConnection().join_alliance(allianceId);
			allianceField.setText("");
			message = "";
		} else {
			message = "Allianz does not exists!";
		}

	}

	public void leaveAlliance() {

		ciClient.getServerConnection().leave_alliance();
		allianceField.setText("");

	}

}
