package api.PowerBank.tests.depositservice.EP3_1;

import api.PowerBank.ApiHelp.DepositService.DepositsInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getDepositListRequest;
import static api.PowerBank.ApiHelp.ApiRequests.getRequest;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class ep3_1 {

    @Test
    public void CleanEP3_1GetInfoAboutDepositProducts1Test() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        DepositsInfo depositsInfo = getRequest(paramsMap, headersMap, endPoint).as(DepositsInfo.class);

        //Проверяем количество депозитных карт банка
        System.out.println(depositsInfo.getName());

    }

    @Test
    public void CleanEP3_1GetInfoAboutDepositProducts2Test() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<DepositsInfo> depositsInfo = getDepositListRequest(paramsMap, headersMap, endPoint);

        Assertions.assertEquals(9,depositsInfo.size());
    }

    @Test
    public void CleanEP3_1GetInfoAboutDepositProductsIsActiveFalseTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "false");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<DepositsInfo> depositsInfo = getDepositListRequest(paramsMap, headersMap, endPoint);

        Assertions.assertEquals(1,depositsInfo.size());
        Assertions.assertEquals("AutumnOffer",depositsInfo.get(0).getName());
    }

    @Test
    public void CleanEP3_1GetInfoAboutDepositProductsIsActiveNotBooleanTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();
        paramsMap.put("isActive", "12");
        headersMap.put("Authorization", "Bearer " + accessToken);

        getRequest(paramsMap, headersMap, endPoint);
    }

    @Test
    public void CleanEP3_1GetInfoAboutDepositProductsUnauthorizedTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnauthorized401());

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();
        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer");

        getRequest(paramsMap, headersMap, endPoint);
    }


}
