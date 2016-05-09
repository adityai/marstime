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
		System.out.println("jD_UT_BD = " + jD_UT_BD);
     
		double jD_UT = jD_UT_BD.doubleValue(); //2451549.5;
		double ttMinusUTC = 64.184;
		
      double jD_TT = jD_UT + ((ttMinusUTC) / 86400); //2451549.50074;
      System.out.println("jD_TT = " + jD_TT);
     
		double deltaTj2000 = jD_TT - 2451545.0;//4.50074;
      System.out.println("deltaTj2000 = " + deltaTj2000);
      
      double M = 19.3871 + 0.52402073 * deltaTj2000;
      System.out.println("M = " + M);
     
		double aFMS = 270.3871 + 0.524038496 * deltaTj2000;//272.7456;
      System.out.println("aFMS = " + aFMS);
     
      //Complex calculation using sigma. Revisit later.
      double PBS = 0.00142;
      System.out.println("PBS = " + PBS);
     
		double vMinusM = ( ( 10.691 + 3.0 * Math.pow(10, -7) * deltaTj2000 ) * Math.sin(Math.toRadians(M) ) ) + 
                        0.623 * Math.sin(Math.toRadians(2 * M)) + 
        						0.05 * Math.sin(Math.toRadians(3 * M)) + 
        						0.005 * Math.sin(Math.toRadians(4 * M)) + 
        						0.0005 * Math.sin(Math.toRadians(5 * M)) + PBS; //4.44193;
      System.out.println("vMinusM = " + vMinusM);
     
		double ls = aFMS + vMinusM; //277.18758
      System.out.println("ls = " + ls);
     
		double eot = getEOT(ls, vMinusM); //-5.18774 = -0.34585h = -0:20:45
		double eotH = getEOTHours(eot);
		System.out.println("Actual  : " + eot + " = " + eotH + " = " + getTimeString(eotH));
		System.out.println("Expected: -5.18774 = -0.34585h = -0:20:45");
      
      double MTC =   ( 24 * ( ( (jD_TT - 2451549.5)/1.0274912517 )+ 44796.0 - 0.0009626 ) ) % 24  ;
      System.out.println("MTC_hours = " + MTC + " MTC = " + getTimeString(MTC) );
		
		//lambda is the Mars latitude. 0 implies that the location is on the Mars prime meridian.
		double lambda = 0D;
		double LMST = MTC - (lambda / 15 );
		System.out.println("LMST_hours = " + LMST + " LMST = " + getTimeString(LMST));
		
		double LTST = LMST + (eot / 15);
		System.out.println("LTST_hours = " + LTST + " LTST = " + getTimeString(LTST));
		
		double lambdaS = ((MTC + eotH) * 15) - 180;
		System.out.println("lambdaS = " + lambdaS);
		double expectedLambdaS = ((23.99425 - 0.34585) * 15) - 180; 
		System.out.println("Expected lambdaS: 174.72600 = " + expectedLambdaS);
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