package com.example.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;

/**
 * A custom user details service implementation to supply user credentials from the storage.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException { //NOSONAR
		Optional<User> optional = repository.findById(username);
		if (optional.isPresent()) {
			User user  = optional.get();
			return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getAuthority())
				.build();
		}

		throw new UsernameNotFoundException("Username not found");
	}
}
