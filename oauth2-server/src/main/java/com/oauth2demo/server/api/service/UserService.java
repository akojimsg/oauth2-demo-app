package com.oauth2demo.server.api.service;

import com.oauth2demo.server.api.dto.UserDTO;
import com.oauth2demo.server.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  User createUser(UserDTO request);
  User findByUserName(String userName) throws Exception;
  List<User> getAllUsers();
  User findUserById(Long id);
  void deleteUser(Long id);
}
