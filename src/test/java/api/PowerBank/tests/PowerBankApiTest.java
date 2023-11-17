package api.PowerBank.tests;

import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.Transactions.DepositTransactionsResponse;
import api.ReqresSitePractice.Specifications;
import api.ReqresSitePractice.UserData;
import api.PowerBank.Auth;
import api.PowerBank.Token;
import api.PowerBank.Transactions.DepositTransaction;
import api.PowerBank.Transactions.TransactionBody;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PowerBankApiTest {
    private final static String URL = "http://172.17.1.45:7254/api/v1";
    private static String email = "@reqres.in";

    @Test
    public void authToken(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем конструктор с данными для авторизации
        Auth auth = new Auth("79772345685", "ls23Ghq#wEr");

        Token token = given()
                //подставляем данные для регистрации/авторизации
                .body(auth)
                .when()
                .post("/user/login/mobile_phone")
                .then().log().all()

                //извлекаем ответ в класс
                .extract().as(Token.class);

        //Проверяем что ответ не пустой
        Assert.assertNotNull(token.getAccessToken());

        //Проверяем конкретные полученные значения с ожидаемым
        System.out.println(token.getAccessToken());
    }

    @Test
    public void EP6TransactionHistory(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        GetToken getToken = new GetToken();

        //Создаем конструктор с данными
        TransactionBody transactionBody = new TransactionBody(0, 10,"1403202300001A","2022-07-18","2023-07-18","Пополнение вклада");

        List <DepositTransaction> depositTransactionResponse = (given()
                //подставляем данные body
                .header("Authorization", "Bearer " + getToken.accessToken("79772345685","ls23Ghq#wEr"))
                .body(transactionBody)
                .when()
                // указываем endpoint
                .post("/transaction-history/deposit")
                .then().log().all()

                //извлекаем ответ в класс
                .extract().body().jsonPath().getList("depositTransactions", DepositTransaction.class));

        //Проверяем что у полученной истории транзакции все наименования равны "Пополнение вклада"
        depositTransactionResponse.stream().forEach(x -> Assert.assertTrue(x.getName().contains("Пополнение вклада")));
    }

    @Test
    public void EP6TransactionHistory1(){

        //pagination ?? unrecognize field ???
        // Посмотреть/реализовать
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        GetToken getToken = new GetToken();

        //Создаем конструктор с данными
        TransactionBody transactionBody = new TransactionBody(0, 10,"1403202300001A","2022-07-18","2023-07-18","Пополнение вклада");

        DepositTransactionsResponse depositTransactionResponse = (given()
                //подставляем данные body
                .header("Authorization", "Bearer " + getToken.accessToken("79772345685","ls23Ghq#wEr"))
                .body(transactionBody)
                .when()
                // указываем endpoint
                .post("/transaction-history/deposit")
                .then()
                .log()
                .all()

                //извлекаем ответ в класс
                .extract()
                .body()
                .as(DepositTransactionsResponse.class));

        //Проверяем что у полученной истории транзакции все наименования равны "Пополнение вклада"
        depositTransactionResponse.getDepositTransactions().stream().forEach(x -> Assert.assertTrue(x.getName().contains("Пополнение вклада")));

        depositTransactionResponse.getDepositTransactions().get(3);


//        depositTransactionResponse.stream().forEach(x -> Assert.assertTrue(x.getName().contains("Пополнение вклада")));
    }
}
