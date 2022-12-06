package pizzas;

import java.util.List;
import lombok.Data;

@Data
public class Pizza {
	private String name;
	private List<Ingredient> ingredients;
	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}
	public String getName() {
		return this.name;
	}
}