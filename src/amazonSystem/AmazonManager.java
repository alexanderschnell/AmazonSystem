package amazonsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AmazonManager {
	
	public String fileName;

	public final String OPTION0_EXIT = "[Q]";
	public final String OPTION1_LOAD = "[A]";
	public final String OPTION2_SHOW = "[B]";
	public final String OPTION3_SEARCH = "[C]";
	public final String OPTION4_ADD_CUSTOMER = "[D]";
	public final String OPTION5_SHOW_CUSTOMER = "[E]";
	public final String OPTION6_ADD_CREDIT = "[F]";
	public final String OPTION7_SHOW_CREDIT = "[G]";
	public final String OPTION8_ADD_TO_WISHLIST = "[H]";
	public final String OPTION9_REMOVE_FROM_WISHLIST = "[I]";
	public final String OPTION10_SHOW_FROM_WISHLIST = "[J]";
	public final String OPTION11_ADD_TO_CART = "[K]";
	public final String OPTION12_REMOVE_FROM_CART = "[L]";
	public final String OPTION13_SHOW_FROM_CART = "[M]";
	public final String OPTION14_BUY_FROM_CART = "[N]";
	public final String OPTION15_COMMENT_ON_PRODUCT = "[O]";
	public final String OPTION16_LIST_COMMENTS = "[P]";

	public static Scanner input = new Scanner(System.in);

	private AmazonProductList productList;
	private ArrayList<AmazonCustomer> customerList;
	
	//ANSI color codes
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String BLUE_BOLD = "\u001B[1;34m";
	public static final String BLACK_BOLD = "\u001B[1;30m";
	public static final String CYAN_BOLD = "\u001B[1;36m";
	public static final String ANSI_RESET = "\u001B[0m";
	

	public AmazonManager() {
		productList = new AmazonProductList();
		customerList = new ArrayList<AmazonCustomer>();		
	}

	public static void main(String[] args) {
		AmazonManager manager = new AmazonManager();
		try {
			manager.menuController();
		} catch (RuntimeException e) {
			System.err.println(ANSI_RED + "An unexpected critical error occurred: " + e.getMessage() + ANSI_RESET);
			e.printStackTrace();

		} catch (AmazonException e) {
			e.printStackTrace();
		}																
	}

	// New menu controller 
	public void menuController() throws AmazonException {
		String choice;
		do {
			showMenu();
			choice = input.nextLine();
			try {
				switch (choice.toUpperCase()) {
				case "Q":
					exit();
					break;
				case "A":
					loadProductList();
					break;
				case "B":
					showProductList();
					break;
				case "C":
					searchInProducts();
					break;	
				case "D":
					addCustomer();
					break;
				case "E":
					showCustomer();
					break;
				case "F":
					addCreditToCustomer();
					break;
				case "G":
					showCreditFromCustomer();
					break;
				case "H":
					addProductinWishList();
					break;
				case "I":
					removeProductFromWishList();
					break;
				case "J":
					showWishList();
					break;
				case "K":
					addItemInCart();
					break;	
				case "L":
					removeProductFromCart();
					break;	
				case "M":
					showProductsInCart();
					break;	
				case "N":
					payCart();
					break;	
				case "O":
					addCommentToProduct();
					break;	
				case "P":
					showComments();
					break;	
				default:
					System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
				}
			} catch (Exception e) {
				System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET); 
			}		
		} while (!choice.equalsIgnoreCase("Q"));
	}
	
	// New menu (Could change to assignment format) 
	public void showMenu() {
		
		System.out.println();
		System.out.println(ANSI_BLACK + "=============================================================\r\n"
				+  "||"  + CYAN_BOLD  +    " ~~~~~~~~~~~~~~~~~~~~ AMAZON SYSTEM ~~~~~~~~~~~~~~~~~~~~ " + ANSI_RESET +  "||\r\n"  
				+ "=============================================================\n");

		System.out.println(CYAN_BOLD + "***ADMIN***\n" + ANSI_RESET);
		System.out.println(BLACK_BOLD + "Product options...................." + ANSI_RESET);
		System.out.println(OPTION1_LOAD + " Load product list");
		System.out.println(OPTION2_SHOW + " Show product list");
		System.out.println(OPTION3_SEARCH + " Search in the list\n");
		System.out.println(BLACK_BOLD + "Customer options..................." + ANSI_RESET);
		System.out.println(OPTION4_ADD_CUSTOMER + " Add customers");
		System.out.println(OPTION5_SHOW_CUSTOMER + " Show customers\n");
		System.out.println(CYAN_BOLD + "***USER***\n" + ANSI_RESET);
		System.out.println(BLACK_BOLD + "Credit options....................." + ANSI_RESET);
		System.out.println(OPTION6_ADD_CREDIT + " Add credits to customers");
		System.out.println(OPTION7_SHOW_CREDIT + " Show customer credits\n");
		System.out.println(BLACK_BOLD + "Wishlist Options..................." + ANSI_RESET);
		System.out.println(OPTION8_ADD_TO_WISHLIST + " Add products to wishlist");
		System.out.println(OPTION9_REMOVE_FROM_WISHLIST + " Remove products from wishlst");
		System.out.println(OPTION10_SHOW_FROM_WISHLIST + " Show products from wishlist\n");
		System.out.println(BLACK_BOLD + "Cart Options......................." + ANSI_RESET);
		System.out.println(OPTION11_ADD_TO_CART + " Add products to cart");
		System.out.println(OPTION12_REMOVE_FROM_CART + " Remove products from cart");
		System.out.println(OPTION13_SHOW_FROM_CART + " Show products from cart");
		System.out.println(OPTION14_BUY_FROM_CART + " Buy products from cart\n");
		System.out.println(BLACK_BOLD + "Comment Options...................." + ANSI_RESET);
		System.out.println(OPTION15_COMMENT_ON_PRODUCT + " Comment on purchased products");
		System.out.println(OPTION16_LIST_COMMENTS + " List comments from products\n");		
		System.out.println(BLACK_BOLD + "...................................");
		System.out.println(OPTION0_EXIT + " Exit" + ANSI_RESET);
		System.out.print(CYAN_BOLD + "\nChoose an option: " + ANSI_RESET);
	}

	public void exit() {
		System.out.println("=============================================================\r\n"
				+ "||     " + CYAN_BOLD + "[Application ended]" + ANSI_RESET + BLUE_BOLD + "  [Author: Alexander Schnell]" + ANSI_RESET  + "    ||\r\n"
				+ "=============================================================" + ANSI_RESET);
		input.close();
	}

	// Taken from A1
	public void loadProductList() throws AmazonException {
		boolean isValidFile = false;

		while (!isValidFile) {
			System.out.print("Enter the filename to load: ");
			String fileName = input.nextLine();

			try {
				productList.createList(fileName);
				System.out.println(ANSI_PURPLE + "Product list created successfully!" + ANSI_RESET);
				isValidFile = true;

			} catch (AmazonException e) {
				System.err.println("Failed to create product list. Please enter valid file name.");
			}
		}
	}

	// Taken from A1
	public void showProductList() {
		if (productList == null || productList.getSize() == 0) {
			System.out.println("The product list is empty.");
			return;
		} else 
			System.out.println(BLACK_BOLD + "[Printing Productlist ...]" + ANSI_RESET);

		for (int i = 0; i < productList.getSize(); i++) {
			AmazonProduct product = productList.findProductByIndex(i);

			if (product != null) {
				System.out.println(ANSI_PURPLE + product.toString() + ANSI_RESET);
			}	
		}
	}

	// Taken from A1
	public void searchInProducts() throws AmazonException  {
		System.out.print("Enter a keyword to find: ");
		String keyword = input.nextLine();
		productList.search(keyword);
	}

	public void addCustomer() {
		System.out.print("Enter customer ID: ");
		String id = input.nextLine();
		System.out.print("Enter customer name: ");
		String name = input.nextLine();
		System.out.print("Enter customer address: ");
		String address = input.nextLine();

		String[] customerData = {id, name, address};
		AmazonCustomer newCustomer = AmazonCustomer.createAmazonCustomer(customerData);
		
		customerList.add(newCustomer);
		
		System.out.println(ANSI_PURPLE + "Customer added successfully!" + ANSI_RESET);
	}

	public void showCustomer() {
		if (customerList == null || customerList.size() == 0) {
			System.out.println("The customer list is empty.");
		} else
			System.out.println(BLACK_BOLD + "[Printing customer(s) ...]" + ANSI_RESET);

		for (int i = 0; i < customerList.size(); i++) {
			AmazonCustomer customer = customerList.get(i); 

			if (customer != null) {
				System.out.println(ANSI_PURPLE + customer.toString() + ANSI_RESET);
			}
		}
	}

	public void addCreditToCustomer() throws AmazonException {
		if (customerList.isEmpty()) {
			System.out.println(ANSI_RED + "No customers found!" + ANSI_RESET);
			return;
		}
		System.out.print("Enter the Customer ID: ");
		try {
			int id = Integer.parseInt(input.nextLine());

			for (AmazonCustomer customer : customerList) {
				if (customer.getId() == id) {
					System.out.print("Enter the Type of credit ([1]: Cash, [2]: Check, [3]: Card): ");
					int choice = Integer.parseInt(input.nextLine());

					switch(choice) {
					case 1:
						System.out.print("Enter Cash value: ");
						String amount = input.nextLine();
						String[] cashInfo = {amount};
						AmazonCash cash = AmazonCash.createCash(cashInfo);
						customer.addCredit(cash);
						System.out.println(ANSI_PURPLE + "Credit added with success!" + ANSI_RESET);
						break;
					case 2:
						System.out.print("Enter Check value: ");
						String checkAmount = input.nextLine();
						System.out.print("Enter Account Number: ");
						String accountNumber = input.nextLine();
						String[] checkInfo = {checkAmount, accountNumber};
						AmazonCheck check = AmazonCheck.createCheck(checkInfo);
						customer.addCredit(check);
						System.out.println(ANSI_PURPLE + "Credit added with success!" + ANSI_RESET);
						break;
					case 3:
						System.out.print("Enter Card value: ");
						String cardAmount = input.nextLine();
						System.out.print("Enter Card Number: ");
						String cardNumber = input.nextLine();
						System.out.print("Enter Expiration Date: ");
						String expiration = input.nextLine();
						String[] cardInfo = {cardAmount, cardNumber, expiration};
						AmazonCard card = AmazonCard.createCard(cardInfo);
						customer.addCredit(card);
						System.out.println(ANSI_PURPLE + "Credit added with success!" + ANSI_RESET);
						break;
					default:
						throw new AmazonException(ANSI_RED + "Invalid credit type selected" + ANSI_RESET);
					}
				}
			}

		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid input format");
		}
	}

	public void showCreditFromCustomer() {

		System.out.print("Enter the customer ID: ");
		int id = Integer.parseInt(input.nextLine());

		for (AmazonCustomer customer : customerList) {
			if (customer.getId() == id) {
				System.out.println(BLACK_BOLD + "[Printing customer(s) credit...]" + ANSI_RESET);
				customer.showCredits();
			}
		}
	}

	public void addProductinWishList() throws AmazonException {
		if (customerList.isEmpty()) {
			System.out.println(ANSI_RED + "No customer found." + ANSI_RESET);
			return; 
		}
		System.out.print("Enter the Customer ID: ");
		try {
			int customerId = Integer.parseInt(input.nextLine());

			AmazonCustomer customer = null;
			for (AmazonCustomer c : customerList) {
				if (c.getId() == customerId) {
					customer = c;
					break;
				}
			}

			if (customer == null) {
				System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
				return;  
			}

			System.out.print("Enter the Product ID to include in the wishlist: ");
			int productId = Integer.parseInt(input.nextLine());

			AmazonProduct product = productList.findProductById(productId); 

			if (product == null) {
				System.out.println(ANSI_RED + "Product not found." + ANSI_RESET);
				return;  
			}

			customer.addProductInWishList(product);

		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid input format.");
		}
	}

	public void removeProductFromWishList() throws AmazonException {
		if (customerList.isEmpty()) {
			System.out.println(ANSI_RED + "No wishlists found." + ANSI_RESET);
		} else

			System.out.print("Enter the Customer ID: ");
		try {
			int id = Integer.parseInt(input.nextLine());

			AmazonCustomer customer = null;
			for (AmazonCustomer c : customerList) {
				if (c.getId() == id) {
					customer = c;
					break;
				}
			}

			if (customer == null) {
				System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
				return;
			}

			System.out.print("Enter the Product ID to  the wishlist: ");
			int productId = Integer.parseInt(input.nextLine());

			AmazonProduct product = productList.findProductById(productId); 

			if (product == null) {
				System.out.println(ANSI_RED + "Product not found." + ANSI_RESET);
				return;  
			}

			customer.removeProductInWishList(product);

		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid input format.");
		}
	}

	public void showWishList() {
		if (customerList.isEmpty()) {
			System.out.print(ANSI_RED + "No customers found." + ANSI_RESET);
			return;
		}

		System.out.print("Enter the Customer ID: ");
		try {
			int id = Integer.parseInt(input.nextLine());

			AmazonCustomer customer = null;
			for (AmazonCustomer c : customerList) {
				if (c.getId() == id) {
					customer = c;
					break;
				}
			}

			if (customer == null) {
				System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
				return;
			}

			System.out.println(BLACK_BOLD + "[Printing wishlist ...]" + ANSI_RESET);
			customer.showWishList();
		} catch (NumberFormatException e) {
			System.out.println(ANSI_RED + "Invalid input format." + ANSI_RESET);
		}
	}

	public void addItemInCart() throws AmazonException {
		if (customerList.isEmpty()) {
			System.out.println(ANSI_RED + "No customers found." + ANSI_RESET);
			return;
		}
		System.out.print("Enter the Customer ID: ");
		try {
			int customerId = Integer.parseInt(input.nextLine());

			AmazonCustomer customer = null;
			for (AmazonCustomer c : customerList) {
				if (c.getId() == customerId) {
					customer = c;
					break;
				}
			}

			if (customer == null) {
				System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
				return;
			}
			System.out.print("Enter the Product ID to buy from your cart: ");
			int productId = Integer.parseInt(input.nextLine());

			AmazonProduct product = productList.findProductById(productId); 

			if (product == null) {
				System.out.println(ANSI_RED + "Product not found." + ANSI_RESET);
				return;  
			}

			System.out.print("Enter the number of items to put in cart: ");
			int quantity = Integer.parseInt(input.nextLine());

			if (quantity <= 0) {
				System.out.println(ANSI_RED + "Quantity must be positive." + ANSI_RESET);
				return;
			}
			
			AmazonCartItem cartItem = new AmazonCartItem(product, quantity);
			customer.addItemInCart(cartItem);
			
			System.out.println(ANSI_PURPLE + "Cart updated: [" + quantity + " item(s) of Product #" + productId + 
					" added for Customer #" + customerId + "]" + ANSI_RESET);

		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid input format.");
		}
	}

	public void removeProductFromCart() throws AmazonException {
		if (customerList.isEmpty()) {
			System.out.println(ANSI_RED + "No customers found." + ANSI_RESET);
			return;
		}
		System.out.print("Enter the Customer ID: "); 
		int customerId = Integer.parseInt(input.nextLine());

		AmazonCustomer customer = null;
		for (AmazonCustomer c : customerList) {
			if (c.getId() == customerId) {
				customer = c;
				break;
			}
		}

		if (customer == null) {
			System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
			return;
		}

		System.out.print("Enter the Product ID to remove from your cart: ");
		int productId = Integer.parseInt(input.nextLine());

		AmazonProduct product = productList.findProductById(productId); 

		if (product == null) {
			System.out.println(ANSI_RED + "Product not found." + ANSI_RESET);
			return;  
		}

		System.out.print("Enter the number of items to remove from cart: ");
		int quantity = Integer.parseInt(input.nextLine());

		if (quantity <= 0) {
			System.out.println(ANSI_RED + "Quantity must be positive." + ANSI_RESET);
			return;
		}	        

		customer.removeProductFromCart(product, quantity);
	}

	public void showProductsInCart() {
		if (customerList.isEmpty()) {
			System.out.print(ANSI_RED + "No customers found." + ANSI_RESET);
			return;
		}

		System.out.print("Enter the Customer ID: ");
		try {
			int id = Integer.parseInt(input.nextLine());


			AmazonCustomer customer = null;
			for (AmazonCustomer c : customerList) {
				if (c.getId() == id) {
					customer = c;
					break;
				}
			}

			if (customer == null) {
				System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
				return;
			}

			System.out.println(BLACK_BOLD + "[Printing cart ...]" + ANSI_RESET);
			customer.showCart();
		} catch (NumberFormatException e) {
			System.out.println(ANSI_RED + "Invalid input format." + ANSI_RESET);
		}
	}

	public void payCart() throws AmazonException {
	    if (customerList.isEmpty()) {
	        throw new AmazonException(ANSI_RED + "No customers found." + ANSI_RESET);
	    }

	    System.out.print("Enter the Customer ID: ");
	    int id = Integer.parseInt(input.nextLine());

	    AmazonCustomer customer = null;
	    for (AmazonCustomer c : customerList) {
	        if (c.getId() == id) {
	            customer = c;
	            break;
	        }
	    }

	    if (customer == null) {
	        throw new AmazonException(ANSI_RED + "Customer not found." + ANSI_RESET);
	    }

	    if (customer.getCart() == null || customer.getCart().getCartItems().isEmpty()) {
	        throw new AmazonException(ANSI_RED + "Cart is empty" + ANSI_RESET);
	    }

	    System.out.print("Confirm payment by typing [P]: ");
	    String choice = input.nextLine();

	    if (!choice.equalsIgnoreCase("p")) {
	        throw new AmazonException(ANSI_RED + "No credits available." + ANSI_RESET);
	    }

	    float totalAmount = customer.getCart().calcSubTotal();
	    if (customer.getCredits().getAmount() >= totalAmount) {
	        customer.getCredits().deduct(totalAmount);
	        customer.getCart().getCartItems().clear();
	        System.out.println(ANSI_PURPLE + "Customer credit updated: Type - " + "[" + customer.getCredits().getType() +
	                         "]," + " Credit Amount - " + "[" + customer.getCredits().getAmount() + "]");
	        System.out.println("Cart empty - you can comment products now." + ANSI_RESET);
	    } else {
	        throw new AmazonException(ANSI_RED + "Insufficient credit amount." + ANSI_RESET);
	    }
	}

	public void addCommentToProduct() throws AmazonException {
		if (customerList.isEmpty()) {
			throw new AmazonException(ANSI_RED + "No customers found." + ANSI_RESET);
		}
		
		System.out.print("Enter the customer ID: ");
		int id = Integer.parseInt(input.nextLine());
		
		AmazonCustomer customer = null;
		for (AmazonCustomer c : customerList) {
			if (c.getId() == id) {
				customer = c;
				break;
			}
		}
		if ( customer == null) {
			throw new AmazonException(ANSI_RED + "Customer not found." + ANSI_RESET);
		}
		
		System.out.print("Enter the Product ID for comment: ");
		int productId = Integer.parseInt(input.nextLine());

		AmazonProduct product = productList.findProductById(productId); 

		if (product == null) {
			System.out.println(ANSI_RED + "Product not found." + ANSI_RESET);
			return;  
		}
		
		System.out.println("Commenting product: " + product.toString());
		System.out.print("Enter the comment: ");
		String comment = input.nextLine();
		System.out.print("Enter the star rating: ");
		float rating = Float.parseFloat(input.nextLine());
		
		 if (rating < 1.0 || rating > 5.0) {
	            throw new AmazonException(ANSI_RED + "Rating must be between 1.0 and 5.0" + ANSI_RESET);
	        }
		 	
		 	AmazonComment commentText = new AmazonComment(product, comment, rating);
	        customer.addComment(commentText);
	}
	
	public void showComments() {
		if (customerList.isEmpty()) {
			System.out.print(ANSI_RED + "No customers found." + ANSI_RESET);
			return;
		}

		System.out.print("Enter the Customer ID: ");
		try {
			int id = Integer.parseInt(input.nextLine());


			AmazonCustomer customer = null;
			for (AmazonCustomer c : customerList) {
				if (c.getId() == id) {
					customer = c;
					break;
				}
			}

			if (customer == null) {
				System.out.println(ANSI_RED + "Customer not found." + ANSI_RESET);
				return;
			}

			customer.showProductComments();
			
		} catch (NumberFormatException e) {
			System.out.println(ANSI_RED + "Invalid input format." + ANSI_RESET);
		}
	}
}

/*
	public void addProduct() throws AmazonProductException {
		if (productList == null) {
			throw new AmazonProductException("Product list has not been created. Please load a product first.");
		}

		int newID = productList.getSize();
		AmazonProduct newProduct = null;

		while (newProduct == null) {
			try {
				newProduct = createProduct(newID);
			} catch (NumberFormatException e) {
				System.out.println(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
			}
		}

		productList.add(newProduct);
		System.out.println(ANSI_PURPLE + "Product added successfully! New list size: " + productList.getSize() + ANSI_RESET);
	}
	public AmazonProduct createProduct(int id) throws AmazonProductException {
		System.out.print("Enter product name: ");
		String name = input.nextLine();
		System.out.print("Enter product category: ");
		String category = input.nextLine();
		System.out.print("Enter product subcategory: ");
		String subcategory = input.nextLine();
		System.out.print("Enter image URL: ");
		String imageURL = input.nextLine();
		System.out.print("Enter product link: ");
		String link = input.nextLine();
		System.out.print("Enter rating: ");
		float rating = AmazonProductUtil.convertStrToFloat(input.nextLine());
		System.out.print("Enter number of ratings: ");
		int nRatings = Integer.parseInt(input.nextLine());
		System.out.print("Enter discount price: ");
		float discountPrice = AmazonProductUtil.convertStrToFloat(input.nextLine());
		System.out.print("Enter actual price: ");
		float actualPrice = AmazonProductUtil.convertStrToFloat(input.nextLine());

		return new AmazonProduct(id, name, new AmazonProductCategory(category),
				new AmazonProductSubCategory(subcategory), imageURL, link, rating, nRatings,
				discountPrice, actualPrice);
	}



	public void deleteProduct() throws AmazonProductException {
		while (true) {
			System.out.print("Enter the ID of the product to delete: ");
			try {
				int id = Integer.parseInt(input.nextLine());
				AmazonProduct existingProduct = productList.findProductById(id);

				if (existingProduct == null) {
					System.out.println(ANSI_RED + "Product with ID " + id + " not found. Please try again." + ANSI_RESET);
					continue;  
				}

				boolean deleted = productList.delete(id);

				if (deleted) {
					System.out.println(ANSI_PURPLE + "Product successfully deleted!" + ANSI_RESET);

				} else {
					System.out.println(ANSI_RED + "Failed to delete product." + ANSI_RESET);
				}
				return; 
				
			} catch (NumberFormatException e) {
				System.out.println(ANSI_RED + "Invalid ID. Please enter a valid number." + ANSI_RESET);

			}
		}
	}

	public void editProduct() throws AmazonProductException {
		while (true) {
			System.out.print("Enter the ID of the product to edit: ");
			try {
				int id = Integer.parseInt(input.nextLine());
				AmazonProduct existingProduct = productList.findProductById(id);

				if (existingProduct == null) {
					System.out.println(ANSI_RED + "Product with ID " + id + " not found. Please try again." + ANSI_RESET);
					continue;  
				}

				AmazonProduct updatedProduct = createProduct(id);
				boolean edited = productList.edit(id, updatedProduct);

				if (edited) {
					System.out.println(ANSI_PURPLE + "Product successfully edited!" + ANSI_RESET);
					
				} else {
					System.out.println(ANSI_RED + "Failed to edit product." + ANSI_RESET);
				}
				return; 
				
			} catch (NumberFormatException e) {
				System.out.println(ANSI_RED + "Invalid ID. Please enter a valid number." + ANSI_RESET);

			}
		}
	}

	

	public void saveProductList() throws AmazonProductException {
		if (productList == null || productList.getSize() == 0) {
			System.out.println("The product list is empty. Nothing to save.");
			return;
		}

		System.out.print("Enter the filename to save: ");
		String fileName = input.nextLine();
		productList.saveList(fileName);
	}

	*/
	




