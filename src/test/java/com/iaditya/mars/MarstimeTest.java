package com.iaditya.mars;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MarstimeTest {

	@Test
	public void testGo() {
		//Marstime marstime = new Marstime( new BigDecimal("1468216812405"), 64.184, 0);
		System.out.println("***** Test for midnight on millenium:");
     	Marstime marstime = new Marstime( new BigDecimal("947116800000"), 64.184, 0);
		assertEquals("23:59:39", marstime.go());
	}

}
