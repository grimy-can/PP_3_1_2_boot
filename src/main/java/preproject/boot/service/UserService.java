package preproject.boot.service;

import preproject.boot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    Optional<User> getUser(Integer id);

    void deleteUser(User user);

    List<User> getUsers();
}