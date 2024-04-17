package com.oauth2demo.server.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oauth2demo.server.model.entity.UserInfo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserInfoConfig implements UserDetails {
  private final UserInfo userInfo;
  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(userInfo.getRole().name()));
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return userInfo.getPassword();
  }

  @Override
  public String getUsername() {
    return userInfo.getUsername();
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}