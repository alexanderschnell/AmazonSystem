package amazonsystem;

public class AmazonProduct {

	private int id; 
	private String name; 
	private AmazonProductCategory category;
	private AmazonProductSubCategory subCategory;
	private String imageURL;
	private String link; 
	private float rating; 
	private int nRatings; 
	private float discountPrice; 
	private float actualPrice; 


	private AmazonProduct(int id, String name, AmazonProductCategory category, 
			AmazonProductSubCategory subCategory, String imageURL, 
			String link, float rating, int nRatings, 
			float discountPrice, float actualPrice) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.imageURL = imageURL;
		this.link = link;
		this.rating = rating;
		this.nRatings = nRatings;
		this.discountPrice = discountPrice;
		this.actualPrice = actualPrice;
	}


	public static AmazonProduct createAmazonProduct(int id, String name, AmazonProductCategory category, 
			AmazonProductSubCategory subCategory, String imageURL, String link, 
			float rating, int nRatings, float discountPrice, float actualPrice) {
		return new AmazonProduct(id, name, category, subCategory, imageURL,
				link, rating, nRatings, discountPrice, actualPrice);
	}


	public static AmazonProduct createAmazonProduct(String[] data) {
		if (data == null || data.length != 10) {
			return null;
		}

		if (!AmazonSystemUtil.isValidInt(data[0]) ||           // id
				!AmazonSystemUtil.isValidString(data[1]) ||    // name
				!AmazonSystemUtil.isValidString(data[2]) ||    // category
				!AmazonSystemUtil.isValidString(data[3]) ||    // subcategory
				!AmazonSystemUtil.isValidString(data[4]) ||    // image
				!AmazonSystemUtil.isValidString(data[5]) ||    // url
				!AmazonSystemUtil.isValidFloat(data[6]) ||     // rating
				!AmazonSystemUtil.isValidInt(data[7]) ||       // nratings
				!AmazonSystemUtil.isValidFloat(data[8]) ||     // discPrice
				!AmazonSystemUtil.isValidFloat(data[9])) {     // actPrice
			return null;
		}

		try {
			int id = Integer.parseInt(data[0]);
			AmazonProductCategory category = new AmazonProductCategory(data[2]);
			AmazonProductSubCategory subCategory = new AmazonProductSubCategory(data[3]);
			float rating = Float.parseFloat(data[6]);
			int nratings = Integer.parseInt(data[7]);
			float discPrice = Float.parseFloat(data[8]);
			float actPrice = Float.parseFloat(data[9]);

			return new AmazonProduct(id, data[1], category, subCategory, data[4], 
					data[5], rating, nratings, discPrice, actPrice);
		} catch (Exception e) {
			return null;
		}
	}
	public String toString() {

		String str = 
				"[" 
						+ id + ", "
						+ name + ", "
						+ category.getCategory() + ", "
						+ subCategory.getSubCategory() + ", "
						+ imageURL + ", "
						+ link + ", "
						+ rating + ", "
						+ nRatings + ", "
						+ discountPrice + ", "
						+ actualPrice +
						"]"
						;

		return str;			
	}

	// GETTERS & SETTERS
	public float getActual_price() {
		return actualPrice;
	}

	public void setActual_Price(float actualPrice) { 
		this.actualPrice = actualPrice; 
	}

	public float getDiscount_price() {
		return discountPrice;
	}

	public void setDiscount_price(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public AmazonProductCategory getMain_category() {
		return category; 
	}

	public void setMain_category(AmazonProductCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name; 
	}

	public int getNo_of_rating() {
		return nRatings;
	}

	public void setNo_of_rating(int nRatings) {
		this.nRatings = nRatings;
	}

	public float getRatings() {
		return rating;
	}

	public void setRatings(float rating) {
		this.rating = rating; 
	}

	public AmazonProductSubCategory getSub_category() {
		return subCategory;
	}

	public void setSub_Category(AmazonProductSubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}