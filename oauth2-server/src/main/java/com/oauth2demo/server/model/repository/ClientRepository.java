package com.oauth2demo.server.model.repository;

import java.util.Optional;

import com.oauth2demo.server.model.entity.Client;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, String> {
  Optional<Client> findByClientId(String clientId);
}