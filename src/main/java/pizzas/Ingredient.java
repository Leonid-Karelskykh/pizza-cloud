package pizzas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {
	
	@Id
	private String id;
	public String name;
	private Type type;
	public enum Type {
		BASE, CHEESE, MEET, VEGGIES;
	}
	public Type getType() {
		return this.type;
	}
}