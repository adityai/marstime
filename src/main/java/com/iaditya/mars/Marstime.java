/*
 * Created on Apr 16, 2016
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iaditya.mars;

import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 * @author Aditya Inapurapu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Marstime {

	public Marstime(BigDecimal millis, double ttMinusUTC, double lambda) {
		this.millis = millis;
		this.ttMinusUTC = ttMinusUTC;
		this.lambda = lambda;
	}
	
	public BigDecimal getMillis() {
		return millis;
	}

	public void setMillis(BigDecimal millis) {
		this.millis = millis;
	}

	public BigDecimal getjD_UT_BD() {
		return jD_UT_BD;
	}

	public void setjD_UT_BD(BigDecimal jD_UT_BD) {
		this.jD_UT_BD = jD_UT_BD;
	}

	public double getjD_UT() {
		return jD_UT;
	}

	public void setjD_UT(double jD_UT) {
		this.jD_UT = jD_UT;
	}

	public double getTtMinusUTC() {
		return ttMinusUTC;
	}

	public void setTtMinusUTC(double ttMinusUTC) {
		this.ttMinusUTC = ttMinusUTC;
	}

	public double getjD_TT() {
		return jD_TT;
	}

	public void setjD_TT(double jD_TT) {
		this.jD_TT = jD_TT;
	}

	public double getDeltaTj2000() {
		return deltaTj2000;
	}

	public void setDeltaTj2000(double deltaTj2000) {
		this.deltaTj2000 = deltaTj2000;
	}

	public double getM() {
		return M;
	}

	public void setM(double m) {
		M = m;
	}

	public double getaFMS() {
		return aFMS;
	}

	public void setaFMS(double aFMS) {
		this.aFMS = aFMS;
	}

	public double getPBS() {
		return PBS;
	}

	public void setPBS(double pBS) {
		PBS = pBS;
	}

	public double getvMinusM() {
		return vMinusM;
	}

	public void setvMinusM(double vMinusM) {
		this.vMinusM = vMinusM;
	}

	public double getLs() {
		return ls;
	}

	public void setLs(double ls) {
		this.ls = ls;
	}

	public double getEot() {
		return eot;
	}

	public void setEot(double eot) {
		this.eot = eot;
	}

	public double getEotH() {
		return eotH;
	}

	public void setEotH(double eotH) {
		this.eotH = eotH;
	}

	public double getMTC() {
		return MTC;
	}

	public void setMTC(double mTC) {
		MTC = mTC;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getLMST() {
		return LMST;
	}

	public void setLMST(double lMST) {
		LMST = lMST;
	}

	public double getLTST() {
		return LTST;
	}

	public void setLTST(double lTST) {
		LTST = lTST;
	}

	public double getLambdaS() {
		return lambdaS;
	}

	public void setLambdaS(double lambdaS) {
		this.lambdaS = lambdaS;
	}

	private BigDecimal millis = null; //userInput
	private BigDecimal jD_UT_BD = null;
	private double jD_UT;
	private double ttMinusUTC; //userInput
    private double jD_TT; 
	private double deltaTj2000;
    private double M;
	private double aFMS;
    private double PBS;
    private double vMinusM;
    private double ls;
	private double eot; 
	private double eotH;
	private double MTC;
	private double lambda; //userInput
	private double LMST;
	private double LTST;
	private double lambdaS;
	
	public static void main(String[] args) {
		//Marstime marstime = new Marstime( new BigDecimal("947116800000"), 64.184, 0);
		//marstime.go();
     
      Long utc = System.currentTimeMillis();
		String utcString = utc.toString();     
      System.out.println(utcString);
     
		Marstime marstime = new Marstime( new BigDecimal(utcString), 64.184, 0);
     
		//Marstime marstime = new Marstime( new BigDecimal("1468216812405"), 64.184, 0);
     	//Marstime marstime = new Marstime( new BigDecimal("947116800000"), 64.184, 0);

		marstime.go();

	}

	/**
	 * POC method that performs all the calculations for example 1.
	 */
	private void go() {
		BigDecimal jD_UT_BD = new BigDecimal("2440587.5").add( getMillis().divide(new BigDecimal("86400000"), RoundingMode.HALF_UP));//2451549.5;
		System.out.println("jD_UT_BD = " + jD_UT_BD);
     
		double jD_UT = jD_UT_BD.doubleValue(); //2451549.5;
		double jD_TT = jD_UT + (( getTtMinusUTC() ) / 86400); //2451549.50074;
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
     
		double eot = calculateEOT(ls, vMinusM); //-5.18774 = -0.34585h = -0:20:45
		double eotH = calculateEOTHours(eot);
//		Uncomment for debugging:
//		System.out.println("Actual  : " + eot + " = " + eotH + " = " + generateTimeString(eotH));
//		System.out.println("Expected: -5.18774 = -0.34585h = -0:20:45");
      
		double MTC =   ( 24 * ( ( (jD_TT - 2451549.5)/1.0274912517 )+ 44796.0 - 0.0009626 ) ) % 24  ;
		System.out.println("MTC_hours = " + MTC + " MTC = " + generateTimeString(MTC) );
		
	//lambda is the Mars latitude. 0 implies that the location is on the Mars prime meridian.
		double lambda = 0D;
		double LMST = MTC - (lambda / 15 );
		System.out.println("LMST_hours = " + LMST + " LMST = " + generateTimeString(LMST));
		
		double LTST = LMST + (eot / 15);
		System.out.println("LTST_hours = " + LTST + " LTST = " + generateTimeString(LTST));
		
	//Formula is different, but answer is correct.
		double lambdaS = ((MTC + eotH) * 15) - 180;
		System.out.println("lambdaS = " + lambdaS);

	 	double expectedLambdaS = ((23.99425 - 0.34585) * 15) - 180; 
//		Uncomment for debugging
// 		System.out.println("Expected lambdaS: 174.72600 = " + expectedLambdaS);
	 	
// 		double secondLambdaS = ((13.16537 - 0.85170) * 15) - 180;
// 		System.out.println("Second example lambdaS: " + secondLambdaS);
	}
		
	private static double calculateEOT(double ls, double vMinusM) {
		double eot = 
			2.861 * Math.sin(Math.toRadians(2 * ls)) 
			- 0.071 * Math.sin(Math.toRadians(4 * ls)) 
			+ 0.02 * Math.sin(Math.toRadians(6 * ls))
			- vMinusM;
		return eot;
	}
	
	private static double calculateEOTHours(double eot) {
		return eot / 15;
	}
	
	private static String generateTimeString(double eotH) {
		double hours_double = Math.abs(eotH);
		int hours_int = (int) hours_double;
		double minutes_double = (hours_double - hours_int) * 60;
		int minutes_int = (int) minutes_double;
		double seconds_double = (minutes_double - minutes_int) * 60;
		int seconds_int = (int) seconds_double;
		int sign = (int) (eotH / Math.abs(eotH));
		String signString = "";
		if (sign < 0) {
			signString = "-";
		}
		return signString + hours_int + ":" + minutes_int + ":" + seconds_int;
	}

}