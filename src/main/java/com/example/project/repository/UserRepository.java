package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project.model.User;

/**
 * This is the repository to work with the {@link User} entity.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
