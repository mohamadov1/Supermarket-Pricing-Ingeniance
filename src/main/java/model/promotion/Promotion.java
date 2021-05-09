package model.promotion;

import model.Product;

public abstract class Promotion {

	private Product product;

	public Promotion(Product produit) {

		this.product = produit;
	}

	public Product getProduct() {
		return product;
	}


	
}
