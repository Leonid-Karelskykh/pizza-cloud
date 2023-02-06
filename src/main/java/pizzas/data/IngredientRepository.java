package pizzas.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import pizzas.Ingredient;

@RestResource
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}