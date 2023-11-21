package api.ReqresSitePractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import java.util.HashMap;
import java.util.Map;

public class Specifications {

    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecError400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecUnauthorized401() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .build();
    }

    public static ResponseSpecification responseSpecBAD500() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static RequestSpecification installParam(HashMap<String,String> paramMap) {
        //таблица Param
        //поиск по ключу и вывод значения для ввода в параметр
        Map<String, String> params = new HashMap<>();

        // Добавляем элементы в Map
        params.put("isActive", "true");
        params.put("type","debet");
        params.put("type","credit");
        params.put("type","virtual");

        for(String keyName: params.keySet()){
            String arrayKeyName = keyName;
            System.out.println(arrayKeyName);
        }
        return new RequestSpecBuilder()
                .addPathParam(paramMap.get(0))
                .addPathParam("type", "debet")
                .build();
    }

    public static RequestSpecification requestSpecification = given
}
