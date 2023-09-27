package preproject.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preproject.boot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}