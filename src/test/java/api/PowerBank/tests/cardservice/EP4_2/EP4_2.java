package api.PowerBank.tests.cardservice.EP4_2;

import api.PowerBank.ApiHelp.ApiRequests;
import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import api.PowerBank.ApiHelp.CardService.CardProductInfo;
import api.PowerBank.ApiHelp.CardService.CardProductsInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getRequestP;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class EP4_2 {

    @Test
    public void getInfoAboutProductsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();

        String endPoint = "/card/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardProductsInfo> cardProductsInfo;
        Response response = getRequestP(paramsMap,headersMap,endPoint);
        cardProductsInfo = response.getBody().jsonPath().getList(".", CardProductsInfo.class);

        System.out.println("______________________________");
        System.out.println(cardProductsInfo.get(1).toString());
        System.out.println("______________________________");
        System.out.println(cardProductsInfo.get(1).getName());
    }
}
