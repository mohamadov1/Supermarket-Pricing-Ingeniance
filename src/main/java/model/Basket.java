package model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Basket {

	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product, int quantity) {
		product.setQuantity(quantity);
		products.add(product);
	}
	public void addProductWithWeight(Product product, BigDecimal weight, String unit) {
		product.setWeight(weight);
		product.setUnit(unit);
		products.add(product);
	}


}
