import model.Basket;
import model.Product;
import org.junit.Before;
import org.junit.Test;
import utils.Weight;

import java.math.BigDecimal;


public class CheckoutTest {

	private Product pizza;
	private Product yogurt;
	private Product coca;
	private Product apple;
	private Product almonds;
	private Product nuts;
	private Product potato;


	
	@Before
	public void init() {
		pizza  = new Product(new BigDecimal("1.5"),1);
		yogurt = new Product(new BigDecimal("1.0"),2);
		coca   = new Product(new BigDecimal("2.5"),3);
		apple  = new Product(new BigDecimal("3.0"), Weight.KILOGRAMME.getValue(),4);
		almonds= new Product(new BigDecimal("0.5"), Weight.GRAMME.getValue(),5);
		nuts   = new Product(new BigDecimal("0.6"), Weight.GRAMME.getValue(),5);
		potato   = new Product(new BigDecimal("1.5"), Weight.POUND.getValue(),5);

	  }

	@Test
	public void testEmptyBasket() {
		Checkout checkout = new Checkout();
		Basket basket = new Basket();
		BigDecimal total = checkout.calculateTheTotalBasket(basket,null);
		org.junit.Assert.assertEquals(new BigDecimal("0.00"), total.setScale(2));
	}
	
	@Test
	public void testBasketWithTwoProduct() {
		Checkout checkout = new Checkout();
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		BigDecimal total = checkout.calculateTheTotalBasket(basket,null);
		org.junit.Assert.assertEquals(new BigDecimal("25.00"), total.setScale(2));	
	}
	
	@Test
	public void testBasketWihtThreeProduct() {
		Checkout checkout = new Checkout();
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,1);
		BigDecimal total = checkout.calculateTheTotalBasket(basket,null);
		org.junit.Assert.assertEquals(new BigDecimal("27.50"), total.setScale(2));	
	}

	

	@Test
	public void testBaksetWithKilogrammeProduct() {
		Checkout checkout = new Checkout();
		Basket basket = new Basket();
		basket.addProductWithWeight(apple,new BigDecimal("1.50"), Weight.KILOGRAMME.getValue());
		BigDecimal total = checkout.calculateTheTotalBasket(basket,null);
		org.junit.Assert.assertEquals(new BigDecimal("4.50"), total.setScale(2));
	}
	
	@Test
	public void testBasketMultipeProductType() {
		Checkout checkout = new Checkout();
		Basket basket = new Basket();
		basket.addProduct(pizza,10);
		basket.addProduct(yogurt,10);
		basket.addProduct(coca,1);
		basket.addProductWithWeight(apple,new BigDecimal("1.50"), Weight.KILOGRAMME.getValue());
		BigDecimal total = checkout.calculateTheTotalBasket(basket,null);
		org.junit.Assert.assertEquals(new BigDecimal("32.00"), total.setScale(2));
	}


	@Test
	public void testBasketMultipleTypeOfWeight() {
		Checkout checkout = new Checkout();
		Basket basket = new Basket();
		basket.addProduct(nuts,50);
		basket.addProduct(almonds,10);
		basket.addProduct(potato,1);
		BigDecimal total = checkout.calculateTheTotalBasket(basket,null);
		org.junit.Assert.assertEquals(new BigDecimal("36.50"), total.setScale(2));
	}
	
}
