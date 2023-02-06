package pizzas.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import pizzas.PizzaOrder;
import pizzas.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
public class OrderController {
	
	private OrderRepository orderRepo;
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid PizzaOrder order, Errors errors, SessionStatus sessionStatus) {
		
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		log.info("Order submitted: {}", order);
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}