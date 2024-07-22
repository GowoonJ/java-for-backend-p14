package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
	private Long id;
	private String name;
	private Integer price;
	private Integer amount;

	public static ProductDto toDto(Product product) {
		return new ProductDto(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getAmount()
		);
	}

}