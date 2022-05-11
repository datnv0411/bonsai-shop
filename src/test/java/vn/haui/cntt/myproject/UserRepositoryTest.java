package vn.haui.cntt.myproject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use the real db, not in memory db
@Rollback(value = false) //don't want to roll back transaction
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    // test create users
    @Test
    public void testCreateUser(){
        User user = new User();

        user.setEmail("nguyennam@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Nam");
        user.setLastName("Nguyen");

        User savedUser = userRepository.save(user);

        User existedUser = testEntityManager.find(User.class, savedUser.getId());

        assertThat(existedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email = "cmcglobalinfo@cmc.com.vn";

        User user = userRepository.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
