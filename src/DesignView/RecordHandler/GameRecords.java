package DesignView.RecordHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameRecords {
	


	static ArrayList<RecordItem> recordItems = new ArrayList<>();
	static File recordFile = new File("records.txt");

	public static ArrayList<RecordItem> getRecordItems() {
		return recordItems;
	}

	public static void saveRecord(RecordItem rItem) {
		try{
			FileWriter fWriter = new FileWriter(recordFile, true);
			fWriter.write(String.format("%s,%d|",
					rItem.getName(),
					rItem.getRecord()));
			fWriter.flush();
			fWriter.close();
		}
		catch(Exception ex){}
	}

	public static void loadRecords() {
		try {
			boolean hasRecords = recordFile.exists();
			if (hasRecords) {
				recordItems.clear();
				Scanner in = new Scanner(new FileReader(recordFile));
				while (in.hasNextLine()) {
					String token = in.nextLine();
					if (token.contains("|")){
						String[] recordUnits = token.split("\\|");
						for(int i = 0 ; i < recordUnits.length; i++){
							if (recordUnits[i].contains(",")){
								String name = recordUnits[i].split(",")[0];
								int score = Integer.parseInt(recordUnits[i].split(",")[1]);
								recordItems.add(new RecordItem(name, score));
							}
						}
					}
				}
				in.close();
			}
		} catch (IOException e) {}
	
	}

}
