package api.PowerBank.ApiHelp.CardService;

public class CardProductsInfo {
    public String name;
    public String type;
    public String shortDescription;
    public String currencyCode;
    public Double cashbackMax;
    public String interestRate;
    public Double servicePrice;
    public String interestFreeDays;
    public String paymentSystem;
    public String imageLink;

    public CardProductsInfo() {
    }

    public CardProductsInfo(String name, String type, String shortDescription, String currencyCode, Double cashbackMax, String interestRate, Double servicePrice, String interestFreeDays, String paymentSystem, String imageLink) {
        this.name = name;
        this.type = type;
        this.shortDescription = shortDescription;
        this.currencyCode = currencyCode;
        this.cashbackMax = cashbackMax;
        this.interestRate = interestRate;
        this.servicePrice = servicePrice;
        this.interestFreeDays = interestFreeDays;
        this.paymentSystem = paymentSystem;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getCashbackMax() {
        return cashbackMax;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public String getInterestFreeDays() {
        return interestFreeDays;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public String getImageLink() {
        return imageLink;
    }
}
