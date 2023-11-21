package api.PowerBank.tests.EP4_1;

import api.PowerBank.ApiHelp.*;
import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import api.PowerBank.ApiHelp.CardService.CardRequests;
import api.ReqresSitePractice.Specifications;
import groovy.util.logging.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class Ep4_1 {

    private final static String URL = "http://172.17.1.45:7254/api/v1";

    @Test
    public void EP4_1GetInfoAboutClientCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации Анатолия Петрова:
//        номер телефона: 79772345685
//        пароль: ls23Ghq#wEr
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");
//      accessToken передаем в header() вместе с доп заголовками: "Authorization", "Bearer "

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive", "true")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );

        //Проверяем что пользователь не заблокирован
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getUserBlocked(), false));
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getBankBlocked(), false));
    }

    @Test
    public void EP4_1GetInfoAboutClientCardRequestMethodTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        CardRequests cardRequests = new CardRequests();

        GetToken getToken = new GetToken();

        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");
//      accessToken передаем в метод, где происходит подстановка в header(), вместе с доп заголовками: "Authorization", "Bearer "

        //присваиваем переменной ответ для обработки и проверки
       var response = cardRequests.getCardAgreementsInfoRequest("isActive","true",accessToken);

        //Проверяем что пользователь не заблокирован
        response.stream().forEach(x -> Assertions.assertEquals(x.getUserBlocked(), false));
        response.stream().forEach(x -> Assertions.assertEquals(x.getBankBlocked(), false));
    }




    @Test
    public void EP4_1GetInfoAboutDebetCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации:
//        Телефон: 76666666666
//        Пароль: Ihave6Cards!

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive", "true")
                        .param("type", "debet")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод и тип карты
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );

        //Проверяем тип карт
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getType(), "debet"));
    }

    @Test
    public void EP4_1GetInfoAboutDebetCardMethodRequestTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        CardRequests cardRequests = new CardRequests();

        GetToken getToken = new GetToken();

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        var response = cardRequests.getCardAgreementsInfoRequest("isActive","true",accessToken,"debet");

        response.stream().forEach(x -> Assertions.assertEquals(x.getType(), "debet"));
    }

    @Test
    public void EP4_1GetInfoAboutCreditCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации:
//        Телефон: 76666666666
//        Пароль: Ihave6Cards!

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive", "true")
                        .param("type", "credit")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );

        //Проверяем тип карт
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getType(), "credit"));
    }

    @Test
    public void EP4_1GetInfoAboutCreditCardMethodRequestTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        CardRequests cardRequests = new CardRequests();

        GetToken getToken = new GetToken();

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        var response = cardRequests.getCardAgreementsInfoRequest("isActive","true",accessToken,"credit");

        response.stream().forEach(x -> Assertions.assertEquals(x.getType(), "credit"));
    }

    @Test
    public void EP4_1GetInfoAboutVirtualCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации:
//        Телефон: 76666666666
//        Пароль: Ihave6Cards!

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive", "true")
                        .param("type", "virtual")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );

        //Проверяем тип карт
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getType(), "virtual"));
    }

    @Test
    public void EP4_1GetInfoAboutVirtualCardMethodRequestTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        CardRequests cardRequests = new CardRequests();

        GetToken getToken = new GetToken();

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        var response = cardRequests.getCardAgreementsInfoRequest("isActive","true",accessToken,"virtual");

        response.stream().forEach(x -> Assertions.assertEquals(x.getType(), "virtual"));
    }

    @Test
    public void EP4_1GetInfoAboutNoCardsClientTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации:
//        Телефон: 75335178939
//        Пароль: 1z3rgtY&1y6

        String accessToken = getToken.accessToken("75335178939", "1z3rgtY&1y6");

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive", "true")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );

        //Проверяем что ответ в body пустой, т.е. класс пуст и не имеет ни одного ответа в поле
        Assertions.assertEquals(cardAgreementInfo.size(), 0);
    }

    @Test
    public void EP4_1GetInfoAboutNoCardsClientMethodRequestTest() {

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        CardRequests cardRequests = new CardRequests();

        GetToken getToken = new GetToken();

        String accessToken = getToken.accessToken("75335178939", "1z3rgtY&1y6");

        var response = cardRequests.getCardAgreementsInfoRequest("isActive","true", accessToken);

        Assertions.assertEquals(response.size(), 0);
    }

    @Test
    //баг приходит ответ 200 и пустой массив, вместо 400
    public void EP4_1GetInfoAboutUncorrectTypeCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());

        //класс в котором у нас лежит метод по получению access token
        GetToken getToken = new GetToken();

//        Данные для авторизации под кредами Анатолия Петрова:
//        номер телефона: 79772345685
//        пароль: ls23Ghq#wEr

        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        List<CardAgreementInfo> cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive", "true")
                        .param("type", "1")
                        //подставляем данные header
                        .header("Authorization", "Bearer " + accessToken)
                        //подставляем данные body
                        .when()
                        // указываем endpoint и HTTP метод
                        .get("/card/agreements")
                        .then()
                        .log()
                        .all()

                        //извлекаем ответ в класс pojo
                        .extract()
                        .body()
                        .jsonPath()
                        //в пути ставим точку так как нет явного открытия массива в теле ответа
                        .getList(".", CardAgreementInfo.class)
        );
    }

    @Test
    //баг приходит ответ 200 и пустой массив, вместо 400
    public void EP4_1GetInfoAboutUncorrectTypeCardMethodRequestTest() {

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());

        CardRequests cardRequests = new CardRequests();

        GetToken getToken = new GetToken();

        String accessToken = getToken.accessToken("75335178939", "1z3rgtY&1y6");

        cardRequests.getCardAgreementsInfoRequest("isActive","true", accessToken,"1");
    }

    @Test
    public void EP4_1GetInfoAboutCardWithoutAuthorizationTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnauthorized401());

        given()
                .param("isActive", "true")
                //подставляем данные header
                .header("Authorization", "Bearer ")
                //подставляем данные body
                .when()
                // указываем endpoint и HTTP метод
                .get("/card/agreements")
                .then()
                .log()
                .all();
    }

    @Test
    public void EP4_1GetInfoAboutCardWithoutAuthorizationMethodRequestTest() {

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnauthorized401());

        CardRequests cardRequests = new CardRequests();

        cardRequests.getCardAgreementsInfoRequest("isActive","true");
    }

}
