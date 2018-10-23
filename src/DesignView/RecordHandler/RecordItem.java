package DesignView.RecordHandler;

public class RecordItem{
	private String name;
	private int record;
	
	public RecordItem(){}
	public RecordItem(String name, int record){
		this.name = name;
		this.record = record;
	}
	
	public String getName(){
		return name;
	}
	public int getRecord(){
		return record;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setRecord(int record){
		this.record = record;
	}
}