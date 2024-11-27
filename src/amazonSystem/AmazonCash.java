package amazonsystem;

public class AmazonCash extends AmazonCredit { 
    private AmazonCash(float amount) {
        super(amount);
        setType(PaymentType.CASH);
    }
    
    // needs validation 
    public static AmazonCash createCash(String[] data) {
        if (data == null || data.length != 1 || data[0] == null || data[0].trim().isEmpty()) {
            return null;
        }
        try {
            float amount = Float.parseFloat(data[0]);
            return new AmazonCash(amount);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}