package api.PowerBank.ApiHelp.CardService;

import api.PowerBank.ApiHelp.GetToken;
import api.ReqresSitePractice.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CardRequests {

    private final static String URL = "http://172.17.1.45:7254/api/v1";

    public void getCardAgreementsInfoRequest(String paramName, String paramValue) {
        given()
                //подставляем параметры запроса
                .param(paramName, paramValue)
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

    public List<CardAgreementInfo> getCardAgreementsInfoRequest(String paramName, String paramValue, String accessToken) {
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
                        .get("/card/agreements")
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

    //Перегрузить метод или сделать отдельным для типа карты?
    public List<CardAgreementInfo> getCardAgreementsInfoRequest(String paramName, String paramValue, String accessToken, String cardType) {

        // массив в параметр вместо param*
        // список параметров*



        List<CardAgreementInfo> cardAgreementInfo;

        cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param(paramName, paramValue)
                        .param("type", cardType)
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
                        .getList(".", CardAgreementInfo.class));

        return cardAgreementInfo;
    }
}
