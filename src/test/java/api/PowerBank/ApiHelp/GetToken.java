package api.PowerBank.ApiHelp;

import org.junit.Assert;


import static io.restassured.RestAssured.given;

public class GetToken {
    public String accessToken(String mobilePhone, String passwordEncode){
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
