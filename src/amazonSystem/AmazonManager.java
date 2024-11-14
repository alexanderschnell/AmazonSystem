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
	
	
	public static Scanner input = new Scanner(System.in);
	
	private AmazonProductList productList;
	private ArrayList<AmazonCustomer> customerList;
	
	//TODO
	// METHODS INCOMPLETE ON MANY CLASSES (AMAZONCUSTOMER ARRAYLIST)
	//START IMPLEMENTING NEW METHODS 

	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
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
					addProductinWishList();
					break;
				case "J":
					addProductinWishList();
					break;				
				default:
					System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
				}
			} catch (Exception e) {
				System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET); 
			}		
		} while (!choice.equalsIgnoreCase("Q"));
	}
		
	public void showMenu() {
		System.out.println(ANSI_BLACK + "================================\r\n"
				+ "||      Amazon Manager: A2    ||\r\n"
				+ "================================\n");
		
		System.out.println("ADMIN\n");
		System.out.println("Product options....................");
		System.out.println(OPTION1_LOAD + ". Load product list");
		System.out.println(OPTION2_SHOW + ". Show product list");
		System.out.println(OPTION3_SEARCH + ". Search in the list\n");
		System.out.println("Customer options...................");
		System.out.println(OPTION4_ADD_CUSTOMER + ". Add customer");
		System.out.println(OPTION5_SHOW_CUSTOMER + ". Show customer\n");
		System.out.println("USER\n");
		System.out.println("Credit options.....................");
		System.out.println(OPTION6_ADD_CREDIT + ". Add credits to customer");
		System.out.println(OPTION7_SHOW_CREDIT + ". Show customer credits\n");
		System.out.println("Wishlist Options...................");
		System.out.println(OPTION8_ADD_TO_WISHLIST + ". Add product in wishlist");
		System.out.println(OPTION9_REMOVE_FROM_WISHLIST + ". Remove product from wishlst");
		System.out.println(OPTION10_SHOW_FROM_WISHLIST + ". Show products from wishlist\n");
		System.out.println("...................................");
		System.out.println(OPTION0_EXIT + ". Exit");
		System.out.print("\nChoose an option: ");
		//System.out.println(OPTION3_ADD + ". Add product");
		//System.out.println(OPTION4_EDT + ". Edit a product");
		//System.out.println(OPTION5_DEL + ". Delete a product");
		//System.out.println(OPTION6_SAV + ". Save product list");
		
	}

	public void exit() {
		System.out.println("================================\r\n"
				+  "||     [Application ended]    ||\r\n"
				+ "================================" + ANSI_RESET);
		input.close();
		System.exit(0);
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

			System.out.println(ANSI_PURPLE + "PRODUCTLIST............");
		System.out.println(ANSI_PURPLE + "BOOKLIST............");;

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
	
	public void addCustomer() throws AmazonException {
	    System.out.print("Enter customer ID: ");
	    String id = input.nextLine();
	    System.out.print("Enter customer name: ");
	    String name = input.nextLine();
	    System.out.print("Enter customer address: ");
	    String address = input.nextLine();
	    
	    String[] customerData = {id, name, address};
	    try {
	        AmazonCustomer newCustomer = AmazonCustomer.createAmazonCustomer(customerData);
	        customerList.add(newCustomer);
	        System.out.println(ANSI_PURPLE + "Customer added successfully!" + ANSI_RESET);
	    } catch (AmazonException e) {
	        System.err.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
	    }
	}
	
	public void showCustomer() {
		if (customerList == null || customerList.size() == 0) {
			System.out.println("The customer list is empty.");
		} else
			
			System.out.println("[Printing customer ...]");
		
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
	                        return;
	                    case 2:
	                        System.out.print("Enter Check value: ");
	                        String checkAmount = input.nextLine();
	                        String[] checkInfo = {checkAmount};
	                        AmazonCheck check = AmazonCheck.createCheck(checkInfo);
	                        customer.addCredit(check);
	                        System.out.println(ANSI_PURPLE + "Credit added with success!" + ANSI_RESET);
	                        return;
	                    case 3:
	                        System.out.print("Enter Card value: ");
	                        String cardAmount = input.nextLine();
	                        String[] cardInfo = {cardAmount};
	                        AmazonCard card = AmazonCard.createCard(cardInfo);
	                        customer.addCredit(card);
	                        System.out.println(ANSI_PURPLE + "Credit added with success!" + ANSI_RESET);
	                        return;
	                    default:
	                        throw new AmazonException(ANSI_RED + "Invalid credit type selected" + ANSI_RESET);

	                }
	            }
	        }
	        System.out.println(ANSI_RED + "Customer with ID " + id + " not found." + ANSI_RESET);
	        
	    } catch (NumberFormatException e) {
	        throw new AmazonException("Invalid input format");
	    }
	}
		
	public void showCreditFromCustomer() {

		System.out.print("Enter the customer ID: ");
		int id = Integer.parseInt(input.nextLine());

		for (AmazonCustomer customer : customerList) {
			if (customer.getId() == id) {

				customer.showCredits();

			}
		}

	}

	public void addProductinWishList() {
	
	}
	
	public void removeProductFromWishList() {
		
	}
	
	public void showWishList() {
		
	}
	
	public void addProductinCart() {
		
	}
	
	public void removeProductFromCart() {
		
	}
	
	public void showProductsInCart() {
		
	}
	
	public void payCart() {
		
	}
	
	public void addCommentToProduct() {
		
	}
	
	public void showComments() {
		
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
	




