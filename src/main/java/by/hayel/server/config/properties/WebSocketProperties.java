package by.hayel.server.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "web-socket")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebSocketProperties {
  String app;
  String broker;
  String endpoint;
  String allowedOrigins;
  String user;
}
