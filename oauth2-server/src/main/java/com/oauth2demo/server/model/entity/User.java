package com.oauth2demo.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oauth2demo.server.utils.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`user`")
public class User implements OAuth2User, UserDetails {
  @Id
  @SequenceGenerator(sequenceName = "user_id_sequence", name = "user_id_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
  private Long id;

  @Column
  protected String username;

  @Column
  @JsonIgnore
  protected String password;

  @Column
  @Email
  //@Schema(example = "john.doe@email.com")
  protected String email;

  @Column
  protected String firstName;

  @Column
  //@Schema(example = "John Doe")
  protected String fullName;

  @Column
  //@Schema(example = "Doe")
  protected String lastName;

  @Column
  @Enumerated(EnumType.STRING)
  //@Schema
  protected Role role;

  @Column(nullable = false)
  //@Schema(example = "1974-08-12")
  protected LocalDate dob;

  @Column
  @Transient
  protected Integer age;

  public Integer getAge() {
    return Period.between(this.dob, LocalDate.now()).getYears();
  }

  public String getFullName() {return this.firstName.concat(" ").concat(this.lastName); }

  @Override
  public <A> A getAttribute(String name) {
    return OAuth2User.super.getAttribute(name);
  }

  @Override
  public Map<String, Object> getAttributes() {
    return Map.of();
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
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

  @Override
  public String getName() {
    return this.username;
  }
}