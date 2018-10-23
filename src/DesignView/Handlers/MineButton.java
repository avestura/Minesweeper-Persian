package DesignView.Handlers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import DesignView.BlockHandler;
import DesignView.Components.AudioPlayer;
import DesignView.Components.IconProvider;
import DesignView.Components.PersianNumeric;
import DesignView.Components.AudioPlayer.DefinedAudios;
import DesignView.Components.IconProvider.GameIconSet;
import DesignView.Frames.GamePad;

public class MineButton extends JLabel {

	private int mineWidth;
	private int mineHeight;
	
	public MineButton(int minW, int minH) {
		setBorder(new LineBorder(new Color(192, 192, 192)));
		setOpaque(true);
		setBackground(new Color(240, 240, 240));
		addMouseListener(new MineMouseEvent());
		mineWidth = minW;
		mineHeight = minH;
	}
	
	public static int findMineID(String name){
		return Integer.parseInt(name.replace(",selected", ""));
	}

	class MineMouseEvent implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

			JLabel sender = (JLabel) e.getSource();
			if (sender.getName() != null && !sender.getName().toString().contains(",selected")) {
				sender.setBorder(new LineBorder(new Color(102, 194, 255)));
				sender.setBackground(new Color(204, 235, 255));
				sender.setForeground(new Color(0, 102, 204));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {

			JLabel sender = (JLabel) e.getSource();
			if (sender.getName() != null && !sender.getName().toString().contains(",selected")) {
				sender.setBorder(new LineBorder(new Color(192, 192, 192)));
				sender.setBackground(new Color(240, 240, 240));
				sender.setForeground(Color.black);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JLabel sender = (JLabel) e.getSource();
			
			if (sender.getName() != null && !sender.getName().toString().contains(",selected")) {
				boolean isRightClick =  SwingUtilities.isRightMouseButton(e);
				if (!isRightClick){
					sender.setBorder(new LineBorder(new Color(0, 138, 230)));
					sender.setBackground(new Color(26, 163, 255));
					sender.setForeground(Color.white);
				}
				else{
					if (sender.getIcon() == null) sender.setIcon(IconProvider.getIconResource(GameIconSet.Flag20));
					else sender.setIcon(null);
				}
			}

		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			JLabel sender = (JLabel) e.getSource();
			if (sender.getName() != null && !sender.getName().toString().contains(",selected")) {
				
				mouseEntered(e);
				boolean isRightClick = SwingUtilities.isRightMouseButton(e);
				if (sender.getName() != null && !isRightClick) {
					int buttonID =  findMineID(sender.getName());
					if (GamePad.mineList.get(buttonID)) { // Check if Bomb
						try {
							AudioPlayer.playAudioByResourceName(DefinedAudios.EXPLODE_Sound);
						} catch (Exception ex) {}
						
						
						GamePad.gameLost();
						sender.setName(buttonID + ",selected");
						sender.setBackground(Color.pink);
						sender.setBorder(new LineBorder(new Color(255, 92, 51)));
						sender.setIcon(IconProvider.getIconResource(GameIconSet.Mine24));
						
						
					} else { // If not bomb
						if (!GamePad.isGameStatred()) GamePad.startTimer();
						sender.setName(buttonID + ",selected");
						sender.setBackground(new Color(238, 255, 204));
						sender.setIcon(null);
						setForeground(new Color(85, 128, 0));
						sender.setBorder(new LineBorder(Color.lightGray));
						
						GamePad.increaseGoodCellCount();
						
						int[] indexes = BlockHandler.getNeighborIndexes(buttonID, mineWidth, mineHeight);
						int bombCount = 0;
						for(int i = 0; i < indexes.length; i++) if (GamePad.mineList.get(indexes[i])) bombCount++;
						
						if (bombCount != 0){
							sender.setText(PersianNumeric.toPersianNumberic(String.valueOf(bombCount)));
						}
						else{
							GamePad.freeNearbyBlocks(buttonID, mineWidth, mineHeight);
						}
						
					}
				}

			}
		}
	}

}
