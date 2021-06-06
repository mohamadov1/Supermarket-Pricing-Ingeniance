import lombok.Getter;
import model.Basket;
import model.promotion.Promotion;
import utils.PromotionCalculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Checkout {
	
	
	private List<Promotion> promotions = new ArrayList<>();

	public void addPromotion(Promotion promotion) {
		promotions.add(promotion);

	}

	/**
	 *
	 * @param basket  Basket with all types of products
	 * @param promotions All possible promotions on products
	 * @return  Amount to be paid
	 */
	public BigDecimal calculateTheTotalBasket(Basket basket, List<Promotion> promotions) {
		Optional<Basket> basketOptional = Optional.ofNullable(basket);
		if (basketOptional.isPresent()) {

			// Calculate Total amount of unit products
			BigDecimal totalNormalByQuanityProducts = basket.getProducts().stream()
					.filter(g -> g.getWeight() == null)
					.map(g -> PromotionCalculate.totalUniteProduct(g))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			// Calculate Total Amount of Weight Products
			BigDecimal totalNormalByUnitProduct = basket.getProducts().stream()
					.filter(g -> g.getWeight() != null)
					.map(g -> PromotionCalculate.totalWeightProduct(g))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			// Total of all products without reduction
			BigDecimal TotalNormalWithoutAnyPromotion = totalNormalByQuanityProducts.add(
					totalNormalByUnitProduct);

			// The sum of the discount to be applied
			BigDecimal calculeAmoutToEarnWithPromotions = basket.getProducts().stream()
					.map(g -> PromotionCalculate.applyMultiplePromotions(promotions, g))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			// The amount to pay
			return TotalNormalWithoutAnyPromotion.subtract(calculeAmoutToEarnWithPromotions);

		}

		// Empty basket
		return BigDecimal.ZERO;
	}

}
