package project1;
/**
The container class that defines the abstract data type.
It contains the data field and operations of a shopping bag.
 */
public class ShoppingBag {
    private GroceryItem[] bag;	// array-based implementation of the bag
    private int size;			// number of items currently in the bag

    /**
    Constructor for the ShoppingBag class.
    Initializes the shopping bag with an initial array size of 2.
     */
    public ShoppingBag() {
        bag = new GroceryItem[2];
    }
    
    /**
    Helper method to find a grocery item in the shopping bag array.
    @param item to be found
    @return index of the grocery item, -1 if the item is not in the shopping bag
     */
    private int find(GroceryItem item) {
        for (int i = 0; i < size; i++) {
        	if (bag[i].equals(item)) {
        		return i;
        	}
        }
        return -1;
    }
    
    /**
    Helper method to grow the capacity.
    The method doubles the maximum length of the shopping bag.
     */
    private void grow() {
        GroceryItem[] newBag = new GroceryItem[size * 2];
        
        for (int i = 0; i < size; i++) {
        	newBag[i] = bag[i];
        }
        bag = newBag;
    }

    /**
    This method adds item to the array bag.
    The GroceryItem is assumed to be properly formatted.
    If the array is full, then it will call grow() to double array's size.
    @param item to be added to Shopping Bag
     */
    public void add(GroceryItem item) {
    	if (this.size == bag.length) { // the shopping bag is at maximum capacity
    		grow();
    	}
    	
    	bag[size] = item;
    	this.size++;
    }

    /**
    Method to remove a grocery item from bag if found.
    If the removed item is not the last item in the array,
    the last item will replace the removed item.
    The bag array will not be affected if the item is not found in the bag.
    @param item to be removed from Shopping Bag
    @return true if item was found and removed, false if the item is not in the bag
     */
    public boolean remove(GroceryItem item) {
        int index = find(item);
        
        if (index == -1) { // item was not found
        	return false;
        }
        
        if (index != size) {
        	bag[index] = bag[size-1];	// swap index and last item
        }
        bag[size-1] = null;
        this.size--;
        
        return true;
    }

    /**
    Calculates the sales price of items in the shopping bag.
    The method does not format the sales price to "$0.00".
    @return total sales price
     */
    public double salesPrice() {
        double salesPrice = 0;

        for (int x = 0; x < size; x++) {
        	salesPrice += bag[x].getPrice();
        }
        
        return salesPrice;
    }

    /**
    Calculates the sales tax of items in the shopping bag.
    The method does not format the sales tax to "$0.00".
    @return total sales tax
     */
    public double salesTax() {
    	double taxRate = 0.06625;
        double salesTax = 0;

        for (int i = 0; i < this.size; i++) {
        	if (bag[i].getTax()) {
        		salesTax += bag[i].getPrice() * taxRate;
        	}
        }
        
        return salesTax;
    }

    /**
    Prints to IO all items in the shopping bag.
    The method will print nothing if the bag is empty.
     */
    public void print() {
        for (int e = 0; e < size; e++) {
            System.out.println("·" + bag[e].toString());
        }
    }
    
    /**
    Shows the number of items in the shopping bag.
    @return integer size of shopping bag
     */
    public int getSize() {
        return this.size;
    }
    
    /**
    Checks the size of the shopping bag for items and determines whether it's empty.
    @return true if the shopping bag is empty
     */
    public boolean isEmpty() {
    	if (size > 0) {
    		return false;
    	}
    	return true;
    }
    
    /**
    Nulls all items in the shopping bag array and resets the size to 0.
    This method does not change the total length of the array.
     */
    public void emptyBag() {
        for (int i = 0; i < size; i++) {
        	bag[i] = null;
        }
        size = 0;
    }

    /**
    Test bed main that implements test cases.
    Only the results of the tests are printed to console.
    Input data and test cases will not be displayed.
    @param args is not used
     */
    public static void main(String[] args) {
    	ShoppingBag testBag = new ShoppingBag();
    	GroceryItem item1 = new GroceryItem("I", 5.987, false);
    	GroceryItem item2 = new GroceryItem("like", 4.321, false);
    	GroceryItem item3 = new GroceryItem("sushi_and_burger", 1000, false);
    	GroceryItem item4 = new GroceryItem("hello", 40, true);
    	GroceryItem item5 = new GroceryItem("world", 60, true);
    	
    	// test 1
    	System.out.println("Test Case #1");
    	testBag.add(item1);
    	testBag.print();
    	System.out.println();
    	
    	// test 2
    	System.out.println("Test Case #2");
    	testBag.emptyBag();
    	testBag.print();
    	System.out.println();
    	
    	// test 3
    	System.out.println("Test Case #3");
    	System.out.println("Case 1 returns");
    	testBag.add(item1);
    	testBag.add(item1);
    	testBag.add(item1);
    	testBag.print();
    	System.out.println("Case 2 returns");
    	testBag.add(item1);
    	testBag.add(item1);
    	testBag.add(item1);
    	testBag.print();
    	System.out.println();
    	testBag.emptyBag();
    	
    	// test 4
    	System.out.println("Test Case #4");
    	System.out.println("Case 1 returns");
    	testBag.add(item1);
    	System.out.println(testBag.remove(item2));
    	System.out.println("Case 2 returns");
    	System.out.println(testBag.remove(item1));
    	System.out.println("Case 3 returns");
    	testBag.add(item1);
    	testBag.add(item1);
    	testBag.add(item1);
    	System.out.println(testBag.remove(item1));
    	System.out.println();
    	testBag.emptyBag();
    	
    	// test 5
    	System.out.println("Test Case #5");
    	testBag.add(item1);
    	testBag.add(item2);
    	testBag.add(item3);
    	testBag.add(item4);
    	testBag.print();
    	System.out.println();
    	testBag.emptyBag();
    	
    	// test 6
    	System.out.println("Test Case #6");
    	testBag.add(item1);
    	testBag.add(item2);
    	System.out.println(testBag.salesPrice());
    	System.out.println();
    	testBag.emptyBag();
    	
    	// test 7
    	System.out.println("Test Case #7");
    	testBag.add(item4);
    	testBag.add(item5);
    	System.out.println(testBag.salesTax());
    	System.out.println();
    	testBag.emptyBag();
    	
    	// test 8
    	System.out.println("Test Case #8");
    	System.out.println("Case 1 returns");
    	testBag.add(item1);
    	System.out.println(testBag.getSize());
    	System.out.println("Case 2 returns");
    	testBag.add(item1);
    	testBag.add(item1);
    	System.out.println(testBag.getSize());
    	System.out.println();
    	testBag.emptyBag();
    	
    	// test 9
    	System.out.println("Test Case #9");
    	System.out.println("Case 1 returns");
    	testBag.add(item1);
    	System.out.println(testBag.isEmpty());
    	System.out.println("Case 2 returns");
    	testBag.emptyBag();
    	System.out.println(testBag.isEmpty());
    }
}
