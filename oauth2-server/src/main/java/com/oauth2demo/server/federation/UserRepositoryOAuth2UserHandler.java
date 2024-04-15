package com.oauth2demo.server.federation;

import java.util.function.Consumer;

import com.oauth2demo.server.model.repository.UserRepository;
import com.oauth2demo.server.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * Example {@link Consumer} to perform JIT provisioning of an {@link OAuth2User}.
 *
 * @author Steve Riesenberg
 * @since 1.1
 */
@RequiredArgsConstructor
public final class UserRepositoryOAuth2UserHandler implements Consumer<User> {

  private final UserRepository userRepository;

  @Override
  public void accept(User user) {
    // Capture user in a local data store on first authentication
    if (this.userRepository.findByUsername(user.getName()).isEmpty()) {
      System.out.println("Saving first-time user: name=" + user.getName() + ", claims=" + user.getAttributes() + ", authorities=" + user.getAuthorities());
      this.userRepository.save(user);
    }
  }
}