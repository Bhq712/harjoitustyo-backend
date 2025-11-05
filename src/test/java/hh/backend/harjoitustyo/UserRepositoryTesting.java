package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.backend.harjoitustyo.domain.User;
import hh.backend.harjoitustyo.domain.UserRepository;

@DataJpaTest
public class UserRepositoryTesting {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void createNewUser() {
        User user = new User("testuser", "password123", "USER");
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
        assertThat(userRepository.findByUsername("testuser")).isNotNull();
    }

    @Test
    public void findUserByUsernameShouldReturnUser() {
        User user = new User("admin", "securepass", "ADMIN");
        userRepository.save(user);

        User found = userRepository.findByUsername("admin");
        assertThat(found).isNotNull();
        assertThat(found.getRole()).isEqualTo("ADMIN");
    }

    @Test
    public void deleteUserShouldRemoveFromDatabase() {
        User user = new User("tempuser", "pass", "USER");
        userRepository.save(user);

        Long id = user.getId();
        userRepository.deleteById(id);

        assertThat(userRepository.findById(id)).isEmpty();
    }

    @Test
    public void passwordShouldBeEncryptedUsingBCrypt() {
        // Kryptataan salasana
        String rawPassword = "mypassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Tallennetaan käyttäjä kryptatulla salasanalla
        User user = new User("cryptoUser", encodedPassword, "USER");
        userRepository.save(user);

        // Haetaan tallennettu käyttäjä
        User found = userRepository.findByUsername("cryptoUser");
        assertThat(found).isNotNull();

        // Tarkistetaan että tallennettu salasana ei ole selkokielinen
        assertThat(found.getPassword()).isNotEqualTo(rawPassword);

        // Varmistetaan että BCrypt osaa verrata oikein
        boolean matches = passwordEncoder.matches(rawPassword, found.getPassword());
        assertThat(matches).isTrue();
    }
}
