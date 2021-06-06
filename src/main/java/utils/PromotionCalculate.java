package utils;

import model.Product;
import model.promotion.BuyXforYpricePromotion;
import model.promotion.FreeProductPromotion;
import model.promotion.Promotion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public final class PromotionCalculate {


    /**
     *
     * @param product Product on which the promotion can be applied
     * @param freeProductPromotion The promotional object to have if the product is eligible
     * @return Amount to reduce
     * Example : Buy 2 champions get 1 FREE
     */
    public static BigDecimal applyPromotionFreeProduct(Product product, FreeProductPromotion freeProductPromotion) {
        if (freeProductPromotion.getProduct().getCodeBarProdcut() == product.getCodeBarProdcut()) {
            int rapport = product.getQuantity() / freeProductPromotion.getNumberMin();
            return new BigDecimal(rapport).multiply(product.getPrice());
        }
        return BigDecimal.ZERO;
    }


    /**
     *
     * @param product Product on which the promotion can be applied
     * @param buyXforYpricePromotion
     * @return Amount to reduce
     * Example : Champion price = 15 euros => Buy 2 champions for 25 euros
     */
    public static BigDecimal applyPromotionXProductForYPrice(Product product, BuyXforYpricePromotion buyXforYpricePromotion) {
        int N = product.getQuantity() / buyXforYpricePromotion.getNumberOfProduct();
        return new BigDecimal(N)
                .multiply(product.getPrice()
                        .multiply(new BigDecimal(buyXforYpricePromotion.getNumberOfProduct()))
                        .subtract(buyXforYpricePromotion.getPrice()));


    }


    /**
     *
     * @param promotions The list of promotions that can be applied to the product
     * @param product
     * @return total Discount Amount
     */
    public static BigDecimal applyMultiplePromotions(List<Promotion> promotions, Product product) {
        final AtomicReference<BigDecimal> totalDiscountAmount = new AtomicReference<>(new BigDecimal(0));
        Optional<List<Promotion>> optionalPromotions = Optional.ofNullable(promotions);
        if (optionalPromotions.isPresent()) {
            promotions.forEach(promotion ->
                    {
                        if (promotion instanceof FreeProductPromotion) {
                            FreeProductPromotion freeProductPromotion = (FreeProductPromotion) promotion;
                            BigDecimal discount = PromotionCalculate.applyPromotionFreeProduct(product, freeProductPromotion);
                            totalDiscountAmount.set(totalDiscountAmount.get().add(discount));

                        } else if (promotion instanceof BuyXforYpricePromotion) {
                            BuyXforYpricePromotion buyXforYpricePromotion = (BuyXforYpricePromotion) promotion;
                            if (buyXforYpricePromotion.getProduct().getCodeBarProdcut() == product.getCodeBarProdcut()) {
                                BigDecimal discount = PromotionCalculate.applyPromotionXProductForYPrice(product, buyXforYpricePromotion);
                                totalDiscountAmount.set(totalDiscountAmount.get().add(discount));
                            }
                        }
                    }
            );
        }

        return totalDiscountAmount.get();
    }


    public static BigDecimal totalUniteProduct(Product product) {
        return product.getPrice().multiply(new BigDecimal(product.getQuantity()));
    }

    public static BigDecimal totalWeightProduct(Product product) {
        return product.getWeight().multiply(product.getPrice());
    }
}
