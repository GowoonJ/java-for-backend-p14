package kr.co.ordermanagement.presentation.dto;

import java.util.List;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
	private Long id;
	private List<OrderedProductDto> orderedProducts;
	private Integer totalPrice;
	private State state;

	public static OrderResponseDto toDto(Order order) {
		List<OrderedProductDto> orderedProductDtos = order.getOrderedProducts()
			.stream()
			.map(orderedProduct -> OrderedProductDto.toDto(orderedProduct))
			.toList();

		return new OrderResponseDto(
			order.getId(),
			orderedProductDtos,
			order.getTotalPrice(),
			order.getState()
		);
	}
}
