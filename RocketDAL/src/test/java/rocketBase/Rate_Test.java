package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	// - RocketDAL rate_test
	// Check to see if a known credit score returns a known interest rate
	@Test
	public void test1() throws Exception {
		ArrayList<RateDomainModel> rate = RateDAL.getAllRates();
		System.out.println("Rates size: " + rate.size());
		assertEquals(rate.size(), 5);

		assertEquals(rate.get(4).getiMinCreditScore(), 800);
		assertEquals(rate.get(3).getiMinCreditScore(), 750);
		assertEquals(rate.get(2).getiMinCreditScore(), 700);
		assertEquals(rate.get(1).getiMinCreditScore(), 650);
		assertEquals(rate.get(0).getiMinCreditScore(), 600);

		// - RocketDAL rate_test
		// Check to see if a RateException is thrown if there are no rates for a
		// given
		// credit score

		assertEquals(rate.get(4).getdInterestRate(), 5.50, 0.01);
		assertEquals(rate.get(3).getdInterestRate(), 5.75, 0.01);
		assertEquals(rate.get(2).getdInterestRate(), 6.00, 0.01);
		assertEquals(rate.get(1).getdInterestRate(), 6.50, 0.01);
		assertEquals(rate.get(0).getdInterestRate(), 7.00, 0.01);
		assertNotEquals(rate.get(1).getdInterestRate(), 7.00, 0.01);
	}
	
	@Test 
	public void test2() throws Exception {
		
	}
}
