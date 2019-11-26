package com.example.project.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project.ObjectFactory;
import com.example.project.model.Sale;
import com.example.project.repository.SaleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleRepositoryTest {

	@Autowired
	private SaleRepository repository;

	@Test
	public void saveAndFindAll() {
		Sale expected = ObjectFactory.sale();
		String targetId = repository.save(expected).getId();
		Iterable<Sale> sales = repository.findAll();

		Optional<Sale> optional = findSaleById(sales, targetId);
		assertTrue(optional.isPresent());
		assertSale(optional.get(), expected);
	}

	@Test
	public void saveAndFindById() {
		Sale actual = ObjectFactory.sale();
		Optional<Sale> optional = repository
			.findById(repository.save(actual).getId());

		assertTrue(optional.isPresent());
		assertSale(actual, optional.get());
	}

	private Optional<Sale> findSaleById(Iterable<Sale> sales, String targetId) {
		return StreamSupport
			.stream(sales.spliterator(), false)
			.filter(sale -> targetId.equals(sale.getId()))
			.findFirst();
	}

	private void assertSale(Sale actual, Sale expected) {
		assertEquals(actual.getProductName(), expected.getProductName());
		assertEquals(actual.getQuantity(), expected.getQuantity());
		assertEquals(actual.getTimestamp(), expected.getTimestamp());
	}
}
