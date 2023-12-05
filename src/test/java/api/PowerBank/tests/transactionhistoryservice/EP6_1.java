package api.PowerBank.tests.transactionhistoryservice;

import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.DepositService.DepositTransactionsResponse;
import api.PowerBank.ApiHelp.Specifications;
import api.PowerBank.ApiHelp.DepositService.TransactionBody;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static api.PowerBank.ApiHelp.ApiRequests.postRequest;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class EP6_1 {

    @Test
    public void CleanEP6TransactionHistory(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685","ls23Ghq#wEr");

        //Создаем конструктор с данными
        TransactionBody transactionBody = new TransactionBody(0, 10,"1403202300001A","2022-07-18","2023-07-18","Пополнение вклада");
        String endPoint = "/transaction-history/deposit";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer " + accessToken);
        DepositTransactionsResponse depositTransactionResponse = postRequest(paramsMap,headersMap,transactionBody,endPoint).as(DepositTransactionsResponse.class);

        //Проверяем что у полученной истории транзакции все наименования равны "Пополнение вклада"
        depositTransactionResponse.getDepositTransactions().stream().forEach(x -> Assert.assertTrue(x.getName().contains("Пополнение вклада")));
    }
}
