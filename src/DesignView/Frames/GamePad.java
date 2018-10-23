package DesignView.Frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import DesignView.BlockHandler;
import DesignView.Components.AudioPlayer;
import DesignView.Components.IconProvider;
import DesignView.Components.ImagePanel;
import DesignView.Components.PersianNumeric;
import DesignView.Components.AudioPlayer.DefinedAudios;
import DesignView.Components.IconProvider.GameIconSet;
import DesignView.Handlers.MineButton;
import DesignView.SaveGame.GameSave;
import DesignView.SaveGame.SavingElement;
import DesignView.SaveGame.SavingElement.cellStyle;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GamePad extends JFrame {

	// Size of the buttons on the scene
	private final int buttonSizePixel = 24;

	/*
	 * Counts the non-mine cells on the scene that has been clicked. Required
	 * for checking user win.
	 * 
	 * Main function: increaseGoodCellCount();
	 */
	private static int goodCellCount = 0;

	// Game Time counter
	private static int timeCount = 0;

	// Determines that if the game is NewGame or LoadedGame
	private boolean isLoadedGame = false;

	/*
	 * Determines width, height and mine traffic of the game. This variables are
	 * loaded from main menu (New game) or from the serialized object (Loaded
	 * game)
	 */
	private static int mineWidth;
	private static int mineHeight;
	private static int minePercentage;

	/*
	 * Determines that is game started. This variable is used to check when to
	 * start the timer.
	 * 
	 * Main function: MineButtons Mouse Handler
	 */
	private static boolean _isGameStarted = false;

	private JPanel contentPane;
	private JPanel headerPanel;
	private JPanel footerPanel;
	private ImagePanel mainPanel;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private Box gameNameBox;
	private Component verticalStrut;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Box gameTextBox;
	private static JLabel lblGameTime;
	private Component horizontalStrut_2;
	private JLabel label_2;
	private Component horizontalStrut_3;
	private JLabel lblGameStatus;
	private Component horizontalStrut_4;
	private static JPanel pnlGameMines;

	/*
	 * Game timers, and update timer task definition
	 */
	static Timer timer;
	static Timer delayedGameoverForm;
	static TimerTask task;

	/*
	 * This list determines that a specified button in the game is a mine or an
	 * empty slot
	 */
	public static ArrayList<Boolean> mineList = new ArrayList<Boolean>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePad frame = new GamePad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Form Minimum Size: 400 x 450 Form Margin From Table: 10
	 */

	/**
	 * Create the frame.
	 */
	public GamePad() {

		initalize();
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(GamePad.class.getResource("/DesignView/Images/Explosion-16.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		setMinimumSize(new Dimension(400, 450));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		headerPanel = new JPanel();
		headerPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		contentPane.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		gameNameBox = Box.createHorizontalBox();
		headerPanel.add(gameNameBox, BorderLayout.EAST);

		lblGameStatus = new JLabel("\u0645\u062A\u0646 \u0645\u062A\u063A\u06CC\u0631");
		lblGameStatus.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblGameStatus.setForeground(Color.GRAY);
		gameNameBox.add(lblGameStatus);

		horizontalStrut_4 = Box.createHorizontalStrut(8);
		gameNameBox.add(horizontalStrut_4);

		lblNewLabel_1 = new JLabel("\u0645\u06CC\u0646 \u0631\u0648\u0628");
		lblNewLabel_1.setAlignmentX(0.5f);
		gameNameBox.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(51, 153, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		horizontalStrut = Box.createHorizontalStrut(5);
		gameNameBox.add(horizontalStrut);

		lblNewLabel = new JLabel("");
		lblNewLabel.setAlignmentX(0.5f);
		gameNameBox.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(GamePad.class.getResource("/DesignView/Images/Bomb With Timer-16.png")));

		horizontalStrut_1 = Box.createHorizontalStrut(10);
		gameNameBox.add(horizontalStrut_1);

		verticalStrut = Box.createVerticalStrut(30);
		gameNameBox.add(verticalStrut);

		gameTextBox = Box.createHorizontalBox();
		headerPanel.add(gameTextBox, BorderLayout.WEST);

		horizontalStrut_3 = Box.createHorizontalStrut(10);
		gameTextBox.add(horizontalStrut_3);

		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(GamePad.class.getResource("/DesignView/Images/Time-16.png")));
		label_2.setAlignmentX(0.5f);
		gameTextBox.add(label_2);

		horizontalStrut_2 = Box.createHorizontalStrut(5);
		gameTextBox.add(horizontalStrut_2);

		lblGameTime = new JLabel("000 \u062B\u0627\u0646\u06CC\u0647");
		lblGameTime.setForeground(SystemColor.textHighlight);
		lblGameTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGameTime.setAlignmentX(0.5f);
		lblGameTime.setText(PersianNumeric.toPersianNumberic(lblGameTime.getText()));
		gameTextBox.add(lblGameTime);

		mainPanel = new ImagePanel(Toolkit.getDefaultToolkit()
				.getImage(MainMenu.class.getResource("/DesignView/Images/DesignerView.png")));
		mainPanel.setBackground(Color.WHITE);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		pnlGameMines = new JPanel();
		pnlGameMines.setOpaque(false);
		// pnlGameMines.setBackground(Color.red);
		pnlGameMines.setBounds(0, 0, 93, 94);
		mainPanel.add(pnlGameMines);
		pnlGameMines.setLayout(new GridLayout(1, 0, 0, 0));

		footerPanel = new JPanel();
		footerPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		contentPane.add(footerPanel, BorderLayout.SOUTH);

		label = new JLabel(
				"\u0637\u0631\u0627\u062D\u06CC \u0648 \u0628\u0631\u0646\u0627\u0645\u0647 \u0646\u0648\u06CC\u0633\u06CC \u062A\u0648\u0633\u0637 \u0622\u0631\u06CC\u0646 \u0627\u0628\u0631\u0627\u0647\u06CC\u0645 \u067E\u0648\u0631 \u060C \u062F\u0627\u0646\u0634\u06AF\u0627\u0647 \u06AF\u06CC\u0644\u0627\u0646");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		footerPanel.add(label);

	}

	/**
	 * Constructor used when loading a game without setting game variables
	 * Usually used when loading game from serialized object
	 * 
	 * @param load
	 *            Is game loaded
	 */
	public GamePad(boolean load) {
		this();
		isLoadedGame = load;
		createMinesScene();
		updateStatusText();
		addWindowListener(new windowClosingClass());

	}

	/**
	 * Constructor used when loading a game with setting game variables Usually
	 * used when creating a new game
	 * 
	 * @param load
	 * @param mineW
	 * @param mineH
	 * @param minePer
	 */
	public GamePad(boolean load, int mineW, int mineH, int minePer) {
		this();
		isLoadedGame = load;
		mineHeight = mineH;
		mineWidth = mineW;
		minePercentage = minePer;
		updateStatusText();
		createMinesScene();
		addWindowListener(new windowClosingClass());
	}

	/**
	 * Initializes gamePad and resets variables
	 */
	private void initalize() {
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				updateTimeTimer();
			}
		};
		delayedGameoverForm = new Timer();
		pnlGameMines = null;
		goodCellCount = 0;
		timeCount = 0;
		isLoadedGame = false;
		_isGameStarted = false;
	}

	public static boolean isGameStatred() {
		return _isGameStarted;
	}

	public static int getGoodCellCount() {
		return goodCellCount;
	}

	/**
	 * This method counts non-mine cells to check if game is ended or not
	 */
	public static void increaseGoodCellCount() {
		goodCellCount++;
		int minesLen = (int) ((mineHeight * mineWidth) * ((float) (minePercentage / 100f)));
		int goodLen = ((mineHeight * mineWidth) - minesLen);
		// Checks if user won or not:
		if (goodCellCount >= goodLen) {
			gameWon();
		}

	}

	/**
	 * Triggers for winning game
	 */
	public static void gameWon() {
		suspendTimer();
		showFlowers();
		// Delete saved game
		try {
			File saveFile = new File("save.ser");
			saveFile.delete();
		} catch (Exception ex) {
		}
		try {
			AudioPlayer.playAudioByResourceName(DefinedAudios.BEACH_Sound);
		} catch (Exception ex) {
		}
		// Show win form after 2 second
		delayedGameoverForm.schedule(new TimerTask() {
			@Override
			public void run() {
				for (Frame frame : getFrames())
					frame.setVisible(false);
				RegisterRecord recordRegisterForm = new RegisterRecord(timeCount);
				recordRegisterForm.setVisible(true);

			}
		}, 2000);

	}

	/**
	 * Triggers for loosing game
	 */
	public static void gameLost() {
		suspendTimer();
		showMines();
		// Delete saved game
		try {
			File saveFile = new File("save.ser");
			saveFile.delete();
		} catch (Exception ex) {
		}
		// Show lose form after 2 second
		delayedGameoverForm.schedule(new TimerTask() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null,
						"\u0645\u062A\u0627\u0633\u0641\u0627\u0646\u0647 \u0634\u0645\u0627 \u0628\u0627\u062E\u062A\u06CC\u062F. \u0628\u0631\u0627\u06CC \u0627\u06CC\u062C\u0627\u062F \u0628\u0627\u0632\u06CC \u062C\u062F\u06CC\u062F \u0648\u0627\u0631\u062F \u0645\u0646\u0648 \u0627\u0635\u0644\u06CC \u062E\u0648\u0627\u0647\u06CC\u062F \u0634\u062F",
						"\u0628\u0627\u0632\u06CC \u062F\u0648\u0628\u0627\u0631\u0647",
						JOptionPane.INFORMATION_MESSAGE);
				MainMenu.main(null);
				for (Frame frame : getFrames())
					frame.setVisible(false);
			}
		}, 2000);
	}

	public static void suspendTimer() {
		timer.cancel();
	}

	public static void startTimer() {
		_isGameStarted = true;
		timer.scheduleAtFixedRate(task, new Date(), 1000);

	}

	/**
	 * Updates game record timer
	 */
	public static void updateTimeTimer() {
		timeCount++;
		lblGameTime.setText(PersianNumeric.toPersianNumberic(String.format("%03d", timeCount))
				+ " \u062B\u0627\u0646\u06CC\u0647");
	}

	/**
	 * Updates game status bar text
	 */
	private void updateStatusText() {
		String gameStatus = "( ";
		if (isLoadedGame)
			// UnicodeText: Persian (Edame)
			gameStatus += "\u0627\u062F\u0627\u0645\u0647 \u0628\u0627\u0632\u06CC ";
		else
			// UnicodeText: Persian (New Game)
			gameStatus += "\u0628\u0627\u0632\u06CC \u062C\u062F\u06CC\u062F ";

		// UnicodeText: Persian (Dar Zamine)
		gameStatus += "\u062F\u0631 \u0632\u0645\u06CC\u0646 ";
		gameStatus += mineWidth;

		// UnicodeText: Persian (Dar)
		gameStatus += " \u062F\u0631 ";
		gameStatus += mineHeight;

		// UnicodeText: Persian (Ba)
		gameStatus += " \u0628\u0627 ";
		gameStatus += minePercentage;

		// UnicodeText: Persian (Ba Darsad Min)
		gameStatus += " \u062F\u0631\u0635\u062F \u0645\u06CC\u0646 )";

		lblGameStatus.setText(PersianNumeric.toPersianNumberic(gameStatus));

	}

	/**
	 * Shows entire mines in the gamepad
	 */
	public static void showMines() {

		for (Component xButton : pnlGameMines.getComponents()) {
			// Check if control is MineButton and it's a BOMB
			int buttonID = MineButton.findMineID(xButton.getName());
			if (mineList.get(buttonID)) {

				MineButton sender = (MineButton) xButton;
				sender.setName(buttonID + ",selected");
				sender.setBackground(new Color(204, 204, 204));
				sender.setBorder(new LineBorder(Color.lightGray));
				sender.setIcon(IconProvider.getIconResource(GameIconSet.Mine24Gray));

			} else if (!xButton.getName().contains(",selected")) {

				MineButton sender = (MineButton) xButton;
				sender.setName(buttonID + ",selected");
				sender.setBackground(new Color(242, 242, 242));
				sender.setBorder(new LineBorder(Color.lightGray));
				sender.setIcon(null);

			}
		}
	}

	/**
	 * Show entire flowers in the game
	 */
	public static void showFlowers() {

		for (Component xButton : pnlGameMines.getComponents()) {
			// Check if control is MineButton and it's a BOMB
			MineButton sender = (MineButton) xButton;
			if (!sender.getName().contains(",selected")) {

				sender.setName("bombfound,selected");
				sender.setBackground(new Color(255, 238, 204));
				sender.setBorder(new LineBorder(new Color(255, 195, 77)));
				sender.setIcon(IconProvider.getIconResource(GameIconSet.Flower));

			}
		}
	}

	/**
	 * Checks if the block doesn't have any mines nearby This function works in
	 * a recursive way
	 * 
	 * @param id
	 *            index of the block
	 * @param w
	 *            width of the scene
	 * @param h
	 *            height of the scene
	 */
	public static void freeNearbyBlocks(int id, int w, int h) {

		int[] neighbors = BlockHandler.getNeighborIndexes(id, w, h);

		for (int counter = 0; counter < neighbors.length; counter++) {

			MineButton mineButton = (MineButton) pnlGameMines.getComponents()[neighbors[counter]];

			// Check if not mine and not selected
			if (!mineList.get(neighbors[counter]) && !mineButton.getName().contains(",selected")) {

				int bombCount = 0;
				int[] innerNeighbors = BlockHandler.getNeighborIndexes(neighbors[counter], w, h);
				for (int i = 0; i < innerNeighbors.length; i++)
					if (mineList.get(innerNeighbors[i]))
						bombCount++;

				int buttonID = MineButton.findMineID(mineButton.getName());
				mineButton.setName(buttonID + ",selected");
				mineButton.setBackground(new Color(238, 255, 204));
				mineButton.setIcon(null);
				mineButton.setForeground(new Color(85, 128, 0));
				mineButton.setBorder(new LineBorder(Color.lightGray));

				increaseGoodCellCount();

				if (bombCount != 0) {
					mineButton.setText(PersianNumeric.toPersianNumberic(String.valueOf(bombCount)));
				} else {
					freeNearbyBlocks(neighbors[counter], w, h);
				}
			}

		}
	}

	/**
	 * Creates the scene of the game, puts the buttons in the scene, brings
	 * buttons to center of the pad, and the makes the Frame center-screen
	 */
	private void createMinesScene() {

		if (!isLoadedGame) {

			// +1 show hgap size:
			int panelBoundWidth = (mineWidth + 1) * buttonSizePixel;
			// +1 show vgap size:
			int panelBoundHeight = (mineHeight + 1) * buttonSizePixel;

			// 20 means: 2 * 10 = 2 * margin from table
			// 40 + 65 means sizes of the footer and header
			setSize(new Dimension(panelBoundWidth + 20, panelBoundHeight + 40 + 65));

			int bombCount = (int) ((float) (mineHeight * mineWidth) * ((float) (minePercentage / 100f)));
			mineList.clear();
			// Adding bomb(true) and non-bomb(false) items to the item list.
			for (int i = 0; i < mineHeight * mineWidth; i++) {
				if (i < bombCount)
					mineList.add(true);
				else
					mineList.add(false);

			}
			Collections.shuffle(mineList);

			// Set Panel size
			pnlGameMines.setBounds(0, 0, panelBoundWidth, panelBoundHeight);

			int freeHorizonalSpace = (getSize().width - 20) - pnlGameMines.getSize().width;
			int freeVerticalSpace = (getSize().height - 40 - 65) - pnlGameMines.getSize().height;
			if (freeVerticalSpace == 0)
				freeVerticalSpace = 10;

			pnlGameMines.setBounds(freeHorizonalSpace / 2, freeVerticalSpace / 2, panelBoundWidth, panelBoundHeight);

			// rows-columns-hgap-vgap
			pnlGameMines.setLayout(new GridLayout(mineHeight, mineWidth, 1, 1));

			// bring form to center screen
			setLocationRelativeTo(null);

			for (int i = 0; i < mineHeight * mineWidth; i++) {
				MineButton button = new MineButton(mineWidth, mineHeight);
				button.setVerticalAlignment(SwingConstants.CENTER);
				button.setHorizontalAlignment(SwingConstants.CENTER);
				button.setName(String.valueOf(i));
				pnlGameMines.add(button);
			}
		} else
			loadSavedGame();
	}

	/**
	 * Loads saved game form 'save.ser' serialized object
	 */
	private void loadSavedGame() {
		GameSave saveData = null;
		try {
			FileInputStream fileIn = new FileInputStream("save.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			saveData = (GameSave) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception ex) {
			return;
		}

		// Reseting Variables of the Game
		_isGameStarted = true;
		mineWidth = saveData.getWidth();
		mineHeight = saveData.getHeight();
		goodCellCount = saveData.getGoodCellCount();
		timeCount = saveData.getTimeCount();
		minePercentage = saveData.getMineTrafic();
		lblGameTime.setText(PersianNumeric.toPersianNumberic(String.format("%03d", timeCount))
				+ " \u062B\u0627\u0646\u06CC\u0647");

		startTimer();
		
		
		// +1 show hgap size:
		int panelBoundWidth = (mineWidth + 1) * buttonSizePixel;
		// +1 show vgap size:
		int panelBoundHeight = (mineHeight + 1) * buttonSizePixel;

		setSize(new Dimension(panelBoundWidth + 20, panelBoundHeight + 40 + 65));

		mineList = saveData.getMineList();

		pnlGameMines.setBounds(0, 0, panelBoundWidth, panelBoundHeight); // Setting
																			// panelSize

		int freeHorizonalSpace = (getSize().width - 20) - pnlGameMines.getSize().width;
		int freeVerticalSpace = (getSize().height - 40 - 65) - pnlGameMines.getSize().height;
		if (freeVerticalSpace == 0)
			freeVerticalSpace = 10;

		pnlGameMines.setBounds(freeHorizonalSpace / 2, freeVerticalSpace / 2, panelBoundWidth, panelBoundHeight);

		pnlGameMines.setLayout(new GridLayout(mineHeight, mineWidth, 1, 1)); // rows-columns-hgap-vgap

		setLocationRelativeTo(null); // bring form to center screen

		for (int i = 0; i < mineHeight * mineWidth; i++) {
			MineButton button = new MineButton(mineWidth, mineHeight);
			button.setVerticalAlignment(SwingConstants.CENTER);
			button.setHorizontalAlignment(SwingConstants.CENTER);

			if (saveData.getElementsList().get(i).getStyle() == cellStyle.SELECTED) {
				button.setName(i + ",selected");
				button.setBackground(new Color(238, 255, 204));
				button.setIcon(null);
				button.setForeground(new Color(85, 128, 0));
				button.setBorder(new LineBorder(Color.lightGray));
				button.setText(saveData.getElementsList().get(i).getText());
			} else if (saveData.getElementsList().get(i).getStyle() == cellStyle.UNSELECTED_MARKED) {
				button.setIcon(IconProvider.getIconResource(GameIconSet.Flag20));
				button.setName(String.valueOf(i));
			} else {
				button.setName(String.valueOf(i));
			}

			pnlGameMines.add(button);
		}
	}

	class windowClosingClass extends WindowAdapter {
		
		/**
		 * Saves the game before closing the form
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			
			super.windowClosing(e);
			
			ArrayList<SavingElement> elementsList = new ArrayList<>();
			
			for (int i = 0; i < mineWidth * mineHeight; i++) {
				
				MineButton mineButton = (MineButton) pnlGameMines.getComponents()[i];

				cellStyle style;

				if (mineButton.getName().contains(",selected"))
					style = cellStyle.SELECTED;
				else if (mineButton.getIcon() != null)
					style = cellStyle.UNSELECTED_MARKED;
				else
					style = cellStyle.UNSELECTED_UNMARKED;

				elementsList.add(new SavingElement(style, mineButton.getText()));
				
			}
			GameSave saver = new GameSave(mineWidth, mineHeight, goodCellCount, timeCount, minePercentage, mineList,
					elementsList);
			GameSave.saveGame(saver);

		}
	}
}
