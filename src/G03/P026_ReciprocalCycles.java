package G03;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;
/**
 * Strategy: Brute Force
 * Multiply the numerator by 2000 to get the division 
 * results on the left of the decimal point.
 * Use BigIntegers to hold the results of a division that has 2000
 * digits of precision.
 * Reverse the string result of the division so that the cycles are
 * on the left. Now the result reads like Arabic, right to left.
 * Look for cycles starting on the left for all numbers from 2 to 999.
 * @author Harold Almon
 *
 */
public class P026_ReciprocalCycles {
	private final boolean FALSE = false;
	int maximumCycle = 0;

	public int reciprocalCycles(int upperLimit) {
		StringBuilder reverseQuotient = new StringBuilder("");

		int precision = 2000;
		StringBuffer numeratorString = new StringBuffer("1");

		for (int i = 0; i < precision; i++) {
			numeratorString.append( "0" );
		}

		BigInteger numerator = new BigInteger(numeratorString.toString());
		if (FALSE)
			System.out.printf("No. of digits calculated = %d%n",precision);
		
		int cycle = enumerateDenominator(upperLimit, reverseQuotient, precision, numerator);

		return cycle;
	}

	private int enumerateDenominator(int limit, StringBuilder reverseQuotient,
			int precision, BigInteger numerator) {
		int cycle = 0;
		BigInteger quotient;

		for (int denominator = 2; denominator < limit; denominator++) {
			quotient = numerator.divide(new BigInteger(Integer.toString(denominator)) );
			reverseQuotient.append(quotient.toString()).reverse();
			cycle = findDenominatorWIthMaximumCycleLength(reverseQuotient, precision, cycle, denominator);
			reverseQuotient.setLength(0);
		}

		if(FALSE)
			System.out.printf("Maximum cycle length found = %d%n", maximumCycle);
		return cycle;
	}

	private int findDenominatorWIthMaximumCycleLength(
			StringBuilder reverseQuotient, int precision, int cycle,
			int denominator) {
		for (int length = 1; length < precision / 2; length++) {
			if( reverseQuotient.toString().substring(0, length).equals(reverseQuotient.toString().substring(length, length*2))) {
				if(length > maximumCycle) {
					maximumCycle = length;
					cycle = denominator;
				}
				break;
			}
		}
		return cycle;
	}

	@Test(timeout = 2_000)
	public void ReciprocalCycles() {
		int maximumCycleLength = reciprocalCycles(1_000);
		System.out.printf("reciprocalCycles(1_000) = %d%n", maximumCycleLength);
		assertEquals(983, maximumCycleLength);
	}
}
