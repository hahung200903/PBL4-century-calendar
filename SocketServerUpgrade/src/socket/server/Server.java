package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import socket.model.Calendar;
import socket.model.Date;
import socket.model.Day;

public class Server {
	private static final double LOCAL_TIMEZONE = 7.0;
	private static final int port = 2003;

	public void serve() {
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Server is ready ...");
			while(true) {
				Socket socket = server.accept();
				System.out.println("Server connected in port " + port);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							//Receiving from client
							BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							String request = br.readLine();							
							if (request != null && IsInt_ByException(request)) {
								System.out.println("Received from client: " + request);
								
								//Response to client
								Calendar response = Calculator(Integer.parseInt(request));
								ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
								oos.writeObject(response);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean IsInt_ByException(String str)
	 {
	     try
	     {
	         Integer.parseInt(str);
	         return true;
	     }
	     catch(NumberFormatException nfe)
	     {
	         return false;
	     }
	 }
	
	public static boolean isLeapYear(int y) {
        return ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0));
    }

    public static int getDow(int q, int m, int y) {
        if (m == 1) {
            m = 13;
            y--;
        }
        if (m == 2) {
            m = 14;
            y--;
        }
        int k = y % 100;
        int j = y / 100;
        int h = q + (13 * (m + 1)) / 5 + k + k / 4 + j / 4 + 5 * j;
        h = h % 7;
        return (h == 0) ? 6 : h - 1;
    }
    
    public static int INT(double d) {
		return (int)Math.floor(d);
	}

	public static int MOD(int x, int y) {
		int z = x - (int)(y * Math.floor(((double)x / y)));
		if (z == 0) {
		  z = y;
		}
		return z;
	}
    
	public static double UniversalToJD(int D, int M, int Y) {
		double JD;
		if (Y > 1582 || (Y == 1582 && M > 10) || (Y == 1582 && M == 10 && D > 14)) {
			JD = 367*Y - INT(7*(Y+INT((M+9)/12))/4) - INT(3*(INT((Y+(M-9)/7)/100)+1)/4) + INT(275*M/9)+D+1721028.5;
		} else {
			JD = 367*Y - INT(7*(Y+5001+INT((M-9)/7))/4) + INT(275*M/9)+D+1729776.5;
		}
		return JD;
	}
	
	public static int[] UniversalFromJD(double JD) {
		int Z, A, alpha, B, C, D, E, dd, mm, yyyy;
		double F;
		Z = INT(JD+0.5);
		F = (JD+0.5)-Z;
		if (Z < 2299161) {
		  A = Z;
		} else {
		  alpha = INT((Z-1867216.25)/36524.25);
		  A = Z + 1 + alpha - INT(alpha/4);
		}
		B = A + 1524;
		C = INT( (B-122.1)/365.25);
		D = INT( 365.25*C );
		E = INT( (B-D)/30.6001 );
		dd = INT(B - D - INT(30.6001*E) + F);
		if (E < 14) {
		  mm = E - 1;
		} else {
		  mm = E - 13;
		}
		if (mm < 3) {
		  yyyy = C - 4715;
		} else {
		  yyyy = C - 4716;
		}
		return new int[]{dd, mm, yyyy};
	}
	
	public static int[] LocalFromJD(double JD) {
		return UniversalFromJD(JD + LOCAL_TIMEZONE/24.0);
	}
	
	public static double LocalToJD(int D, int M, int Y) {
		return UniversalToJD(D, M, Y) - LOCAL_TIMEZONE/24.0;
	}

	public static double NewMoon(int k) {
		double T = k/1236.85; // Time in Julian centuries from 1900 January 0.5
		double T2 = T * T;
		double T3 = T2 * T;
		double dr = Math.PI/180;
		double Jd1 = 2415020.75933 + 29.53058868*k + 0.0001178*T2 - 0.000000155*T3;
		Jd1 = Jd1 + 0.00033*Math.sin((166.56 + 132.87*T - 0.009173*T2)*dr); // Mean new moon
		double M = 359.2242 + 29.10535608*k - 0.0000333*T2 - 0.00000347*T3; // Sun's mean anomaly
		double Mpr = 306.0253 + 385.81691806*k + 0.0107306*T2 + 0.00001236*T3; // Moon's mean anomaly
		double F = 21.2964 + 390.67050646*k - 0.0016528*T2 - 0.00000239*T3; // Moon's argument of latitude
		double C1=(0.1734 - 0.000393*T)*Math.sin(M*dr) + 0.0021*Math.sin(2*dr*M);
		C1 = C1 - 0.4068*Math.sin(Mpr*dr) + 0.0161*Math.sin(dr*2*Mpr);
		C1 = C1 - 0.0004*Math.sin(dr*3*Mpr);
		C1 = C1 + 0.0104*Math.sin(dr*2*F) - 0.0051*Math.sin(dr*(M+Mpr));
		C1 = C1 - 0.0074*Math.sin(dr*(M-Mpr)) + 0.0004*Math.sin(dr*(2*F+M));
		C1 = C1 - 0.0004*Math.sin(dr*(2*F-M)) - 0.0006*Math.sin(dr*(2*F+Mpr));
		C1 = C1 + 0.0010*Math.sin(dr*(2*F-Mpr)) + 0.0005*Math.sin(dr*(2*Mpr+M));
		double deltat;
		if (T < -11) {
			deltat= 0.001 + 0.000839*T + 0.0002261*T2 - 0.00000845*T3 - 0.000000081*T*T3;
		} else {
			deltat= -0.000278 + 0.000265*T + 0.000262*T2;
		};
		double JdNew = Jd1 + C1 - deltat;
		return JdNew;
	}

	public static int[] LunarMonth11(int Y) {
		double off = LocalToJD(31, 12, Y) - 2415021.076998695;
		int k = INT(off / 29.530588853);
		double jd = NewMoon(k);
		int[] ret = LocalFromJD(jd);
		double sunLong = SunLongitude(LocalToJD(ret[0], ret[1], ret[2])); // sun longitude at local midnight
		if (sunLong > 3*Math.PI/2) {
			jd = NewMoon(k-1);
		}
		return LocalFromJD(jd);
	}
    
    public static double SunLongitude(double jdn) {
    	double T = (jdn - 2451545.0 ) / 36525; // Time in Julian centuries from 2000-01-01 12:00:00 GMT
    	double T2 = T*T;
    	double dr = Math.PI/180; // degree to radian
    	double M = 357.52910 + 35999.05030*T - 0.0001559*T2 - 0.00000048*T*T2; // mean anomaly, degree
    	double L0 = 280.46645 + 36000.76983*T + 0.0003032*T2; // mean longitude, degree
    	double DL = (1.914600 - 0.004817*T - 0.000014*T2)*Math.sin(dr*M);
    	DL = DL + (0.019993 - 0.000101*T)*Math.sin(dr*2*M) + 0.000290*Math.sin(dr*3*M);
    	double L = L0 + DL; // true longitude, degree
    	L = L*dr;
    	L = L - Math.PI*2*(INT(L/(Math.PI*2))); // Normalize to (0, 2*PI)
    	return L;
    }
    
    public static final double[] SUNLONG_MAJOR = new double[] {
    		0, Math.PI/6, 2*Math.PI/6, 3*Math.PI/6, 4*Math.PI/6, 5*Math.PI/6, Math.PI, 7*Math.PI/6, 8*Math.PI/6, 9*Math.PI/6, 10*Math.PI/6, 11*Math.PI/6
    	};

    	public static int[][] LunarYear(int Y) {
    		int[][] ret = null;
    		int[] month11A = LunarMonth11(Y-1);
    		double jdMonth11A = LocalToJD(month11A[0], month11A[1], month11A[2]);
    		int k = (int)Math.floor(0.5 + (jdMonth11A - 2415021.076998695) / 29.530588853);
    		int[] month11B = LunarMonth11(Y);
    		double off = LocalToJD(month11B[0], month11B[1], month11B[2]) - jdMonth11A;
    		boolean leap = off > 365.0;
    		if (!leap) {
    			ret = new int[13][5];
    		} else {
    			ret = new int[14][5];
    		}
    		ret[0] = new int[]{month11A[0], month11A[1], month11A[2], 0, 0};
    		ret[ret.length - 1] = new int[]{month11B[0], month11B[1], month11B[2], 0, 0};
    		for (int i = 1; i < ret.length - 1; i++) {
    			double nm = NewMoon(k+i);
    			int[] a = LocalFromJD(nm);
    			ret[i] = new int[]{a[0], a[1], a[2], 0, 0};
    		}
    		for (int i = 0; i < ret.length; i++) {
    			ret[i][3] = MOD(i + 11, 12);
    		}
    		if (leap) {
    			initLeapYear(ret);
    		}
    		return ret;
    	}

    	static void initLeapYear(int[][] ret) {
    		double[] sunLongitudes = new double[ret.length];
    		for (int i = 0; i < ret.length; i++) {
    			int[] a = ret[i];
    			double jdAtMonthBegin = LocalToJD(a[0], a[1], a[2]);
    			sunLongitudes[i] = SunLongitude(jdAtMonthBegin);
    		}
    		boolean found = false;
    		for (int i = 0; i < ret.length; i++) {
    			if (found) {
    				ret[i][3] = MOD(i+10, 12);
    				continue;
    			}
    			double sl1 = sunLongitudes[i];
    			double sl2 = sunLongitudes[i+1];
    			boolean hasMajorTerm = Math.floor(sl1/Math.PI*6) != Math.floor(sl2/Math.PI*6);
    			if (!hasMajorTerm) {
    				found = true;
    				ret[i][4] = 1;
    				ret[i][3] = MOD(i+10, 12);
    			}
    		}		
    	}
    
    public static Day SolarToLunar(int D, int M, int Y) {
    	int yy = Y;
    	int[][] ly = LunarYear(Y); // Please cache the result of this computation for later use!!!
    	int[] month11 = ly[ly.length - 1];
    	double jdToday = LocalToJD(D, M, Y);
    	double jdMonth11 = LocalToJD(month11[0], month11[1], month11[2]);
    	if (jdToday >= jdMonth11) {
    		ly = LunarYear(Y+1);
    		yy = Y + 1;
    	}
    	int i = ly.length - 1;
    	while (jdToday < LocalToJD(ly[i][0], ly[i][1], ly[i][2])) {
    		i--;
    	}
    	int dd = (int)(jdToday - LocalToJD(ly[i][0], ly[i][1], ly[i][2])) + 1;
    	int mm = ly[i][3];
    	if (mm >= 11) {
    		yy--;
    	}
        return new Day(dd, mm, yy);
    }
    
    public static String getDetailLunar(Day ngayAm, double jd) {
    	int d = ngayAm.day, m = ngayAm.month, y = ngayAm.year;
    	String[] can = {"Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý"};
    	String[] chi = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"};
    	String ngay = can[INT(jd)%10] + " " + chi[INT(jd+2)%12];
    	String thang = can[(y*12+m+3)%10] + " " + chi[m>=11?m-11:m+1];
    	String nam = can[(y+6)%10] + " " + chi[(y+8)%12];
    	return "Ngày " + ngay + ", Tháng " + thang + ", Năm " + nam;
    }

    public static Date[][] initCal(int y, int m) {
        int rows = 6;
        int cols = 7;
        Date[][] cal = new Date[rows][cols];

        int day = 1;
        int dow = getDow(day, m, y);
        int dim = 0, dipm = 0;
        switch(m) {
        case 4:
        case 6:
        case 9:
        case 11: dim = 30; dipm = 31; break;
        case 5:
        case 7:
        case 10:
        case 12: dim = 31; dipm = 30; break;
        case 1:
        case 8: dim = 31; dipm = 31; break;
        case 2: dim = isLeapYear(y) ? 29 : 28; dipm = 31; break;
        case 3: dim = 31; dipm = isLeapYear(y) ? 29 : 28; break;
        }
        
        int d, mm, yyyy;
        for (int i = 0; i < rows; i++) {
            if (i == 0) {
                for (int j = dow-1; j >= 0; j--) {
                	d = dipm--;
                	mm = m==1 ? 12: m-1;
                	yyyy = y;
                	cal[i][j] = new Date(new Day(d, mm, yyyy), SolarToLunar(d, mm, yyyy), getDetailLunar(SolarToLunar(d, mm, yyyy), LocalToJD(d, mm, yyyy)), "", "");
                }                
                for (int j = dow; j < cols; j++) {
                	d = day++;
                	mm = m;
                	yyyy = y;
                	cal[i][j] = new Date(new Day(d, mm, yyyy), SolarToLunar(d, mm, yyyy), getDetailLunar(SolarToLunar(d, mm, yyyy), LocalToJD(d, mm, yyyy)), "", "");
                }
            } else {
                for (int j = 0; j < cols; j++) {
                    if (day > dim) {
                    	mm = m==12 ? 1 : m+1;
                    	yyyy = m==12 ? y+1 : y;
                    	day = 1;
                    }
                    d = day++;
                    mm = m;
                    yyyy = y;
                    cal[i][j] = new Date(new Day(d, mm, yyyy), SolarToLunar(d, mm, yyyy), getDetailLunar(SolarToLunar(d, mm, yyyy), LocalToJD(d, mm, yyyy)), "", "");
                }
            }
        }
        return cal;
    }
    
    public static Calendar Calculator(int y) {
    	Date thang1[][] = initCal(y, 1);
    	Date thang2[][] = initCal(y, 2);
    	Date thang3[][] = initCal(y, 3);
    	Date thang4[][] = initCal(y, 4);
    	Date thang5[][] = initCal(y, 5);
    	Date thang6[][] = initCal(y, 6);
    	Date thang7[][] = initCal(y, 7);
    	Date thang8[][] = initCal(y, 8);
    	Date thang9[][] = initCal(y, 9);
    	Date thang10[][] = initCal(y, 10);
    	Date thang11[][] = initCal(y, 11);
    	Date thang12[][] = initCal(y, 12);
    	Calendar calendar = new Calendar(thang1, thang2, thang3, thang4, thang5, thang6, thang7, thang8, thang9, thang10, thang11, thang12);
    	
        return calendar;
    }
	
	public static void main(String[] args) {
		Server s = new Server();
		s.serve();
	}
}
