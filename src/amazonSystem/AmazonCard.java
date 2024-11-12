package amazonSystem;

public class AmazonCard extends AmazonCredit {
	
	private String number;
	private String expiration;
	
	private AmazonCard(String number, String expiration, float amount) {
		super();
		this.setNumber(number);
		this.setExpiration(expiration);
				
	}
	
	public String[] createCredit(AmazonCard Card) {
		return createCredit(Card);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
}
