package amazonsystem;

public class AmazonCash extends AmazonCredit { 
    private AmazonCash(float amount) {
        super(amount);
        setType(PaymentType.CASH);
    }

    public static AmazonCash createCash(String[] cashInfo) throws AmazonException {
        if (cashInfo == null || cashInfo.length != 1) {
            throw new AmazonException("Invalid cash input format");
        }

        try {
            float amount = Float.parseFloat(cashInfo[0]);
            if (amount <= 0) {
                throw new AmazonException("Amount must be positive");
            }
            return new AmazonCash(amount);
        } catch (NumberFormatException e) {
            throw new AmazonException("Invalid amount format");
        }
    }
}