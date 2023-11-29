package api.PowerBank.tests;

import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.DepositService.DepositTransactionsResponse;
import api.PowerBank.ApiHelp.PaymentService.SavedTemplateInfo;
import api.PowerBank.ApiHelp.Specifications;
import api.PowerBank.ApiHelp.Auth;
import api.PowerBank.ApiHelp.Token;
import api.PowerBank.ApiHelp.DepositService.DepositTransaction;
import api.PowerBank.ApiHelp.DepositService.TransactionBody;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.postRequest;
import static api.PowerBank.ApiHelp.PaymentService.TranslationTemplateInfo.getUUidTranslationTemplate;
import static io.restassured.RestAssured.given;

public class Ep6_1 {
    private final static String URL = "http://172.17.1.45:7254/api/v1";


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
