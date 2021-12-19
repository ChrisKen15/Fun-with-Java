package project4;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for the Order class.
 */
class OrderTest {
	
	@Test 
	void lineNumber() {
		Order.lineNumber++;
		assertEquals(Order.lineNumber, 2);
	}
	
	@Test
	void addTest1() {
		Order order = new Order();
		assertEquals(order.add(new OrderLine(Order.lineNumber, new Chicken())), true);
	}

	@Test
	void addTest2() {
		Sandwich sandwich = new Chicken();
		Order order = new Order();
		assertEquals(order.add(sandwich), false);
	}
	
	@Test
	void removeTest1() {
		Sandwich sandwich = new Chicken();
		OrderLine line = new OrderLine(Order.lineNumber, sandwich);
		Order order = new Order();
		order.add(line);
		assertEquals(order.remove(line), true);
	}
	
	@Test
	void removeTest2() {
		Sandwich sandwich = new Chicken();
		OrderLine line = new OrderLine(Order.lineNumber, sandwich);
		Order order = new Order();
		assertEquals(order.remove(line), false);
	}
	
	@Test
	void removeTest3() {
		Order order = setupOrder();
		assertEquals(order.remove(new OrderLine(1, new Chicken())), true);
	}
	
	@Test
	void sizeTest() {
		Order order = setupOrder();
		assertEquals(order.size(), 3);
		order.remove(new OrderLine(1, new Chicken()));
	}
	
	@Test 
	void clearTest() {
		Order order = setupOrder();
		OrderLine line = new OrderLine(Order.lineNumber, new Chicken());
		order.clear();
		
		assertEquals(order.size(), 0);
		assertEquals(Order.lineNumber, 1);
		assertEquals(order.remove(line), false);
		assertEquals(order.getOrderLine(0), null);
	}
	
	@Test
	void getOrderLineTest1() {
		Order order = setupOrder();
		OrderLine get = order.getOrderLine(0);
		assertEquals(get.getLineNumber(), 1);
		assertEquals(get.getSandwich().toString(), "Chicken,Fried Chicken,Spicy Sauce,Pickles,$8.99");
		assertEquals(get.getPrice(), 8.99, 0);
		
		get = order.getOrderLine(1);
		assertEquals(get.getLineNumber(), 2);
		assertEquals(get.getSandwich().toString(), "Beef,Roast Beef,Provolone Cheese,Mustard,$10.99");
		assertEquals(get.getPrice(), 10.99, 0);
		
		get = order.getOrderLine(2);
		assertEquals(get.getLineNumber(), 3);
		assertEquals(get.getSandwich().toString(), "Fish,Grilled Snapper,Cilantro,Lime,$12.99");
		assertEquals(get.getPrice(), 12.99, 0);
	}
	
	@Test
	void getOrderLineTest2() {
		Order order = setupOrder();
		OrderLine get = order.getOrderLine(-1);
		assertEquals(get, null);
		get = order.getOrderLine(4);
		assertEquals(get, null);
	}
	
	@Test
	void reorderTest() {
		Order order = setupOrder();
		order.remove(new OrderLine(2, new Beef()));
		order.reorder();
		
		OrderLine get = order.getOrderLine(0);
		assertEquals(get.getLineNumber(), 1);
		assertEquals(get.getSandwich().toString(), "Chicken,Fried Chicken,Spicy Sauce,Pickles,$8.99");
		assertEquals(get.getPrice(), 8.99, 0);
		
		get = order.getOrderLine(1);
		assertEquals(get.getLineNumber(), 2);
		assertEquals(get.getSandwich().toString(), "Fish,Grilled Snapper,Cilantro,Lime,$12.99");
		assertEquals(get.getPrice(), 12.99, 0);
	}
	
	/**
	 * Sets up an Order object for testing.
	 * @return Order
	 */
	private Order setupOrder() {
		Order order = new Order();
		order.add(new OrderLine(Order.lineNumber, new Chicken()));
		order.add(new OrderLine(Order.lineNumber, new Beef()));
		order.add(new OrderLine(Order.lineNumber, new Fish()));
		
		return order;
	}
}
