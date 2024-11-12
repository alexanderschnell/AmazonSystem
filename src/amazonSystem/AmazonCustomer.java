package amazonSystem;

public class AmazonCustomer {
	
	private int id;
	private String name;
	private String address;
	
	private AmazonCustomer(int id, String name, String address) {
		this.setId(id);
		this.setName(name);
		this.setAddress(address); 
	}
	
	//TODO ALL METHODS 
	
	public String[] createAmazonCustomer(AmazonCustomer customer) {
		return createAmazonCustomer(customer);
	}
	
	public void addCredit() {
		
	}
	
	public void showCredits() {
		
	}
	
	public void addProductsInWishList() {
		
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
		return ""; //FOR NOW
	}

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
