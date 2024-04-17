package com.oauth2demo.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oauth2demo.server.model.entity.User;
import com.oauth2demo.server.model.repository.UserRepository;
import com.oauth2demo.server.service.JdbcRegisteredClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OAuth2ServerApplicationConfig {
  private static final Logger logger = LoggerFactory.getLogger(OAuth2ServerApplicationConfig.class);
  private final PasswordEncoder passwordEncoder;
  private final JdbcRegisteredClientRepository jdbcRegisteredClientRepository;
  private final UserRepository userRepository;

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      initJdbcdUserRepository();
      initRegisteredClientRepository();
    };
  }

  private void initJdbcdUserRepository() {
    logger.info("Loading initial data from json file");
    try (InputStream inputStream = TypeReference.class.getResourceAsStream("/users.json")) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
      List<User> userInfos = mapper.readValue(inputStream, new TypeReference<List<User>>() {})
          .stream()
          .peek((user) -> user.setPassword(passwordEncoder.encode("changeit$"))).collect(Collectors.toList());
      userRepository.saveAll(userInfos);
      logger.info("Initial data loaded successfully!");
    } catch (IOException e) {
      logger.error("Failed to load initial data: {0}", e);
    }
  }

  private void initRegisteredClientRepository(){
    RegisteredClient messagingClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("messaging-client")
        .clientSecret("{bcrypt}secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
        .redirectUri("http://127.0.0.1:8080/authorized")
        .postLogoutRedirectUri("http://127.0.0.1:8080/logged-out")
        .scope(OidcScopes.OPENID)
        .scope(OidcScopes.PROFILE)
        .scope("message.read")
        .scope("message.write")
        .scope("user.read")
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build();

    RegisteredClient deviceClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("device-messaging-client")
        .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
        .authorizationGrantType(AuthorizationGrantType.DEVICE_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .scope("message.read")
        .scope("message.write")
        .build();

    RegisteredClient tokenExchangeClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("token-client")
        .clientSecret("{noop}token")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(new AuthorizationGrantType("urn:ietf:params:oauth:grant-type:token-exchange"))
        .scope("message.read")
        .build();

    RegisteredClient mtlsDemoClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("mtls-demo-client")
        .clientAuthenticationMethod(new ClientAuthenticationMethod("tls_client_auth"))
        .clientAuthenticationMethod(new ClientAuthenticationMethod("self_signed_tls_client_auth"))
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .scope("message.read")
        .scope("message.write")
        .clientSettings(
            ClientSettings.builder()
                .jwkSetUrl("http://127.0.0.1:8080/jwks")
                .build()
        )
        .build();

    jdbcRegisteredClientRepository.save(messagingClient);
    jdbcRegisteredClientRepository.save(deviceClient);
    jdbcRegisteredClientRepository.save(tokenExchangeClient);
    jdbcRegisteredClientRepository.save(mtlsDemoClient);
  }
}
