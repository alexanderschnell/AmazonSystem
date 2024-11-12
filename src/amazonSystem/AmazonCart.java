package amazonSystem;

import java.util.ArrayList;
import java.util.Date;

public class AmazonCart implements AmazonPayable {
	
	private AmazonCustomer customer;
	private Date date;
	private ArrayList<AmazonCartItem> cartItems = new ArrayList<AmazonCartItem>();
	private float orderValue;
	
	public AmazonCart(AmazonCustomer customer, Date date) {
		this.setCustomer(customer);
		this.setDate(date);
		
	}
	
	//TODO ALL METHODS
	
	public float calcSubTotal() {
		return 0; //FOR NOW
	}
	
	public int getItem(AmazonCartItem cartItem) {
		return getItem(cartItem);
	}
	
	public boolean hasItem(AmazonProduct product) {
		return hasItem(product);
	}
	
	public boolean pay(AmazonCredit amount) {
		return pay(amount);
	}
	
	public void addItem(AmazonCartItem cartItem) {
		
	}
	
	public void removeItem(AmazonProduct product) {
		
	}
	
	public String getCartDetails() {
		return ""; //FOR NOW
	}
	
	public String toString() {
		return ""; //FOR NOW
	}
	
	
	//GETTERS & SETTERS
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AmazonCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(AmazonCustomer customer) {
		this.customer = customer;
	}

	public ArrayList<AmazonCartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(ArrayList<AmazonCartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public float getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(float orderValue) {
		this.orderValue = orderValue;
	}
	

}
