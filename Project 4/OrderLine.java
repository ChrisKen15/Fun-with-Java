package project4;

/**
 * OrderLine class used to stroe information about an order line for Order.
 */
public class OrderLine {
	private int lineNumber;		// serial number
	private Sandwich sandwich;	// sandwich object for order line
	private double price;		// cost of the sandwich
	
	/**
	 * Constructor for the OrderLine class.
	 * @param lineNumber from Order.lineNumber
	 * @param sandwich object
	 */
	public OrderLine(int lineNumber, Sandwich sandwich) {
		this.lineNumber = lineNumber;
		this.sandwich = sandwich;
		this.price = sandwich.price();
	}
	
	/**
	 * Sets a new line number for the order line.
	 * @param lineNumber
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	/**
	 * Gets the line number.
	 * @return lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}
	
	/**
	 * Gets the sandwich in the order line.
	 * @return sandwich object
	 */
	public Sandwich getSandwich() {
		return sandwich;
	}
	
	/**
	 * Gets the cost of the sandwich in the orderline.
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
}
