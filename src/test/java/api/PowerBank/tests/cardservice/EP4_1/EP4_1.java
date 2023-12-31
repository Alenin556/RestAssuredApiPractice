package api.PowerBank.tests.cardservice.EP4_1;

import api.PowerBank.ApiHelp.ApiRequests;
import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;
import api.PowerBank.ApiHelp.CardService.CardRequests;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getRequestP;
import static api.PowerBank.ApiHelp.Specifications.*;


public class EP4_1 {

    //поправить после поднятия CardService

    @Test
    public void CleanEP4_1GetInfoAboutClientCardsRequestMethodTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/card/agreements";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);


        List<CardAgreementInfo> cardAgreementInfo;

        Response response = getRequestP(paramsMap,headersMap,endPoint);
        cardAgreementInfo = response.getBody().jsonPath().getList(".", CardAgreementInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        //Проверяем что карты пользователя не заблокированы
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getUserBlocked(), false));
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals(x.getBankBlocked(), false));

        //Проверяем количество карт клиента
        Assertions.assertEquals(2,cardAgreementInfo.size());
    }

    @Test
    public void CleanEP4_1GetInfoAboutDebetCardsTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/agreements";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("type", "debet");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardAgreementInfo> cardAgreementInfo;

        Response response = getRequestP(paramsMap,headersMap,endPoint);
        cardAgreementInfo = response.getBody().jsonPath().getList(".", CardAgreementInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        //Проверяем тип карты
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals( "debet",x.getType()));
    }

    @Test
    public void CleanEP4_1GetInfoAboutCreditCardsTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/agreements";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("type", "credit");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardAgreementInfo> cardAgreementInfo;

        Response response = getRequestP(paramsMap,headersMap,endPoint);
        cardAgreementInfo = response.getBody().jsonPath().getList(".", CardAgreementInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        //Проверяем тип карты
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals( "credit",x.getType()));
    }

    @Test
    public void CleanEP4_1GetInfoAboutVirtualCardsTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("76666666666", "Ihave6Cards!");

        String endPoint = "/card/agreements";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("type", "virtual");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardAgreementInfo> cardAgreementInfo;

        Response response = getRequestP(paramsMap,headersMap,endPoint);
        cardAgreementInfo = response.getBody().jsonPath().getList(".", CardAgreementInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        //Проверяем тип карты
        cardAgreementInfo.stream().forEach(x -> Assertions.assertEquals("virtual",x.getType()));
    }

    @Test
    public void CleanEP4_1GetInfoAboutNoCardsClientTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("75335178939", "1z3rgtY&1y6");

        String endPoint = "/card/agreements";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("type", "virtual");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardAgreementInfo> cardAgreementInfo;

        Response response = getRequestP(paramsMap,headersMap,endPoint);
        cardAgreementInfo = response.getBody().jsonPath().getList(".", CardAgreementInfo.class);
        //присваиваем переменной ответ для обработки и проверки

        //Проверяем что ответ в body пустой, т.е. класс пуст и не имеет ни одного ответа в поле
        Assertions.assertEquals(0,cardAgreementInfo.size());
    }

    @Test
    public void CleanEP4_1GetInfoAboutUncorrectTypeCardTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/card/agreements";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        paramsMap.put("type", "1");

        headersMap.put("Authorization", "Bearer " + accessToken);

        List<CardAgreementInfo> cardAgreementInfo;

        checkStatusCode(responseSpecNotFound404());
        getRequestP(paramsMap,headersMap,endPoint);
    }

    @Test
    public void CleanEP4_1GetInfoAboutCardWithoutAuthorizationTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnauthorized401());

        CardRequests cardRequests = new CardRequests();

        cardRequests.getCardAgreementsInfoRequest("isActive","true");
    }
}
