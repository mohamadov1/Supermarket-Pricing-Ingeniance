import model.Basket;
import model.promotion.Promotion;
import utils.PromotionCalculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checkout {
	
	
	private List<Promotion> promotions = new ArrayList<>();


	public BigDecimal calculateTheBasketTotal(Basket basket, List<Promotion> promotions) {
		Optional<Basket> basketOptional = Optional.ofNullable(basket);
		if (basketOptional.isPresent()) {

			BigDecimal totalNormalByQuanityProducts = basket.getProducts().stream()
					.filter(g -> g.getWeight() == null)
					.map(g -> PromotionCalculate.calculerTotalSansReductionUnit(g))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal totalNormalByUnitProduct = basket.getProducts().stream()
					.filter(g -> g.getWeight() != null)
					.map(g -> PromotionCalculate.calculerMontantGroupProduit(g))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal TotalNoramlWithoutAnyPromotion = totalNormalByQuanityProducts.add(
					totalNormalByUnitProduct);

			BigDecimal calculeAmoutToEarnWithPromotions = basket.getProducts().stream()
					.map(g -> PromotionCalculate.applyMultiplePromotions(promotions, g))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			return TotalNoramlWithoutAnyPromotion.subtract(calculeAmoutToEarnWithPromotions);

		}
		return BigDecimal.ZERO;
	}
	
	public void addPromotion(Promotion promotion) {
		promotions.add(promotion);

	}
	public List<Promotion> getPromotions() {
		return promotions;
	}

}
