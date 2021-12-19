package project3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	// fxml for main
	@FXML
	public Button FileOptionsButton, CommandsButton;
	
	@FXML
	public TextArea TextAreaPrint;
	
	// fxml for command
	@FXML
	public Button SubmitFromCommand;
	
	@FXML
	public RadioButton OpenAccRB, CloseAccRB, DepositRB, WithdrawAccRB, MoneyMarketRB, SavingRB, CheckingRB, DisplayByLName, DisplayByDate, DisplayAllAcc;
	
	@FXML
	public CheckBox SpecialSavingAccCB, DirectDepositCheckingsCB;
	
	@FXML
	public TextField FirstNameInput, LastNameInput, AmountInput, DateInput, NumWithDrawals;
	
	// fxml for file
	@FXML
	public Button SelectFileButton, SubmitFileButton, ExportFileButton;
	
	@FXML
	public TextField FileNameToExport, FileImportNameTxtF;
    
	@FXML
	public MenuItem PrintAllAccMenuItem, PrintByLastNameMenuItem, PrintByDateOpenMenuItem;
	
    // private variables for transaction management
    private AccountDatabase accountDatabase = new AccountDatabase();
    private String first_name;
    private String last_name;
    private String Amount;
    private String Date;
    private String Withdrawal;

    @FXML
    /**
     * Method to get data from commands window
     *
     * @param event
     * @throws Exception
     */
    public void getDataFromCommands(ActionEvent event) throws Exception {
    	boolean open = false, close = false, deposit = false, withdraw = false;
    	boolean check = false, save = false, market = false;
    	boolean Special = false, Direct = false;
    	
    	try {
    		open = OpenAccRB.isSelected();
    		close = CloseAccRB.isSelected();
    		deposit = DepositRB.isSelected();
    		withdraw = WithdrawAccRB.isSelected();
    		
    		check = CheckingRB.isSelected();
    		save = SavingRB.isSelected();
    		market = MoneyMarketRB.isSelected();
    		
    		Special = SpecialSavingAccCB.isSelected();
    		Direct = DirectDepositCheckingsCB.isSelected();
    		
    		first_name = FirstNameInput.getText();
            last_name = LastNameInput.getText();
    	}
    	catch(NumberFormatException e) {
    		TextAreaPrint.appendText("Input data type mismatch.\n");
    	}
    	catch(InputMismatchException e) {
    		TextAreaPrint.appendText("Input data type mismatch.\n");
    	}
    	
    	// checks validity of first name
    	if (first_name.isEmpty()) {
    		TextAreaPrint.appendText("Input data type mismatch.\n");
			return;
    	}
    	
    	// checks validity of last name
    	if (last_name.isEmpty()) {
    		TextAreaPrint.appendText("Input data type mismatch.\n");
			return;
    	}
    	
    	double balance = 0.0;
    	Date date = new Date(1, 1, 2000);
    	Account acc = null;
    	boolean result = false;
    	if (open == true) {
    		try {
    			Amount = AmountInput.getText();
    			balance = Double.parseDouble(Amount);
    		} 
    		catch (NumberFormatException e) {
    			TextAreaPrint.appendText("Input data type mismatch.\n");
    			return;
    		}
    		
    		try {
    			Date = DateInput.getText();
        		date = stringToDate(Date);
    		}
    		catch (NumberFormatException e) {
    			TextAreaPrint.appendText("Input data type mismatch.\n");
    			return;
    		}
    		
    		if (!date.isValid()) {
    			TextAreaPrint.appendText(Date);
    			TextAreaPrint.appendText(" is not a valid date!\n");
    			return;
    		}
    		
    		if (check == true) {
    			acc = new Checking(first_name, last_name, balance, date, Direct);
    		}
    		else if (save == true) {
    			acc = new Savings(first_name, last_name, balance, date, Special);
    		}
    		else if (market == true) {
    			int withdrawal = 0;
    			try {
    				Withdrawal = NumWithDrawals.getText();
        			withdrawal = Integer.parseInt(Withdrawal);
    			}
    			catch (NumberFormatException e) {
        			TextAreaPrint.appendText("Input data type mismatch.\n");
        			return;
        		}
    			acc = new MoneyMarket(first_name, last_name, balance, date, withdrawal);
    		}
    		else {
    			TextAreaPrint.appendText("No command was selected.\n");
    			return;
    		}
    		result = accountDatabase.add(acc);
    		printOpen(result);
    	} 
    	else if (close == true) {
    		if (check == true) {
    			acc = new Checking(first_name, last_name, balance, date, Direct);
    		}
    		else if (save == true) {
    			acc = new Savings(first_name, last_name, balance, date, Special);
    		}
    		else if (market == true) {
    			acc = new MoneyMarket(first_name, last_name, balance, date, 0);
    		}
    		else {
    			TextAreaPrint.appendText("No command was selected.\n");
    			return;
    		}
    		
    		result = accountDatabase.remove(acc);
			printClose(result);
    	}
    	else if (deposit == true) {
    		try {
    			Amount = AmountInput.getText();
    			balance = Double.parseDouble(Amount);
    		} 
    		catch (NumberFormatException e) {
    			TextAreaPrint.appendText("Input data type mismatch.\n");
    			return;
    		}
    		
    		if (check == true) {
    			acc = new Checking(first_name, last_name, balance, date, Direct);
    		}
    		else if (save == true) {
    			acc = new Savings(first_name, last_name, balance, date, Special);
    		}
    		else if (market == true) {
    			acc = new MoneyMarket(first_name, last_name, balance, date, 0);
    		}
    		else {
    			TextAreaPrint.appendText("No command was selected.\n");
    			return;
    		}
    		result = accountDatabase.deposit(acc, balance);
    		printDeposit(result, balance);
    	}
    	else if (withdraw == true) {
    		try {
    			Amount = AmountInput.getText();
    			balance = Double.parseDouble(Amount);
    		} 
    		catch (NumberFormatException e) {
    			TextAreaPrint.appendText("Input data type mismatch.\n");
    			return;
    		}
    		
    		if (check == true) {
    			acc = new Checking(first_name, last_name, balance, date, Direct);
    		}
    		else if (save == true) {
    			acc = new Savings(first_name, last_name, balance, date, Special);
    		}
    		else if (market == true) {
    			acc = new MoneyMarket(first_name, last_name, balance, date, 0);
    		}
    		else {
    			TextAreaPrint.appendText("No command was selected.\n");
    			return;
    		}
    		int r = accountDatabase.withdrawal(acc, balance);
    		printWithdraw(r, balance);
    	}
    	else {
    		TextAreaPrint.appendText("No command was selected.\n");
    		return;
    	}
    }
    
    /**
     * Method to input and submit a file
     * filtered by .txt extension.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void getFileInput(ActionEvent event) throws Exception {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Source File for the Import");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
		//write code to read from the file.
		
		Scanner sc = null;
		try {
			sc = new Scanner(sourceFile).useDelimiter("\\s+");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			TextAreaPrint.appendText("Error. File not found.\n");
			return;
		}
		
		String delim = ",";
		while (sc.hasNext())
		{
			Account acc;
			String input = sc.nextLine();
			//System.out.println(input);
			
			String inputs[] = input.split(delim);
			
			// get balance
			double balance = 0;
			try {
				balance = Double.parseDouble(inputs[3]);
			}
			catch (InputMismatchException e) {
				TextAreaPrint.appendText("Input data type mismatch.\n");
				break;
			}
			
			// get date
			Date date = null;
			try {
				date = stringToDate(inputs[4]);
			}
			catch (InputMismatchException e) {
				TextAreaPrint.appendText("Input data type mismatch.\n");
				break;
			}
			
			// determine type of account to open
			if (inputs[0].equals("C")) {
				boolean direct = false;
				try {
					direct = Boolean.parseBoolean(inputs[5]);
				}
				catch (InputMismatchException e) {
					TextAreaPrint.appendText("Input data type mismatch.\n");
					break;
				}
				
				if (direct == false) { // parse boolean doesn't catch false strings
					if (!inputs[5].toLowerCase().equals("false")) {
						TextAreaPrint.appendText("Input data type mismatch.\n");
						break;
					}
				}
				
				acc = new Checking(inputs[1], inputs[2], balance, date, direct);
				accountDatabase.add(acc);
				TextAreaPrint.appendText("Account opened and added to the database.\n");
			}
			else if (inputs[0].equals("S")) {
				boolean isLoyal = false;
				try {
					isLoyal = Boolean.parseBoolean(inputs[5]);
				}
				catch (InputMismatchException e) {
					TextAreaPrint.appendText("Input data type mismatch.\n");
					break;
				}
				
				if (isLoyal == false) { // parse boolean doesn't catch false strings
					if (!inputs[5].toLowerCase().equals("false")) {
						TextAreaPrint.appendText("Input data type mismatch.\n");
						break;
					}
				}
				
				acc = new Savings(inputs[1], inputs[2], balance, date, isLoyal);
				accountDatabase.add(acc);
				TextAreaPrint.appendText("Account opened and added to the database.\n");
			}
			else if (inputs[0].equals("M")) {
				int withdrawals = 0;
				try {
					withdrawals = Integer.parseInt(inputs[5]);
				}
				catch (NumberFormatException e) {
					TextAreaPrint.appendText("Input data type mismatch.\n");
					break;
				}
				
				acc = new MoneyMarket(inputs[1], inputs[2], balance, date, withdrawals);
				accountDatabase.add(acc);
				TextAreaPrint.appendText("Account opened and added to the database.\n");
			}
			else {
				TextAreaPrint.appendText("Command '");
				TextAreaPrint.appendText(inputs[0]);
				TextAreaPrint.appendText("' not supported!.\n");
			}
		}
		sc.close();
    }    
	
    /**
     * Method to disable specials savings check box or direct deposit check box
     * based on selection of account type.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void DisableCheckBox(ActionEvent event) throws Exception {
    	if (!SavingRB.isSelected() || !OpenAccRB.isSelected()) {//if savings is not selected than disable specials acc check box
            SpecialSavingAccCB.setSelected(false);
            SpecialSavingAccCB.setDisable(true);
        } else SpecialSavingAccCB.setDisable(false);

        if (!CheckingRB.isSelected() || !OpenAccRB.isSelected()) {//if checkings is not selected than disable direct deposit check boc
            DirectDepositCheckingsCB.setSelected(false);
            DirectDepositCheckingsCB.setDisable(true);
        } else DirectDepositCheckingsCB.setDisable(false);

        if (CloseAccRB.isSelected()) {//if and else if to disable textfields based on bank options
            AmountInput.setDisable(true);
            DateInput.setDisable(true);
        } else if (DepositRB.isSelected() || WithdrawAccRB.isSelected()) {
            AmountInput.setDisable(false);
            DateInput.setDisable(true);
        } else {
            AmountInput.setDisable(false);
            DateInput.setDisable(false);
        }
        if(!MoneyMarketRB.isSelected() || !OpenAccRB.isSelected()){
            NumWithDrawals.setDisable(true);
        }
        else NumWithDrawals.setDisable(false);
    }    

    /**
     * Prints accounts to target file.
     */
    @FXML
    public void exportFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
		//write code to write to the file.
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(targetFile);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (pw != null) {
			String[] printData = accountDatabase.printAccounts(); 
			
			for (int i = 0; i < printData.length; i++) {
				pw.write(printData[i]);
				pw.write("\n");
			}
		}
		
		pw.close();
    }
    
    /**
     * Prints the account database by date open when the PrintByLastNameMenuItem has an event.
     * @param event
     */
    @FXML
    public void PrintAccByLastName(ActionEvent event) {
    	String acc[] = accountDatabase.printByLastName();
        
        if (acc[0].equals("Database is empty.")) {
        	TextAreaPrint.appendText("Database is empty.\n");
        	return;
        }
        
        String delim = "&";
        for (int i = 0; i < acc.length; i++) {
        	TextAreaPrint.appendText("\n");
        	if (i == acc.length - 1) {
        		TextAreaPrint.appendText(acc[i]);
    			TextAreaPrint.appendText("\n");
        	}
        	else {
        		String elements[] = acc[i].split(delim);
        		for (int j = 0; j < elements.length; j++) {
        			TextAreaPrint.appendText(elements[j]);
        			TextAreaPrint.appendText("\n");
        		}
        	}
        }
    }
    
    /**
     * Prints the account database by date open when the PrintByDateOpenMenuItem has an event.
     * @param event
     */
    @FXML
    public void PrintAccByDateOpen(ActionEvent event) {
    	String acc[] = accountDatabase.printByDateOpen();
        
        if (acc[0].equals("Database is empty.")) {
        	TextAreaPrint.appendText("Database is empty.\n");
        	return;
        }
        
        String delim = "&";
        for (int i = 0; i < acc.length; i++) {
        	TextAreaPrint.appendText("\n");
        	if (i == acc.length - 1) {
        		TextAreaPrint.appendText(acc[i]);
    			TextAreaPrint.appendText("\n");
        	}
        	else {
        		String elements[] = acc[i].split(delim);
        		for (int j = 0; j < elements.length; j++) {
        			TextAreaPrint.appendText(elements[j]);
        			TextAreaPrint.appendText("\n");
        		}
        	}
        }
    }
    
    /**
     * Method to append to text area.
     * Method calls return method in AccountDatabase class
     * and receives and array of accounts to append.
     */
    @FXML
    public void DisplayAccounts() {
    	String acc[]= accountDatabase.printAccounts();
        
        if (acc[0].equals("Database is empty.")) {
        	TextAreaPrint.appendText("Database is empty.\n");
        	return;
        }
        
        String delim = "&";
        for (int i = 0; i < acc.length; i++) {
        	if (i == acc.length - 1) {
        		TextAreaPrint.appendText(acc[i]);
    			TextAreaPrint.appendText("\n");
        	}
        	else {
        		String elements[] = acc[i].split(delim);
        		for (int j = 0; j < elements.length; j++) {
        			TextAreaPrint.appendText(elements[j]);
        			TextAreaPrint.appendText("\n");
        		}
        	}
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
	 * Print statements for the result of opening an account.
	 * @param result of the account database call
	 */
	private void printOpen(boolean result) {
		if (result == true) {
			TextAreaPrint.appendText("Account opened and added to the database.");
		} else {
			TextAreaPrint.appendText("Account is already in the database.");
		}
		TextAreaPrint.appendText("\n");
	}
	
	/**
	 * Print statements for the result of closing an account.
	 * @param result of the account database call
	 */
	private void printClose(boolean result) {
		if (result == true) {
			TextAreaPrint.appendText("Account closed and removed from the database.");
		} else {
			TextAreaPrint.appendText("Account does not exist.");
		}
		TextAreaPrint.appendText("\n");
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
			TextAreaPrint.appendText(currency.format(amount) + " deposited to account.");
		} else {
			TextAreaPrint.appendText("Account does not exist.");
		}
		TextAreaPrint.appendText("\n");
	}
	
	/**
	 * Print statements for the result of withdrawing money from an account.
	 * @param result of the account database call
	 * @param amount of money to withdraw
	 */
	private void printWithdraw(int result, double amount) {
		DecimalFormat currency = new DecimalFormat("0.00");
		
		if (result == 0) {
			TextAreaPrint.appendText(currency.format(amount) + " withdrawn from account.");
		} 
		else if (result == 1) {
			TextAreaPrint.appendText("Insufficient funds.");
		} 
		else if (result == -1) {
			TextAreaPrint.appendText("Account does not exist.");
		} else {
			TextAreaPrint.appendText("Input data type mismatch.");
		}
		TextAreaPrint.appendText("\n");
	}
	
    /**
     * Called when view loads
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    
}
