package api.PowerBank.ApiHelp.DepositService;

public class TransactionBody {
    public int page;
    public int perPage;
    public String accountNumber;
    public String startDate;
    public String endDate;
    public String transactionType;

    public TransactionBody(int page, int perPage, String accountNumber, String startDate, String endDate, String transactionType) {
        this.page = page;
        this.perPage = perPage;
        this.accountNumber = accountNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactionType = transactionType;
    }
}
