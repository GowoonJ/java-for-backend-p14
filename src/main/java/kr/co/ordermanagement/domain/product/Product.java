package kr.co.ordermanagement.domain.product;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.co.ordermanagement.domain.exception.NotEnoughAmountException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Integer price;

	@Column(name = "amount")
	private Integer amount;

	public Boolean sameId(Long id) {
		return this.id.equals(id);
	}

	public void checkEnoughAmount(Integer orderedAmount) {
		if (this.amount < orderedAmount)
			throw new NotEnoughAmountException(this.id + "번 상품의 수량이 부족합니다.");
	}

	public void decreaseAmount(Integer orderedAmount) {
		this.amount = this.amount - orderedAmount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product)o;
		return Objects.equals(id, product.id);
	}
}
