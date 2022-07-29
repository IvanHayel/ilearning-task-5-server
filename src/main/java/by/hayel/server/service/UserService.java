package by.hayel.server.service;

import by.hayel.server.model.user.User;
import java.util.List;

public interface UserService {
  List<User> getUsers();

  User getUserById(Long id);

  User getUserByUsername(String username);

  Boolean existsByUsername(String username);

  User createUser(String username);

  void save(User user);
}
