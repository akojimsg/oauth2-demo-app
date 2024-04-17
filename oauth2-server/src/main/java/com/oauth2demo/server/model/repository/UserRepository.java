package com.oauth2demo.server.model.repository;

import com.oauth2demo.server.model.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
}
