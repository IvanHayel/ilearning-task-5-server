package by.hayel.server.service;

import by.hayel.server.model.message.Message;
import by.hayel.server.model.user.User;
import java.util.List;

public interface MessageService {
  Message getMessageById(Long id);

  List<Message> getMessagesBySender(User sender);

  List<Message> getMessagesByReceiver(User receiver);

  List<Message> getPublicMessages();

  void save(Message message);
}
