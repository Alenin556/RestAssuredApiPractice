package api.PowerBank.ApiHelp;

import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;

import java.util.List;

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
}
