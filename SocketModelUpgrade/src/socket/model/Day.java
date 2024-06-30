package socket.model;

import java.io.Serializable;

public class Day implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int day;
	public int month;
	public int year;
	public Day(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}
}
