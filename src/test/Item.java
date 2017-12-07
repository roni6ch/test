package test;

import java.sql.Timestamp;

public class Item {
	int id;
	String date;
	String note;
	boolean done;

	public Item(int id2,String date2, String note2, boolean done2) {
		this.id = id2;
		this.date = date2;
		this.note = note2;
		this.done = done2;
	}

	public int getId() {
		return id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
