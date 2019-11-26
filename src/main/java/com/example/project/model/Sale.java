package com.example.project.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class defines sale model.
 */
@Data
@NoArgsConstructor
@RedisHash("Sale")
public class Sale implements Serializable {

	@Id
	@ApiModelProperty(notes = "An auto-generated sale Id")
	private String id;

	/** The name of the product that was sold */
	@ApiModelProperty(notes = "Sale product name")
	private String productName;

	/** Quantity of the sold product */
	@ApiModelProperty(notes = "Sale product quantity")
	private Integer quantity;

	/** Timestamp of the sale */
	@ApiModelProperty(notes = "Timestamp of the sale(Unix), ex. `1574787013`")
	private Long timestamp;
}
