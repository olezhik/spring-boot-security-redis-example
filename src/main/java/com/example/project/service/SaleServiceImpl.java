package com.example.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.Sale;
import com.example.project.repository.SaleRepository;

/**
 * This class provides functionality to work with the {@link Sale} model.
 */
@Service
public class SaleServiceImpl implements SaleService {

	private SaleRepository repository;

	@Autowired
	public SaleServiceImpl(SaleRepository repository) {
		this.repository = repository;
	}


	@Override
	public List<Sale> findAll() {
		List<Sale> sales = new ArrayList<>();
		repository.findAll().forEach(sales::add);
		return sales;
	}

	@Override
	public Sale saveOrUpdate(final Sale sale) {
		return repository.save(sale);
	}
}
