package DesignView.SaveGame;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class GameSave implements Serializable{
	
	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 2582038396500569491L;
	
	private int sceneWidth;
	private int sceneHeight;
	private int goodCellCount;
	private int timeCount;
	private int minePercent;
	
	private ArrayList<Boolean> mineList;
	private ArrayList<SavingElement> elementsList;
	
	
	public GameSave(int width,
			int height,
			int goodCell,
			int timeCount,
			int minePer,
			ArrayList<Boolean> mineList,
			ArrayList<SavingElement> elementsList) {
		
		sceneHeight = height;
		sceneWidth = width;
		this.goodCellCount = goodCell;
		this.timeCount = timeCount;
		this.minePercent = minePer;
		this.mineList = mineList;
		this.elementsList = elementsList;
	}
	
	public static void saveGame(GameSave saveObj){
	     try
	      {
	         FileOutputStream fileOut = new FileOutputStream("save.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(saveObj);
	         out.close();
	         fileOut.close();
	         //System.out.printf("Game Saved!");
	      }catch(Exception ex)
	      {
	    	  ex.printStackTrace();
	    	  //System.out.printf("Saving game failed!");
	 
	      }
	}
	
	public ArrayList<Boolean> getMineList(){
		return mineList;
	}
	
	public ArrayList<SavingElement> getElementsList(){
		return elementsList;
	}
	
	public int getWidth(){
		return sceneWidth;
	}
	public int getHeight(){
		return sceneHeight;
	}
	public int getGoodCellCount(){
		return goodCellCount;
	}
	public int getTimeCount(){
		return timeCount;
	}
	public int getMineTrafic(){
		return minePercent;
	}
}
