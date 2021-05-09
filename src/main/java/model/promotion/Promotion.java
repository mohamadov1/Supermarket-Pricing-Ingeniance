package model.promotion;

import lombok.*;
import model.Product;

@Setter
@Getter
@AllArgsConstructor
public abstract class Promotion {
	private Product product;
}
