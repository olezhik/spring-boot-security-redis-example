package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.project.ObjectFactory;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserDetailsServiceImpl service;

	@Test
	public void loadUserByUsername() {
		User user = ObjectFactory.user();
		when(repository.findById(user.getUsername())).thenReturn(Optional.of(user));

		UserDetails details = service.loadUserByUsername(user.getUsername());

		assertEquals(details.getUsername(), user.getUsername());
		assertEquals(details.getPassword(), user.getPassword());
		verify(repository, times(1)).findById(user.getUsername());
	}

}
