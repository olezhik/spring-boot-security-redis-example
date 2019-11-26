package com.example.project.service;

import java.util.List;

import com.example.project.model.Sale;

/**
 * This class defines the interface to work with the {@link Sale} model.
 */
public interface SaleService {

	/**
	 * Retrieves all the sales.
	 *
	 * @return A list of sales.
	 */
	List<Sale> findAll();

	/**
	 * Saves the input sale.
	 *
	 * @param sale {@link Sale}
	 * @return The saved sale entity.
	 */
	Sale saveOrUpdate(Sale sale);
}
