package pizzas.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import pizzas.Pizza;

@RestResource
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
	
}