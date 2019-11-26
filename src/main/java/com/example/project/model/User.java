package com.example.project.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

/**
 * This class defines user data to access the API.
 */
@Builder
@Data
@RedisHash("User")
public class User implements Serializable {
	@Id
	private String username;
	private String password;
	private String authority;
}
