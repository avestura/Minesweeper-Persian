package DesignView.Handlers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import DesignView.Components.AudioPlayer;
import DesignView.Components.AudioPlayer.DefinedAudios;
import DesignView.Frames.GamePad;
import DesignView.Frames.MainMenu;
import DesignView.Frames.Records;

public class LabelButtons {
	
	private MainMenu mainFrame;
	private int passMinerW;
	private int passMinerH;
	private int passMinerP;
	
	public LabelButtons(MainMenu arg){
		mainFrame = arg;
	}
	
	private void updateUpcomingGameSettings(){
	
		passMinerH = mainFrame.getMinerHSeterValue();
		passMinerP = mainFrame.getMinerPerSeterValue();
		passMinerW = mainFrame.getMinerWSeterValue();
		
	}

	
	
	public LabelButtonMouseListener createMouseListener(){
		return new LabelButtonMouseListener();
	}
	
	public class LabelButtonMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
	
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel sender = (JLabel)e.getSource();
			
			if (sender.isEnabled()){
				sender.setBorder(new LineBorder(new Color(102, 194, 255)));
				sender.setBackground(new Color(204, 235, 255));
				sender.setForeground(new Color(0, 102, 204));
		
					try {
						AudioPlayer.playAudioByResourceName(DefinedAudios.FAST_Sound);
					} catch (Exception ex){}
				}
	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			JLabel sender = (JLabel)e.getSource();
			if (sender.isEnabled()){
				sender.setBorder(new LineBorder(new Color(192, 192, 192)));
				sender.setBackground(new Color(240,240,240));
				sender.setForeground(Color.black);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JLabel sender = (JLabel)e.getSource();
				if (sender.isEnabled()){
				sender.setBorder(new LineBorder(new Color(0, 138, 230)));
				sender.setBackground(new Color(26, 163, 255));
				sender.setForeground(Color.white);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseEntered(e);
			JLabel sender = (JLabel)e.getSource();
			switch(sender.getName()){
			
				// Start Game Button
				case "startBtn":
					updateUpcomingGameSettings();
					mainFrame.setVisible(false);
					GamePad padNew = new GamePad(false, passMinerW, passMinerH, passMinerP);
					padNew.setVisible(true);
					break;
					
				// Load Game Button
				case "loadBtn":
					File saveFile = new File("save.ser");					
					if (saveFile.exists()){
						updateUpcomingGameSettings();
						mainFrame.setVisible(false);
						GamePad padLoad = new GamePad(true);
						padLoad.setVisible(true);
					}
					break;
					
				// Records Button
				case "recordsBtn":
					Records.main(null);
					break;
					
				// Exit Button
				case "exitBtn":
					mainFrame.dispose();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {}
						System.exit(0);
					break;
			}
			
		}
		
	}
	

}
