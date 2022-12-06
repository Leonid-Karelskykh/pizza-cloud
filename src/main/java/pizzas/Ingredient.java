package pizzas;

import lombok.Data;
@Data
public class Ingredient {
	public String id;
	public String name;
	private Type type;
	public enum Type {
		BASE, CHEESE, MEET, VEGGIES;
	}
	public Ingredient(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	public Type getType() {
		return this.type;
	}
}