package com.example.project.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project.Properties;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private Properties properties;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository repository;

	@Test
	public void findById() {
		Optional<User> optional = repository.findById(properties.getUsername());

		assertTrue(optional.isPresent());
		User user = optional.get();

		assertTrue(encoder.matches(properties.getPassword(), user.getPassword()));
		assertEquals(user.getAuthority(), properties.getAuthority());
	}
}
