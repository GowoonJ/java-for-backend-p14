package kr.co.ordermanagement.application;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderJpaRepository;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductJpaRepository;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SimpleOrderService {

	private final ProductJpaRepository productJpaRepository;
	private final OrderJpaRepository orderJpaRepository;

	public OrderResponseDto createOrder(List<OrderProductRequestDto> orderProductRequestDtos) {
		List<Product> orderedProducts = makeOrderedProducts(orderProductRequestDtos);
		decreaseProductsAmount(orderedProducts);

		Order order = new Order(orderedProducts);
		orderJpaRepository.save(order);

		return OrderResponseDto.toDto(order);
	}

	public OrderResponseDto findById(Long orderId) {
		Order order = orderJpaRepository.findById(orderId)
			.orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다. : " + orderId));

		return OrderResponseDto.toDto(order);
	}

	/**
	 * 주문 상태 변경
	 *
	 * @param orderId
	 * @param changeStateRequestDto
	 * @return
	 */
	public OrderResponseDto changeState(Long orderId, ChangeStateRequestDto changeStateRequestDto) {
		Order order = orderJpaRepository.findById(orderId)
			.orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다. : " + orderId));

		State state = changeStateRequestDto.getState();
		order.changeStateForce(state);
		orderJpaRepository.save(order);

		return OrderResponseDto.toDto(order);
	}

	public List<OrderResponseDto> findByState(State state) {
		List<Order> orders = orderJpaRepository.findByState(state);

		return orders.stream()
			.map(OrderResponseDto::toDto)
			.toList();
	}

	public OrderResponseDto cancelOrderById(Long orderId) {
		Order order = orderJpaRepository.findById(orderId)
			.orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다. : " + orderId));

		order.cancel();
		orderJpaRepository.save(order);

		return OrderResponseDto.toDto(order);
	}

	private List<Product> makeOrderedProducts(List<OrderProductRequestDto> orderProductRequestDtos) {
		return orderProductRequestDtos
			.stream()
			.map(orderProductRequestDto -> {
				Long productId = orderProductRequestDto.getId();
				Product product = productJpaRepository.findById(productId).orElseThrow(
					() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다. : " + productId)
				);

				Integer orderedAmount = orderProductRequestDto.getAmount();
				product.checkEnoughAmount(orderedAmount);

				return new Product(
					productId,
					product.getName(),
					product.getPrice(),
					orderProductRequestDto.getAmount()
				);
			}).toList();
	}

	private void decreaseProductsAmount(List<Product> orderedProducts) {
		orderedProducts
			.forEach(orderedProduct -> {
				Long productId = orderedProduct.getId();
				Product product = productJpaRepository.findById(productId).orElseThrow(
					() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다. : " + productId)
				);

				Integer orderedAmount = orderedProduct.getAmount();
				product.decreaseAmount(orderedAmount);
			});
	}
}
