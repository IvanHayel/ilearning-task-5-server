package by.hayel.server.config;

import by.hayel.server.config.properties.CorsProperties;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebConfiguration implements WebMvcConfigurer {
  CorsProperties corsProperties;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping(corsProperties.getMapping())
        .allowedOrigins(corsProperties.getAllowedOrigins())
        .allowedMethods(corsProperties.getAllowedMethods())
        .allowedHeaders(corsProperties.getAllowedHeaders())
        .allowCredentials(corsProperties.isAllowCredentials());
  }

  @Bean
  public LocaleResolver localeResolver() {
    var cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
    return cookieLocaleResolver;
  }
}
