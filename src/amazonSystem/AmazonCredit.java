package amazonSystem;

public abstract class AmazonCredit {
	
	enum PaymentType {CASH, CHECK, CARD}
	
	private float amount;
	private PaymentType type;
	


	public float Payment() {
		return amount;
		
	}
	
	public String toString() {
		return "";
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
}