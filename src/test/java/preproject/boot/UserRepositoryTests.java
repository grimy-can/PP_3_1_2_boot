package preproject.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import preproject.boot.model.User;
import preproject.boot.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {


    @Autowired
    private UserRepository repository;

    @Value("${dateformat.value}")
    private String formatDateTime;

    private User user;

    @BeforeEach
    public void setup(){

        user = User.builder()
                .name("Уолтер")
                .lastName("Уайт")
                .age(50)
                .alias("Гайзенберг")
                .occupation("Учитель")
                .isAlive(true)
                .regdate(getDateTimeFormatter().format(LocalDateTime.now()))
                .build();
    }

    @Test
    public void saveUser() {

        repository.save(user);

    }

    @Test
    public void getUser() {

        repository.save(user);

        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(1);
        user = users.get(0);
        assertThat(user.getName()).isEqualTo("Уолтер");
        assertThat(user.getLastName()).isEqualTo("Уайт");
        assertThat(user.getAge()).isEqualTo(50);
        assertThat(user.getAlias()).isEqualTo("Гайзенберг");
        assertThat(user.getOccupation()).isEqualTo("Учитель");
        assertThat(user.getIsAlive()).isEqualTo(true);
    }


    @Test
    public void updateUser(){

        repository.save(user);

        List<User> users = repository.findAll();
        user = users.get(0);
        user.setIsAlive(false);

        repository.save(user);

        users = repository.findAll();
        user = users.get(0);
        assertThat(user.getIsAlive()).isEqualTo(false);
    }

    @Test
    public void deleteUser() {

        repository.save(user);

        List<User> users = repository.findAll();
        user = users.get(0);

        repository.delete(user);

        users = repository.findAll();
        assertThat(users.size()).isEqualTo(0);
    }


    private DateTimeFormatter getDateTimeFormatter(){
        return  DateTimeFormatter.ofPattern(formatDateTime);
    }

}
