package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project.model.Sale;

/**
 * This is the repository to work with the {@link Sale} entity.
 */
public interface SaleRepository extends CrudRepository<Sale, String> {
}
