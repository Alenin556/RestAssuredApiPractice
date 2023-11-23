package api.PowerBank.ApiHelp.CardService;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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


    public static HashMap<String,String> getHashMapParams(){

        //таблица Param
        //поиск по ключу и вывод значения для ввода в параметр
        HashMap<String, String> params = new HashMap<>();

        // Добавляем элементы в Map
        params.put("isActive", "true");
        params.put("type","debet");
        params.put("type","credit");
        params.put("type","virtual");

        // или попробовать вставить в перебор запрос и подстановку?
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


    public static String getParamsSwitchCase(int paramType){

        switch (paramType){
            case 1:
              String isActive = "isActive";
                return isActive;
            case 2:
                String isTrue = "true";
                return isTrue;
            case 3:
                String isFalse = "false";
                return isFalse;
            case 4:
                String debet = "debet";
                return debet;
            case 5:
                String credit = "credit";
                return credit;
            case 6:
                String vitrual = "virtual";
                return vitrual;
            default:
                throw new RuntimeException("Incorrect paramType");
        }
    }

    public List<CardAgreementInfo> getCardAgreementsInfoRequestSwitchCaseParameter(String param, String accessToken) {

        List<CardAgreementInfo> cardAgreementInfo;

        cardAgreementInfo = (
                given()
                        //подставляем параметры запроса
                        .param("isActive",param)
                        .param("type", param)
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
