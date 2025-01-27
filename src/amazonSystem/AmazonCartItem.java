package amazonsystem;

public class AmazonCartItem {
	
	private AmazonProduct product;
	private int quantity;
	
	public AmazonCartItem(AmazonProduct product, int quantity) {
		this.setProduct(product);
		this.setQuantity(quantity);
	}

	public float calSubTotal() {
	    return product.getActual_price() * quantity;
	}
	
	public String toString() {
	    return "[" + product.getId() + ", " + product.getName() + "], Quantity: [" + quantity + "]";
	}
	
	// GETTERS AND SETTERS
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
