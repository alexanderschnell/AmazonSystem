package amazonsystem;

public class AmazonCheck extends AmazonCredit {
	
	private String accountNumber;
	
	
    private AmazonCheck(float amount) {
        super(amount);
        setType(PaymentType.CHECK);
    }
    
    // fix this constructor
    public AmazonCheck(String accountNumber, String amount) {
        super(Float.parseFloat(amount));  // Parse amount and pass to parent
        setType(PaymentType.CHECK);
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Credit Type: [" + getType() + 
               "], Account Number - [" + getAccountNumber() + 
               "], Amount - [" + getAmount() + "]";
    }

    public static AmazonCheck createCheck(String[] data) {
        if (data == null || data.length != 2 || 
            data[0] == null || data[0].trim().isEmpty() ||
            data[1] == null || data[1].trim().isEmpty()) {
            return null;
        }

        try {
            return new AmazonCheck(data[0], data[1]);
        } catch (Exception e) {
            return null;
        }
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
