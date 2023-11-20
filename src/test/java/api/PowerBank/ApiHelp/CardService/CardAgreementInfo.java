package api.PowerBank.ApiHelp.CardService;

public class CardAgreementInfo {
    public String name;
    public String cardNumber;
    public String expirationDate;
    public String paymentSystem;
    public String currencyCode;
    public Integer currentBalance;
    public String type;
    public Boolean isUserBlocked;
    public Boolean isBankBlocked;

    public CardAgreementInfo(){
    };

    public CardAgreementInfo(String name, String cardNumber, String expirationDate, String paymentSystem, String currencyCode, Integer currentBalance, String type, Boolean isUserBlocked, Boolean isBankBlocked) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.paymentSystem = paymentSystem;
        this.currencyCode = currencyCode;
        this.currentBalance = currentBalance;
        this.type = type;
        this.isUserBlocked = isUserBlocked;
        this.isBankBlocked = isBankBlocked;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public String getType() {
        return type;
    }

    public Boolean getUserBlocked() {
        return isUserBlocked;
    }

    public Boolean getBankBlocked() {
        return isBankBlocked;
    }
}
