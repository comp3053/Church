package Model;

import java.util.Date;

public class Brew {
	private String ID;
	private String batchSize;
	private String date;
	
	public Recipe recipe;
	
	Brew(String batchSize){
		Date date = new Date();
		this.date = date.toString();
		this.batchSize = batchSize;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}
	
}
