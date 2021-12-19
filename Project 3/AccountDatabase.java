package project3;
import java.text.DecimalFormat;

/**
 * This is an array based container class to store the different account instances of Checking, Savings or MoneyMarket.
 * Class contains method to add, remove, and print accounts along with its helper methods.
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;

    /**
     * Constructor for AccountDatabase.
     * Creates a new array of size 2 of accounts.
     */
    public AccountDatabase() {
        accounts = new Account[2];
    }

    /**
     * Searches the accounts array to find the desired account.
     * @param account to find in the database
     * @return index of the account if found, -1 if account is not in the database
     */
    private int find(Account account) {
    	for (int i = 0; i < size; i++) {
    		if (account.equals(accounts[i]) == true) {
				return i;
			}
    	}
    	
        return -1;
    }

    /**
     * Helper method to grow the number of accounts.
     */
    private void grow() {
        Account[] newAccounts = new Account[size * 2];

        for (int i = 0; i < size; i++) {
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    }

    /**
     * Adds an account to the accounts array.
     * @param account to add to the database
     * @return false if account exists in the database
     */
    public boolean add(Account account) {
        if (this.size == accounts.length) {
            grow();
        }
        if (find(account) == -1) { // account does not exist, can add new account
            accounts[size] = account;
            this.size++;
            return true;
        }
        
        return false;
    }

    /**
     * Removes the desired account from the accounts array if it exists.
     * @param account to remove from the database
     * @return false if account doesn't exist in the database
     */
    public boolean remove(Account account) {
        int indexOf = find(account);
        if (indexOf == -1) {
            return false;
        }
        if (indexOf != size) {
            accounts[indexOf] = accounts[size - 1];	// swap index and last item
        }

        accounts[size - 1] = null;
        this.size--;

        return true;
    }

    /**
     * Deposits money into the desired account.
     * @param account to deposit money into
     * @param amount of money to deposit
     * @return false if account doesn't exist
     */
    public boolean deposit(Account account, double amount) {
        if (find(account) == -1) {
            return false;
        }
        
        if(find(account) != -1){//account found
        
            int indexOf = find(account);
            accounts[indexOf].credit(amount);
        
        }

        return true;
    }

    /**
     * Withdraws money from the desired account.
     * @param account to withdrawal money from
     * @param amount of money to withdrawal from the account
     * @return 0: withdrawal successful, 1: insufficient funds, -1 account
     * doesn't exist
     */
    public int withdrawal(Account account, double amount) {
        if (find(account) == -1) {
            return -1;
        }

       int indexOf = find(account);
         
       if (indexOf != -1) {
    	   if (accounts[indexOf].getBalance() < amount) { // insufficient funds
    		   return 1;
    	   }
    	   accounts[indexOf].incW();
    	   accounts[indexOf].debit(amount);
   		}
        
        return 0;
    }

    /**
     * Helper method to sorts the accounts in ascending order by date open. 
     * Uses selection sort to sort the array.
     */
    private void sortByDateOpen() {
    	for (int i = 0; i < size - 1; i++) {
            int min = i;

            for (int j = i + 1; j < size; j++) {
                if (accounts[j].getDate().compareTo(accounts[min].getDate()) == -1) { // date j is less than min date
                    min = j;
                }
                
                // dates are equal so compare names
                if (accounts[j].getDate().compareTo(accounts[min].getDate()) == 0) {
                	if (accounts[j].getLastName().compareTo(accounts[min].getLastName()) == 1) {
                		min = j;
                	}
                	
                	if (accounts[j].getLastName().compareTo(accounts[min].getLastName()) == 0) {
                		if (accounts[j].getFirstName().compareTo(accounts[min].getFirstName()) == 1) {
                    		min = j;
                    	}
                	}
                }
            }

            Account temp = accounts[min];
            accounts[min] = accounts[i];
            accounts[i] = temp;
        }
    }

    /**
     * Helper method to sorts the accounts in ascending order by last name.
     * Uses selection sort to sort the array.
     */
    private void sortByLastName() {
    	for (int i = 0; i < size - 1; i++) {
            int min = i;

            for (int j = i + 1; j < size; j++) {
            	if (accounts[j].getLastName().compareTo(accounts[min].getLastName()) < 0) {
            		min = j;
            	}
            	
            	if (accounts[j].getLastName().compareTo(accounts[min].getLastName()) == 0) {
            		if (accounts[j].getFirstName().compareTo(accounts[min].getFirstName()) > 0) {
                		min = j;
                	}
            	}
            }

            Account temp = accounts[min];
            accounts[min] = accounts[i];
            accounts[i] = temp;
        }
    }

    /**
     * Method calculates the monthly interests and fees and prints the account statements by date open.
     * This method will change the balance of the accounts.
     */
    public String[] printByDateOpen() {
        sortByDateOpen();
        DecimalFormat decimalFormat = new DecimalFormat("0,000.00");
        DecimalFormat currency = new DecimalFormat("0.00");

        double intrest = 0;
        double fee = 0;
        double balance = 0;
        double newB = 0;
        String acc;
        String typeAcc;
        int numW = 0;
        
        String print[] = new String[size + 2];
        
        if (size == 0) {
            print[0] = "Database is empty.";
            return print;
        }
        
        // System.out.println();
        print[0] = "--Printing statements by date opened--";
        int i = 1;
        for (int x = 0; x < size; x++) {
            typeAcc = "";
            acc = "";
            
            if (accounts[x] instanceof Checking) {
                acc = "Checking";
                if (accounts[x].isDd()) {
                    typeAcc = "*direct deposit account*";
                }
                intrest = accounts[x].monthlyInterest() * accounts[x].getBalance();
                fee = accounts[x].monthlyFee(accounts[x].getBalance());
                balance = accounts[x].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[x] instanceof MoneyMarket) {
                acc = "Money Market";
                numW = accounts[x].getW();
                if (numW == 1) {
                	typeAcc = typeAcc.concat("*" + numW + " withdrawal*");
                } else {
                	typeAcc = typeAcc.concat("*" + numW + " withdrawals*");
                }

                intrest = accounts[x].monthlyInterest() * accounts[x].getBalance();
                fee = accounts[x].monthlyFee(accounts[x].getBalance());
                balance = accounts[x].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[x] instanceof Savings) {
                acc = "Savings";
                if (accounts[x].isLy()) {
                    typeAcc = "*special Savings account*";
                }
                intrest = accounts[x].monthlyInterest() * accounts[x].getBalance();
                fee = accounts[x].monthlyFee(accounts[x].getBalance());
                balance = accounts[x].getBalance();
                newB = balance + intrest - fee;
            }

            // print statements
            //System.out.println("");
            String delim = "&";
            
            //System.out.println("*" + acc + "*" + accounts[x].toString() + typeAcc);
            String str = "*";
            str = str.concat(acc);
            str = str.concat("*");
            str = str.concat(accounts[x].toString());
            str = str.concat(typeAcc);
            str = str.concat(delim);
            
            //System.out.println("-interest: $ " + currency.format(intrest));
            str = str.concat("-interest: $ ");
            str = str.concat(currency.format(intrest));
            str = str.concat(delim);
            
            //System.out.println("-fee: $ " + currency.format(fee));
            str = str.concat("-fee: $ ");
            str = str.concat(currency.format(fee));
            str = str.concat(delim);
            
            if (newB >= 1000) {
            	//System.out.println("-new balance: $ " + decimalFormat.format(newB));
            	str = str.concat("-new balance: $ ");
            	str = str.concat(decimalFormat.format(newB));
            } else {
            	//System.out.println("-new balance: $ " + currency.format(newB));
            	str = str.concat("-new balance: $ ");
            	str = str.concat(currency.format(newB));
            }
            str = str.concat(delim);
            
            // modify accounts
            accounts[x].debit(balance);
            accounts[x].credit(newB);
            
            // modify string array
            print[i] = str;
            i++;
        }
        
        //System.out.println("--end of printing--");
        print[i] = "--end of printing--";
        return print;
    }

    /**
     * Method calculates the monthly interests and fees and prints the accounts by last name.
     * This method will change the balance of the accounts.
     */
    public String[] printByLastName() {
        sortByLastName();
        DecimalFormat decimalFormat = new DecimalFormat("0,000.00");
        DecimalFormat currency = new DecimalFormat("0.00");
        
        int numW = 0;
        double intrest = 0;
        double fee = 0;
        double balance = 0;
        double newB = 0;
        String acc;
        String typeAcc;

        String print[] = new String[size + 2];
        
        if (size == 0) {
            print[0] = "Database is empty.";
            return print;
        }
        
        // System.out.println();
        print[0] = "--Printing statements by last name--";
        int i = 1;
        for (int x = 0; x < size; x++) {
            typeAcc = "";
            acc = "";

            if (accounts[x] instanceof Checking) {
                acc = "Checking";
                if (accounts[x].isDd()) {
                    typeAcc = "*direct deposit account*";
                }
                intrest = accounts[x].monthlyInterest() * accounts[x].getBalance();
                fee = accounts[x].monthlyFee(accounts[x].getBalance());
                balance = accounts[x].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[x] instanceof MoneyMarket) {
                acc = "Money Market";
                numW = accounts[x].getW();
                if (numW == 1) {
                	typeAcc = typeAcc.concat("*" + numW + " withdrawal*");
                } else {
                	typeAcc = typeAcc.concat("*" + numW + " withdrawals*");
                }

                intrest = accounts[x].monthlyInterest() * accounts[x].getBalance();
                fee = accounts[x].monthlyFee(accounts[x].getBalance());
                balance = accounts[x].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[x] instanceof Savings) {
                acc = "Savings";
                if (accounts[x].isLy()) {
                    typeAcc = "*special Savings account*";
                }
                intrest = accounts[x].monthlyInterest() * accounts[x].getBalance();
                fee = accounts[x].monthlyFee(accounts[x].getBalance());
                balance = accounts[x].getBalance();
                newB = balance + intrest - fee;
            }

            // print statements
            //System.out.println("");
            String delim = "&";
            
            //System.out.println("*" + acc + "*" + accounts[x].toString() + typeAcc);
            String str = "*";
            str = str.concat(acc);
            str = str.concat("*");
            str = str.concat(accounts[x].toString());
            str = str.concat(typeAcc);
            str = str.concat(delim);
            
            //System.out.println("-interest: $ " + currency.format(intrest));
            str = str.concat("-interest: $ ");
            str = str.concat(currency.format(intrest));
            str = str.concat(delim);
            
            //System.out.println("-fee: $ " + currency.format(fee));
            str = str.concat("-fee: $ ");
            str = str.concat(currency.format(fee));
            str = str.concat(delim);
            
            if (newB >= 1000) {
            	//System.out.println("-new balance: $ " + decimalFormat.format(newB));
            	str = str.concat("-new balance: $ ");
            	str = str.concat(decimalFormat.format(newB));
            } else {
            	//System.out.println("-new balance: $ " + currency.format(newB));
            	str = str.concat("-new balance: $ ");
            	str = str.concat(currency.format(newB));
            }
            str = str.concat(delim);
            
            // modify accounts
            accounts[x].debit(balance);
            accounts[x].credit(newB);
            
            // modify string array
            print[i] = str;
            i++;
        }
        //System.out.println("--end of printing--");
        print[i] = "--end of printing--";
        return print;
    }

    /**
     * Method prints all accounts in database.
     * This method will not change the balance of the accounts.
     */
    public String[] printAccounts() {
        String acc;
        String typeAcc;
        int numW = 0;

        String print[] = new String[size + 2];
        
        if (size == 0) {
            print[0] = "Database is empty.";
            return print;
        }
        
        print[0] = "--Listing accounts in the database--";
        int i = 1;

        for (int x = 0; x < size; x++) {
            typeAcc = "";
            acc = "";

            if (accounts[x] instanceof Checking) {
                acc = "Checking";
                if (accounts[x].isDd()) {
                    typeAcc = "*direct deposit account*";
                }
            }
            if (accounts[x] instanceof MoneyMarket) {
                acc = "Money Market";
                numW = accounts[x].getW();
                if (numW == 1) {
                	typeAcc = typeAcc.concat("*" + numW + " withdrawal*");
                } else {
                	typeAcc = typeAcc.concat("*" + numW + " withdrawals*");
                }
            }
            if (accounts[x] instanceof Savings) {
                acc = "Savings";
                if (accounts[x].isLy()) {
                    typeAcc = "*special Savings account*";
                }
            }
            
            //System.out.println("*" + acc + "*" + accounts[x].toString() + typeAcc);
            String str = "*";
            str = str.concat(acc);
            str = str.concat("*");
            str = str.concat(accounts[x].toString());
            str = str.concat(typeAcc);
            print[i] = str;
            i++;
        }
        
        //System.out.println("--end of listing--");
        print[i] = "--end of listing--";
        return print;
    }
}
