package model.promotion;

import lombok.Getter;
import model.Product;

@Getter
public class FreeProductPromotion extends Promotion {

	private int numberMin;

	public FreeProductPromotion(Product product, int numberMin) {
		super(product);
		this.numberMin = numberMin;
	}

}
