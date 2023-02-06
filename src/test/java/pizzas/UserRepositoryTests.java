package pizzas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import pizzas.data.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repo;

	@Test
	public void testCreateUser() {
		User2 user = new User2("habuma", "password", 
	            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
	            "76227", "123-123-1234");
		
		User2 savedUser = repo.save(user);
		
		User2 existUser = entityManager.find(User2.class, savedUser.getId());
		
		assertThat(user.getId()).isEqualTo(existUser.getId());
}
}