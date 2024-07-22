package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderRestController {

	private SimpleOrderService simpleOrderService;

	/**
	 * 상품 주문 API
	 *
	 * @param orderProductRequestDtos
	 * @return
	 */
	@PostMapping(value = "/orders")
	public ResponseEntity<OrderResponseDto> createOrder(
		@RequestBody List<OrderProductRequestDto> orderProductRequestDtos) {
		OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDtos);

		return ResponseEntity.ok(orderResponseDto);
	}

	/**
	 * 주문번호로 조회 API
	 *
	 * @param orderId
	 * @return
	 */
	@GetMapping(value = "/orders/{orderId}")
	public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
		OrderResponseDto orderResponseDto = simpleOrderService.findById(orderId);

		return ResponseEntity.ok(orderResponseDto);
	}

	/**
	 * 주문상태 강제 변경 API
	 *
	 * @param orderId
	 * @param changeStateRequestDto
	 * @return
	 */
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PATCH)
	public ResponseEntity<OrderResponseDto> changeOrderState(
		@PathVariable Long orderId,
		@RequestBody ChangeStateRequestDto changeStateRequestDto
	) {
		OrderResponseDto orderResponseDto = simpleOrderService.changeState(orderId, changeStateRequestDto);

		return ResponseEntity.ok(orderResponseDto);
	}

	/**
	 * 주문상태로 조회 API
	 *
	 * @param state
	 * @return
	 */
	@GetMapping(value = "/orders")
	public ResponseEntity<List<OrderResponseDto>> getOrdersByState(@RequestParam State state) {
		List<OrderResponseDto> orderResponseDtos = simpleOrderService.findByState(state);

		return ResponseEntity.ok(orderResponseDtos);
	}

	/**
	 * 주문 취소 API
	 *
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.PATCH)
	public ResponseEntity<OrderResponseDto> cancelOrderById(@PathVariable Long orderId) {
		OrderResponseDto orderResponseDto = simpleOrderService.cancelOrderById(orderId);

		return ResponseEntity.ok(orderResponseDto);
	}

}
