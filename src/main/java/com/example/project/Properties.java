package com.example.project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * This is a container class to keep the application properties.
 * It's loaded during the context initialization from the `application.properties` file.
 */
@Getter
@Component
public class Properties {

	@Value("${user.username}")
	private String username;

	@Value("${user.password}")
	private String password;

	@Value("${user.authority}")
	private String authority;
}
