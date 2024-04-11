## Implementation of OAuth2 with Spring Boot

### Tools

- Gradle
- Java JDK v21.0+
- Spring Boot
- Spring CLI

### Snippets

```
# Create cert.pem
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -passout pass:changeit

# Create keystore
openssl pkcs12 -export -in cert.pem -inkey key.pem -out keystore.p12 -name oauth2-server-certificate -passin pass:changeit -passout pass:changeit

# Create truststore
keytool -import -file cert.pem -alias oauth2-server-certificate -keystore truststore

# List keystore
keytool -list -keystore keystore.p12

```

### Resources

1. [Spring Authorization Server](https://docs.spring.io/spring-authorization-server/reference/overview.html)
2. [Applications With SSL Bundles](https://www.baeldung.com/spring-boot-security-ssl-bundles)
3. [Generating a KeyStore and TrustStore](https://docs.oracle.com/cd/E19509-01/820-3503/6nf1il6er/index.html)