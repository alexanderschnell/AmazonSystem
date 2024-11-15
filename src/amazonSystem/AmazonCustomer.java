package amazonsystem;

import java.util.ArrayList;
import java.util.Date;

//TODO
//START IMPLEMENTING NEW METHODS 

public class AmazonCustomer {
	
	private int id;
	private String name;
	private String address;
	private ArrayList<AmazonCredit> credits = new ArrayList<>();
	private ArrayList<AmazonProduct> wishlist = new ArrayList<>();
	private AmazonCart cart;
	
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	private AmazonCustomer(int id, String name, String address) {
		this.setId(id);
		this.setName(name);
		this.setAddress(address); 
		this.cart = new AmazonCart(this, new Date());
	}
	
	public static AmazonCustomer createAmazonCustomer(String[] customerInfo) throws AmazonException {
	    
	    if (customerInfo == null || customerInfo.length != 3) {
	        throw new AmazonException("Invalid customer data format");
	    }
	    
	    try {
	        int id = Integer.parseInt(customerInfo[0]);
	        if (id <= 0) {
	            throw new AmazonException("Invalid ID: must be positive");
	        }
	        
	        if (customerInfo[1] == null || customerInfo[1].trim().isEmpty()) {
	            throw new AmazonException("Name cannot be empty");
	        }
	        
	        if (customerInfo[2] == null || customerInfo[2].trim().isEmpty()) {
	            throw new AmazonException("Address cannot be empty");
	        }
	        
	        return new AmazonCustomer(id, customerInfo[1], customerInfo[2]);
	    } catch (NumberFormatException e) {
	        throw new AmazonException("Invalid ID format");
	    }
	}
	
	public void addCredit(AmazonCredit credit) {
		if (credit != null) {
			credits.add(credit);
		}		
	}
	
	public void showCredits() {
		if (credits == null || credits.size() == 0) {
			System.out.println("The customer has 0 credits.");
		} else
		
		for (int i = 0; i < credits.size(); i++) {
			AmazonCredit credit = credits.get(i); 
			
			if (credit != null) {
				System.out.println(ANSI_PURPLE + credit.toString() + ANSI_RESET);
			}
		}	
	}
		
	public void addProductsInWishList(AmazonProduct product) throws AmazonException {
		if (product == null) {
			throw new AmazonException("Cannot add null product to wishlist");
		}
		if (wishlist.contains(product)) {
			throw new AmazonException("Product already exists in wishlist");
		}
			wishlist.add(product);
			System.out.println(ANSI_PURPLE + "[Product " + product.getId() + " added into Customer #" + this.id + "'s list]" + ANSI_RESET);
		}

	public void removeProductInWishList(AmazonProduct product) throws AmazonException {
		if (wishlist == null || wishlist.size() == 0) {
			System.out.println("The wishlist is empty.");
			return;
		}
		if (product == null) {
			throw new AmazonException("Cannot remove null product from wishlist");
		}

		if (!wishlist.contains(product)) {
			throw new AmazonException("Product not found in wishlist");
		}

		wishlist.remove(product);
		System.out.println(ANSI_PURPLE + "[Product " + product.getId() + 
				" removed from Customer #" + this.id + "'s wishlist]" + ANSI_RESET);
	}
	
	public void showWishList() {
		if (wishlist == null || wishlist.size() == 0) {
			System.out.println("The wishlist is empty.");
		} else 
			
		for (int i = 0; i < wishlist.size(); i++) {
			AmazonProduct product = wishlist.get(i);
			
			if (product != null) { 
				System.out.println(ANSI_PURPLE + product.toString() + ANSI_RESET);
			}	
		}	
	}
	
	public void addItemInCart(AmazonProduct product, int quantity) throws AmazonException {
        if (cart == null) {
            cart = new AmazonCart(this, new Date());
        }
        
        if (product == null) {
            throw new AmazonException("Cannot add null product to cart");
        }
        if (quantity <= 0) {
            throw new AmazonException("Quantity must be positive");
        }

        cart.addItem(product, quantity);
    }
	
	public void removeProductFromCart() {
		
	}
	
	public void showCart() {
		
	}
	
	public void pay() {
		
	}
	
	public void addComment() {
		
	}

	public void showComments() {
		
	}
	
	public String toString() {
		return "- Customer: [Id: " + id + "], [Name: " + name + "], [Address: " + address + "]"; 
	}

	//GETTERS & SETTERS
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
