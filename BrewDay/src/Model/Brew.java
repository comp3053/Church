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
		return this.ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getBatchSize() {
		return this.batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}
	
}
