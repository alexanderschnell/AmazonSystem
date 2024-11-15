package amazonsystem;

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
	    orderValue = 0;
	    for (AmazonCartItem item : cartItems) {
	        orderValue += item.getProduct().getActual_price() * item.getQuantity();
	    }
	    return orderValue;
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
	
	public void removeItem(AmazonProduct product, int quantity) throws AmazonException {
		
	      if (product == null) {
	            throw new AmazonException("Cannot add null product to cart");
	        }
	        if (quantity <= 0) {
	            throw new AmazonException("Quantity must be positive");
	        }
	        
	        if (!hasItem(product)) {
	            throw new AmazonException("Product not found in cart");
	        }
	        
	        AmazonCartItem itemToRemove = null;
	        for (AmazonCartItem item : cartItems) {
	            if (item.getProduct().equals(product)) {
	                itemToRemove = item;
	                break;
	            }
	        }

	        if (itemToRemove != null) {
	            if (quantity > itemToRemove.getQuantity()) {
	                throw new AmazonException("Not enough items to remove");
	            }
	            if (quantity == itemToRemove.getQuantity()) {
	                cartItems.remove(itemToRemove);
	            } else {
	                itemToRemove.setQuantity(itemToRemove.getQuantity() - quantity);
	            }
	        }
	    }
	
    @Override
    public boolean pay(AmazonCredit credit) {
        if (credit.getAmount() >= this.getOrderValue()) {
            credit.deduct(this.getOrderValue());
            return true;
        }
        return false;
    }
	
	public String toString() {
	    String result = "";
	    int i = 1;
	    result += "[Customer: " + customer.getName() + "]\n";
	    result += "[Date: " + date + "]\n";
	    for (AmazonCartItem item : cartItems) {
	        if (item != null) {
	            result += "Item[" + i + "]" + item.toString() + "\n";
	            i++;
	        }
	    }
	    result += "Total Value: " + orderValue;
	    return result;
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
