package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderedProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public static OrderedProductDto toDto(Product orderedProduct) {
        return new OrderedProductDto(
                orderedProduct.getId(),
                orderedProduct.getName(),
                orderedProduct.getPrice(),
                orderedProduct.getAmount()
        );
    }
}
