package model.promotion;

import model.Product;

public class FreeProductPromotion extends Promotion {

	private int numberMin;

	public FreeProductPromotion(Product product, int numberMin) {
		super(product);
		this.numberMin = numberMin;
	}

	public int getNumberMin() {
		return numberMin;
	}
	

}
