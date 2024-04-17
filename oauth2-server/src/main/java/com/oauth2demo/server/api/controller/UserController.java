package com.oauth2demo.server.api.controller;

import com.oauth2demo.server.api.dto.UserDTO;
import com.oauth2demo.server.api.service.UserService;
import com.oauth2demo.server.model.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/api/oauth2/users")
public class UserController {
  private final UserService userService;

  @PostMapping
  ResponseEntity<UserInfo> registerNewUser(@RequestBody UserDTO userDto) {
    UserInfo entity = userService.createUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(entity);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserInfo> getUserById(@PathVariable Long id) {
    UserInfo student = userService.findUserById(id);
    return ResponseEntity.ok(student);
  }
}
