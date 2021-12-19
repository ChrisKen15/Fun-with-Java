package project4;

import java.util.ArrayList;

/**
 * Abstract class for the sandwich object.
 */
public abstract class Sandwich implements Customizable {
	static final int MAX_EXTRAS = 6;
	static final double PER_EXTRA = 1.99;
	protected ArrayList<Extra> extras;
	
	/**
	 * Constructor for the Sandwich class.
	 */
	public Sandwich() {
		extras = new ArrayList<Extra>();
	}
	
	public abstract boolean add(Object obj);
	public abstract boolean remove(Object obj);
	public abstract double price();
	
	/**
	 * Converts the extras array list into a string with comma delimeter.
	 * @return String representation of extras 
	 */
	public String toString() {
		String str = "";
		
		for (int i = 0; i < extras.size(); i++)
		{
			str = str.concat(extras.get(i).toString());
			str = str.concat(",");
		}
		return str;
	}

	/**
	 * Gets the total number of ingredients a sandwich can have.
	 * @return MAX_EXTRAS
	 */
	public int getMaxExtras() {
		return MAX_EXTRAS;
	}
	
	/**
	 * Gets the price of an extra ingredient on a sandwich.
	 * @return PER_EXTRA
	 */
	public double getPerExtra() {
		return PER_EXTRA;
	}
}
