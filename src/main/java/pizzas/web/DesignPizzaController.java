package pizzas.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import pizzas.Ingredient;
import pizzas.Ingredient.Type;
import pizzas.Pizza;
import pizzas.PizzaOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient("TOSA", "Tomato Sauce", Type.BASE),
			new Ingredient("CRSA", "Creamy Sauce", Type.BASE),
			new Ingredient("PACH", "Parmesan Cheese", Type.CHEESE),
			new Ingredient("MOCH", "Mozarella Cheese", Type.CHEESE),
			new Ingredient("HAMM", "Ham", Type.MEET),
			new Ingredient("SLMI", "Salami", Type.MEET),
			new Ingredient("CHKN", "Chicken", Type.MEET),
			new Ingredient("TMTO", "Tomatoes", Type.VEGGIES),
			new Ingredient("CORN", "Corn", Type.VEGGIES),
			new Ingredient("BLKO", "Black Olives", Type.VEGGIES),
			new Ingredient("RDON", "Red Onion", Type.VEGGIES)
		);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
				filterByType(ingredients, type));
		}
	}
	@ModelAttribute(name = "pizzaOrder")
	public PizzaOrder order() {
		return new PizzaOrder();
	}
	@ModelAttribute(name = "pizza")
	public Pizza pizza() {
		return new Pizza();
	}
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	@PostMapping
	public String processTaco(Pizza pizza,
	@ModelAttribute PizzaOrder pizzaOrder) {
	pizzaOrder.addPizza(pizza);
	log.info("Processing taco: {}", pizza);
	return "redirect:/orders/current";
	}
}