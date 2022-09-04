package project1;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
User interface class that handles the input commands,
output data, and messages.
 */
public class Shopping {
	
	/**
	Creates a new GroceryItem.
	The method assumes the string input is formatted.
	@param input string from user
 	@return GroceryItem object
	 */
	private GroceryItem makeItem(String input) {
		String[] elements = input.split(" ");
		double price = 0.0;
		boolean taxable = false;
		
		price = Double.parseDouble(elements[2]);
		
		if (elements[3].equals("true")) {
			taxable = true;
		}

		return new GroceryItem(elements[1], price, taxable);
	}
	
	/**
	Runs the output messages for the checkout command,
	if there are grocery items in the shopping bag.
	Prints all items in the shopping bag and then empties the shopping bag.
	This method handles formating with total price and tax.
	@param bag to check out
	 */
	private void checkout(ShoppingBag bag) {
		DecimalFormat currency = new DecimalFormat("0.00");
		DecimalFormat expensive = new DecimalFormat("0,000.00");
		double price = bag.salesPrice();
		double tax = bag.salesTax();
		double total = price + tax;;
		int size = bag.getSize();
		
		if (size == 1) {
			System.out.println("**Checking out 1 item.");
		} else {
			System.out.println("**Checking out " + size + " items.");
		}
		
		bag.print();
		bag.emptyBag();
		
		if (price >= 1000) {
			System.out.println("*Sales total: $" + expensive.format(price));
		} else {
			System.out.println("*Sales total: $" + currency.format(price));
		}
		
		if (tax >= 1000) {
			System.out.println("*Sales tax: $" + expensive.format(tax));
		} else {
			System.out.println("*Sales tax: $" + currency.format(tax));
		}
		
		if (total >= 1000) {
			System.out.println("*Total amount paid: $" + expensive.format(total));
		} else {
			System.out.println("*Total amount paid: $" + currency.format(total));
		}
	}
	
	/**
	Main method from driver class will call this for I/O.
	The run() method handles all user interactions.
	 */
    public void run() {
        Scanner sc = new Scanner(System.in).useDelimiter("\\s+"); // to get input for command and data
        
        ShoppingBag bag = new ShoppingBag();
        String input = "";
        
        boolean running = true;
        System.out.println("Let's start shopping!");

        // this while loop will continue asking the user for new commands after each
        // new item is added to the array until they decide to checkout
        while (running) {
            String command;
            GroceryItem item;

            input = sc.nextLine();
			
			command = input.substring(0, 1);
			input = input.trim();
			
			if (command.equals("A")) { // add item to shopping bag
				item = makeItem(input);
				bag.add(item);
				System.out.println(item.getName() + " added to the bag.");
			} 
			else if (command.equals("R")) { // remove item from shopping bag
				item = makeItem(input);
				boolean result = bag.remove(item);
				if (result == true) {
					System.out.println(item.getName() + " " + item.getPrice() + " removed.");
				} else {
					System.out.println("Unable to remove, this item is not in the bag.");
				}
			} 
			else if (command.equals("P")) { // display all items in the bag
				if (bag.isEmpty()) {
					System.out.println("The bag is empty!");
				} else {
					int size = bag.getSize();
					if (size == 1) {
						System.out.println("**You have 1 item in the bag.");
					} else {
						System.out.println("**You have " + size + " items in the bag.");
					}
					bag.print();
					System.out.println("**End of list");
				}
			}
			else if (command.equals("C")) { // checking out the grocery items in bag
				if (bag.isEmpty()) {
					System.out.println("Unable to check out, the bag is empty!");
				} else {
					checkout(bag);
				}
			} 
			else if (command.equals("Q")) { // ends loop to stop program execution
				if (!bag.isEmpty()) { // automatically check out items in the bag
					checkout(bag);
				}
				running = false;
			} 
			else { // user input was invalid
				System.out.println("Invalid command!");
			}
		}
		
		System.out.println("Thanks for shopping with us!");
		sc.close();
	}
}
