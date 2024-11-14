package amazonsystem;

public class AmazonCheck extends AmazonCredit {
    private AmazonCheck(float amount) {
        super(amount);
        setType(PaymentType.CHECK);
    }

    public static AmazonCheck createCheck(String[] checkInfo) throws AmazonException {
        if (checkInfo == null || checkInfo.length != 1) {
            throw new AmazonException("Invalid check input format");
        }

        try {
            float amount = Float.parseFloat(checkInfo[0]);
            if (amount <= 0) {
                throw new AmazonException("Amount must be positive");
            }
            return new AmazonCheck(amount);
        } catch (NumberFormatException e) {
            throw new AmazonException("Invalid amount format");
        }
    }
}
