package amazonsystem;

public abstract class AmazonCredit {
	
	enum PaymentType {CASH, CHECK, CARD}
	
	private float amount;
	private PaymentType type;
	
	   public AmazonCredit(float amount) {
	        this.amount = amount;
	    }	
	
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
	
	public String toString() {
	    return "Credit: Payment Type - [" + type + "], " + "Credit Amount - [" + amount + "]";
	}
}