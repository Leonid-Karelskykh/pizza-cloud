package pizzas.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pizzas.Ingredient;
import pizzas.Ingredient.Type;
import pizzas.User2;
import pizzas.data.IngredientRepository;
import pizzas.data.UserRepository;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login");
	}
	
	@Bean
	  public CommandLineRunner dataLoader(IngredientRepository repo,
	        UserRepository userRepo, PasswordEncoder encoder) {
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	    	repo.deleteAll();
	        userRepo.deleteAll();

	        repo.save(new Ingredient("TOSA", "Tomato Sauce", Type.BASE));
			repo.save(new Ingredient("CRSA", "Creamy Sauce", Type.BASE));
			repo.save(new Ingredient("PACH", "Parmesan Cheese", Type.CHEESE));
			repo.save(new Ingredient("MOCH", "Mozarella Cheese", Type.CHEESE));
			repo.save(new Ingredient("HAMM", "Ham", Type.MEET));
			repo.save(new Ingredient("SLMI", "Salami", Type.MEET));
			repo.save(new Ingredient("CHKN", "Chicken", Type.MEET));
			repo.save(new Ingredient("TMTO", "Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("CORN", "Corn", Type.VEGGIES));
			repo.save(new Ingredient("BLKO", "Black Olives", Type.VEGGIES));
			repo.save(new Ingredient("RDON", "Red Onion", Type.VEGGIES));
	                
	        userRepo.save(new User2("habuma", encoder.encode("password"), 
	            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
	            "76227", "123-123-1234"));
	      }
	    };
	  }
	
}