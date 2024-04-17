package com.oauth2demo.server.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.oauth2demo.server.model.entity.User;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

//@JsonTypeInfo(
//    use = JsonTypeInfo.Id.NAME,
//    include = JsonTypeInfo.As.PROPERTY,
//    property = "__typename"
//)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserDetailsManger implements UserDetails {
public class UserDetailsManger {
//  private User userDetails;
//
//  @Override
//  @JsonIgnore
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return List.of(new SimpleGrantedAuthority(userDetails.getRole().name()));
//  }
//
//  @Override
//  @JsonIgnore
//  public String getPassword() {
//    return userDetails.getPassword();
//  }
//
//  @Override
//  public String getUsername() {
//    return userDetails.getUsername();
//  }
//
//  @Override
//  @JsonIgnore
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  @JsonIgnore
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  @JsonIgnore
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  @JsonIgnore
//  public boolean isEnabled() {
//    return true;
//  }
}