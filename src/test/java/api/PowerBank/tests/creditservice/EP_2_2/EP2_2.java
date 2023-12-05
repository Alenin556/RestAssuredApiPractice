package api.PowerBank.tests.creditservice.EP_2_2;
import api.PowerBank.ApiHelp.CreditService.CreditProductInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getRequest;
import static api.PowerBank.ApiHelp.Specifications.*;

public class EP2_2 {

    @Test
    public void EP2_2ValidRequestTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/credit/products";
        String creditProductName = "/Семейный";

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer " + accessToken);

        CreditProductInfo creditProductInfo = getRequest(headersMap,endPoint+creditProductName).as(CreditProductInfo.class);

        Assertions.assertEquals("Семейный",creditProductInfo.getName());
    }

    @Test
    public void EP2_2NotValidRequestWithSpaceInProductNameTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/credit/products";
        String creditProductName = "/Семей ный";

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer " + accessToken);

        checkStatusCode(responseSpecNotFound404());
        getRequest(headersMap,endPoint+creditProductName);
    }

    @Test
    public void EP2_2NotValidRequestWithSymbolsInProductNameTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/credit/products";
        String creditProductName = "/Семе!%№ый";

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer " + accessToken);

        checkStatusCode(responseSpecNotFound404());
        getRequest(headersMap,endPoint+creditProductName);
    }

    @Test
    public void EP2_2NotValidRequestWithNumbersInProductNameTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/credit/products";
        String creditProductName = "/Семейный666";

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer " + accessToken);

        checkStatusCode(responseSpecNotFound404());
        getRequest(headersMap,endPoint+creditProductName);
    }

    @Test
    public void EP2_2NotValidRequestWithNoneProductNameTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/credit/products";
        String creditProductName = "/";

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer " + accessToken);

        checkStatusCode(responseSpecNotFound404());
        getRequest(headersMap,endPoint + creditProductName);
    }

    @Test
    public void EP2_2UnauthorizedRequestTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnauthorized401());

        String endPoint = "/credit/products";
        String creditProductName = "/Семейный";

        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("Authorization", "Bearer ");

        getRequest(headersMap,endPoint + creditProductName);
    }
}
