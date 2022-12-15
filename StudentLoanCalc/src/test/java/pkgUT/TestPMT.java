package pkgUT;


import org.apache.poi.ss.formula.functions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import pkgLogic.Loan;

public class TestPMT {

	@Test
	public void TestPMT() {
		
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));		
		double PMTExpected = 1162.95;		
		assertEquals(PMTExpected, PMT, 0.01);
	}
	@Test
	public void TestLoanWithNoExtraPayment() {
		
		//This unit test should work with the values given
		double dLoanAmount = 50000;
		double dInterestRate = 0.07;
		double dAdjustLength = 0.00;
		double dAdjustPeriod = 0.00;
		double dAdjustRateMax = 0.00;
		int iNbrOfYears = 20;
		LocalDate localDate = LocalDate.now();
		double dAdditionalPayment = 0;
		double dEscrow = 0;

		Loan loan = new Loan(dLoanAmount, dInterestRate, dAdjustLength, dAdjustPeriod, dAdjustRateMax, 
				iNbrOfYears*12, localDate, dAdditionalPayment, dEscrow);
		
		assertTrue(loan.getLoanPayments().size() == 240);
		assertEquals(loan.getTotalPayments(), 93033.62, 0.01);
		assertEquals(loan.getTotalInterest(), 43035.87, 0.01);
	}
	
	@Test
	public void TestLoanWithExtraPayment() {
		
		//This unit test should work with the values given
		double dLoanAmount = 50000;
		double dInterestRate = 0.07;
		double dAdjustLength = 0.00;
		double dAdjustPeriod = 0.00;
		double dAdjustRateMax = 0.00;
		int iNbrOfYears = 20;
		LocalDate localDate = LocalDate.now();
		double dAdditionalPayment = 200;
		double dEscrow = 0;

		Loan loan = new Loan(dLoanAmount, dInterestRate, dAdjustLength, dAdjustPeriod, dAdjustRateMax, 
				iNbrOfYears*12, localDate, dAdditionalPayment, dEscrow);

		System.out.println(loan.getLoanPayments().size() == 240);
		System.out.println(loan.getTotalPayments() - (loan.getTotalPayments() + dAdditionalPayment));
		
		assertTrue(loan.getLoanPayments().size() == 240);
		assertEquals(loan.getTotalPayments(), 93036, 0.01);
		assertEquals(loan.getTotalInterest(), 43035.87, 0.01);
		assertEquals(loan.getTotalInterest() - ((loan.getTotalInterest() + dAdditionalPayment)), 43035.87, 0.01);
		assertEquals(loan.getTotalPayments() - (loan.getTotalPayments() + dAdditionalPayment), 80970709, 987);
		//TODO: Assert correct values based on amort spreadsheet (total payments, total payment amt, 
		//		total interest, total interest saved, total payments saved.
	}	
}

 

