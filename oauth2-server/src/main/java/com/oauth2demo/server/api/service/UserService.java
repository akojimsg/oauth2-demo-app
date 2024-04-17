package com.oauth2demo.server.api.service;

import com.oauth2demo.server.api.dto.UserDTO;
import com.oauth2demo.server.model.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  UserInfo createUser(UserDTO request);
  UserInfo findByUserName(String userName) throws Exception;
  List<UserInfo> getAllUsers();
  UserInfo findUserById(Long id);
  void deleteUser(Long id);
}
