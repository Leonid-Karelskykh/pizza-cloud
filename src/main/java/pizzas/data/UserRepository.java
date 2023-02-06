package pizzas.data;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzas.User2;

public interface UserRepository extends JpaRepository<User2, Long> {
	User2 findByUsername(String username);
}