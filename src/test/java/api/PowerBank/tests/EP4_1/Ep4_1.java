package api.PowerBank.tests.EP4_1;

import api.PowerBank.ApiHelp.*;
import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import api.PowerBank.ApiHelp.CardService.CardProductInfo;
import api.PowerBank.ApiHelp.CardService.CardProductsInfo;
import api.ReqresSitePractice.Specifications;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class Ep4_1 {

    private final static String URL = "http://172.17.1.45:7254/api/v1";

    @Test
    public void EP4_1GetInfoAboutClientCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации Анатолия Петрова:
//
//        номер телефона: 79772345685
//        пароль: ls23Ghq#wEr
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");
//      accessToken передаем в header() вместе с доп заголовками: "Authorization", "Bearer "

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive","true")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );

        //Проверяем что пользователь не заблокирован
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getUserBlocked(),false));
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getBankBlocked(),false));
    }

    @Test
    public void EP4_1GetInfoAboutBankCardProductsTest() {
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
    public void EP4_1GetInfoAboutShoppingCardProductTest() {
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

        CardProductInfo cardProductInfo  = (given()
                        .param("isActive", "true")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/products" + "/Shopping%20Card/")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс
                        .extract()
                        .body()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .as( CardProductInfo.class)
        );

        //Проверяем название карты и ее тип
        cardProductInfo.getName().equals("Shopping Card");
        cardProductInfo.getType().equals("credit");

    }
}
