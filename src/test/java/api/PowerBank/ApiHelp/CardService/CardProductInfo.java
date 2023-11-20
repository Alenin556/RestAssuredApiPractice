package api.PowerBank.ApiHelp.CardService;

import java.util.ArrayList;

public class CardProductInfo {
    public String name;
    public String type;
    public String currencyCode;
    public Object cashbackMin;
    public Object cashbackMax;
    public Double interestRate;
    public Double servicePrice;
    public Integer interestFreeDays;
    public Double cashMaxDay;
    public Object cashMaxMonth;
    public Double notificationPrice;
    public String paymentSystem;
    public Double amountCreditMax;
    public Double cashWithdrawalFee;
    public String imageLink;
    public ArrayList<Document> documents;

    public CardProductInfo(){};

    public CardProductInfo(String name, String type, String currencyCode, Object cashbackMin, Object cashbackMax, Double interestRate, Double servicePrice, Integer interestFreeDays, Double cashMaxDay, Object cashMaxMonth, Double notificationPrice, String paymentSystem, Double amountCreditMax, Double cashWithdrawalFee, String imageLink, ArrayList<Document> documents) {
        this.name = name;
        this.type = type;
        this.currencyCode = currencyCode;
        this.cashbackMin = cashbackMin;
        this.cashbackMax = cashbackMax;
        this.interestRate = interestRate;
        this.servicePrice = servicePrice;
        this.interestFreeDays = interestFreeDays;
        this.cashMaxDay = cashMaxDay;
        this.cashMaxMonth = cashMaxMonth;
        this.notificationPrice = notificationPrice;
        this.paymentSystem = paymentSystem;
        this.amountCreditMax = amountCreditMax;
        this.cashWithdrawalFee = cashWithdrawalFee;
        this.imageLink = imageLink;
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Object getCashbackMin() {
        return cashbackMin;
    }

    public Object getCashbackMax() {
        return cashbackMax;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public Integer getInterestFreeDays() {
        return interestFreeDays;
    }

    public Double getCashMaxDay() {
        return cashMaxDay;
    }

    public Object getCashMaxMonth() {
        return cashMaxMonth;
    }

    public Double getNotificationPrice() {
        return notificationPrice;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public Double getAmountCreditMax() {
        return amountCreditMax;
    }

    public Double getCashWithdrawalFee() {
        return cashWithdrawalFee;
    }

    public String getImageLink() {
        return imageLink;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }
}
