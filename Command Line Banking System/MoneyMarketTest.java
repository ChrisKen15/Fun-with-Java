package project2;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit testing class for the MoneyMarket class.
 */
public class MoneyMarketTest {
    
    /**
     * JUnit Test of monthlyInterest method. Interest Rate remains the same.
     */
    @Test
    public void testMonthlyInterest() {
        double balance = 1200;
        int withD = 4;
        Date d = new Date(8,7,2012);
        MoneyMarket m = new MoneyMarket("" ,"" , balance , d, withD);
        double expResult = 0.0005 ;
        double result = m.monthlyInterest();
        assertEquals(expResult, result, 4.0E-5);
    }

    /**
     * JUnit Test of monthlyFee method for balance greater than 2500 and withdrawals less than 6.
     */
    @Test
    public void testMonthlyFee1() {
          
        double balance = 2700;
        int withD = 4;
        Date d = new Date(8,7,2012);
        MoneyMarket m = new MoneyMarket("" ,"" , balance , d, withD);
        double expResult = 0;
        double result = m.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
    }

    
    /**
     * JUnit Test of monthlyFee method for balance equals 2500 and withdrawals less than 6.
     */
    @Test
    public void testMonthlyFee2() {
          
        double balance = 2500;
        int withD = 4;
        Date d = new Date(8,7,2012);
        MoneyMarket m = new MoneyMarket("" ,"" , balance , d, withD);
        double expResult = 0;
        double result = m.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
    }
    
    
    /**
     * JUnit Test of monthlyFee method for balance less than 2500 and withdrawals less than 6.
     */
    @Test
    public void testMonthlyFee3() {
          
        double balance = 1800;
        int withD = 4;
        Date d = new Date(8,7,2012);
        MoneyMarket m = new MoneyMarket("" ,"" , balance , d, withD);
        double expResult = 12;
        double result = m.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
    }
    
    
    /**
     * JUnit Test of monthlyFee method for balance greater than 2500 and withdrawals greater than 6.
     */
    @Test
    public void testMonthlyFee4() {
          
        double balance = 2700;
        int withD = 6;
        Date d = new Date(8,7,2012);
        MoneyMarket m = new MoneyMarket("" ,"" , balance , d, withD);
        double expResult = 12;
        double result = m.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
    }

   /**
     * JUnit Test of getW method. Returns number of withdrawals.
     */
    @Test
    public void testGetW() {
        double balance = 2700;
        int withD = 6;
        Date d = new Date(8,7,2012);
        MoneyMarket m = new MoneyMarket("" ,"" , balance , d, withD);
        int expResult = 6;
        int result = (int) m.getW();
        assertEquals(expResult, result, 0.0);
    }

    
}
