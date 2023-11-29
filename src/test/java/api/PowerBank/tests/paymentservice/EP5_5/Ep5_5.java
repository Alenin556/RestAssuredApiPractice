package api.PowerBank.tests.paymentservice.EP5_5;

import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.PaymentService.TranslationTemplateInfo;
import api.PowerBank.ApiHelp.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.*;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class Ep5_5 {


    @Test
    public void EP5_6ValidSelectionOfSavedTemplatesTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/payment/template";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<TranslationTemplateInfo> translationTemplateInfo;

        Response response = getRequest(paramsMap,headersMap,endPoint);
        translationTemplateInfo = response.getBody().jsonPath().getList(".", TranslationTemplateInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        String UUid = translationTemplateInfo.get(0).templateId;
        System.out.println(UUid);
    }
}
