package api.PowerBank.ApiHelp;

import api.PowerBank.ApiHelp.CardService.CardProductsInfo;
import api.PowerBank.ApiHelp.DepositService.DepositTransaction;
import api.PowerBank.ApiHelp.DepositService.DepositsInfo;
import api.PowerBank.ApiHelp.DepositService.TransactionBody;
import api.PowerBank.ApiHelp.PaymentService.SavedTemplateInfo;
import api.PowerBank.ApiHelp.PaymentService.TranslationTemplateInfo;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {

    public static Response getRequest() {

        Response getResponse = (Response) given()
                .params("isActive","true")
                .headers("Authorization", "Bearer ")
                .when()
                .get()
                .then()
                .log()
                .all()

                //извлекаем ответ в класс
                .extract()
                .body();

        return getResponse;
    }

    public static Response getRequestP(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {

        Response getResponse = (Response) given()
                .params(paramsMap)
                .headers(headersMap)
                .when()
                .get(endpoint)
                .then()
                .log()
                .all()

                //извлекаем ответ в класс
                .extract()
                .body();

        return getResponse;
    }


    public static Response postRequest(Map<String, String> paramsMap, Map<String,String> headersMap, Object body, String endpoint) {

        Response postResponse = (Response) given()
                .params(paramsMap)
                .headers(headersMap)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log()
                .all()

                //извлекаем ответ в класс
                .extract()
                .body();

        return postResponse;
    }

    //Example for post request
    public void EP6TransactionHistoryExample(){
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
    // + 1 post

//    вынести в тестовые методы преобразование ответа метода getRequestP в лист
//    List<CardAgreementInfo> cardAgreementInfo;
//
//    Response response = getRequestP(paramsMap,headersMap,endpoint);
//    cardAgreementInfo = response.jsonPath().getList(".", CardAgreementInfo.class);



    public static List<CardProductsInfo> getListCardProductsRequest(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {
        List<CardProductsInfo> cardProductsInfo;

        cardProductsInfo = (
                given()
                        //подставляем параметры запроса
                        .params(paramsMap)
                        //подставляем данные header
                        .headers(headersMap)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get(endpoint)
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardProductsInfo.class));

        return cardProductsInfo;
    }


    public static List<DepositsInfo> getDepositListRequest(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {
        List<DepositsInfo> depositsInfo;

        depositsInfo = (
                given()
                        //подставляем параметры запроса
                        .params(paramsMap)
                        //подставляем данные header
                        .headers(headersMap)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get(endpoint)
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", DepositsInfo.class));

        return depositsInfo;
    }

    public static List<TranslationTemplateInfo> getTranslationTemplateListRequest(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {
        List<TranslationTemplateInfo> translationTemplateInfo;

        translationTemplateInfo = (
                given()
                        //подставляем параметры запроса
                        .params(paramsMap)
                        //подставляем данные header
                        .headers(headersMap)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get(endpoint)
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", TranslationTemplateInfo.class));

        return translationTemplateInfo;
    }

    public static List<SavedTemplateInfo> getSavedTemplateListRequest(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {
        List<SavedTemplateInfo> savedTemplateInfo;

        savedTemplateInfo = (
                given()
                        //подставляем параметры запроса
                        .params(paramsMap)
                        //подставляем данные header
                        .headers(headersMap)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get(endpoint)
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", SavedTemplateInfo.class));

        return savedTemplateInfo;
    }


}
