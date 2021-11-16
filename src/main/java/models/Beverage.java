package models;

import java.util.Set;
/*
 * POJO class for Beverage
 */
public class Beverage {
	public final BeverageName name;
	public Recipe recipe;
	/*
	 * Beverage constructor
	 */
	public Beverage(BeverageName name, Set<Ingredient> ingredients) {
		this.name=name;
		recipe=new Recipe(ingredients);
	}
	/*
	 * returning String format of the Beverage object
	 */
	public String toString() {
		return this.name+"\n"+this.recipe.toString();
	}
	
}
