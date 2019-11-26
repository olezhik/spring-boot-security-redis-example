package com.example.project;

import java.util.Date;

import com.example.project.model.Sale;
import com.example.project.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectFactory {

	public static Sale sale() {
		final Sale sale = new Sale();
		sale.setProductName("Some product name");
		sale.setQuantity(5);
		sale.setTimestamp(new Date().getTime());
		return sale;
	}

	public static User user() {
		return User.builder()
			.username("steve")
			.password("nash")
			.authority("guard")
			.build();
	}

	public static String invalidJson() {
		return "{\"productName\":\"Some product\",}"; // with a comma
	}

	public static String toJson(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
