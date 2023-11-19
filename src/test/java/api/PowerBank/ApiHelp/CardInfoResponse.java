package api.PowerBank.ApiHelp;

import java.util.List;

public class CardInfoResponse {
    List<CardInfo> cardInfo;

    public CardInfoResponse(){};

    public CardInfoResponse(List<CardInfo> cardInfo) {
        this.cardInfo = cardInfo;
    }

    public List<CardInfo> getCardInfo() {
        return cardInfo;
    }
}
