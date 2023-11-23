package api.PowerBank.tests.EP4_1;

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

import static io.restassured.RestAssured.given;

public class Ep4_2 {

    private final static String URL = "http://172.17.1.45:7254/api/v1";

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
    public void EP4_2GetInfoAboutShoppingCardProductErrorTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();
        String card = "/Shopping%20Card/";

//        Данные для авторизации:
//
//        Телефон: 76666666666
//        Пароль: Ihave6Cards!

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");
//      accessToken передаем в header() вместе с доп заголовками: "Authorization", "Bearer "

        List <CardProductInfo> cardProductInfo  = (given()
                .param("isActive", "true")
                //подставляем данные header
                .header("Authorization", "Bearer " + accessToken)
                //подставляем данные body
                .when()
                // указываем endpoint и HTTP метод и необходимую карту
                .get("/card/products" + card)
                .then()
                .log()
                .all()

                //извлекаем ответ в класс
                .extract()
                .body()
                .jsonPath()
                .getList(".", CardProductInfo.class)

                // Ошибка в тесте возникает потому что мы неправильно определяем тип получаемого ответа и хотим записать его в список,
                // когда тело ответа содержит объект типа LinkedHashMap, поэтому следующий тест будет иметь правильный вид
                //где мы присваиваем ответ объекту, без явного определения коллекции List : CardProductInfo cardProductInfo  = (given()...
        );

        //Проверяем название карты и ее тип
    }
    @Test
    public void EP4_2GetInfoAboutShoppingCardProductTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();
        String card = "/Shopping%20Card/";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

//
//        // Добавляем элементы в Map
//        paramsMap.put("isActive", "true");
//        paramsMap.put("type","debet");

//        Данные для авторизации:
//
//        Телефон: 76666666666
//        Пароль: Ihave6Cards!

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");
//      accessToken передаем в header() вместе с доп заголовками: "Authorization", "Bearer "

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        Response getWithParamResponse = (Response) given()
                .params(paramsMap)
                .headers(headersMap)
                .when()
                .get("/card/products" + card)
                .then()
                .log()
                .all()

                //извлекаем ответ в класс
                .extract()
                .body();

        CardProductInfo cardProductInfo = getWithParamResponse.as(CardProductInfo.class);

//        CardProductInfo cardProductInfo  = (given()
//                .param("isActive", "true")
//                //подставляем данные header
//                .header("Authorization", "Bearer " + accessToken)
//                //подставляем данные body
//                .when()
//                // указываем endpoint и HTTP метод и необходимую карту
//                .get("/card/products" + card)
//                .then()
//                .log()
//                .all()
//
//                //извлекаем ответ в класс
//                .extract()
//                .body()
//                //в пути ставим точку так как нет явного открытия массива в теле ответа
//                .as(CardProductInfo.class)
//        );

        //Проверяем название карты и ее тип
        cardProductInfo.getName().equals("Shopping Card");
        cardProductInfo.getType().equals("credit");
    }
}
