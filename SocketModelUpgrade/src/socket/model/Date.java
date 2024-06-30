package socket.model;

import java.io.Serializable;

public class Date implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Day Solar;
	public Day Lunar;
	public String DetailLunar;
	public String SolarHoliday;
	public String LunarHoliday;
	
	public Date(Day solar, Day lunar, String detailLunar, String solarHoliday, String lunarHoliday) {
		super();
		Solar = solar;
		Lunar = lunar;
		DetailLunar = detailLunar;
		SolarHoliday = solarHoliday;
		LunarHoliday = lunarHoliday;
	}
}
