package kr.co.ordermanagement.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderedProduct {

	private Long id;

	private String name;

	private Integer price;

	private Integer amount;
}
