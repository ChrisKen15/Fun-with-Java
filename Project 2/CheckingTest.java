package project2;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit testing class for the Checking class.
 */
public class CheckingTest {

    /**
     * JUnit Test of monthlyFee method for balance less than 2500 and withdrawals less than 6.
     * Interest for checkings will not change. 
     */
    @Test
    public void testMonthlyInterest() {
        double balance = 1200;
        boolean isDD = true;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 0.00004 ;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 4.0E-5);
        
    }

    /**
     * JUnit Test of monthlyFee method .
     * If balance less than 1500 and account is not direct deposit.
     */
    @Test
    public void testMonthlyFee1() {
       
        double balance = 1400;
        boolean isDD = false;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 25;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
       
    }
    
    /**
     * JUnit Test of monthlyFee method.
     * If balance equals 1500 and account is not direct deposit.
     */
    @Test
    public void testMonthlyFee2() {
       
       double balance = 1500;
        boolean isDD = false;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 0;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
       
    }
    
     /**
     * JUnit Test of monthlyFee method.
     * If balance greater than 1500 and account is not direct deposit.
     */
    @Test
    public void testMonthlyFee3() {
       
       double balance = 1600;
        boolean isDD = false;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 0;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
        
    }
    
     /**
     * JUnit Test of monthlyFee method, of class Checking.
     * If less than 1500 and account is direct deposit.
     */
    @Test
    public void testMonthlyFee4() {
        
         
        double balance = 1200;
        boolean isDD = true;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 0;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
       
    }
    
     /**
     * JUnit Test of monthlyFee method, of class Checking.
     * If balance is equal to 1500 and account is direct deposit.
     */
    @Test
    public void testMonthlyFee5() {
        
        double balance = 1500;
        boolean isDD = true;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 0;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * JUnit Test of monthlyFee method, of class Checking.
     * If balance is greater than 1500 and account is direct deposit.
     */
    @Test
    public void testMonthlyFee6() {
        
        double balance = 1800;
        boolean isDD = true;
        Date d = new Date(8,7,2012);
        Checking c = new Checking("" ,"" , balance , d, isDD);
        double expResult = 0;
        double result = c.monthlyFee(balance);
        assertEquals(expResult, result, 0.0);
    }
   
}
