package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import models.Beverage;
import models.BeverageName;
import models.Ingredient;
import utils.TreeSetComparator;

public class BeverageService {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
/*
 * creates an object of Beverage if all input is correct.
 * Same beverage cannot be added twice.
 * The BeverageName should be one of the values defined in BeverageName enum
 */
	public static Beverage addBeverage(List<Beverage> beverages) throws IOException {
		Set<Ingredient> ingredients = new TreeSet<Ingredient>(new TreeSetComparator());
		System.out.println("Enter the name of new beverage:");
		String name = br.readLine();
		Set<BeverageName> bName=new HashSet<BeverageName>();
		for(Beverage beverage:beverages) {
			bName.add(beverage.name);
		}
		for (BeverageName b : BeverageName.values()) {
			if (b.name().equals(name)) {
				if(bName.contains(b.name())) {
					System.out.println("Beverage already added!");
					return null;
				}
				System.out.println("Enter ingredient name:quantity");
				System.out.println("Example:");
				System.out.println("hot_water:500,hot_milk:200");
				String composition = br.readLine();
				String ingredient[] = composition.split(",");
				for (int i = 0; i < ingredient.length; i++) {
					Ingredient tempIng = new Ingredient(ingredient[i].split(":")[0],
							Integer.parseInt(ingredient[i].split(":")[1]));
					ingredients.add(tempIng);
				}
				return new Beverage(b, ingredients);
			}
		}
		System.out.println("Invalid input!");
		return null;
	}
/*
 * Loops on the list of beverages and uses the toString() to display the beverage name and recipe
 */
	public static void displayBeverages(List<Beverage> beverages) {
		// TODO Auto-generated method stub
		for (Beverage b : beverages)
			System.out.println(b.toString());
	}
/*
 * Serves the beverages based on the recipe, inventory and number of outlets
 */
	public static Set<Ingredient> serveBeverages(List<Beverage> beverages, Set<Ingredient> ingredients)
			throws NumberFormatException, IOException {
		List<Beverage> beveragestoServe = new ArrayList<Beverage>();
		System.out.println("Enter the number of outlets:");
		int numberOfOutlets = Integer.parseInt(br.readLine());
		int count = 0;
		Set<Ingredient> copyIngredients = ingredients;
		boolean canBeServed = true;
		for (Beverage b : beverages) {
//			looping through the list of beverages and checking if any outlet is still available
			
			if (count < numberOfOutlets) {
				
				// looping through the recipe of each beverage and checking if all ingredients are present in the required quantity in the inventory.
				
				Set<Ingredient> bIngredients = b.recipe.ingredients;
				for (Ingredient i : bIngredients) {
					for (Ingredient j : ingredients) {
						if (i.name.equals(j.name)) {
							if (i.quantity <= j.quantity)
								j.quantity -= i.quantity;
							else {
								canBeServed = false;
								break;
							}
						}
					}
					//if a particular beverage cannot be served, the inventory is rolled back to its previous state
					if (!canBeServed) {
						ingredients = copyIngredients;
						break;
					}
				}
				//if the beverage can be served, then the inventory is updated
				if (canBeServed) {
					copyIngredients = ingredients;
					beveragestoServe.add(b);
					count++;
				}
			}
		}
		if(count>0) {
			System.out.println("Beverages that can be served:");
			for(Beverage b:beveragestoServe)
				System.out.println(b.name);
		}
		else {
			System.out.println("No beverages can be served!");
		}
		return ingredients;
	}

}
