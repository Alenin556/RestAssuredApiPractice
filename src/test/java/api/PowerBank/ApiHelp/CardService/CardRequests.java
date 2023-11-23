package api.PowerBank.ApiHelp.CardService;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<CardAgreementInfo> getCardAgreementsInfoRequestListParameter(List<String> param, String accessToken) {


        List<CardAgreementInfo> cardAgreementInfo;

        cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive",param.get(0))
                        .param("type", param.get(2))
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

    public static HashMap<String,String> getHashMapParams(){

        //таблица Param
        //поиск по ключу и вывод значения для ввода в параметр
        HashMap<String, String> params = new HashMap<>();

        // Добавляем элементы в Map
        params.put("isActive", "true");
        params.put("type","debet");
        params.put("type","credit");
        params.put("type","virtual");

        // Кажется что нужно два метода из двух коллекций которые бы возвращали два разных значения, потому что метод с HashMap не вернет и не подставит ключ и значение,
        // то есть иначе необходимо два значения на выходе из одного метода, а мы можем вернуть только одно значение

        //От сюда вывод, что можно воспользоваться Списком с общими параметрами

        // так как одна коллекция вернет только одно значение либо keyName либо keyValue
        for(String keyName: params.keySet()) {
            System.out.println(keyName);
            System.out.println(params.get(keyName));
        }
        return params;
    }


    //Реализую список с общими параметрами для запросов
    public static List<String> getListOfParams(){
        List <String> params = new ArrayList<>();
        params.add("true");
        params.add("false");
        params.add("credit");
        params.add("debet");
        params.add("virtual");
        return params;
    }
}
