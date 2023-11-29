package api.PowerBank.ApiHelp.PaymentService;

public class SavedTemplateInfo {
    public String templateName;
    public String senderCardNumber;
    public String recipientNumber;
    public String currencyCode;
    public Double amountTransfer;
    public Double amountCommission;
    public Double amountWithdrawal;
    public String transferType;
    public String templateId;

    public SavedTemplateInfo(String templateName, String senderCardNumber, String recipientNumber, String currencyCode, Double amountTransfer, Double amountCommission, Double amountWithdrawal, String transferType, String templateId) {
        this.templateName = templateName;
        this.senderCardNumber = senderCardNumber;
        this.recipientNumber = recipientNumber;
        this.currencyCode = currencyCode;
        this.amountTransfer = amountTransfer;
        this.amountCommission = amountCommission;
        this.amountWithdrawal = amountWithdrawal;
        this.transferType = transferType;
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getAmountTransfer() {
        return amountTransfer;
    }

    public Double getAmountCommission() {
        return amountCommission;
    }

    public Double getAmountWithdrawal() {
        return amountWithdrawal;
    }

    public String getTransferType() {
        return transferType;
    }

    public String getTemplateId() {
        return templateId;
    }
}
