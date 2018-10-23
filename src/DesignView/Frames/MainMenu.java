package DesignView.Frames;

import java.awt.BorderLayout;

import DesignView.Components.ImagePanel;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import DesignView.Handlers.*;
import DesignView.RecordHandler.GameRecords;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.io.File;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import java.awt.ComponentOrientation;


public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JPanel headerPanel;
	private ImagePanel mainPanel;
	private JPanel footerPanel;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblBtn_Startgame;
	private JLabel lblNewLabel_4;
	private JSpinner minerWSeter;
	private JLabel label;
	private JSpinner minerHSeter;
	private JLabel label_1;
	public JSpinner minerPerSeter;
	private JLabel lblBtn_Endgame;
	private JLabel lblBtn_Loadgame;
	private JLabel lblBtn_Scoreboard;
	private JLabel label_2;
	private JLabel lblnbsp;
	
	public int getMinerWSeterValue(){
		return Integer.parseInt(minerWSeter.getValue().toString());
	}
	public int getMinerHSeterValue(){
		return Integer.parseInt(minerHSeter.getValue().toString());
	}
	public int getMinerPerSeterValue(){
		return Integer.parseInt(minerPerSeter.getValue().toString());
	}
	
	/**
	 * This method lunches after creating form
	 */
	public void mainMenuFinalInitalize(){
		GameRecords.loadRecords();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/DesignView/Images/Explosion-48.png")));
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			}
			catch(Exception exc){}
		}
		setBackground(Color.WHITE);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 500);
		setMinimumSize(new Dimension(480,  400));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		headerPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		headerPanel.setBackground(new Color(240, 240, 240));
		FlowLayout flowLayout = (FlowLayout) headerPanel.getLayout();
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		headerPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("\u0628\u0627\u0632\u06CC \u0645\u06CC\u0646 \u0631\u0648\u0628");
		lblNewLabel_1.setForeground(new Color(0, 102, 153));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Bomb With Timer-32.png")));
	
        
		mainPanel = new ImagePanel(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/DesignView/Images/DesignerView.png")));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		
		
		lblNewLabel_4 = new JLabel("\u0637\u0648\u0644 \u0632\u0645\u06CC\u0646 \u0628\u0627\u0632\u06CC (\u062D\u062F\u0627\u06A9\u062B\u0631 \u06F3\u06F5):");
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(257, 71, 136, 14);
		mainPanel.add(lblNewLabel_4);
		
		minerWSeter = new JSpinner();
		minerWSeter.setForeground(Color.DARK_GRAY);
		minerWSeter.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		minerWSeter.setModel(new SpinnerNumberModel(10, 5, 35, 1));
		minerWSeter.setBounds(100, 65, 94, 20);
		
		mainPanel.add(minerWSeter);
		
		label = new JLabel("\u0639\u0631\u0636 \u0632\u0645\u06CC\u0646 \u0628\u0627\u0632\u06CC (\u062D\u062F\u0627\u06A9\u062B\u0631 \u06F2\u06F5):");
		label.setForeground(Color.DARK_GRAY);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(257, 93, 136, 14);
		mainPanel.add(label);
		
		minerHSeter = new JSpinner();
		minerHSeter.setForeground(Color.DARK_GRAY);
		minerHSeter.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		minerHSeter.setModel(new SpinnerNumberModel(10, 5, 25, 1));
		minerHSeter.setBounds(100, 87, 94, 20);
		mainPanel.add(minerHSeter);
		
		label_1 = new JLabel("\u0645\u06CC\u0646 \u0647\u0627 (\u062F\u0631\u0635\u062F):");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(257, 115, 136, 14);
		mainPanel.add(label_1);
		
		minerPerSeter = new JSpinner();
		minerPerSeter.setForeground(Color.DARK_GRAY);
		minerPerSeter.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		minerPerSeter.setModel(new SpinnerNumberModel(20, 5, 80, 1));
		minerPerSeter.setBounds(100, 109, 94, 20);
		mainPanel.add(minerPerSeter);
		
		lblBtn_Startgame = new JLabel("\u0634\u0631\u0648\u0639 \u0628\u0627\u0632\u06CC \u062C\u062F\u06CC\u062F", 
				SwingConstants.RIGHT);
		lblBtn_Startgame.setName("startBtn");
		lblBtn_Startgame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBtn_Startgame.setOpaque(true);
		lblBtn_Startgame.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Explosion-32.png")));
		lblBtn_Startgame.setBounds(100, 21, 293, 39);
		lblBtn_Startgame.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblBtn_Startgame.setHorizontalTextPosition(SwingConstants.LEFT);
		lblBtn_Startgame.setIconTextGap(5);
		lblBtn_Startgame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblBtn_Startgame.addMouseListener(new LabelButtons(this).createMouseListener()); 
		
		mainPanel.add(lblBtn_Startgame);
		
		lblBtn_Endgame = new JLabel("\u062E\u0631\u0648\u062C \u0627\u0632 \u0628\u0627\u0632\u06CC", SwingConstants.RIGHT);
		lblBtn_Endgame.setName("exitBtn");
		lblBtn_Endgame.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Exit Sign-32.png")));
		lblBtn_Endgame.setOpaque(true);
		lblBtn_Endgame.setIconTextGap(5);
		lblBtn_Endgame.setHorizontalTextPosition(SwingConstants.LEFT);
		lblBtn_Endgame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBtn_Endgame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblBtn_Endgame.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblBtn_Endgame.setBounds(100, 308, 293, 39);
		lblBtn_Endgame.addMouseListener(new LabelButtons(this).createMouseListener());
		
		mainPanel.add(lblBtn_Endgame);
		
		lblBtn_Loadgame = new JLabel("\u0628\u0627\u0631\u06AF\u0630\u0627\u0631\u06CC \u0622\u062E\u0631\u06CC\u0646 \u0628\u0627\u0632\u06CC \u0627\u0646\u062C\u0627\u0645 \u0634\u062F\u0647", SwingConstants.RIGHT);
		lblBtn_Loadgame.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Explosion-load32.png")));
		lblBtn_Loadgame.setOpaque(true);
		lblBtn_Loadgame.setName("loadBtn");
		lblBtn_Loadgame.setIconTextGap(5);
		lblBtn_Loadgame.setHorizontalTextPosition(SwingConstants.LEFT);
		lblBtn_Loadgame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBtn_Loadgame.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblBtn_Loadgame.setBounds(100, 208, 293, 39);
		lblBtn_Loadgame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblBtn_Loadgame.addMouseListener(new LabelButtons(this).createMouseListener());
		File saveFile = new File("save.ser");					
		if (saveFile.exists()) lblBtn_Loadgame.setEnabled(true);
		else lblBtn_Loadgame.setEnabled(false);
		mainPanel.add(lblBtn_Loadgame);
		
		lblBtn_Scoreboard = new JLabel("\u0627\u0645\u062A\u06CC\u0627\u0632\u0627\u062A", SwingConstants.RIGHT);
		lblBtn_Scoreboard.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Ratings-32.png")));
		lblBtn_Scoreboard.setOpaque(true);
		lblBtn_Scoreboard.setName("recordsBtn");
		lblBtn_Scoreboard.setIconTextGap(5);
		lblBtn_Scoreboard.setHorizontalTextPosition(SwingConstants.LEFT);
		lblBtn_Scoreboard.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBtn_Scoreboard.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblBtn_Scoreboard.setBounds(100, 258, 293, 39);
		lblBtn_Scoreboard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblBtn_Scoreboard.addMouseListener(new LabelButtons(this).createMouseListener());
		mainPanel.add(lblBtn_Scoreboard);
		
		label_2 = new JLabel("\u067E\u0633 \u0627\u0632 \u062A\u063A\u06CC\u06CC\u0631 \u0645\u0642\u0627\u062F\u06CC\u0631 \u062A\u0646\u0638\u06CC\u0645\u0627\u062A \u0628\u0627\u0644\u0627 \u06A9\u0644\u06CC\u062F Enter \u0631\u0627 \u0628\u0641\u0634\u0627\u0631\u06CC\u062F");
		label_2.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		label_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		label_2.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Error-16.png")));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setBounds(100, 138, 293, 25);
		mainPanel.add(label_2);
		
		lblnbsp = new JLabel("<html><body style='text-align:right;direction:rtl;'>\r\n\u0628\u0631\u0627\u06CC \u062F\u0631\u062C \u067E\u0631\u0686\u0645 \u0648 \u0646\u0634\u0627\u0646\u0647 \u06AF\u0630\u0627\u0631\u06CC \u0631\u0648\u06CC \u062E\u0627\u0646\u0647 \u0647\u0627\u06CC \u0628\u0627\u0632\u06CC \u0627\u0632 \u06A9\u0644\u06CC\u062F \r\n<b>\u0631\u0627\u0633\u062A \u06A9\u0644\u06CC\u06A9</b>\r\n \u0627\u0633\u062A\u0641\u0627\u062F\u0647 \u06A9\u0646\u06CC\u062F\r\n\r\n</body></html>");
		lblnbsp.setIcon(new ImageIcon(MainMenu.class.getResource("/DesignView/Images/Flag-16.png")));
		lblnbsp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblnbsp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnbsp.setForeground(Color.DARK_GRAY);
		lblnbsp.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblnbsp.setBounds(110, 160, 283, 25);
		mainPanel.add(lblnbsp);
		
		footerPanel = new JPanel();
		footerPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		footerPanel.setBackground(new Color(240, 240, 240));
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		
		lblNewLabel_2 = new JLabel("\u0637\u0631\u0627\u062D\u06CC \u0648 \u0628\u0631\u0646\u0627\u0645\u0647 \u0646\u0648\u06CC\u0633\u06CC \u062A\u0648\u0633\u0637 \u0622\u0631\u06CC\u0646 \u0627\u0628\u0631\u0627\u0647\u06CC\u0645 \u067E\u0648\u0631 \u060C \u062F\u0627\u0646\u0634\u06AF\u0627\u0647 \u06AF\u06CC\u0644\u0627\u0646");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		footerPanel.add(lblNewLabel_2);
		mainMenuFinalInitalize();
	}
}
