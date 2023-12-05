package api.PowerBank.tests.paymentservice.EP5_6;

import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.PaymentService.SavedTemplateInfo;
import api.PowerBank.ApiHelp.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.*;
import static api.PowerBank.ApiHelp.PaymentService.TranslationTemplateInfo.getUUidTranslationTemplate;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class EP5_6 {

    @Test
    public void EP5_6ValidSelectionOfSavedTemplatesTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/payment/template/id";
        String UUid = getUUidTranslationTemplate(accessToken,0);

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("templateId",UUid);
        headersMap.put("Authorization", "Bearer " + accessToken);

        SavedTemplateInfo templateInfo = getRequestP(paramsMap,headersMap,endPoint).as(SavedTemplateInfo.class);

        Assertions.assertEquals(UUid, templateInfo.getTemplateId());
    }

    @Test
    public void EP5_6SelectionOfSavedTemplatesUnauthorizedTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        String endPoint = "/payment/template/id";
        String UUid = "6170fad3-7a37-4147-86be-6ebbe33c5e24";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("templateId",UUid);
        headersMap.put("Authorization", "Bearer ");

        Specifications.checkStatusCode(Specifications.responseSpecUnauthorized401());
        getRequestP(paramsMap,headersMap,endPoint);
    }

    @Test
    public void EP5_6SelectionOfSavedTemplatesWithoutTemplateIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/payment/template/id";
        String UUid = " ";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("templateId",UUid);
        headersMap.put("Authorization", "Bearer " + accessToken);

        Specifications.checkStatusCode(Specifications.responseSpecError400());
        getRequestP(paramsMap,headersMap,endPoint);
    }

    @Test
    public void EP5_6SelectionOfSavedTemplatesWithWrongEndpointIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/payment/template/";
        String UUid = "6170fad3-7a37-4147-86be-6ebbe33c5e24";

        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("templateId",UUid);
        headersMap.put("Authorization", "Bearer " + accessToken);

        Specifications.checkStatusCode(Specifications.responseSpecNotFound404());
        getRequestP(paramsMap,headersMap,endPoint);
    }

}
