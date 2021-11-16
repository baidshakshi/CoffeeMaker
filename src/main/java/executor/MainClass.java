package executor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import models.Beverage;
import models.Ingredient;
import service.BeverageService;
import service.IngredientService;
import utils.TreeSetComparator;
/*
 * MainClass - deals with end user inputs to perform actions like adding beverages, ingredients,
 *  displaying beverages, ingredients as well as serving beverages.
 */
public class MainClass {
	static Set<Ingredient> ingredients = new TreeSet<Ingredient>(new TreeSetComparator());
	static List<Beverage> beverages = new ArrayList<Beverage>();

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ch = "n";
		int option = 1;
		/*
		 * taking user input as a choice for what action needs to be performed.
		 */
		do {
			System.out.println("Enter 1 to add new ingredients");
			System.out.println("Enter 2 to update stock of ingredients");
			System.out.println("Enter 3 to display ingredients");
			System.out.println("Enter 4 to add new beverage");
			System.out.println("Enter 5 to display beverages");
			System.out.println("Enter 6 to serve beverages");
			System.out.print("Please enter your choice:");
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			switch (option) {
			case 1:
				/*
				 * Adding new Ingredients
				 */
				do {
					ingredients = IngredientService.addIngredient(ingredients);
					System.out.println("Enter y to add more ingredients:");
					ch = br.readLine().trim();
				} while (ch.equalsIgnoreCase("y"));
				break;
			case 2:
				/*
				 * Restock Ingredients
				 */
				do {
					IngredientService.restockIngredient(ingredients);
					System.out.println("Enter y to restock more ingredients:");
					ch = br.readLine();
				} while (ch.equalsIgnoreCase("y"));
				break;
			case 3:
				/*
				 * Display Inventory
				 */
				IngredientService.displayIngredients(ingredients);
				break;
			case 4:
				/*
				 * add Beverages
				 */
				do {
					Beverage b = BeverageService.addBeverage(beverages);
					if (b != null)
						beverages.add(b);
					System.out.println("Enter y to add more beverages:");
					ch = br.readLine().trim();
				} while (ch.equalsIgnoreCase("y"));
				break;
			case 5:
				/*
				 * displaying beverages with Recipe
				 */
				BeverageService.displayBeverages(beverages);
				break;
			case 6:
				/*
				 * serving beverages based on number of outlets and displays inventory after drink is served
				 */
				ingredients = BeverageService.serveBeverages(beverages, ingredients);
				System.out.println("After serving, inventory is as follows:\n");
				IngredientService.displayIngredients(ingredients);
				break;
			}
			System.out.println("Enter y to continue:");
			ch = br.readLine().trim();
		} while (ch.equalsIgnoreCase("y"));
	}

}
