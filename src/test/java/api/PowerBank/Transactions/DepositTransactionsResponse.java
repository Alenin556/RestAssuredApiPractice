package api.PowerBank.Transactions;


import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

import java.util.List;

public class DepositTransactionsResponse {
    List <DepositTransaction> depositTransactions;
//    @JsonIgnoreProperties
//    @JsonProperty("pagination")
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
