package DesignView.Components;

import java.net.URL;
import javax.swing.ImageIcon;
import DesignView.Frames.MainMenu;

public class IconProvider {

	public enum GameIconSet{
		BombIcon16, BombIcon32, BombIcon48, Mine16,Mine24, Mine32, Mine24Gray, Flag16, Flag20, Flower, Score32
	}
	
	public static ImageIcon getIconResource(GameIconSet x){
		switch(x){
			case BombIcon16:
				return new ImageIcon(generateURL("Bomb With Timer-16.png"));
			case BombIcon32:
				return new ImageIcon(generateURL("Bomb With Timer-32.png"));
			case BombIcon48:
				return new ImageIcon(generateURL("Bomb With Timer.png"));	
			case Mine16:
				return new ImageIcon(generateURL("Self-Destruct Button-16.png"));
			case Mine24:
				return new ImageIcon(generateURL("Self-Destruct Button-24.png"));
			case Mine32:
				return new ImageIcon(generateURL("Self-Destruct Button-32.png"));
			case Mine24Gray:
				return new ImageIcon(generateURL("Self-Destruct Button-Grayscale-24.png"));
			case Flag16:
				return new ImageIcon(generateURL("Flag-16.png"));
			case Flag20:
				return new ImageIcon(generateURL("Flag-20.png"));
			case Flower:
				return new ImageIcon(generateURL("Flowers-24.png"));
			case Score32:
				return new ImageIcon(generateURL("Rating-32.png"));
			default:
					return null;
		}
	}
	
	private static URL generateURL(String res){
		return MainMenu.class.getResource("/DesignView/Images/" + res);
	}
}
