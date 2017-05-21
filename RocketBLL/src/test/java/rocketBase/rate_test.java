package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score

	@Test
	public void rate_1_Test() throws RateException {
		assertTrue(6.0==RateBLL.getRate(730));
		assertNotEquals(6.0, RateBLL.getRate(680));
	}
	
	@Test 
	public void rate_2_Test() throws RateException {
		RateBLL.getRate(600);
	}
	
	@Test
	public void GetPaymentTest() throws RateException{
		double pmt = RateBLL.getPayment(4, 30, 300000, 0, false);
		System.out.println(pmt);
		java.text.DecimalFormat format =new java.text.DecimalFormat("#.00");  
		format.format(pmt);
		assertEquals(pmt,1432.25,0.01);
}
	
}
