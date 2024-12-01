package amazonsystem;

import java.util.ArrayList;
import java.util.Date;

public class AmazonCustomer {

	private int id;
	private String name;
	private String address;
	private ArrayList<AmazonCredit> credits = new ArrayList<>();
	private ArrayList<AmazonProduct> wishlist = new ArrayList<>();
	private AmazonCart cart;
	private ArrayList<AmazonComment> comments = new ArrayList<>();
	private ArrayList<AmazonProduct> purchasedProducts = new ArrayList<>();
 
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
	
	// method to record purchases when cart is paid
	public void recordPurchases() {
	    if (cart != null) {
	        for (AmazonCartItem item : cart.getCartItems()) {
	            if (!purchasedProducts.contains(item.getProduct())) {
	                purchasedProducts.add(item.getProduct());
	            }
	        }
	    }
	}
	
	// method had exceptions, changed to return null to pass jUnit test
	public static AmazonCustomer createAmazonCustomer(String[] customerInfo) {
	    if (customerInfo == null || customerInfo.length != 3) {
	        return null;
	    }
	    if (!AmazonSystemUtil.isValidInt(customerInfo[0]) || 
	        !AmazonSystemUtil.isValidString(customerInfo[1]) || 
	        !AmazonSystemUtil.isValidString(customerInfo[2])) {
	        return null;
	    }
	    try {
	        int id = Integer.parseInt(customerInfo[0]);
	        if (id <= 0) {
	            return null;
	        }
	        return new AmazonCustomer(id, customerInfo[1], customerInfo[2]);
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}

	public void addCredit(AmazonCredit credit) {
		if (credit != null) {
			credits.add(credit);
		}		
	}

	public void showCredits() {
		if (credits == null || credits.size() == 0) {
			System.out.println(ANSI_RED + "The customer has 0 credits." + ANSI_RESET);
		} else

			for (int i = 0; i < credits.size(); i++) {
				AmazonCredit credit = credits.get(i); 

				if (credit != null) {
					System.out.println(ANSI_PURPLE + credit.toString() + ANSI_RESET);
				}
			}	
	}
	
	// removed exceptions to pass jUnit test 
	public void addProductInWishList(AmazonProduct product) {
		if (product == null) {
			return;
		}
		if (wishlist.contains(product)) {
			return;
		}
		wishlist.add(product);
		System.out.println(ANSI_PURPLE + "[Product " + product.getId() + " added into Customer #" + this.id + "'s list]" + ANSI_RESET);
	}

	public void removeProductInWishList(AmazonProduct product) throws AmazonException {
		if (wishlist == null || wishlist.size() == 0) {
			System.out.println(ANSI_RED + "The wishlist is empty." + ANSI_RESET);
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
			System.out.println(ANSI_RED + "The wishlist is empty." + ANSI_RESET);
		} else 

			for (int i = 0; i < wishlist.size(); i++) {
				AmazonProduct product = wishlist.get(i);

				if (product != null) { 
					System.out.println(ANSI_PURPLE + product.toString() + ANSI_RESET);
				}	
			}	
	}

	public void addItemInCart(AmazonCartItem item) {
	    if (cart == null) {
	        cart = new AmazonCart(this, new Date());
	    }
	    
	    if (item == null) {
	        return;
	    }
	    
	    cart.addItemInCart(item.getProduct(), item.getQuantity());
	}
	
	public void removeProductFromCart(AmazonProduct product, int quantity) throws AmazonException {
		if (cart == null) {
			System.out.print(ANSI_RED + "The cart is empty." + ANSI_RESET);
		}
		if (product == null) {
			throw new AmazonException("Cannot remove null product from wishlist");
		}

		if (!cart.hasItem(product)) {
			throw new AmazonException("Product not found in wishlist");
		}

		if (quantity <= 0) {
			throw new AmazonException("Quantity must be positive");
		}

		cart.removeItem(product, quantity);
		System.out.println(ANSI_PURPLE + "Cart updated: Removed product - " + product+ 
				" for Customer #" + id + "]" + ANSI_RESET);
	}

	public void showCart() {
		if (cart == null || cart.getCartItems().isEmpty()) {
			System.out.println(ANSI_RED + "The cart is empty." + ANSI_RESET);
			return;
		}
		System.out.println(ANSI_PURPLE + cart.toString() + ANSI_RESET);
	}

	// removed exceptions to pass jUnit test
	public boolean pay() {
	    if (cart == null || cart.getCartItems().isEmpty()) {
	        System.out.println(ANSI_RED + "Cart is empty." + ANSI_RESET);
	        return false;
	    }

	    float totalAmount = cart.calcSubTotal();
	    AmazonCredit latestCredit = getCredits();

	    if (latestCredit == null) {
	        System.out.println(ANSI_RED + "No credits available." + ANSI_RESET);
	        return false;
	    }

	    if (latestCredit.getAmount() < totalAmount) {
	        System.out.println(ANSI_RED + "Insufficient credit amount." + ANSI_RESET);
	        return false;
	    }

	    latestCredit.deduct(totalAmount);
	    return true;
	}

	// modified addComment to check if product was purchased
	public void addComment(AmazonComment comment) {
	    if (comment == null || comment.getProduct() == null || 
	        comment.getComment() == null || comment.getComment().trim().isEmpty()) {
	        return; 
	    }
	    
	    // causing part 3 of 'TestComments' jUnit test to fail
	    // chose to adhere to business logic   
	    // checks for purchased products
	    if (!purchasedProducts.contains(comment.getProduct())) {
	        System.out.println(ANSI_RED + "You can only comment on products you have purchased." + ANSI_RESET);
	        return;
	    }

	    comments.add(comment);
	    System.out.println(ANSI_PURPLE + "Comment from customer: Customer ID - [" + 
	                      this.id + "], Name - [" + this.name + "] ..." + ANSI_RESET);
	}
	
	public void showProductComments() {
	    if (comments == null || comments.isEmpty()) {
	        System.out.println(ANSI_RED + "No comments available." + ANSI_RESET);
	        return;
	    }
	    
	    for (AmazonComment comment : comments) {
	        if (comment != null) {
	            System.out.println(ANSI_PURPLE + "Comment Information: Product Id - [" + comment.getProduct().getId() + "]," +
	                             " Comment - [" + comment.getComment() + "]," + 
	                             " Rating - [" + comment.getRating() + "]" + ANSI_RESET);
	        }
	    }
	}

	public String toString() {
		return "Customer: [Id - " + id + "], [Name - " + name + "], [Address - " + address + "]"; 
	}

	// GETTERS & SETTERS
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

	public AmazonCart getCart() {
		return cart;
	}

	public void setCart(AmazonCart cart) {
		this.cart = cart;
	}

	public AmazonCredit getCredits() {
		if (credits.isEmpty()) {
			return null;
		} else {
			return credits.get(credits.size() - 1); 
		}
	}
		
	public int getWishlistSize() {
		return wishlist.size();
	}	
	
	public int getCartSize() {
	    if (cart == null) {
	        return 0;
	    }
	    return cart.getCartItems().size();
	}
	
	public int getNumberOfComments() {
	    return comments.size();
	}
	
	public int getNumberOfCredits() {
	    return credits.size();
	}
}

