package by.hayel.server.payload.request;

import by.hayel.server.payload.ClientRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnterChatRequest implements ClientRequest {
  String username;
}
