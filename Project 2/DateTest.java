package project2;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit testing class for the Date class.
 */
public class DateTest {

	/**
	 * JUnit test that compares equivalent dates.
	 */
	@Test
	public void compareToTest1() {
		Date test = new Date(10, 8, 2020);
		int result = test.compareTo(test);
		assertEquals(result, 0);
	}
	
	/**
	 * JUnit test that compares non-equivalent dates.
	 */
	@Test
	public void compareToTest2() {
		Date testA = new Date(10, 8, 2020);
		Date testB = new Date(10, 7, 2020);
		int result = testA.compareTo(testB);
		assertEquals(result, 1);
	}
	
	/**
	 * JUnit test that compares non-equivalent dates.
	 */
	@Test
	public void compareToTest3() {
		Date testA = new Date(10, 8, 2020);
		Date testB = new Date(10, 7, 2020);
		int result = testB.compareTo(testA);
		assertEquals(result, -1);
	}
	
	/**
	 * JUnit test that inputs a date with single digit months.
	 */
	@Test
	public void toStringTest1() {
		Date test = new Date(1, 1, 2020);
		String date = test.toString();
		assertEquals(date, "1/1/2020");
	}
	
	/**
	 * JUnit test that inputs a date with double digit months.
	 */
	@Test
	public void toStringTest2() {
		Date test = new Date(10, 10, 2020);
		String date = test.toString();
		assertEquals(date, "10/10/2020");
	}
	
	/**
	 * JUnit test that runs a valid date into isValid().
	 */
	@Test
	public void isValidTest1() {
		Date test = new Date(10, 8, 2020);
		boolean result = test.isValid();
		assertEquals(result, true);
	}
	
	/**
	 * JUnit test that inputs a negative year into isValid().
	 */
	@Test
	public void isValidTest2() {
		Date test = new Date(1, 1, -1);
		boolean result = test.isValid();
		assertEquals(result, false);
	}
	
	/**
	 * JUnit test that inputs a negative month into isValid().
	 */
	@Test
	public void isValidTest3() {
		Date test = new Date(-1, 1, 2020);
		boolean result = test.isValid();
		assertEquals(result, false);
	}
	
	/**
	 * JUnit test that inputs a large month into isValid().
	 */
	@Test
	public void isValidTest4() {
		Date test = new Date(13, 1, 2020);
		boolean result = test.isValid();
		assertEquals(result, false);
	}
	
	/**
	 * JUnit test that test all valid months for isValid().
	 */
	@Test
	public void isValidTest5() {
		Date test;
		boolean result;
		for (int i = 1; i < 13; i++) {
			test = new Date(i, 1, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
	}
	
	/**
	 * JUnit test that tests valid days for February.
	 */
	@Test
	public void isValidTest6() {
		Date test;
		boolean result;
		for (int i = 1; i < 29; i++) {
			test = new Date(2, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
	}
	
	/**
	 * JUnit test that inputs invalid days for February.
	 */
	@Test
	public void isValidTest7() {
		Date test = new Date(2, 30, 2020);
		boolean result = test.isValid();
		assertEquals(result, false);
	}
	
	/**
	 * JUnit test that inputs a negative day into isValid().
	 */
	@Test
	public void isValidTest8() {
		Date test = new Date(1, -1, 2020);
		boolean result = test.isValid();
		assertEquals(result, false);
	}
	
	/**
	 * JUnit test that inputs a large day into isValid().
	 */
	@Test
	public void isValidTest9() {
		Date test = new Date(1, 32, 2020);
		boolean result = test.isValid();
		assertEquals(result, false);
	}
	
	/**
	 * JUnit test that tests days for months with 30 days.
	 */
	@Test
	public void isValidTest10() {
		Date test;
		boolean result;
		
		// month of April
		for (int i = 1; i < 30; i++) {
			test = new Date(4, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of June
		for (int i = 1; i < 30; i++) {
			test = new Date(6, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of September
		for (int i = 1; i < 30; i++) {
			test = new Date(9, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of November
		for (int i = 1; i < 30; i++) {
			test = new Date(11, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
	}
	
	/**
	 * JUnit test that tests days for months with 31 days.
	 */
	@Test
	public void isValidTest11() {
		Date test;
		boolean result;
		
		// month of January
		for (int i = 1; i < 31; i++) {
			test = new Date(1, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of March
		for (int i = 1; i < 31; i++) {
			test = new Date(3, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of May
		for (int i = 1; i < 31; i++) {
			test = new Date(5, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of July
		for (int i = 1; i < 31; i++) {
			test = new Date(7, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of August
		for (int i = 1; i < 31; i++) {
			test = new Date(8, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of October
		for (int i = 1; i < 31; i++) {
			test = new Date(10, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
		
		// month of December
		for (int i = 1; i < 31; i++) {
			test = new Date(12, i, 2020);
			result = test.isValid();
			assertEquals(result, true);
		}
	}
	
	/**
	 * JUnit test that tests valid leap years.
	 */
	@Test
	public void isValidTest12() {
		Date test;
		boolean result;
		for (int i = 2004; i <= 2020; i += 4) {
			test = new Date(2, 29, i);
			result = test.isValid();
			assertEquals(result, true);
		}
	}
	
	/**
	 * JUnit test that tests invalid leap years.
	 */
	@Test
	public void isValidTest13() {
		Date test;
		boolean result;
		for (int i = 2003; i <= 2019; i += 4) {
			test = new Date(2, 29, i);
			result = test.isValid();
			assertEquals(result, false);
		}
	}
}
