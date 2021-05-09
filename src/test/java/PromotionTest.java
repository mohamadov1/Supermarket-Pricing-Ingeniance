import model.Basket;
import model.Product;
import model.promotion.BuyXforYpricePromotion;
import model.promotion.FreeProductPromotion;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;


public class PromotionTest {

	private Product pizza;
	private Product yogurt;
	private Product coca;

	@Before
	public void init() {
		pizza  = new Product(new BigDecimal("1.5"),1);
		yogurt = new Product(new BigDecimal("1.0"),2);
		coca   = new Product(new BigDecimal("2.5"),3);
	  }

	
	@Test
	public void testBasketWithFreeProductPromotion() {
		Checkout checkout = new Checkout();
		checkout.addPromotion(new FreeProductPromotion(pizza,3));
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,1);
		BigDecimal total = checkout.calculateTheBasketTotal(basket, checkout.getPromotions());
		org.junit.Assert.assertEquals(new BigDecimal("23.00"), total.setScale(2));

	}
	
	@Test
	public void testBasketThreeProductWithFreeProdcutPromotion() {
		Checkout checkout = new Checkout();
		checkout.addPromotion(new FreeProductPromotion(pizza,3));
		checkout.addPromotion(new FreeProductPromotion(yogurt,3));
		checkout.addPromotion(new FreeProductPromotion(coca,3));
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,10);
		BigDecimal total = checkout.calculateTheBasketTotal(basket, checkout.getPromotions());
		org.junit.Assert.assertEquals(new BigDecimal("35.00"), total.setScale(2));
	}
	
	@Test
	public void testBasketWithBuyXforYPricePromotion() {
		Checkout checkout = new Checkout();
		checkout.addPromotion(new BuyXforYpricePromotion(yogurt,new BigDecimal("2.5") , 3));
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,1);
		BigDecimal total = checkout.calculateTheBasketTotal(basket, checkout.getPromotions());
		org.junit.Assert.assertEquals(new BigDecimal("26.00"), total.setScale(2));

	}


	@Test
	public void testBasketWithMultipleBuyXforYpricePromotion() {
		Checkout checkout = new Checkout();
		checkout.addPromotion(new BuyXforYpricePromotion(yogurt,new BigDecimal("2.5") , 3));
		checkout.addPromotion(new BuyXforYpricePromotion(pizza,new BigDecimal("5.5") , 4));
		checkout.addPromotion(new BuyXforYpricePromotion(coca,new BigDecimal("4") , 2));
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,10);
		BigDecimal total = checkout.calculateTheBasketTotal(basket, checkout.getPromotions());
		org.junit.Assert.assertEquals(new BigDecimal("42.50"), total.setScale(2));

	}
	
	@Test
	public void testBasketWithTwoPromotionType() {
		Checkout checkout = new Checkout();
		checkout.addPromotion(new BuyXforYpricePromotion(yogurt,new BigDecimal("2.5") , 3));
		checkout.addPromotion(new FreeProductPromotion(pizza,3));
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,1);
		BigDecimal total = checkout.calculateTheBasketTotal(basket, checkout.getPromotions());
		org.junit.Assert.assertEquals(new BigDecimal("21.50"), total.setScale(2));

	}
}
