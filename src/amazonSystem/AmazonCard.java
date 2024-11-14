package amazonsystem;

public class AmazonCard extends AmazonCredit {
    private AmazonCard(float amount) {
        super(amount);
        setType(PaymentType.CARD);
    }

    public static AmazonCard createCard(String[] cardInfo) throws AmazonException {
        if (cardInfo == null || cardInfo.length != 1) {
            throw new AmazonException("Invalid card input format");
        }

        try {
            float amount = Float.parseFloat(cardInfo[0]);
            if (amount <= 0) {
                throw new AmazonException("Amount must be positive");
            }
            return new AmazonCard(amount);
        } catch (NumberFormatException e) {
            throw new AmazonException("Invalid amount format");
        }
    }
}
