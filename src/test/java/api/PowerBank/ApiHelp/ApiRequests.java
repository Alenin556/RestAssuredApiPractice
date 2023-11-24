package api.PowerBank.ApiHelp;

import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {

    // сделать метод абстрактным? чтобы каждый класс имел свою реализацию и перегрузку метода?
    public List<CardAgreementInfo> getRequest(String paramName, String paramValue, String accessToken, String endpoint) {
        List<CardAgreementInfo> cardAgreementInfo;

        cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param(paramName, paramValue)
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
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



}
