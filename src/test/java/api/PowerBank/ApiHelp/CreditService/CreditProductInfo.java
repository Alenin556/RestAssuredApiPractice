package api.PowerBank.ApiHelp.CreditService;

import java.util.ArrayList;

public class CreditProductInfo {

    public String name;
    public Integer interestRate;
    public Integer amountMin;
    public Integer amountMax;
    public Integer minDurationMonths;
    public Integer maxDurationMonths;
    public Boolean isRevocable;
    public Boolean isGuarantee;
    public Boolean isActive;
    public String description;
    public ArrayList<NameDocument> nameDocuments;
    public ArrayList<Document> documents;
    public String imageLink;

    public CreditProductInfo() {
    }

    public CreditProductInfo(String name, Integer interestRate, Integer amountMin, Integer amountMax, Integer minDurationMonths, Integer maxDurationMonths, Boolean isRevocable, Boolean isGuarantee, Boolean isActive, String description, ArrayList<NameDocument> nameDocuments, ArrayList<Document> documents, String imageLink) {
        this.name = name;
        this.interestRate = interestRate;
        this.amountMin = amountMin;
        this.amountMax = amountMax;
        this.minDurationMonths = minDurationMonths;
        this.maxDurationMonths = maxDurationMonths;
        this.isRevocable = isRevocable;
        this.isGuarantee = isGuarantee;
        this.isActive = isActive;
        this.description = description;
        this.nameDocuments = nameDocuments;
        this.documents = documents;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public Integer getInterestRate() {
        return interestRate;
    }

    public Integer getAmountMin() {
        return amountMin;
    }

    public Integer getAmountMax() {
        return amountMax;
    }

    public Integer getMinDurationMonths() {
        return minDurationMonths;
    }

    public Integer getMaxDurationMonths() {
        return maxDurationMonths;
    }

    public Boolean getRevocable() {
        return isRevocable;
    }

    public Boolean getGuarantee() {
        return isGuarantee;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<NameDocument> getNameDocuments() {
        return nameDocuments;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public String getImageLink() {
        return imageLink;
    }
}

