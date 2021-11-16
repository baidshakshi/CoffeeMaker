package models;
/*
 * POJO class for Ingredient
 */
public class Ingredient {
	public final String name;
	public int quantity;
	/*
	 * Ingredient constructor
	 */
	public Ingredient(String name,int quantity){
		this.name=name;
		this.quantity=quantity;
	}
	/*
	 * returning String format of the Ingredient object
	 */
	public String toString() {
		return this.name+":"+this.quantity;
	}

}
