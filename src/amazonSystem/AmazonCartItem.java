package amazonSystem;

public class AmazonCartItem {
	
	private AmazonProduct product;
	private int quantity;
	
	public AmazonCartItem(AmazonProduct product, int quantity) {
		this.setProduct(product);
		this.setQuantity(quantity);
		
	}
	
	//TODO ALL METHODS
	
	public float calSubTotal() {
		return 0; //FOR NOW
	}
	
	public String toString() {
		return ""; //FOR NOW 
	}
	
	
	//GETTERS AND SETTERS

	public AmazonProduct getProduct() {
		return product;
	}

	public void setProduct(AmazonProduct product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
