package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double dInterestRate = 0;
		
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		for (RateDomainModel rate : rates) {
			if (GivenCreditScore >= rate.getiMinCreditScore()) {
				dInterestRate = rate.getdInterestRate();
			}
		}
		if (dInterestRate <= 0) {
			throw new RateException(rates.get(GivenCreditScore));
		}
		
		//TODO: Filter the ArrayList...  look for the correct rate for the given credit score.
		//	Easiest way is to apply a filter using a Lambda function.
		//
		//	Example... how to use Lambda functions:
		//			https://github.com/CISC181/Lambda
		return dInterestRate;
	}
	
	
	
	
	
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t) throws RateException {
//		double rate = getRate(((int) r) / 12);
//		double period = n * 12;
//		double future = 0;
//		t = false;
//		double pay = Math.abs(FinanceLib.pmt(rate, period, p, future, t));
//		return pay;
		return Math.abs(FinanceLib.pmt(r/100/12, n*12, p, f, t));
	}
}
