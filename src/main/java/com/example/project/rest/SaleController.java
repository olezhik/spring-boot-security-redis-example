package com.example.project.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Sale;
import com.example.project.service.SaleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * This controller provides endpoints to work with the {@link Sale} entity.
 */
@RestController
@Api(tags = {"sale-controller"})
@SwaggerDefinition(tags = {
	@Tag(name = "sale-controller", description = "Provides functionality to work with Sales.")
})
public class SaleController {

	private SaleService service;

	@Autowired
	public SaleController(SaleService service) {
		this.service = service;
	}

	@ApiOperation(value = "Get all the sales from the storage.", response = ResponseEntity.class)
	@GetMapping(value = {Endpoints.LIST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sale>> getAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a sale to the storage. Returns the added sale details.", response = ResponseEntity.class)
	@PostMapping(value = {Endpoints.SALE}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sale> add(@Valid @RequestBody Sale sale) {
		return new ResponseEntity<>(service.saveOrUpdate(sale), HttpStatus.OK);
	}
}
