package kr.co.ordermanagement.domain.order;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.ordermanagement.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	@Id
	@Setter
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "total_price")
	private Integer totalPrice;

	@Column(name = "state")
	private State state;

	@OneToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Product> orderedProducts = new ArrayList<>();

	public Order(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
		this.totalPrice = calculateTotalPrice(orderedProducts);
		this.state = State.CREATED;
	}

	public Boolean sameId(Long id) {
		return this.id.equals(id);
	}

	public Boolean sameState(State state) {
		return this.state.equals(state);
	}

	public void changeStateForce(State state) {
		this.state = state;
	}

	public void cancel() {
		this.state.checkCancellable();
		this.state = State.CANCELED;
	}

	private Integer calculateTotalPrice(List<Product> orderedProducts) {
		return orderedProducts
			.stream()
			.mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
			.sum();
	}
}
