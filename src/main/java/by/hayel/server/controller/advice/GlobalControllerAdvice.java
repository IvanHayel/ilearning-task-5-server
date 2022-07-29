package by.hayel.server.controller.advice;

import by.hayel.server.error.AdviceErrorMessage;
import by.hayel.server.exception.MessageNotFoundException;
import by.hayel.server.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public AdviceErrorMessage handleUserNotFoundException(
      UserNotFoundException exception, WebRequest request) {
    return new AdviceErrorMessage(
        HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false));
  }

  @ExceptionHandler(MessageNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public AdviceErrorMessage handleMessageNotFoundException(
      MessageNotFoundException exception, WebRequest request) {
    return new AdviceErrorMessage(
        HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false));
  }
}
