package api.PowerBank.ApiHelp.DepositService;

import java.util.Date;

public class DepositsInfo {
    public String accountNumber;
    public String depositProductName;
    public Date initialOpenDate;
    public Date initialCloseDate;
    public Double currentBalance;
    public String currencyCode;
    public String imageLink;

    public DepositsInfo(){
    }

    public DepositsInfo(String accountNumber, String depositProductName, Date initialOpenDate, Date initialCloseDate, Double currentBalance, String currencyCode, String imageLink) {
        this.accountNumber = accountNumber;
        this.depositProductName = depositProductName;
        this.initialOpenDate = initialOpenDate;
        this.initialCloseDate = initialCloseDate;
        this.currentBalance = currentBalance;
        this.currencyCode = currencyCode;
        this.imageLink = imageLink;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDepositProductName() {
        return depositProductName;
    }

    public Date getInitialOpenDate() {
        return initialOpenDate;
    }

    public Date getInitialCloseDate() {
        return initialCloseDate;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getImageLink() {
        return imageLink;
    }
}
