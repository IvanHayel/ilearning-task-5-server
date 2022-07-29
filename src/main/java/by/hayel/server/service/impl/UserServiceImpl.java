package by.hayel.server.service.impl;

import by.hayel.server.exception.UserNotFoundException;
import by.hayel.server.model.user.User;
import by.hayel.server.repository.UserRepository;
import by.hayel.server.service.UserService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
  UserRepository repository;

  @Override
  public List<User> getUsers() {
    return repository.findAll();
  }

  @Override
  public User getUserById(Long id) {
    return repository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User getUserByUsername(String username) {
    return repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public Boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }

  @Override
  public User createUser(String username) {
    User user = new User();
    user.setUsername(username);
    save(user);
    return user;
  }

  @Override
  public void save(User user) {
    repository.save(user);
  }
}
