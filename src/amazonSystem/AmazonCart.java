package amazonsystem;

import java.util.ArrayList;
import java.util.Date;

public class AmazonCart implements AmazonPayable {
	
	private AmazonCustomer customer;
	private Date date;
	private ArrayList<AmazonCartItem> cartItems = new ArrayList<AmazonCartItem>();
	private float orderValue;
	
	//ANSI color codes
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String BLUE_BOLD = "\u001B[1;34m";
	public static final String BLACK_BOLD = "\u001B[1;30m";
	public static final String CYAN_BOLD = "\u001B[1;36m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	public AmazonCart(AmazonCustomer customer, Date date) {
		this.setCustomer(customer);
		this.setDate(date);
		
	}
	
	public boolean addItemInCart(AmazonProduct product, int quantity) {
	    if (product == null || quantity <= 0) {
	        return false;
	    }
	    for (AmazonCartItem item : cartItems) {
	        if (item.getProduct().equals(product)) {
	            item.setQuantity(item.getQuantity() + quantity);
	            calcSubTotal();
	            return true;
	        }
	    }
	    AmazonCartItem item = new AmazonCartItem(product, quantity);
	    cartItems.add(item);
	    calcSubTotal();
	    return true;
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
	        
	        //calculate the order value after modifying cart
	        calcSubTotal();
	    }
	}
	
	@Override
	public boolean pay(AmazonCredit credit) {
	    // check if customer has any credits
	    if (credit == null) {
	        System.out.println(ANSI_RED + "No credits available." + ANSI_RESET);
	        return false;
	    }

	    if (cartItems == null || cartItems.isEmpty()) {
	        return false;
	    }

	    if (!AmazonSystemUtil.isValidFloat(String.valueOf(this.getOrderValue()))) {
	        return false;
	    }

	    if (!AmazonSystemUtil.isValidFloat(String.valueOf(credit.getAmount()))) {
	        return false;
	    }

	    if (credit.getAmount() >= this.getOrderValue()) {
	        credit.deduct(this.getOrderValue());
	        return true;
	    }
	    
	    System.out.println(ANSI_RED + "Insufficient credit amount." + ANSI_RESET);
	    return false;
	}
	
	public String toString() {
	    String result = "";
	    result += "Customer: [" + customer.getName() + "]\n";
	    result += "Date: [" + date + "]\n";
	    for (AmazonCartItem item : cartItems) {
	        if (item != null) {
	            result += "Product: " + item.toString() + "\n";
	        }
	    }
	    result += "Total Value: [" + orderValue + "]";
	    return result;
	}
		
	// GETTERS & SETTERS
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
