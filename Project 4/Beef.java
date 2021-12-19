package project4;

import java.text.DecimalFormat;

/**
 * Subclass of sandwich that deals with beef sandwiches.
 */
public class Beef extends Sandwich {
	private double Price = 10.99;
	
	/**
	 * Constructor for the beef class.
	 */
	public Beef() {
		super();
	}
	
	/**
	 * Adds an ingredient to the extras list.
	 * @return true if the ingredient was added, false otherwise
	 */
	@Override
	public boolean add(Object obj) {
		if (super.extras.size() == super.getMaxExtras()) {
			return false;
		}
		
		if (obj instanceof Extra) {
			return super.extras.add((Extra) obj);
		}

		return false;
	}

	/**
	 * Removes an ingredient to the extras list.
	 * @return true if the ingredient was removed, false otherwise
	 */
	@Override
	public boolean remove(Object obj) {
		if (obj instanceof Extra) {
			for (int i = 0; i < super.extras.size(); i++) {
				if (super.extras.get(i).toString().equals(obj.toString())) {
					super.extras.remove(i);
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Calculates the price of the sandwich.
	 * @return double representation of the price
	 */
	@Override
	public double price() {
		double extraPrice = 0.0;
		
		for (int i = 0; i < super.extras.size(); i++) {
			extraPrice += getPerExtra();
		}
		
		return Price + extraPrice;
	}
	
	/**
	 * Converts the sandwich details into a string with comma delimeter separating details.
	 * @return String of the sandwich
	 */
	@Override
	public String toString() {
		String str = "Beef,";
		DecimalFormat format = new DecimalFormat("0.00");
		
		str = str.concat("Roast Beef,Provolone Cheese,Mustard,");
		str = str.concat(super.toString());
		str = str.concat("$");
		str = str.concat(format.format(price()));
		
		return str;
	}

}
