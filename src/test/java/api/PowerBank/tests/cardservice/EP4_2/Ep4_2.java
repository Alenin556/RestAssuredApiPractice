package api.PowerBank.tests.cardservice.EP4_2;

import api.PowerBank.ApiHelp.CardService.CardProductInfo;
import api.PowerBank.ApiHelp.CardService.CardProductsInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.ReqresSitePractice.Specifications;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.*;
import static io.restassured.RestAssured.given;

public class Ep4_2 {
    private final static String URL = "http://172.17.1.46:7254/api/v1";

    @Test
    public void EP4_2GetInfoAboutBankCardProductsTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации:
//
//        Телефон: 76666666666
//        Пароль: Ihave6Cards!

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");
//      accessToken передаем в header() вместе с доп заголовками: "Authorization", "Bearer "

        List<CardProductsInfo> cardProductsInfo  = (
                given()
                        .param("isActive","true")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/products")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardProductsInfo.class)
        );


        //Проверяем количество карт
        Assertions.assertEquals(cardProductsInfo.size(),10);
    }

    @Test
    public void EP4_2GetInfoAboutBankCardProductsMethod1Test() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        CardProductsInfo cardProductsInfo = getRequest(paramsMap,headersMap,endPoint).as(CardProductsInfo.class);


        //Проверяем количество карт
        System.out.println("test");


    }

    @Test
    public void EP4_2GetInfoAboutBankCardProductsMethod2Test() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardProductsInfo> cardProductsInfo = getListRequest1(paramsMap,headersMap,endPoint);

        //Проверяем количество карт
        Assertions.assertEquals(cardProductsInfo.size(),10);

    }

//    @Test
//    public void EP4_2GetInfoAboutBankCardProductsMethod3Test() {
//        //Пред установки и пред проверка запроса на статус ответа
//        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
//
//        //класс в котором у нас лежит метод по получению access token
//        GetToken getToken = new GetToken();
//        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");
//
//        String endPoint = "/card/products";
//
//        Map<String, String> paramsMap = new HashMap<>();
//        Map<String, String> headersMap = new HashMap<>();
//
//        paramsMap.put("isActive", "true");
//        headersMap.put("Authorization", "Bearer " + accessToken);
//
//        CardProductsInfo cardProductsInfo = getListRequest2(paramsMap,headersMap,endPoint,CardProductsInfo.class);
//
//
//        //Проверяем количество карт
////        Assertions.assertEquals(cardProductsInfo.size(),10);
//
//    }

}