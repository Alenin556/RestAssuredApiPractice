package api.PowerBank.tests.depositservice.EP3_2;

import api.PowerBank.ApiHelp.DepositService.DepositsInfo;
import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getDepositListRequest;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class ep3_2 {

    @Test
    public void CleanEP3_2GetInfoAboutDepositTest() {
        //Пред установки и пред проверка запроса на статус ответа
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        //Создаем объект для использования метода по отправке запроса
        GetToken getToken = new GetToken();
        String accessToken = getToken.accessToken("79772345685", "ls23Ghq#wEr");

        String endPoint = "/deposit/products";
        String depositName = "/SummerOffer";

        //Создаем коллекцию с необходимыми вводными параметрами и headers
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();

        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);

        List<DepositsInfo> depositsInfo = getDepositListRequest(paramsMap, headersMap, endPoint + depositName);
        //присваиваем переменной ответ для обработки и проверки

    }
}
