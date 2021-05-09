package model.promotion;

import model.Product;

import java.math.BigDecimal;

public class BuyXforYpricePromotion extends Promotion {

	private BigDecimal price;
	
	private int numberOfProduct;

	public BigDecimal getPrice() {
		return price;
	}


	public int getNumberOfProduct() {
		return numberOfProduct;
	}

	public BuyXforYpricePromotion(Product product, BigDecimal price, int numberOfProduct) {
		super(product);
		this.price = price;
		this.numberOfProduct = numberOfProduct;
	}
	
	

}
