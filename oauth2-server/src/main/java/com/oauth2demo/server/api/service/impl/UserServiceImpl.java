package com.oauth2demo.server.api.service.impl;

import com.oauth2demo.server.api.dto.UserDTO;
import com.oauth2demo.server.api.service.UserService;
import com.oauth2demo.server.model.entity.UserInfo;
import com.oauth2demo.server.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserInfo createUser(UserDTO request) {
    if (repository.existsByUsername(request.username())){
      throw new UsernameNotFoundException(String.format("Username %s is already registered!", request.username()));
    };
    var user = UserInfo.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .dob(request.dob())
        .username(request.username())
        .password(passwordEncoder.encode(request.password()))
        .role(request.role())
        .build();
    return repository.save(user);
  }

  @Override
  public UserInfo findByUserName(String userName) throws Exception {
    return repository.findByUsername(userName).orElseThrow(
        () -> new Exception(String.format("No user with username: %s", userName))
    );
  }

  @Override
  public List<UserInfo> getAllUsers(){
    return this.repository.findAll();
  }

  @Override
  public UserInfo findUserById(Long id){
    return repository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException(String.format("Student not found with id %d", id))
    );
  }
  @Override
  public void deleteUser(Long id) {

  }
}
