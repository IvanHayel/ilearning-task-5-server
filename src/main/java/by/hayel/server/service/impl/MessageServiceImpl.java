package by.hayel.server.service.impl;

import by.hayel.server.exception.MessageNotFoundException;
import by.hayel.server.model.message.Message;
import by.hayel.server.model.user.User;
import by.hayel.server.repository.MessageRepository;
import by.hayel.server.service.MessageService;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl implements MessageService {
  MessageRepository repository;

  @Override
  public Message getMessageById(Long id) {
    return repository.findById(id).orElseThrow(MessageNotFoundException::new);
  }

  @Override
  public List<Message> getMessagesBySender(User sender) {
    return repository.findAllBySender(sender).orElse(Collections.emptyList());
  }

  @Override
  public List<Message> getMessagesByReceiver(User receiver) {
    return repository.findAllByReceiver(receiver).orElse(Collections.emptyList());
  }

  @Override
  public List<Message> getPublicMessages() {
    return repository.findAllByReceiverIsNull().orElse(Collections.emptyList());
  }

  @Override
  public void save(Message message) {
    repository.save(message);
  }
}
