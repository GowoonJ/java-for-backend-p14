package kr.co.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import kr.co.ordermanagement.domain.product.Product;

@SpringBootApplication
public class OrdermanagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrdermanagementApplication.class, args);
	}
}
