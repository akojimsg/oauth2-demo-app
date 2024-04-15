package com.oauth2demo.server.model.repository;

import java.util.Optional;

import com.oauth2demo.server.model.entity.Authorization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends ListCrudRepository<Authorization, String> {
  Optional<Authorization> findByState(String state);
  Optional<Authorization> findByAuthorizationCodeValue(String authorizationCode);
  Optional<Authorization> findByAccessTokenValue(String accessToken);
  Optional<Authorization> findByRefreshTokenValue(String refreshToken);
  Optional<Authorization> findByOidcIdTokenValue(String idToken);
  Optional<Authorization> findByUserCodeValue(String userCode);
  Optional<Authorization> findByDeviceCodeValue(String deviceCode);
  @Query("select a from Authorization a where a.state = :token" +
         " or a.authorizationCodeValue = :token" +
         " or a.accessTokenValue = :token" +
         " or a.refreshTokenValue = :token" +
         " or a.oidcIdTokenValue = :token" +
         " or a.userCodeValue = :token" +
         " or a.deviceCodeValue = :token"
  )
  Optional<Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(@Param("token") String token);
}