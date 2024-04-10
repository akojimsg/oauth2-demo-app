package com.oauth2demo.server;

import com.oauth2demo.server.web.AuthorizationConsentController;
import org.thymeleaf.expression.Lists;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.Arrays;

@SpringBootApplication
@ImportRuntimeHints(OAuth2ServerApplication.OAuth2ServerApplicationRuntimeHintsRegistrar.class)
public class OAuth2ServerApplication {
	static class OAuth2ServerApplicationRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
		@Override
		public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
			hints.reflection().registerTypes(
					Arrays.asList(
							TypeReference.of(AuthorizationConsentController.ScopeWithDescription.class),
							TypeReference.of(Lists.class)
					), builder -> builder.withMembers(MemberCategory.DECLARED_FIELDS,
							MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS)
			);
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(OAuth2ServerApplication.class, args);
	}

}
