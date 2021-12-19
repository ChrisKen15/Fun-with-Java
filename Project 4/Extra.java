package project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Extra class for details of extra ingredients.
 */
public class Extra {
	private String ingredient;
		
	/**
	 * Constructor for the Extra class.
	 * @param ingredient
	 */
	public Extra(String ingredient) {
		this.ingredient = ingredient;
	}
	
	/**
	 * Returns the string representation of ingredient.
	 * @return ingredient
	 */
	public String toString() {
		return this.ingredient;
	}
	
	/**
     * Creates an observable list of type String with extra ingredients.
     * @return ObservableList of ingredients
     */
    public static ObservableList<String> ingredientList() {
    	ObservableList<String> ingredients = FXCollections.observableArrayList();
        ingredients.add("Lettuce");
        ingredients.add("Tomatoes");
        ingredients.add("Onions");
        ingredients.add("Cheddar Cheese");
        ingredients.add("Blue Cheese");
        ingredients.add("Sliced Peppers");
        ingredients.add("Buffalo Sauce");
        ingredients.add("Ranch Sauce");
        ingredients.add("Pepperoni");
        ingredients.add("Hummus");
        return ingredients;
    }
}
