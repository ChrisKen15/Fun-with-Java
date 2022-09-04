package project3;

/**
 * This is a sub class of Account and creates an Account of type Checking.
 */
public class Checking extends Account {
	private boolean directDeposit;
	
	/**
     * Constructor for Checking account.
     * @param first_name of the account holder
     * @param last_name of the account holder
     * @param balance the account starts with
     * @param date the account was opened
     * @param directDeposit availability on the account
     */    
	public Checking(String first_name, String last_name, double balance, Date date, boolean directDeposit) {
		super(first_name, last_name, balance, date);
		this.directDeposit = directDeposit;
	}

    /**
     * Gets the interest rate of the Checking account.
     * @return value of monthly interest
     */
	@Override
	public double monthlyInterest() {
		double interest = 0.05 / 100.0;
		return interest / 12;
	}
       
    /**
     * Calculates the monthly fee of the Checking account.
     * @param balance of the account
     * @return 0 if balance is greater than $1500 or if account is direct deposit. Else returns a fee of 25.  
     */    
	@Override
	public double monthlyFee(double balance) {
     	if(balance >= 1500 || directDeposit)
            return 0;
     	else{
             return 25;   
     	}
	}
        
    /**
     * Method will return true if account is direct deposit and false if not.
     * @return directDeposit
     */  
     @Override
     public boolean isDd(){
         return this.directDeposit;
     }
     
     /**
      * Compares the account instance and account profile names to determine whether accounts are equal.
      * @param account to compare to
      * @return true if accounts are equals, false otherwise
      */
     @Override
     public boolean equals(Account account) {
    	 if (account instanceof Checking) {
    		 if (super.stringEquals(super.getName(), account.getName()) == true) {
    			 return true;
    		 }
    	 }
    	 return false;
     }
    
}
