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
    public void testExample() {
        // GET запросы

        //Необходимо получить список пользователей

        // Объявляя List<UserData> users мы говорим что результат выполнения запроса мы хотим сохранить в список,
        // который принимает в себя объекты типа UserData,
        // а UserData создаст объект на основе Pojo класса, где указаны поля в соответствии с json
        List<UserData> users =
                // ключевое слово для выполнения запроса
                given()
                        //ключевое слово when()
                        .when() // слово после которого, мы указываем параметры при которых мы будем работать
                        .contentType(ContentType.JSON) // указываем обрабатываемый тип данных, в нашем случае это JSON

                        // указываем один из методов http (get,post,put,patch,delete)
                        .get(URL + "/api/users?page=2") // указываем ссылку на ресурс с которым мы хотим общаться, получать и отправлять запросы
                        //ключевое слово then() указывается перед тем как указать что необходимо сделать с полученными данными
                        .then().log().all() // выводим логи полученного ответа

                        // ИЗВЛЕЧЕНИЕ
                        // Мы хотим извлечь данные в pojo класс чтобы произошло сохранение объекта,
                        // чтобы получить объекты из определенного массива данных, необходимо указать путь в getList() методе.
                        // Путем, будет считаться название переменной после которой, идет массив с объектами.
                        // Затем, вторым параметром нужно указать класс в который будут сохранены, извлечены данные ответа.
                        .extract().body().jsonPath().getList("data", UserData.class);

        //ОБРАБОТКА
        //Мы хотим обработать данные и отфильтровать данные, поэтому воспользуемся обработкой потоков stream()
        // x -> используется как переменная для перебора которая сохранит в себе указанное поле.
        // т.е. x -> Assert.assertTrue (говорит выполнить проверку для всех) у кого строка аватар соответствует номеру id
        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

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

//        depositTransactionResponse.stream().forEach(x -> Assert.assertTrue(x.getName().contains("Пополнение вклада")));
    }
}
