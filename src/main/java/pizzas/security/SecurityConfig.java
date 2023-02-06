package pizzas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pizzas.User2;
import pizzas.data.UserRepository;

@Configuration
public class SecurityConfig {
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepo) {
    return username -> {
      User2 user = userRepo.findByUsername(username);
      if (user != null) {
        return user;
      }
      throw new UsernameNotFoundException(
                      "User2 '" + username + "' not found");
    };
  }
  
  @SuppressWarnings("deprecation")
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  
	  return http.csrf()
		      .disable()
		      .authorizeRequests()
			  .requestMatchers("/design", "/orders", "/data-api").hasRole("USER")
			  .anyRequest().permitAll()
    		
			  .and()
			  .formLogin()
			  .loginPage("/login")

			  .and()
			  .logout()
			  .logoutSuccessUrl("/")

      // Make H2-Console non-secured; for debug purposes
			  /*.and()
			  .csrf()
			  .ignoringRequestMatchers("/h2-console/**")*/

      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
			  .and()  
			  .headers()
			  .frameOptions()
			  .sameOrigin()
		
			  .and()
			  .build();
  }
  
}