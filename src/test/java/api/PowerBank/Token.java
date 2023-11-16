package api.PowerBank;

public class Token {
    public String accessToken;
    public String refreshToken;

    public Token(){
    }
    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
