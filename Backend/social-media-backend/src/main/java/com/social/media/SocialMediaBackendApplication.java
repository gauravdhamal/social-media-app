package com.social.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Social Media API", version = "1.0"))
public class SocialMediaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaBackendApplication.class, args);
	}

}
