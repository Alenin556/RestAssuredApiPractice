package api.PowerBank.tests.cardservice.EP4_4;

import api.PowerBank.ApiHelp.ApiRequests;
import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
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

public class Ep4_4 {

    @Test
    public void CleanEP4_4GetInfoAboutClientCardRequestMethodTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/agreements";
        String cardNumber = "/5123459671906923";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardAgreementInfo> cardAgreementInfo;

        Response response = ApiRequests.getRequestP(paramsMap,headersMap,endPoint+cardNumber);
        cardAgreementInfo = response.getBody().jsonPath().getList(".", CardAgreementInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        //Проверяем номер карты
        Assertions.assertEquals(cardAgreementInfo.get(0).cardNumber,cardNumber);
    }
}
