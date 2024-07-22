package kr.co.ordermanagement.application;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductJpaRepository;
import kr.co.ordermanagement.presentation.dto.ProductDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SimpleProductService {

	private ProductJpaRepository productJpaRepository;

	public List<ProductDto> findAll() {
		List<Product> products = productJpaRepository.findAll();
		return products.stream()
			.map(ProductDto::toDto)
			.toList();
	}

	@PostConstruct
	void initProducts() {
		final List<Product> products = new CopyOnWriteArrayList<>();

		products.add(new Product(1L, "상품1", 10000, 100));
		products.add(new Product(2L, "상품2", 25000, 300));
		products.add(new Product(3L, "상품3", 30000, 500));

		productJpaRepository.saveAll(products);
	}
}
