package kr.co.ordermanagement.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ordermanagement.application.SimpleProductService;
import kr.co.ordermanagement.presentation.dto.ProductDto;

@RestController
public class ProductRestController {

	private SimpleProductService simpleProductService;

	@Autowired
	ProductRestController(SimpleProductService simpleProductService) {
		this.simpleProductService = simpleProductService;
	}

	@GetMapping(value = "/products")
	public List<ProductDto> findProducts() {
		return simpleProductService.findAll();
	}

}
