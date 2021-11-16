package utils;

import java.util.Comparator;

import models.Ingredient;

public class TreeSetComparator implements Comparator<Ingredient> {

	@Override
	public int compare(Ingredient ingredient1, Ingredient ingredient2) {
		if((ingredient1.quantity-ingredient2.quantity)>=0)
			return 1;
		else
			return -1;
	}

}
