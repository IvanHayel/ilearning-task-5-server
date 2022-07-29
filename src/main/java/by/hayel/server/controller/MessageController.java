package by.hayel.server.controller;

import by.hayel.server.model.message.Message;
import by.hayel.server.model.user.User;
import by.hayel.server.payload.request.SendMessageRequest;
import by.hayel.server.service.MessageService;
import by.hayel.server.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {
  private static final String DESTINATION_PRIVATE = "/private";
  private static final String DESTINATION_SENDING = "/sending";

  UserService userService;
  MessageService messageService;
  SimpMessagingTemplate template;

  @MessageMapping("/message")
  @SendTo("/chat/public")
  public Message handlePublicMessage(@Payload SendMessageRequest request) {
    User sender = userService.getUserByUsername(request.getSenderUsername());
    Message message = new Message();
    message.setSender(sender);
    message.setContent(request.getContent());
    messageService.save(message);
    return message;
  }

  @MessageMapping("/private-message")
  public Message handlePrivateMessage(@Payload SendMessageRequest request) {
    User sender = userService.getUserByUsername(request.getSenderUsername());
    User receiver = userService.getUserByUsername(request.getReceiverUsername());
    sender.addContact(receiver.getUsername());
    receiver.addContact(sender.getUsername());
    userService.save(sender);
    userService.save(receiver);
    Message message = new Message();
    message.setSender(sender);
    message.setReceiver(receiver);
    message.setContent(request.getContent());
    messageService.save(message);
    template.convertAndSendToUser(receiver.getUsername(), DESTINATION_PRIVATE, message);
    template.convertAndSendToUser(sender.getUsername(), DESTINATION_SENDING, message);
    return message;
  }
}
