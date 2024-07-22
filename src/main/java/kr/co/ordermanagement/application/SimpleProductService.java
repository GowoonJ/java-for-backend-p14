package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.ProductDto;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleProductService {

	private ProductRepository productRepository;

	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return products.stream()
			.map(product -> ProductDto.toDto(product))
			.toList();
	}
}
