package api.PowerBank.tests.cardservice.EP4_2;

import api.PowerBank.ApiHelp.CardService.CardProductsInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.*;
import static api.PowerBank.ApiHelp.Specifications.URL;
import static io.restassured.RestAssured.given;

public class Ep4_2 {

    @Test
    public void EP4_2GetInfoAboutBankCardProducts1Test() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        CardProductsInfo cardProductsInfo = getRequest(paramsMap,headersMap,endPoint).as(CardProductsInfo.class);

    }

    @Test
    public void EP4_2GetInfoAboutBankCardProducts2Test() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardProductsInfo> cardProductsInfo = getListCardProductsRequest(paramsMap,headersMap,endPoint);

        //Проверяем количество карт
        Assertions.assertEquals(10,cardProductsInfo.size());

    }

//    @Test
//    public void EP4_2GetInfoAboutBankCardProductsMethod3Test() {
//        //Пред установки и пред проверка запроса на статус ответа
//        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
//
//        //класс в котором у нас лежит метод по получению access token
//        GetToken getToken = new GetToken();
//        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");
//
//        String endPoint = "/card/products";
//
//        Map<String, String> paramsMap = new HashMap<>();
//        Map<String, String> headersMap = new HashMap<>();
//
//        paramsMap.put("isActive", "true");
//        headersMap.put("Authorization", "Bearer " + accessToken);
//
//        CardProductsInfo cardProductsInfo = getListRequest2(paramsMap,headersMap,endPoint,CardProductsInfo.class);
//
//
//        //Проверяем количество карт
////        Assertions.assertEquals(cardProductsInfo.size(),10);
//
//    }

}
