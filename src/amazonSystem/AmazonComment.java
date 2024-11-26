package amazonsystem;

public class AmazonComment {
	
	private String comment;
	private AmazonProduct product;
	private float rating;
	
	
	public AmazonComment(AmazonProduct product, String comment, float rating) {
		this.setProduct(product);
		this.setComment(comment);
		this.setRating(rating);
	}
	
	public AmazonComment(AmazonProduct product) {
		this.setProduct(product);
	}
	
	public String toString() {
		return "Product ID: " + product + ", Comment - [" + comment + "], Rating - [" + rating + "]";
	}

	// GETTERS & SETTERS
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public AmazonProduct getProduct() {
		return product;
	}

	public void setProduct(AmazonProduct product) {
		this.product = product;
	}

}
