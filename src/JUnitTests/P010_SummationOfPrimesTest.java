package JUnitTests;

/** Strategy: Brute Force. */
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.translationdata.p010.P010_SummationOfPrimes;

@RunWith(Parameterized.class)
public class P010_SummationOfPrimesTest {

	int limit;
	long prime;
	private static P010_SummationOfPrimes summationOfPrimes = new P010_SummationOfPrimes();

	@Parameters
	public static List<Object []> data() {
		return Arrays.asList( new Object[][] {
			// takes ~133 seconds on an i7 laptop
			{ 2_000_000, 142913828922L }, 
			{ 6,		 10L }, 
			{ 10,		 17L } 
		} );
	}

	public P010_SummationOfPrimesTest(int limit, long prime) {
		this.limit = limit;
		this.prime = prime;
	}

	private final int oneSecond = 1_000;
	@Test( timeout = 500 * oneSecond )
	public void SummationOfPrimes() {
		long primeSum = summationOfPrimes.primesSum( limit );
		assertEquals( prime, primeSum );
		System.out.printf("P010_SummationOfPrimesTest %d%n", primeSum);
	}
	
}
