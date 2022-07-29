package by.hayel.server.controller;

import by.hayel.server.model.user.User;
import by.hayel.server.payload.ServerResponse;
import by.hayel.server.payload.request.EnterChatRequest;
import by.hayel.server.payload.response.EnterChatResponse;
import by.hayel.server.service.MessageService;
import by.hayel.server.service.UserService;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
  UserService userService;
  MessageService messageService;

  @PostMapping("/enter")
  public ResponseEntity<ServerResponse> enterChat(@RequestBody EnterChatRequest request) {
    String username = request.getUsername();
    ServerResponse response;
    var publicMessages = messageService.getPublicMessages();
    if (Boolean.TRUE.equals(userService.existsByUsername(username))) {
      User user = userService.getUserByUsername(username);
      var sentMessages = messageService.getMessagesBySender(user);
      var receivedMessages = messageService.getMessagesByReceiver(user);
      response = new EnterChatResponse(user, sentMessages, receivedMessages, publicMessages);
      return ResponseEntity.ok(response);
    } else {
      User user = userService.createUser(username);
      response =
          new EnterChatResponse(
              user, Collections.emptyList(), Collections.emptyList(), publicMessages);
      return ResponseEntity.ok(response);
    }
  }
}
