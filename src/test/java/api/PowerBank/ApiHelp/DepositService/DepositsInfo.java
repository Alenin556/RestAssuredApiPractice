package api.PowerBank.ApiHelp.DepositService;

import java.util.Date;

public class DepositsInfo {
    public String name;
    public Double amountMin;
    public String currencyCode;
    public Double maxInterestRate;
    public Integer minDurationMonths;
    public String imageLink;
    public String description;

    public DepositsInfo(String name, Double amountMin, String currencyCode, Double maxInterestRate, Integer minDurationMonths, String imageLink, String description) {
        this.name = name;
        this.amountMin = amountMin;
        this.currencyCode = currencyCode;
        this.maxInterestRate = maxInterestRate;
        this.minDurationMonths = minDurationMonths;
        this.imageLink = imageLink;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getAmountMin() {
        return amountMin;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getMaxInterestRate() {
        return maxInterestRate;
    }

    public Integer getMinDurationMonths() {
        return minDurationMonths;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return description;
    }
}
