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


    // Ex: buy 2 get 3
    public static BigDecimal applyPromotionFreeProduct(Product gProduct, FreeProductPromotion freeProductPromotion) {
        if (freeProductPromotion.getProduct().getCodeBarProdcut() == gProduct.getCodeBarProdcut()) {
            int rapport = gProduct.getQuantity() / freeProductPromotion.getNumberMin();
            return new BigDecimal(rapport).multiply(gProduct.getPrice());
        }
        return BigDecimal.ZERO;
    }


    // X unité d'un produit ,avec Y euro.
    public static BigDecimal applyPromotionXProductForYPrice(Product product, BuyXforYpricePromotion buyXforYpricePromotion) {
        int N = product.getQuantity() / buyXforYpricePromotion.getNumberOfProduct();
        return new BigDecimal(N)
                .multiply(product.getPrice()
                        .multiply(new BigDecimal(buyXforYpricePromotion.getNumberOfProduct()))
                        .subtract(buyXforYpricePromotion.getPrice()));


    }

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


    public static BigDecimal calculerTotalSansReductionUnit(Product product) {
        return product.getPrice().multiply(new BigDecimal(product.getQuantity()));
    }

    public static BigDecimal calculerMontantGroupProduit(Product product) {
        return product.getWeight().multiply(product.getPrice());
    }
}
