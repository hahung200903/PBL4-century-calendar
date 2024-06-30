package socket.model;

import java.io.Serializable;

public class Calendar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date January[][];
	private Date February[][];
	private Date March[][];
	private Date April[][];
	private Date May[][];
	private Date June[][];
	private Date July[][];
	private Date August[][];
	private Date September[][];
	private Date October[][];
	private Date November[][];
	private Date December[][];
	
	public Calendar(Date[][] january, Date[][] february, Date[][] march, Date[][] april, Date[][] may, Date[][] june,
			Date[][] july, Date[][] august, Date[][] september, Date[][] october, Date[][] november, Date[][] december) {
		super();
		January = january;
		February = february;
		March = march;
		April = april;
		May = may;
		June = june;
		July = july;
		August = august;
		September = september;
		October = october;
		November = november;
		December = december;
	}
	
	public Date[][] getJanuary() {
		return January;
	}
	public Date[][] getFebruary() {
		return February;
	}
	public Date[][] getMarch() {
		return March;
	}
	public Date[][] getApril() {
		return April;
	}
	public Date[][] getMay() {
		return May;
	}
	public Date[][] getJune() {
		return June;
	}
	public Date[][] getJuly() {
		return July;
	}
	public Date[][] getAugust() {
		return August;
	}
	public Date[][] getSeptember() {
		return September;
	}
	public Date[][] getOctober() {
		return October;
	}
	public Date[][] getNovember() {
		return November;
	}
	public Date[][] getDecember() {
		return December;
	}
}
