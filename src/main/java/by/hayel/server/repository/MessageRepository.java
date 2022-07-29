package by.hayel.server.repository;

import by.hayel.server.model.message.Message;
import by.hayel.server.model.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  Optional<List<Message>> findAllBySender(User sender);

  Optional<List<Message>> findAllByReceiver(User receiver);

  Optional<List<Message>> findAllByReceiverIsNull();
}
