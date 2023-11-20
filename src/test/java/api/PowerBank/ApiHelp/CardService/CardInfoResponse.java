package api.PowerBank.ApiHelp.CardService;

import api.PowerBank.ApiHelp.CardService.CardAgreementInfo;

import java.util.List;

public class CardInfoResponse {
    List<CardAgreementInfo> cardAgreementInfo;

    public CardInfoResponse(){};

    public CardInfoResponse(List<CardAgreementInfo> cardAgreementInfo) {
        this.cardAgreementInfo = cardAgreementInfo;
    }

    public List<CardAgreementInfo> getCardInfo() {
        return cardAgreementInfo;
    }
}
