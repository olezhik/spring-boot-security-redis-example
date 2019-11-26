package com.example.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;

/**
 * Redis server configuration. An embedded Redis server starts and a default user is added.
 */
@Configuration
public class RedisConfiguration {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private Properties properties;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		return new JedisConnectionFactory();
	}

	@PostConstruct
	public void createDefaultUser() {
		userRepository.save(User.builder()
			.username(properties.getUsername())
			.password(encoder.encode(properties.getPassword()))
			.authority(properties.getAuthority())
			.build());
	}
}
