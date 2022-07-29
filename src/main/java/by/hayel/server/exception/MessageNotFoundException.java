package by.hayel.server.exception;

import java.io.Serial;

public class MessageNotFoundException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;

  private static final String DEFAULT_MESSAGE = "Error -> Message not found!";
  private static final String MESSAGE_WITH_ID = "Error -> Message with id %d not found!";

  public MessageNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public MessageNotFoundException(Long id) {
    super(String.format(MESSAGE_WITH_ID, id));
  }
}
