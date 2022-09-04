package project4;

import java.util.ArrayList;

/**
 * Order class used to track order lines for controller.
 */
public class Order implements Customizable {
	public static int lineNumber;
	private ArrayList<OrderLine> orderlines;

	public Order() {
		lineNumber = 1;
		orderlines = new ArrayList<OrderLine>();
	}
	
	/**
	 * Adds an OrderLine object to the orderlines array list.
	 * @return true if the object was added, false otherwise
	 */
	@Override
	public boolean add(Object obj) {
		if (obj instanceof OrderLine) {
			lineNumber++;
			return orderlines.add((OrderLine) obj);
		}
		
		return false;
	}

	/**
	 * Removes an OrderLine object to the orderlines array list.
	 * @return true if the object was removed, false otherwise
	 */
	@Override
	public boolean remove(Object obj) {
		if (obj instanceof OrderLine) {
			OrderLine temp = (OrderLine) obj;
			for (int i = 0; i < orderlines.size(); i++) {
				if (orderlines.get(i).getSandwich().toString().equals(temp.getSandwich().toString())) {
					if (orderlines.get(i).getLineNumber() == temp.getLineNumber()) {
						orderlines.remove(i);
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * Gets the size of the orderlines array list.
	 * @return size
	 */
	public int size() {
		return orderlines.size();
	}
	
	/**
	 * Resets the line number and clears the orderlines array.
	 */
	public void clear() {
		lineNumber = 1;
		orderlines.clear();
	}
	
	/**
	 * Gets the order line at the given index
	 * @param index
	 * @return OrderLines if the index is valid, null otherwise
	 */
	public OrderLine getOrderLine(int index) {
		if (index < 0 || index > size()) {
			return null;
		}
		if (size() == 0) {
			return null;
		}
		return orderlines.get(index);
	}
	
	/** 
	 * Reorders the line numbers in the array list.
	 */
	public void reorder() {
		for (int i = 0; i < size(); i++) {
			if (orderlines.get(i).getLineNumber() != i + 1) {
				orderlines.get(i).setLineNumber(i + 1);
			}
		}
		lineNumber = size() + 1;
	}
}
