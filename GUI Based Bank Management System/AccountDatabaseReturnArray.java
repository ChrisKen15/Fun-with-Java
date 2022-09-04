package project3;
import java.text.DecimalFormat;

/**
 * This is an array based container class to store the different account instances of Checking, Savings or MoneyMarket.
 * Class contains method to add, remove, and return accounts arrays to append to text area along with its helper methods.
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;
 //   Controller c = new Controller();
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
     * Method calculates the monthly interests and fees and returns an array of accounts.
     * Every fourth index is a new account. The four indexes represent different
     * statements to append to the text area.
     * Accounts will be appended to the text area by date open order.
     * This method will change the balance of the accounts.
     *
     * @return accountArray
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
        String accountArray[] = new String[size * 4];


        if (size == 0) {
            return accountArray;
        }

        System.out.println();
        System.out.println("--Printing statements by date opened--");

        for (int x = 0; x < size * 4; x = x+4) {
            int a = x;
            typeAcc = "";
            acc = "";
            if(x != 0 )a = x/4;
            if (accounts[a] instanceof Checking) {
                acc = "Checking";
                if (accounts[a].isDd()) {
                    typeAcc = "*direct deposit account*";
                }
                intrest = accounts[a].monthlyInterest() * accounts[a].getBalance();
                fee = accounts[a].monthlyFee(accounts[a].getBalance());
                balance = accounts[a].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[a] instanceof MoneyMarket) {
                acc = "Money Market";
                numW = accounts[a].getW();
                if (numW == 1) {
                    typeAcc = typeAcc.concat("*" + numW + " withdrawal*");
                } else {
                    typeAcc = typeAcc.concat("*" + numW + " withdrawals*");
                }

                intrest = accounts[a].monthlyInterest() * accounts[a].getBalance();
                fee = accounts[a].monthlyFee(accounts[a].getBalance());
                balance = accounts[a].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[a] instanceof Savings) {
                acc = "Savings";
                if (accounts[a].isLy()) {
                    typeAcc = "*special Savings account*";
                }
                intrest = accounts[a].monthlyInterest() * accounts[a].getBalance();
                fee = accounts[a].monthlyFee(accounts[a].getBalance());
                balance = accounts[a].getBalance();
                newB = balance + intrest - fee;
            }

            // print statements
            accountArray[x] = ("*" + acc + "*" + accounts[a].toString() + typeAcc);
            accountArray[x+1] = ("-interest: $ " + currency.format(intrest));
            accountArray[x+2] = ("-fee: $ " + currency.format(fee));


            if (newB >= 1000) {
                accountArray[x+3] = ("-new balance: $ " + decimalFormat.format(newB));
            } else {
                accountArray[x+3] = ("-new balance: $ " + currency.format(newB));
            }

            // modify accounts
            accounts[a].debit(balance);
            accounts[a].credit(newB);
        }
        return accountArray;
    }


    /**
     * Method calculates the monthly interests and fees and returns an array of accounts.
     * Every fourth index is a new account. The four indexes represent different
     * statements to append to the text area.
     * Accounts will be appended to the text area by last name order.
     * This method will change the balance of the accounts.
     *
     * @return accountArray
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
        String accountArray[] = new String[size * 4];


        if (size == 0) {
            return accountArray;
        }


        for (int x = 0; x < size * 4; x = x+4)  {
            int a = x;
            typeAcc = "";
            acc = "";

            if(x != 0 )a = x/4;
            if (accounts[a] instanceof Checking) {
                acc = "Checking";
                if (accounts[a].isDd()) {
                    typeAcc = "*direct deposit account*";
                }
                intrest = accounts[a].monthlyInterest() * accounts[a].getBalance();
                fee = accounts[a].monthlyFee(accounts[a].getBalance());
                balance = accounts[a].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[a] instanceof MoneyMarket) {
                acc = "Money Market";
                numW = accounts[a].getW();
                if (numW == 1) {
                    typeAcc = typeAcc.concat("*" + numW + " withdrawal*");
                } else {
                    typeAcc = typeAcc.concat("*" + numW + " withdrawals*");
                }

                intrest = accounts[a].monthlyInterest() * accounts[a].getBalance();
                fee = accounts[a].monthlyFee(accounts[a].getBalance());
                balance = accounts[a].getBalance();
                newB = balance + intrest - fee;
            }

            if (accounts[a] instanceof Savings) {
                acc = "Savings";
                if (accounts[a].isLy()) {
                    typeAcc = "*special Savings account*";
                }
                intrest = accounts[a].monthlyInterest() * accounts[a].getBalance();
                fee = accounts[a].monthlyFee(accounts[a].getBalance());
                balance = accounts[a].getBalance();
                newB = balance + intrest - fee;
            }
            accountArray[x] = ("*" + acc + "*" + accounts[a].toString() + typeAcc);
            accountArray[x+1] = ("-interest: $ " + currency.format(intrest));
            accountArray[x+2] = ("-fee: $ " + currency.format(fee));

            if (newB >= 1000) {
                accountArray[x+3] =("-new balance: $ " + decimalFormat.format(newB));
            } else {
                accountArray[x+3] =("-new balance: $ " + currency.format(newB));

            }

            // modify accounts
            accounts[a].debit(balance);
            accounts[a].credit(newB);
        }
        return accountArray;
    }

    /**
     * Method returns and array of accounts,
     * each index represents a different account
     * to be appended to the text area.
     *
     * @return accountArray
     */
    public String[] printAccounts() {
        String acc;
        String typeAcc;
        int numW = 0;
        String accountArray[] = new String[size];

        if (size == 0) {
            return accountArray;
        }


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
            accountArray[x] = ("*" + acc + "*" + accounts[x].toString() + typeAcc);
        }
        return accountArray;
    }

}
