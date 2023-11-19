package api.PowerBank.tests.EP4_1;

import api.PowerBank.ApiHelp.*;
import api.ReqresSitePractice.Specifications;
import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Ep4_1 {

    private final static String URL = "http://172.17.1.45:7254/api/v1";
    private static String email = "@reqres.in";

    @Test
    public void EP4_1GetInfoAboutCardTest() {
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

        List<CardInfo> cardInfo = (
                given()
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
                        .getList(".",CardInfo.class)
        );
        //Проверяем что пользователь не заблокирован
        cardInfo.stream().forEach(x -> Assertions.assertEquals(x.getUserBlocked(),false));
        cardInfo.stream().forEach(x -> Assertions.assertEquals(x.getBankBlocked(),false));
    }
}
