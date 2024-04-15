package com.oauth2demo.server.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;
//import io.swagger.v3.oas.annotations.media.Schema;

//@Schema(enumAsRef = true)
public enum Role {
  STUDENT ("STUDENT"),
  STAFF ("STAFF"),
  ADMIN ("ADMIN");

  private final String role;
  Role(String role){
    this.role = role;
  }

  @Override
  @JsonValue
  public String toString() {
    return this.role;
  }
}
