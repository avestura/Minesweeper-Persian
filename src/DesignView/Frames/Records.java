package DesignView.Frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DesignView.Components.IconProvider;
import DesignView.Components.IconProvider.GameIconSet;
import DesignView.RecordHandler.GameRecords;
import DesignView.Components.ImagePanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;

public class Records extends JDialog {

	private final ImagePanel contentPanel = new ImagePanel(Toolkit.getDefaultToolkit()
			.getImage(MainMenu.class.getResource("/DesignView/Images/DesignerView.png")));
	private JTable table;
	private JLabel lblNewLabel;
	static Records dialog;
	private JLabel lblNoItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new Records();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Records() {
		setResizable(false);
		setTitle("\u0631\u06A9\u0648\u0631\u062F \u0647\u0627");
		setBounds(100, 100, 463, 477);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setShowHorizontalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(Color.WHITE);
		table.setRowHeight(25);
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setOpaque(false);
		table.setSelectionForeground(new Color(51, 153, 255));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"\u0627\u0645\u062A\u06CC\u0627\u0632", "\u0646\u0627\u0645"}) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		// Next 3 lines brings contents to center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		centerRenderer.setOpaque(false);

		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		
		table.getColumnModel().getColumn(0).setPreferredWidth(126);
		table.getColumnModel().getColumn(1).setPreferredWidth(149);
		table.setBounds(10, 62, 427, 335);
		contentPanel.add(table);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(IconProvider.getIconResource(GameIconSet.Score32));
		lblNewLabel.setBounds(397, 15, 32, 32);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0627\u0645\u062A\u06CC\u0627\u0632\u0627\u062A");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(350, 15, 42, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html>\r\n<body><p style=\"margin-right:28px\">\u0631\u06A9\u0648\u0631\u062F \u0632\u0645\u0627\u0646 \u0628\u0627\u0632\u06CC \u0647\u0627 \u0631\u0627 \u0627\u0632 \u0627\u06CC\u0646 \u0642\u0633\u0645\u062A \u0645\u06CC\u062A\u0648\u0627\u0646\u06CC\u062F \u0645\u0634\u0627\u0647\u062F\u0647 \u06A9\u0646\u06CC\u062F</p></body>\r\n</html>");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		lblNewLabel_2.setBounds(14, 29, 413, 23);
		contentPanel.add(lblNewLabel_2);
		
		lblNoItem = new JLabel("\u0647\u0646\u0648\u0632 \u0622\u06CC\u062A\u0645\u06CC \u0627\u0636\u0627\u0641\u0647 \u0646\u0634\u062F\u0647 \u0627\u0633\u062A");
		lblNoItem.setForeground(Color.DARK_GRAY);
		lblNoItem.setBounds(155, 197, 137, 14);
		contentPanel.add(lblNoItem);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u062A\u0627\u06CC\u06CC\u062F");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		loadRecords();
	}
	
	/**
	 * Load records into view
	 */
	void loadRecords(){
		if (!(GameRecords.getRecordItems().size() == 0)){
			lblNoItem.setVisible(false);
			for(int i = 0; i < GameRecords.getRecordItems().size(); i++){
				
				 String name = GameRecords.getRecordItems().get(i).getName();
				 int score = GameRecords.getRecordItems().get(i).getRecord();
					
				 Object[] row = { score, name};
	
				 DefaultTableModel model = (DefaultTableModel) table.getModel();
	
				 model.addRow(row);
			}
		}
	}
}
