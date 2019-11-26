package com.example.project.integration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.example.project.ObjectFactory;
import com.example.project.model.Sale;
import com.example.project.rest.Endpoints;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerTest {

	private static final String INVALID_ENDPOINT = Endpoints.SALE + "/invalid";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void sale() throws Exception {
		final Sale sale  = ObjectFactory.sale();
		this.mockMvc.perform(
			post(Endpoints.SALE)
				.with(basicAuth())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectFactory.toJson(sale)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id").isNotEmpty())
			.andExpect(jsonPath("$.productName").value(sale.getProductName()))
			.andExpect(jsonPath("$.quantity").value(sale.getQuantity()))
			.andExpect(jsonPath("$.timestamp").value(sale.getTimestamp()));
	}

	@Test
	public void list() throws Exception {
		this.mockMvc.perform(
			get(Endpoints.LIST)
				.with(basicAuth()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void saleNegative() throws Exception {
		this.mockMvc.perform(
			post(Endpoints.SALE)
				.with(basicAuth())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(ObjectFactory.invalidJson()))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void basicNegative() throws Exception {
		// Unauthorized
		this.mockMvc.perform(get(Endpoints.LIST))
			.andExpect(status().isUnauthorized());

		// Unauthorized
		this.mockMvc.perform(post(Endpoints.SALE))
			.andExpect(status().isUnauthorized());

		// Unauthorized over not allowed
		this.mockMvc.perform(get(Endpoints.SALE))
			.andExpect(status().isUnauthorized());

		// Unauthorized over not found
		this.mockMvc.perform(get(INVALID_ENDPOINT))
			.andExpect(status().isUnauthorized());

		// Not allowed
		this.mockMvc.perform(get(Endpoints.SALE).with(basicAuth()))
			.andExpect(status().isMethodNotAllowed());

		// Not found
		this.mockMvc.perform(get(INVALID_ENDPOINT).with(basicAuth()))
			.andExpect(status().isNotFound());
	}

	private RequestPostProcessor basicAuth() {
		return httpBasic("admin", "password");
	}
}
