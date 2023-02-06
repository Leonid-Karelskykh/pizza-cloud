package pizzas.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import pizzas.PizzaOrder;

@RestResource
public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
	
}