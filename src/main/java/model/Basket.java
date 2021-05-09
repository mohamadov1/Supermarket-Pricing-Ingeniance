package model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<Product> products = new ArrayList<>();

	public List<Product> getProducts() {
		return products;
	}

	public void addProduct(Product product, int i) {
		product.setQuantity(i);
		products.add(product);
	}
	public void addProductWithWeight(Product product, BigDecimal weight, String unit) {
		product.setWeight(weight);
		product.setUnit(unit);
		products.add(product);
	}

}
