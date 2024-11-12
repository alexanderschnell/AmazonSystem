package amazonSystem;

public class AmazonCheck extends AmazonCredit {
	
	private String accountNumber;


	private AmazonCheck(String accountNumber, float amount) {
		super();
		this.setAccountNumber(accountNumber);
		

}
	public String[] createCheck(AmazonCheck Check) {
		return createCheck(Check);
	}

	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}