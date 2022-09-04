package project2;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the user interface class that handles the transactions and displays the results on the console.
 */
public class TransactionManager {
	
	/**
	 * Print statements for the result of opening an account.
	 * @param result of the account database call
	 */
	private void printOpen(boolean result) {
		if (result == true) {
			System.out.println("Account opened and added to the database.");
		} else {
			System.out.println("Account is already in the database.");
		}
	}
	
	/**
	 * Print statements for the result of closing an account.
	 * @param result of the account database call
	 */
	private void printClose(boolean result) {
		if (result == true) {
			System.out.println("Account closed and removed from the database.");
		} else {
			System.out.println("Account does not exist.");
		}
	}

	/**
	 * Print statements for the result of depositing money from an account.
	 * @param result of the account database call
	 * @param amount of money to deposit
	 */
	private void printDeposit(boolean result, double amount) {
		DecimalFormat currency = new DecimalFormat("0.0");
		//DecimalFormat currency = new DecimalFormat("0.0");
		
		if (result == true) {
			System.out.println(currency.format(amount) + " deposited to account.");
		} else {
			System.out.println("Account does not exist.");
		}
	}
	
	/**
	 * Print statements for the result of withdrawing money from an account.
	 * @param result of the account database call
	 * @param amount of money to withdraw
	 */
	private void printWithdraw(int result, double amount) {
		DecimalFormat currency = new DecimalFormat("0.00");
		
		if (result == 0) {
			System.out.println(currency.format(amount) + " withdrawn from account.");
		} 
		else if (result == 1) {
			System.out.println("Insufficient funds.");
		} 
		else if (result == -1) {
			System.out.println("Account does not exist.");
		} else {
			System.out.println("Input data type mismatch.");
		}
	}
	
	/**
	 * Converts a string representation of a date into a date object.
	 * Does not perform exception handling.
	 * @param date in the form of a string
	 * @return date object with values from string input
	 */
	private Date stringToDate(String date) {
		String[] elements = date.split("/");
		return new Date(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
	}
	
	/**
	 * Creates a Checking account given the string input.
	 * @param input from the command line
	 * @return Checking account with data from input, null on exception
	 */
	private Checking createChecking(String input) {
		String[] elements = input.split(" ");
		
		String first_name = elements[1];
		String last_name = elements[2];
		double balance = 0.0;
		Date date = new Date(1, 1, 2000);
		boolean directDeposit = false;
		
		if (elements.length == 3) { // C commands
			return new Checking(first_name, last_name, balance, date, directDeposit);
		}
		
		// set up balance
		try {
			balance = Double.parseDouble(elements[3]);
		} 
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		if (elements.length == 4) { // D & W commands
			return new Checking(first_name, last_name, balance, date, directDeposit);
		} 
		
		// set up date
		try {
			date = stringToDate(elements[4]);
		}
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		
		if (date.isValid() == false) {
			System.out.println(date.toString() + " is not a valid date!");
			return null;
		}
		if (elements.length == 5) { // O commands
			return new Checking(first_name, last_name, balance, date, directDeposit);
		}
		
		// set up directDeposit
		try {
			directDeposit = Boolean.parseBoolean(elements[5]);
		}
		catch (InputMismatchException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		
		if (directDeposit == false) { // parse boolean doesn't catch false strings
			if (!elements[5].toLowerCase().equals("false")) {
				System.out.println("Input data type mismatch.");
				return null;
			}
		}
		
		return new Checking(first_name, last_name, balance, date, directDeposit);
	}
	
	/**
	 * Creates a Savings account given the string input.
	 * @param input from the command line
	 * @return Savings account with data from input, null on exception
	 */
	private Savings createSavings(String input) {
		String[] elements = input.split(" ");
		
		String first_name = elements[1];
		String last_name = elements[2];
		double balance = 0.0;
		Date date = new Date(1, 1, 2000);
		boolean isLoyal = false;
		
		if (elements.length == 3) { // C commands
			return new Savings(first_name, last_name, balance, date, isLoyal);
		}
		
		// set up balance
		try {
			balance = Double.parseDouble(elements[3]);
		} 
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		if (elements.length == 4) { // D & W commands
			return new Savings(first_name, last_name, balance, date, isLoyal);
		} 
		
		// set up date
		try {
			date = stringToDate(elements[4]);
		}
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		
		if (date.isValid() == false) {
			System.out.println(date.toString() + " is not a valid date!");
			return null;
		}
		if (elements.length == 5) { // O commands
			return new Savings(first_name, last_name, balance, date, isLoyal);
		}
		
		// set up isLoyal
		try {
			isLoyal = Boolean.parseBoolean(elements[5]);
		}
		catch (InputMismatchException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		
		if (isLoyal == false) { // parse boolean doesn't catch false strings
			if (!elements[5].toLowerCase().equals("false")) {
				System.out.println("Input data type mismatch.");
				return null;
			}
		}
		
		return new Savings(first_name, last_name, balance, date, isLoyal);
	}
	
	/**
	 * Creates a MoneyMarket account given the string input.
	 * @param input from the command line
	 * @return MoneyMarket account with data from input, null on exception
	 */
	private MoneyMarket createMoneyMarket(String input) {
		String[] elements = input.split(" ");
		
		String first_name = elements[1];
		String last_name = elements[2];
		double balance = 0.0;
		Date date = new Date(1, 1, 2000);
		int withdrawals = 0;
		
		if (elements.length == 3) { // C commands
			return new MoneyMarket(first_name, last_name, balance, date, withdrawals);
		}
		
		// set up balance
		try {
			balance = Double.parseDouble(elements[3]);
		} 
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		if (elements.length == 4) { // D & W commands
			return new MoneyMarket(first_name, last_name, balance, date, withdrawals);
		} 
		
		// set up date
		try {
			date = stringToDate(elements[4]);
		}
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		
		if (date.isValid() == false) {
			System.out.println(date.toString() + " is not a valid date!");
			return null;
		}
		if (elements.length == 5) { // O commands
			return new MoneyMarket(first_name, last_name, balance, date, withdrawals);
		}
		
		// set up withdrawals
		try {
			withdrawals = Integer.parseInt(elements[5]);
		}
		catch (NumberFormatException e) {
			System.out.println("Input data type mismatch.");
			return null;
		}
		return new MoneyMarket(first_name, last_name, balance, date, withdrawals);
	}
	
	/**
	 * Takes the String representation of the command line input and creates a double of the amount stated in the input.
	 * @param input from the command line
	 * @return double of the amount stated from the input, -1 if exception occurred
	 */
	private double getAmount(String input) {
		String[] elements = input.split(" ");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(elements[3]);
		}
		catch (NumberFormatException e) {
			//System.out.println("Input data type mismatch.");
			return -1;
		}
		return amount;
	}
	
	/**
	 * Method in charge of conduction I/O.
	 */
	public void run() {
		Scanner scn = new Scanner(System.in).useDelimiter("\\s+"); // to get input for command and data
		AccountDatabase database = new AccountDatabase();
		String input = new String(); // string to store console inputs
		boolean running = true; // boolean for command loop
		
		System.out.println("Transaction processing starts.....");
		while (running) { // command loop
			String command;
			boolean result = false;
			int withdrawal_result = -2;
			
			try {
				input = scn.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println("InputMismatchException");
				continue;
			}
			
			if (input.length() > 1) {
				command = input.substring(0, 2);
			} else {
				command = input.substring(0, 1);
			}
			
			command = command.trim();
			
			// open commands
			if (command.equals("OC")) {
				Checking acc = createChecking(input);
				if (acc == null) {
					continue;
				} else {
					result = database.add(acc);
				}
				printOpen(result);
			}
			else if (command.equals("OS")) {
				Savings acc = createSavings(input);
				if (acc == null) {
					continue;
				} else {
					result = database.add(acc);
				}
				printOpen(result);
			} 
			else if (command.equals("OM")) {
				MoneyMarket acc = createMoneyMarket(input);
				if (acc == null) {
					continue;
				} else {
					result = database.add(acc);
				}
				printOpen(result);
			}
			
			// close commands
			else if (command.equals("CC")) {
				Checking acc = createChecking(input);
				if (acc == null) {
					continue;
				} else {
					result = database.remove(acc);
				}
				printClose(result);
			}
			else if (command.equals("CS")) {
				Savings acc = createSavings(input);
				if (acc == null) {
					continue;
				} else {
					result = database.remove(acc);
				}
				printClose(result);
			} 
			else if (command.equals("CM")) {
				MoneyMarket acc = createMoneyMarket(input);
				if (acc == null) {
					continue;
				} else {
					result = database.remove(acc);
				}
				printClose(result);
			}
			
			// deposit commands
			else if (command.equals("DC")) {
				Checking acc = createChecking(input);
				double amount = getAmount(input);
				
				if (acc == null || amount == -1) {
					continue;
				} else {
					result = database.deposit(acc, amount);
				}
				printDeposit(result, amount);
			}
			else if (command.equals("DS")) {
				Savings acc = createSavings(input);
				double amount = getAmount(input);
				
				if (acc == null || amount == -1) {
					continue;
				} else {
					result = database.deposit(acc, amount);
				}
				printDeposit(result, amount);
			} 
			else if (command.equals("DM")) {
				MoneyMarket acc = createMoneyMarket(input);
				double amount = getAmount(input);
				
				if (acc == null || amount == -1) {
					continue;
				} else {
					result = database.deposit(acc, amount);
				}
				printDeposit(result, amount);
			}
			
			// withdrawal commands
			else if (command.equals("WC")) {
				Checking acc = createChecking(input);
				double amount = getAmount(input);
				
				if (acc == null || amount == -1) {
					continue;
				} else {
					withdrawal_result = database.withdrawal(acc, amount);
				}
				printWithdraw(withdrawal_result, amount);
			}
			else if (command.equals("WS")) {
				Savings acc = createSavings(input);
				double amount = getAmount(input);
				
				if (acc == null || amount == -1) {
					continue;
				} else {
					withdrawal_result = database.withdrawal(acc, amount);
				}
				printWithdraw(withdrawal_result, amount);
			} 
			else if (command.equals("WM")) {
				MoneyMarket acc = createMoneyMarket(input);
				double amount = getAmount(input);
				
				if (acc == null || amount == -1) {
					continue;
				} else {
					withdrawal_result = database.withdrawal(acc, amount);
				}
				printWithdraw(withdrawal_result, amount);
			}
			
			// print commands
			else if (command.contains("PA")) {
				database.printAccounts();
			}
			else if (command.contains("PD")) {
				database.printByDateOpen();
			} 
			else if (command.contains("PN")) {
				database.printByLastName();
			}
			else if (command.equals("Q")) { // quit
				System.out.println("Transaction processing completed.");
				running = false;
			} else {
				if (input.length() > 1) {
					command = input.substring(0, 2);
				}
				command.trim();
				System.out.println("Command '" + command + "' not supported!");
			}
		}
		scn.close();
	}
}
