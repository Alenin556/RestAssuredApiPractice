package api.PowerBank.Transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class DepositTransactionsResponse {
    List <DepositTransaction> depositTransactions;
    @JsonIgnoreProperties
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
}
