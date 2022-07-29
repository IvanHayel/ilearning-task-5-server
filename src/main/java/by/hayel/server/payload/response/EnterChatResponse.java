package by.hayel.server.payload.response;

import by.hayel.server.model.message.Message;
import by.hayel.server.model.user.User;
import by.hayel.server.payload.ServerResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnterChatResponse implements ServerResponse {
  User user;
  List<Message> sentMessages;
  List<Message> receivedMessages;
  List<Message> publicMessages;
}
