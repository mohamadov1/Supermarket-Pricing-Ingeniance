package model.promotion;

import lombok.Getter;
import model.Product;

import java.math.BigDecimal;

@Getter
public class BuyXforYpricePromotion extends Promotion {

	private BigDecimal price;
	private int numberOfProduct;

	public BuyXforYpricePromotion(Product product, BigDecimal price, int numberOfProduct) {
		super(product);
		this.price = price;
		this.numberOfProduct = numberOfProduct;
	}
}
