package DesignView.Frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import DesignView.Components.ImagePanel;
import DesignView.Components.PersianNumeric;
import DesignView.RecordHandler.GameRecords;
import DesignView.RecordHandler.RecordItem;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterRecord extends JDialog {

	private final ImagePanel contentPanel = new ImagePanel(Toolkit.getDefaultToolkit()
			.getImage(MainMenu.class.getResource("/DesignView/Images/DesignerView.png")));
	private JTextField txtPlayerName;
	private JTextField txtField;
	private static String registredName;
	private static int registredScore;

	public String getRegistredName(){
		return registredName;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterRecord dialog = new RegisterRecord();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegisterRecord() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				MainMenu.main(null);
			}
		});
		setResizable(false);
		setBounds(100, 100, 450, 219);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(RegisterRecord.class.getResource("/DesignView/Images/Rating-32.png")));
			label.setBounds(402, 11, 32, 32);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u062B\u0628\u062A \u0632\u0645\u0627\u0646");
			label.setVerticalAlignment(SwingConstants.BOTTOM);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			label.setBounds(350, 11, 47, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("<html>\r\n<body><p style=\"margin-right:28px\">\r\n\u0632\u0645\u0627\u0646 \u062E\u0648\u062F \u0631\u0627 \u062B\u0628\u062A \u06A9\u0646\u06CC\u062F\r\n</p></body>\r\n</html>");
			label.setVerticalAlignment(SwingConstants.TOP);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
			label.setBounds(19, 25, 413, 23);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u0646\u0627\u0645 \u0628\u0627\u0632\u06CC\u06A9\u0646:");
			label.setVerticalAlignment(SwingConstants.BOTTOM);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(337, 62, 60, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u0627\u0645\u062A\u06CC\u0627\u0632:");
			label.setVerticalAlignment(SwingConstants.BOTTOM);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label.setBounds(337, 93, 60, 14);
			contentPanel.add(label);
		}
		
		txtPlayerName = new JTextField();
		txtPlayerName.setSelectedTextColor(Color.GRAY);
		txtPlayerName.setSelectionColor(SystemColor.control);
		txtPlayerName.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtPlayerName.setCaretColor(Color.LIGHT_GRAY);
		txtPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayerName.setText("New Player");
		txtPlayerName.setBounds(36, 59, 291, 20);
		contentPanel.add(txtPlayerName);
		txtPlayerName.setColumns(10);
		
		txtField = new JTextField();
		txtField.setHorizontalAlignment(SwingConstants.CENTER);
		txtField.setText(PersianNumeric.toPersianNumberic("0"));
		txtField.setEditable(false);
		txtField.setColumns(10);
		txtField.setBounds(36, 90, 291, 20);
		contentPanel.add(txtField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u062B\u0628\u062A \u0631\u06A9\u0648\u0631\u062F");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						if (txtField.getText().trim() == "") JOptionPane.showMessageDialog(null, 
								"\u0644\u0637\u0641\u0627 \u0646\u0627\u0645\u06CC \u0631\u0627 \u0648\u0627\u0631\u062F \u06A9\u0646\u06CC\u062F",
								"\u0627\u062E\u0637\u0627\u0631",
								JOptionPane.WARNING_MESSAGE);
						else if(txtField.getText().contains(",") || txtField.getText().contains("|")) JOptionPane.showMessageDialog(null, 
								"\u0644\u0637\u0641\u0627 \u0646\u0627\u0645\u06CC \u0631\u0627 \u06A9\u0647 \u0634\u0627\u0645\u0644 \u06A9\u0627\u0631\u0627\u06A9\u062A\u0631 \u0647\u0627\u06CC \u063A\u06CC\u0631 \u0645\u062C\u0627\u0632 \u0646\u0628\u0627\u0634\u062F \u0627\u0646\u062A\u062E\u0627\u0628 \u06A9\u0646\u06CC\u062F",
								"\u0627\u062E\u0637\u0627\u0631",
								JOptionPane.WARNING_MESSAGE);
						else{
							registredName = txtPlayerName.getText();
							GameRecords.saveRecord(new RecordItem(registredName, registredScore));
							dispose();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u0628\u06CC\u062E\u06CC\u0627\u0644");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}
	public RegisterRecord(int score) {
		this();
		registredScore = score;
		txtField.setText(PersianNumeric.toPersianNumberic(String.valueOf(registredScore)));
	}
}
