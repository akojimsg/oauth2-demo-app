## Implementation of OAuth2 with Spring Boot

### Tools

- Gradle
- Java JDK v21.0+
- Spring Boot
- Spring CLI

### Test Grant flow

```
# Get grant token

curl --location --request GET \
'http://localhost:9000/oauth2/authorize?response_type=code&client_id=messaging-client&scope=message.read message.write user.read&redirect_uri=http://127.0.0.1:8080/authorized' \
--header 'Cookie: JSESSIONID=67C7723DE672D58F7478EF8047B0773A'
```

```
# Get Auth Token

curl --location --request POST 'http://localhost:9000/oauth2/token' \
--header 'Authorization: Basic bWVzc2FnaW5nLWNsaWVudDpzZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=67C7723DE672D58F7478EF8047B0773A' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'code=3luC55VMTD2lw7sljiXZkPvRDmWQX0C4wBnjrdCEGr09KrraEaPKYT1ilMb728hINb_JWU9-qCF1ucvvrE2WtRXx48vuablSf3pyW_OeCq0q3ehwQhWAlg-xM7o0gHFS' \
--data-urlencode 'redirect_uri=http://127.0.0.1:8080/authorized' | jq
```

### Snippets

```
# Create cert.pem
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -passout pass:changeit

# Create keystore
openssl pkcs12 -export -in cert.pem -inkey key.pem -out keystore.p12 -name oauth2-server-certificate -passin pass:changeit -passout pass:changeit

# Create truststore
keytool -import -noprompt -file cert.pem -alias oauth2-server-certificate -keystore truststore -storepass changeit

# List keystore
keytool -list -keystore keystore.p12

```

### Resources

1. [Spring Authorization Server](https://docs.spring.io/spring-authorization-server/reference/overview.html)
2. [Applications With SSL Bundles](https://www.baeldung.com/spring-boot-security-ssl-bundles)
3. [Generating a KeyStore and TrustStore](https://docs.oracle.com/cd/E19509-01/820-3503/6nf1il6er/index.html)