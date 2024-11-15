package amazonsystem;

import java.util.ArrayList;
import java.util.Date;

//TODO
//START IMPLEMENTING NEW METHODS 

public class AmazonCart implements AmazonPayable {
	
	private AmazonCustomer customer;
	private Date date;
	private ArrayList<AmazonCartItem> cartItems = new ArrayList<AmazonCartItem>();
	private float orderValue;
	
	public AmazonCart(AmazonCustomer customer, Date date) {
		this.setCustomer(customer);
		this.setDate(date);
		
	}
	
	public void addItem(AmazonProduct product, int quantity) throws AmazonException {
        if (product == null) {
            throw new AmazonException("Cannot add null product to cart");
        }
        if (quantity <= 0) {
            throw new AmazonException("Quantity must be positive");
        }
        
        if (hasItem(product)) {
            throw new AmazonException("Product already exists in cart");
        }
        
        AmazonCartItem item = new AmazonCartItem(product, quantity);
        cartItems.add(item);  
        calcSubTotal();
    }
	
	public float calcSubTotal() {
		return 0; //FOR NOW
	}
	
	public boolean hasItem(AmazonProduct product) {
	    if (product == null) return false;
	    
	    for (AmazonCartItem item : cartItems) {
	        if (item.getProduct().getId() == product.getId()) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean pay(AmazonCredit amount) {
		return pay(amount);
	}
	
	public void removeItem(AmazonProduct product) {
		
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
