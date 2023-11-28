package api.PowerBank.ApiHelp;

import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import api.PowerBank.ApiHelp.CardService.CardProductsInfo;
import api.PowerBank.ApiHelp.DepositService.DepositsInfo;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {


    public static Response getRequest(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {

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


    public static List<CardAgreementInfo> getListRequest(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {
        List<CardAgreementInfo> cardAgreementInfo;

        cardAgreementInfo = (
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
                        .getList(".", CardAgreementInfo.class));

        return cardAgreementInfo;
    }

    public static List<CardProductsInfo> getListRequest1(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint) {
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

    public static  <E>  E getListRequest2(Map<String, String> paramsMap, Map<String,String> headersMap, String endpoint, Class<E> tClass) {
        List<E> response;

        response = (
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
                        .getList(".", tClass));

        return (E) response;
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


}
