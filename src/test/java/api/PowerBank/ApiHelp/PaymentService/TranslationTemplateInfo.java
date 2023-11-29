package api.PowerBank.ApiHelp.PaymentService;

import api.PowerBank.ApiHelp.GetToken;
import api.PowerBank.ApiHelp.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.PowerBank.ApiHelp.ApiRequests.getTranslationTemplateListRequest;
import static api.PowerBank.ApiHelp.Specifications.URL;

public class TranslationTemplateInfo {
    public String templateName;
    public String transferType;
    public String templateId;

    public TranslationTemplateInfo(){
    }

    public TranslationTemplateInfo(String templateName, String transferType, String templateId) {
        this.templateName = templateName;
        this.transferType = transferType;
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getTransferType() {
        return transferType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public static String getUUidTranslationTemplate(String accessToken , int UUidIndex) {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        String endPoint = "/payment/template";
        Map<String, String> paramsMap = new HashMap<>();
        Map<String, String> headersMap = new HashMap<>();
        paramsMap.put("isActive", "true");
        headersMap.put("Authorization", "Bearer " + accessToken);
        List<TranslationTemplateInfo> translationTemplateInfo = getTranslationTemplateListRequest(paramsMap, headersMap, endPoint);
        return translationTemplateInfo.get(UUidIndex).templateId;
    }
}
