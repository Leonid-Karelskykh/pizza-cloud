package pizzas.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pizzas.Ingredient;
import pizzas.Ingredient.Type;
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	private Map<String, Ingredient> ingredientMap = new HashMap<>();
	public IngredientByIdConverter() {
		ingredientMap.put("TOSA", new Ingredient("TOSA", "Tomato Sauce", Type.BASE));
		ingredientMap.put("CRSA", new Ingredient("CRSA", "Creamy Sauce", Type.BASE));
		ingredientMap.put("PACH", new Ingredient("PACH", "Parmesan Cheese", Type.CHEESE));
		ingredientMap.put("MOCH", new Ingredient("MOCH", "Mozarella Cheese", Type.CHEESE));
		ingredientMap.put("HAMM", new Ingredient("HAMM", "Ham", Type.MEET));
		ingredientMap.put("SLMI", new Ingredient("SLMI", "Salami", Type.MEET));
		ingredientMap.put("CHKN", new Ingredient("CHKN", "Chicken", Type.MEET));
		ingredientMap.put("TMTO", new Ingredient("TMTO", "Tomatoes", Type.VEGGIES));
		ingredientMap.put("CORN", new Ingredient("CORN", "Corn", Type.VEGGIES));
		ingredientMap.put("BLKO", new Ingredient("BLKO", "Black Olives", Type.VEGGIES));
		ingredientMap.put("RDON", new Ingredient("RDON", "Red Onion", Type.VEGGIES));
	}
	@Override
	public Ingredient convert(String id) {
	return ingredientMap.get(id);
	}
}