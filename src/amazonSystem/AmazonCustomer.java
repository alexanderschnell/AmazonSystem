package amazonsystem;

import java.util.ArrayList;

public class AmazonCustomer {
	
	private int id;
	private String name;
	private String address;
	private ArrayList<AmazonCredit> credits = new ArrayList<>();
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	private AmazonCustomer(int id, String name, String address) {
		this.setId(id);
		this.setName(name);
		this.setAddress(address); 
	}
	
	//TODO ALL METHODS 
	
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
		
	public void addProductsInWishList(){
		
		
	}
	
	public void removeProductInWishList() {
		
	}
	
	public boolean isProductInWishList() {
		return true; //FOR NOW 
	}
	
	public void showWishList() {
		
	}
	
	public void addItemInCart() {
		
	}
	
	public void removeProductFromCart() {
		
	}
	
	public void showCart() {
		
	}
	
	public void pay() {
		
	}
	
	public void moveFromCartToComments() {
		
	}
	
	public boolean hasProductToComment() {
		return true; //FOR NOW
	}
	
	public void addComment() {
		
	}
	
	public void setComment() {
		
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
