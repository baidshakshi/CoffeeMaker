package models;

import java.util.Set;
/*
 * Recipe is a collection of ingredients. 
 */
public class Recipe {
	public Set<Ingredient> ingredients;
	/*
	 * Constructor for Recipe
	 */
	public Recipe(Set<Ingredient> ingredients) {
		this.ingredients=ingredients;
	}
	/*
	 * returning String format of the Recipe object
	 */	
	public String toString() {
		String s="";
		for(Ingredient i:this.ingredients)
			s+=i.toString()+"\n";
		return s;
	}

}
