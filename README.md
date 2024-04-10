## Implementation of OAuth2 with Spring Boot

### Tools

- Gradle
- Java JDK v21.0+
- Spring Boot
- Spring CLI

### Snippets

```
# Create cert.pem
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -passout pass:password

# Create keystore
openssl pkcs12 -export -in cert.pem -inkey key.pem -out keystore.p12 -name oauth2-server-poc -passin pass:password -passout pass:password

# List keystore
keytool -list -keystore keystore.p12
```

### Resources

1. [Spring Authorization Server](https://docs.spring.io/spring-authorization-server/reference/overview.html)
2. [Applications With SSL Bundles](https://www.baeldung.com/spring-boot-security-ssl-bundles)