package pizzas.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import pizzas.Ingredient;
import pizzas.Ingredient.Type;
import pizzas.Pizza;
import pizzas.PizzaOrder;
import pizzas.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignPizzaController(
		IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
				filterByType((List<Ingredient>) ingredients, type));
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
	public String processPizza(@Valid Pizza pizza, Errors errors, @ModelAttribute PizzaOrder pizzaOrder) {
		
		if (errors.hasErrors()) {
			return "design";
		}
		
		pizzaOrder.addPizza(pizza);
		log.info("Processing pizza: {}", pizza);
		
		return "redirect:/orders/current";
	}
}