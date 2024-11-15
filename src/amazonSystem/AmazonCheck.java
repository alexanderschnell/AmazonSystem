package amazonsystem;

public class AmazonCheck extends AmazonCredit {
	
	private String accountNumber;
	
    private AmazonCheck(float amount) {
        super(amount);
        setType(PaymentType.CHECK);
    }
    
    @Override
    public String toString() {
        return "Credit: Type: " + getType() + 
               ", Account: " + accountNumber + 
               ", Value: " + getAmount();
    }

    public static AmazonCheck createCheck(String[] checkInfo) throws AmazonException {
    	if (checkInfo == null || checkInfo.length != 2) {
    		throw new AmazonException("Invalid check input format");
    	}
    	try {
    		float amount = Float.parseFloat(checkInfo[0]);
    		if (amount <= 0) {
    			throw new AmazonException("Amount must be positive");
    		}
    		AmazonCheck check = new AmazonCheck(amount);
    		check.setAccountNumber(checkInfo[1]);
    		return check;
    	} catch (NumberFormatException e) {
    		throw new AmazonException("Invalid amount format");
    	}
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
