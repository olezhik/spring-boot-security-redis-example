package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.project.ObjectFactory;
import com.example.project.model.Sale;
import com.example.project.repository.SaleRepository;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceImplTest {

	@Mock
	private SaleRepository repository;

	@InjectMocks
	private SaleServiceImpl service;

	@Test
	public void findAll() {
		Sale original = ObjectFactory.sale();
		when(repository.findAll()).thenReturn(Arrays.asList(original));

		List<Sale> sales = service.findAll();

		assertTrue(sales.size() == 1);
		assertEquals(sales.get(0), original);
		verify(repository, times(1)).findAll();
	}

	@Test
	public void saveOrUpdate() {
		final String id = "someId";
		Sale original = ObjectFactory.sale();
		when(repository.save(original)).thenAnswer(invocationOnMock -> {
			original.setId(id);
			return original;
		});

		Sale saved = service.saveOrUpdate(original);

		assertEquals(saved.getId(), id);
		verify(repository, times(1)).save(original);
	}
}
