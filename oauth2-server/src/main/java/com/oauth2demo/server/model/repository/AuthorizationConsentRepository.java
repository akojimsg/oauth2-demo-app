package com.oauth2demo.server.model.repository;

import com.oauth2demo.server.model.entity.AuthorizationConsent;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationConsentRepository extends ListCrudRepository<AuthorizationConsent, AuthorizationConsent.AuthorizationConsentId> {
  Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
  void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}