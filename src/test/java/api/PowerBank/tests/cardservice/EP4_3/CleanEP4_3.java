package api.PowerBank.tests.cardservice.EP4_3;

import api.PowerBank.ApiHelp.ApiRequests;
import api.PowerBank.ApiHelp.CardService.CardProductInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getRequestP;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class CleanEP4_3 {
    @Test
    public void getInfoAboutShoppingCardProductTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();

        String endPoint = "/card/products";
        String card = "/Shopping%20Card/";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        CardProductInfo cardProductInfo = ApiRequests.getRequestP(paramsMap,headersMap,endPoint+card).as(CardProductInfo.class);

        Assertions.assertEquals(cardProductInfo.getName(),"Shopping Card");
        Assertions.assertEquals(cardProductInfo.getType(),"credit");
    }
}
