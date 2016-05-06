/*
 * Created on Apr 16, 2016
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iaditya.mars;

import java.math.BigDecimal;



/**
 * @author Aditya Inapurapu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Marstime {

	public static void main(String[] args) {
//		double tt;
//		double utc;
		BigDecimal millis = new BigDecimal("947116800000");
		BigDecimal jD_UT_BD = new BigDecimal("2440587.5").add(millis.divide(new BigDecimal("86400000")));//2451549.5;
		
		double jD_UT = 2451549.5;
		double ttMinusUTC = 64.184;
		double jD_TT = jD_UT + ((ttMinusUTC) / 86400); //2451549.50074;
		double deltaTj2000 = jD_TT - 2451545.0;//4.50074;
		double aFMS = 270.3871 + 0.524038496 * deltaTj2000;//272.7456;
		double vMinusM = 4.44193;
		double ls = aFMS + vMinusM;
		double eot = getEOT(ls, vMinusM);
		double eotH = getEOTHours(eot);
		System.out.println(eot + " = " + eotH + " = " + getTimeString(eotH));
		System.out.println("Expected: -0.34585 = " + getTimeString(-0.34585));
	}
		
	private static double getEOT(double ls, double vMinusM) {
		double eot = 
			2.861 * Math.sin(Math.toRadians(2 * ls)) 
			- 0.071 * Math.sin(Math.toRadians(4 * ls)) 
			+ 0.02 * Math.sin(Math.toRadians(6 * ls))
			- vMinusM;
		return eot;
	}
	
	private static double getEOTHours(double eot) {
		return eot / 15;
	}
	
	private static String getTimeString(double eotH) {
		double hours_double = Math.abs(eotH);
//		System.out.println(hours_double);
		
		int hours_int = (int) hours_double;
//		System.out.println("h=" + hours_int);
		
		double minutes_double = (hours_double - hours_int) * 60;
//		System.out.println(minutes_double);
		
		int minutes_int = (int) minutes_double;
//		System.out.println("m=" + minutes_int);
		
		double seconds_double = (minutes_double - minutes_int) * 60;
//		System.out.println(seconds_double);
		
		int seconds_int = (int) seconds_double;
//		System.out.println(seconds_int);

		int sign = (int) (eotH / Math.abs(eotH));
		String signString = "";
		if (sign < 0) {
			signString = "-";
		}
		return signString + hours_int + ":" + minutes_int + ":" + seconds_int;
	}

}