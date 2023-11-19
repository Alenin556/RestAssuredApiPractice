package api.PowerBank.ApiHelp;

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
