package api.PowerBank.ApiHelp;

import api.ReqresSitePractice.Specifications;
import org.junit.Assert;


import static io.restassured.RestAssured.given;

public class GetToken {
    private final static String URL = "http://172.17.1.45:7254/api/v1";


    public String accessToken(String mobilePhone, String passwordEncode){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем конструктор с данными для авторизации
        Auth auth = new Auth(mobilePhone,passwordEncode);

        Token token = given()
                //подставляем данные для регистрации/авторизации
                .body(auth)
                .when()
                .post("/user/login/mobile_phone")
                .then().log().all()

                //извлекаем ответ в класс
                .extract().as(Token.class);

        //Проверяем что ответ не пустой
        Assert.assertNotNull(token.getAccessToken());

        //Проверяем конкретные полученные значения с ожидаемым
        System.out.println(token.getAccessToken());
        return token.getAccessToken();
    }
}
