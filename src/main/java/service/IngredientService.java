package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import models.Ingredient;

public class IngredientService {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
/*
 * returns the Set<Ingredients>
 * takes ingredient name and quantity as input from user
 */
	public static Set<Ingredient> addIngredient(Set<Ingredient> ingredients) throws IOException {
		System.out.println("Enter ingredient name:quantity");
		System.out.println("Example:");
		System.out.println("hot_water:500,hot_milk:200"); 
		String input = br.readLine();
		String ingredient[] = input.split(",");
		for (int i = 0; i < ingredient.length; i++) {
			Ingredient tempIng = new Ingredient(ingredient[i].split(":")[0],
					Integer.parseInt(ingredient[i].split(":")[1]));
			ingredients.add(tempIng);
		}
		return ingredients;
	}
	/*
	 * To update the quantity of an ingredient this method can be used.
	 * if the ingredient is not present it will display an error message
	 */

	public static void restockIngredient(Set<Ingredient> ingredients) throws IOException{
		// TODO Auto-generated method stub
		System.out.println("Enter ingredient name:quantity");
		String input = br.readLine();
		String ingredientName = input.split(":")[0];
		int ingredientQuantity = Integer.parseInt(input.split(":")[1]);
		Ingredient i;
		if ((i=isIngredientAvailable(ingredientName, ingredients))!=null) {
			i.quantity+=ingredientQuantity;
		}
		else {
			System.out.println("Ingredient not in Inventory!");
		}
	}
/*
 * checks if a particular ingredient is present in the inventory or not
 */
	private static Ingredient isIngredientAvailable(String ingredientName, Set<Ingredient> ingredients) {
		// TODO Auto-generated method stub
		for (Ingredient i : ingredients) {
			if (i.name.equalsIgnoreCase(ingredientName))
				return i;
		}
		return null;
	}
/*
 * Displaying the Ingredient Name and Quantity
 */
	public static void displayIngredients(Set<Ingredient> ingredients) {
		// TODO Auto-generated method stub
		for(Ingredient i :ingredients) {
			System.out.println(i.toString());
		}
	}

}
