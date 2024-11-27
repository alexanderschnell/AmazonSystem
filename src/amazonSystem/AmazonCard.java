package amazonsystem;

public class AmazonCard extends AmazonCredit {
	
	private String number;
	private String expiration;
	
	
    private AmazonCard(float amount) {
        super(amount);
        setType(PaymentType.CARD);
    }
    
    // needs validation 
    public static AmazonCard createCard(String[] cardInfo) throws AmazonException {
        if (cardInfo == null || cardInfo.length != 3) {
            throw new AmazonException("Invalid card input format");
        }
        try {
            float amount = Float.parseFloat(cardInfo[0]);
            if (amount <= 0) {
                throw new AmazonException("Amount must be positive");
            }
            AmazonCard card = new AmazonCard(amount);
            card.setNumber(cardInfo[1]);      
            card.setExpiration(cardInfo[2]);   
            return card;
        } catch (NumberFormatException e) {
            throw new AmazonException("Invalid amount format");
        }
    }
    
    @Override
    public String toString() {
        return "Credit Type: " + "[" + getType() + 
               "], Card Number - [" + number +  
               "], Expiration Date - [" + expiration + 
               "], Credit Amount - [" + getAmount() + "]";
    }

    // GETTERS & SETTERS
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
