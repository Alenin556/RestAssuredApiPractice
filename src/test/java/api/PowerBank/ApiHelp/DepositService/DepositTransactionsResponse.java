package api.PowerBank.ApiHelp.DepositService;


import java.util.List;

public class DepositTransactionsResponse {
    List <DepositTransaction> depositTransactions;
    Pagination pagination;

    public DepositTransactionsResponse() {
    }

    public DepositTransactionsResponse(List<DepositTransaction> depositTransactions, Pagination pagination) {
        this.depositTransactions = depositTransactions;
        this.pagination = pagination;
    }

    public List<DepositTransaction> getDepositTransactions() {
        return depositTransactions;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
