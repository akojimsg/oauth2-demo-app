package com.oauth2demo.server.model.repository;

import com.oauth2demo.server.model.entity.UserInfo;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<UserInfo, Long> {
  Optional<UserInfo> findByUsername(String username);
  Boolean existsByUsername(String username);
}
