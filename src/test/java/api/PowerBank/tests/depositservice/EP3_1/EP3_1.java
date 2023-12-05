package api.PowerBank.tests.depositservice.EP3_1;

import api.PowerBank.ApiHelp.DepositService.DepositsInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getDepositListRequest;
import static api.PowerBank.ApiHelp.ApiRequests.getRequestP;
import static api.PowerBank.ApiHelp.Specifications.*;

public class EP3_1 {

    @Test
    public void CleanEP3_1GetInfoAboutDepositProductsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<DepositsInfo> depositsInfo;
        Response response = getRequestP(paramsMap, headersMap, endPoint);

        depositsInfo = response.getBody().jsonPath().getList(".", DepositsInfo.class);

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

        List<DepositsInfo> depositsInfo;
        Response response = getRequestP(paramsMap, headersMap, endPoint);

        depositsInfo = response.getBody().jsonPath().getList(".", DepositsInfo.class);

        Assertions.assertEquals(1,depositsInfo.size());
        Assertions.assertEquals("AutumnOffer",depositsInfo.get(0).getName());
    }

    @Test
    public void CleanEP3_1GetInfoAboutDepositProductsIsActiveNotBooleanTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();
        paramsMap.put("isActive", "12");
        headersMap.put("Authorization", "Bearer " + accessToken);

        checkStatusCode(responseSpecError400());
        getRequestP(paramsMap, headersMap, endPoint);
    }

    @Test
    public void CleanEP3_1GetInfoAboutDepositProductsUnauthorizedTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnauthorized401());

        String endPoint = "/deposit/products";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();
        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer");

        getRequestP(paramsMap, headersMap, endPoint);
    }


}
